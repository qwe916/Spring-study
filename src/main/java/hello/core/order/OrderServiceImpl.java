package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor//필수값(final)을 파라미터로 받는 생성자 메소드 생성
public class OrderServiceImpl implements OrderService{
    private final MemberRepository memberRepository;//생성자 키워드를 쓰면 final 키워드를 사용가능(오직 생성자 주입만 사용가능) - 혹시라도 값이 설정되지 않는 오류를 컴파일 시점에서 막아준다.
    private final DiscountPolicy discountPolicy;// 조회 대상 빈이 두개 이상이면 타입 매칭을 하고 둘다 타입이 동일하면 파라미터 명 혹은 필드명 에 맞는 대상을 매칭한다. ex DiscountPolicy rateDiscountPolicy
    //private final DiscountPolicy discountPolicy = new RateDiscountPolicy(); - DIP 위반
    //private final DiscountPolicy discountPolicy = new FixDiscountPolic(); - DIP 위반


    /*
    * 다양한 의존 관계 주입 방법
    * 1. 생성자 주입: 불변,필수, 생성자가 1개 있으면 @Autowired 생략가능
    * 2. 수정자 주입(setter 주입) : 선택, 변경 가능 , @Autowired(required = false)를 하면 주입할 대상이 없어도 동작한다.
    * 3. 필드 주입: 외부에서 변경이 힘들어 테스트 하기 힘들다는 단점
    * 4. 일반 메서드 주입: 한 번에 여러 필드를 주입받을 수 있따.      */

    //@Autowired 생성자가 1개일시 생략 가능
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {//@Qualifier("mainDiscountPolicy") DiscountPolicy discountPolicy - @Qualifier사용시
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
    //Lombok의 @RequiredArgsConstructor로 생성자가 이미 생성되어 있음*/

    /*
    @Autowired // 수정자로 의존관계 주입시
    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }
    @Autowired
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    */

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
