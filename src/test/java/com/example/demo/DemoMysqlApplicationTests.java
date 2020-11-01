package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Student;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@SpringBootTest
class DemoMysqlApplicationTests {

	@Test
	void contextLoads() {
	}

	@Resource    // 自动注入，spring boot会帮我们实例化一个对象
	private JdbcTemplate jdbcTemplate;   // 一个通过JDBC连接数据库的工具类，可以通过这个工具类对数据库进行增删改查

	@Test
	public void mySqlTest(){
		String sql = "select id,name,password,sex,age from student";
		List<Student> students = jdbcTemplate.query(sql, new RowMapper<Student>() {
			@Override
			public Student mapRow(ResultSet resultSet, int i) throws SQLException {
				Student student = new Student();
				student.setId(resultSet.getInt("id"));
				student.setUsername(resultSet.getString("name"));
				student.setPassword(resultSet.getString("password"));
				student.setSex(resultSet.getString("sex"));
				return student;
			}
		});

		System.out.println("查询成功");
		for(Student s : students){
			System.out.println(s);
		}
	}
//原文链接：https://blog.csdn.net/f2764052703/article/details/95043085

}

