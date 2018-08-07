<%-- <%@page import="cn.mftcc.util.DateUtil"%> --%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%-- <%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/tld/loan.tld" prefix="dhcc" %> --%>
<%@ include file="message.jsp" %>
<%-- <%@ page import="app.component.common.SysGlobalParams"%>
<%@ page import="app.component.sys.entity.MfSysCompanyMst"%> --%>

<%
String contextpath = request.getContextPath();
// ↓ 使用规范命名的变量。
String contextPath = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+contextPath+"/";
//MfSysCompanyMst mfSysCompanyMst=( MfSysCompanyMst) SysGlobalParams.get("COMPANY");
%>
<script type="text/javascript">
var contextPath = "<%=contextPath%>";
<%-- var today = "<%=DateUtil.getDate("yyyy-MM-dd")%>"; --%>
<%-- var loadingGifPath = "<%=mfSysCompanyMst.getLoadAnimationImg()%>"; --%>
</script>
<script type="text/javascript" src="<%=basePath%>/report1/layout/ie8/js/es5-shim.js"></script>
<script type="text/javascript" src="<%=basePath%>/report1/layout/ie8/js/es5-sham.js"></script>
<!-- JSON 兼容 -->
<script type="text/javascript" src="<%=contextPath%>/report1/layout/ie8/js/json2.js"></script>
<!-- CSS伪类兼容 -->
<script type="text/javascript" src="<%=contextPath%>/report1/layout/ie8/js/selectivizr-min.js"></script>
<!-- CSS伪类兼容 -->
<script type="text/javascript" src="<%=contextPath%>/report1/layout/ie8/js/jquery.pseudo.js"></script>
<!-- placeholder兼容 -->
<script type="text/javascript" src="<%=contextPath%>/report1/component/include/jquery.placeholder.min.js"></script>

<script type="text/javascript" src="<%=basePath%>/report1/component/include/SysConstant.js?v=${cssJsVersion}"></script>
<%--jquery表单序列化--%>
<script type="text/javascript" src="<%=contextPath%>/report1/themes/view/js/jquery.serializejson.min.js"></script>
<%--日期控件:
	选择月-----onClick="laydatemonth(this)"
	选择日-----onClick="fPopUpCalendarDlg()",若选择日期需要从当前日期开始，则使用fPopUpCalendarDlg({min:new Date().toLocaleDateString(),}) 
		若选择日期在当前日期之前，则使用fPopUpCalendarDlg({max:new Date().toLocaleDateString()})"
		若需要会调函数，则使用fPopUpCalendarDlg({choose:sssss})"
	选择日(时分秒)-----onClick="laydateTimer(this)"
	选择年-----onClick="laydateYear()"
	选择年月日时分秒格式------onclick="laydateTimer()",若是选择需要从当前日期开始，则使用laydateTimer({min: new Date().toLocaleDateString(),})
			若选择日期在当前日期之前，则使用laydateTimer({max:new Date().toLocaleDateString()})"
		          若需要会调函数，则使用laydateTimer({choose:sssss})"
--%>
<script type="text/javascript" src="<%=contextPath%>/report1/component/include/laydate/laydate.dev.js"></script>
<script type="text/javascript" src="<%=contextPath%>/report1/component/include/laydate/laydate.month.js"></script>
<script type="text/javascript" src="<%=contextPath%>/report1/component/include/laydate/laydate.year.js"></script>
<%--自动下拉菜单
	onclick="prodAutoMenu(this,url,callback)"
--%>
<script type="text/javascript" src="<%=contextPath%>/report1/component/include/jquery.autocompleter.js"></script>
<script type="text/javascript" src="<%=contextPath%>/report1/component/include/jquery.autocompleter.unbound.js"></script>
<link rel="stylesheet" href="<%=contextPath%>/report1/component/include/autocompleter.css?v=${cssJsVersion}" />

