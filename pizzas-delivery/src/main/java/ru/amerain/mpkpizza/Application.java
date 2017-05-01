package ru.amerain.mpkpizza;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;


@EnableAutoConfiguration
@ComponentScan
public class Application {

    public static void main(String[] args) throws Exception {
       SpringApplication.run(Application.class, args);
        ApplicationContext context = new AnnotationConfigApplicationContext(ContextConfiguration.class);
    }
}
