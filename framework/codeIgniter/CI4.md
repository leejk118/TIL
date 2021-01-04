ci4



## setup

#### composer installation

- `php.ini` 파일에서 extension:intl 주석해제

- `composer create-project codeigniter4/appstarter projectname`

#### Run

- `php spark serve`



reference

- xampp와 mysql workbench 사용
  - https://stackoverflow.com/questions/57774867/using-xampp-and-mysql-workbench-together





## Application 구조

#### 기본 디렉토리

- app
  - 작성한 모든 어플리케이션 코드가 있는 곳
  - /app
    - /Config : 구성 파일 저장
    - /Controllers : 프로그램 흐름을 결정하는 컨트롤러
    - /Database : 데이터베이스 마이그레이션 및 시드 파일 저장
    - /Filters : 컨트롤러 전후에 실행할 수 있는 필터 클래스 저장
    - /Helpers : 독립형 함수 모음 저장
    - /Language : 다국어 지원을 위한 언어 파일 저장
    - /Libraries : 카테











## Controller 와 Routing







#### IncomingRequest Class

* Accessing the Request

  - 클래스가 `CodeIgniter\Controller`를 상속 받았다면 클래스의 request 속성을 통해 request 클래스 인스턴스에 엑세스 할 수 있다.

  - 컨트롤러가 아닌 곳에서 Request 객체에 엑세스해야 하는 경우 Services class를 통해 사본을 얻을 수 있다.

    - ```php
      $request = \Config\Services::request();
      ```

  - 컨트롤러 이외으 ㅣ클래스에서 클래스 속성으로 엑세스하고 싶다면 Request를 종속성으로 전달하는 것이 좋다.

    - ```php
      class SomeClass
      {
          protected $request;
          
          public function __contstruct(RequestInterface $request)
          {
              $this->request = $request;
          }
      }
      
      $someClass = new SomeClass(\Config\Services::request());
      ```



* 요청 유형 결정

  * 요청은 AJAX 요청 또는 커맨드 라인에서의 요청 등 여러 유형을 포함하며, `isAJAX()`와 `isCLI()` 메서드로 확인 가능

    * ```php
      $request->isAJAX();
      $request->isCLI();    
      ```

  * `getMethod()` 메소드를 이용하여 요청중인 HTTP 메소드 확인 가능

    * ```php
      $method = $request->getMethod();
      ```

    * 기본적으로 소문자로 값을 반환, 매개 변수로 `true`를 전달하면 대문자로 반환

    * ```php
      $request->getMethod(true);
      ```

  * `isSecure()` 메소드를 이용하여 HTTPS 연결을 통해 요청이 이루어 졌는지 확인 가능

    * ```php
      if (! $request->isSecure())
      {
          force_https();
      }
      ```



- 입력 검색

  - Request 객체를 통해 `$_SERVER`, `$_GET`, `$_POST`, `$_ENV`, `$_SESSION` 에서 입력을 검색할 수 있다.

  - 데이터는 자동으로 필터링 되지 않으며 요청에 전달된대로 입력 데이터를 리턴

  - 전역 변수($_POST['something'])를 직접 엑세스하는 대신 이러한 메소드를 사용하는 것의 주된 장점은 항목이 존재하지 않으면 null을 리턴하고 데이터를 필터링할 수 있다는 것.

    - ```php
      $something = isset($_POST['foo']) ? $_POST['foo'] : NULL;
      ```

      > 다음과 같이 항목이 먼저 존재하는지 테스트하지 않고도 편리하게 데이터를 사용 가능

    - ```php
      $something = $request->getVar('foo');
      ```

  - `getVar()` 메소드는 `$_REQUEST`에서 데이터를 가져오므로, `$_GET`, `$_POST`, `$_COOKIE`의 모든 데이터를 반환한다. 

    - 더욱 구체적인 방법을 사용할 땐 다음을 사용한다.
      - `$request->getGet()`
      - `$request->getPost()`
      - `$request->getServer()`
      - `$request->getCookie()`
    - 가져오는 순서를 제어하는 기능도 제공
      - `$request->getPostGet()`
        - POST 체크 후 GET
      - `$request->getGetPost()`
        - GET 체크 후 POST

  - JSON 데이터 가져오기

    - `getJSON()`을 사용하여 `php://input`의 내용을 JSON으로 가져올 수 있다.

      > 들어오는 데이터가 유효한 JSON인지 확인할 수 없으므로, JSON인 경우에만 사용해야 한다.

    - ```php
      $json = $request->getJSON();
      ```

    - ```php
      $json = $request->getJSON(true);
      ```

      > 기본적으로 JSON 데이터의 모든 객체는 PHP 객체로 반환.
      >
      > 연관 배열로 반환하려면 첫 번째 매개변수로 true 전달.
      >
      > 두 번째와 세 번째 매개 변수는 PHP 함수 json_decode의 depth, options 매개 변수와 일치.

  - 원시(raw) 데이터 검색(PUT, PATCH, DELETE)

    - `getRawInput()`을 사용하여 `php://input`의 내용을 원시 스트림으로 가져올 수 있다.

    - ```php
      $data = $request->getRawInput();
      ```

      > 데이터를 검색하여 배열로 반환

  - 입력 데이터 필터링

    - 어플리케이션의 보안을 유지하려면 엑세스하는 모든 입력을 필터링해야 한다.

    - 위 메소드들의 두 번째 매개 변수로 필터 유형을 전달할 수 있다. (getJSON 제외)

    - `filter_var()` 네이티브 함수가 필터링에 사용된다.

    - ```php
      $email = $request->getVar('email', FILTER_SANITIZE_EMAIL);
      ```



- 헤더 검색
  - `getHeaders()` 메소드로 요청과 함께 전송된 모든 헤더에 액세스 할 수 있다.

























