package dao;

import model.Homework;
import model.Student;
import model.StudentHomework;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * @Author:txy
 * @Date:created in 8:50 2020/3/8
 */
@Configuration
public class HomeworkDao {
    @Bean(name = "hwJdbc")
    public HomeworkDao getHomeworkJdbc() {
        return new HomeworkDao();
    }

    //老师布置作业
    public static boolean addHomework(Homework th) throws ClassNotFoundException {
        String url="dao:mysql://127.0.0.1:3306/school?serverTimezone=UTC";
        String drivername = "com.mysql.cj.dao.Driver";
        Class.forName(drivername);//可以省略
        boolean isSuccess = true;
        try (Connection connection = DatabasePool.getHikariDataSource().getConnection()) {
            //Preparestatement （增、删、改、查）
            String sqlString = "insert into homework(id,title,content,create_time) values (?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sqlString);
            ps.setLong(1,th.getId());
            ps.setString(2,th.getTitle());
            ps.setString(3,th.getContent());
            ps.setTimestamp(4, th.getCreateTime());

            //成功返回false 失败返回true
            isSuccess = ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //返回相反的结果
        return !isSuccess;
    }


    //查询所有学生提交的作业
    public static List<StudentHomework> selectAllStudentHomework() throws ClassNotFoundException {
        String url="dao:mysql://127.0.0.1:3306/school?serverTimezone=UTC";

        String drivername = "com.mysql.cj.dao.Driver";

        String sqlString = "select * from student_homework ";

        List<StudentHomework>list=new ArrayList<>();
        Class.forName(drivername);//可以省略
        try (Connection connection = DatabasePool.getHikariDataSource().getConnection()) {
            //通过连接获取statement
            try (Statement statement = connection.createStatement()) {
                //statement （增、删、改、查）
                try (ResultSet resultSet = statement.executeQuery(sqlString)) {
                    //获取执行结果
                    while (resultSet.next()) {
                        StudentHomework sh = new StudentHomework();
                        sh.setId(resultSet.getLong("id"));
                        sh.setStudentId(resultSet.getLong("student_id"));
                        sh.setHomeworkId(resultSet.getLong("homework_id"));
                        sh.setHomeworkTitle(resultSet.getString("homework_title"));
                        sh.setHomeworkContent(resultSet.getString("homework_content"));
                        sh.setCreateTime(resultSet.getTimestamp("create_time"));
                        sh.setUpdateTime(resultSet.getTimestamp("update_time"));
                        list.add(sh);
                    }
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    //查询所有教师布置的作业
    public static List<Homework> selectAllTeacherHomework() throws ClassNotFoundException {
        String url="dao:mysql://127.0.0.1:3306/school?serverTimezone=UTC";

        String drivername = "com.mysql.cj.dao.Driver";

        String sqlString = "select * from homework ";

        List<Homework>list=new ArrayList<>();
        Class.forName(drivername);//可以省略
        try (Connection connection = DatabasePool.getHikariDataSource().getConnection()) {
            //通过连接获取statement
            try (Statement statement = connection.createStatement()) {
                //statement （增、删、改、查）
                try (ResultSet resultSet = statement.executeQuery(sqlString)) {
                    //获取执行结果
                    while (resultSet.next()) {
                        Homework th = new Homework();
                        th.setId(resultSet.getLong("id"));
                        th.setTitle(resultSet.getString("title"));
                        th.setContent(resultSet.getString("content"));
                        th.setCreateTime(resultSet.getTimestamp("create_time"));
                        th.setUpdateTime(resultSet.getTimestamp("update_time"));
                        list.add(th);
                    }
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    //查询某位学生提交的作业
    public static List<StudentHomework> selectAStudentHomework(Long student_id) throws ClassNotFoundException {
        String url="dao:mysql://127.0.0.1:3306/school?serverTimezone=UTC";

        String drivername = "com.mysql.cj.dao.Driver";

        String sqlString = "select * from student_homework where student_id="+student_id;

        List<StudentHomework>list=new ArrayList<>();
        Class.forName(drivername);//可以省略
        try (Connection connection = DatabasePool.getHikariDataSource().getConnection()) {
            //通过连接获取statement
            try (Statement statement = connection.createStatement()) {
                //statement （增、删、改、查）
                try (ResultSet resultSet = statement.executeQuery(sqlString)) {
                    //获取执行结果
                    while (resultSet.next()) {
                        StudentHomework sh = new StudentHomework();
                        sh.setId(resultSet.getLong("id"));
                        sh.setStudentId(resultSet.getLong("student_id"));
                        sh.setHomeworkId(resultSet.getLong("homework_id"));
                        sh.setHomeworkTitle(resultSet.getString("homework_title"));
                        sh.setHomeworkContent(resultSet.getString("homework_content"));
                        sh.setCreateTime(resultSet.getTimestamp("create_time"));
                        sh.setUpdateTime(resultSet.getTimestamp("update_time"));
                        list.add(sh);
                    }
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    //查询某次作业的提交情况
    public static List<StudentHomework> selectAHomework(Long homework_id) throws ClassNotFoundException {
        String url="dao:mysql://127.0.0.1:3306/school?serverTimezone=UTC";

        String drivername = "com.mysql.cj.dao.Driver";

        String sqlString = "select * from student_homework where homework_id="+homework_id;

        List<StudentHomework>list=new ArrayList<>();
        Class.forName(drivername);//可以省略
        try (Connection connection = DatabasePool.getHikariDataSource().getConnection()) {
            //通过连接获取statement
            try (Statement statement = connection.createStatement()) {
                //statement （增、删、改、查）
                try (ResultSet resultSet = statement.executeQuery(sqlString)) {
                    //获取执行结果
                    while (resultSet.next()) {
                        StudentHomework sh = new StudentHomework();
                        sh.setId(resultSet.getLong("id"));
                        sh.setStudentId(resultSet.getLong("student_id"));
                        sh.setHomeworkId(resultSet.getLong("homework_id"));
                        sh.setHomeworkTitle(resultSet.getString("homework_title"));
                        sh.setHomeworkContent(resultSet.getString("homework_content"));
                        sh.setCreateTime(resultSet.getTimestamp("create_time"));
                        sh.setUpdateTime(resultSet.getTimestamp("update_time"));
                        list.add(sh);
                    }
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

   //学生提交作业
    public static String handHomework(StudentHomework sh) throws ClassNotFoundException {
        String url="dao:mysql://127.0.0.1:3306/school?serverTimezone=UTC";
        String drivername = "com.mysql.cj.dao.Driver";
        Class.forName(drivername);//可以省略
        String resp = "提交成功";
        List<StudentHomework> shlist = HomeworkDao.selectAllStudentHomework();
        List<Student> slist = StudentDao.selectAllStudent();
        List<Homework> thlist = HomeworkDao.selectAllTeacherHomework();
        boolean studentExist = false;
        boolean homeworkExist = false;
        //检查学生id是否存在
        for(Student s:slist){
            if(s.getId().equals(sh.getStudentId())){
                studentExist=true;
                break;
            }
        }
        if(!studentExist){
            return  "输入的学生id不存在，请检查后再输入";
        }
        //检查作业是否存在
        for(Homework th:thlist){
            if(th.getId()==sh.getHomeworkId()){
                homeworkExist=true;
                sh.setHomeworkTitle(th.getTitle());
                break;
            }
        }
        if(!homeworkExist){
            return  "输入的作业不存在，请检查后再输入";
        }
        try (Connection connection = DatabasePool.getHikariDataSource().getConnection()) {
            //通过连接获取statement
            String sqlString = "insert into student_homework(id,student_id,homework_id,homework_title,homework_content,create_time,update_time)values(?,?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sqlString);
            ps.setLong(1,shlist.size()+1);
            ps.setLong(2,sh.getStudentId());
            ps.setLong(3,sh.getHomeworkId());
            ps.setString(4,sh.getHomeworkTitle());
            ps.setString(5,sh.getHomeworkContent());
            ps.setTimestamp(6, sh.getCreateTime());
            ps.setTimestamp(7, sh.getUpdateTime());
            //成功返回false 失败返回true
            if(ps.execute()){
                return "提交不成功，请检查后再提交";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //返回相反的结果
        return resp;
    }


    public static void main(String[] args) throws ClassNotFoundException {

    }
}
