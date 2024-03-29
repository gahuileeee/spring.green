package kr.co.ch04.controller;

import kr.co.ch04.dto.User5DTO;
import kr.co.ch04.service.User5Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class User5Controller {

    @Autowired
    private User5Service service;

    @GetMapping("/user5/register")
    public String register(){
      return "/user5/register";
    }


    @PostMapping("/user5/register")
    public String register(User5DTO user5DTO){
        //등록
        service.insertUser5(user5DTO);

        //리다이렉트
        return  "redirect:/user5/list";
    }

    @GetMapping("/user5/list")
    public String list(Model model){
        List<User5DTO> users= service.selectUser5s();
        model.addAttribute("users",users);
        return "/user5/list";
    }

    @GetMapping("/user5/delete")
    public String delete(@RequestParam("seq") String uid){
        service.deleteUser5(uid);
        return  "redirect:/user5/list";
    }

    @GetMapping("/user5/modify")
    public String modify(@RequestParam("seq") String uid, Model model){
     User5DTO user5DTO=   service.selectUser5(uid);
     model.addAttribute("user",user5DTO);
     return "/user5/modify";
    }

    @PostMapping("/user5/modify")
    public String modify(User5DTO user5DTO){
        service.updateUser5(user5DTO);
        return "redirect:/user5/list";
    }
}
