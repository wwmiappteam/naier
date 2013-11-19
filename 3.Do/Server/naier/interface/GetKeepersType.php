<?php
	//7、根据活动查询商品列表
	include_once 'common.php';
	$sql = "select * from keeper_type ";
	$result = mysql_query($sql,$con);
	$json = array();
	$json['data'] = array();
	while($row=mysql_fetch_assoc($result)){
		$tmp = array();
		$tmp['id']=$row['id'];
		$tmp['typeName']=$row['type_name'];
		$tmp['typeCode']=$row['type_code'];
		$tmp['typeDescription']=$row['type_description'];
		$subsql = "select * from keeper_info where keeper_type_id='$row[id]'";
		$subresult = mysql_query($subsql);
		$tmp['keeperNum'] = mysql_num_rows($subresult);
		$tmp['keepers'] = array();
		while($subrow=mysql_fetch_assoc($subresult)){
			$subtmp = array();
			$subtmp['id']=$subrow['id'];
			$subtmp['keeperName']=$subrow['keeper_name'];
			if($subrow['keeper_photo']!= ""){
				$subtmp['keeperPhoto']=$base.$subrow['keeper_photo'];
			}else{
				$subtmp['keeperPhoto'] = "";
			}
			array_push($tmp['keepers'], $subtmp);
		}
		array_push($json['data'], $tmp);
	}
  	$json['msg']="";
  	echo json($json);
	
?>