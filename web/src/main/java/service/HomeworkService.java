package service;

import jdbc.HomeworkJdbc;
import jdbc.StudentJdbc;
import model.Homework;
import model.StudentHomework;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Service;

import java.util.List;

@ComponentScan("java")
@Service
@Configuration
public class HomeworkService {
    @Bean(name = "hwService")
    public HomeworkService getHomeworkService() {
        return new HomeworkService();
    }

    ApplicationContext applicationContext = new AnnotationConfigApplicationContext(HomeworkJdbc.class);
    HomeworkJdbc hwJdbc = (HomeworkJdbc) applicationContext.getBean("hwJdbc");

    //老师布置作业
    public boolean addHomework(Homework th) throws ClassNotFoundException {
        return hwJdbc.addHomework(th);
    }

    //查询所有学生提交的作业
    public List<StudentHomework> selectAllStudentHomework() throws ClassNotFoundException {
        return  hwJdbc.selectAllStudentHomework();
    }

    //查询所有教师布置的作业
    public List<Homework> selectAllTeacherHomework() throws ClassNotFoundException {
        return hwJdbc.selectAllTeacherHomework();
    }

    //查询某位学生提交的作业
    public List<StudentHomework> selectAStudentHomework(Long student_id) throws ClassNotFoundException {
        return hwJdbc.selectAStudentHomework(student_id);
    }

    //查询某次作业的提交情况
    public List<StudentHomework> selectAHomework(Long homework_id) throws ClassNotFoundException {
        return hwJdbc.selectAHomework(homework_id);
    }

    //学生提交作业
    public String handHomework(StudentHomework sh) throws ClassNotFoundException {
        return hwJdbc.handHomework(sh);
    }
}
