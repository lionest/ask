<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="js/jq.js"></script>
<script>
	var hash_url = window.location.hash;
	if(hash_url!=""){
		var errMsg = hash_url.split("#")[1];
		parent.parent.callBack(errMsg);
	}
	</script>
</head>
<body>
</body>
</html>