
<%@ page contentType="text/html" language="java" pageEncoding="utf-8"%>

<style>
.{font:12px;}
.listBox{
	border:1px solid #999999;
	height:400px;
	overflow-y:auto;
}
.listBox .listItem, .listBox .listItem_Hover, .listBox .listItem_Selected{
	cursor:pointer;
	padding:4px 4px 3px 4px;
	margin:0px;
	color:#000000;
	background:;
	border:1px solid #FFFFFF;
	height:20px;
}
.listBox .listItem_Hover{
	color:#0000FF;
	border:1px solid #C6CDBE;
}
.listBox .listItem_Selected{
	background:#E8EFDF;
	border:1px solid #C6CDBE;
}
.listBox .listItemHasChild{
	background:url() no-repeat 100% 50%;
	padding:0px;
	margin:0px;
}

#selectPanel{
	width:700px;
}
</style>
<table id="selectPanel" cellpadding="0" cellspacing="2">
	<tr>
		<td><div id="lb_1" class="listBox" style="display:none;"></div></td>
		<td><div id="lb_2" class="listBox" style="display:none;"></div></td>
		<td><div id="lb_3" class="listBox" style="display:none;"></div></td>
		<td><div id="lb_4" class="listBox" style="display:none;"></div></td>
		<td><div id="lb_5" class="listBox" style="display:none;"></div></td>
	</tr>
</table>
<input type="hidden" id="domainId"/>
您选择的问题种类为：<input type="text" id="domainName" >
<!-- 
<form id="form1" name="form1" method="post" action="">
<input name="id" type="text" class="inputclass" />
<input type="name" id="domainId" />
</form>
 -->
<script language="javascript">

//初始化分类项
function initSelectPanel()
{
	showListItems(1, 0);
	//showListItems(2, 2668);	//修改时对应分类要修改的值
	//Id_2001.className = "listItem_Selected";//修改时对应分类要修改的值
	//Id_2668.className = "listItem_Selected";//修改时对应分类要修改的值
}
//分类项 - 鼠标事件
function listItemOnMouseOver(listItem)
{
	if(listItem.className != "listItem_Selected"){
		listItem.className += "_Hover";
	}
}
//分类项 - 鼠标事件
function listItemOnMouseOut(listItem)
{
	if(listItem.className != "listItem_Selected"){
		listItem.className = listItem.className.replace("_Hover", "");
	}
}
//分类项被点选择
function listItemOnClick(listItem)
{
	var cateId=listItem.getAttribute("cateid");
	var cate = cateArr[cateId];
	var depth = cate.getCateDepth();
	clearNextListBoxes(depth);
	showListItems(depth + 1, cate.id);

	//清理样式listItem.className
	var listBox = document.getElementById("lb_" + depth);
	if(!listBox) return;
	var items = listBox.getElementsByTagName("div");
	for(var i=0; i<items.length; i++){
		if(items[i].className == "listItem_Selected") items[i].className = "listItem";
	}
	listItem.className = "listItem_Selected";
	//alert(cate.id);
	var s = document.getElementById("Id_"+cate.id).innerHTML.replace(/<[^>]+>/g,"");
	document.getElementById("domainId").value=cate.id;
	document.getElementById("domainName").value=s;
	//this.form1.id.value=depth+","+cate.id;
}
//显示 depth 深度的分类列表
function showListItems(depth, fid){
	var listBox = document.getElementById("lb_" + depth);
	if(!listBox) return;

	listBox.innerHTML = "";
	var item = cateArr[fid];
	//alert(item);
	var listItemIds = item.childIds;
	var listItemCount = listItemIds.length;
	if(listItemCount == 0) return;
		
	listBox.style.display = "";
	if(item.html != ""){
		listBox.innerHTML = item.html;
		return;
	}
	for(var i=0; i<listItemCount; i++){
		var cate = cateArr[listItemIds[i]];
		var title = cate.title;
		if(cate.childIds.length > 0){
			title = "<div class='listItemHasChild'>" + title + "</div>";
		}
		listBox.innerHTML += "<div cateid='" + cate.id + "' id=Id_" + cate.id + " class='listItem' onmouseout='javascript:listItemOnMouseOut(this);' onmouseover='javascript:listItemOnMouseOver(this);' onclick='javascript:listItemOnClick(this);'>" + title + "</div>";
	}
	item.html = listBox.innerHTML; //缓存 html 代码
}
//清空 所有子级 选择框
function clearNextListBoxes(depth)
{
	for(var i=depth+1; i<=5; i++){
		var listBox = document.getElementById("lb_" + i);
		if(!listBox) return;

		listBox.innerHTML = "";
		listBox.style.display = "none";
	}
}
</script>

<script language="javascript">
var cateArr = new Array();

function TCategory(fid, id, title)
{
	this.fid = fid;
	this.id = id;
	this.title = title;
	this.childIds = new Array();
	this.getCateDepth = getCateDepth;
	this.html = ""; //被选中时，下级listBox内的HTML，省得每次计算
	
	if(fid > -1){
		cateArr[fid].childIds.push(id);
	}
}
function getCateDepth()
{
	var depth = 1;
	var c = this;
	while(c.fid >0){
		depth++;
		c = cateArr[c.fid];
	}
	return depth;
}

</script>

<script language="javascript">
cateArr[0] = new TCategory(-1, 0, 'root');

var domains=${domainJson};
for(var i=0;i<domains.length;i++){
	var domain=domains[i];
	cateArr[parseInt(domain.id)]=	new TCategory(domain.parentId, domain.id, domain.name);
}
initSelectPanel();
</script>


