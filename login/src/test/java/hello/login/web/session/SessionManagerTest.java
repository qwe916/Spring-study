package hello.login.web.session;

import hello.login.domain.member.Member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.http.HttpServletResponse;


class SessionManagerTest {
    SessionManager sessionManager = new SessionManager();

    @Test
    void sessionTest() {
        //스프링에서 제공하는 HttpServletResponse
        MockHttpServletResponse response = new MockHttpServletResponse();

        //세션 생성
        Member member = new Member();
        sessionManager.createSession(member, response);

        //요청에 응답 쿠키 생성
        MockHttpServletRequest req = new MockHttpServletRequest();
        req.setCookies(response.getCookies());

        //세션 조회
        Object result = sessionManager.getSession(req);
        Assertions.assertThat(result).isEqualTo(member);

        //세션 종료
        sessionManager.expire(req);
        Object expireSession = sessionManager.getSession(req);
        Assertions.assertThat(expireSession).isNull();

    }
}