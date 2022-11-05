# React JS

> https://reactjs.org/


## Add React in One Minute
- 1단계: HTML에 DOM 컨테이너 추가하기
    ```html
      <div id="like_button_container"></div>
    ```
    - ㅇㅁㄴㅇㄹ
- 2단계: 스크립트 태그 추가하기
    ```html
  <!-- Load React. -->
  <!-- Note: when deploying, replace "development.js" with "production.min.js". -->
  <script src="https://unpkg.com/react@18/umd/react.development.js" crossorigin></script>
  <script src="https://unpkg.com/react-dom@18/umd/react-dom.development.js" crossorigin></script>

  <!-- Load our React component. -->
  <script src="like_button.js"></script>
    ```
- 3단계: 리액트 컴포넌트 생성하기
    ```html
    const domContainer = document.querySelector('#like_button_container');
    const root = ReactDOM.createRoot(domContainer);
    root.render(e(LikeButton));  
    ```
    
## Create a New React App
- 추천 ToolChain
    - `Create React App`
    - `Next.js`
    - `Gatsby`
    
- `Create React App`
    - 요구사항
        - Node >= 14.0.0
        - npm >= 5.6
    - 설치
        ```bash
        npx create-react-app my-app
        cd my-app
        npm start
        ``` 
    - 설명
        - 백엔드/데이터베이스 다루지 않음
        - 프론트엔드 빌드 파이프라인만 생성
        - babel과 webpack을 사용
        - 운영 환경으로 배포 시 `npm run build`를 사용하면 최적화된 빌드 폴더를 생성해줌
        
- `Next.js`
    - 유명하고 가벼운 프레임워크
    - 정적이고 서버 렌더링 어플리케이션 
    


# Main Concepts
## 1. Hello World
- 리액트 예시
    ```html
    const root = ReactDOM.createRoot(document.getElementById('root'));
    root.render(<h1>Hello, world</h1>);
    ```
> 리액트는 자바스크립트 라이브러리

## 2. Introducing JSX


















