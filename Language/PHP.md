# PHP

출처 : https://www.w3schools.com/php

## PHP Syntax
### Basic PHP Syntax
* PHP 스크립트가 서버에서 실행되어, plain HTML 결과가 브라우저로 돌아온다.
* PHP 스크립트는`<?php`로시작하고 `?>`로 끝난다.
* PHP 파일의 기본 확장자는 `.php`이다.
* PHP 스크립트는내장 PHP 함수로 `echo`를 사용하여 텍스트를 웹페이지에 출력할 수 있다.
* PHP 구문은 세미콜론`;`으로 끝난다.

### PHP Case Sensitivity
* PHP에서 키워드들(`if`, `else`, `while`, `echo`, etc.), 클래스, 함수, 사용자 정의 함수들은 대소문자를 구분하지 않는다.
* 그러나 모든 변수명은 대소문자를 구분한다.
    > 예: `$color`, `$COLOR`, `$coLOR`는 모두 다르게 인식된다.


## PHP Comments
* single-line 주석 : `//`
* multiple-line 주석 : `/*  */`


## PHP Variables
* 변수는 정보를 저장하는 "containers"
* PHP 변수 선언
    * PHP에서 변수는 `$`으로 시작하고, 이름이 뒤에 붙는다.
    * text 값을 할당한 땐, quotes를 변수 앞뒤에 써준다.
    * 다른 언어들과 달리, PHP는 변수를 선언하는 명령이 없다. 변수에 값을 처음 할당하는 순간 변수가 생성된다.
* PHP 변수들
    * PHP 변수 규칙
        * 변수는 `$`로 시작하고, 이름이 뒤에 붙는다.
        * 변수명은 문자나 `_`로 시작해야 한다.
        * 변수명은 숫자로 시작할 수 없다.
        * 변수명에는 오직 알파벳, 숫자, 언더스코어만 포함할 수 있다. (A-z, 0-9, _)
        * 변수명은 대소문자를 구분한다.
* 변수 출력하기
    * PHP `echo`문은 데이터를 화면에 출력할 때 종종 사용된다.
    ```php
    <?php
    $txt = "PHP";
    echo "This is $txt!";
    // or
    echo "This is" . $txt . "!";
    ?>
    ```
* PHP는 Loosely Typed Language
    * PHP는 값에 따라 변수의 데이터타입을 결정한다.
    * 데이터 타입이 엄격히 정해져 있지 않기 때문에, 문자열과 정수를 더해도 에러가 발생하지 않는다.
    * PHP 7에서는, 타입 선언이 더해졌다. 타입이 맞지 않을땐, "Fatal Error"를 던져준다.

* PHP 변수 스코프
    * PHP에서 변수는 스크립트 안이라면 어디든 선언가능하다.
    * PHP는 3가지 변수 스코프를 가지고 있다.
        * local
        * global
        * static

* Global, Local Scope
    * GLOBAL SCOPE
        * 함수 밖에서 선언된 변수는 글로벌 스코프를 가지고, **함수 밖**에서만접근이 가능하다.
    * LOCAL SCOPE
        * 함수언에서 선언된 변수는 로컬 스코프를 가지고, **함수 안**에서만 접근이 가능하다.

* global 키워드
    * `global` 키워드는 글로벌 변수를 함수 안에서 접근할때 사용한다.
    * 사용법은 `global` 키워드를 변수 앞에 적는다. (함수 안에서)
        ```php
        <?php
        $x = 5;
        $y = 10;
        function myTest(){
            global $x, $y;
            $y = $x + $y;
        }
        myTest();
        echo $y; // 15
        ?>
        ```
    * PHP는 모든 글로벌 변수들을 `$GLOBALS[index]`라는 배열 안에 저장한다. `index`는 변수명이다. 이 배열은 함수 안에서도 접근이 가능하며, 글로벌 변수를 직접적으로 변경도 가능하다.
        ```php
        <?php
        $x = 5; $y = 10;
        function myTest() {
            $GLOBALS['y'] = $GLOBALS['x'] + $GLOBALS['y'];
        }
        myTest();
        echo $y; // 15
        ?>
        ```
    
* static 키워드
    * 일반적으로, 함수가 실행이 끝나게 되면  그 안의 변수들은 사라지게 된다. 이 지역변수들을 사라지지 않게 해주는 것이 static 이다.
    * `static` 키워드를 처음 변수 선언시 사용한다.
        ```php
        <?php
        function myTest(){
            static $x = 0;
            echo $x;
            $x++;
        }
        myTest(); // 0
        myTest(); // 1
        myTest(); // 2
        ?>
        ```










