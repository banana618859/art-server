package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.Student;
import com.example.demo.entity.result;
import com.example.demo.entity.Person;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.alibaba.fastjson.JSONObject.*;

@Controller
public class hellc {

    @GetMapping(value = "/")
    public String index(){
        return "index.html";
    }

    @GetMapping(value = "/excel")
    public String indexa(){
        System.out.println("come in excel--");
        return "/abc/index_cska";
//        return "index_csk";
    }

    @GetMapping(value = "/excela")
    public String indexab(){
        System.out.println("打开子页面");
//        return "/abc/eee/cske.html";
        return "/abc/cska.html";
    }


    @GetMapping(value = "/getAll")
    public String getAll(@RequestBody JSONObject jsonObject){
        String col=jsonObject.get("allCol").toString();
        System.out.println("列"+col);
        return col;
    }

    @ResponseBody
    @PostMapping(value = "/ggg")
    public JSONObject demo(@RequestBody Person person){
        System.out.println(person);
        Object [] top = person.getCol();
        System.out.println("top-name:"+top);
        String sq = "select";
        for(int i=0;i<top.length;i++){
            if(i==top.length-1){
                sq+=" "+top[i];
            }else{
                sq+=" "+top[i]+",";
            }
        }
        String sql =  " from student";
        sql = sq+sql;
        System.out.println("sql: "+sql);
        JSONObject user = this.stu(sql);

        System.out.println(user);

        return user;
    }
//  原文链接：https://blog.csdn.net/li3455277925/article/details/88933221
//    原文链接：https://blog.csdn.net/weixin_43377380/article/details/82998819


    /**
     * 判断查询结果集中是否存在某列
     * @param rs 查询结果集
     * @param columnName 列名
     * @return true 存在; false 不存咋
     */
    public boolean isExistColumn(ResultSet rs, String columnName) {
        try {
            if (rs.findColumn(columnName) > 0 ) {
                return true;
            }
        }
        catch (SQLException e) {
            return false;
        }

        return false;
    }
//原文链接：https://blog.csdn.net/u014543351/article/details/49813867

    @Resource    // 自动注入，spring boot会帮我们实例化一个对象
    private JdbcTemplate jdbcTemplate;
    // 一个通过JDBC连接数据库的工具类，可以通过这个工具类对数据库进行增删改查
    
    public List<Student> searchFun(String sql){
//        String sql = "select * from student";
        List<Student> students = jdbcTemplate.query(sql, new RowMapper<Student>() {
            @Override
            public Student mapRow(ResultSet resultSet, int i) throws SQLException {
                Student student = new Student();
                if(isExistColumn(resultSet, "id")){
                    student.setId(resultSet.getInt("id"));
                }
                if(isExistColumn(resultSet, "username")){
                    student.setUsername(resultSet.getString("username"));
                }
                if(isExistColumn(resultSet, "password")){
                    student.setPassword(resultSet.getString("password"));
                }
                if(isExistColumn(resultSet, "sex")){
                    student.setSex(resultSet.getString("sex"));
                }
                return student;
            }
        });
        return students;
    }



    @ResponseBody
    @GetMapping(value = "/stu")
    public JSONObject stu(String sql){
        if(sql == null){
            sql = "select * from students";
        }
        List<Student> students = this.searchFun(sql);
        result one = new result();
        one.setCode(0);
        one.setCount(students.toArray().length);
        one.setData((ArrayList) students);
        one.setMsg("查询成功！");
        String userJson = JSON.toJSONString(one);
        JSONObject user = parseObject(userJson);
        System.out.println("查询结果-back："+user);
        return user;
    }
}
