package hello.proxy.config.v4_postprocessor.postprocessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
@Slf4j
public class PackageLogTraceProxyPostProcessor implements BeanPostProcessor {
    private final String basePackage;
    private final Advisor advisor;
    public PackageLogTraceProxyPostProcessor(String basePackage, Advisor
            advisor) {
        this.basePackage = basePackage;
        this.advisor = advisor;
    }
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName)
            throws
            BeansException {
        log.info("param beanName={} bean={}", beanName, bean.getClass());
        //프록시 적용 대상 여부 체크
        //프록시 적용 대상이 아니면 원본을 그대로 반환
        String packageName = bean.getClass().getPackageName();
        if (!packageName.startsWith(basePackage)) {//basepackage가 아니라면 원본을 반환하고 아니면 프록시를 반환
            return bean;
        }
        //프록시 대상이면 프록시를 만들어서 반환
        ProxyFactory proxyFactory = new ProxyFactory(bean);
        proxyFactory.addAdvisor(advisor);
        Object proxy = proxyFactory.getProxy();
        log.info("create proxy: target={} proxy={}", bean.getClass(),
                proxy.getClass());
        return proxy;
    }
}
