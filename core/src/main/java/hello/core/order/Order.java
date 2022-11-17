package hello.core.order;

public class Order {
    private Long memberId;
    private String itemName;
    private int itemPrice;

    public Order(Long memberId, String itemName, int itemPrice, int discoutnPrice) {
        this.memberId = memberId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.discoutnPrice = discoutnPrice;
    }
    public int calculatePrice() {
        return itemPrice - discoutnPrice;
    }
    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getDiscoutnPrice() {
        return discoutnPrice;
    }

    public void setDiscoutnPrice(int discoutnPrice) {
        this.discoutnPrice = discoutnPrice;
    }

    private int discoutnPrice;

    @Override
    public String toString() {
        return "Order{" +
                "memberId=" + memberId +
                ", itemName='" + itemName + '\'' +
                ", itemPrice=" + itemPrice +
                ", discoutnPrice=" + discoutnPrice +
                '}';
    }
}
