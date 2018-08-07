<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
/*  String blockType= (String)request.getParameter("blockType");
 String placeholder= (String)request.getParameter("placeholder");
 if("".equals(placeholder) || placeholder==null){
 */
 /* placeholder ="智能搜索"; */
 //}
//头部只有自定义筛选的情况（无搜索框）
//if("1".equals(blockType)){%>
	<div class="col-xs-12 column mysearch-div" id="pills">
		<div id="filter_list" class="pull-left">
			<ul class="ztree" id="my_filter" style="-moz-user-select: none;">
			</ul>
			<!--更多下拉 -->
			<div class="dropdown pull-left hide">
			    <button type="button" class="btn dropdown-toggle " id="dropdownMenu" data-toggle="dropdown">更多
			        <span class="caret"></span>
			    </button>
			    <ul id="my_more" class="dropdown-menu ztree" role="menu">
			       
			    </ul>
			</div>
			<div class="pull-left btn btn-link add-filter" onclick="showFilterBox();">
				<i class="i i-add"></i><span>定义筛选</span>
			</div>
		</div>
	</div>
<%//}else if("2".equals(blockType)){ //仅右侧有搜索框的情况，占3列。左侧由引用页面自定义%>
<%-- 	<div class="col-xs-3 column znsearch-div pull-right">
		<div class="input-group pull-right">
			<i class="i i-fangdajing"></i>
			<input type="text" class="form-control" id="filter_in_input" placeholder="<%= placeholder%>">
			<span class="input-group-addon" id="filter_btn_search">搜索</span>
		</div>
	</div> --%>
<%//}else if("3".equals(blockType)){//头部左侧自定义筛选，右侧搜索框的情况%>
	<%-- <div class="col-xs-9 column mysearch-div" id="pills">
		<div id="filter_list" class="pull-left">
			<ul class="ztree" id="my_filter" style="-moz-user-select: none;">
			</ul>
			<!--更多下拉 -->
			<div class="dropdown pull-left hide">
			    <button type="button" class="btn dropdown-toggle " id="dropdownMenu" data-toggle="dropdown">更多
			        <span class="caret"></span>
			    </button>
			    <ul id="my_more" class="dropdown-menu ztree" role="menu">
			       
			    </ul>
			</div>
			<div class="pull-left btn btn-link add-filter" onclick="showFilterBox();">
				<i class="i i-add"></i><span>定义筛选</span>
			</div>
		</div>
	</div>
	<div class="col-xs-3 column znsearch-div pull-right">
		<div class="input-group pull-right">
			<i class="i i-fangdajing"></i>
			<input type="text" class="form-control" id="filter_in_input" placeholder="<%= placeholder%>">
			<span class="input-group-addon" id="filter_btn_search">搜索</span>
		</div>
	</div> --%>
<%//}else if("4".equals(blockType)){//头部左侧没有自定义筛选的情况，右侧搜索框的情况%>
<%-- 	<div class="col-xs-9 column mysearch-div" id="pills">
		<div id="filter_list" class="pull-left">
			<ul class="ztree" id="my_filter" style="-moz-user-select: none;">
			</ul>
		
		</div>
	</div>
	<div class="col-xs-3 column znsearch-div pull-right">
		<div class="input-group pull-right">
			<i class="i i-fangdajing"></i>
			<input type="text" class="form-control" id="filter_in_input" placeholder="<%= placeholder%>">
			<span class="input-group-addon" id="filter_btn_search">搜索</span>
		</div>
	</div> --%>
<%//}%>
