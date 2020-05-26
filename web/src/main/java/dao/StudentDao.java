package dao;

import model.Student;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@Configuration
public interface StudentDao extends JpaRepository<Student,Long>{
    /**
     * 查询学生列表
     * @return
     * @throws SQLException
     */
    @Query(value = "select * from student", nativeQuery=true)
    public List<Student> selectAllStudent();

    /**
     * 添加学生
     * @param
     * @return
     * @throws SQLException
     */
    @Query(value = "insert into student (id,name,create_time,update_time) values(:id,:name,:ctime,:uptime)",nativeQuery=true)
    public int addStudent(@Param("id")long id, @Param("name")String studentName, @Param("ctime")Timestamp createTime,@Param("uptime")Timestamp updateTime);

}
