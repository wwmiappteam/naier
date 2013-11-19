<?php
	//8、登录
	include_once 'common.php';
	$login = $_REQUEST['userName'];
	$password = $_REQUEST['pwd'];
	$msg = "";
	$json = array();
	$json["userId"] = "";
	$json["userIntegral"] = "";
	$json["installPhone"] = "";
	$json["servicePhone"] = "";
	$json["msg"] = "";
	if(isset($login)&&isset($password)){
		$sql = "select * from member where mobilenum='".$login."'";
		$result = mysql_query($sql,$con);
		$row=mysql_fetch_assoc($result);
		if(isset($row['password'])){
			if(md5($password)==$row['password']){
				$json["userid"] = $row["memberid"];
				$json["mobilenum"] = $row["mobilenum"];
				$json["password"] = $password;
				$json["name"] = $row["name"];
				$json["work"] = $row["work"];
				$json["sex"] = $row["sex"];
				$json["birthday"] = $row["birthday"];
				$json["addr"] = $row["addr"];
				$json["email"] = $row["email"];
				$json["passcode"] = $row["passcode"];
				$json["qq"] = $row["qq"];
				$json["inviteCode"] = $row["invitecode"];
				
				//每日登录增加积分
				$today = date('Y-m-d');
				if($row["llogintime"]!=$today){
					$everyday_point=5;
					$points = intval($row["points"])+$everyday_point;
					$row["points"] = $points;
					$time = time();
					$addPoint = "update member set points=$points,llogintime='$today',shopviewnum=0 where memberid=".$row["memberid"];
					mysql_query($addPoint);
				}
				//首次登录增加积分20
				if($row["logined"]=="0"){
					$first_point=20;
					$points = intval($row["points"])+$first_point;
					$row["points"] = $points;
					$addPoint = "update member set points=$points,logined=1 where memberid=".$row["memberid"];
					mysql_query($addPoint);
				}
				$json["points"] = $row["points"];
			}else{
				$json["msg"]="密码不正确！";
			}
		}else{
			$json["msg"]="用户名不存在！";
		}
	}
	echo json($json);
?>