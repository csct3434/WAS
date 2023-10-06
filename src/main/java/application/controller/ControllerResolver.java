package application.controller;

import application.controller.article.ArticleSaveController;
import application.controller.article.ArticleViewController;
import application.controller.article.ArticleWriteController;
import application.controller.file.FileController;
import application.controller.user.UserListController;
import application.controller.user.UserLoginController;
import application.controller.user.UserLogoutController;
import application.controller.user.UserSaveController;
import application.controller.index.IndexPageController;
import webserver.utils.HttpMethod;

import java.util.HashMap;
import java.util.Map;

public class ControllerResolver {
    private static ControllerResolver instance;

    private final Map<ControllerSignature, Controller> controllers = new HashMap<>();
    private final Controller indexPageController = new IndexPageController();
    private final Controller fileController = new FileController();

    private ControllerResolver() {
        initControllers();
    }

    public static ControllerResolver getInstance() {
        if (instance == null) {
            synchronized (ControllerResolver.class) {
                instance = new ControllerResolver();
            }
        }
        return instance;
    }

    private void initControllers() {
        controllers.put(new ControllerSignature("/", HttpMethod.GET), indexPageController);
        controllers.put(new ControllerSignature("/index.html", HttpMethod.GET), indexPageController);
        controllers.put(new ControllerSignature("/user/create", HttpMethod.POST), new UserSaveController());
        controllers.put(new ControllerSignature("/user/login", HttpMethod.POST), new UserLoginController());
        controllers.put(new ControllerSignature("/user/list", HttpMethod.GET), new UserListController());
        controllers.put(new ControllerSignature("/user/logout", HttpMethod.GET), new UserLogoutController());
        controllers.put(new ControllerSignature("/article/write", HttpMethod.GET), new ArticleWriteController());
        controllers.put(new ControllerSignature("/article/save", HttpMethod.POST), new ArticleSaveController());
        controllers.put(new ControllerSignature("/article/view", HttpMethod.GET), new ArticleViewController());
    }

    public Controller resolve(String path, String method) {
        return controllers.keySet().stream()
                .filter(signature -> signature.verifySignature(path, method))
                .findFirst()
                .map(controllers::get)
                .orElse(fileController);
    }

    private static class ControllerSignature {
        private final String path;
        private final String method;

        public ControllerSignature(String path, String method) {
            this.path = path;
            this.method = method;
        }

        public boolean verifySignature(String path, String method) {
            if (path == null || method == null) {
                return false;
            }
            return (path.equals(this.path)) && (method.equals(this.method));
        }
    }
}
