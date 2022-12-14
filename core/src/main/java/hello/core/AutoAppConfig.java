package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = "hello.core", //탐색 시작 파일, 디폴트시 @Component가 붙어있는 설정정보 패키지가 시작 파일이다.
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class
        )
)// 자동으로 @Component 에노테이션이 붙은 클래스를 스캔하여 bean 등록

public class AutoAppConfig {
        /*@Bean(name = "memoryMembeRepository")
        MemberRepository memberRepository() {
                return new MemoryMemberRepository();
        }
        수동으로 빈 등록
        동일한 이름의 빈이 2개 있으면 오류 발생
        */
}
