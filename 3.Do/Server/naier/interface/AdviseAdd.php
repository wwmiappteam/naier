<?php
	include_once 'common.php';
	$custom_id = $_REQUEST['customID'];
	$custom_cellphone = $_REQUEST['cellphone'];
	$advise_content = $_REQUEST['adviseContent'];
	$sql = "insert into advise(custom_id,custom_cellphone,advise_content) values('$custom_id','$custom_cellphone','$advise_content')";
	$json = array();
	if(mysql_query($sql,$con)){
		$json['msg'] = "";
	}else{
		$json['msg'] = "意见反馈失败！";
	}
	echo json($json);
?>