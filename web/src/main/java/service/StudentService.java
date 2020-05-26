package service;
import model.Student;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Service;

import java.util.List;

@ComponentScan("java")
@Service
@Configuration
public interface StudentService {
    //添加学生
    public String addStudent(Student newStudent) throws ClassNotFoundException;

    //查询所有的学生
    public List<Student> selectAllStudent() throws ClassNotFoundException;
}
