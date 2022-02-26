package lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetworkClient  {//implements InitializingBean, DisposableBean 초기화,소멸 인터페이스
    private String url;
    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
    }

    public void setUrl(String url) {
        this.url = url;
    }
    //서버스 시작시 호출
    public void connet() {
        System.out.println("connect: " + url);
    }

    public void call(String message) {
        System.out.println("call: " + url + " message = " + message);
    }
    //서비스 종료시
    public void disconnect() {
        System.out.println("close " + url);
    }
    @PostConstruct //초기화 콜백 에노테이션 - 외부 라이브러리에는 적용하지 못하기 때문에 외부 라이브러리 초기화 할때는 @Bean 속성 초기화 방법으로 하자
    public void init() {
        System.out.println("NetworkClient.init");
        connet();
        call("초기화 연결 메시지");
    }
    @PreDestroy // 소멸 콜백 에노테이션
    public void close()  {
        System.out.println("NetworkClient.close");
        disconnect();
    }

  /*  @Override//의존관계 주입이 끝나고 실행하는 메소드
    public void afterPropertiesSet() throws Exception {
        System.out.println("NetworkClient.afterPropertiesSet");
        connet();
        call("초기화 연결 메시지");
    }

    @Override//종료 시점 콜 백
    public void destroy() throws Exception {
        System.out.println("NetworkClient.destroy");
        disconnect();
    }*/
}
