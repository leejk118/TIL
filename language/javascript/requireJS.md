# Require JS

>  출처 https://requirejs.org/



## 1.1 자바스크립트 파일 로드하기

- RequireJS 는 `<script>` 태그와 다른 방식으로 스크립트 파일을 로딩한다.

- 빠르게 동작하고 최적화가 잘 되어있고, 모듈러 코드를 지향

  - **모듈 ID**를 사용하는 것을 권장 (스크립트 태그 URL 대신)

- 모든 코드를 `baseUrl` 상대경로로 로드한다.

- `baseUrl`은 보통 `data-main` 속성이 사용된 스크립트 태그가 있는 같은 디렉토리로 설정되거나 

- `data-main` 속성은 require.js가 스크립트를 로딩하는 것을 체크하는 특수한 속성이다.

  - ```js
    <script data-main="scripts/main.js" src="scripts/require.js"></script>
    ```

- `baseUrl`은 `RequireJS config`를 통해 수동으로 설정할 수 있다.

- 외부 config 파일이 없거나 data-main이 사용되지 않으면, 디폴트 `baseUrl`은 requireJS가 실행되는 HTML 페이지가 포함된 디렉토리로 설정된다.

- 모든 의존성들을 기본적으로 스크립트라고 가정하기 때문에 `module ID`에 `'.js'`라는 접미사를 붙일 필요 없다.

  - module ID를 경로로 translating 할 때 requireJS가 자동적으로 붙여줄 것이다.

- paths config 시, 스크립트 그룹의 위치를 설정할 수 있다.




- 모듈 ID의 경로를 설정하기 위해 `baseUrl` 과 `paths` config 를 사용하는 것이 좋다.
  - 그래야 이름을 바꾸거나 경로를 다시 설정할 때 최적화되기 때문
- 



### 1.2 data-main Entry Point

- `data-main` 속성은 requirejs가 스크립트를 로딩하기 시작하는지 체크해주는 특별한 속성이다.

- Note : 스크립트 태그 require.js는 async 속성이 포함된  `data-main` 모듈로 만들 것이다.

- ㄴㄴ

- `require()` 함수를 HTML 페이지에서 호출하고 싶으면, `data-main`을 사용하지 않는 것이 최선이다.

  - `data-main`은 오직 페이지가 하나의 메인 엔트리포인트를 가지고 있을 때만 사용되도록 의도되어 있다.

  - 인라인 `require()` 를 호출하고 싶으면, 이 함수를 `require()` 함수 호출 내부에 묶는 것이 가장 좋다.

    ```js
    <script src="scripts/require.js"></script>
    <script>
    require(['scripts/config'], function () {
        // Configuration 이 로드된 후, 다른 require 호출이 해당 config에 의존하여 호출하게 된다.
        require(['foo'], function (foo) {
            //
        });
    })
    ```



### 1.3 모듈 정의

- 모듈은 기존 스크립트 파일과 다른데, well-scoped 객체가 정의되어 있다. 전역 네임스페이스를 오염시키는 것을 방지하기 위해
- ㅇㅇ
- RequireJS의 모듈들은 모듈패턴의 확장이다.







## 3. Configuration options

- `require()` 를 HTML 페이지 윗부분(또는 모듈이 정의되지 않은 맨 위 스크립트 파일)일 경우, configuration 객체는 첫번째 옵션으로 전달될 수 있다.
  ```javascript
  <script src="scripts/require.js"></script>
  <script>
      require.config({
      	baseUrl: "/another/path",
  	    paths: {
              "some": "some/v1.0"
          },
      	waitSeconds: 15
  	});
  	require(["some/module", "my/module", "a.js", "b.js"], {
          function(someModule, myModule) {
          	// 이 함수는 배열에 있는 의존성들이 모두 로드된 후 호출된다.
              // 이 함수는 페이지가 로드되기 전에도 호출될 수 있다.
              // 이 콜백함수는 옵션
          }
      });
  </script>
  ```

- `require.config`를 data-main 시작점에서 부를 수도 있지만, data-main 스크립트는 비동기적으로 로딩됨을 유의해야 한다.
- 

