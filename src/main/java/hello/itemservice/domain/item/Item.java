package hello.itemservice.domain.item;

import lombok.Data;

//@Getter @Setter
@Data // @Data를 쓸때는 잘 보고 사용하기!!
public class Item {
    private Long id;
    private String itemName;
    private Integer price;
    private Integer guantity;

    public Item(String itemA, int i, int i1) {
    }

    public Item(Long id, String itemName, Integer price, Integer guantity) {
        this.id = id;
        this.itemName = itemName;
        this.price = price;
        this.guantity = guantity;
    }
}
