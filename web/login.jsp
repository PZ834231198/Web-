<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
        <meta charset="UTF-8">
        <title>登陆页面</title>
    <link href="css/login.css" rel="stylesheet" type="text/css">
    <script src="js/login.js"></script>
</head>
 <body >
 <div class="div1">
     <form action="Login.do" method="POST"  >
         <p>
             <input type="text" name="userName" placeholder="账户" class="userName"/>
         </p>
         <p>
             <input type="password" name="password" placeholder="密码" class="password"/>
         <p>
             <input type="text" name="userCode" size="5px" placeholder="验证码" class="vCode">
             <img src="CreateVerifyImage.do" id="vCode"  onclick="ChangeImage()" title="看不清，换一张" style="position: relative;top: 8px">
         </p>
		 <div>
			 <input type="checkbox" name="autologin" style="position: relative;left: -50px;" >
			 <font color="#6495ed" style="position: relative;left: -50px;">一周内免登陆</font>
			 </input>
		 </div>
         <p>
             <input type="submit" value="登      陆" style="width:150px;height:40px;background-color: blue;color: white;font-weight: 550;"/>
         </p>
     </form>
 </div>

 </body>
</html>
