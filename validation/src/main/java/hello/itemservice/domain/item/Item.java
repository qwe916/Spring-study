package hello.itemservice.domain.item;

import lombok.Data;
//hibernate validation
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.ScriptAssert;
//표준 validation
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

//Bean Validation 적용 (@Validated 애노테이션이 있을경우)
/**
 * @ScriptAssert(lang = "javascript", script="_this.price * _this.quantity >= 1000",message = "총합이 10000원이 넘게 입력해 주세요")//ObjectError를 표시할때 쓰는 방법 @ScriptAssert
    그러나 이 애노테이션은 기능의 제약이 많고 복잡한 오류에는 적절하지 않아 잘 쓰이지 않는다.그냥 컨트롤러에서 오류 처리하는 것이 좋다.
 */
@Data
public class Item {
    //@NotNull(groups = UpdateCheck.class)//수정 요구 사항 추가 및 groups 추가
    private Long id;
    //오류 메세지는 애노테이션 파라미터 message를 정의해준다.
    //@NotBlank(message = "공백 x", groups = {SaveCheck.class,UpdateCheck.class})//빈값 + 공백만 있는 경우를 허용하지 않는다.
    private String itemName;

    //@NotNull(groups = {SaveCheck.class,UpdateCheck.class})//null을 허용하지 않는다.
    //@Range(min = 1000, max=1000000,groups = {SaveCheck.class,UpdateCheck.class})//범위 설정
    private Integer price;

    //@NotNull(groups = {SaveCheck.class,UpdateCheck.class})
    //@Max(value = 9999,groups = {SaveCheck.class}) //추가 요구 사항 추가
    // 최대값 설정
    private Integer quantity;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
