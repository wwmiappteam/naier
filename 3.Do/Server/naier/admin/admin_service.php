<?php 
	include_once 'common_service.php';
	$action = $_REQUEST["action"];
	//商品一览页面请求的数据
	if($action=="list"){
		$sql = "select * from sys_user order by id desc";
		$result = mysql_query($sql,$con);
		$json = array();
		while($row=mysql_fetch_assoc($result)){
			array_push($json, $row);
  		}
		echo json($json);
	}else if($action == "add"){
		//普通参数获取
		$login = $_REQUEST["login"];
		$password = md5($_REQUEST["password"]);
		$name = $_REQUEST["name"];
		$phone = $_REQUEST["phone"];
		$qq = $_REQUEST["qq"];
		$note = $_REQUEST["note"];
		$sql = "insert into sys_user(login,password,name,phone,qq,note) values('$login','$password','$name','$phone','$qq','$note');";
		mysql_query($sql,$con);
		header("Location:./admin_list.php");
	}else if($action=="delete"){
		$sql = "delete from sys_user where id=".$_REQUEST['id'];
		mysql_query($sql);
		echo "success";
	}else if($action=="edit"){
		//普通参数获取
		$id = $_REQUEST["id"];
		$name = $_REQUEST["name"];
		$phone = $_REQUEST["phone"];
		$qq = $_REQUEST["qq"];
		$note = $_REQUEST["note"];
		$sql = "update sys_user set name='$name',phone='$phone',qq='$qq',note='$note' where id=$id";
		mysql_query($sql,$con);
		header("Location:./admin_list.php");
	}else if($action=="resetpwd"){
		//普通参数获取
		$id = $_REQUEST["id"];
		$pwd = md5($_REQUEST["password"]);
		$sql = "update sys_user set password='$pwd' where id=$id";
		mysql_query($sql,$con);
		header("Location:./admin_list.php");
	}
?>

