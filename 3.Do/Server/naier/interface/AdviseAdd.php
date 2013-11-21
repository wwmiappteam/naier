<?php
	include_once 'common.php';
	$custom_id = $_REQUEST['customID'];
	$custom_cellphone = $_REQUEST['cellphone'];
	$advise_content = $_REQUEST['adviseContent'];
	$timefmt = date("Y-m-d H:i");
	$sql = "insert into advise(custom_id,custom_cellphone,advise_content,update_time) values('$custom_id','$custom_cellphone','$advise_content','$timefmt')";
	$json = array();
	if(mysql_query($sql,$con)){
		$json['msg'] = "";
	}else{
		$json['msg'] = "意见反馈失败！";
	}
	echo json($json);
?>