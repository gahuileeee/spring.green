package kr.co.ch08.controller;

import kr.co.ch08.DTO.UserDTO;
import kr.co.ch08.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@AllArgsConstructor
@Slf4j
@Controller
public class User1Controller {
    private  final UserService userService;

    @GetMapping("/user1/login")
    public String login(@RequestParam(value = "success" , required = false) String success,
                        Model model){
        model.addAttribute("success", success);
        //security가 해줌
        return "/user1/login";
    }

    @GetMapping("/user1/register")
    public String register(){
        return "/user1/register";
    }

    @PostMapping ("/user1/register")
    public String register(UserDTO userDTO){
        userService.insertUser(userDTO);
        return "redirect:user1/login";
    }

    @GetMapping("/user1/success")
    public String success(){
        return "/user1/success";
    }

}
