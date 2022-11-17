package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
    @Test
    @DisplayName("모든 빈 출력하기")
    void findBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();//bean 이름을 스트링 배열 반환
        for (String beanDefinitionName : beanDefinitionNames
        ) {
            Object bean = ac.getBean(beanDefinitionName);//빈 객체 반환
            System.out.println("Name = " + beanDefinitionName+" object = "+bean);
        }
    }
    @Test
    @DisplayName("애플리케이션 빈 출력하기")
    void findApplicationBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames
        ) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);//빈에 대한 메타데이터 정보
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                //스프링 내부에서 설정한 것이 아닌 개발자가 등록한것
                // Role ROLE_INFRASTRUCTURE 은 스프링 내부에서 사용하는 빈
                Object bean = ac.getBean(beanDefinitionName);//빈 객체 반환
                System.out.println("Name = " + beanDefinitionName+" object = "+bean);
            }
        }
    }
}
