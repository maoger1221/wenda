package cn.edu.tj.wenda.controller;

import cn.edu.tj.wenda.model.*;
import cn.edu.tj.wenda.service.CommentService;
import cn.edu.tj.wenda.service.QuestionService;
import cn.edu.tj.wenda.service.UserService;
import cn.edu.tj.wenda.utils.WendaUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by mao on 2017/5/23.
 */
@Controller
public class QuestionController {
    @Autowired
    QuestionService questionService;
    @Autowired
    HostHolder hostHolder;
    @Autowired
    UserService userService;
    @Autowired
    CommentService commentService;

    private static final Logger LOGGER = LoggerFactory.getLogger(QuestionController.class);

    @RequestMapping(value = "/question/add",method = RequestMethod.POST)
    @ResponseBody
    public String addQuestion(@RequestParam("title") String title,@RequestParam("content") String content){
        try{
            Question question = new Question();
            question.setTitle(title);
            question.setContent(content);
            question.setCreatedDate(new Date());
            question.setCommentCount(0);
            if(hostHolder.getUser() != null){
                question.setUserId(hostHolder.getUser().getId());
            }else{
                question.setUserId(WendaUtil.ANONYMOUS_USERID);
//                return WendaUtil.getJSONString(999);
            }

            if (questionService.addQuestion(question) > 0){
                return WendaUtil.getJSONString(0);
            }

        }catch (Exception e){
            LOGGER.error("提问失败" + e.getMessage());
        }
        return WendaUtil.getJSONString(1,"失败");
    }

    @RequestMapping("/question/{qid}")
    public String questionDetail(Model model, @PathVariable("qid") int qid){
        Question question = questionService.getQuestion(qid);
        model.addAttribute("question",question);
//        model.addAttribute("user",userService.getUser(question.getUserId()));
        List<Comment> commentList = commentService.getCommentByEntity(qid, EntityType.ENTITY_QUESTION);
        List<ViewObject> comments = new ArrayList<>();
        for (Comment comment : commentList){
            ViewObject vo = new ViewObject();
            vo.set("comment",comment);
            vo.set("user",userService.getUser(comment.getUserId()));
            comments.add(vo);
        }
        model.addAttribute("comments",comments);
        return "detail";
    }
}
