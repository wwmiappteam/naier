<?php
	//12、获取卖场信息列表
	include_once 'common.php';
	$sql = "select * from shop_info order by id desc";
	$json = array();
	$json["shopList"] = array();
	$result = mysql_query($sql,$con);
	while($row=mysql_fetch_assoc($result)){
  		$row['installPhone']=$row['installphone'];
  		$row['servicePhone']=$row['servicephone'];
  		array_push($json["shopList"], $row);
  	}
  	$json['msg']="";
  	echo json($json);
?>