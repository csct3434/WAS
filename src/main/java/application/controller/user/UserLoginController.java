package application.controller.user;

import application.controller.Controller;
import application.dto.user.UserLoginDto;
import application.service.SessionService;
import application.service.UserService;
import view.ModelAndView;
import webserver.exceptions.LoginFailException;
import webserver.http.HttpRequest;
import webserver.http.HttpResponse;
import webserver.utils.CookieConstants;
import webserver.utils.Location;

public class UserLoginController implements Controller {
    private final UserService userService = UserService.getInstance();
    private final SessionService sessionService = SessionService.getInstance();

    @Override
    public ModelAndView process(HttpRequest httpRequest, HttpResponse httpResponse) {
        try {
            UserLoginDto userLoginDto = new UserLoginDto(
                    httpRequest.getParameter("userId"),
                    httpRequest.getParameter("password"));

            userService.verifyCredentials(userLoginDto);

            processLoginResponse(httpResponse, userLoginDto.getUserId());
        } catch (LoginFailException e) {
            httpResponse.sendRedirect(Location.LOGIN_FAIL_PAGE);
        }
        return null;
    }

    private void processLoginResponse(HttpResponse httpResponse, String userId) throws LoginFailException {
        String sessionId = sessionService.create(userId);

        httpResponse.setCookie(CookieConstants.SESSION_ID, sessionId);
        httpResponse.setCookie(CookieConstants.PATH, "/");
        httpResponse.sendRedirect(Location.INDEX_PAGE);
    }
}
