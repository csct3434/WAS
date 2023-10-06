package webserver;

import db.UserDatabase;
import application.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import application.controller.index.IndexPageController;
import webserver.http.HttpRequest;
import webserver.http.HttpResponse;
import webserver.session.Session;
import db.SessionDatabase;
import webserver.utils.HttpRequestCreateUtil;

class IndexPageControllerTest {
    IndexPageController indexPageController = new IndexPageController();

    @BeforeAll
    static void init() {
        UserDatabase.addUser(new User("userId", "password", "userA", "email@naver.com"));
    }

    @Test
    @DisplayName("회원이 인덱스 페이지에 접속하면, 로그인 버튼 대신 사용자의 이름이 표시된다")
    void userRequestIndexPage() throws Exception {
        //given
        Session loginUserSession = new Session("userId");
        SessionDatabase.addSession(loginUserSession);

        String requestMessage = "GET /index.html HTTP/1.1\r\n"
                + "Cookie: sid=" + loginUserSession.getSessionId() + "\r\n"
                + "\r\n";

        //when
        HttpRequest httpRequest = HttpRequestCreateUtil.createHttpRequest(requestMessage);
        HttpResponse httpResponse = new HttpResponse();

        indexPageController.process(httpRequest, httpResponse);

        //then
        String bodyMessage = new String(httpResponse.getBodyBytes());
        Assertions.assertTrue(bodyMessage.contains("userA"));
        Assertions.assertFalse(bodyMessage.contains("<li><a href=\"user/login.html\" role=\"button\">로그인</a></li>"));
    }

    @Test
    @DisplayName("로그인하지 않은 사용자가 인덱스 페이지에 접속하면, 로그인 버튼이 표시되어야 한다")
    void guestRequestIndexPage() throws Exception {
        //given
        String requestMessage = "GET /index.html HTTP/1.1\r\n"
                + "\r\n";

        //when
        HttpRequest httpRequest = HttpRequestCreateUtil.createHttpRequest(requestMessage);
        HttpResponse httpResponse = new HttpResponse();

        indexPageController.process(httpRequest, httpResponse);

        //then
        String bodyMessage = new String(httpResponse.getBodyBytes());
        Assertions.assertTrue(bodyMessage.contains("<li><a href=\"user/login.html\" role=\"button\">로그인</a></li>"));
    }
}