package org.example.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


import model.Homework;
import model.Student;
import model.StudentHomework;
import service.HomeworkService;
import service.StudentService;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

@ComponentScan("java")
@Controller
public class TeacherController {
    @Autowired
    StudentService studentService;
    @Autowired
    HomeworkService homeworkService;

    @RequestMapping("/addhomework")
    protected void addHomework(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        if(req.getParameter("id").equals("")){
            resp.getWriter().println("作业编号不能为空,5s后返回初始界面");
        }else if(req.getParameter("title").equals("")){
            resp.getWriter().println("作业标题不能为空,5s后返回初始界面");
        }else if(req.getParameter("content").equals("")){
            resp.getWriter().println("作业内容不能为空,5s后返回初始界面");
        }else{
            Homework th = new Homework();
            th.setId(Long.parseLong(req.getParameter("id")));
            th.setTitle(req.getParameter("title"));
            th.setContent(req.getParameter("content"));
            Timestamp dateNow=new Timestamp(System.currentTimeMillis());

            th.setCreateTime(dateNow);
            try {
                resp.getWriter().println(homeworkService.addHomework(th));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        resp.setHeader("refresh","5;URL=index.jsp");
    }

    @RequestMapping("/addStudent")
    protected void addStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //解决中文乱码问题
        resp.setContentType("text/html;charset=UTF-8");
        if(req.getParameter("id").equals("")){
            resp.getWriter().println("学生学号不能为空,5s后返回初始界面");
        }else if(req.getParameter("name").equals("")){
            resp.getWriter().println("学生姓名不能为空,5s后返回初始界面");
        }else{
            Student newStudent = new Student();
            newStudent.setId(Long.parseLong(req.getParameter("id")));
            newStudent.setName(req.getParameter("name"));
            Timestamp dateNow=new Timestamp(System.currentTimeMillis());

            newStudent.setCreateTime(dateNow);
            String response = "添加成功";
            try {
                response = studentService.addStudent(newStudent);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                resp.getWriter().println(response);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        resp.setHeader("refresh","5;URL=index.jsp");
    }

    @RequestMapping("/searchAllTh")
    protected String doGet1(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Homework> homeworkList = null;
        try {
            homeworkList = homeworkService.selectAllTeacherHomework();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        req.setAttribute("teacherhomeworklist", homeworkList);
        return "Tea_Add_HW";
    }

    @RequestMapping("/queryHWSubmissionStatus")
    protected void doPost3(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<StudentHomework> studentHomeworkList = null;
        try {
            studentHomeworkList = homeworkService.selectAHomework(Long.parseLong(req.getParameter("homework_id")));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        req.setAttribute("studenthomeworklist",studentHomeworkList);
        req.getRequestDispatcher("AStudentHW.jsp").forward(req,resp);
    }

    @RequestMapping("/queryStuHW")
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<StudentHomework> studentHomeworkList = null;
        try {
            studentHomeworkList = homeworkService.selectAStudentHomework(Long.parseLong(req.getParameter("student_id")));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        req.setAttribute("studenthomeworklist",studentHomeworkList);
        req.getRequestDispatcher("AStudentHW.jsp").forward(req,resp);
    }

    @RequestMapping("/searchAllSh")
    protected String doGet2(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<StudentHomework> studentHomeworkList = null;
        try {
            studentHomeworkList = homeworkService.selectAllStudentHomework();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        req.setAttribute("studenthomeworklist",studentHomeworkList);
        return "Tea_Query_HW";
    }

    @RequestMapping("/searchStudent")
    protected String doGet3(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Student> list = null;
        try {
            list = studentService.selectAllStudent();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        req.setAttribute("studentlist",list);
        return "Tea_Add_Student";
    }

}
