package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.Student;
import com.example.demo.entity.result;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;

import org.apache.poi.hssf.usermodel.*;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;




@Controller
public class excel {

    @Resource    // 自动注入，spring boot会帮我们实例化一个对象
    private JdbcTemplate jdbcTemplate;
// 一个通过JDBC连接数据库的工具类，可以通过这个工具类对数据库进行增删改查

    /**
     * Excel表格导出接口
     * http://localhost:8080/ExcelDownload
     * @param response response对象
     * @throws IOException 抛IO异常
     */
    @RequestMapping("/exceldl")
    public void excelDownload(HttpServletResponse response) throws IOException {

        String sql = "select * from student";
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
        result one = new result();
        one.setCode(0);
        one.setCount(students.toArray().length);
        ArrayList a = (ArrayList) students;
        System.out.println("stu-a:"+a);
        Object[] arr = a.toArray();
        System.out.println("stu-arr:"+arr);
        for(int i=0;i<arr.length;i++){
            System.out.println("stu-one:"+i+"--"+arr[i]);
        }
//        one.setData((ArrayList) students);
//        one.setMsg("查询成功！");
//        String userJson = JSON.toJSONString(one);
//        JSONObject user = JSONObject.parseObject(userJson);
//        System.out.println("查询结果-back："+user);


        //表头数据
        String[] header = {"ID", "姓名", "性别", "年龄", "地址", "分数"};


        //数据内容

        String[] student1 = {"1", "小fa", "男", "26", "成都金牛区", "91"};
        String[] student2 = {"2", "小强", "男", "26", "成都金牛区", "91"};
        String[] student3 = {"3", "小明", "男", "28", "成都武侯区", "90"};

        //声明一个工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();

        //生成一个表格，设置表格名称为"学生表"
        HSSFSheet sheet = workbook.createSheet("学生表");

        //设置表格列宽度为10个字节
        sheet.setDefaultColumnWidth(10);

        //创建第一行表头
        HSSFRow headrow = sheet.createRow(0);

        //遍历添加表头(下面模拟遍历学生，也是同样的操作过程)
        for (int i = 0; i < header.length; i++) {
            //创建一个单元格
            HSSFCell cell = headrow.createCell(i);

            //创建一个内容对象
            HSSFRichTextString text = new HSSFRichTextString(header[i]);

            //将内容对象的文字内容写入到单元格中
            cell.setCellValue(text);
        }

        //模拟遍历结果集，把内容加入表格
        //模拟遍历第一个学生
        HSSFRow row1 = sheet.createRow(1);
        for (int i = 0; i < student1.length; i++) {
            HSSFCell cell = row1.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(student1[i]);
            cell.setCellValue(text);
        }

        //模拟遍历第二个学生
//        HSSFRow row2 = sheet.createRow(2);
//        for (int i = 0; i < student2.length; i++) {
//            HSSFCell cell = row2.createCell(i);
//            HSSFRichTextString text = new HSSFRichTextString(student2[i]);
//            cell.setCellValue(text);
//        }
//
//        //模拟遍历第三个学生
//        HSSFRow row3 = sheet.createRow(3);
//        for (int i = 0; i < student3.length; i++) {
//            HSSFCell cell = row3.createCell(i);
//            HSSFRichTextString text = new HSSFRichTextString(student3[i]);
//            cell.setCellValue(text);
//        }

        //准备将Excel的输出流通过response输出到页面下载
        //八进制输出流
//        response.setContentType("application/octet-stream");
//
//        //这后面可以设置导出Excel的名称，此例中名为student.xls
//        response.setHeader("Content-disposition", "attachment;filename=student.xls");
//
//        //刷新缓冲
//        response.flushBuffer();
//
//        //workbook将Excel写入到response的输出流中，供页面下载
//        workbook.write(response.getOutputStream());
    }

}
//原文链接：https://blog.csdn.net/weixin_39448458/article/details/83013677
