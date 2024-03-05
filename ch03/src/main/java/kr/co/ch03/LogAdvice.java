package kr.co.ch03;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class LogAdvice {

    @Pointcut("execution(void  kr.co.ch03.BasicService.insert())")
    public  void  insertPointcut(){}//내용이 없는 참조 메서드

    @Pointcut("execution(void  kr.co.ch03..*Service.select*(..))")
    //이 경로 아래 service로 끝나는 method이며, 반환 값은 제한없음
    public  void  selectPointcut(){}



    @Before("insertPointcut() || selectPointcut()") //이 pointcut을 지정한 method가 실행되기 전에 실행됨
    public  void  beforeLog(){
        System.out.println("-----------------------");
        System.out.println("부각기능 - beforeLog()...");
    }

    @After("insertPointcut() || selectPointcut()")
    public  void  afterLog(){
        System.out.println("-----------------------");
        System.out.println("부각기능 - afterLog()...");
    }

    @AfterReturning("insertPointcut()") //afterlog 전에 실행됨
    public  void  afterReturnLog(){
        System.out.println("부각기능 -  afterReturnLog()...");
    }

    @Around("insertPointcut()") //핵심 관심을 감싸는 형태
    public  void  arroundLog(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("부가기능 - arroundLog()...1");
        pjp.proceed(); //핵심 관심 실행
        System.out.println("부가기능 - arroundLog()...2");
    }

    @AfterThrowing("selectPointcut()")
    public  void  afterThrowLog(){
        System.out.println("부가기능 - afterThrowLog()...");
    }
    

}
