<?php
	//10、意见反馈
	include_once 'common.php';
	$userId = $_REQUEST['userId'];
	$feedback = $_REQUEST['feedback'];
	$sql = "insert into comment(memberid,content) values($userId,'$feedback')";
	mysql_query($sql,$con);
	$json = array();
	$json['msg'] = "";
	echo json($json);
?>