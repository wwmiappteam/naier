<?php 
	include_once 'common_service.php';
	$action = $_REQUEST["action"];
	if(!isset($_REQUEST["action"])){return;}
	//系统用户一览页面请求的数据
	if($action=="list"){
		$page = $_REQUEST["page"];
		$rows = $_REQUEST["rows"];
		$sql = "select advise.*,custom.custom_username,custom.custom_name  from advise left join custom on advise.custom_id = custom.id ";
		$result = mysql_query($sql,$con);
		$json = array();
  		$json["total"] =  mysql_num_rows($result);
  		$sql = $sql." order by status asc,advise.id desc ";
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
		$sql = "delete from advise where id=".$_REQUEST['id'];
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
	}else if($action=="chuli"){
		$sql = "update advise set status='1' where id=".$_REQUEST['id'];
		mysql_query($sql);
		echo "success";
	}
?>