<%--滚动条js 和鼠标滚动事件js--%>
<script type="text/javascript" src="<%=contextPath%>/report1/UIplug/customScrollbar/js/jquery.mousewheel-3.0.6.min.js"></script>
<script type="text/javascript" src="<%=contextPath%>/report1/UIplug/customScrollbar/js/jquery.mCustomScrollbar.js?v=${cssJsVersion}"></script>
<script type="text/javascript" src="<%=contextPath%>/report1/component/include/myCustomScrollbarForForm.js"></script>
<!-- 自定义JS 基础JS -->
<script type='text/javascript' src='<%=contextPath%>/report1/component/include/base.js'></script>
<!-- 自定义js 失去焦点校验 保存校验 -->
<script type="text/javascript" src='<%=contextPath%>/report1/component/include/uior_val1.js?v=${cssJsVersion}'> </script>
<script type="text/javascript" src='<%=contextPath%>/report1/component/include/xcqi_cal.js'> </script>
<%--详情表单双击修改--%>
<script type='text/javascript' src="<%=contextPath%>/report1/component/include/dbClick.js?v=${cssJsVersion}"></script>
<script type="text/javascript" src="<%=contextPath%>/report1/component/include/form.js?v=${cssJsVersion}"></script>
<script type="text/javascript" src="<%=contextPath%>/report1/component/include/format.js?v=${cssJsVersion}"></script>
<script type="text/javascript" src="<%=contextPath%>/report1/component/include/idvalidate.js?v=${cssJsVersion}"></script>
<script type="text/javascript" src="<%=contextPath%>/report1/component/include/freewall.js?v=${cssJsVersion}"></script>
<%--时间轴--%>
<%-- <script type="text/javascript" src="<%=contextPath%>/component/include/formline.js"></script> --%>
<script type="text/javascript" src="<%=contextPath%>/report1/component/include/navLine.js"></script>
<%--字体图标--%>
<link rel="stylesheet" href="<%=contextPath%>/report1/UIplug/Font-Awesome/css/font-awesome.min.css" />
<link rel="stylesheet" href="<%=contextPath%>/report1/UIplug/Font-Awesome/css/font-awesome.css" />
<link rel="stylesheet" href="<%=contextPath%>/report1/UIplug/iconmoon/style.css?v=${cssJsVersion}" />
<%--bootstrap框架--%>
<%-- <script type="text/javascript" src="<%=contextPath%>/UIplug/bootstrap/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="<%=contextPath%>/UIplug/bootstrap/css/bootstrap.min.css" /> --%>

<%--滚动样式--%>
<%-- <link rel="stylesheet" href="<%=contextPath%>/UIplug/customScrollbar/css/jquery.mCustomScrollbar.css?v=${cssJsVersion}" type="text/css"/>
<script src="<%=contextPath%>/UIplug/userAgent/userAgent.js" language="javascript" type="text/javascript"></script> --%>
<!-- 行政区划和行业划分 -->
<%-- <script type="text/javascript" src="<%=contextPath%>/component/include/IndustryAndArea.js"></script>
<link rel="stylesheet" href="<%=contextPath%>/component/include/mulitselector/mulitSelect.css?v=${cssJsVersion}" />
<link rel="stylesheet" href="<%=contextPath%>/component/include/leaseCheckBox.css?v=${cssJsVersion}" />
<script type="text/javascript" src="<%=contextPath%>/component/include/leaseCheckBox.js?v=${cssJsVersion}" ></script>
<script type="text/javascript" src="<%=contextPath%>/component/include/jquery.qtip.min.js" ></script>
<link rel="stylesheet" href="<%=contextPath%>/component/include/jquery.qtip.min.css" />
<script type="text/javascript" src="<%=contextPath%>/component/include/tablelistshowdiv.js?v=${cssJsVersion}" ></script> --%>
<!-- 证件号码验证 -->
<%-- <script type="text/javascript" src='<%=contextPath%>/component/include/idCheck.js?v=${cssJsVersion}'> </script> --%>
<!-- 弹层关闭的方法 -->
<script type="text/javascript" src='<%=contextPath%>/report1/component/include/closePopUpBox.js?v=${cssJsVersion}'> </script>

<!-- ztree -->
<link rel="stylesheet" href="<%=contextPath%>/report1/UIplug/zTree/zTreeStyle/zTreeStyle.css" />
<script type='text/javascript' src='<%=contextPath%>/report1/UIplug/zTree/jquery.ztree.all-3.5.min.js'></script>
		
<script type="text/javascript" src="<%=contextpath%>/report1/component/include/popupSelection/popupSelection.js?v=${cssJsVersion}" ></script>
<script type="text/javascript" src="<%=contextpath%>/report1/component/include/popupSelection/popupList.js?v=${cssJsVersion}" ></script>
<!--帮助组件  -->

