package com.zyp.restdemo.controller;

import com.zyp.restdemo.Constants;
import com.zyp.restdemo.entity.User;
import com.zyp.restdemo.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService userService;

    //private final AtomicLong counter = new AtomicLong();

//    @GetMapping("/")
//    public User testuser(@RequestParam(value = "name", defaultValue = "World") String name, @RequestParam(value = "img", defaultValue = "/") String img,
//                         @RequestParam(value = "pwd", defaultValue = "/" ) String pwd, @RequestParam(value = "email", defaultValue = "" ) String email) {
//        return new User(counter.incrementAndGet(), name, email, pwd, img);
//    }

    @RequestMapping("/getAllUser")
    @ResponseBody
    public List<User> findAll() {
        List<User> list = new ArrayList<User>();
        list = userService.findAll();
        return list;
    }

    @RequestMapping("/getByUserName")
    @ResponseBody
    public User getByUserName(String userName) {
        User user = userService.findByUserName(userName);
        return user;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginUser(HttpServletRequest request, Model model) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
//        String email = (String) userMap.get("email");
//        String password = (String) userMap.get("password");
        User user = userService.validateUser(email, password);
        return new ResponseEntity<>(generateJWTToken(user), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody Map<String, Object> userMap) {
        String username = (String) userMap.get("username");
        String email = (String) userMap.get("email");
        String password = (String) userMap.get("password");
//        User user = new User(username,email,password);
//        userService.saveUser(user);
        User user = userService.registerUser(username, email, password);
        return new ResponseEntity<>(generateJWTToken(user), HttpStatus.OK);
    }

    @RequestMapping("/save")
    @ResponseBody
    public ResponseEntity<Map<String, String>> saveUser(HttpServletRequest request, Model model) {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if(password.length() < 6){
            model.addAttribute("message","Please Enter at least 6 length password");
            //return "register";
        }
        String email = request.getParameter("email");

        User user = new User(username,password,email);
        userService.saveUser(user);

        return new ResponseEntity<>(generateJWTToken(user), HttpStatus.OK);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String delete(HttpServletRequest request, Model model) {
        User user = userService.findByUserName(request.getParameter("username"));
        userService.delete(user);
        return "success";

    }

    private Map<String, String> generateJWTToken(User user) {
        long timestamp = System.currentTimeMillis();
        String token = Jwts.builder().signWith(SignatureAlgorithm.HS256, Constants.API_SECRET_KEY)
                .setIssuedAt(new Date(timestamp))
                .setExpiration(new Date(timestamp + Constants.TOKEN_VALIDITY))
                .claim("userId", user.getUser_id())
                .claim("email", user.getEmail())
                .claim("userName", user.getUser_name())
                .compact();
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        return map;
    }


}
