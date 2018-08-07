<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>

 <%
 	//String reportId = request.getParameter("reportId");
	String reporttype=(String)request.getAttribute("reporttype");
	/* String funRoleType = request.getParameter("funRoleType"); */
    //String type = request.getParameter("type");
	String uid = (String)request.getAttribute("uid");
	//String currMonth = request.getParameter("currMonth");
	//String currDate = request.getParameter("currDate");
	//String id = request.getParameter("id");
	//String lastMonth = request.getParameter("lastMonth");
	String preIndex="designPreviewIndex.jsp";
    if(reporttype.equals("C")){
   	  preIndex="designPreviewIndexC.jsp";
    }
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>报表入口页面</title>
	<script type="text/javascript" src="<%=request.getContextPath()%>/report1/layout/view/js/jquery-1.11.2.min.js"></script>
	<%-- <link rel="stylesheet" href="<%=contextPath%>/component/report/css/MfReportEntrance.css?v=${cssJsVersion}" />
	<link rel="stylesheet" href="<%=contextPath%>/themes/factor/css/search_filter.css" />
	<script type="text/javascript" src="<%=contextPath%>/themes/factor/js/search_filter.js"></script>
	<script type="text/javascript" src="<%=contextPath%>/component/finance/js/cw_common.js"></script> --%>
