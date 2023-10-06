package webserver.http;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

class HttpRequestTest {
    @Test
    @DisplayName("바디가 없는 HTTP Request 메시지를 파싱할 수 있어야 한다")
    void createWithoutBody() {
        //given
        SoftAssertions softAssertions = new SoftAssertions();

        String requestMessage = "GET /hello-world HTTP/1.1\r\n" +
                "Host: localhost:8080\r\n" +
                "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:90.0) Gecko/20100101 Firefox/90.0\r\n" +
                "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8\r\n" +
                "Accept-Language: en-US,en;q=0.5\r\n" +
                "Accept-Encoding: gzip, deflate\r\n" +
                "Connection: keep-alive\r\n" +
                "Upgrade-Insecure-Requests: 1\r\n" +
                "Cache-Control: max-age=0\r\n" +
                "Cookie: sid=1234; authorization=U3213e!K\r\n" +
                "\r\n";

        InputStream inputStream = new ByteArrayInputStream(requestMessage.getBytes());

        try {
            //when
            HttpRequest httpRequest = new HttpRequest(inputStream);

            //then
            softAssertions.assertThat(httpRequest.getMethod()).isEqualTo("GET");
            softAssertions.assertThat(httpRequest.getHeader("URI")).isEqualTo("/hello-world");
            softAssertions.assertThat(httpRequest.getHeader("Version")).isEqualTo("HTTP/1.1");
            softAssertions.assertThat(httpRequest.getHeader("Host")).isEqualTo("localhost:8080");
            softAssertions.assertThat(httpRequest.getHeader("User-Agent")).isEqualTo("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:90.0) Gecko/20100101 Firefox/90.0");
            softAssertions.assertThat(httpRequest.getHeader("Accept")).isEqualTo("text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
            softAssertions.assertThat(httpRequest.getHeader("Accept-Language")).isEqualTo("en-US,en;q=0.5");
            softAssertions.assertThat(httpRequest.getHeader("Accept-Encoding")).isEqualTo("gzip, deflate");
            softAssertions.assertThat(httpRequest.getHeader("Connection")).isEqualTo("keep-alive");
            softAssertions.assertThat(httpRequest.getHeader("Upgrade-Insecure-Requests")).isEqualTo("1");
            softAssertions.assertThat(httpRequest.getHeader("Cache-Control")).isEqualTo("max-age=0");
            softAssertions.assertThat("1234").isEqualTo(httpRequest.getCookie("sid"));
            softAssertions.assertThat("U3213e!K").isEqualTo(httpRequest.getCookie("authorization"));
            softAssertions.assertAll();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Test
    @DisplayName("HTTP Request 메시지의 바디를 파싱할 수 있어야 한다")
    void createWithBody() {
        //given
        String requestBody = "rqwnlrkqlwrnlqwnrklqwn wnkelnqwlk eqwen kqwlne qwe qw\r\n" +
                "wqenklwqnelkqwnke enklwqnekl qwe klqwnel wq\r\n" +
                "wqknenwqlenklwq neklwqn eqwnkelqw";

        String requestMessage = "POST /api/endpoint HTTP/1.1\r\n" +
                "Host: example.com\r\n" +
                "Content-Type: application/json\r\n" +
                "Content-Length: " + requestBody.length() + "\r\n" +
                "Authorization: Bearer your_access_token\r\n" +
                "\r\n" +
                requestBody;

        InputStream inputStream = new ByteArrayInputStream(requestMessage.getBytes());

        try {
            //when
            HttpRequest httpRequest = new HttpRequest(inputStream);

            //then
            Assertions.assertEquals(requestBody, httpRequest.getBody());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
