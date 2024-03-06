package kr.co.ch05.controller;

import kr.co.ch05.dto.User1DTO;
import kr.co.ch05.dto.User2DTO;
import kr.co.ch05.service.User1Service;
import kr.co.ch05.service.User2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class User2Controller {
    private  final User2Service service;

    @Autowired
    public User2Controller(User2Service service) {
        this.service = service;
    }

    @RequestMapping(value = "/user2/list", method = RequestMethod.GET)
    public String list(Model model){
        List<User2DTO> users= service.selectUser2s();
        System.out.println(users);
        model.addAttribute("users",users);
        return "/user2/list";
    }

    @GetMapping("/user2/register")
    public String register(){
        return "/user2/register";
    }

    @PostMapping ("/user2/register")
    public  String register(User2DTO user2DTO){
        service.insertUser2(user2DTO);
        return "redirect:/user2/list";
    }

    @GetMapping ("/user2/modify")
    public String modify(@RequestParam("uid")String uid , Model model){
        User2DTO user = service.selectUser2(uid);
        return "/user2/modify";
    }

    @PostMapping ("/user2/modify")
    public String modify(User2DTO user2DTO){
        service.updateUser2(user2DTO);
        return "redirect:/user2/list";
    }

    @GetMapping("/user2/delete")
    public String delete(@RequestParam("uid")String uid){
        service.deleteUser2(uid);
        return "redirect:/user2/list";
    }
}
