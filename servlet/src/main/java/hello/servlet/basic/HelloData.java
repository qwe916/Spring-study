package hello.servlet.basic;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter //lombok으로 자동으로 getter setter 생성
public class HelloData {
    private String username;
    private int age;
}
