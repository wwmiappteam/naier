<?php
	//14、获取微博接口数据 
	include_once 'common.php';
	//类型id
	$sql = "select name,appkey,appsecret,redirecturl from weibo ";
	$json = array();
	$json["weiboList"] = array();
	$result = mysql_query($sql,$con);
	while($row=mysql_fetch_assoc($result)){
  		$tmp = array();
  		$tmp['name']=$row['name'];
  		$tmp['appKey']=$row['appkey'];
  		$tmp['appSecret']=$row['appsecret'];
  		$tmp['redirectUrl']=$row['redirecturl'];
  		array_push($json["weiboList"], $tmp);
  	}
  	$json['msg']="";
  	echo json($json);
?>