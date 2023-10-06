package application.controller.user;

import application.controller.Controller;
import application.service.SessionService;
import application.service.UserService;
import view.ModelAndView;
import webserver.http.HttpRequest;
import webserver.http.HttpResponse;
import webserver.http.HttpStatus;
import webserver.utils.Location;

import java.io.IOException;

public class UserListController implements Controller {
    private final UserService userService = UserService.getInstance();
    private final SessionService sessionService = SessionService.getInstance();

    @Override
    public ModelAndView process(HttpRequest httpRequest, HttpResponse httpResponse) throws IOException {
        ModelAndView modelAndView = new ModelAndView(Location.USER_LIST_PAGE);
        String sessionId = httpRequest.getSessionId();

        if (sessionService.verifySessionId(sessionId)) {
            httpResponse.setStatus(HttpStatus.OK);
            modelAndView.setLogin(sessionService.findUserId(sessionId));
            modelAndView.addAttribute("userList", userService.getList());
            return modelAndView;
        }

        httpResponse.sendRedirect(Location.LOGIN_PAGE);
        return null;
    }
}
