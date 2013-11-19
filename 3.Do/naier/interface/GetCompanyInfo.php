<?php
	//7、根据活动查询商品列表
	include_once 'common.php';
	$sql = "select * from company  limit 0,1";
	$result = mysql_query($sql,$con);
	$company=mysql_fetch_assoc($result);
	
	$json = array();
	$json['data'] = array();
  	$json['data']['picturesList']=array();
  	if($company['company_pictures1']!=""){
	  	array_push($json['data']['picturesList'], $base.$company['company_pictures1']);
  	}
  	if($company['company_pictures2']!=""){
	  	array_push($json['data']['picturesList'], $base.$company['company_pictures2']);
  	}
  	if($company['company_pictures3']!=""){
	  	array_push($json['data']['picturesList'], $base.$company['company_pictures3']);
  	}
  	if($company['company_pictures4']!=""){
	  	array_push($json['data']['picturesList'], $base.$company['company_pictures4']);
  	}
  	$json['data']['advise_tel'] = $company['company_advise_tel'];
  	$json['data']['complain_tel'] = $company['company_complain_tel'];
  	$json['data']['secretary_tel'] = $company['company_secretary_tel'];
  	$json['data']['keeper_tel'] = $company['company_keeper_tel'];
  	$json['data']['address'] = $company['company_address'];
  	$json['data']['email'] = $company['company_email'];
  	$json['data']['qq'] = $company['company_qq'];
  	$json['data']['about'] = $company['company_about_interface'];
  	$json['msg']="";
  	echo json($json);
	
?>