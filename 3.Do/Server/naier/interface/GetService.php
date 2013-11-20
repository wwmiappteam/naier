<?php
	include_once 'common.php';
	$currentPage = $_REQUEST['currentPage'];
	$pageRows = $_REQUEST['pageRows'];
  	$sql = "select * from service ";
	$result = mysql_query($sql,$con);
	$json = array();
  	$json["currentPage"] = $currentPage;
	$json["totalPage"] =  ceil(mysql_num_rows($result)/intval($pageRows));
	$sql = $sql." order by id desc limit ".(intval($currentPage)-1)*$pageRows.",".$pageRows;
	$json["data"] = array();
	$result = mysql_query($sql,$con);
	while($row=mysql_fetch_assoc($result)){
  		$tmp = array();
  		$tmp['serviceID']=$row['id'];
  		$tmp['servTitle']=$row['serv_title'];
  		$tmp['servIntroduce']=$row['serv_introduce_interface'];
  		array_push($json["data"], $tmp);
  	}
  	$json['msg']="";
  	echo json($json);
	
?>