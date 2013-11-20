<?php 
	include_once 'common_service.php';
	$action = $_REQUEST["action"];
	if(!isset($_REQUEST["action"])){return;}
	//系统用户一览页面请求的数据
	if($action=="list"){
		$page = $_REQUEST["page"];
		$rows = $_REQUEST["rows"];
		$sql = "select * from orderinfo_view ";
		$custom_username = $_REQUEST["custom_username"];
		$custom_name = $_REQUEST["custom_name"];
		$keeper_name = $_REQUEST["keeper_name"];
		$sql = $sql." where 1=1 ";
		if($custom_username!=""){
			$sql = $sql." and custom_username like '%$custom_username%' ";
		}
		if($custom_name!=""){
			$sql = $sql." and custom_name like '%$custom_name%' ";
		}
		if($keeper_name!=""){
			$sql = $sql." and keeper_name like '%$keeper_name%' ";
		}
		$result = mysql_query($sql,$con);
		$json = array();
  		$json["total"] =  mysql_num_rows($result);
  		$sql = $sql." order by orderid desc ";
  		if(isset($page)&&isset($rows)){
	  		$sql = $sql." limit ".($page-1)*$rows.",".$page*$rows;
  		}
  		$result = mysql_query($sql,$con);
  		$json["rows"] = array();
  		while($row=mysql_fetch_assoc($result)){
  			array_push($json["rows"], $row);
  		}
  		echo json($json);
	}else if($action=="delete"){
		$sql = "delete from keeper_order where id=".$_REQUEST['id'];
		mysql_query($sql);
		echo "success";
	}else if($action=="edit"){
		$id = $_REQUEST["id"];		
		$keeper_id = $_REQUEST["keeper_id"];		
		$start_time = $_REQUEST["start_time"];		
		$end_time = $_REQUEST["end_time"];		
		$order_description = $_REQUEST["order_description"];		
		
		$sql = "update keeper_order set keeper_id='$keeper_id',start_time='$start_time',end_time='$end_time',order_description='$order_description' where id='$id'";
		mysql_query($sql);
		header("location:./order_list.php");
	}
?>

