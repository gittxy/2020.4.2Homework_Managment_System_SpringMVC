<%@ page import="java.util.List" %>
<%@ page import="model.Student" %><%--
  Created by IntelliJ IDEA.
  User: xinyu
  Date: 2020/3/8
  Time: 22:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>作业管理系统</title>
</head>
<body>
<h1 align="center">
    学生列表
</h1>
<table align="center" width="950" border="1" bgcolor="black" cellpadding="1" cellspacing="1">
    <tr bgcolor="aqua">
        <td align="center">学号</td>
        <td align="center">姓名</td>
    </tr>
    <%
        List<Student> list = (List<Student>) request.getAttribute("studentlist");
        if(null == list||list.size()<=0){
            out.println("None data.");
        }else{
            for(Student s:list){
    %>
    <tr align="center" bgcolor="white" height="40">
        <td><%=s.getId()%></td>
        <td><%=s.getName()%></td>
    </tr>
    <%}}%>
</table>
<br/>
<br/>
<br/>
<br/>
<h1 align="center">
    添加学生
</h1>
<form align="center" action="/addStudent" method="post">
    学号:<input type="number" name="id">
    <br><br><br>
    姓名:<input type="text" name="name">
    <br><br>
    <input type="submit" value="添加">
</form>
<a href="http://localhost:8080/" >
    <button >返回</button>
</a>
</body>
</html>
