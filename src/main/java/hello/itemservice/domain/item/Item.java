package hello.itemservice.domain.item;

import lombok.Data;
//hibernate validation
import org.hibernate.validator.constraints.Range;
//표준 validation
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class Item {

    private Long id;

    @NotBlank//빈값 + 공백만 있는 경우를 허용하지 않는다.
    private String itemName;

    @NotNull//null을 허용하지 않는다.
    @Range(min = 1000, max=1000000)//범위 설정
    private Integer price;

    @NotNull
    @Max(9999)//최대값 설정
    private Integer quantity;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
