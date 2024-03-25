package kr.co.sboard.controller;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.servlet.http.HttpServletRequest;
import kr.co.sboard.dto.ArticleDTO;
import kr.co.sboard.dto.FileDTO;
import kr.co.sboard.dto.PageRequestDTO;
import kr.co.sboard.dto.PageResponseDTO;
import kr.co.sboard.service.ArticleService;
import kr.co.sboard.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.support.incrementer.HsqlMaxValueIncrementer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.Console;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ArticleController {

    private final ArticleService articleService;
    private  final FileService fileService;

    /*
        @ModelAttribute("cate")
         - modelAttribute("cate", cate)와 동일
    */
    @GetMapping("/article/list")
    public String list(Model model, PageRequestDTO pageRequestDTO, @ModelAttribute("cate") String cate, @RequestParam(value = "pg", required = false) Integer pg)
    {
        if(pg != null){
            pageRequestDTO.setPg(pg);
        }

        PageResponseDTO pageResponseDTO = articleService.findByParentAndCate(pageRequestDTO);
        model.addAttribute(pageResponseDTO);

        return "/article/list";
    }

    @GetMapping("/article/write")
    public String write(@ModelAttribute("cate") String cate){
        return "/article/write";
    }

    @PostMapping("/article/write")
    public String write(HttpServletRequest req, ArticleDTO articleDTO){
        /*
            글작성을 테스트할 때는 로그인해야하기 때문에
            SecurityConfig 인가 설정 수정할 것
        */
        String regip = req.getRemoteAddr();
        articleDTO.setRegip(regip);

        log.info(articleDTO.toString());

        articleService.insertArticle(articleDTO);

        return "redirect:/article/list?cate="+articleDTO.getCate();
    }

    @GetMapping("/article/view")
    public String view(int no, Model model, @ModelAttribute("pg") int pg){
        ArticleDTO articleDTO= articleService.selectArticle(no);
        model.addAttribute("articleDTO", articleDTO);
        articleDTO.setHit(articleDTO.getHit() +1);
        articleService.updateArtice(articleDTO);

        List<ArticleDTO> comments = articleService.selectComment(no);
        log.info("comments "+comments);
        model.addAttribute("cate", articleDTO.getCate());
        model.addAttribute("comments",comments);
        return "/article/view";
    }

    @GetMapping("/article/modify")
    public String modify(int no, Model model, @ModelAttribute("pg") int pg) {
        ArticleDTO articleDTO = articleService.selectArticle(no);
        model.addAttribute("article", articleDTO);
        model.addAttribute("cate", articleDTO.getCate());
        return "/article/modify";
    }

    @ResponseBody
    @PutMapping("/article/modifyFile")
    public ResponseEntity modifyFile(@RequestBody Map<String, List<Integer>> map){
        List<Integer> deleteList = map.get("deleteFileList");
        int no = map.get("no").get(0);

        return  fileService.deleteFile(deleteList, no);
    }

    @GetMapping("/article/delete")
    public String delete( int no, String cate, @ModelAttribute("pg") int pg) {
        fileService.deleteFiles(no);
        articleService.deleteArticle(no);
        return "redirect:/article/list?cate="+cate+"&pg="+pg;
    }

    @PostMapping("/article/modify")
    public String modify(ArticleDTO articleDTO, @ModelAttribute("pg") int pg){
        log.info("modify"+articleDTO.toString());

        articleService.modifyArticle(articleDTO);

        return "redirect:/article/view?no="+articleDTO.getNo()+"&pg="+pg;
    }

    //comment
    @PostMapping("/article/insertComment" )
    public ResponseEntity insertComment(@RequestBody ArticleDTO commentDTO, HttpServletRequest request){
        commentDTO.setRegip(request.getRemoteAddr());
        log.info("info.. "+commentDTO);
        return articleService.inserComment(commentDTO);
    }

    @ResponseBody
    @DeleteMapping("/article/deleteComment/{no}")
    public ResponseEntity deleteComment(@PathVariable("no") int no){
     return   articleService.deleteComment(no);
    }

    @ResponseBody
    @PutMapping("/article/modifyComment")
    public ResponseEntity  modifyComment(@RequestBody ArticleDTO commentDTO){
        log.info("modify! "+commentDTO);
        ArticleDTO oldComment = articleService.selectCommentByNo(commentDTO.getNo());
        oldComment.setContent(commentDTO.getContent());

        return articleService.updateComment(oldComment);
    }

    @ResponseBody
    @GetMapping("/article/selectComment/{no}")
    public ResponseEntity  selectComment(@PathVariable("no") int no){
        ArticleDTO articleDTO =articleService.selectCommentByNo(no);
        Map<String , Object> map = new HashMap<>();
        map.put("article", articleDTO);
        return ResponseEntity.ok().body(map);
    }

}

