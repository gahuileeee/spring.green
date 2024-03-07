package kr.co.ch05.controller;

import kr.co.ch05.dto.LunchDTO;
import kr.co.ch05.service.LunchService;
import kr.co.ch05.service.LunchService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

import java.util.List;

@Controller
public class LunchController {
    private  final LunchService service;

    public LunchController(LunchService service) {
        this.service = service;
    }

    @GetMapping("/lunch/list")
    public String list(Model model){
        List<LunchDTO> lunches = service.selectLunchs();
        model.addAttribute("lunches", lunches);
        return "/lunch/list";
    }

    @GetMapping("/lunch/register")
    public  String register(){
        return  "/lunch/register";
    }

    @PostMapping("/lunch/register")
    public  String register(LunchDTO lunchDTO){
        service.insertLunch(lunchDTO);
        return "redirect:/lunch/list";
    }

    @GetMapping("/lunch/modify")
    public String modify(@RequestParam("seq") String seq, Model model){
        LunchDTO lunch = service.selectLunch(seq);
        model.addAttribute("lunch",lunch);
        return "/lunch/modify";
    }

    @PostMapping("/lunch/modify")
    public String modify(LunchDTO lunchDTO){
        service.updateLunch(lunchDTO);
        return "redirect:/lunch/list";
    }

    @GetMapping("/lunch/delete")
    public String delete(@RequestParam("seq") String seq){
        service.deleteLunch(seq);
        return "redirect:/lunch/list";
    }

    @GetMapping("/lunch/random")
    public  String random(Model model){
        LunchDTO lunch = service.selectLunchOne();
        model.addAttribute("lunch",lunch);
        return "/lunch/random";
    }
}
