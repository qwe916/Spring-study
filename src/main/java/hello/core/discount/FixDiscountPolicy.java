package hello.core.discount;


import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
//@Qualifier("fixDiscountPolicy")
@Primary // 같은 타입이 2개 이상있을 경우 @Primary가 있으면 우선순위가 있어 이 객체가 사용,  @Primary와 @Qurifier 둘 다 비교시 @Qurifier가 더 우선순위가 높다.
public class FixDiscountPolicy implements DiscountPolicy {
    private int discountFixAmount = 1000; // 1000원 할인

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return discountFixAmount;
        } else {
            return 0;
        }

    }
}
