package webserver.http;

import webserver.utils.CookieConstants;
import webserver.utils.HttpField;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class HttpRequest {
    private final BufferedReader bufferedReader;

    private String method;
    private String path;
    private final HttpHeaders httpHeaders;
    private final HttpParameters httpParameters;
    private final Cookie cookie;
    private String body = "";

    public HttpRequest(InputStream in) throws IOException {
        bufferedReader = new BufferedReader(new InputStreamReader(in));
        httpHeaders = new HttpHeaders();
        httpParameters = new HttpParameters();
        cookie = new Cookie();

        parseRequestMessage();
    }

    private void parseRequestMessage() throws IOException {
        parseHeader();
        parseBody();
        parsePath();
        parseUrlEncodedParameters();
    }

    private void parseHeader() throws IOException {
        parseRequestLine();
        parseHeaderFields();
        parseCookie();
    }

    private void parseRequestLine() throws IOException {
        String[] requestLineTokens = bufferedReader.readLine().split(" ");
        method = requestLineTokens[0];
        httpHeaders.put(HttpField.URI, URLDecoder.decode(requestLineTokens[1], StandardCharsets.UTF_8));
        httpHeaders.put(HttpField.VERSION, requestLineTokens[2]);
    }

    private void parsePath() {
        String URI = httpHeaders.get(HttpField.URI);
        path = URI.split("\\?")[0];
    }

    private void parseUrlEncodedParameters() {
        parseURIParameters();
        parseBodyParameters();
    }

    private void parseURIParameters() {
        String URI = httpHeaders.get(HttpField.URI);
        String[] uriTokens = URI.split("\\?");
        if (uriTokens.length == 2) {
            parseParameters(uriTokens[1]);
        }
    }

    private void parseBodyParameters() {
        if (checkContentType()) {
            parseParameters(body);
        }
    }

    private boolean checkContentType() {
        return httpHeaders.get(HttpField.CONTENT_TYPE)
                    .equals("application/x-www-form-urlencoded");
    }

    private void parseParameters(String parameters) {
        String parameterSeparator = "&";

        for (String parameter : parameters.split(parameterSeparator)) {
            addParameter(parameter);
        }
    }

    private void addParameter(String parameter) {
        String[] tokens = parameter.split("=");
        if (tokens.length == 2) {
            httpParameters.put(tokens[0], tokens[1]);
        }
    }

    private void parseHeaderFields() throws IOException {
        String line;
        while (!(line = bufferedReader.readLine()).isEmpty()) {
            int separatorIndex = line.indexOf(":");
            String fieldName = line.substring(0, separatorIndex);
            String fieldValue = line.substring(separatorIndex + 1).trim();
            httpHeaders.put(fieldName, fieldValue);
        }
    }

    private void parseCookie() {
        if (httpHeaders.contains("Cookie")) {
            for (String directive : httpHeaders.get("Cookie").split(";")) {
                cookie.add(directive.trim());
            }
        }
    }

    private void parseBody() throws IOException {
        if (httpHeaders.contains(HttpField.CONTENT_LENGTH)) {
            int bodyLength = Integer.parseInt(httpHeaders.get(HttpField.CONTENT_LENGTH));
            char[] requestBody = new char[bodyLength];

            bufferedReader.read(requestBody, 0, bodyLength);
            this.body = URLDecoder.decode(new String(requestBody), StandardCharsets.UTF_8);
        }
    }

    public String getHeader(String field) {
        return httpHeaders.get(field);
    }

    public String getParameter(String name) {
        return httpParameters.get(name);
    }

    public String getCookie(String name) {
        return cookie.get(name);
    }

    public String getSessionId() {
        return cookie.get(CookieConstants.SESSION_ID);
    }

    public String getBody() {
        return body;
    }

    public String getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }
}
