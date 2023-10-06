# java-was-2023

Java Web Application Server 2023

## 프로젝트 정보 

이 프로젝트는 우아한 테크코스 박재성님의 허가를 받아 https://github.com/woowacourse/jwp-was 
를 참고하여 작성되었습니다.
<br><br><br>
---
# Step-1
## 구현 사항
- [x] Concurrent 패키지의 Executors 클래스를 사용하여 멀티 스레드 방식을 적용
- [x] 소켓 입출력에 버퍼링을 적용
- [x] resources 디렉터리의 모든 파일에 대한 요청을 처리
- [x] 존재하지 않는 리소스 요청 시, 404 페이지를 404 status code로 응답
- [x] resources 디렉터리의 모든 파일(html, css, js, png, eot, svg, ttf, woff, woff2, ico)에 대한 컨텐츠 타입을 지원
- [x] Request-URI가 '/'인 경우, 인덱스 페이지를 응답

## 학습 내용
- HTTP
  - [HTTP/HTTPS](https://github.com/csct3434/be-was/wiki/HTTP%EC%99%80-HTTPS)
  - [HTTP 요청 및 응답](https://github.com/csct3434/be-was/wiki/HTTP-%EC%9A%94%EC%B2%AD-%EB%B0%8F-%EC%9D%91%EB%8B%B5)
  - [SSL/TLS](https://github.com/csct3434/be-was/wiki/SSL-TLS)
  - [세션](https://github.com/csct3434/be-was/wiki/%EC%84%B8%EC%85%98)
- MVC
  - [웹 애플리케이션의 이해](https://github.com/csct3434/be-was/wiki/MVC-%E2%80%90-1.-%EC%9B%B9-%EC%95%A0%ED%94%8C%EB%A6%AC%EC%BC%80%EC%9D%B4%EC%85%98%EC%9D%98-%EC%9D%B4%ED%95%B4)
  - [서블릿](https://github.com/csct3434/be-was/wiki/MVC-%E2%80%90-2.-%EC%84%9C%EB%B8%94%EB%A6%BF)
  - [서블릿, JSP, MVC패턴](https://github.com/csct3434/be-was/wiki/MVC-%E2%80%90-3.-%EC%84%9C%EB%B8%94%EB%A6%BF,JSP,-MVC%ED%8C%A8%ED%84%B4)
  - [MVC 프레임워크 만들기](https://github.com/csct3434/be-was/wiki/MVC-%E2%80%90-4.-MVC-%ED%94%84%EB%A0%88%EC%9E%84%EC%9B%8C%ED%81%AC-%EB%A7%8C%EB%93%A4%EA%B8%B0)
  - [구조 이해](https://github.com/csct3434/be-was/wiki/MVC-%E2%80%90-5.-%EA%B5%AC%EC%A1%B0-%EC%9D%B4%ED%95%B4)
  - [메시지 컨버터](https://github.com/csct3434/be-was/wiki/MVC-%E2%80%90-6.-%EB%A9%94%EC%8B%9C%EC%A7%80-%EC%BB%A8%EB%B2%84%ED%84%B0)
- ETC
  - [Logging](https://github.com/csct3434/be-was/wiki/%EB%A1%9C%EA%B9%85)
  - [Socket](https://github.com/csct3434/be-was/wiki/%EC%86%8C%EC%BC%93)
  - [Java Thread](https://github.com/csct3434/be-was/wiki/Java-Thread)
- [Concurrent Package](https://github.com/csct3434/be-was/wiki/concurrent-package)
<br><br><br>
---
# Step-2
## 구현 사항
- [x] GET으로 회원가입 기능 구현
- [x] URL Parameter 생략 시 Bad Request 응답
- [x] 회원정보 미입력 시 Bad Request 응답
- [x] 아이디가 중복되면 Conflict 응답
## 학습 내용
- [URL 인코딩](https://github.com/csct3434/be-was/wiki/URL-Encoding)
<br><br><br>
---
# Step-3
## 구현 사항
- [x] stylesheet와 파비콘 등을 위한 다양한 컨텐츠 타입 지원
  - html, css, js, png, eot, svg, ttf, woff, woff2, ico, jpg, jpeg
## 학습 내용
- [MIME Type](https://github.com/csct3434/be-was/wiki/MIME-Type)
<br><br><br>
---
# Step-4
## 구현 사항
- [x] 회원가입 요청 방식을 GET에서 POST로 변경
- [x] 회원가입 완료 후 /index.html 페이지로 이동
## 학습 내용
- [HTTP Method : GET vs POST](https://github.com/csct3434/be-was/wiki/GET-vs-POST)
- [Idempotent Method vs Safe Method](https://github.com/csct3434/be-was/wiki/Idempotent-Method-vs-Safe-Method)
- [HTTP Redirect](https://github.com/csct3434/be-was/wiki/HTTP-Redirect)
<br><br><br>
---
# Step-5
## 구현 사항
- [x] 로그인 기능 구현
  - 로그인이 성공하면 index.html로 이동한다.
  - 로그인이 성공할 경우 HTTP 헤더의 쿠키 값을 SID=세션 ID 로 응답한다.
  - 로그인이 실패하면 /user/login_failed.html로 이동한다.
## 학습 내용
- [Cookie & Session](https://github.com/csct3434/be-was/wiki/Cookie-&-Session)
<br><br><br>
---
# Step-6
## 구현 사항
- [x] 사용자가 로그인 상태일 경우 /index.html에서 사용자 이름을 표시해 준다.
- [x] 사용자가 로그인 상태가 아닐 경우 /index.html에서 [로그인] 버튼을 표시해 준다.
- [x] 사용자가 로그인 상태일 경우 http://localhost:8080/user/list 에서 사용자 목록을 출력한다.
- [x] http://localhost:8080/user/list  페이지 접근시 로그인하지 않은 상태일 경우 로그인 페이지(login.html)로 이동한다.
<br><br><br>
---
# Step-7
- [x] 글쓰기 기능 구현
  - index.html 하단에 글쓰기 버튼을 추가
  - 로그인한 사용자가 글쓰기 버튼을 클릭하면 write.html로 이동
  - 로그인하지 않은 사용자가 글쓰기 버튼을 클릭하면 로그인 페이지로 이동
  - write.html에서 글의 본문을 입력할 수 있는 폼을 표시
  - 작성된 게시글의 목록을 index.html에서 표시
- [x] 세부 글 표시 기능 구현
  - 로그인한 사용자가 글 제목 클릭 시 세부 내용을 볼 수 있는 페이지로 이동
  - 로그인하지 않은 사용자가 글 제목 클릭 시 로그인 페이지로 이동
<br><br><br>
---