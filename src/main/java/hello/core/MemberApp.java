package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
/*
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();//회원 서비스
*/
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class); // 스프링 가져오기 스프링 시작시 ApplicationContext 생성
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);// memberService 객체를 찾기 타입은 MemberService
        Member member = new Member(1L, "memberA", Grade.VIP);//회원a
        memberService.join(member);//회원가입
        Member findMember = memberService.findMember(1L);//회원 서비스의 조회 기능
        System.out.println(member.getName());//회원 a의 이름
        System.out.println(findMember.getName());//조회 기능을 통한 회원의 이름
    }
}
