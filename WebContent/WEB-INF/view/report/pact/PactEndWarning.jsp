<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.mftcc.interior.sys.bean.SysOrg"%>

 <%
	String reporttype=(String)request.getAttribute("reporttype");
	String uid = (String)request.getAttribute("uid");
	
	String preIndex="designPreviewIndex.jsp";
    if(reporttype.equals("C")){
   	  preIndex="designPreviewIndexC.jsp";
    }
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>报表入口页面</title>
	<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/report1/js/jquery-1.11.2.min.js"></script> --%>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/report1/css/MfReportEntrance.css" />
	<link rel="stylesheet" href="<%=request.getContextPath()%>/report1/css/search_filter.css" />
	<link rel="stylesheet" href="<%=request.getContextPath()%>/report1/css/factor.css" />
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/report1/jedate/jquery-1.7.2.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/report1/jedate/jquery.jedate.js"></script>
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/report1/jedate/skin/jedate.css">
	<style type="text/css">
		.datainp{ 
			width:200px; 
			height:30px; 
			border:1px #A5D2EC solid;
			}
		.wicon{
			background-image:url("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABkAAAAQCAYAAADj5tSrAAAABHNCSVQICAgIfAhkiAAAAAlwSFlzAAALEgAACxIB0t1+/AAAABZ0RVh0Q3JlYXRpb24gVGltZQAwNi8xNS8xNGnF/oAAAAAcdEVYdFNvZnR3YXJlAEFkb2JlIEZpcmV3b3JrcyBDUzVxteM2AAAAoElEQVQ4jWPceOnNfwYqAz9dYRQ+E7UtwAaGjyUsDAyYYUgJ2HT5LXZLcEmSCnA6duOlN///////H0bDALl8dPH/////Z8FuNW6Qtvw2nL3lyjsGBgYGhlmRqnj1kGwJuqHIlhJlCXq8EOITEsdqCXLEbbr8FisfFkTo+vBZRFZwERNEFFkCiw90nxJtCalxQmzegltCzVyP1RJq5HZ8AABuNZr0628DMwAAAABJRU5ErkJggg=="); 
			background-repeat:no-repeat; 
			background-position:right center;
			}
		body {
			background-color: #F9F9F9;
			margin:0; padding:0;
			}
	</style>
</head>

<body class="overflowHidden">
	<table style="width: 100%; height: 100%; table-layout: fixed;" border="0" cellspacing="0" cellpadding="0">
		<tbody>
			 <tr>
				<td style="overflow: auto;padding-left:5px;padding-right:5px;">
					<table width="100%" height="100%" style="table-layout: fixed;">
						<tbody>
							<tr>
								<td>
									<div class="searchlist" style="-ms-overflow-x: auto;margin-top:5px;">
											<button class="ui-btn ui-btn-sp"  style="margin-top:0px;float:right;" id="filter-submit" onclick="search()">查询</button>
											<div>
												<form id="cwListForm" style="float:left;width:93%;margin-bottom:0px;height:auto;" method="get">
													<ul class="filter-list" id="more-conditions" style="padding-left: 0px; border-bottom: 1px solid #ddd;margin-bottom:0px;" >
														<li class="li-one-wrap st3" id="brNoLi"  showFlag="0">
															<label for="#filter-fromSubject" class=" st1 li-lable-open" id="brNoLabel">部门</label>
															<div  id="abc" class="st2 clearfix" id="brNoDiv" >
																<c:forEach items="${orgs}" var="orgs">
																	<input type="checkbox" name="brs" value="${orgs.brNo }" id="${orgs.brNo }"/>
                            										<label for="${orgs.brNo }" class="checkboxLabel">${orgs.brName }</label>
																</c:forEach>
															</div>
																<a href="#" id="conditions-trigger" class="conditions-trigger-open2 conditions-expand" tabindex="-1" data-flag="0" onclick="ReportEntrance.getMoreData(this);">更多<b></b></a>
																<button  type="button" id="brNoButton" class="">+多选</button>
														</li>
														<li class="li-one-wrap st3" id="brNoLi"  showFlag="0">
															<label for="#filter-fromSubject" class=" st1 li-lable-open" id="brNoLabel">距合同到期天数</label>
															<select id="dates" name="dates">  
    															<option value="5">小于等于5天</option>  
															    <option value="10">小于等于10天</option>  
															    <option value="15">小于等于15天</option>  
															    <option value="30">小于一个月</option>  
															    <option value="31">大于一个月</option> 
															</select>  
														</li>
													</ul>
												</form>
											</div>
									</div>
										
									<form style="display:none" id="btnSearchForm" action="" method="post" target="ifmreport">											
										<input type="hidden" id="sqlCondition" name="sqlCondition" value="">
									</form>
								</td>
							</tr>
						</tbody>
					</table>.
				</td>
			</tr>
			
			<tr>
				<td valign="top" style="padding-left:5px;padding-right:5px;" height="100%">
					<div style="width:100%;height:100%;z-index:-1;">
						<iframe id="ifmreport" name="ifmreport" src="about:blank" frameborder="0" style="width: 100%; height:580px;"></iframe>
					</div>
				</td>
			</tr> 
		</tbody>
	 
	 </table>

	<script type="text/javascript" src="<%=request.getContextPath()%>/report1/ReportEntrance.js"></script>
	
	<script type="text/javascript">
		debugger;
		var openUid = "<%=uid%>";
		var nowdata=$.nowDate(0);
	    reportUrl = "/report_new/report/rbc/designPreviewIndex.jsp?reporttype=D&uid="+openUid;
		$('#btnSearchForm').attr("action",reportUrl);
		$('#sqlCondition').val("date_to_end >0");
		$('#btnSearchForm').submit(); 
		
		/*日期处理*/
		$("#yearmonth").jeDate({
		    isinitVal:true,
		    festival:true,
		    ishmsVal:false,
		    //maxDate: '2016-06-16 23:59:59',
		    maxDate: $.nowDate(0),
		    format:"YYYY",
		    zIndex:3000,
		}) 
	</script>
	<script type="text/javascript">
	
	 function search(){ 
		var dates=$('#dates').serialize();
		var brnos='';
		var no='';
		var vals=0;
		$('#cwListForm').find(':checkbox').each(function() {
		      if($(this).is(":checked")){
		      	no = $(this).val();
		      	if(no != '' && no != null){
		      		brnos = brnos + "," + no;
		          	vals++;
		      	}
		      }
		 });
		$.ajax({
			url:"<%=request.getContextPath()%>/pactReport/pactEndWarningFilter",
			data: "brs="+brnos+"&"+dates,
			type:"POST",
			dataType:"json",
			async:false,
			processData:false,
            success: function (data) {
                 if ("true" != data.mgs) {
                   /*  alert("error!"); */
                 } else {
                   /*  alert("sucess!"); */
                    var openUid ="<%=uid%>";
            	    reportUrl = "/report_new/report/rbc/designPreviewIndex.jsp?reporttype=D&uid="+openUid;
            		$('#btnSearchForm').attr("action",reportUrl);
            		$('#sqlCondition').val(data.sqlCondition);
            		$('#btnSearchForm').submit(); 
                 }
             },
			error:function(){
				alert("error!!!");
			}
		});
	} 
	</script>
</body>
</html>