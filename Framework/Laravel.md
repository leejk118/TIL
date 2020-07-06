# Laravel Framework
* laravel 5.8 version setup
    `composer create-project laravel/laravel projectName "5.8.*"`

## 라라벨 5.8 설치
* php 설치
    * `php -v` 로 버젼확인
    * window는 git bash를 통해 확인 가능
* composer 설치
    * terminal에 `composer`로 설치 확인
* Laravel 설치
    * `laravel`로 설치 확인
* 서버 실행
    * `php artisan serve`
    * `http://127.0.0.1:8000` 접속 해서 확인

## 라라벨 기본 라우팅
* app
    *  Model
*  app/Http
    *   Controller
*   resources/views
    *   view
*   routes/web.php
    ```php
    Route::get("/", function(){
        return view('welcome');
    });
    ```
    `http://127.0.01:8000/`으로 접속하면 `resources/views`에 있는 `welcome.blade.php`파일을 실행


## 블레이드 레이아웃
> 라라벨에서 제공하는 PHP 템플릿
```php
Route::get('/contact', function(){
    return view('contact');
});
```
* @yield()
* @extends()
* @section() / @endsection()

## 블레이드로 데이터 전송
```php
Route::get('/', function(){
    $books = [
        'Harry Potter',
        'Laravel'
    ];
    return view('welcome', [
        'books' => $books
    ]);
});
```
> welcome 블레이드에서 books 데이터 사용 가능
```php
@section('content')
    Welcome
    <ul>
        <?php foreach($books as $book): ?>
            <li><?php echo $book; ?></li>
        <?php endforeach; ?>
    </ul>
@endsection
```
```php
@foreach($books as $book)
    <li> {{ $book }} </li>
$endforeach
```
> 중괄호 안에 있으면 자바스크립트 명령어를 실행하지 않는다.

* 위와 동일
```
return view('welcome')-> with([
    'books' => $books
]);
```
```
return view('welcome')->withBooks($books);
```


## 컨트롤러
`php artisan make:controller --help`
* 컨트롤러 생성
    `php artisan make:controller HomeController`
    > `app/Http/Controllers`밑에HomeController 클래스가 생성됨
* 설정
    * HomeController.php 파일
        ```php
        class HomeController extends Controller{
            public function index(){
                $books = [
                    'Harry Porter',
                    'Laravel'
                ];
                return view('welcome', [
                    'books' => $books    
                ]);
            }
        }
        ```
    * web.php
        ```php
        Route::get('/', 'HomeController@index'); 
        ```
     > HomeController에서 `index`라는 함수를 실행


## 데이터베이스 연결과 마이그레이션
* mysql 설치(포트 3306)
* .env
    * 
* config/database.php
    * 
* phpMyadmin 또는 등등 사용
    * DB 생성 (이름 : chobo)
    * 
* database/migrations
    * up()
        * 마이그레이션 실행 시 
    * down()
        * 삭제
    * `php artisan migrate`
        *  마이그레이팅 실행
    *  `php artisan  migrate:rollback`
    *  `php artisan migrate:fresh`
        *  데이터베이스 삭제하고 다시 실행


## 모델
* 마이그레이션 생성
    `php artisan make:migration create_projects_table`
    > 테이블명 복수형

    * 생성 날짜와 함께 이름이 정해짐
    * 실행 하면 전에 실행 되었던 파일은 실행 되지 않음
    * `날짜_create_projects_table.php`
        ```php
        public function up(){
            Schema::create('projects', function(Blueprint $table){
               $table->bigIncrements('id');
               $table->unsingedBigInteger('user_id');
               $table->string('title');
               $table->longText('description');
               $table->timestamps();
               
               $table->foreign('user_id')->references('id')->on('users');
            });
        }
        ```
        > timestamps()를 하면 created_at과 update_at이 생성
        
        > user_id가 users 테이블의 id를 참조
    * `php artisan migrate`

### 모델 생성
* `php artisan make:model Project`
    > model은 단수형

    * app 밑에 생성됨
