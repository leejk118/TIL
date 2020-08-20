PHP +



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

