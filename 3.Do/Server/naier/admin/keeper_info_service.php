<?php 
	include_once 'common_service.php';
	date_default_timezone_set(PRC);
	$action = $_REQUEST["action"];
	if(!isset($_REQUEST["action"])){return;}
	if($action=="list"){
		$page = $_REQUEST["page"];
		$rows = $_REQUEST["rows"];
		$sql = "select * from keeper_info ";
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
  			if($row['keeper_photo']!=""){
  				$row['keeper_photo'] = $base.$row['keeper_photo'];
  			}
  			if($row['keeper_ispush']=="true"){
  				$row['keeper_ispush_str'] = "是";
  			}else{
  				$row['keeper_ispush_str'] = "否";
  			}
  			$subsql = "select type_name from keeper_type where id='".$row['keeper_type_id']."' ";
  			$subresult = mysql_query($subsql);
  			$subrow = mysql_fetch_assoc($subresult);
  			$row['type_name'] = $subrow['type_name'];
  			array_push($json["rows"], $row);
  		}
  		echo json($json);
	}else if($action=="add"){
		//普通参数获取
		$keeper_name = $_REQUEST["keeper_name"];
		$keeper_type_id = $_REQUEST["keeper_type_id"];
		$keeper_gender = $_REQUEST["keeper_gender"];
		$keeper_age = $_REQUEST["keeper_age"];
		$keeper_experience = $_REQUEST["keeper_experience"];
		$keeper_level = $_REQUEST["keeper_level"];
		$keeper_special = $_REQUEST["keeper_special"];
		$keeper_introduce = $_REQUEST["keeper_introduce"];
		$keeper_professional = $_REQUEST["keeper_professional"];
		$keeper_attitude = $_REQUEST["keeper_attitude"];
		$keeper_hardworking = $_REQUEST["keeper_hardworking"];
		$keeper_attentive = $_REQUEST["keeper_attentive"];
		$keeper_ispush = $_REQUEST["keeper_ispush"]=="true"?"true":"false";
		
		$index =1;//该参数用于同时上传多个文件时，将其文件名区分开
		$time = strval(time());//当前时间戳
		$imgurl = "";
		if(isset($_FILES["keeper_photo"])&&$_FILES["keeper_photo"]["name"]!=""){
			$arr = explode(".",$_FILES["keeper_photo"]["name"]);
			$filetype = $arr[count($arr)-1];
			$filename = $time.strval($index).".".$filetype;
			move_uploaded_file($_FILES["keeper_photo"]["tmp_name"],"./upload/".$filename);
			$imgurl = "/admin/upload/".$filename;
		}
		$sql = "insert into keeper_info(keeper_name,keeper_type_id,keeper_gender,keeper_age,keeper_experience,keeper_level,keeper_special,keeper_introduce,
		keeper_professional,keeper_attitude,keeper_hardworking,keeper_attentive,keeper_ispush,keeper_photo) values('$keeper_name',$keeper_type_id,'$keeper_gender','$keeper_age','$keeper_experience','$keeper_level','$keeper_special','$keeper_introduce',
		'$keeper_professional','$keeper_attitude','$keeper_hardworking','$keeper_attentive','$keeper_ispush','$imgurl')";
		
		
		$timefmt = date("Y-m-d H:i");
		mysql_query($sql,$con);
		header("Location:./keeper_info_list.php");
	}else if($action=="listAll"){
		$json = sub(1);
		echo json($json);
	//编辑分类
	}else if($action=="delete"){
		$sql = "delete from keeper_info where id=".$_REQUEST['id'];
		mysql_query($sql);
		echo "success";
	}else if($action=="edit"){
		//普通参数获取
		$id = $_REQUEST["id"];
		$keeper_name = $_REQUEST["keeper_name"];
		$keeper_type_id = $_REQUEST["keeper_type_id"];
		$keeper_gender = $_REQUEST["keeper_gender"];
		$keeper_age = $_REQUEST["keeper_age"];
		$keeper_experience = $_REQUEST["keeper_experience"];
		$keeper_level = $_REQUEST["keeper_level"];
		$keeper_special = $_REQUEST["keeper_special"];
		$keeper_introduce = $_REQUEST["keeper_introduce"];
		$keeper_professional = $_REQUEST["keeper_professional"];
		$keeper_attitude = $_REQUEST["keeper_attitude"];
		$keeper_hardworking = $_REQUEST["keeper_hardworking"];
		$keeper_attentive = $_REQUEST["keeper_attentive"];
		$keeper_ispush = $_REQUEST["keeper_ispush"]=="true"?"true":"false";
		
		$index =1;//该参数用于同时上传多个文件时，将其文件名区分开
		$time = strval(time());//当前时间戳
		$imgurl = "";
		if(isset($_FILES["keeper_photo"])&&$_FILES["keeper_photo"]["name"]!=""){
			$arr = explode(".",$_FILES["keeper_photo"]["name"]);
			$filetype = $arr[count($arr)-1];
			$filename = $time.strval($index).".".$filetype;
			move_uploaded_file($_FILES["keeper_photo"]["tmp_name"],"./upload/".$filename);
			$imgurl = "/admin/upload/".$filename;
			$subsql = ",keeper_photo='".$imgurl."' ";
		}
		$sql = "update keeper_info  set keeper_name='$keeper_name',keeper_type_id=$keeper_type_id,keeper_gender='$keeper_gender',keeper_age='$keeper_age',keeper_experience='$keeper_experience',keeper_level='$keeper_level',keeper_special='$keeper_special',keeper_introduce='$keeper_introduce',
		keeper_professional='$keeper_professional',keeper_attitude='$keeper_attitude',keeper_hardworking='$keeper_hardworking',keeper_attentive='$keeper_attentive',keeper_ispush='$keeper_ispush' 	 ".$subsql." where id=$id ";
		
		$timefmt = date("Y-m-d H:i");
		mysql_query($sql,$con);
		header("Location:./keeper_info_list.php");
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

