package hello.core.order;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrederServiceTest {
    MemberService memberService  ;
    OrderService orderService ;
    @BeforeEach //test 실행 전에 무조건 실행 되는 것
    public  void beforeEach () {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }
    @Test
    void createOrder() {
        Long memberId = 1L; // long으로 해두 괜찮으나 null이 들어가지 않는다.
        Member member = new Member(memberId, "meberA", Grade.VIP);
        memberService.join(member);
        Order order = orderService.createOrder(memberId, "itemA", 10000);
        Assertions.assertThat(order.getDiscoutnPrice()).isEqualTo(1000);

    }

}
