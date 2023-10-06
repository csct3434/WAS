package webserver.controller.user;

import application.controller.user.UserLoginController;
import db.UserDatabase;
import application.model.User;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import webserver.http.HttpRequest;
import webserver.http.HttpResponse;
import webserver.http.HttpStatus;
import webserver.utils.HttpField;
import webserver.utils.HttpRequestCreateUtil;

public class UserLoginControllerTest {

    @BeforeAll
    static void init() {
        UserDatabase.addUser(new User("userId", "password", "name", "email@test.com"));
    }

    @Test
    @DisplayName("로그인 성공 시, Set-Cookie에 세션 아이디와 Path 속성이 설정되어야 한다")
    void validLoginTest() throws Exception {
        //given

        String body = "userId=userId&password=password";

        String requestMessage = "POST /user/login HTTP/1.1\r\n"
                + "Content-Type: application/x-www-form-urlencoded\r\n"
                + "Content-Length: " + body.length() + "\r\n"
                + "\r\n"
                + body;

        HttpRequest httpRequest = HttpRequestCreateUtil.createHttpRequest(requestMessage);
        HttpResponse httpResponse = new HttpResponse();

        UserLoginController userLoginController = new UserLoginController();

        //when
        userLoginController.process(httpRequest, httpResponse);

        //then
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(httpResponse.getStatus()).isEqualTo(HttpStatus.FOUND);
        softAssertions.assertThat(httpResponse.get(HttpField.LOCATION)).isEqualTo("/index.html");
        softAssertions.assertThat(httpResponse.getCookie().getMessage()).contains("SID");
        softAssertions.assertThat(httpResponse.getCookie().getMessage()).contains("Path=/");
    }

    @Test
    @DisplayName("로그인 실패 시, 로그인 실패 페이지로 리다이렉트 시키고, Set-Cookie 헤더가 없어야 한다")
    void invalidLoginTest() throws Exception {
        //given
        String body = "userId=userId&password=qweqwe";

        String requestMessage = "POST /user/login HTTP/1.1\r\n"
                + "Content-Type: application/x-www-form-urlencoded\r\n"
                + "Content-Length: " + body.length() + "\r\n"
                + "\r\n"
                + body;

        HttpRequest httpRequest = HttpRequestCreateUtil.createHttpRequest(requestMessage);
        HttpResponse httpResponse = new HttpResponse();

        UserLoginController userLoginController = new UserLoginController();

        //when
        userLoginController.process(httpRequest, httpResponse);

        //then
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(httpResponse.getStatus()).isEqualTo(HttpStatus.FOUND);
        softAssertions.assertThat(httpResponse.get(HttpField.LOCATION)).isEqualTo("/user/login_failed.html");
        softAssertions.assertThat(httpResponse.getHeaderMessage()).doesNotContain("Set-Cookie");
        softAssertions.assertAll();
    }

}
