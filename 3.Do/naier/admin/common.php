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
<!-- 引入UEditor -->
<script type="text/javascript" src="./js/ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="./js/ueditor/ueditor.all.js"></script>
<!-- 通用css文件 -->
<link rel="stylesheet" type="text/css" href="./css/common.css">
<?php 
	if(!isset($_SESSION['admin'])){
		header("location:./login.php");
	}
?>
