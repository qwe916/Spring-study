package hello.core.singleton;

public class SingletonService {
    private static final SingletonService instance = new SingletonService();//딱 하나만 존재하는 객체 instance

    public static SingletonService getInstance() {
        return instance;
    }

    private SingletonService() { // private 접근자를 이용하여 다른 자바 파일에서 객체를 생성하지 못하게 만든다.
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
