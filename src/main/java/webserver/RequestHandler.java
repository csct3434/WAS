package webserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import webserver.http.HttpRequest;
import webserver.http.HttpResponse;
import webserver.utils.HttpConstants;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class RequestHandler implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(RequestHandler.class);
    private final DispatcherServlet dispatcherServlet = DispatcherServlet.getInstance();
    private final Socket connection;

    public RequestHandler(Socket connectionSocket) {
        this.connection = connectionSocket;
    }

    public void run() {
        logger.debug("New Client Connect! Connected IP : {}, Port : {}", connection.getInetAddress(), connection.getPort());

        try (BufferedInputStream in = new BufferedInputStream(connection.getInputStream());
             BufferedOutputStream out = new BufferedOutputStream(connection.getOutputStream())) {
            HttpRequest httpRequest = new HttpRequest(in);
            HttpResponse httpResponse = new HttpResponse();

            dispatcherServlet.dispatch(httpRequest, httpResponse);

            sendResponse(httpResponse, out);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    private void sendResponse(HttpResponse httpResponse, OutputStream out) throws IOException {
        out.write(httpResponse.getHeaderBytes());
        out.write(HttpConstants.CRLF.getBytes());
        writeResponseBody(httpResponse, out);
        out.flush();
    }

    private void writeResponseBody(HttpResponse httpResponse, OutputStream out) throws IOException {
        if (!httpResponse.isBodyEmpty()) {
            out.write(httpResponse.getBodyBytes());
        }
    }
}
