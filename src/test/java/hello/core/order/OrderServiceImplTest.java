package hello.core.order;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {
    @Test
    void createOrder() {
        MemoryMemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(new Member(1L, "client1", Grade.VIP));
        OrderServiceImpl orderService = new OrderServiceImpl(new MemoryMemberRepository(),new FixDiscountPolicy() );//수정자 주입시 따로 값을 지정해 주어야 하기 때문에 컴파일 오류
        Order order1 = orderService.createOrder(1L, "order1", 10000);
        Assertions.assertThat(order1.getDiscoutnPrice()).isEqualTo(1000);
    }

}