<?php 
	include_once 'common_service.php';
	date_default_timezone_set(PRC);
	$action = $_REQUEST["action"];
	if(!isset($_REQUEST["action"])){return;}
	if($action=="list"){
		$page = $_REQUEST["page"];
		$rows = $_REQUEST["rows"];
		$sql = "select * from active ";
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
  			if($row['active_poster']!=""){
  				$row['active_poster']=$base.$row['active_poster'];
  			}
  			array_push($json["rows"], $row);
  		}
  		echo json($json);
	}else if($action=="add"){
		//普通参数获取
		$active_title = $_REQUEST["active_title"];
		$active_start = $_REQUEST["active_start"];
		$active_end = $_REQUEST["active_end"];
		$active_tel = $_REQUEST["active_tel"];
		$active_description = $_REQUEST["active_description"];
		$active_description_interface = addslashes(addslashes($active_description));//解决接口传输富文本数据时带有引号的情况
		
		$index =1;//该参数用于同时上传多个文件时，将其文件名区分开
		$time = strval(time());//当前时间戳
		$imgurl = "";
		if(isset($_FILES["active_poster"])&&$_FILES["active_poster"]["name"]!=""){
			$arr = explode(".",$_FILES["active_poster"]["name"]);
			$filetype = $arr[count($arr)-1];
			$filename = $time.strval($index).".".$filetype;
			move_uploaded_file($_FILES["active_poster"]["tmp_name"],"./upload/".$filename);
			$imgurl = "/admin/upload/".$filename;
		}
		
		$sql = "insert into active(active_title,active_start,active_end,active_tel,active_description,active_description_interface,active_poster)
		 values ('$active_title','$active_start','$active_end','$active_tel','$active_description','$active_description_interface','$imgurl')";
		mysql_query($sql,$con);
		header("Location:./active_list.php");
	}else if($action=="listAll"){
		$json = sub(1);
		echo json($json);
	//编辑分类
	}else if($action=="delete"){
		$sql = "delete from active where id=".$_REQUEST['id'];
		mysql_query($sql);
		echo "success";
	}else if($action=="edit"){
		//普通参数获取
		$id = $_REQUEST["id"];
		$active_title = $_REQUEST["active_title"];
		$active_start = $_REQUEST["active_start"];
		$active_end = $_REQUEST["active_end"];
		$active_tel = $_REQUEST["active_tel"];
		$active_description = $_REQUEST["active_description"];
		$active_description_interface = addslashes(addslashes($active_description));//解决接口传输富文本数据时带有引号的情况
		
		$index =1;//该参数用于同时上传多个文件时，将其文件名区分开
		$time = strval(time());//当前时间戳
		$imgurl = "";
		$subsql = "";
		if(isset($_FILES["active_poster"])&&$_FILES["active_poster"]["name"]!=""){
			$arr = explode(".",$_FILES["active_poster"]["name"]);
			$filetype = $arr[count($arr)-1];
			$filename = $time.strval($index).".".$filetype;
			move_uploaded_file($_FILES["active_poster"]["tmp_name"],"./upload/".$filename);
			$imgurl = "/admin/upload/".$filename;
			$subsql = ",active_poster='$imgurl' ";
		}
		
		$sql = "update active set active_title='$active_title',active_start='$active_start',active_end='$active_end',active_tel='$active_tel',active_description='$active_description',active_description_interface='$active_description_interface' ".$subsql." where id=$id";
		mysql_query($sql,$con);
		header("Location:./active_list.php");
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

