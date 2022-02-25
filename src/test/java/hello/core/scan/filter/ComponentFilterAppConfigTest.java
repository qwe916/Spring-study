package hello.core.scan.filter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.springframework.context.annotation.ComponentScan.*;

public class ComponentFilterAppConfigTest {
    @Test
    void filterScan() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
        BeanA beanA = annotationConfigApplicationContext.getBean("beanA", BeanA.class);
        Assertions.assertThat(beanA).isNotNull();
        //BeanB beanB = annotationConfigApplicationContext.getBean("beanB", BeanB.class); 는 제외했기 때문에 오류 발생
        org.junit.jupiter.api.Assertions.assertThrows(
                NoSuchBeanDefinitionException.class, () -> annotationConfigApplicationContext.getBean("beanB", BeanB.class)
        );
    }

    @Configuration
    @ComponentScan(
            includeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponet.class)
            , excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponet.class)
    )
    static class ComponentFilterAppConfig {

    }
}
