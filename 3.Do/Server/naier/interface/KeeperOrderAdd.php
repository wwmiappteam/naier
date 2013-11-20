<?php
	//9、注册
	include_once 'common.php';
	$custom_id = $_REQUEST['customID'];
	$keeper_id = $_REQUEST['keeperID'];
	$start_time = $_REQUEST['startTime'];
	$end_time = $_REQUEST['endTime'];
	$order_description = $_REQUEST['orderDescription'];
	$sql = "insert into keeper_order(custom_id,keeper_id,start_time,end_time,order_description) values('$custom_id','$keeper_id','$start_time','$end_time','$order_description')";
	$json = array();
	if(mysql_query($sql,$con)){
		$json['msg'] = "";
	}else{
		$json['msg'] = "预约失败！";
	}
	echo json($json);
?>