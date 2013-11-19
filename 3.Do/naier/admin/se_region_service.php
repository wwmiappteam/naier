<?php 
	include_once 'common_service.php';
	$action = $_REQUEST["action"];
	//商品一览页面请求的数据
	if($action=="list"){
		$page = $_REQUEST["page"];
		$rows = $_REQUEST["rows"];
		$sql = "select id,region_name from secretary_region order by id desc";
		$result = mysql_query($sql,$con);
		$json = array();
		while($row=mysql_fetch_assoc($result)){
  			array_push($json, $row);
  		}
		echo json($json);
	}else if($action == "add"){
		//普通参数获取
		$region_name = $_REQUEST["region_name"];
		$sql = "insert into secretary_region(region_name) values('$region_name')";
		mysql_query($sql,$con);
		header("Location:./se_region_list.php");
	}else if($action=="delete"){
		$sql = "delete from secretary_region where id=".$_REQUEST['id'];
		mysql_query($sql);
		echo "success";
	}else if($action=="edit"){
		//普通参数获取
		$region_name = $_REQUEST["region_name"];
		$id = $_REQUEST["id"];
		$sql = "update secretary_region set region_name = '$region_name' where id=$id";
		
		mysql_query($sql);
		
		header("Location:./se_region_list.php");
	}
?>

