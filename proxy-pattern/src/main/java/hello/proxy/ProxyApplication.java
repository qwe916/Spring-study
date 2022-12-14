package hello.proxy;

import hello.proxy.config.AppV1Config;
import hello.proxy.config.AppV2Config;
import hello.proxy.config.BeanPostProcessorConfig;
import hello.proxy.config.v1_proxy.ConcreteProxy;
import hello.proxy.config.v1_proxy.InterfaceProxyConfig;
import hello.proxy.config.v2_dynamicproxy.DynamicProxyFilterConfig;
import hello.proxy.config.v3_proxyfactory.ProxyFactoryConfigV1;
import hello.proxy.config.v3_proxyfactory.ProxyFactoryConfigV2;
import hello.proxy.config.v5_autoproxy.AutoProxyConfig;
import hello.proxy.config.v6_config.AopConfig;
import hello.proxy.trace.logtrace.FieldLogTrace;
import hello.proxy.trace.logtrace.LogTrace;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

/*@Import(InterfaceProxyConfig.class)*/
/*@Import(ConcreteProxy.class)*/
/*
@Import(DynamicProxyFilterConfig.class)
*/
/*
@Import(ProxyFactoryConfigV2.class)
*/
/*@Import(AutoProxyConfig.class)*/
@Import(AopConfig.class)
@SpringBootApplication(scanBasePackages = "hello.proxy.app") //주의 componet scan할 때 app 디렉터리 아래의 파일들만 scan한다.
public class ProxyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProxyApplication.class, args);
	}

	@Bean
	public LogTrace logTrace() {
		return new FieldLogTrace();
	}
}
