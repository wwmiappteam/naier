<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>请输入用户名和密码登录系统</title>
<?php
	//配置文件
	include_once '../config/config.inc.php';
	//数据库连接
	include_once '../config/db.php';
	session_start();
?>
<!-- 引入jquery库文件 -->
<script type="text/javascript" src="./js/jquery-1.8.3.min.js" ></script>
<!-- 引入easyui文件 -->
<script type="text/javascript" src="./js/jquery-easyui-1.3.4/jquery.easyui.min.js"></script>
<script type="text/javascript" src="./js/jquery-easyui-1.3.4/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" type="text/css" href="./js/jquery-easyui-1.3.4/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="./js/jquery-easyui-1.3.4/themes/icon.css">
<!-- 通用css文件 -->
<link rel="stylesheet" type="text/css" href="./css/common.css">
<?php 
	$login = $_POST['login'];
	$password = $_POST['password'];
	if(isset($login)&&isset($password)){
		$sql = "select * from sys_user where login='".$login."'";
		$result = mysql_query($sql,$con);
		if(isset($result)){
			$row=mysql_fetch_assoc($result);
			if(md5($password)==$row['password']){
				$_SESSION['admin']=$login;
				header("location:./index.php");
			}else{
				echo "<script>alert('用户名或密码错误请重新登录！');</script>";
			}
		}
	}
	if(isset($_REQUEST['action'])){
		session_destroy();
	}
?>
<style type="text/css">
	.loginbox {
		width: 350px;
		height: 180px;
		border: 3px solid #008bd5;
		margin-top: 150px;
		padding-top:10px;
		padding-bottom:10px;
	}
	.loginbox ul li{
		width: 350px;
		height:30px;
		margin-top:15px;
		line-height:30px;
	}
</style>
</head>
<body>
	<div align="center">
		<form id="loginform" action="./login.php" method="post">
			<div class="loginbox">
				<ul class="ul">
					<li style="maring-top:0px;height:25px;font-size:16px;">欢迎登录奈尔家政后台系统</li>
		        	<li><span class="infoname3">用户名：</span><input type="text" name="login" class="text01"></li>
		        	<li><span class="infoname3">密码：</span><input type="password" name="password" class="text01"></li>
		        	<li><a href="javascript:void(0);" onclick="$('#loginform').submit();" class="easyui-linkbutton">登录</a>&nbsp;&nbsp;
		        		<a href="#" onclick="$('input[type=reset]').trigger('click');" class="easyui-linkbutton">重置</a>
		        	</li>
		        	<input type="reset" style="display:none;" /> 
		      	</ul>
	      	</div>
      	</form>
	</div>
</body>
</html>