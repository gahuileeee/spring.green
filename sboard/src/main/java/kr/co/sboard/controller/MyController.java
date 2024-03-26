package kr.co.sboard.controller;

import jakarta.servlet.http.HttpSession;
import kr.co.sboard.dto.UserDTO;
import kr.co.sboard.mapper.UserMapper;
import kr.co.sboard.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MyController {
    private final UserService userService;

    @GetMapping("/user/setting")
    public  String userSetting(Model model, @RequestParam("uid") String uid , @RequestParam(value = "code", required = false) String code){
        model.addAttribute("user",userService.findByUid(uid) );
        if(code ==null){
            code = "0";
        }
        model.addAttribute("code", code);
        return "/my/setting";
    }

    @PostMapping("/my/passwordChange")
    public String passwordChange(@RequestParam("pass1")String pass1, @RequestParam("uid") String uid){
        UserDTO userDTO = new UserDTO();
        userDTO.setUid(uid);
        userDTO.setPass(pass1);
        userService.updateUserPassword(userDTO);
        return "redirect:/user/setting?code="+100+"&uid="+uid;
    }

    @ResponseBody
    @GetMapping("/my/{type}/{value}/{uid}")
    public ResponseEntity changeUser(@PathVariable("type")String type, @PathVariable("value")String value,
    @PathVariable("uid") String uid){
        log.info(type+value+uid +"!!!");
        return userService.updateUserForType(type, value, uid);
    }

    @GetMapping("/my/leave")
    public String leave(@RequestParam String uid , HttpSession session){
           userService.userLeave(uid);
        return "redirect:/user/login?success="+400;
    }

    @ResponseBody
    @PutMapping("/my/modifyAddr")
    public ResponseEntity modifyAddr (@RequestBody UserDTO userDTO){
        return userService.updateUserAddr(userDTO);
    }

}
