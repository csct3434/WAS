package webserver.http;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import webserver.utils.HttpField;

class HttpResponseTest {
    @Test
    @DisplayName("HTTP 응답 헤더를 작성할 수 있어야 한다")
    void createMessage() {
        //given
        HttpResponse httpResponse = new HttpResponse();

        String responseMessage = "HTTP/1.1 200 OK\r\n" +
                "Authorization: Bearer ABCD1234\r\n" +
                "Content-Length: 0\r\n" +
                "Content-Type: text/html;charset=utf-8\r\n" +
                "Set-Cookie: sid=1321312; authorization=412y942bdb\r\n";

        //when
        httpResponse.setStatus(HttpStatus.OK);
        httpResponse.set(HttpField.CONTENT_TYPE, "text/html;charset=utf-8");
        httpResponse.set(HttpField.CONTENT_LENGTH, 0);
        httpResponse.set(HttpField.AUTHORIZATION, "Bearer ABCD1234");
        httpResponse.setCookie("sid=1321312");
        httpResponse.setCookie("authorization=412y942bdb");
        //then
        Assertions.assertEquals(responseMessage, httpResponse.getHeaderMessage());
    }
}
