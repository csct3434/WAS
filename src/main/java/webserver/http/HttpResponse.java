package webserver.http;

import webserver.utils.HttpConstants;
import webserver.utils.HttpField;

public class HttpResponse {
    private HttpStatus status;
    private final HttpHeaders headers;
    private final Cookie cookie;
    private byte[] body;

    public HttpResponse() {
        headers = new HttpHeaders();
        cookie = new Cookie();
    }

    public String get(String name) {
        return headers.get(name);
    }

    public void set(String name, String value) {
        headers.put(name, value);
    }

    public void set(String name, int value) {
        headers.put(name, String.valueOf(value));
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public Cookie getCookie() {
        return cookie;
    }

    public void setCookie(String directive) {
        cookie.add(directive);
    }

    public void setCookie(String name, String value) {
        StringBuilder stringBuilder = new StringBuilder();

        String directive = stringBuilder
                .append(name)
                .append("=")
                .append(value)
                .toString();

        cookie.add(directive);
    }

    public void setBody(byte[] body) {
        this.body = body;
    }

    public boolean isBodyEmpty() {
        return body == null || body.length == 0;
    }

    public String getHeaderMessage() {
        StringBuilder stringBuilder = new StringBuilder();
        addStatusLineMessage(stringBuilder);
        addHeaderFields(stringBuilder);
        return stringBuilder.toString();
    }

    private void addStatusLineMessage(StringBuilder stringBuilder) {
        stringBuilder.append(status.getStatusLine()).append(HttpConstants.CRLF);
    }

    private void addHeaderFields(StringBuilder stringBuilder) {
        for (String fieldName : headers.getFieldNames()) {
            stringBuilder.append(fieldName)
                    .append(": ")
                    .append(headers.get(fieldName))
                    .append(HttpConstants.CRLF);
        }
        addCookieField(stringBuilder);
    }

    private void addCookieField(StringBuilder stringBuilder) {
        if (!cookie.isEmpty()) {
            stringBuilder.append("Set-Cookie: ");
            stringBuilder.append(cookie.getMessage());
            stringBuilder.append(HttpConstants.CRLF);
        }
    }

    public byte[] getHeaderBytes() {
        return getHeaderMessage().getBytes();
    }

    public byte[] getBodyBytes() {
        return body;
    }

    public void sendRedirect(String location) {
        status = HttpStatus.FOUND;
        headers.put(HttpField.LOCATION, location);
    }
}
