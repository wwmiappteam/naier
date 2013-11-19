<?php 
	include_once 'common_service.php';
	$action = $_REQUEST["action"];
	if(!isset($_REQUEST["action"])){return;}
	//分类一览页面请求的数据
	if($action=="list"){
		$id = $_REQUEST["id"];
		$json = array();
		if(!isset($id)){
			$sql = "select * from shop_category where pid=1";
		}else{
			$sql = "select * from shop_category where pid=$id";
		}
		$result = mysql_query($sql);
		while($row=mysql_fetch_assoc($result)){
			$tmp = array();
			$tmp["id"] =  $row["cid"];
			$tmp["cat"] =  $row["cat"];
			$tmp["text"] =  $row["cat"];
			if($row["pid"]!=""){
				$tmp["pId"] =  $row["pid"];
			}
			$subsql = "select * from shop_category where pid=".$tmp['id'];
			$subresult = mysql_query($subsql);
			if(mysql_num_rows($subresult)>0){
				$tmp["state"]="closed";
			}
  			array_push($json, $tmp);
  		}
		echo json($json);
	//添加分类
	}else if($action=="add"){
		$cat = $_REQUEST["cat"];
		$pid = $_REQUEST["pid"];
		$sql = "insert into shop_category(cat,pid) values('$cat','$pid')";
		mysql_query($sql,$con);
		header("Location:./shopcategory_list.php");
	}else if($action=="delete"){
		$sql = "delete from shop_category where cid=".$_REQUEST['id'];
		mysql_query($sql);
		echo "success";
	}else if($action=="listAll"){
		$json = sub(1);
		echo json($json);
	//编辑分类
	}else if($action=="edit"){
		$id = $_REQUEST["id"];
		$cat = $_REQUEST["cat"];
		$sql = "update shop_category set cat='$cat' where cid='$id'";
		mysql_query($sql,$con);
		header("Location:./shopcategory_list.php");
	}
	function sub($pid){
		$sqlsub = "select * from shop_category where pid=$pid";
		$resultsub = mysql_query($sqlsub);
		$childrensub = array();
		while($row=mysql_fetch_assoc($resultsub)){
			$tmpsub = array();
			$tmpsub["id"] =  $row["cid"];
			$tmpsub["text"] =  $row["cat"];
			$subsql = "select * from shop_category where pid=".$tmpsub["id"];
			$subresult = mysql_query($subsql);
			if(mysql_num_rows($subresult)>0){
				$tmpsub["children"]=sub($tmpsub["id"]);
			}
  			array_push($childrensub, $tmpsub);
  		}
  		return $childrensub;
	}
?>

