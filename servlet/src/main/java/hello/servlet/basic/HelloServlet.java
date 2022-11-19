package hello.servlet.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "helloServlet", urlPatterns = "/hello")//서블릿 애노테이션 name,Pattern 중복 금지
public class HelloServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest requset, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("HelloServlet.service");
        System.out.println("requset = " + requset);
        System.out.println("response = " + response);

        String username = requset.getParameter("username");//쿼리로 받아온 정보를 저장
        System.out.println("username = " + username);

        //reposnse Content type 설정
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write("hello" + username);

    }
}
