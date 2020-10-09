<!--<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>-->
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>下载页面</title>
    <link href="css/download.css" rel = "stylesheet" type="text/css">
    <script src="js/download.js"></script>
	
</head>
<body>
	<div class="div1">
		<p><font size="30" color="black">资源下载</font></p>
	</div>
	<div class="line"></div>

	<c:forEach items="${downloadList}" var="download" varStatus="downloadvst">
		<table style="width: 50%;border-radius: 20px;" align="center" bgcolor="white" border="3" cellspacing="3" frame="void" rules="none">
			<!--
			<caption>定义表格的标题</caption>
			-->
			<tr align="left">
				<th rowspan="1" colspan="10" style="height: 5;"><font size="5px">&nbsp;&nbsp;${download.name}</font></th>
				<th rowspan="3" colspan="3" style="width: 10%;">
					<a href="Download.do?id=${download.id}">
					<button style="width: 100%;height:170;cursor: pointer;background-color: slategray;border-radius: 1.25rem;" >
						<font size="5.6px" color="black" title="点击下载">下载</font>
					</button>
					</a>
				</th>
			</tr>
			<tr>
				<td rowspan="2" colspan="2" align="center" style="width: 10%;"><img src="${download.icon}" style="width: 100px;height: 100px"></td>
				<td rowspan="1" colspan="8" align="left" style="width: 80%; width: 3;">${download.info}</td>
			</tr>
			<tr align="left">
				<td rowspan="1" colspan="8" align="left" style="height: 3;">
					时间：${download.time}&nbsp;&nbsp;
					大小：${download.size}&nbsp;&nbsp;
					星级：<div class="star">
							<span style="width: ${download.star/5*100}%;"></span>
						  </div>
				</td>
			</tr>
		</table>
		<div class="div2"></div>
	</c:forEach>
</body>
</html>
