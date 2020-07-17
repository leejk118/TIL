# Forum Project







#### 프로젝트 초기설정









## 사용자 인증 재구성

#### 라라벨 내장 인증 삭제

* `rm -rf resources/views/auth`

* `rm -rf app/Http/Controllers/Auth`

* 라라벨 내장 인증 라우터 삭제

  ```php
  // Route::auth();
  ```

  

#### routes/web.php

> - 사용자 가입 
>   - GET   auth/register   create
>   - POST   auth/register   store
>   - GET   auth/confirm/{code}   confirm
> - 사용자 인증
>   - GET   auth/login   create
>   - POST auth/login   store
>   - GET   auth/logout   destroy
> - 비밀번호 초기화
>   - GET   auth/remind   create
>   - POST   auth/remind   store
>   - GET   auth/reset/{token}   create
>   - POST   auth/reset   store

* ```php
  Route::get('auth/register', [
      'as' => 'users.create',
      'uses' => 'UsersController@create'
  ]);
  Route::post('auth/register', [
      'as' => 'users.store',
      'uses' => 'UsersController@store'
  ]);
  Route::get('auth/confirm/{code}', [
      'as' => 'users.confirm',
      'uses' => 'UsersController@confirm'
  ]);
  
  Route::get('auth/login', [
      'as' => 'sessions.create',
      'uses' => 'SessionsController@create'
  ]);
  Route::post('auth/login', [
      'as' => 'sessions.store',
      'uses' => 'SessionsController@store'
  ]);
  Route::get('auth/logout', [
      'as' => 'sessions.destroy',
      'uses' => 'SessionsController@destroy'
  ]);
  
  Route::get('auth/remind', [
      'as' => 'reset.create',
      'uses' => 'PasswordsController@getRemind'
  ]);
  Route::post('auth/remind', [
      'as' => 'reset.store',
      'uses' => 'PasswordsController@postRemind'
  ]);
  Route::get('auth/reset/{token}', [
      'as' => 'reset.create',
      'uses' => 'PasswordsController@getReset'
  ]);
  Route::get('auth/reset', [
      'as' => 'reset.store',
      'uses' => 'PasswordsController@postReset'
  ]);
  ```



#### Controller 생성

* `php artisan make:controller UsersController`

  `php artisan make:controller SessionsController`

  `php artisan make:controller PasswordsController`

* 라우터 리스트 생성 확인

  `php artisan route:list`



#### 사용자 등록 구현

* UsersController.php

  ```php
  class UsersController extends Controller {
      public function create(){
          return view('users.create');
      }
  }
  ```

* views/users/create.blade.php

  ```php+HTML
  @extends('layouts.app')
  
  @section('content')
      <form action="{{ route('users.store') }}" method="POST" class="form__auth">
          {!! csrf_field() !!}
  
          <div class="form-group {{ $errors->has('name') ? 'has-error' : '' }}">
              <input type="text" name="name" class="form-control" placeholder="이름" value="{{ old('name') }}" autofocus />
              {!! $errors-> first('name', '<span class="form-error">:message</span>') !!}
          </div>
  
          <div class="form-group {{ $errors->has('email') ? 'has-error' : '' }}">
              <input type="email" name="email" class="form-control" placeholder="email" value="{{ old('email') }}" autofocus />
              {!! $errors-> first('email', '<span class="form-error">:message</span>') !!}
          </div>
  
          <div class="form-group {{ $errors->has('password') ? 'has-error' : '' }}">
              <input type="password" name="password" class="form-control" placeholder="password" value="{{ old('password') }}" autofocus />
              {!! $errors-> first('password', '<span class="form-error">:message</span>') !!}
          </div>
  
          <div class="form-group {{ $errors->has('password') ? 'has-error' : '' }}">
              <input type="password" name="password_confirmation" class="form-control" placeholder="password_confirmation" value="{{ old('password') }}" autofocus />
              {!! $errors-> first('password_confirmation', '<span class="form-error">:message</span>') !!}
          </div>
  
          <div class="form-group">
              <button class="btn btn-primary btn-lg btn-block" type="submit">
                  가입하기
              </button>
          </div>
      </form>
  @endsection
  
  ```

  

#### 사용자 등록 요청 처리

* UsersController.php

  ```php
  public function store(Request $request){
      $this->validate($request, [
          'name' => 'required|max:255',
          'email' => 'required|email|max:255|unique:users',
          'password' => 'required|confirmed|min:6'
      ]);
  
      $user = \App\User::create([
          'name' => $request->input('name'),
          'email' => $request->input('email'),
          'password' => bcrypt($request->input('password'))
      ]);
  
      auth()->login($user);
      flash(auth()->user()->name . '님 환영합니다.');
  
      return redirect('home');
  }
  ```

  

#### 가입 확인 절차

> 현재는 가짜 이메일 정보로도 가입할 수 있는 상태
>
> 로직 : 사용자 등록 폼 처리 시, 사용자가 등록한 이메일 주소로 활성화 코드를 포함한 가입 확인 메일 전송 -> 메일에는 가입확인을 위해 우리 서비스로 다시 들어오는 URL을 포함, URL에 포함된 활성화 코드도 사용자를 찾아서 사용자 정보를 업데이트한다.



* 마이그레이션

  * 마이그레이션 파일 생성

    `php artisan make:migration add_confirm_code_column_on_user_table --table=users`

  * TIMESTAMP_add_confirm_code_users_table.php

    ```php
    public function up()
    {
        Schema::table('users', function (Blueprint $table) {
            $table->string('confirm_code', 60)->nullable();
            $table->boolean('activated')->default(0);
        });
    }
    ```

  * 마이그레이션 실행

    `php artisan migrate`

    > boolean() 메서드는 MySQL에서는 TINYINT 열 타입으로 해석된다. default(0) 메서드로 이 열의 기본값을 0으로 정의했다. 사용자가 가입을 확인하면, 값을 1로 바꿀 것이다.

    > sqlite에서는 TINYINT 값이 문자열로 저장됨

  * User.php

    ```php
    protected $fillable = [
        'name', 'email', 'password', 'confirm_code', 'activated'
    ];
    
    protected $hidden = [
        'password', 'remember_token', 'confirm_code'
    ];
    
    protected $casts = [
        'email_verified_at' => 'datetime',
        'activated' => 'boolean'
    ];
    ```

    * protected $casts = [...]
      * 모델에서 프로퍼티의 값을 조회할 때 자동으로 타입 변환할 목록을 정의
      * 사용 가능 타입은 integer, float, string, boolean, object, array, collection, date, datetime 등
    * protected $fillable = [...], protected $hidden = [...]
      * cofirm_code 열은 컨트롤러에서 대량 할당하므로 추가
      * 엘로퀀트 쿼리 결과에 표시할 필요는 없기 때문에 $hidden 속성도 지정



#### 사용자 등록 절차 수정

> 회원 가입 처리 메서드에서 사용자 테이블에 활성화 코드 기록하고 가입 확인 메일 보냄



* UsersController.php

  ```php
  
  ```

  