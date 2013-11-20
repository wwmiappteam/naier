<?php
	//7、根据活动查询商品列表
	include_once 'common.php';
	$sql = "select * from keeper_info  where keeper_ispush='true'";
	$result = mysql_query($sql,$con);
	$json = array();
	$json['data'] = array();
	while($row=mysql_fetch_assoc($result)){
		$tmp = array();
		$tmp['keeperID'] = $row['id'];
		if($row['keeper_photo']!=""){
			$tmp['keeperPhoto'] = $base.$row['keeper_photo'];
		}else{
			$tmp['keeperPhoto'] = "";
		}
		array_push($json['data'], $tmp);
	}
  	$json['msg']="";
  	echo json($json);
	
?>