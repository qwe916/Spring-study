package hello.proxy.pureproxy.concreateproxy;

import hello.proxy.pureproxy.concreateproxy.code.ConcreteClient;
import hello.proxy.pureproxy.concreateproxy.code.ConcreteLogic;
import hello.proxy.pureproxy.concreateproxy.code.TimeProxy;
import org.junit.jupiter.api.Test;

public class ConcreteProxyTest {

    @Test
    void nProxy() {
        ConcreteClient client = new ConcreteClient(new ConcreteLogic());
        client.execute();
    }

    @Test
    void addProxy() {
        TimeProxy timeProxy = new TimeProxy(new ConcreteLogic());
        ConcreteClient client = new ConcreteClient(timeProxy);//다형성에 의해  ConcreteLogic을 상속한 TimeProxy를 할당할 수 있다.
        client.execute();
    }

}
