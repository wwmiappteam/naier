<?php
	//13、获取首页展示图列表
	include_once 'common.php';
	$json = array();
	//分页取热销商品
	$sql = "select * from img_resource where imgtype='1'";
	$result = mysql_query($sql,$con);
  	$json["homeImgList"] = array();
  	while($row=mysql_fetch_assoc($result)){
  		$tmp = array();
  		array_push($json["homeImgList"], $base.$row["imgsrc"]);
  	}
  	$json["msg"] = "";
  	echo json($json);
?>