package kr.co.ch02.config;

import kr.co.ch02.sub1.Greeting;
import kr.co.ch02.sub1.Hello;
import kr.co.ch02.sub1.Welcome;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"kr.co.ch02"}) //컴포넌트로 선언된 클래스만 자동 스캐닝함
public class AppConfig {

    @Bean (name = "hello") //빈의 이름을 지어주는 것
    public Hello hello(){
        return new Hello();
    }

    @Bean ("welcome") //바로 이렇게 지정해도 됨
    public Welcome welcome(){
        return new Welcome();
    }

    @Bean ("greeting")
    public Greeting greeting(){
        return new Greeting();
    }
}
