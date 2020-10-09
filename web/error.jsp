<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta http-equiv="refresh" content="7;url=login.jsp"/>
        <title>错误页面</title>
		<link href = "css/error.css" rel = "stylesheet" type="text/css">
		<script src="js/error.js"></script>
	</head>
	<body onload="showNum()">
		<form>
			<div class="div1">
				<img src="images/错误界面/错误提示.png" width="300px" height="300px"/>
			</div>
			
			<p class="div2">
				<font color="red"><strong>${errorInfo} </strong></font>
				<br><br>
				<font color="red"><strong id="time"/></font>
				秒后自动跳转
				<br><br>
			    不能跳转，请
			<a href="login.jsp"><font color="red">点击这儿</font></a>
			</p>
		</form>
	</body>
</html>
