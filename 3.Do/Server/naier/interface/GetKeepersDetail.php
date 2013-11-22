<?php
	//7、根据活动查询商品列表
	include_once 'common.php';
	$id = $_REQUEST["keeperID"];
	$sql = "select * from keeper_info where id=$id";
	$result = mysql_query($sql,$con);
	
	$json = array();
	$json['data'] = array();
	if($row=mysql_fetch_assoc($result)){
		$tmp = array();
		$tmp['keeperID']=$row['id'];
		$subsql = "select type_name from keeper_type where id='".$row['keeper_type_id']."' ";
  		$subresult = mysql_query($subsql);
  		$subrow = mysql_fetch_assoc($subresult);
  		$tmp['typeName'] = $subrow['type_name'];
		$tmp['keeper_name']=$row['keeper_name'];
		$tmp['keeper_gender']=$row['keeper_gender'];
		$tmp['keeper_age']=$row['keeper_age'];
		if($row['keeper_photo']!=""){
			$tmp['keeper_photo']=$base.$row['keeper_photo'];
		}else{
			$tmp['keeper_photo'] = "";
		}
		$tmp['keeper_experience']=$row['keeper_experience'];
		$tmp['keeper_level']=$row['keeper_level'];
		$tmp['keeper_professional']=$row['keeper_professional'];
		$tmp['keeper_attitude']=$row['keeper_attitude'];
		$tmp['keeper_hardworking']=$row['keeper_hardworking'];
		$tmp['keeper_attentive']=$row['keeper_attentive'];
		$tmp['keeper_special']=$row['keeper_special'];
		$tmp['keeper_introduce']=$row['keeper_introduce'];
		//array_push($json['data'], $tmp);
		$json['data']=$tmp;
	}
  	$json['msg']="";
  	echo json($json);
	
?>