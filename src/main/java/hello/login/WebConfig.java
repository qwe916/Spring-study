package hello.login;

import hello.login.web.filter.LogFilter;
import hello.login.web.filter.LoginCheckFilter;
import hello.login.web.interceptor.LogInterceptor;
import hello.login.web.interceptor.LoginInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor())
                .order(1)
                .addPathPatterns("/**")//모든 경로에 인터셉터
                .excludePathPatterns("/css/**", "/*.ico", "/error");
        /**
         * 필터와 달리 따로 배열을 만들어서 제외할 URL을 지정할 필요 없고
         * excludePathPatterns 메소드를 통해 제외 시킬 수 있다.
         */
        registry.addInterceptor(new LoginInterceptor())
                .order(2)
                .addPathPatterns("/**")
                .excludePathPatterns("/", "/members/add", "/login", "/logout", "/css/**", "*.ico");
    }

    //Filter Bean 등록
    //@Bean
    public FilterRegistrationBean logFilter() {
        FilterRegistrationBean<Filter> filterFilterRegistrationBean = new FilterRegistrationBean<Filter>();
        filterFilterRegistrationBean.setFilter(new LogFilter());
        filterFilterRegistrationBean.setOrder(1);//필터 우선순위 설정
        filterFilterRegistrationBean.addUrlPatterns("/*");//필터를 적용할 URL 패턴

        return filterFilterRegistrationBean;
    }

    //@Bean
    public FilterRegistrationBean LoginCheckFilter() {
        FilterRegistrationBean<Filter> filterFilterRegistrationBean = new FilterRegistrationBean<Filter>();
        filterFilterRegistrationBean.setFilter(new LoginCheckFilter());
        filterFilterRegistrationBean.setOrder(2);//필터 우선순위 설정
        filterFilterRegistrationBean.addUrlPatterns("/*");//필터를 적용할 URL 패턴

        return filterFilterRegistrationBean;
    }
}
