package kr.co.ch08.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.co.ch08.DTO.UserDTO;
import kr.co.ch08.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@AllArgsConstructor
@Slf4j
@Controller
public class User2Controller {

    private  final UserService service;
    @GetMapping("/user2/login")
    public String login(@RequestParam(value = "success" , required = false) String success,
                        Model model){
        model.addAttribute("success", success);
        //security가 해줌
        return "/user2/login";
    }

    @PostMapping("/user2/login")
    public  String login(UserDTO userDTO, HttpSession session){
        UserDTO user = service.selectUser(userDTO);
        log.info(user.toString());
        if(user != null){
            //회원이 맞을 경우
            session.setAttribute("sessUser",user);
            return  "redirect:/user2/success";
        }else{
            //회원이 아닐 경우
            return  "redirect:/user2/login?success=100";
        }
    }
    @GetMapping("/user2/logout")
    public  String logout(HttpSession session){
        session.removeAttribute("sessUser");
        session.invalidate();
        return "redirect:/user2/login?success=200";
    }
    @GetMapping("/user2/register")
    public String register(){
        return "/user2/register";
    }

    @PostMapping ("/user2/register")
    public String register(UserDTO userDTO){
        return "redirect:user2/login";
    }

    @GetMapping("/user2/success")
    public String success(HttpSession session, HttpServletResponse response){
        UserDTO sessUser = (UserDTO) session.getAttribute("sessUser");
        //쿠키 생성
        Cookie cookie = new Cookie("username", sessUser.getName());
        cookie.setMaxAge(3600);
        cookie.setPath("/");
        response.addCookie(cookie);
        return "/user2/success";
    }

    @GetMapping("/user2/result")
    public  String result(@CookieValue("username")String username, Model model) {
        model.addAttribute("username",username);
        return "/user2/result";
    }
}
