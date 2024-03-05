package kr.co.ch04.controller;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller //컨트롤러
public class MainController {

    @RequestMapping(value="/hello", method = RequestMethod.GET) //주소 mapping
    public  String hello(){
        System.out.println("hello...");
        System.out.println("왜 못찾노!!");
        return "hello";
    }

    @GetMapping("/welcome")
    public String welcome(){
        System.out.println("welcome...");
        return "welcome";
    }

    @GetMapping("/greeting")
    public String greeting(){
        return "greeting";
    }
}
