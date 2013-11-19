<?php
	//7、根据活动查询商品列表
	include_once 'common.php';
	$currentPage = $_REQUEST['currentPage'];
	$pageRows = $_REQUEST['pageRows'];
	//类型ID
	$eventId = $_REQUEST['eventId'];
	$sql = "select id,title,originalPrice,currentPrice,imgsrc,comment,ishot from shop_goods ";
	if($eventId!=""){
		$sql .=" where saleid='".$eventId."'";
	}
	$result = mysql_query($sql,$con);
	$json = array();
  	$json["currentPage"] = $currentPage;
	$json["totalPage"] =  ceil(mysql_num_rows($result)/intval($pageRows));
	$sql = $sql." order by id desc limit ".(intval($currentPage)-1)*$pageRows.",".$pageRows;
	$json["currentPage"] = $currentPage;
	$json["appliancesList"] = array();
	$result = mysql_query($sql,$con);
	while($row=mysql_fetch_assoc($result)){
  		$tmp = array();
  		$tmp['appliancesId']=$row['id'];
  		$tmp['appliancesName']=$row['title'];
  		$tmp['originalPrice']=$row['originalPrice'];
  		$tmp['currentPrice']=$row['currentPrice'];
  		$tmp['imgUrl']=$base.$row['imgsrc'];
  		$tmp['comment']=$row['comment'];
  		array_push($json["appliancesList"], $tmp);
  	}
  	$json['msg']="";
  	echo json($json);
	
?>