package webserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WebServer {
    private static final Logger logger = LoggerFactory.getLogger(WebServer.class);
    private static final int DEFAULT_PORT = 8080;
    private static final int THREAD_POOL_SIZE = Runtime.getRuntime().availableProcessors();

    public static void main(String args[]) throws Exception {
        int port = getPort(args);
        Socket connection;
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

        try (ServerSocket listenSocket = new ServerSocket(port)) {
            logger.info("Web Application Server started {} port.", port);

            while ((connection = listenSocket.accept()) != null) {
                executorService.execute(new RequestHandler(connection));
            }
        } catch (Exception e) {
            logger.debug(e.getMessage());
        }
    }

    private static int getPort(String[] args) {
        if (args == null || args.length == 0) {
            return DEFAULT_PORT;
        }
        return Integer.parseInt(args[0]);
    }
}
