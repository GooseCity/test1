package com.zyp.restdemo.dao;

import com.zyp.restdemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserDAO extends JpaRepository<User, Long> {

    //    void saveUser(User user);

    User findByUserName(String userName);

    User findByEmailAndPassword(String email, String password);

    Integer getCountByEmail(String email);

    User findById(long userId);

    List<User> findAll();


    @Modifying
    @Transactional
    @Query("update User u set u.userName=?1,u.email=?2,u.password=?3 where u.user_id=?4")
    void modifyOne(String userName, String email, String password, long id);

    @Modifying
    @Transactional
    @Query(value = "delete from User u where u.user_id=?1", nativeQuery = true)
    void deleteById(int id);


}
