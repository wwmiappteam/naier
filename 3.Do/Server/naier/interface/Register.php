<?php
	//9、注册
	include_once 'common.php';
	$username = $_REQUEST['username'];
	$password = $_REQUEST['password'];
	$name = $_REQUEST['name'];
	$cellphone = $_REQUEST['cellphone'];
	$address = $_REQUEST['address'];
	$sql = "select * from custom where custom_username='".$username."'";
	$json = array();
	$json["data"] = array();
	$json["data"]["orderList"] = array();
	$result = mysql_query($sql,$con);
	if(mysql_num_rows($result)>0){
		$json["msg"] = "用户名已经被注册！";
		$json["data"]["customID"]="";
		$json["data"]["userName"]=$username;
		$json["data"]["password"]=$password;
		$json["data"]["cellphone"]=$cellphone;
		$json["data"]["name"]=$name;
		$json["data"]["address"]=$address;
		echo json($json);
		exit();
	}else{
		$sql = "insert into custom (custom_username,custom_cellphone,custom_password,custom_name,custom_address)
				values('$username','$cellphone','$password','$name','$address')";
		mysql_query($sql);
		$json["msg"] = "";
		$json["data"]["customID"]=mysql_insert_id();
		$json["data"]["userName"]=$username;
		$json["data"]["password"]=$password;
		$json["data"]["cellphone"]=$cellphone;
		$json["data"]["name"]=$name;
		$json["data"]["address"]=$address;
		echo json($json);
	}
?>