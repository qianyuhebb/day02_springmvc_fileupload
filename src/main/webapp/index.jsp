<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/1/25
  Time: 12:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
   <h3>传统方式文件上传</h3>

<form action="/user/fileUpload1" method="post" enctype="multipart/form-data">
     选择文件： <input type="file" name="upload"/><br>
      <input type="submit"  value="上传"/>

</form>

   <br>
   <h3>spring mvc 文件上传</h3>

<form action="/user/fileUpload2" method="post" enctype="multipart/form-data">
     选择文件： <input type="file" name="upload"/><br>
      <input type="submit"  value="上传"/>

</form> <br>
   <h3>跨服务器 文件上传</h3>

<form action="/user/fileUpload3" method="post" enctype="multipart/form-data">
     选择文件： <input type="file" name="upload"/><br>
      <input type="submit"  value="上传"/>

</form>
</body>
</html>
