package hello.itemservice.validation;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.validation.DefaultMessageCodesResolver;
import org.springframework.validation.MessageCodesResolver;

public class MessageCodesResolverTest {
    MessageCodesResolver codesResolver = new DefaultMessageCodesResolver();
    @Test
    void messageCodesResolverObject() {
        String[] messages = codesResolver.resolveMessageCodes("required", "item");
        for (String message: messages
             ) {
            System.out.println("message = " + message);
        };
        /**
         * reject(), rejectValue() 가 더 디테일한 코드 부터 검증한다.
         */
        Assertions.assertThat(messages).containsExactly("required.item", "required");
    }
    @Test
    void messageCodesResolverField() {
        String[] messageCodes = codesResolver.resolveMessageCodes("required", "item", "itemName", String.class);
        for (String message: messageCodes
             ) {
            System.out.println("message = " + message);//
        }
        //BindingResult가 내부적으로 codesResolver를 사용하여 우선순위에 따라 errorCode 배열을 가져와 FieldError를 생성하여 전달
        //new Field("item","itemName",null,false, messageCodes,null,...);
        Assertions.assertThat(messageCodes).containsExactly("required.item.itemName", "required.itemName", "required.java.lang.String", "required");
    }
    /**
     * 필드 오류 우선순위
     * 1. code+"."+objectb name+"."+ field
     * 2. code+"."+ field
     * 3. code+"."+ field type
     * 4. code
     */
}
