package kr.co.ch07.controller;

import kr.co.ch07.dto.User1DTO;
import kr.co.ch07.service.User1Service;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@AllArgsConstructor
public class User1Controller {
    /*
    private  final User1Service user1Service;

    @GetMapping("/user1/list")
    public String list(Model model){
        model.addAttribute("users", user1Service.selectUser1s());
        return "/user1/list";
    }

    @PostMapping("/user1/register")
    public String register(User1DTO user1DTO){
        user1Service.insertUser1(user1DTO);
        return "redirect:/user1/list";
    }
    @GetMapping("/user1/register")
    public String register(){
        return "/user1/register";
    }

    @PostMapping("/user1/modify")
    public String modify(User1DTO user1DTO){
        user1Service.updateUser1(user1DTO);
        return "redirect:/user1/list";
    }

    @GetMapping("/user1/modify")
    public String modify(String uid , Model model){
        model.addAttribute("user", user1Service.selectUser1(uid));
        return "/user1/modify";
    }

    @GetMapping("/user1/delete")
    public String delete(String uid){
        user1Service.deleteUser1(uid);
        return "redirect:/user1/list";
    }

     */
}
