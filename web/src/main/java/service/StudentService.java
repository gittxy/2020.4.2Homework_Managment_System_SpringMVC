package service;

import jdbc.StudentJdbc;
import model.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Service;

import java.util.List;

@ComponentScan("java")
@Service
@Configuration
public class StudentService {
    @Bean(name = "stuService")
    public StudentService getStudentService() {
        return new StudentService();
    }

    ApplicationContext applicationContext = new AnnotationConfigApplicationContext(StudentJdbc.class);
    StudentJdbc stuJdbc = (StudentJdbc) applicationContext.getBean("stuJdbc");
    //老师添加学生
    public boolean addStudent(Student newStudent) throws ClassNotFoundException {
        return stuJdbc.addStudent(newStudent);
    }

    //查询所有的学生
    public List<Student> selectAllStudent() throws ClassNotFoundException {
        return stuJdbc.selectAllStudent();
    }

}
