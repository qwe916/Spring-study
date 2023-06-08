package hello.servlet.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
//서블릿 애노테이션 name,Pattern 중복 금지
//서블릿을 서블릿 컨테이너에 등록
@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest requset, HttpServletResponse response) throws ServletException, IOException {
        //서블릿이 실행되면 호출되는 메서드
        System.out.println("HelloServlet.service");

        System.out.println("requset = " + requset);
        System.out.println("response = " + response);

        //쿼리로 받아온 정보를 저장
        String username = requset.getParameter("username");
        System.out.println("username = " + username);

        //reposnse Content type 설정하여 response를 보낸다
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write("hello" + username);

    }
}
