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


## PHP Echo / Print
## PHP Data Types@@@@@
* PHP는 다음의 데이터 타입을 지원
    
    * String, Integer, Float, Boolean, Array, Object, NULL, Resource
* PHP **var_dump()** 함수
    * 데이터 타입과 값을 리턴해주는 함수

    ### PHP String
    * "Hello world!"
    * single or double quotes 사용
    ### PHP Integer
    * 12, 3, 5983, 222
        ```php
        $x = 5985;
        var_dump($x); // int(5985)
        ```
    
    ### PHP Float
    

## PHP Strings
### strlen()
* 문자열의 길이 리턴
```php
 echo strlen("Hello world!"); // 12
```
### str_word_count()
* 문자열안의 단어 개수 리턴
```php
echo str_word_count("Hello world!"); // 2
```
### strrev()
* 문자열을 뒤집어서 리턴
```php
echo strrev("Hello world!"); // !dlrow olleH
```
### strpos()
* 문자열에서 텍스트 검색
```php
echo strpos("Hello world!", "world"); // 6
```
### str_replace()
* 문자열 내의 텍스트 대체
```php
echo str_replace("world", "Dolly", "Hello world!"); // Hello Dolly!
```
> string reference : https://www.w3schools.com/php/php_ref_string.asp



## PHP Numbers@@@@@
## PHP Math@@@@@
## PHP Constants@@@@@
* 유효한 상수이름은 문자나 _로시작한다. (`$` 사인이 붙지 않는다.)
* 변수와 달리, 상수는 자동적으로 글로벌하게 사용가능하다.

    ### PHP 상수 생성
    * 상수를 만들기 위해선 `define()` 함수를 사용한다.
    * Syntax
    define(name, value, case-insensitive)
        * name : 상수의 이름을 정의
        * value : 상수의 값 정의
        * case-insensitive : 상수 이름이 대소문자 구분하는 지 정의, 기본은 false 값
    * PHP 상수 배열
        * PHP 7에서는배열도 상수로 생성 가능하다.
            ```php
            define("cars", [
                "Audi",
                "BMW",
                "Toyota"
            ]);
            ```
    

## PHP Operators
### 산술연산자
### 대입연산자
### 비교연산자
### 증감연산자
### 논리연산자
### 문자열연산자
* `.`
    * Concatenation
        ```php
        $txt1 = "Hello";
        $txt2 = " world!";
        echo $txt1 . $txt2; // Hello world!
        ```
* `.=`
    * Concatenation assignment
        ```php
        $txt1 = "Hello";
        $txt2 = " world!";
        $txt1 .= $txt2;
        echo $txt1; // Hello world!
        ```
        










## PHP OOP
### Classes and Objects
* Class 정의
    * Syntax
        ```php
        <?php
        class Fruit {
            //
        }
        ?>
        ```
        ```php
        <?php 
        class Fruit {
            // properties
            public $name;
            public $color;
            
            // methods
            function set_name($name){
                $this->name = $name;
            }
            function get_name(){
                return $this->name;
            }
        }
        ```
    * 클래스에서 변수들은 프로퍼티, 함수들은 메서드라고 불린다.
* object 정의
    * 클래스의 오브젝트들은 `new`키워드로 생성된다.
        ```php
        class Fruit {
            //
        }
        
        $apple = new Fruit();
        $banana = new Fruit();
        
        $apple->set_name('Apple');
        ```
* `$this` 키워드
    
* 현재 객체를 말하고, 메서드 안에서만 사용가능하다.
    
* `instanceof` 키워드
    * 오브젝트가 특정 클래스에 속해있는지 확인할 수 있다.
        ```php
        <?php
        $apple = new Fruit();
        var_dump($apple instanceof Fruit); // bool(true)
        ?>
    ```
    
* `__contstruct` Function
    * 생성자는 객체 생성 시 프로퍼티를 초기화할 수 있다.
    * 클래스에서 `__construct()`함수를 만들면, PHP는 자동적으로 객체를 생성할 때 이 함수를 부른다.
    * 언더스코어 2개로 시작함에 유의하자.

* `__destruct` Function
    * 소멸자는 객체가 소멸되거나 스크립트가 멈춤 혹은 종료 시 불린다.
    * 클래스에서 `__destruct()`함수를 만들면 PHP는 자동적으로 스크립트의 마지막에 이 함수를 부른다.
    * 언더스코어 2개로 시작함에 유의하자.

* Access Modifiers
    * properties와 methods는 어디서 접근 가능할지 정할수 있는 접근제한자를 가질 수 있다.
    * 3가지 접근 제한자가 존재한다.
        * `public`
            property 또는 method를 어디에서나 접근가능. default값
        * `protected`
            property 또는 method를 클래스 내에서 또는, 그 클래스로 부터 파생된 클래스에서만 접근 가능.
        * `private`
            오직 클래스 내에서만 접근 가능.
    
* 상속 (Inheritance)





---



- stdClass()

  - 빈 클래스

- explode

  - 출처 https://www.codingfactory.net/10702
  - 문자열을 분할하여 배열로 저장하는 함수
  - PHP 4 이상
  - `explode(delimiter, string [, limit] )`
    - delimiter : 문자열 분할 기준
    - string : 분할할 문자열
    - limit : 옵션으로 분할할 개수 지정 (정수)

  > $jbstring = 'one two three four';
  >
  > $jbexplode = explode(' ', $jbstring);
  >
  > [0] => one, [1] => two, [2] => three four

  - limit가 음수인 경우 뒤에서 갯수만큼 제거

- 중복연산자 `??` (null coalescing operator)

  - 널 병합 연산자

  - PHP 7

  - 앞의 변수가 설정돼 있지 않으면 뒤의 값을 사용한다.

  - ```php
    $a = isset($a) ? $a : 1;
    // 위와 동일
    $a = $a ?? 1;
    ```

- 





---



### short_tag

> 출처 https://unikys.tistory.com/246

- php에는 `<?= $var ?>`와 같이 short tag로 HTML 소스 중간에 변수 출력을 간단하게 할 수 있었다.
- 이 태그는 xml의 헤더에도 `<?`가 사용되는 것과 혼돈되기 때문에 권장하지 않는다.
- `<?php echo $var ?>` 방식으로 사용할 것을 지향한다.

