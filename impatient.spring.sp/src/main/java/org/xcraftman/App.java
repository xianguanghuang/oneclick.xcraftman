package org.xcraftman;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.xcraftman.service.DefaultFooService;

/**
 * Hello world!
 */
@EnableScheduling
@Configuration
@EnableAutoConfiguration
@ComponentScan("org.xcraftman.service")
@ImportResource({"classpath:/application-context.xml"})
public class App implements CommandLineRunner {

    @Autowired
    private DefaultFooService fooService;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        fooService.updateTestColumn1();
    }
}
