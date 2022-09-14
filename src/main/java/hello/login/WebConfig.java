package hello.login;

import hello.login.web.filter.LogFilter;
import hello.login.web.filter.LoginCheckFilter;
import hello.login.web.interceptor.LogInterceptor;
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
