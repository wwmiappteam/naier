<?php
	//个人信息完善
	include_once 'common.php';
	$userid = $_REQUEST['userid'];
	if($userid==""){
  		exit();
  	}	
	$password = md5($_REQUEST['password']);
	$name = $_REQUEST['name'];
	$work = $_REQUEST['work'];
	$sex = $_REQUEST['sex'];
	$birthday = $_REQUEST['birthday'];
	$addr = $_REQUEST['addr'];
	$email = $_REQUEST['email'];
	$passcode = $_REQUEST['passcode'];
	$qq = $_REQUEST['qq'];
	$sql = "update member set name='$name'".
	 " , work='$work' , sex='$sex' , birthday='$birthday' , addr='$addr' , email='$email' , passcode='$passcode', qq='$qq' where memberid=$userid ";
	mysql_query($sql);
	$json = array();
	$json['msg'] = "";
  	echo json($json);
  	
  	$sql = "select * from member where memberid=$userid ";
  	$result = mysql_query($sql,$con);
	if($row=mysql_fetch_assoc($result)){
		if($row["checked"]!=1&$row["mobilenum"]!=""&&$row["password"]!=""&&$row["name"]!=""&&$row["work"]!=""&&$row["sex"]!=""&&$row["birthday"]!=""&&$row["addr"]!=""&&$row["email"]!=""&&$row["passcode"]!=""&&$row["qq"]!=""){
			$points = intval($row["points"])+$userinfo_point;
			$sql = "update member set checked=1,points=$points where memberid=$userid ";
  			mysql_query($sql,$con);
		}
	}
?>