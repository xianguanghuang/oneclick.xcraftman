package impatient.springboot.configuration;

import impatient.springboot.hello.FooBarHandlerMethodArgumentResolver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * Created by Administrator on 2016/1/5.
 */
//// TODO: 2016/1/5 need add example to verify
@Configuration
@PropertySource("application.properties")
public class ApplyPropertiesConfiguration {

    @Value("${redis.hostname}")
    private String redisHostName;

    @Value("${redis.port}")
    private int redisPort;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public  String useHostName(){
        return redisHostName;
    }

    @Bean
    public FooBarHandlerMethodArgumentResolver resolver(){
        return new FooBarHandlerMethodArgumentResolver();
    }


}
