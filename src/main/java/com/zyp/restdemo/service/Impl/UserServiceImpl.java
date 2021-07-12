package com.zyp.restdemo.service.Impl;

import com.zyp.restdemo.dao.UserDAO;
import com.zyp.restdemo.entity.User;
import com.zyp.restdemo.exception.EtAuthException;
import com.zyp.restdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.regex.Pattern;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDao;

    @Override
    public void saveUser(User user) {
        userDao.save(user);
        //return user.getUser_id();
    }

    @Override
    public User findByUserName(String userName) {
        return null;
    }

    @Override
    public User findByEmailAndPassword(String email, String password) {
        return null;
    }

    @Override
    public Integer getCountByEmail(String email) {
        return userDao.getCountByEmail(email);
    }

    @Override
    public User findById(long userId) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public void delete(User user) {
        userDao.delete(user);
    }

    @Override
    public User validateUser(String email, String password) {
        return null;
    }

    @Override
    public User registerUser(String userName, String email, String password) {
        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
        if(email != null) email = email.toLowerCase();
        if(!pattern.matcher(email).matches()){
            throw new EtAuthException("Invalid email format");
        }

        Integer count = userDao.getCountByEmail(email);
        if(count > 0)
            throw new EtAuthException("Email already in use");
        User user = new User(userName, email, password);
        user = userDao.save(user);
        return userDao.findById(user.getUser_id());
    }
}
