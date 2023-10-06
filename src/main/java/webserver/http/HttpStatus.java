package webserver.http;

public enum HttpStatus {
    OK(200, "HTTP/1.1 200 OK"),
    FOUND(302, "HTTP/1.1 302 Found"),
    UNAUTHORIZED(401, "HTTP/1.1 401 Unauthorized"),
    NOT_FOUND(404, "HTTP/1.1 404 Not Found"),
    FORBIDDEN(403, "HTTP/1.1 403 Forbidden"),
    INTERNAL_SERVER_ERROR(500, "HTTP/1.1 500 Internal Server Error"),
    BAD_GATEWAY(502, "HTTP/1.1 502 Bad Gateway"),
    BAD_REQUEST(400, "HTTP/1.1 400 Bad Request"),
    CONFLICT(409, "HTTP/1.1 409 Conflict"),
    SERVICE_UNAVAILABLE(503, "HTTP/1.1 503 Service Unavailable");

    private int statusCode;
    private String statusLine;

    private HttpStatus(int statusCode, String statusLine) {
        this.statusCode = statusCode;
        this.statusLine = statusLine;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getStatusLine() {
        return statusLine;
    }
}
