function fillProvince(){
	$.ajax({
		type:'post',//HTTP请求类型
		url:"queryProvinceCity.do",
		data:{},
		dataType:"json",
		success:function(response){
			for(var index=0;index < response.length;index++){
				var option = $("<option>").val(response[index].provinceCode)
					.text(response[index].provinceName);
				$("#province").append(option);
			}
		}
	});
}

var xmlHttpRequest;
//创建XMLHTTPRequest对象
function createXMLRequest() {
    if (window.XMLHttpRequest) {
        xmlHttpRequest = new XMLHttpRequest();
    } else {
        xmlHttpRequest = new ActiveXObject("Microsoft.XMLHTTP");
    }
}
$("#registerButton").click(function(e) {
    var userNameError = document.getElementById("userNameError");
    var chrNameError = document.getElementById("chrNameError");
    var emailError = document.getElementById("emailError");
    var provinceError = document.getElementById("provinceError");
	var cityError = document.getElementById("cityError");
    var passwordError = document.getElementById("passwordError");
    var passwordCheckError = document.getElementById("passwordCheckError");

    if(	userNameError == ""&&
		chrNameError == ""&&
		emailError == ""&&
		provinceError == ""&&
		cityError == ""&&
		passwordError == ""&&
		passwordCheckError == ""
		){
			$.ajax({
            type: "post",
            url: "register.do",
            data: {
                "userName": $("#userName").val(),
                "chrName": $("#chrName").val(),
                "mail": $("#mail").val(),
                "province": $("#province").find("option:selected").text(),
                "city": $("#city").find("option:selected").text(),
                "password": $("#password").val()
            },
            dataType: "json",
            success: function(response) {
                if (response.code == 0) {
                    alert(response.info);
                    window.location.href = "index.html";
                } else {
                    alert(response.info + " 请检查输入数据是否正确!");
                }
            }
        });
		}else {
			document.getElementById("buttonError").innerText = json.info;  //显示返回错误信息
    }
});



 
$(document).ready(function(){
	fillProvince();//调用函数，填充省份下拉框
	
	$("#province").change(function(){
		$("#city").empty();
		$("#city").append($("<option>").val("").text("-请选择-"));
		if($(this).val() == ""){
			$("#provinceError").text("必须先选择省份!");
			return;
		}
		province_correct = true;
		$("#provinceError").text("");
		
		var provinceCode = $("#province").val();
		$.ajax({
			type:'post',
			url:"queryProvinceCity.do",
			data:{ "provinceCode": provinceCode },
			dataType:"json",
			success:function(response){
				for(var index=0;index<response.length;index++){
					var option = $("<option>").val(response[index].cityCode)
						.text(response[index].cityName);
					$("#city").append(option);
				}
			}
		});
	});
	
	$("#userName").blur(function(){
		var info = $("#userName").val();
		if(info == "")
			$("#userNameError").text("用户名不能为空");
		else
			$("#userNameError").text("");
	});
	
	$("#chrName").blur(function(){
		var info = $("#chrName").val();
		var pattern = /^[\u4e00-\u9fa5]{2,100}$/;
		if(info == ""){
			$("#chrNameError").text("真实姓名不能为空");
		}else if(!pattern.test(info)){
			$("#chrNameError").text("请正确输入中文名");
		}else{
			$("#chrNameError").text("");
		}
	});
	
	$("#mail").blur(function(){
		var info = $("#mail").val();
		var pattern = /\w[-\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\.)+[A-Za-z]{2,14}/;
		if(info == ""){
			$("#mailError").text("邮箱不能为空");
		}else if(!pattern.test(info)){
			$("#mailError").text("请输入正确的邮箱格式");
		}else{
			$("#mailError").text("");
		}	
	});
		
	$("#password").blur(function(){
		var info = $("#password").val();
		if(info == "")
			$("#passwordError").text("密码不能为空");
		else
			$("#passwordError").text("");
	});
	
	$("#passwordCheck").blur(function(){
		var info1 = $("#password").val();
		var info2 = $("#passwordCheck").val();
		if(info1 == ""){
			$("#passwordCheckError").text("请先设置密码");
		}else if(info2 == ""){
			$("#passwordCheckError").text("请确认密码");
		}else{
			$("#passwordCheckError").text("");
		}	
	});
});