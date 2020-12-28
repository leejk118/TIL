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

- 

