package cn.edu.tj.wenda.controller;

import cn.edu.tj.wenda.model.Question;
import cn.edu.tj.wenda.model.ViewObject;
import cn.edu.tj.wenda.service.QuestionService;
import cn.edu.tj.wenda.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mao on 2017/5/18.
 */
@Controller
public class HomeController {
    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    UserService userService;
    @Autowired
    QuestionService questionService;

    @RequestMapping(path = {"/user/{userId}"},method = {RequestMethod.GET})
    public String userIndex(Model model, @PathVariable("userId") int userId){
        List<ViewObject> vos = getQuestions(userId,0,10);
        model.addAttribute("vos",vos);
        return "index";
    }


    @RequestMapping(path = {"/","/index"},method = {RequestMethod.GET})
    public String index(Model model){
        List<ViewObject> vos = getQuestions(0,0,10);
        model.addAttribute("vos",vos);
        return "index";

    }

    private List<ViewObject> getQuestions(int userId,int offset,int limit) {
        List<Question> questionList = questionService.getLatestQuestions(userId,offset,limit);
        List<ViewObject> vos = new ArrayList<ViewObject>();
        for(Question question : questionList){
            ViewObject vo = new ViewObject();
            vo.set("question",question);
            vo.set("user",userService.getUser(question.getUserId()));
            vos.add(vo);
        }
        return vos;
    }
}
