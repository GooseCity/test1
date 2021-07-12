package com.zyp.restdemo.service;

import com.zyp.restdemo.entity.User;
import com.zyp.restdemo.exception.EtAuthException;

import java.util.List;

public interface UserService {
    public void saveUser(User user);

    public User findByUserName(String userName);

    public User findByEmailAndPassword(String email, String password);

    public Integer getCountByEmail(String email);

    public User findById(long userId);

    public List<User> findAll();

    public void delete(User user);

    public User validateUser(String email, String password) throws EtAuthException;

    public User registerUser(String userName, String email, String password) throws EtAuthException;
}
