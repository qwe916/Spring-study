package hello.itemservice.domain.item;

import lombok.Data;

//@Getter @Setter
@Data // @Data를 쓸때는 잘 보고 사용하기!!
public class Item {
    private Long id;
    private String itemName;
    private Integer price;
    private Integer quantity;
    public Item() {
    }
    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
