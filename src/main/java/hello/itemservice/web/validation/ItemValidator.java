package hello.itemservice.web.validation;

import hello.itemservice.domain.item.Item;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.*;

/**
 * 검증하는 로직이 다른 로직보다 길고 많기 때문에 따로 클래스를 생성하여 검증
 */
@Component//스프링 빈에 등록
public class ItemValidator implements Validator {


    @Override
    public boolean supports(Class<?> clazz) {
        return Item.class.isAssignableFrom(clazz);
        //item == clazz 넘어오는 객체가 Item 클래스인가?
        //item == subItem 자식 클래스여도 통과
    }

    @Override
    public void validate(Object target, Errors errors) {
        Item item = (Item) target;
        //Errors는 BindingResult의 부모 클래스
        BindingResult bindingResult = (BindingResult) errors;

        if (!StringUtils.hasText(item.getItemName())) {//글자가 없으면
            bindingResult.addError(new FieldError("item", "itemName", item.getItemName(), false, new String[]{"required.item.itemName"}, null, null));
        }
        if (item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() > 1000000) {
            bindingResult.addError(new FieldError("item", "price",item.getPrice(), false, new String[]{"range.item.price"}, new Object[]{1000,10000000}, "가격은 1,000~1,000,0000 까지 허용합니다."));
        }
        if (item.getQuantity() == null || item.getQuantity() >= 9999) {
            bindingResult.addError(new FieldError("item", "quantity",item.getQuantity(), false,  new String[]{"max.item.quantity"}, new Object[]{9999}, "수량은 최대 9,999까지 가능합니다."));
        }

        if (item.getPrice() != null && item.getQuantity() != null) {
            int resultPrice = item.getPrice() * item.getQuantity();
            if (resultPrice < 10000) {
                //객체 필드가 아닌 경우 new ObjectError 생성, Object 에러는 넘어오는 값이 없기 때문에 입력된 값을 유지할 것이 없다.
                bindingResult.addError(new ObjectError("item", new String[]{"totalPriceMin"}, new Object[]{10000,resultPrice}, "가격 x 수량의 합은 10,000원 이상이여햐 합니다. 현재 값 " + resultPrice));
            }
        }

    }
}
