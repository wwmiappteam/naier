<?php
	//6、获取活动列表
	include_once 'common.php';
	$json = array();
	//分页取热销商品
	$sql = "select title,id,poster,intro,share_content,logo from shop_sales order by id desc";
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
  		$tmp["shareContent"] = $row["share_content"];
  		$tmp["title"] = $row["title"];
  		array_push($json["eventList"], $tmp);
  	}
  	$json["msg"] = "";
  	echo json($json);
?>