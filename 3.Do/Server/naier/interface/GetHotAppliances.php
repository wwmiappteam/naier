<?php
	//1、接口获取热销商品的接口
	include_once 'common.php';
	$currentPage = $_REQUEST['currentPage'];
	$pageRows = $_REQUEST['pageRows'];
	$json = array();
	//分页取热销商品
	$sql = "select id,title,originalPrice,currentPrice,imgsrc,comment from shop_goods where ishot=1";
	$result = mysql_query($sql,$con);
  	$json["currentPage"] = $currentPage;
	$json["totalPage"] =  ceil(mysql_num_rows($result)/intval($pageRows));
	$sql = $sql." order by id desc  limit ".(intval($currentPage)-1)*$pageRows.",".$pageRows;
	$result = mysql_query($sql,$con);
  	$json["appliancesList"] = array();
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
  	$json["msg"] = "";
  	echo json($json);
?>