<!-- 弹窗消息模板 -->
<script type="text/javascript" src="<%=contextPath%>/report1/component/include/getMessage.js?v=${cssJsVersion}"></script>
<!--artdialog插件  -->
<%-- <script type="text/javascript" src="<%=contextPath%>/UIplug/artDialog/dist/dialog-plus-min.js"></script>
<link rel="stylesheet" href="<%=contextPath%>/UIplug/artDialog/css/ui-dialog.css" /> --%>
<!-- 引入自定义的弹窗扩展 -->
<script type="text/javascript" src="<%=contextPath%>/report1/themes/factor/js/dialog.js?v=${cssJsVersion}"></script>
 
 <!--弹窗选择公用js  -->
 <script type="text/javascript" src="<%=contextPath%>/report1/themes/factor/js/selectInfo.js"></script>
 <!--字符串工具类公用js  -->
 <script type="text/javascript" src="<%=contextPath%>/report1/component/include/StringUtil.js?v=${cssJsVersion}"></script>
 <!--加载动画js  -->
 <script type="text/javascript" src="<%=contextPath%>/report1/themes/factor/js/loadingAnimate.js?v=${cssJsVersion}"></script>
  <!--factor自定义的表单相关js  -->
 <script type="text/javascript" src="<%=contextPath%>/report1/themes/factor/js/formFactor.js?v=${cssJsVersion}"></script>
 <!-- 手机号码和证件号码唯一性验证js -->
<%--  <script type="text/javascript" src='<%=contextPath%>/component/include/checkUniqueVal.js?v=${cssJsVersion}'> </script> --%>
<!-- 动态子表单依赖js -->
<script type="text/javascript" src="<%=contextpath%>/report1/component/include/anchor.js?v=${cssJsVersion}"></script>

<!-- 苹果丽黑字体 -->
<%-- <link rel="stylesheet" href="<%=contextPath%>/themes/factor/css/fonts/ARIALN.css?v=${cssJsVersion}" /> --%>

<!--  供应链公共样式 -->
 <script rel="text/javascript" src="<%=contextPath%>/report1/themes/factor/js/formStyle.js?v=${cssJsVersion}"></script>
 <link rel="stylesheet" href="<%=contextPath%>/report1/themes/factor/css/BS-factor.css?v=${cssJsVersion}" />
 <link rel="stylesheet" href="<%=contextPath%>/report1/themes/factor/css/main-detail.css?v=${cssJsVersion}" />
 <link rel="stylesheet" href="<%=contextPath%>/report1/themes/factor/css/form.css?v=${cssJsVersion}" />
 <link rel="stylesheet" href="<%=contextPath%>/report1/themes/factor/css/rightForm.css?v=${cssJsVersion}" />
 <link rel="stylesheet" href="<%=contextPath%>/report1/themes/factor/css/factor.css?v=${cssJsVersion}" />
 <link rel="stylesheet" href="<%=contextPath%>/report1/themes/factor/css/multi-select.css?v=${cssJsVersion}" />
 <link rel="stylesheet" href="<%=contextPath%>/report1/themes/factor/css/common.css?v=${cssJsVersion}" />
<script type="text/javascript">
	$(function(){
		$("body").delegate(".form-control[readonly]", "focus", function() {
			$(this).blur();
		});
	});
	/**
	 * 获取当前操作的ifream
	 * @param {Object} timeStamp 时间戳
	 * @param {Object} ifreamHeight 窗体高度
	 */
	window.resizeHeight = function(timeStamp,ifreamHeight,callback){
		var thisIfreamObj;
		$(window.document).find("iframe").each(function(index){
			var thisObj = $(this);
			var thisWindow = $(this).contents().find("body");
			var thisTimeStamp = $(thisWindow).find("#timeStamp").attr("timeStamp");
			if(thisTimeStamp!==undefined&&thisTimeStamp==timeStamp){
				thisIfreamObj = thisObj;
				$(thisIfreamObj).animate({height:ifreamHeight+40},500,function(){
					if(callback!==undefined){
						callback.call(this);
					}
				});
			}
		});
	}
	window.flushIfream = function(){
		window.parent.location.reload();
	}
// 	var par = $("body",parent.document);
// 	try{
// 		if(par.find(".pt-page[name=A]").length>0){
// 			getHelp();
// 		}else{
// 			parent.getHelp();
// 		}
// 	}catch(err){
// 		console.warn("当前页面或者父级页面未引用SysHelp.js文件！ --"+err);
// 	}
</script>
