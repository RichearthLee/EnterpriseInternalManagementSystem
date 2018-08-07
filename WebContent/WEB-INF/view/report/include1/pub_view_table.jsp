<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!--[if lte IE 9]> 
<meta http-equiv="X-UA-Compatible" content="IE=8"/>
<![endif]--> 
<link rel="stylesheet" href="<%=request.getContextPath()%>/report1/UIplug/zTree/metroStyle/metroStyle.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/report1/UIplug/zTree/jquery.ztree.all-3.5.min.js"></script>
<%--列表固定表头--%>
<script type="text/javascript" src="<%=request.getContextPath()%>/report1/component/include/table_head.js"></script>	
<%--我的筛选js,列表上的自定义查询借用该js相关功能 所以放在公共页面引用--%>
<script type="text/javascript" src="<%=request.getContextPath()%>/report1/component/include/filter.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/report1/component/include/myCustomScrollbar.js"></script>
<%--我的筛选样式--%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/themes/view/css/filter.css?v=${cssJsVersion}" />
<!-- 行政区划和行业划分 -->
<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/report1/component/include/IndustryAndArea.js"></script> --%>
<!-- <!--帮助组件  -->
<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/component/help/sysHelp.js" ></script> --%>
<%-- <link rel="stylesheet" href="<%=request.getContextPath()%>/component/help/sysHelp.css" /> --%>
<%--保理样式 --%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/report1/themes/factor/css/filter.css?v=${cssJsVersion}" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/report1/themes/factor/css/list.css?v=${cssJsVersion}" />
<%
	String searchData = request.getParameter("ajaxData");
	String searchFlag = request.getParameter("searchFlag");
	if(searchFlag!=null&&searchFlag.equals("true")){
		searchData = new String(searchData.trim().getBytes("ISO-8859-1"), "utf-8");
		searchData = "[{\"customQuery\":\""+searchData+"\"}]";
	}
%>
<script type="text/javascript">
	  $(function(){
	  		var searchData = '<%=searchData%>';
	  		searchData = searchData!=null&&searchData!="null"?eval('('+searchData+')'):[];
	  		for ( var i = 0; i < searchData.length; i++) {
	  			if(searchData[i].hasOwnProperty('customQuery')){
			  		$(".filter-btn-group").find("#filter_in_input").val(searchData[i].customQuery);
	  			}else{
		  			if(i<searchData.length-1){
		  				var zNodes = searchData[i][0];
		  				if(typeof(zNodes)!="undefined" && (zNodes.treeId=="my_filter"&&$("#my_filter").length>0)){
			  				$(function(){
					  			/* var treeObj = $.fn.zTree.getZTreeObj(zNodes.treeId);
					  			var node = treeObj.getNodeByTId(zNodes.tId);
					  			treeObj.checkNode(node, true, true);
					  			addPill(zNodes.tId,node,zNodes.treeId); */
			  				});
		  				}else{
		  					window.onload = function(){ 
		  						$(function(){
		  							//console.log($("#"+zNodes.treeId));
		  							if($("#"+zNodes.treeId).length>0){
			  							var treeObj = $.fn.zTree.getZTreeObj(zNodes.treeId);
							  			var node = treeObj.getNodeByTId(zNodes.tId);
							  			treeObj.checkNode(node, true, true);
							  			addPill(zNodes.tId,node,zNodes.treeId);
			  						}else{
					  					addPillTemp(zNodes.tId,zNodes,zNodes.treeId);
			  						}
		  						});
		  					};
		  				}
		  			}
	  			}
			}
	  });
	 //window.top.getHelp();
// 	 getHelp();
</script>