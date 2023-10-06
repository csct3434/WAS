package application.controller.user;

import application.controller.Controller;
import application.dto.user.UserSaveDto;
import application.service.UserService;
import view.ModelAndView;
import webserver.exceptions.BadRequestException;
import webserver.exceptions.ConflictException;
import webserver.http.HttpRequest;
import webserver.http.HttpResponse;
import webserver.http.HttpStatus;
import webserver.utils.Location;

import java.io.IOException;

public class UserSaveController implements Controller {
    private final UserService userService = UserService.getInstance();

    @Override
    public ModelAndView process(HttpRequest httpRequest, HttpResponse httpResponse) throws IOException {
        try {
            UserSaveDto userSaveDto = new UserSaveDto(
                    httpRequest.getParameter("userId"),
                    httpRequest.getParameter("password"),
                    httpRequest.getParameter("name"),
                    httpRequest.getParameter("email")
            );

            userService.add(userSaveDto);

            httpResponse.sendRedirect(Location.INDEX_PAGE);
            return null;
        } catch (BadRequestException e) {
            httpResponse.setStatus(HttpStatus.BAD_REQUEST);
            ModelAndView modelAndView = new ModelAndView(Location.ERROR_PAGE);
            modelAndView.addAttribute("errorMessage", "400 BAD REQUEST");
            return modelAndView;
        } catch (ConflictException e) {
            httpResponse.setStatus(HttpStatus.CONFLICT);
            ModelAndView modelAndView = new ModelAndView(Location.ERROR_PAGE);
            modelAndView.addAttribute("errorMessage", "409 CONFLICT");
            return modelAndView;
        }
    }
}
