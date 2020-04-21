<%@ page import="java.util.List" %>
<%@ page import="model.Homework" %><%--
  Created by IntelliJ IDEA.
  User: xinyu
  Date: 2020/3/8
  Time: 12:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
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
        List<Homework> tlist = (List<Homework>) request.getAttribute("teacherhomeworklist");
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
<br>
<br>
<br>
<br>
<div align="center">
    <h2>发布作业</h2>
    <form action="/addhomework" method="post">
        作业编号:<input type="number" name="id">
        <br><br><br>
        作业标题:<input type="text" name="title">
        <br><br><br>
        作业内容:<input type="text" name="content">
        <br><br><br>
        <input type="submit" value="发布">
    </form>
</div>
<a href="http://localhost:8080/" >
    <button >返回</button>
</a>
</body>
</html>
