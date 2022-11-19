package hello.servlet.web.frontcontroller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class MyView {
    private String ViewPath;

    public MyView(String viewPath) {
        ViewPath = viewPath;
    }

    public void render(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(ViewPath);
        dispatcher.forward(request, response);
    }

    public void render(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        modleToRequestAttribute(model, request);
        RequestDispatcher dispatcher = request.getRequestDispatcher(ViewPath);
        dispatcher.forward(request, response);
    }

    private void modleToRequestAttribute(Map<String, Object> model, HttpServletRequest request) {
        //모델에 있는 값 들을 request.setAttribute를 통해 다 넣은 후 jsp가 attribute를 사용
        model.forEach((key, value) -> request.setAttribute(key, value));
    }
}
