<?php
	//6、获取活动列表
	include_once 'common.php';
	$json = array();
	//分页取热销商品
	$sql = "select id,poster,intro from shop_sales order by id desc";
	$result = mysql_query($sql,$con);
  	$json["eventList"] = array();
  	while($row=mysql_fetch_assoc($result)){
  		$tmp = array();
  		$tmp["eventId"] = $row["id"];
  		//活动列表图
  		$tmp["imgUrl"] = $base.$row["logo"];
  		//活动海报图
  		$tmp["posterImgUrl"] = $base.$row["poster"];
  		$tmp["desc"] = $row["intro"];
  		array_push($json["eventList"], $tmp);
  	}
  	$json["msg"] = "";
  	echo json($json);
?>