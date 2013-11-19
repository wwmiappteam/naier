<?php
	//获取数据库连接
	$con = mysql_connect($cfg_dbhost,$cfg_dbuser,$cfg_dbpwd);
	if (!$con){
  		die('Could not connect: ' . mysql_error());
  	}
  	mysql_query("set names ".$cfg_db_language);
  	//选择数据库
  	mysql_select_db($cfg_dbname, $con);
  	
  	//查询数据并且组装为datagrid所需格式
  	function query_page($con,$sql,$param,$page,$rows,$order){
  		$json = array();
  		$index = 0 ;
  		if($param!=null){
	  		foreach ($param as $key=>$value) {
	  			if(count($param)>0&&$index==0){
	  				$sql=$sql." WHERE ".$key.$value;
	  			}else{
	  			$sql=$sql." AND ".$key.$value;
	  			$index++;
	  			}
	  		}
  		}
  		$result = mysql_query($sql,$con);
  		$json["total"] =  mysql_num_rows($result);
  		if(isset($page)&&isset($rows)){
	  		$sql = $sql." limit ".($page-1)*$rows.",".$page*$rows;
  		}
  		if(isset($order)){
  			$sql = $sql." ".$order;
  		}
  		$result = mysql_query($sql,$con);
  		$json["rows"] = array();
  		while($row=mysql_fetch_assoc($result)){
  			array_push($json["rows"], $row);
  		}
  		return json($json);
	}
	
	//查询数据并且组装为datagrid所需格式
  	function shopgoods_query_page($con,$sql,$param,$page,$rows,$order){
  		$json = array();
  		$index = 0 ;
  		if($param!=null){
	  		foreach ($param as $key=>$value) {
	  			if(count($param)>0&&$index==0){
	  				$sql=$sql." WHERE ".$key.$value;
	  				continue;
	  			}
	  			$sql=$sql." AND ".$key.$value;
	  			$index++;
	  		}
  		}
  		$result = mysql_query($sql,$con);
  		$json["total"] =  mysql_num_rows($result);
  		if(isset($order)){
  			$sql = $sql." ".$order;
  		}
  		if(isset($page)&&isset($rows)){
	  		$sql = $sql." limit ".($page-1)*$rows.",".$page*$rows;
  		}
  		$result = mysql_query($sql,$con);
  		$json["rows"] = array();
  		while($row=mysql_fetch_assoc($result)){
  			if($row["ishot"]==1){
  				$row["ishot"]="是";
  			}else{
  				$row["ishot"]="否";
  			}
  			if($row["isnew"]==1){
  				$row["isnew"]="是";
  			}else{
  				$row["isnew"]="否";
  			}
  			
  			//所属活动
  			if($row["saleid"]!=""){
  				$saleid = $row["saleid"];
  				$tmpsql = "select title from shop_sales where id=$saleid";
  				$tmpresult = mysql_query($tmpsql);
  				$tmprow=mysql_fetch_assoc($tmpresult);
  				$row['saleid_name'] = $tmprow["title"];
  			}
  			//所属类别
  			if($row["cid"]){
  				$cid = $row["cid"];
  				$tmpsql = "select cat from shop_category where cid=$cid";
  				$tmpresult = mysql_query($tmpsql);
  				$tmprow=mysql_fetch_assoc($tmpresult);
  				$row['cid_name'] = $tmprow["cat"];
  			}
  			//所属品牌
  			if($row["bid"]){
  				$bid = $row["bid"];
  				$tmpsql = "select brand from shop_brand where bid=$bid";
  				$tmpresult = mysql_query($tmpsql);
  				$tmprow=mysql_fetch_assoc($tmpresult);
  				$row['bid_name'] = $tmprow["brand"];
  			}
  			$row["canshu"]="";
  			$row["body"]="";
  			$row["memo"]="";
  			$row["prop1"]="";
  			array_push($json["rows"], $row);
  		}
  		return json($json);
	}
	
	/**************************************************************
	 *  使用特定function对数组中所有元素做处理
	 *  @param  string  &$array     要处理的字符串
	 *  @param  string  $function   要执行的函数
	 *  @return boolean $apply_to_keys_also     是否也应用到key上
	 *  @access public
	 *************************************************************/
	function arrayRecursive(&$array, $function, $apply_to_keys_also = false)
	{
	    static $recursive_counter = 0;
	    if (++$recursive_counter > 1000) {
	        die('possible deep recursion attack');
	    }
	    foreach ($array as $key => $value) {
	        if (is_array($value)) {
	            arrayRecursive($array[$key], $function, $apply_to_keys_also);
	        } else {
	            $array[$key] = $function($value);
	        }
	  
	        if ($apply_to_keys_also && is_string($key)) {
	            $new_key = $function($key);
	            if ($new_key != $key) {
	                $array[$new_key] = $array[$key];
	                unset($array[$key]);
	            }
	        }
	    }
	    $recursive_counter--;
	}
  
	/**************************************************************
	 *  将数组转换为JSON字符串（兼容中文）
	 *  @param  array   $array      要转换的数组
	 *  @return string      转换得到的json字符串
	 *  @access public
	 *************************************************************/
	function json($array) {
	    arrayRecursive($array, 'urlencode', true);
	    $json = json_encode($array);
	    return urldecode($json);
	}
	
?>