<%@ page import="java.util.List" %>
<%@ page import="model.Homework" %>
<%@ page import="dao.HomeworkJdbc" %>
<%@ page import="model.StudentHomework" %>
<%--
  Created by IntelliJ IDEA.
  User: xinyu
  Date: 2020/3/8
  Time: 20:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>课后作业</title>
</head>
<body>
<h1 align="center">
    已发布作业
</h1>
<table align="center" width="950" border="1" bgcolor="black" cellpadding="1" cellspacing="1">
    <tr bgcolor="aqua">
        <td align="center">作业编号</td>
        <td align="center">作业标题</td>
        <td align="center">作业内容</td>
        <td align="center">发布时间</td>
    </tr>
    <%
        List<Homework> tlist = HomeworkJdbc.selectAllTeacherHomework();
        if(null == tlist||tlist.size()<=0){
            out.println("None data.");
        }else{
            for(Homework th:tlist){
    %>
    <tr align="center" bgcolor="white" height="40">
        <td><%=th.getId()%></td>
        <td><%=th.getTitle()%></td>
        <td><%=th.getContent()%></td>
        <td><%=th.getCreateTime()%></td>
    </tr>
    <%}}%>
</table>
<h1 align="center">
    作业提交记录
</h1>
<table align="center" width="950" border="1" bgcolor="black" cellpadding="1" cellspacing="1">
    <tr bgcolor="aqua">
        <td align="center">ID</td>
        <td align="center">学号</td>
        <td align="center">作业编号</td>
        <td align="center">作业标题</td>
        <td align="center">作业内容</td>
        <td align="center">创建时间</td>
    </tr>
    <%
        List<StudentHomework> slist = (List<StudentHomework>)request.getAttribute("studenthomeworklist");
        if(null == slist || slist.size()<=0){
            out.println("None data.");
        }else{
            for(StudentHomework sh: slist){
    %>
    <tr align="center" bgcolor="white" height="30">
        <td><%=sh.getId()%></td>
        <td><%=sh.getStudentId()%></td>
        <td><%=sh.getHomeworkId()%></td>
        <td><%=sh.getHomeworkTitle()%></td>
        <td><%=sh.getHomeworkContent()%></td>
        <td><%=sh.getCreateTime()%></td>
    </tr>
    <%}}%>
</table>
<br/>
<br/>
<a href="http://localhost:8080/" >
    <button >返回</button>
</a>
<h1 align="center">
    查询某位同学作业
</h1>
<form align="center" action="/queryStuHW" method="post">
    学生学号:<input type="number" name="student_id">
    <br><br><br>
    <input type="submit" value="查询">
</form>
<h1 align="center">
    查询某次作业提交情况
</h1>
<form align="center" action="/queryHWSubmissionStatus" method="post">
    作业编号:<input type="number" name="homework_id">
    <br><br><br>
    <input type="submit" value="查询">
</form>
</body>
</html>
