<?php
	//个人信息完善
	include_once 'common.php';
	$Id = $_REQUEST['customID'];
	$cellphone = $_REQUEST['cellphone'];
	$address = $_REQUEST['address'];
	$passwordInitial = $_REQUEST['passwordInitial'];
	$passwordNew = $_REQUEST['passwordNew'];
	
	$timefmt = date("Y-m-d H:i");
	$sql = "update custom set update_time='$timefmt'";
	if($cellphone!=""){
		$sql = $sql." ,custom_cellphone='$cellphone' ";
	}
	if($address!=""){
		$sql = $sql." ,custom_address='$address' ";
	}
	
	if($passwordNew!=""){
		$sql = $sql." ,custom_password='$passwordNew' ";
		$sql = $sql." where id=$Id";
		$sql = $sql." and custom_password='$passwordInitial'";
	}else{
		$sql = $sql." where id=$Id";
	}
	
	$json = array();
	$json['msg'] = "";
	if(!mysql_query($sql)){
		$json['msg'] = "请输入正确的旧密码";
	}
  	echo json($json);
  	
?>