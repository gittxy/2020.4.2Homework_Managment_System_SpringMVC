package service;

import dao.HomeworkDao;
import dao.StudentDao;
import model.Homework;
import model.Student;
import model.StudentHomework;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Service;

import java.util.List;

@ComponentScan("java")
@Service
@Configuration
public class HomeworkServiceImpl implements HomeworkService{
    @Autowired
    HomeworkDao homeworkDao;
    @Autowired
    StudentDao studentDao;

    //老师布置作业
    public String addHomework(Homework th) throws ClassNotFoundException {
        String response = "";
        List<Homework> thList = null;
        thList = homeworkDao.selectAllTeacherHomework();
        boolean isExist = false;
        for(Homework h:thList){
            if(th.getId().equals(h.getId())){
                isExist = true;
                break;
            }
        }

        if(isExist){
            return "id重复,5s后返回初始界面";
        }else {
            if(homeworkDao.addHomework(th.getId(),th.getTitle(),th.getContent(),th.getCreateTime())>=0){
                response ="添加成功,5s后返回初始界面";
            }else {
                response = "添加失败，请检查后再添加,5s后返回初始界面";
            }

        }

        return response;
    }

    //查询所有学生提交的作业
    public List<StudentHomework> selectAllStudentHomework() throws ClassNotFoundException {
        return homeworkDao.selectAllStudentHomework();
    }

    //查询所有教师布置的作业
    public List<Homework> selectAllTeacherHomework() throws ClassNotFoundException {
        return homeworkDao.selectAllTeacherHomework();
    }

    //查询某位学生提交的作业
    public List<StudentHomework> selectAStudentHomework(Long student_id) throws ClassNotFoundException {
        return homeworkDao.selectAStudentHomework(student_id);
    }

    //查询某次作业的提交情况
    public List<StudentHomework> selectAHomework(Long homework_id) throws ClassNotFoundException {
        return homeworkDao.selectAHomework(homework_id);
    }

    //学生提交作业
    public String handHomework(StudentHomework sh) throws ClassNotFoundException {
        String respone = "提交成功";
        List<Student> slist = null;
        List<Homework> thlist = null;
        slist = studentDao.selectAllStudent();
        thlist = homeworkDao.selectAllTeacherHomework();
        boolean studentExist = false;
        boolean homeworkExist = false;

        //检查学生id
        for(Student s:slist){
            if(s.getId().equals(sh.getStudentId())){
                studentExist=true;
                break;
            }
        }
        if(!studentExist){
            return  "当前输入的学生id不存在";
        }
        //检查作业是否存在
        for(Homework th:thlist){
            if(th.getId()==sh.getHomeworkId()){
                homeworkExist=true;
                break;
            }
        }
        if(!homeworkExist){
            return  "未发现该作业";
        }


        if(homeworkDao.handHomework(sh.getId(),sh.getStudentId(),sh.getHomeworkId(),sh.getHomeworkTitle(),sh.getHomeworkContent(),sh.getCreateTime(),sh.getUpdateTime())>0){
            return respone;
        }else {
            return "提交失败";
        }
    }
}
