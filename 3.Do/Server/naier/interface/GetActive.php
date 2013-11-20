<?php
	include_once 'common.php';
	$currentPage = $_REQUEST['currentPage'];
	$pageRows = $_REQUEST['pageRows'];
  	$sql = "select * from active ";
	$result = mysql_query($sql,$con);
	$json = array();
  	$json["currentPage"] = $currentPage;
	$json["totalPage"] =  ceil(mysql_num_rows($result)/intval($pageRows));
	$sql = $sql." order by id desc limit ".(intval($currentPage)-1)*$pageRows.",".$pageRows;
	$json["data"] = array();
	$result = mysql_query($sql,$con);
	while($row=mysql_fetch_assoc($result)){
  		$tmp = array();
  		$tmp['activeID']=$row['id'];
  		$tmp['activeTitle']=$row['active_title'];
  		if($row["active_poster"]!=""){
	  		$tmp['activePoster']=$base.$row['active_poster'];
  		}else{
	  		$tmp['activePoster']=$row['active_poster'];
  		}
  		$tmp['activeStart']=$row['active_start'];
  		$tmp['activeEnd']=$row['active_end'];
  		$tmp['activeTel']=$row['active_tel'];
  		$tmp['activeDescription']=$row['active_description_interface'];
  		array_push($json["data"], $tmp);
  	}
  	$json['msg']="";
  	echo json($json);
	
?>