package kr.co.ch05.controller;

import kr.co.ch05.dto.MemberDTO;
import kr.co.ch05.dto.ParentDTO;
import kr.co.ch05.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.service.annotation.GetExchange;

import java.util.List;

@Controller
public class MemberController {
    private  final MemberService service;

    public MemberController(MemberService service) {
        this.service = service;
    }

    @GetExchange("/member/list")
    public String list(Model model){
        List<MemberDTO> members = service.selectMembers();
        model.addAttribute("members", members);
        return "/member/list";
    }

    @GetMapping("/member/register")
    public  String register(){
        return  "/member/register";
    }

    @PostMapping("/member/register")
    public  String register(MemberDTO memberDTO){
        service.insertMember(memberDTO);
        return "redirect:/member/list";
    }

    @GetMapping("/member/modify")
    public String modify(@RequestParam("uid") String uid, Model model){
        MemberDTO member = service.selectMember(uid);
        model.addAttribute("member",member);
        return "/member/modify";
    }

    @PostMapping("/member/modify")
    public String modify(MemberDTO memberDTO){
        service.updateMember(memberDTO);
        return "redirect:/member/list";
    }

    @GetMapping("/member/delete")
    public String delete(@RequestParam("uid") String uid){
        service.deleteMember(uid);
        return "redirect:/member/list";
    }

    @ResponseBody // json으로 출력
    @GetMapping("/member/parents")
    public void parent(){
        List<ParentDTO> parents = service.selectParentWithChild();
        for(ParentDTO p : parents){
            System.out.println(p);
        }
    }
    @GetMapping("/member/search")
    public String search(@RequestParam("type") String type,
                         @RequestParam("value") String value,
                         @RequestParam(value = "pos" , required = false) String [] pos,
                         Model model){
        List<MemberDTO> members= service.selectMembersForSearch(type,value, pos);
        model.addAttribute("members",members);
        return "/member/list";
    }

}
