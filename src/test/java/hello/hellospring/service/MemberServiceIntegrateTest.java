package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest//스프링 부트 테스트
@Transactional//commit을 하기전까지 DB에 반영이 안되게 만드는 에노테이션(TEST가 끝나면 롤백됨)
class MemberServiceIntegrateTest {
    @Autowired MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    void 회원가입() {//테스트는 한글로 이름 적어도 가능
        //given 어떠한 상황이 주어진다.
        Member member = new Member();
        member.setName("hello");

        //when 어떠한 것을 실행하면
        Long saveId = memberService.join(member);

        //then 이러한 결과가 나와야 한다.
        Member findMember = memberService.findOnd(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");
        Member member2 = new Member();
        member2.setName("spring");
        //when
        memberService.join(member1);
       /* try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }*/
        assertThrows(IllegalStateException.class, () -> memberService.join(member2));//멤버2를 넣으면 예외가 터져야 테스트가 성공하는 문법

        //then
    }
}