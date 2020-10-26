//点击验证码图片
function ChangeImage() {
    //需要让每次请求的url都发生变化，否则服务器会认为访问的是同一张图片，就不会请求刷新了
    var image =  document.getElementById("vcodeImage");
    image.src ="CreateVerifyImage.do?t=" + Math.random();
}

$(document).ready(function(){
$("#userName").blur(function(){
    var info = $("#userName").val();
    if(info == "")
        $("#userNameError").text("用户名不能为空");
    else
        $("#userNameError").text("");
});
$("#password").blur(function(){
  	var info = $("#password").val();
  	if(info == "")
  		$("#passwordError").text("密码不能为空");
  	else
  		$("#passwordError").text("");
});
$("#vcode").blur(function(){
  	var info = $("#vcode").val();
  	if(info == "")
  		$("#vcodeError").text("验证码不能为空");
  	else
  		$("#vcodeError").text("");
});
});

var xmlHttp;
//创建XMLHTTPRequest对象
function createXmlHttp(){
	if(window.XMLHttpRequest){
		xmlHttp = new XMLHttpRequest();
	}else{
		xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
}

//使用原生的js实现ajax登录
function ajaxCheckLogin(){
	//取表单数据
	var userName = document.getElementById("userName").value;
	var password = document.getElementById("password").value;
	var vcode = document.getElementById("vcode").value;
	var autoLogin = document.getElementById("autoLogin").checked;

	createXmlHttp(); //调用自定义函数创建XMLHttpRequest对象
	xmlHttp.open("post","AjaxLoginCheck.do",true);
	xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	xmlHttp.send("userName=" + userName + "&password=" + password + "&vcode=" + vcode + "&autoLogin=" + autoLogin);
	
	xmlHttp.onreadystatechange = function(){ //回调函数
		if(xmlHttp.readyState == 4 && xmlHttp.status == 200) {
            var response = xmlHttp.responseText;
            var json = JSON.parse(response); //调用系统函数将字符串转化为json对象
            if (json.code == 0) { //登录成功
                window.location.href = "main.jsp";
            } else { //登陆失败
                document.getElementById("checkError").innerText = json.info;  //显示返回错误信息
            }
        }
	}

}