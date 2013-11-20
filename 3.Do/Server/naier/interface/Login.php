<?php
	//8、登录
	include_once 'common.php';
	$username = $_REQUEST['username'];
	$password = $_REQUEST['password'];
	$json = array();
	$json['data'] = array();
	$json['data']["id"] = "";
	$json['data']["userName"] = "";
	$json['data']["password"] = "";
	$json['data']["cellphone"] = "";
	$json['data']["name"] = "";
	$json['data']["address"] = "";
	$json['data']["orderList"] = array();
	$json["msg"] = "";
	if(isset($username)&&isset($password)){
		$sql = "select * from custom where custom_username='".$username."'";
		$result = mysql_query($sql,$con);
		$row=mysql_fetch_assoc($result);
		if(isset($row['custom_password'])){
			if($password==$row['custom_password']){
				$json['data']["customID"] = $row['id'];
				$json['data']["userName"] = $row['custom_username'];
				$json['data']["password"] = $row['custom_password'];
				$json['data']["cellphone"] = $row['custom_cellphone'];
				$json['data']["name"] = $row['custom_name'];
				$json['data']["address"] = $row['custom_address'];
				
				$subsql = "select * from keeper_order o,keeper_info info,keeper_type type where
				 o.keeper_id=info.id and info.keeper_type_id=type.id and o.custom_id=".$row['id'];
				$subresult = mysql_query($subsql);
				while($subrow=mysql_fetch_assoc($subresult)){
					$tmp = array();
					$tmp["orderID"] = $subrow["id"];
					$tmp["keeperID"] = $subrow["keeper_id"];
					$tmp["keeperName"] = $subrow["keeper_name"];
					$tmp["keeperTypeDescription"] = $subrow["type_description"];
					if($subrow["keeper_photo"]!=""){
						$tmp["keeperPhoto"] = $base.$subrow["keeper_photo"];
					}else{
						$tmp["keeperPhoto"] = "";
					}
					$tmp["startTime"] = $subrow["start_time"];
					$tmp["endTime"] = $subrow["end_time"];
					array_push($json['data']["orderList"], $tmp);
				}
				
			}else{
				$json["msg"]="密码不正确！";
			}
		}else{
			$json["msg"]="用户名不存在！";
		}
	}
	echo json($json);
?>