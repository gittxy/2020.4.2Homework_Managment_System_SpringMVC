package model;

import jdbc.HomeworkJdbc;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.sql.Timestamp;

/**
 * @Author:txy
 * @Date:created in 8:50 2020/3/8
 */
@Configuration
public class Homework {
    @Bean(name = "hw")
    public Homework getHomework() {
        return new Homework();
    }

    private Long id;
    private String title;
    private String content;
    private Timestamp createTime;
    private Timestamp updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
