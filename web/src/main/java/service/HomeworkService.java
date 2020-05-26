package service;

import model.Homework;
import model.StudentHomework;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Service;

import java.util.List;

@ComponentScan("java")
@Service
@Configuration
public interface HomeworkService {
    //老师布置作业
    public String addHomework(Homework th) throws ClassNotFoundException;

    //查询所有学生提交的作业
    public List<StudentHomework> selectAllStudentHomework() throws ClassNotFoundException;

    //查询所有教师布置的作业
    public List<Homework> selectAllTeacherHomework() throws ClassNotFoundException;

    //查询某位学生提交的作业
    public List<StudentHomework> selectAStudentHomework(Long student_id) throws ClassNotFoundException;

    //查询某次作业的提交情况
    public List<StudentHomework> selectAHomework(Long homework_id) throws ClassNotFoundException;

    //学生提交作业
    public String handHomework(StudentHomework sh) throws ClassNotFoundException;
}
