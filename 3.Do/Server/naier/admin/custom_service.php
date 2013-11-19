<?php 
	include_once 'common_service.php';
	$action = $_REQUEST["action"];
	if(!isset($_REQUEST["action"])){return;}
	//系统用户一览页面请求的数据
	if($action=="list"){
		$page = $_REQUEST["page"];
		$rows = $_REQUEST["rows"];
		$sql = "select * from member";
		$result = mysql_query($sqltotal,$con);
		$mobilenum = $_REQUEST['mobilenum'];
		$param = null;
		if($mobilenum!=""){
			$param = array();
			$param["mobilenum"]=" like '%".$mobilenum."%'";
		}
		echo  query_page($con,$sql,$param,$page,$rows,null);
	}else if($action=="delete"){
		$sql = "delete from member where memberid=".$_REQUEST['id'];
		echo $sql;
		mysql_query($sql);
		echo "success";
	}else if($action=="edit"){
		$id = $_REQUEST["id"];		
		$name = $_REQUEST["name"];		
		$mobilenum = $_REQUEST["mobilenum"];		
		$points = $_REQUEST["points"];		
		$work = $_REQUEST["work"];		
		$sex = $_REQUEST["sex"];		
		$birthday = $_REQUEST["birthday"];		
		$addr = $_REQUEST["addr"];		
		$email = $_REQUEST["email"];		
		$passcode = $_REQUEST["passcode"];		
		$qq = $_REQUEST["qq"];
		
		$sql = "update member set name='$name',mobilenum='$mobilenum',points='$points',work='$work',sex='$sex',birthday='$birthday',addr='$addr',email='$email',passcode='$passcode',qq='$qq' where memberid=$id";
		mysql_query($sql);
		header("location:./member_list.php");
	}
?>

