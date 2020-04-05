package org.example.controller;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


import jdbc.HomeworkJdbc;
import model.Homework;
import jdbc.StudentJdbc;
import model.Student;
import model.StudentHomework;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

@ComponentScan("java.jdbc")
@Controller
public class TeacherController {
    ApplicationContext applicationContext = new AnnotationConfigApplicationContext(HomeworkJdbc.class);
    HomeworkJdbc hwJdbc = (HomeworkJdbc) applicationContext.getBean("hwJdbc");

    ApplicationContext applicationContext2 = new AnnotationConfigApplicationContext(StudentJdbc.class);
    StudentJdbc stuJdbc = (StudentJdbc) applicationContext2.getBean("stuJdbc");
    

    @RequestMapping("/addhomework")
    protected void doPost1(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
            List<Homework> thList = null;
            try {
                thList = hwJdbc.selectAllTeacherHomework();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            boolean isExist = false;
            for(Homework h:thList){
                if(th.getId()==h.getId()){
                    isExist = true;
                    break;
                }
            }

            if(isExist){
                resp.getWriter().println("id重复,5s后返回初始界面");
            }else {
                try {
                    if(hwJdbc.addHomework(th)){
                        resp.getWriter().println("添加成功,5s后返回初始界面");
                    }else {
                        resp.getWriter().println("添加失败，请检查后再添加,5s后返回初始界面");
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

            }
        }

        resp.setHeader("refresh","5;URL=index.jsp");
    }

    @RequestMapping("/addStudent")
    protected void doPost2(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
            List<Student> studentList = null;
            try {
                studentList = stuJdbc.selectAllStudent();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            boolean isExist = false;
            for(Student s:studentList){
                if( newStudent.getId().equals(s.getId())){
                    isExist = true;
                    break;
                }
            }

            if(isExist){
                resp.getWriter().println("学号重复,5s后返回初始界面");
            }else {

                try {
                    if(stuJdbc.addStudent(newStudent)){
                        resp.getWriter().println("添加成功,5s后跳转");
                    }else {
                        resp.getWriter().println("添加失败，请检查后再添加,5s后返回初始界面");
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }

        resp.setHeader("refresh","5;URL=index.jsp");
    }

    @RequestMapping("/searchAllTh")
    protected String doGet1(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Homework> homeworkList = null;
        try {
            homeworkList = hwJdbc.selectAllTeacherHomework();
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
            studentHomeworkList = hwJdbc.selectAHomework(Long.parseLong(req.getParameter("homework_id")));
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
            studentHomeworkList = hwJdbc.selectAStudentHomework(Long.parseLong(req.getParameter("student_id")));
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
            studentHomeworkList = hwJdbc.selectAllStudentHomework();
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
            list = stuJdbc.selectAllStudent();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        req.setAttribute("studentlist",list);
        return "Tea_Add_Student";
    }

}
