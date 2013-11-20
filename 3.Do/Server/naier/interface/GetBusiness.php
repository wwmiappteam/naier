<?php
	include_once 'common.php';
	$currentPage = $_REQUEST['currentPage'];
	$pageRows = $_REQUEST['pageRows'];
  	$sql = "select * from business ";
	$result = mysql_query($sql,$con);
	$json = array();
  	$json["currentPage"] = $currentPage;
	$json["totalPage"] =  ceil(mysql_num_rows($result)/intval($pageRows));
	$sql = $sql." order by id desc limit ".(intval($currentPage)-1)*$pageRows.",".$pageRows;
	$json["data"] = array();
	$result = mysql_query($sql,$con);
	while($row=mysql_fetch_assoc($result)){
  		$tmp = array();
  		$tmp['businessID']=$row['id'];
  		$tmp['busiTitle']=$row['busi_title'];
  		$tmp['busiPrice']=$row['busi_price'];
  		$tmp['busiIntroduce']=$row['busi_introduce_interface'];
  		array_push($json["data"], $tmp);
  	}
  	$json['msg']="";
  	echo json($json);
	
?>