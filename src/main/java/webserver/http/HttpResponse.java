package webserver.http;

import webserver.utils.HttpConstants;
import webserver.utils.HttpField;

import java.util.LinkedHashMap;
import java.util.Map;

public class HttpResponse {
    private HttpStatus status;
    private final Map<String, String> headers;
    private byte[] body;

    public HttpResponse() {
        headers = new LinkedHashMap<>();
    }

    public void setHeader(String name, String value) {
        headers.put(name, value);
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public void setContentType(String contentType) {
        headers.put(HttpField.CONTENT_TYPE, contentType);
    }

    public void setContentLength(int contentLength) {
        headers.put(HttpField.CONTENT_LENGTH, String.valueOf(contentLength));
    }

    public void setBody(byte[] body) {
        this.body = body;
    }

    public boolean isBodyEmpty() {
        return body == null || body.length == 0;
    }

    public String getHeaderMessage() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(status.getStatusLine())
                .append(HttpConstants.CRLF);

        for (String key : headers.keySet()) {
            stringBuilder.append(key)
                    .append(": ")
                    .append(headers.get(key))
                    .append(HttpConstants.CRLF);
        }

        return stringBuilder.toString();
    }

    public HttpStatus getStatus() {
        return status;
    }

    public byte[] getHeaderBytes() {
        return getHeaderMessage().getBytes();
    }

    public byte[] getBodyBytes() {
        return body;
    }
}
