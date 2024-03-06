package kr.co.ch05.controller;

import kr.co.ch05.dto.User3DTO;
import kr.co.ch05.service.User3Service;
import kr.co.ch05.service.User3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class User3Controller {
    private  final User3Service service;

    @Autowired
    public User3Controller(User3Service service) {
        this.service = service;
    }

    @RequestMapping(value = "/user3/list", method = RequestMethod.GET)
    public String list(Model model){
        List<User3DTO> users= service.selectUser3s();
        model.addAttribute("users",users);
        return "/user3/list";
    }

    @GetMapping("/user3/register")
    public String register(){
        return "/user3/register";
    }

    @PostMapping ("/user3/register")
    public  String register(User3DTO user3DTO){
        service.insertUser3(user3DTO);
        return "redirect:/user3/list";
    }

    @GetMapping ("/user3/modify")
    public String modify(@RequestParam("uid")String uid , Model model){
        User3DTO user = service.selectUser3(uid);
        model.addAttribute("user",user);
        return "/user3/modify";
    }

    @PostMapping ("/user3/modify")
    public String modify(User3DTO user3DTO){
        service.updateUser3(user3DTO);
        return "redirect:/user3/list";
    }

    @GetMapping("/user3/delete")
    public String delete(@RequestParam("uid")String uid){
        service.deleteUser3(uid);
        return "redirect:/user3/list";
    }
}
