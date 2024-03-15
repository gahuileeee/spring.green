package kr.co.ch07.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor
@Slf4j
@Controller
public class MainController {

    @GetMapping(value = {"/","/index"})
    public  String index(){
        return  "/index";
    }

}
