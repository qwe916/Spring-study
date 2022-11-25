package hello.springtx.order;

/**
 * 잔고 부족시 롤백을 하면 안된다.
 */
public class NotEnoughMoneyException extends Exception{

    public NotEnoughMoneyException(String message) {
        super(message);
    }
}
