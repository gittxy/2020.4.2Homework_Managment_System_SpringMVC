package service;

import dao.StudentDao;
import model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Service;


import java.util.List;

@EnableAspectJAutoProxy
@ComponentScan("java")
@Configuration
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentDao studentDao;

    public String addStudent(Student newStudent) throws ClassNotFoundException {
        List<Student> studentList;
        String response ="";

        studentList = studentDao.selectAllStudent();

        for(Student student:studentList){
            //使用equals方法
            if( newStudent.getId().equals(student.getId())){
                return "学号重复,5s后返回初始界面";
            }
        }
        if(studentDao.addStudent(newStudent.getId(),newStudent.getName(),newStudent.getCreateTime(),newStudent.getUpdateTime())>0){
            response ="添加成功,5s后跳转";
        }else {
            response = "添加失败，请检查后再添加,5s后返回初始界面";
        }

        return response;
    }

    //查询所有的学生
    public List<Student> selectAllStudent() throws ClassNotFoundException {
        return studentDao.selectAllStudent();
    }

}
