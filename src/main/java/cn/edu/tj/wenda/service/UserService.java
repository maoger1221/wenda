package cn.edu.tj.wenda.service;

import cn.edu.tj.wenda.dao.UserDao;
import cn.edu.tj.wenda.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by mao on 2017/5/18.
 */
@Service
public class UserService {
    @Autowired
    UserDao userDao;
    public User getUser(int id){
        return userDao.selectById(id);
    }
}
