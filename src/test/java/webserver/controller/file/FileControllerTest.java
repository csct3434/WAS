package webserver.controller.file;

import application.controller.file.FileController;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import webserver.http.HttpRequest;
import webserver.http.HttpResponse;
import webserver.http.HttpStatus;
import webserver.utils.HttpField;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

class FileControllerTest {
    FileController fileController = new FileController();

    SoftAssertions softAssertions;
    HttpRequest httpRequest;
    HttpResponse httpResponse;

    @BeforeEach
    void init() {
        softAssertions = new SoftAssertions();
    }

    @Test
    @DisplayName("유효하지 않은 리소스 요청에 대한 응답은 '404 NOT FOUND'이어야 한다")
    void notFoundTest() throws Exception {
        //given
        String requestMessage = "GET /invalid-page.aaa HTTP/1.1\r\n"
                + "\r\n";

        ByteArrayInputStream inputStream = new ByteArrayInputStream(requestMessage.getBytes());

        initHttpRequestAndResponse(inputStream);

        //when
        fileController.process(httpRequest, httpResponse);

        //then
        softAssertions.assertThat(httpResponse.getStatus()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    private void initHttpRequestAndResponse(InputStream in) throws IOException {
        httpRequest = new HttpRequest(in);
        httpResponse = new HttpResponse();
    }

    @Test
    @DisplayName("응답 파일의 MIME 타입을 Content-Type에 설정하여 응답해야 한다")
    void MIME() throws Exception {
        //given
        String requestMessage = "GET /fonts/glyphicons-halflings-regular.eot HTTP/1.1\r\n"
                + "\r\n";

        ByteArrayInputStream inputStream = new ByteArrayInputStream(requestMessage.getBytes());

        initHttpRequestAndResponse(inputStream);

        //when
        fileController.process(httpRequest, httpResponse);

        //then
        softAssertions.assertThat(httpResponse.get(HttpField.CONTENT_TYPE)).isEqualTo("application/vnd.ms-fontobject");
    }
}