package com.egova;

import com.egova.EgovaPlatform;
import com.egova.model.PropertyDescriptorScan;
import com.egova.security.EgovaResourceServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.boot.web.servlet.support.ErrorPageFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 微服务启动
 *
 * @author 奔波儿灞
 * @since 1.0.0
 */
@EgovaResourceServer
@EgovaPlatform
@EnableScheduling
@EnableAsync
@PropertyDescriptorScan
public class CloudApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(CloudApplication.class, args);
        } catch (Throwable t) {
            // 防止启动异常来不及输出日志而退出程序
            t.printStackTrace();
        }
    }

    @Bean
    public ConfigurableServletWebServerFactory webServerFactory() {
        // 在请求目标中发现无效字符。有效字符在RFC 7230和RFC 3986中定义。
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
        factory.addConnectorCustomizers(connector -> connector.setProperty("relaxedQueryChars", "|{}[]"));
        return factory;
    }

    @Bean
    public ErrorPageFilter errorPageFilter() {
        return new ErrorPageFilter();
    }

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Bean
    public FilterRegistrationBean<ErrorPageFilter> disableSpringBootErrorFilter(ErrorPageFilter filter) {
        FilterRegistrationBean<ErrorPageFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(filter);
        filterRegistrationBean.setEnabled(false);
        return filterRegistrationBean;
    }

}
