package webserver.utils;

import webserver.http.HttpRequest;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class HttpRequestCreateUtil {
    public static HttpRequest createHttpRequest(String requestMessage) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(requestMessage.getBytes());
        return new HttpRequest(byteArrayInputStream);
    }
}
