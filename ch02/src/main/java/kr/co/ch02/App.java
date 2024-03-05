package kr.co.ch02;

import kr.co.ch02.config.AppConfig;
import kr.co.ch02.sub1.Greeting;
import kr.co.ch02.sub1.Hello;
import kr.co.ch02.sub1.Welcome;
import kr.co.ch02.sub2.Computer;
import kr.co.ch02.sub2.Cpu;
import kr.co.ch02.sub2.Hdd;
import kr.co.ch02.sub2.Ram;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        //스프링 컨테이너 생성 (AppConfig class를 이용하여 ApplicationContext 객체 생성)
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        //빈 가져오기
        Hello hello = context.getBean(Hello.class);
        hello.show();

        Welcome welcome = (Welcome) context.getBean("welcome");
        welcome.show();

        Greeting greeting = (Greeting) context.getBean("greeting");
        greeting.show();

        //Ioc/DI 기법을 사용하지 않을 경우
        /*
        Cpu cpu= new Cpu();
        Ram ram = new Ram();
        Hdd hdd = new Hdd();

        Computer com= new Computer(cpu,ram);
        com.show();
    */

        Computer com = (Computer) context.getBean("com");
        //주입된 빈의 이름을 직접 지정하지 않았다면 해당 클래스의 첫 글자를 소문자로 한 이름을 자동으로 할당
        com.show();
    }
}
