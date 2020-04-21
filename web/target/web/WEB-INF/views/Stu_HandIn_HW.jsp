<%--
  Created by IntelliJ IDEA.
  User: xinyu
  Date: 2020/3/8
  Time: 14:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>提交作业</title>
</head>
<body>
<h1 align="center">
    学生作业提交
</h1>
<form align="center" action="/StuSubmitHW" method="post">
    作业编号:<input type="number" name="homework_id">
    <br><br><br>
    作业标题:<input type="text" name="homework_title">
    <br><br><br>
    学生学号:<input type="number" name="student_id">
    <br><br><br>
    作业内容:<input type="text" name="homework_content">
    <br><br><br>
    <input type="submit" value="提交">
</form>
<a href="http://localhost:8080/" >
    <button >返回</button>
</a>
</body>
</html>
