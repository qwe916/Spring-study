package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService{
    /*
    * 다양한 의존 관계 주입 방법
    * 1. 생성자 주입: 불변,필수, 생성자가 1개 있으면 @Autowired 생략가능
    * 2. 수정자 주입(setter 주입) : 선택, 변경 가능 , @Autowired(required = false)를 하면 주입할 대상이 없어도 동작한다.
    * 3. 필드 주입: 외부에서 변경이 힘들어 테스트 하기 힘들다는 단점
    * 4. 일반 메서드 주입: 한 번에 여러 필드를 주입받을 수 있따.      */
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    private MemberRepository memberRepository;
    //private final DiscountPolicy discountPolicy = new RateDiscountPolicy(); - DIP 위반
    //private final DiscountPolicy discountPolicy = new FixDiscountPolic(); - DIP 위반
    private DiscountPolicy discountPolicy;
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
