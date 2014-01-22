<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="../css/common.css" type="text/css" />
<title>管理区域</title>
</head>
<style type="text/css"> 
/* CSS Document */ 

body { 
font: normal 11px auto "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif; 
color: #4f6b72; 
background: #E6EAE9; 
} 

a { 
color: #c75f3e; 
} 

#mytable { 
width: 700px; 
padding: 0; 
margin: 0; 
} 

caption { 
padding: 0 0 5px 0; 
width: 700px; 
font: italic 11px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif; 
text-align: right; 
} 

th { 
padding: 6px 6px 6px 12px; 
background: #CAE8EA no-repeat; 
} 
/*power by www.winshell.cn*/ 
th.nobg { 
border-top: 0; 
border-left: 0; 
border-right: 1px solid #C1DAD7; 
background: none; 
} 

td { 
padding: 0px 6px 0px 12px; 
} 
/*power by www.winshell.cn*/ 

td.alt { 
background: #F5FAFA; 
color: #797268; 
} 

th.spec { 
border-left: 1px solid #C1DAD7; 
border-top: 0; 
background: #fff no-repeat; 
font: bold 10px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif; 
} 

th.specalt { 
border-left: 1px solid #C1DAD7; 
border-top: 0; 
background: #f5fafa no-repeat; 
font: bold 10px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif; 
color: #797268; 
} 
/*---------for IE 5.x bug*/ 
html>body td{ font-size:13px;} 
body,td,th { 
font-family: 宋体, Arial; 
font-size: 13px; 
} 
.btn1_mouseover{
BORDER-RIGHT: #7EBF4F 1px solid; PADDING-RIGHT: 2px; BORDER-TOP: #7EBF4F 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#ffffff, EndColorStr=#C1DAD7); BORDER-LEFT: #7EBF4F 1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px; BORDER-BOTTOM: #7EBF4F 1px solid
}

</style> 

<body>
<script>
var id = 1;
function findAllChecBox(){
  var ids = document.getElementById("ids");
   var r = document.getElementsByName("entityIds");
           if(id==1){
       for(var i=0;i<r.length;i++){
	     r[i].checked=true;
	       id=2;
		 }
	   }else{
	     for(var i=0;i<r.length;i++){
	     r[i].checked=false;
	       id=1
		 }
	   }
}
</script>
<div id="man_zone">
  <div style="margin:10px;">
  <div>
  <select style="margin-bottom:5px;">
    <option>用户名</option>
  </select>
  <a href="#" ><img src="../images/query.jpg"></a>
  </div>
  </div>
 <div class="pageContent" align="center">
    <table class="table" width="1200" layoutH="138">
        <thead>
            <tr>
                <th width="56">
                  <input type="checkbox" group="entityIds" class="checkboxCtrl" onclick="findAllChecBox();"/>
                </th>
          <th width="470" orderField="name" class="asc">回复内容</th>
          <th width="93">用户ID</th>
          <th width="81">问题ID</th>
		  <th width="90">回答时间</th>
           <th width="103" align="center">修改时间</th>
           <th width="104" align="center">是否被采纳</th>
     <th width="68" align="center">
                   状态</th>
  <th width="95">
                    操作                </th>
          </tr>
        </thead>
        <tbody>
        <c:forEach items="" var="user" varStatus="s">
            <tr target="sid_user" rel="1" >
                <td height="30px">
                    <input name="entityIds" value="" type="checkbox" />
                </td>
                <td>&nbsp;</td>
               
          <td>
                    <c:if test=""></c:if></td>
                <td>
                   <c:if test=""></c:if><c:if test=""></c:if>
                </td>
                <td>
                <c:if test=""></c:if></td>
                <td>&nbsp;</td>
              <td>
                </td>
                <td>
                </td>
                <td><a href="#">删除</a> <a href="#">回复</a></td>
            </tr>
            </c:forEach>
        </tbody>
    </table>
    <div class="panelBar">
        <div class="pages" align="right" style="padding-right:50px; margin-top:10px;">
        <jsp:include page="../pager.jsp" />
        </div>
    </div>
</div>




</div>
</body>
</html>
