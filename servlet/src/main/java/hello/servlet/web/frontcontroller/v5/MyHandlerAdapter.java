package hello.servlet.web.frontcontroller.v5;

import hello.servlet.web.frontcontroller.ModelView;
import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface MyHandlerAdapter {
    //어댑터가 해당 컨트롤러를 처리할 수 있는지 판단하는 메서드
    boolean support(Object handler);
    // 컨트롤러를 호출하여 modelView 반환
    ModelView handle(HttpServletRequest request, HttpServletResponse response,Object handler) throws ServletException, IOException;

}
