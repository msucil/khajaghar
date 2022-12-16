package com.msucil.app.lomba.web.view.error;

import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class ExceptionController extends BasicErrorController{

	public ExceptionController(ErrorAttributes errorAttributes, ServerProperties serverProperties) {
		super(errorAttributes, serverProperties.getError());
	}

	@Override
    public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response) {
        final ModelAndView modelAndView = super.errorHtml(request, response);
        modelAndView.setViewName("pages/misc/error");
        return modelAndView;
    }
}
