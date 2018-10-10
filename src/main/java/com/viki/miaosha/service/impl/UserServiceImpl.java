package com.viki.miaosha.service.impl;

import com.viki.miaosha.dao.UserDao;
import com.viki.miaosha.domain.User;
import com.viki.miaosha.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public User getByid(int id) {
      return userDao.getById(id);
    }

    @Override
    @Transactional
    public boolean tx() {
       User user1=new User();
       user1.setId(2);
       user1.setName("2");
       User user2=new User();
       user2.setId(2);
       user2.setName("3");
       userDao.insertUser(user1);
       userDao.insertUser(user2);
       return true;
    }
}
