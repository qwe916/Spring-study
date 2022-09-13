package hello.login.web.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

@Slf4j
public class LogFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("log filter 초기화");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("log filter doFilter");
        HttpServletRequest httpRequest = (HttpServletRequest) request;//ServletRequest는 별 기능이 없어 다운캐스팅
        String requestURI = httpRequest.getRequestURI();// 요청 rui

        String uuid = UUID.randomUUID().toString();//uuid 생성

        try {
            log.info("Request [{}][{}]", uuid, requestURI);
            chain.doFilter(request, response);//다음 필터를 호출, 필터가 없으면 서블릿 호출
        } catch (Exception e) {
            throw e;
        }finally {
            log.info("RESPONSE [{}][{}]",uuid,requestURI);
        }
    }

    @Override
    public void destroy() {
        log.info("log filter destroy");
    }
}
