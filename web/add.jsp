<%--
  Created by IntelliJ IDEA.
  User: snowkkk
  Date: 2020/8/6
  Time: 17:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加学生</title>
</head>
<body>
    <form action="/student" method="post">
        姓名：<input type="text", name="name"/><br/>
        成绩：<input type="text", name="score"/><br/>
        <input type="hidden" name="method" value="add"/>
        <input type="submit" value="提交"/>
    </form>
</body>
</html>
