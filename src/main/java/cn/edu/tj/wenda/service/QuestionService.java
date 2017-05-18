package cn.edu.tj.wenda.service;

import cn.edu.tj.wenda.dao.QuestionDao;
import cn.edu.tj.wenda.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mao on 2017/5/18.
 */
@Service
public class QuestionService {
    @Autowired
    QuestionDao questionDao;

    public List<Question> getLatestQuestions(int userId,int offset,int limit){
        return questionDao.selectLatestQuestions(userId,offset,limit);
    }
}
