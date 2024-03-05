package kr.co.ch03;

import kr.co.ch03.config.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App 
{
    public static void main( String[] args )
    {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        BasicService basicService = (BasicService) context.getBean("basicService");
        basicService.insert();
        basicService.select(null); //예외 발생시킴
        basicService.update();
        basicService.delete();
    }
}
