<?php 
	include_once 'common_service.php';
	date_default_timezone_set(PRC);
	$action = $_REQUEST["action"];
	if(!isset($_REQUEST["action"])){return;}
	if($action=="list"){
		$page = $_REQUEST["page"];
		$rows = $_REQUEST["rows"];
		$sql = "select * from business ";
		$result = mysql_query($sql,$con);
		$json = array();
  		$json["total"] =  mysql_num_rows($result);
  		$sql = $sql." order by id desc ";
  		if(isset($page)&&isset($rows)){
	  		$sql = $sql." limit ".($page-1)*$rows.",".$page*$rows;
  		}
  		$result = mysql_query($sql,$con);
  		$json["rows"] = array();
  		while($row=mysql_fetch_assoc($result)){
  			$tmp = array();
  			$tmp['id'] = $row['id'];
  			$tmp['busi_title'] = $row['busi_title'];
  			$tmp['busi_price'] = $row['busi_price'];
  			array_push($json["rows"], $tmp);
  		}
  		echo json($json);
	}else if($action=="add"){
		//普通参数获取
		$busi_title = $_REQUEST["busi_title"];
		$busi_price = $_REQUEST["busi_price"];
		$busi_introduce = $_REQUEST["busi_introduce"];
		$busi_introduce_interface = addslashes(addslashes($busi_introduce));//解决接口传输富文本数据时带有引号的情况
		$sql = "insert into business(busi_title,busi_price,busi_introduce,busi_introduce_interface) values ('$busi_title','$busi_price','$busi_introduce','$busi_introduce_interface')";
		mysql_query($sql,$con);
		header("Location:./business_list.php");
	}else if($action=="listAll"){
		$json = sub(1);
		echo json($json);
	//编辑分类
	}else if($action=="delete"){
		$sql = "delete from business where id=".$_REQUEST['id'];
		mysql_query($sql);
		echo "success";
	}else if($action=="edit"){
		//普通参数获取
		$id = $_REQUEST["id"];
		$busi_title = $_REQUEST["busi_title"];
		$busi_price = $_REQUEST["busi_price"];
		$busi_introduce = $_REQUEST["busi_introduce"];
		$busi_introduce_interface = addslashes(addslashes($busi_introduce));//解决接口传输富文本数据时带有引号的情况
		$sql = "update business set busi_title='$busi_title',busi_price='$busi_price',busi_introduce='$busi_introduce',busi_introduce_interface='$busi_introduce_interface' where id='$id' ";
		mysql_query($sql,$con);
		header("Location:./business_list.php");
	}
	
	function sub($pid){
		$sqlsub = "select * from secretary_info where pid=$pid";
		$resultsub = mysql_query($sqlsub);
		$childrensub = array();
		while($row=mysql_fetch_assoc($resultsub)){
			$tmpsub = array();
			$tmpsub["id"] =  $row["cid"];
			$tmpsub["text"] =  $row["cat"];
			$subsql = "select * from secretary_info where pid=".$tmpsub["id"];
			$subresult = mysql_query($subsql);
			if(mysql_num_rows($subresult)>0){
				$tmpsub["children"]=sub($tmpsub["id"]);
			}
  			array_push($childrensub, $tmpsub);
  		}
  		return $childrensub;
	}
?>

