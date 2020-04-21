package org.example.controller;
import model.Homework;
import model.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;

import jdbc.HomeworkJdbc;
import model.StudentHomework;
import service.HomeworkService;
import service.StudentService;

@ComponentScan("java")
@Controller
public class StudentController {
    ApplicationContext applicationContext = new AnnotationConfigApplicationContext(StudentService.class);
    StudentService stuService = (StudentService) applicationContext.getBean("stuService");

    ApplicationContext applicationContext2 = new AnnotationConfigApplicationContext(HomeworkService.class);
    HomeworkService hwService = (HomeworkService) applicationContext2.getBean("hwService");

    ApplicationContext applicationContext3 = new AnnotationConfigApplicationContext(StudentHomework.class);

    @RequestMapping("/StuSubmitHW")
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //解决中文乱码问题
        resp.setContentType("text/html;charset=UTF-8");
        if(req.getParameter("homework_id").equals("")){
            resp.getWriter().println("作业编号不能为空,5s后返回初始界面");
        }else if(req.getParameter("homework_title").equals("")){
            resp.getWriter().println("作业标题不能为空,5s后返回初始界面");
        }else if(req.getParameter("student_id").equals("")){
            resp.getWriter().println("学生学号不能为空,5s后返回初始界面");
        }else if(req.getParameter("homework_content").equals("")){
            resp.getWriter().println("作业内容不能为空,5s后返回初始界面");
        }else{
            StudentHomework sh = (StudentHomework) applicationContext3.getBean("studentHw");
            sh.setStudentId(Long.parseLong(req.getParameter("student_id")));
            sh.setHomeworkId(Long.parseLong(req.getParameter("homework_id")));
            sh.setHomeworkTitle(req.getParameter("homework_title"));
            sh.setHomeworkContent(req.getParameter("homework_content"));
            Timestamp dateNow=new Timestamp(System.currentTimeMillis());

            sh.setCreateTime(dateNow);


            try {
                resp.getWriter().println(hwService.handHomework(sh)+",5s后返回初始界面");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        resp.setHeader("refresh","5;URL=index.jsp");
    }

    @RequestMapping("/handInHomework")
    protected String doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        return "Stu_HandIn_HW";
    }
}
