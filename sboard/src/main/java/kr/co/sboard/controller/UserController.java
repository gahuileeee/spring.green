package kr.co.sboard.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kr.co.sboard.config.AppInfo;
import kr.co.sboard.dto.TermsDTO;
import kr.co.sboard.dto.UserDTO;
import kr.co.sboard.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @GetMapping("/user/login")
    public String login(@ModelAttribute("success") String success, Model model){
        // 매개변수 success에 @ModelAttribute 선언으로 View 참조할 수 있음
        return "/user/login";
    }

    @GetMapping("/user/terms")
    public String terms(Model model){

        TermsDTO termsDTO = userService.selectTerms();
        model.addAttribute(termsDTO);

        return "/user/terms";
    }

    @GetMapping("/user/register")
    public String register(){
        return "/user/register";
    }

    @PostMapping("/user/register")
    public String register(HttpServletRequest req, UserDTO userDTO){

        String regip = req.getRemoteAddr();
        userDTO.setRegip(regip);

        log.info(userDTO.toString());

        userService.insertUser(userDTO);

        return "redirect:/user/login?success=200";
    }

    @ResponseBody
    @GetMapping("/user/{type}/{value}")
    public ResponseEntity<?> checkUser(HttpSession session,
                                       @PathVariable("type")  String type,
                                       @PathVariable("value") String value){

        int count = userService.selectCountUser(type, value);
        log.info("count : " + count);

        // 중복 없으면 이메일 인증코드 발송
        if(count == 0 && type.equals("email")){
            log.info("email : " + value);
            userService.sendEmailCode(session, value);
        }

        // Json 생성
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result", count);

        return ResponseEntity.ok().body(resultMap);
    }

    // 이메일 인증 코드 검사
    @ResponseBody
    @GetMapping("/email/{code}")
    public ResponseEntity<?> checkEmail(HttpSession session, @PathVariable("code")  String code){

        String sessionCode = (String) session.getAttribute("code");

        if(sessionCode.equals(code)){
            // Json 생성
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("result", true);

            return ResponseEntity.ok().body(resultMap);
        }else{
            // Json 생성
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("result", false);

            return ResponseEntity.ok().body(resultMap);
        }
    }

    @GetMapping("/user/findId")
    public  String findId(){
        return "/user/findId";
    }
    @PostMapping("/user/findId")
    public  ResponseEntity findId(@RequestBody UserDTO userDTO, HttpSession session ){
        return  userService.findByNameAndEmail(session, userDTO);
    }

    @PostMapping("/user/findIdResult")
    public  String findIdResult(@RequestParam("email") String email , Model model){
        log.info("이메일 : "+email);
        model.addAttribute("user", userService.findByEmail(email));
        return "/user/findIdResult";
    }

    @PostMapping("/user/findPassword")
    public  ResponseEntity findPassword(@RequestBody UserDTO userDTO, HttpSession session ){
        return  userService.findByUidAndEmail(session, userDTO);
    }

    @GetMapping("/user/findPassword")
    public  String findPassword(){
        log.info("여기1!");
        return "/user/findPassword";
    }

    @PostMapping("/user/findPasswordChange")
    public  String findPasswordChange(@RequestParam("email") String email, Model model){
        model.addAttribute("user", userService.findByEmail(email));
        return "/user/findPasswordChange";
    }

    @PostMapping("/user/passwordChange")
    public  String passwordChange(@RequestParam("pass1")String pass1, @RequestParam("uid") String uid){
        UserDTO userDTO = new UserDTO();
        userDTO.setUid(uid);
        userDTO.setPass(pass1);
        userService.updateUserPassword(userDTO);
        return "redirect:/user/login";
    }


}