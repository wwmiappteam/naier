<?php 
	include_once 'common_service.php';
	$action = $_REQUEST["action"];
	if(!isset($_REQUEST["action"])){return;}
	//系统用户一览页面请求的数据
	if($action=="list"){
		$page = $_REQUEST["page"];
		$rows = $_REQUEST["rows"];
		$sql = "select * from custom ";
		$custom_username = $_REQUEST["custom_username"];
		$custom_name = $_REQUEST["custom_name"];
		$sql = $sql." where 1=1 ";
		if($custom_username!=""){
			$sql = $sql." and custom_username like '%$custom_username%' ";
		}
		if($custom_name!=""){
			$sql = $sql." and custom_name like '%$custom_name%' ";
		}
		$result = mysql_query($sql,$con);
		$json = array();
  		$json["total"] =  mysql_num_rows($result);
  		$sql = $sql." order by id desc ";
  		if(isset($page)&&isset($rows)){
	  		$sql = $sql." limit ".($page-1)*$rows.",".$rows;
  		}
  		$result = mysql_query($sql,$con);
  		$json["rows"] = array();
  		while($row=mysql_fetch_assoc($result)){
  			array_push($json["rows"], $row);
  		}
  		echo json($json);
	}else if($action=="delete"){
		$sql = "delete from custom where id=".$_REQUEST['id'];
		mysql_query($sql);
		echo "success";
	}else if($action=="edit"){
		$custom_username = $_REQUEST["custom_username"];		
		$custom_cellphone = $_REQUEST["custom_cellphone"];		
		$custom_password = $_REQUEST["custom_password"];		
		$custom_name = $_REQUEST["custom_name"];		
		$custom_address = $_REQUEST["custom_address"];		
		
		$sql = "update custom set custom_cellphone='$custom_cellphone',custom_password='$custom_password',custom_name='$custom_name',custom_address='$custom_address' where custom_username='$custom_username'";
		mysql_query($sql);
		header("location:./custom_list.php");
	}
?>

