package jdbc;


import model.Student;
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
public class StudentJdbc {
    @Bean(name = "stuJdbc")
    public StudentJdbc getStudentJdbc() {
        return new StudentJdbc();
    }

    //老师添加学生
    public static boolean addStudent(Student newStudent) throws ClassNotFoundException {
        String url="jdbc:mysql://127.0.0.1:3306/school?serverTimezone=UTC";
        String drivername = "com.mysql.cj.jdbc.Driver";
        Class.forName(drivername);//可以省略
        boolean isSuccess = true;
        try (Connection connection = DatabasePool.getHikariDataSource().getConnection()) {
            connection.setAutoCommit(false);
            //Preparestatement （增、删、改、查）
            String sqlString = "insert into student(id,name,create_time,update_time) values (?,?,?,?)";
            try(PreparedStatement ps = connection.prepareStatement(sqlString)){
                ps.setLong(1,newStudent.getId());
                ps.setString(2,newStudent.getName());
                ps.setTimestamp(3, newStudent.getCreateTime());
                ps.setTimestamp(4, newStudent.getUpdateTime());
                //成功返回false 失败返回true
                isSuccess = ps.execute();
            }catch (SQLException exception){
                System.out.println("数据写入失败");
                connection.rollback();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        //返回相反的结果
        return !isSuccess;
    }


    //查询所有的学生
    public static List<Student> selectAllStudent() throws ClassNotFoundException {
        String url="jdbc:mysql://127.0.0.1:3306/school?serverTimezone=UTC";

        String drivername = "com.mysql.cj.jdbc.Driver";

        String sqlString = "select * from student ";

        List<Student>list=new ArrayList<>();
        Class.forName(drivername);//可以省略
        try (Connection connection = DatabasePool.getHikariDataSource().getConnection()) {
            connection.setAutoCommit(false);
            //通过连接获取statement
            try (Statement statement = connection.createStatement()) {
                //statement （增、删、改、查）
                try (ResultSet resultSet = statement.executeQuery(sqlString)) {
                    //获取执行结果
                    while (resultSet.next()) {
                        Student student = new Student();
                        student.setId(resultSet.getLong("id"));
                        student.setName(resultSet.getString("name"));
                        student.setCreateTime(resultSet.getTimestamp("create_time"));
                        student.setUpdateTime(resultSet.getTimestamp("update_time"));
                        list.add(student);
                    }
                    connection.commit();
                }

            }catch (SQLException exception){
                System.out.println("数据读取失败");
                connection.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) throws ClassNotFoundException {

    }
}
