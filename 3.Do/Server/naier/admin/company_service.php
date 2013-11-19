<?php
include_once 'common_service.php';
$action = $_REQUEST["action"];
if(!isset($_REQUEST["action"])){return;}
//系统用户一览页面请求的数据
if($action=="edit"){
	$company_advise_tel = $_REQUEST["company_advise_tel"];
	$company_complain_tel = $_REQUEST["company_complain_tel"];
	$company_secretary_tel = $_REQUEST["company_secretary_tel"];
	$company_keeper_tel = $_REQUEST["company_keeper_tel"];
	$company_address = $_REQUEST["company_address"];
	$company_email = $_REQUEST["company_email"];
	$company_qq = $_REQUEST["company_qq"];
	$company_about = $_REQUEST["company_about"];

	$company_about_interface = addslashes(addslashes($company_about));//解决接口传输富文本数据时带有引号的情况

	$sql = "update company set company_advise_tel='$company_advise_tel',company_complain_tel='$company_complain_tel',company_secretary_tel='$company_secretary_tel',company_keeper_tel='$company_keeper_tel',company_address='$company_address',company_email='$company_email',company_qq='$company_qq',company_about='$company_about',company_about_interface='$company_about_interface'";
	mysql_query($sql);
	
	$time = strval(time());//当前时间戳
	
	$company_pictures1 = "";
	if(isset($_FILES["imgsrc1"])&&$_FILES["imgsrc1"]["name"]!=""){
		$arr = explode(".",$_FILES["imgsrc1"]["name"]);
		$filetype = $arr[count($arr)-1];
		$filename = $time."1.".$filetype;
		move_uploaded_file($_FILES["imgsrc1"]["tmp_name"],"./upload/".$filename);
		$imgurl = "/admin/upload/".$filename;
		$company_pictures1 = $imgurl;
		$sql = "update company set company_pictures1='$company_pictures1'";
		mysql_query($sql);
	}
	$company_pictures2 = "";
	if(isset($_FILES["imgsrc2"])&&$_FILES["imgsrc2"]["name"]!=""){
		$arr = explode(".",$_FILES["imgsrc2"]["name"]);
		$filetype = $arr[count($arr)-1];
		$filename = $time."2.".$filetype;
		move_uploaded_file($_FILES["imgsrc2"]["tmp_name"],"./upload/".$filename);
		$imgurl = "/admin/upload/".$filename;
		$company_pictures2 = $imgurl;
		$sql = "update company set company_pictures2='$company_pictures2'";
		mysql_query($sql);
	}
	$company_pictures3 = "";
	if(isset($_FILES["imgsrc3"])&&$_FILES["imgsrc3"]["name"]!=""){
		$arr = explode(".",$_FILES["imgsrc3"]["name"]);
		$filetype = $arr[count($arr)-1];
		$filename = $time."3.".$filetype;
		move_uploaded_file($_FILES["imgsrc3"]["tmp_name"],"./upload/".$filename);
		$imgurl = "/admin/upload/".$filename;
		$company_pictures3 = $imgurl;
		$sql = "update company set company_pictures3='$company_pictures3'";
		mysql_query($sql);
	}
	$company_pictures4 = "";
	if(isset($_FILES["imgsrc4"])&&$_FILES["imgsrc4"]["name"]!=""){
		$arr = explode(".",$_FILES["imgsrc4"]["name"]);
		$filetype = $arr[count($arr)-1];
		$filename = $time."4.".$filetype;
		move_uploaded_file($_FILES["imgsrc4"]["tmp_name"],"./upload/".$filename);
		$imgurl = "/admin/upload/".$filename;
		$company_pictures4 = $imgurl;
		$sql = "update company set company_pictures4='$company_pictures4'";
		mysql_query($sql);
	}
	
	header("location:./company_edit.php");
}
?>

