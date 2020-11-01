package com.example.demo.controller;

import com.example.demo.entity.User;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
public class userC {

    @Resource    // 自动注入，spring boot会帮我们实例化一个对象
    private JdbcTemplate jdbcTemplate;
    // 一个通过JDBC连接数据库的工具类，可以通过这个工具类对数据库进行增删改查

    @ResponseBody
    @PostMapping(value = "/login")
    public String login(@RequestBody User user){
        System.out.println("登录:+username+password"+user);
        String userSql="select * from users where username='"+user.getUsername()+"' AND password='"+user.getPassword()+"'";

        List<Map<String, Object>> userOne = jdbcTemplate.queryForList(userSql);
//      String userAccount=(String)jdbcTemplate.queryForObject(userAccountSql, java.lang.String.class);
        System.out.println("userOne:"+userOne);
        String str = JSON.toJSONString(userOne); // List转json
        return str;
    }
}
