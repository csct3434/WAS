package application.controller;

import view.ModelAndView;
import webserver.http.HttpRequest;
import webserver.http.HttpResponse;

import java.io.IOException;

public interface Controller {
    ModelAndView process(HttpRequest httpRequest, HttpResponse httpResponse) throws IOException;
}
