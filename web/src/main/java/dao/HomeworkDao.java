package dao;

import model.Homework;
import model.StudentHomework;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public interface HomeworkDao extends JpaRepository<StudentHomework,Long>{
    /**
     * 学生提交作业
     * @param
     * @param id
     * @return 返回结果
     * @throws SQLException
     */
    @Modifying
    @Transactional
    @Query(value = "insert into student_homework(id,student_id,homework_id,homework_title,homework_content,create_time,update_time)values(:id,:sid,:hid,:title,:content,:ctime,:uptime)",nativeQuery=true )
    public int handHomework (@Param("id")long id, @Param("sid")long studentId, @Param("hid")long homeworkId, @Param("title")String homeworkTitle, @Param("content")String homeworkContent, @Param("ctime")Timestamp createTime, @Param("uptime")Timestamp updateTime);

    /**
     * 查询所有学生提交的作业记录
     * @return 返回作业提交记录
     * @throws SQLException
     */
    @Query(value = "select * from student_homework",nativeQuery=true)
    public List<StudentHomework> selectAllStudentHomework();

    /**
     * 教师发布作业
     * @param
     * @return
     * @throws SQLException
     */
    @Modifying
    @Transactional
    @Query(value = "insert into homework(id,title,content,create_time) values (:id,:title,:content,:ctime)",nativeQuery = true)
    public int addHomework(@Param("id") Long id, @Param("title") String homeworkTitle, @Param("content") String homeworkContent, @Param("ctime") Timestamp createTime);

    /**
     * 查询所有教师作业的列表
     * @return
     * @throws SQLException
     */
    @Query(value = "select * from homework",nativeQuery = true)
    public List<Homework> selectAllTeacherHomework();

    /**
     * 查询某位同学提交的作业
     * @return
     * @throws SQLException
     */
    @Query(value = "select * from student_homework where student_id=:sid",nativeQuery = true)
    public List<StudentHomework> selectAStudentHomework(@Param("sid") Long studentId);

    /**
     * 查询某次作业的提交情况
     * @return
     * @throws SQLException
     */
    @Query(value = "select * from student_homework where homework_id=:hid",nativeQuery = true)
    public List<StudentHomework> selectAHomework(@Param("hid") Long homeworkId);

}
