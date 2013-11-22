<?php 
	include_once 'common_service.php';
	date_default_timezone_set(PRC);
	$action = $_REQUEST["action"];
	if(!isset($_REQUEST["action"])){return;}
	if($action=="list"){
		$page = $_REQUEST["page"];
		$rows = $_REQUEST["rows"];
		$sql = "select * from service ";
		$result = mysql_query($sql,$con);
		$json = array();
  		$json["total"] =  mysql_num_rows($result);
  		$sql = $sql." order by id desc ";
  		if(isset($page)&&isset($rows)){
	  		$sql = $sql." limit ".($page-1)*$rows.",".$rows;
  		}
  		$result = mysql_query($sql,$con);
  		$json["rows"] = array();
  		while($row=mysql_fetch_assoc($result)){
  			$tmp = array();
  			$tmp['id'] = $row['id'];
  			$tmp['serv_title'] = $row['serv_title'];
  			array_push($json["rows"], $tmp);
  		}
  		echo json($json);
	}else if($action=="add"){
		//普通参数获取
		$serv_title = $_REQUEST["serv_title"];
		$serv_introduce = $_REQUEST["serv_introduce"];
		$serv_introduce_interface = addslashes(addslashes($serv_introduce));//解决接口传输富文本数据时带有引号的情况
		$sql = "insert into service(serv_title,serv_introduce,serv_introduce_interface) values ('$serv_title','$serv_introduce','$serv_introduce_interface')";
		mysql_query($sql,$con);
		header("Location:./service_list.php");
	}else if($action=="listAll"){
		$json = sub(1);
		echo json($json);
	//编辑分类
	}else if($action=="delete"){
		$sql = "delete from service where id=".$_REQUEST['id'];
		mysql_query($sql);
		echo "success";
	}else if($action=="edit"){
		//普通参数获取
		$id = $_REQUEST["id"];
		$serv_title = $_REQUEST["serv_title"];
		$serv_introduce = $_REQUEST["serv_introduce"];
		$serv_introduce_interface = addslashes(addslashes($serv_introduce));//解决接口传输富文本数据时带有引号的情况
		$sql = "update service set serv_title='$serv_title',serv_introduce='$serv_introduce',serv_introduce_interface='$serv_introduce_interface' where id='$id' ";
		mysql_query($sql,$con);
		header("Location:./service_list.php");
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

