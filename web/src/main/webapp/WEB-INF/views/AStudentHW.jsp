<%@ page import="java.util.List" %>
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
    作业提交记录
</h1>
<table align="center" width="950" border="1" bgcolor="black" cellpadding="1" cellspacing="1">
    <tr bgcolor="aqua">
        <td align="center">ID</td>
        <td align="center">学号</td>
        <td align="center">作业编号</td>
        <td align="center">作业标题</td>
        <td align="center">作业内容</td>
        <td align="center">提交时间</td>
    </tr>
    <%
        List<StudentHomework> slist = (List<StudentHomework>)request.getAttribute("studenthomeworklist");
        if(null == slist || slist.size()<=0){
            out.println("None data.Maybe wrong input or data is null");
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
<a href="http://localhost:8080/searchAllSh" >
    <button >返回</button>
</a>
</body>
</html>
