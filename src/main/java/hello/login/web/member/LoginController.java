package hello.login.web.member;

import hello.login.domain.login.LoginService;
import hello.login.domain.member.Member;
import hello.login.web.login.LoginForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginForm") LoginForm form) {
        return "login/loginForm";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute LoginForm form, BindingResult result, HttpServletResponse response) {
        if (result.hasErrors()) {
            return "login/loginForm";
        }
        Member loginMember = loginService.login(form.getLoginId(), form.getPassword());

        if (loginMember == null) {
            result.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "login/loginForm";
        }

        //로그인 성공처리

        //쿠키에 시간 정보를 주지 않으면 세션 쿠기(브라우저 종료시 모두 종료)
        Cookie idCookie = new Cookie("memberId", String.valueOf(loginMember.getId()));
        response.addCookie(idCookie);//쿠키를 HttpServletResponse에 담아서 보낸다.
        /**
         * 그러나 이렇게 구현한다면 보안 문제가 발생한다.
         * 1. 클라이언트가 쿠키 값을 변경할 수 있다.
         * 2. 쿠키에 보관된 정보를 훔쳐갈 수 있다.
         * 3. 헤커가 쿠키를 한번 훔쳐가면 평생 사용할 수 있다.(쿠키 내용을 훔쳐가 악의적으로 이용할 수 있다.)
         * 대안
         * 1. 토큰값을 노출
         * 2. 해커가 토크값을 넣어도 찾을 수 없도록 예상 불가능하게 만든다.
         * 3.토큰 만료시간을 설정하여 해킹 의심을 강제로 해제한다.
         */
        return "redirect:/";
    }

    @PostMapping("/logout")
    public String logout(HttpServletResponse response) {
        expireCookie(response,"memberId");
        return "redirect:/";
    }

    private void expireCookie(HttpServletResponse response,String cookieName) {
        //쿠키를 만료 시켜서 쿠키 없애기
        Cookie cookie = new Cookie(cookieName, null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
}
