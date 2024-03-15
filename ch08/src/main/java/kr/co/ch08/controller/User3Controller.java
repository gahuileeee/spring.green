package kr.co.ch08.controller;

import jakarta.servlet.http.HttpSession;
import kr.co.ch08.DTO.UserDTO;
import kr.co.ch08.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@AllArgsConstructor
@Slf4j
@Controller
@SessionAttributes("sessUser")
public class User3Controller {

    private  final UserService service;
    @GetMapping("/user3/login")
    public String login(@RequestParam(value = "success" , required = false) String success,
                        Model model){
        model.addAttribute("success", success);
        //security가 해줌
        return "/user3/login";
    }

    @PostMapping("/user3/login")
    public  String login(UserDTO userDTO, Model model){
            UserDTO user = service.selectUser(userDTO);
            if( user != null){
                //Annotation으로 sessUser Session 생성했기 때문에
                //model을 통해 참조해서 저장함
                model.addAttribute("sessUser", user);
                return "/user3/success";
            }else{
                return "redirect:/user3/login?success=100";
            }
    }
    @GetMapping("/user3/logout")
    public  String logout(SessionStatus status){
        //Annotation으로 지정한 SessUser session 해제
        status.setComplete();
        return "redirect:/user3/login?success=200";
    }
    @GetMapping("/user3/register")
    public String register(){
        return "/user3/register";
    }

    @PostMapping ("/user3/register")
    public String register(UserDTO userDTO){
        return "redirect:user3/login";
    }

    @GetMapping("/user3/success")
    public String success(){
        return "/user3/success";
    }


}
