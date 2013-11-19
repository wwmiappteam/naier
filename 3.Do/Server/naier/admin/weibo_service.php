<?php 
	include_once 'common_service.php';
	$action = $_REQUEST["action"];
	//商品一览页面请求的数据
	if($action=="list"){
		$sql = "select * from weibo order by id desc";
		$result = mysql_query($sql,$con);
		$json = array();
		while($row=mysql_fetch_assoc($result)){
			array_push($json, $row);
  		}
		echo json($json);
	}else if($action == "add"){
		//普通参数获取
		$name = $_REQUEST["name"];
		$appkey = $_REQUEST["appkey"];
		$appsecret = $_REQUEST["appsecret"];
		$redirecturl = $_REQUEST["redirecturl"];
		$sql = "insert into weibo(name,appkey,appsecret,redirecturl) values('$name','$appkey','$appsecret','$redirecturl');";
		mysql_query($sql,$con);
		header("Location:./weibo_list.php");
	}else if($action=="delete"){
		$sql = "delete from weibo where id=".$_REQUEST['id'];
		mysql_query($sql);
		echo "success";
	}else if($action=="edit"){
		$id = $_REQUEST["id"];
		$name = $_REQUEST["name"];
		$appkey = $_REQUEST["appkey"];
		$appsecret = $_REQUEST["appsecret"];
		$redirecturl = $_REQUEST["redirecturl"];
		$sql = "update weibo set name='$name',appkey='$appkey',appsecret='$appsecret',redirecturl='$redirecturl' where id=$id;";
		mysql_query($sql,$con);
		header("Location:./weibo_list.php");
	}
?>

