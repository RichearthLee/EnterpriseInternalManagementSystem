<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/disk/adddisk" method="post">

diskname:<input type="text" name="diskname"/>

<input type="submit" value="新建网盘"/>
</form>


<form action="<%=request.getContextPath()%>/disk/testdisk" method="post">
diskid:<input type="text" name="diskid"/>

<input type="submit" value="进入网盘"/>
</form>

<form action="<%=request.getContextPath()%>/file/list_myfile" method="post">

foderid:<input type="text" name="foderid"/>
pwd:<input type="text" name="pwd"/>

<input type="submit" value="测试list"/>

<form action="<%=request.getContextPath()%>/file/list_myfile" method="post">

foderid:<input type="text" name="foderid"/>
pwd:<input type="text" name="pwd"/>

<input type="submit" value="测试list"/>

</form>

<form action="<%=request.getContextPath()%>/test/test1" method="post">

userId:<input type="text" name="userId"/>


<input type="submit" value="测试shuliang"/>

</form>
</body>
</html>