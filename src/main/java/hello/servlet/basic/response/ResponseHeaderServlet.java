package hello.servlet.basic.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest rerequest, HttpServletResponse response) throws ServletException, IOException {
        //[status-line]
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        //[reponse-header]
        response.setHeader("Content-Type", "text/plain;charset=utf-8");
        response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("my-header", "hello");
        //[헤더의 편의 메서드]
        content(response);
        //쿠키 관련 메서드
        cookie(response);
        //Redirect 편의 메서드
        redirect(response);
        PrintWriter writer = response.getWriter();
        writer.println("ok 안녕하세요");
    }

    private void redirect(HttpServletResponse response) throws IOException {
        //Status Code 302
        //Location /basic/hello-form.html
        //response.setStatus(HttpServletResponse.SC_FOUND); //302
        //response.setHeader("Location", "/basic/hello-form.html");
        response.sendRedirect("/basic/hello-form.html");
    }

    private void cookie(HttpServletResponse response) {
        //Set-Cookie : myCookie=good; Max-Age=60
        //response.setHeader("Set-Cookie", "myCookie=good; Max-Age=60);
        Cookie cookie = new Cookie("myCookie", "good");
        cookie.setMaxAge(60);
        response.addCookie(cookie);
    }

    private void content(HttpServletResponse response) {
        //Content-Type : text/plain;charset=utf-8
        //Content-Length : 2
        //response.setHeader("Content-Type", "text/plain;charset=utf-8");
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        //reponse.setContentLength(2); //(생략시 자동생성)
    }
}