* `/projects` 로 접근 시 데이터베이스에서 불러온 데이터 출력하기
    * web.php
        ```php
        Route::get('/projects', 'ProjectController@index');
        ```
    * ProjectController.php
        > php artisan make:controller ProjectController
        
        ```php
        public function index(){
            $projects = \App\Project::all();
            
            return view('projects.index', [
                'projects' => $projects
            ]);
        }
        ```
    * views/projects/index.blade.php
        ```php
        @extends('layout')
        
        @section('content')
            @foreach($projects as $project)
                Title: {{ $project->title }} <br>
                Description : {{ $project->description }}
            @endforeach
        @endsection
        ```
        
## tailwindcss 설치
* package.json
    *  설치된 것확인가능
* npm 설치
    * `node -v` , `npm -v`으로 확인
    * `npm install`
        >package.json의 패키지 설치
* `npm install tailwindcss`
* resources/css
* mix 
* 


## 테스크 MVC 파일 생성
* `php artisan make:model Task -c -m`
    > controller와 migration 파일도 생성
* `/tasks`로 접속 시 
    * migration_file
        ```php
        public function up(){
            Schema::create('tasks', function(Blueprint $table){
               $table->bigIncrements('id');
               $table->string('title');
               $table->longText('body');
               $table->timestamps();
            });
        }
        ```
    * web.php
        ```php
        Route::get('/tasks', 'TaskController@index');
        ```
    * TaskController.php
        ```php
        public function index(){
            return view('tasks.index', [
                
            ]);
        }
        ```
    * resources/views/tasks/index.blade.php
        ```php
        @extends('layout')
        
        @section('title')
            Tasks
        @endsection
        
        @section('content')
            <h1 class="font-bold text-3xl"> Task Test </h1>
        @endsection
        ```

* task 생성 폼
    * `tasks/create`로 접속
    * web.php
        ```php
        Route::get('/tasks/create', @TaskController@create);
        ```
    * create.blade.php
        ```php
        @extends('layout')
        
        @section('title', 'task create')
        
        @section('content')
            <h1 class="font-bold text-3xl">create Task</h1>
        @endsection
        ```
    * TaskController
        ```php
        public function create(){
            return view('tasks.create');
        }
        ```

## 태스크 추가 폼 구현
* 폼 구현
    * create.blade.php
        ```php
        <div class="px-64">
            <form action="/tasks" method="POST">
                <label class="block" for="title">black</label>
                <input class="border w-full" type="text" name="title" id="title"><br>
                <label class="block" for="body">Body</label` >
                <textarea class="border w-full" name="body" id="body" cols="30" rows="10"></textarea><br>
                
                <button class="bg-blue-600 text-white px-4 py-2 float right">Submit</button>
            </form>
        </div>
        ```
    * web.php
        ```php
        Route::post('/tasks', 'TaskController@store');
        ```
    * TaskController.php
        ```php
        use App\Task;
        
        public function store(Request $request){
            $task = Task::create([
                'title' => $request->input('title');
                'body' => $request->input('body');
            ]);

            return redirect('/tasks');
        }
        ```
    * Task.php
        ```php
        class Task extends Model {
            protected $filable = ['title', 'body'];
        }
        ```
        > $guarded : 넣지 못하게
    
    * TaskController.php
        ```php
        public function index(){
            $tasks = Task::all();
            
            return view('tasks.index',[
                'tasks' => $tasks;
            ]);
        }
        ```
    * index.blade.php
        ```php
        @section('content')
        <div class="px-64">
            <h1 class="font-bold text-3xl">Tasks List</h1>
            <ul>
                  @foreach($tasks as $task)
                    <li class="border m-3 p-3">Title: {{ $task->title }} <small class="float-right">Created at {{ $tasks->created_at }} </small></li>
                    @endforeach
            </ul>
        </div>
        @endsection
        ```

## 태스크 디테일 페이지
> task id를 통해 task 정보를 받아오기

> GET /tasks/{id}

* web.php





