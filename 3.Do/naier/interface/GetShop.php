<?php
	include_once 'common.php';
	$currentPage = $_REQUEST['currentPage'];
	$pageRows = $_REQUEST['pageRows'];
  	$sql = "select * from shop ";
	$result = mysql_query($sql,$con);
	$json = array();
  	$json["currentPage"] = $currentPage;
	$json["totalPage"] =  ceil(mysql_num_rows($result)/intval($pageRows));
	$sql = $sql." order by id desc limit ".(intval($currentPage)-1)*$pageRows.",".$pageRows;
	$json["data"] = array();
	$result = mysql_query($sql,$con);
	while($row=mysql_fetch_assoc($result)){
  		$tmp = array();
  		$tmp['id']=$row['id'];
  		$tmp['shopName']=$row['shop_name'];
  		$tmp['shopTel']=$row['shop_tel'];
  		$tmp['shopAddress']=$row['shop_address'];
  		$tmp['baiduLongitude']=$row['baidu_longitude'];
  		$tmp['baiduLatitude']=$row['baidu_latitude'];
  		$tmp['googleLongitude']=$row['google_longitude'];
  		$tmp['googleLatitude']=$row['google_latitude'];
  		array_push($json["data"], $tmp);
  	}
  	$json['msg']="";
  	echo json($json);
	
?>