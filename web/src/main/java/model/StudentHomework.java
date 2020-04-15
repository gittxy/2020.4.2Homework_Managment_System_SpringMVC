package model;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.sql.Timestamp;

/**
 * @Author:txy
 * @Date:created in 8:50 2020/3/8
 */
@Configuration
public class StudentHomework {
    @Bean(name = "studentHw")
    public StudentHomework getStudentHomework() {
        return new StudentHomework();
    }

    private Long id;
    private Long studentId;
    private Long homeworkId;
    private String homeworkTitle;
    private String homeworkContent;
    private Timestamp createTime;
    private Timestamp updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(Long homeworkId) {
        this.homeworkId = homeworkId;
    }

    public String getHomeworkTitle() {
        return homeworkTitle;
    }

    public void setHomeworkTitle(String homeworkTitle) {
        this.homeworkTitle = homeworkTitle;
    }

    public String getHomeworkContent() {
        return homeworkContent;
    }

    public void setHomeworkContent(String homeworkContent) {
        this.homeworkContent = homeworkContent;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }
}
