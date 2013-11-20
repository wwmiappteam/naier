<?php
	include_once 'common.php';
	$custom_id = $_REQUEST['customID'];
	$keeper_id = $_REQUEST['keeperID'];
	$complain_content = $_REQUEST['complainContent'];
	$sql = "insert into complain(custom_id,keeper_id,complain_content) values('$custom_id','$keeper_id','$complain_content')";
	$json = array();
	if(mysql_query($sql,$con)){
		$json['msg'] = "";
	}else{
		$json['msg'] = "投诉失败！";
	}
	echo json($json);
?>