<body class="overflowHidden">
	<table style="width: 100%; height: 100%; table-layout: fixed;" border="0" cellspacing="0" cellpadding="0">
		<tbody>
			 <tr>
				<td style="overflow: auto;padding-left:5px;padding-right:5px;">
					<table width="100%" height="100%" style="table-layout: fixed;">
						<tbody>
							<tr>
								<td  ><div class="searchlist" style="-ms-overflow-x: auto;margin-top:5px;">
										数据请求表单开始
												<div style="width:100%;height:5px;background: #F1FAF9;margin-top: 40px;">
												
												</div>
												
												<a class="ui-btn ui-btn-sp"  style="margin-top:0px;float:right;" id="filter-submit" onclick="ReportEntrance.openSaveSqlCondition()">查询</a>
												<div>
												<form id="cwListForm" style="float:left;width:93%;margin-bottom:0px;" >
												<ul class="filter-list" id="more-conditions" style="padding-left: 0px; border-bottom: 1px solid #ddd;margin-bottom:0px;" >
												<!--<li class="li-one-wrap st3" id="brNoLi" style="display: none;" showFlag="0">
													<label for="#filter-fromSubject" class=" st1 li-lable-open" id="brNoLabel">部门</label>
													<input type="hidden" id="brNo" saveFlag="1" noFlag="1">
													<input type="hidden" class="form-control form-warp form-input-width" name="brName" id="brName" saveFlag="0" noFlag="0" autocomplete="off">
													<div class="st2 clearfix" id="brNoDiv" >
														&nbsp;</br>&nbsp;
													</div>
													<div class="btns btns_div">
														<a href="#" id="conditions-trigger" class="conditions-trigger-open2 conditions-expand" tabindex="-1" data-flag="0" onclick="ReportEntrance.getMoreData(this);">更多<b></b></a>
														<button  type="button" id="brNoButton" class="btns_button">+多选</button>
													</div>
												</li>
												<li class="li-one-wrap st3" id="opNoLi" style="display: none;" showFlag="0">
												<label for="#filter-fromSubject" class=" st1 li-lable-open" id="opNoLable">操作员</label>
												<input type="hidden" id="opNo" saveFlag="1" noFlag="1">
												<input type="hidden" class="form-control form-warp form-input-width" name="opName" id="opName" saveFlag="0" noFlag="0"  autocomplete="off">
												<div class="st2 clearfix" id="opNoButtonDiv">
													&nbsp;</br>&nbsp;
												</div>
												<div class="btns btns_div">
													<a href="#" id="conditions-trigger" class="conditions-trigger-open2 conditions-expand" tabindex="-1" onclick="ReportEntrance.getMoreData(this);">更多<b></b></a>
													<button  type="button" id="opNoButton"  class="btns_button">+多选</button>
												</div>
												</li>
												<li class="li-one-wrap st3" id="manageBrNoLi" showFlag="0">
													<label for="#filter-fromSubject" class="li-lable-open st1" id="manageBrNoLabel">管户机构</label>
													<input type="hidden" id="manageBrNo form-input-width" saveFlag="1" noFlag="1">
													<input type="hidden" class="form-control form-warp" name="manageBrName" id="manageBrName" saveFlag="0" noFlag="0" autocomplete="off">
													<div class="st2 clearfix" id="manageBrNoDiv">
													&nbsp;</br>&nbsp;
												</div>
												<div class="btns btns_div">
													<a href="#" id="conditions-trigger" class="conditions-trigger-open2 conditions-expand" tabindex="-1" onclick="ReportEntrance.getMoreData(this);" >更多<b></b></a>
													<button  type="button" id="manageBrNoButton"  class="btns_button">+多选</button>
												</div>
												</li>
												<li class="li-one-wrap st3" id="manageOpNoLi" showFlag="0">
													<label for="#filter-fromSubject" class="li-lable-open  st1" id="manageOpNoLable">客户经理</label>
													<input type="hidden" id="manageOpNo" saveFlag="1" noFlag="1">
													<input type="hidden" class="form-control form-warp form-input-width" name="manageOpName" id="manageOpName" saveFlag="0" noFlag="0"  autocomplete="off">
													<div class="st2 clearfix" id="manageOpNoDiv">
													&nbsp;</br>&nbsp;
													</div>
													<div class="btns btns_div">
														<a href="#" id="conditions-trigger" class="conditions-trigger-open2 conditions-expand" tabindex="-1" onclick="ReportEntrance.getMoreData(this);">更多<b></b></a>
														<button  type="button" id="manageOpNoButton"  class="btns_button">+多选</button>
													</div>
												</li>
												<li class="li-one-wrap st3" id="pledgeClassLi" style="display: none;" showFlag="0">
													<label for="#filter-fromSubject" class="li-lable-open st1" id="pledgeClassLable">押品类别</label>
													<input type="hidden" id="pledgeClassNo" saveFlag="1" noFlag="1">
													<input type="hidden" class="form-control form-warp form-input-width" name="pledgeClass" id="pledgeClass"  saveFlag="0"  noFlag="0" autocomplete="off">
													<div class="st2 clearfix" id="pledgeClassDiv">
													&nbsp;</br>&nbsp;
													</div>
													<div class="btns btns_div">
														<a href="#" id="conditions-trigger" class="conditions-trigger-open2 conditions-expand" tabindex="-1" onclick="ReportEntrance.getMoreData(this);" >更多<b></b></a>
														<button  type="button" id="pledgeClassButton"  class="btns_button">+多选</button>
													</div>												
												</li>
												<li class="li-one-wrap st3" id="cusTypeLi" style="display: none;" showFlag="0">
													<label for="#filter-fromSubject" class="li-lable-open st1" id="cusTypeLable">客户分类</label>
													<input type="hidden" id="cusTypeNo form-input-width" saveFlag="1" noFlag="1">
													<input type="hidden" class="form-control form-warp" name="cusType" id="cusType" saveFlag="0"  noFlag="0" autocomplete="off">
													<div class="st2 clearfix" id="cusTypeDiv">
													&nbsp;</br>&nbsp;
													</div>
													<div class="btns btns_div">
														<a href="#" id="conditions-trigger" class="conditions-trigger-open2 conditions-expand" tabindex="-1" onclick="ReportEntrance.getMoreData(this);" >更多<b></b></a>
														<button  type="button" id="cusTypeButton"  class="btns_button">+多选</button>
													</div>	
												</li>
												<li class="li-one-wrap st3" id="kindTypeLi" style="display: none;" showFlag="0">
													<label for="#filter-fromSubject" class="li-lable-open st1" id="kindTypeLable">产品种类</label>
													<input type="hidden" id="kindNo" saveFlag="1" noFlag="1">
													<input type="hidden" class="form-control form-warp form-input-width" name="kindType" id="kindType" saveFlag="0"  noFlag="0" autocomplete="off">
													<div class="st2 clearfix"  id="kindTypeDiv">
													&nbsp;</br>&nbsp;
													</div>
													<div class="btns btns_div">
														<a href="#" id="conditions-trigger" class="conditions-trigger-open2 conditions-expand" tabindex="-1" onclick="ReportEntrance.getMoreData(this);" >更多<b></b></a>
														<button  type="button" id="kindTypeButton"  class="btns_button">+多选</button>
													</div>	
												</li>
												<li class="li-one-wrap st3" id="dataSourceLi" style="display: none;" showFlag="0">
													<label for="#filter-fromSubject" class="li-lable-open st1" id="dataSourceLable">项目来源</label>
													<input type="hidden" id="dataSourceNo" saveFlag="1" noFlag="1">
													<input type="hidden" class="form-control form-warp form-input-width" name="dataSource" id="dataSource"  saveFlag="0"  noFlag="0" autocomplete="off">
													<div class="st2 clearfix" id="dataSourceDiv">
													&nbsp;</br>&nbsp;
													</div>
													<div class="btns btns_div">
														<a href="#" id="conditions-trigger" class="conditions-trigger-open2 conditions-expand" tabindex="-1" onclick="ReportEntrance.getMoreData(this);" >更多<b></b></a>
														<button  type="button" id="dataSourceButton"  class="btns_button">+多选</button>
													</div>	
												</li>
												<li class="li-one-wrap st3" id="fincIdLi" style="display: none;" showFlag="0">
													<label for="#filter-fromSubject" class="li-lable-open st1" id="fincIdLable">借据项目</label>
													<input type="hidden" id="fincId" saveFlag="1" noFlag="1">
													<input type="hidden" class="form-control form-warp form-input-width" name="appName" id="appName"  saveFlag="0"  noFlag="0" autocomplete="off">
													<div class="st2 clearfix" id="appNameDiv">
													&nbsp;</br>&nbsp;
													</div>
													<div class="btns btns_div">
														<a href="#" id="conditions-trigger" class="conditions-trigger-open2 conditions-expand" tabindex="-1" onclick="ReportEntrance.getMoreData(this);" >更多<b></b></a>
														<button  type="button" id="appNameButton"  class="btns_button">+多选</button>
													</div>	
												</li>
									
												<li class="li-one-wrap st3"  id="cusNoLi" style="display: none;"  showFlag="0">
													<label for="#filter-fromSubject" id="cusNoLable" class="li-lable-open st1">客户姓名</label>
													<input type="hidden" id="cusNo" saveFlag="1" noFlag="1">
													<input type="hidden" class="form-control form-warp" name="cusName" id="cusName" saveFlag="0" noFlag="0"  autocomplete="off">
													<div class="st2 clearfix" id="cusNameDiv">
													&nbsp;</br>&nbsp;
													</div>
													<div class="btns btns_div">
														<a href="#" id="conditions-trigger" class="conditions-trigger-open2 conditions-expand" tabindex="-1" onclick="ReportEntrance.getMoreData(this);" >更多<b></b></a>
														<button  type="button" id="cusNameButton"  class="btns_button">+多选</button>
													</div>
												</li>
												<li class="li-one-wrap st3" id="channelSourceNoLi" style="display: none;"  showFlag="0">
													<label for="#filter-fromSubject" id="channelSourceNoLable" class="li-lable-open st1">渠道名称</label>
													<input type="hidden" id="channelSourceNo" saveFlag="1" noFlag="1">
													<input type="hidden" class="form-control form-warp" name="channelSourceName" id="channelSourceName" saveFlag="0" noFlag="0"  autocomplete="off">
													<div class="st2 clearfix" id="channelSourceNameDiv">
													&nbsp;</br>&nbsp;
													</div>
													<div class="btns btns_div">
														<a href="#" id="conditions-trigger" class="conditions-trigger-open2 conditions-expand" tabindex="-1" onclick="ReportEntrance.getMoreData(this);" >更多<b></b></a>
														<button  type="button" id="channelSourceNameButton"  class="btns_button">+多选</button>
													</div>
												</li>-->
												<li class="li-one-wrap st3" id="cityLi" style="display: none;"  showFlag="0">
													<label for="#filter-fromSubject" id="cityLable" class="li-lable-open st1">城市</label>
													<input type="hidden" id="cityNo" saveFlag="1" noFlag="1">
													<input type="hidden" class="form-control form-warp" name="cityName" id="cityName" saveFlag="0" noFlag="0"  autocomplete="off">
													<div class="st2 clearfix" id="cityNameDiv">
													&nbsp;</br>&nbsp;
													</div>
													<div class="btns btns_div">
														<a href="#" id="conditions-trigger" class="conditions-trigger-open2 conditions-expand" tabindex="-1" onclick="ReportEntrance.getMoreData(this);" >更多<b></b></a>
														<button  type="button" id="cityNameButton"  class="btns_button">+多选</button>
													</div>
												</li>
												<li class="li-two-wrap st3" id="dateIntervalLi" style="display: none;" showFlag="0">
													<label id="dateIntervalLable" class="li-lable-open st1">登记日期</label> 
													<input type="text" class="form-control form-warp cw-week dateInterver dateIntervalLable" readonly name="beginDate" style="margin-left:30px;background-color:#FFFFFF" saveFlag="1"  noFlag="0"  id="beginDate" autocomplete="off" onclick="ReportEntrance.showCalendarDlg(this);" onkeydown="enterKey();">
													<span>至</span> 
													<input type="text" class="form-control form-warp cw-week dateInterver dateIntervalLable" readonly name="endDate"  style="background-color:#FFFFFF"saveFlag="1"  noFlag="0"  id="endDate" autocomplete="off" onclick="ReportEntrance.showCalendarDlg(this);" onkeydown="enterKey();">
												</li>
												<li class="li-one-wrap st3" id="dateLi" style="display: none;" showFlag="0">
													<label id="dateLable" class="li-lable-open st1">到期日</label> 
													<input type="text" class="form-control form-warp cw-week  dateInterver dateIntervalLable" value="" name="date" id="date"  saveFlag="1"   noFlag="0" autocomplete="off" onclick="fPopUpCalendarDlg(this);">
												</li>
												<!-- <li class="li-one-wrap st3" id="monthLi" style="display: none;" showFlag="0">
													<label id="monthLable" class="li-lable-open st1">月份</label> 
													<input type="text" class="form-control form-warp cw-week  dateInterver dateIntervalLable" value="" name="month" id="month"  saveFlag="1" noFlag="0" autocomplete="off" onclick="laydatemonth(this);">
												</li>
												<li class="li-one-wrap st3" id="yearLi" style="display: none;" showFlag="0">
													<label id="yearLable" class="li-lable-open st1">年份</label> 
													<input type="text" class="form-control form-warp cw-week  dateInterver dateIntervalLable" value="" name="year" id="year"  saveFlag="1"  noFlag="0" autocomplete="off" onclick="ReportEntrance.showCalendarDlg(this);">
												</li>
												<li class="li-one-wrap st3" id="numTypeLi" style="display: none;"  showFlag="0">
													<label for="#filter-fromSubject" class="li-lable-open st1" id="numTypeLable">数据类型</label> 
													<input type="hidden" id="numTypeNo" saveFlag="1" noFlag="1">
													<input type="text" class="form-control form-warp form-input-width" name="numType" id="numType" saveFlag="0"  noFlag="0" autocomplete="off">
													<span class="glyphicon glyphicon-search search-addon comitem_select" for="numTypeNo"></span>
												</li>-->
												</ul>
											</form>
											</div>
											</div>
										
											
											<form style="display:none" id="btnSearchForm" action="" method="post" target="ifmreport">											
												<input type="hidden" id="sqlCondition" name="sqlCondition" value="">
												<!-- <input type="hidden" id="sqlMap" name="sqlMap" value=""> -->
												<!-- <input type="button" class="btnsearch" value="查询" onclick="dosearch()"/> -->
											</form>
										 数据请求表单结束
									</td>
							</tr>
						</tbody>
					</table>
				</td>
			</tr>
			<!-- <tr id="op_more_tr" style="display:none;">
				<td valign="top" style="text-align:center;height:30px;" >
					<span style="display:none; background:#F1FAF9;padding-left:50px;padding-right:50px;cursor:pointer" tabindex="-1" data-flag="1" onclick="ReportEntrance.getMoreLi(this)" id="shouqi_icon"> 
					<img src="images/shouqi.png" style="width:10px;height:10px;">&nbsp;&nbsp;收起查询条件
					</span>
					<span style="background:#F1FAF9;padding-left:50px;padding-right:50px;cursor:pointer" tabindex="-1" data-flag="0" onclick="ReportEntrance.getMoreLi(this)" id="zhankai_icon"> 
					<img src="images/zhankai.png" style="width:10px;height:10px;">&nbsp;&nbsp;展开查询条件
					</span>
				</td>
			</tr> -->
			<tr>
				<td valign="top" style="padding-left:5px;padding-right:5px;" height="100%">
					<div style="width:100%;height:100%;z-index:-1;">
						<iframe id="ifmreport" name="ifmreport" src="about:blank" frameborder="0" style="width: 100%; height: 100%;"></iframe>
					</div>
				</td>
			</tr> 
		</tbody>
	 
	 </table>

	<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/report1/MfReportEntrance.js?v=${cssJsVersion}"></script> --%>
	<script type="text/javascript">
		debugger;
		var openUid = "<%=uid%>";
	    /* reportUrl = "/report_new/report/rbc/designPreviewIndex.jsp?reporttype=D&uid="+openUid;
		$('#btnSearchForm').attr("action",reportUrl);
		$('#sqlCondition').val("'%test1%'");
		$('#btnSearchForm').submit();  */
		
		
		<%-- var openReportId = "<%=reportId%>";
		var reportId = "<%=reportId%>"; --%>
		var openReporttype = "<%=reporttype%>";
		
		var openPreIndex = "<%=preIndex%>";
		
		<%-- var funRoleType = "<%=funRoleType%>"; --%>
		<%-- var type = "<%=type%>"; --%>
		<%-- var id = "<%=id%>"; --%>
		<%-- var currMonth = "<%=currMonth%>";
		var lastMonth = "<%=lastMonth%>";
		var currDate = "<%=currDate%>"; --%>
		 var javaUrl = "";
		function openReportAction(){
			jQuery.ajax({
				url:"MfReportQueryConditionUserActionAjax_reportQuery",
				data:{reportId:openReportId},
				type:"POST",
				dataType:"json",
				beforeSend:function(){  
				},success:function(data){
					if(data.flag == "success"){
						debugger;
						var reportUrl = "/report_new/report/rbc/"+openPreIndex+"?reporttype="+openReporttype+"&uid="+openUid;
						$('#btnSearchForm').attr("action",reportUrl);
						$('#sqlCondition').val(data.querySqlCondition);
						$('#btnSearchForm').submit();
					}else if(data.flag == "error"){
					}
				},error:function(data){
				}
			});
		}  
		//$(function(){
			/* ReportEntrance.openInit();
			if(type!='java'){
				openReportAction();
			}else{
 				//var reportUrl = "MfAppEfficiencyAction_appEfficiency.action?reportId=report-appEfficiency";
	 			ReportEntrance.getJavaUrl(reportId);
				 $('#btnSearchForm').attr("action",javaUrl);
				 ReportEntrance.openSaveSqlCondition();
			} */
			
			
		//});
		
	</script>
</body>
</html>