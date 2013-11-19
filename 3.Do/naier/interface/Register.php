<?php
	//9、注册
	include_once 'common.php';
	$login = $_REQUEST['userName'];
	if($login==""){
		exit();
	}
	$password = md5($_REQUEST['pwd']);
	$invitationCode = $_REQUEST['invitationCode'];
	$sql = "select * from member where mobilenum='".$login."'";
	$json = array();
	$result = mysql_query($sql,$con);
	if(mysql_num_rows($result)>0){
		$json["msg"] = "用户名已经被注册！";
		echo json($json);
		exit();
	}else{
		//当有注册码时，判断是否老会员介绍新会员
		$flag =false;
		if($invitationCode!=""){
			$tmpsql = "select * from member where invitecode='$invitationCode'";
			$mem = mysql_query($tmpsql,$con);
			if(mysql_num_rows($mem)>0){
				$memrow=mysql_fetch_assoc($mem);
				$points = $memrow["points"]==""?0:intval($memrow["points"])+$invite_point;
				$tmpsql = "update member set points=$points where invitecode='$invitationCode'";
				mysql_query($tmpsql);
				$flag = true;
			}
		}
		//$myinvitationCode = strval(time()).strval(substr($login, -3));
		$strInvitationCode = '1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ';
		$myinvitationCode=$strInvitationCode[rand(0,61)].$strInvitationCode[rand(0,61)].$strInvitationCode[rand(0,61)].$strInvitationCode[rand(0,61)].$strInvitationCode[rand(0,61)].$strInvitationCode[rand(0,61)];
		if($flag){
			$sql = "insert into member(mobilenum,password,invitecode,points) value('$login','$password','$myinvitationCode',$invite_point)";
		}else{
			$sql = "insert into member(mobilenum,password,invitecode) value('$login','$password','$myinvitationCode')";
		}
		mysql_query($sql);
		$json["msg"] = "";
		echo json($json);
	}
?>