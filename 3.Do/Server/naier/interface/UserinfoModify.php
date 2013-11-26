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
	$json = array();
	$json['msg'] = "";
	
	if($passwordNew!=""){
		$checksql = "select * from custom where id=$Id and custom_password='$passwordInitial'";
		$resultcheck = mysql_query($checksql);
		if(mysql_num_rows($resultcheck)!=1){
			$json['msg'] = "请输入正确的旧密码";
		}else{
			$sql = $sql." ,custom_password='$passwordNew' ";
			$sql = $sql." where id=$Id";
			$sql = $sql." and custom_password='$passwordInitial'";
		}
	}else{
		$sql = $sql." where id=$Id";
	}
	mysql_query($sql);
  	echo json($json);
  	
?>