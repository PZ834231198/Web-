<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<meta charset="UTF-8">
    <title>主页面</title>
	<link href="css/main.css" rel="stylesheet" type="text/css"/>
	<script src="js/main.js"></script>
</head>

<body>
	<div>
		<img src="images/主界面/角标.png" alt="Study Website" width="170px" class="div1">
	</div>
	<p class="div2"><font size="8px" color="greenyellow">超越梦想</font></p>
	
	<label class="div3">
		<font>欢迎您，</font>
		<font color="red" style="cursor: pointer;">${chrName}</font>
	</label>

	<form method="GET" name="out">
		<label onClick="exit()" class="div4">
			<font color="purple" size="4%">[安全退出]</font>
		</label>
	</form>

	<form method="GET" name="CheckPage" class="form">
		<label onClick="" class="Labels" >
			<font>&nbsp;首页&nbsp;</font>
		</label>
		<label onClick="goDownload()" class="Labels" >
			<font>|&nbsp;资源下载&nbsp;</font>
		</label>
		<label onClick="goUserManage()" class="Labels" >
			<font>|&nbsp;用户管理&nbsp;</font>
		</label>
		<label onClick="goResManage()" class="Labels" >
			<font>|&nbsp;资源管理&nbsp;</font>
		</label>
		<label onClick="goPersonCenter()" class="Labels" >
			<font>|&nbsp;个人中心&nbsp;</font>
		</label>
	</form>
	
	<div class="line"/>

</body>
</html>
