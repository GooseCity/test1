package com.zyp.restdemo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue
    private long user_id;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "user_profile_img")
    private String user_profile_img;
    @Column(name = "user_password")
    private String password;
    @Column(name = "user_email")
    private String email;

    public User(String name, String email,String pwd){
        this.userName = name;
        this.email = email;
        this.password = pwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String user_email) {
        this.email = user_email;
    }

    public User() {

    }

    public String getUser_password() {
        return password;
    }

    public void setUser_password(String user_password) {
        this.password = user_password;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return userName;
    }

    public void setUser_name(String user_name) {
        this.userName = user_name;
    }

    public String getUser_profile_img() {
        return user_profile_img;
    }

    public void setUser_profile_img(String user_profile_img) {
        this.user_profile_img = user_profile_img;
    }
}
