package hello.core.discount;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@Qualifier("mainDiscountPolicy") -> @Qualifier 를 사용하여 이름가 매치 되는 것이 있다면 그 객체 사용 , @Qualifier는 @Qualifier끼리만 매칭, 그다음 빈 매칭 하고 그래도 없으면 NoSuchBeansDefinitionException 발생
@MainDiscountPolicy//이 에노테이션을 쓰면 이 만든 MainDiscountPolicy의 에노테이션들의 효과를 다 갖는다.
public class RateDiscountPolicy implements DiscountPolicy {
    private int discountPercent = 10;
    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return price * discountPercent / 100;
        } else {
            return 0;
        }

    }
}
