package hello.servlet.web.frontcontroller.v1.controller;

import hello.servlet.web.frontcontroller.v1.ControllerV1;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MemberFormControllerV1 implements ControllerV1 {

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String viewPath = "/WEB-INF/views/new-form.jsp";
        //경로 이동
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        //다른 서블릿이나 JSP로 이동할 수 있는 기능(리다이렉트와 다르다 : 서버에서 일어나기 때문)
        dispatcher.forward(request, response);
    }
}
