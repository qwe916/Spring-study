package hello.itemservice.validation;

import hello.itemservice.domain.item.Item;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class BeanValidationTest {

    @Test
    void beanValidation() {
        //검증기 생성
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        Item item = new Item();
        //오류 설정
        item.setItemName(" ");
        item.setPrice(0);
        item.setQuantity(10000000);
        //검증 실행
        Set<ConstraintViolation<Item>> validations = validator.validate(item);
        //오류 메시지는 자동으로 hiverate가 생성해준 메시지 이다.
        for (ConstraintViolation<Item> violation:validations
             ) {
            System.out.println("violation = " + violation);
            System.out.println("violation.getMessage() = " + violation.getMessage());
        }
    }
}
