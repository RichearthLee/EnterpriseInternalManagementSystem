<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%-- <%@ taglib prefix="s" uri="/struts-tags"%> --%>
<%
Object pageInfo = request.getSession().getAttribute("pageInfoMsg");
String descTempContent = request.getSession().getAttribute("descTempContent")+"";
%>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<script type="text/javascript" src="<%=request.getContextPath()%>/report1/layout/view/js/jquery-1.11.2.min.js"></script>
<!-- placeholder兼容 -->
<script type="text/javascript" src="<%=request.getContextPath()%>/report1/component/include/jquery.placeholder.min.js"></script>

<!--[if lte IE 9]> 
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>

<script>
$(function(){
	var scrollDiv = $(".scroll-content");
	var scrOffset = parseInt(scrollDiv.attr("scrollOffset")) || 60; 
	
	scrollDiv.height($(window).height() - scrOffset);
	$(window).resize(function(){
		scrollDiv.height($(window).height() - scrOffset);
	});
});
</script>
<![endif]--> 
<!-- ES5标准兼容 -->

<script type="text/javascript">
var pageInfo = '<%=pageInfo%>';
var descTempContent = '<%=descTempContent%>';

// 处理console的兼容性
if(!window.console || !console.log){
	window.console = {
		log:function(){},
		error:function(){},
		debug:function(){},
		warn:function(){},
		info:function(){}
	};
}

$(function(){
	try {
		var fw = $(".inner-center #iframepage");
		if(fw.length==0){
			if(pageInfo!="null"&&pageInfo!=null&&pageInfo!=""){
				window.top.showPageInfo(pageInfo);
				jQuery.ajax({
					url:"SysDescTempActionAjax_updateSession.action",
					type:"POST",
					dataType:"json",
					success:function(data){
					},error:function(data){
					}
				});
			}else{
				window.top.showPageInfo(descTempContent);
			}
		}
	} catch (e) {
		// TODO: handle exception
	}
});

/**
 * Created by jzh on 2015/1/22.
 */
/*提示框
 * 参数  1.提示框信息  2.提示框名称
 * */
function GenerateHtml(type,msg,title){
    if(title==""||title===undefined){
        title="提示信息";
    }
    var ghtml = "";
    ghtml+='<div class="modal fade bs-example-modal-sm" id="myAlert" tabindex="-1" role="dialog" aria-hidden="true">'+
    '<div class="modal-dialog modal-sm" style="width: 400px;">'+
    '<div class="modal-content">'+
    '<div class="modal-header">'+
    '<button type="button" class="close"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>'+
    '<h4 class="modal-title" style="color: black;font-size: 18px;">'+title+'</h4>'+
    '</div>'+
    '<div class="modal-body" style="color: black;font-size: 16px;">'+msg+'</div>'+
    '<div class="modal-footer">'+
    '<button type="button" class="btn btn-default alertConfirm">确定</button>';
    if(type=="confirm"){
        ghtml+='<button type="button" class="btn btn-default alertClose">取消</button>';
    }
    ghtml+='</div>'+
    '</div>'+
    '</div>'+
    '</div>';
    $("body").append(ghtml);
    $("#myAlert").modal({
        backdrop:false,
        show:true,
        keyboard: false
    });
    $("body").append("<div class='modal-backdrop modal-backdrop1 fade in'></div>");
}
function ChooseHtml(msg,title,chooseNameArr,noneCloseFlag){
    if(title==""||title===undefined){
        title="提示信息";
    }
    var ghtml = "";
    ghtml+='<div class="modal fade bs-example-modal-sm" id="myAlert" tabindex="-1" role="dialog" aria-hidden="true">'+
        '<div class="modal-dialog modal-sm">'+
        '<div class="modal-content">'+
        '<div class="modal-header">'+
        '<button type="button" class="close"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>'+
        '<h4 class="modal-title">'+title+'</h4>'+
        '</div>'+
        '<div class="modal-body">'+msg+'</div>'+
        '<div class="modal-footer">';
        for(var i=0;i<chooseNameArr.length;i++){
            ghtml+='<button type="button" class="btn btn-default" id="alertChoose_'+i+'">'+chooseNameArr[i]+'</button>';
        }
        if(noneCloseFlag===undefined||noneCloseFlag==true){
        	 ghtml+='<button type="button" class="btn btn-default alertClose">取消</button>';
        }
        ghtml+='</div>'+
    '</div>'+
    '</div>'+
    '</div>';
    $("body").append(ghtml);
    $("#myAlert").modal({
        backdrop:false,
        show:true,
        keyboard: false
    });
    $("body").append("<div class='modal-backdrop modal-backdrop1 fade in'></div>");
}
function myAlertConfirm(callback){
    $("#myAlert .alertConfirm").click(function(){
        $("#myAlert").modal("hide");
        $(".modal-backdrop1").remove();
        $("#myAlert").remove();
        if(typeof(callback) == 'function'){
            callback();
        }
        $("body").css("overflow","");
    });
}
function myAlertChooseConfirm(callbackArr){
    for(var i=0;i<callbackArr.length;i++){
        var chooseStr = "#myAlert #alertChoose_"+i;
        $(chooseStr).click(function(e){
            var callBackNo =$(this).attr("id").split("_")[1];
            $("#myAlert").modal("hide");
            $(".modal-backdrop1").remove();
            $("#myAlert").remove();
            if(typeof(callbackArr[callBackNo]) == 'function'){
                callbackArr[callBackNo]();
            }
        });
    }
	$("body").css("overflow","");
}

function myAlertCancel(){
    $("#myAlert .alertClose,#myAlert .close").click(function(){
        $("#myAlert").modal("hide");
        $(".modal-backdrop1").remove();
        $("#myAlert").remove();
        $("body").css("overflow","");
    });
}
$(function(){
    $.myAlert = {
        Alert:function(msg,title){
            GenerateHtml("alert",msg,title);
            myAlertConfirm();
            myAlertCancel();
        },
        Confirm:function(msg,title,callback){
            GenerateHtml("confirm",msg,title);
            myAlertConfirm(callback);
            myAlertCancel();
        },
        Choose:function(msg,title,chooseNameArr,callbackArr,noneCloseFlag){
            ChooseHtml(msg,title,chooseNameArr,noneCloseFlag);
            myAlertChooseConfirm(callbackArr);
            myAlertCancel();
        }
    };
});
window.myAlert = function(msg){
	$.myAlert.Alert(msg,"提示信息");
};
function startCompareAlert(msg){
	document.onreadystatechange=function changeselect(){
		if(document.readyState=="complete"){
			//$.myAlert.Alert(msg,"提示信息");
			window.top.alert(msg,1);
		}
	};
 }
function startErrorAlert(msg){
	document.onreadystatechange=function changeselect(){
		if(document.readyState=="complete"){
			window.top.alert(msg,0);
		}
	};
 }
//重写alert
var oldAlert = window.alert;
window.alert = function(msg,type,fun1,fun2){
    if(typeof(type)!="undefined"){
    	switch(type){
	    	case 0:
	    		window.top.alert(msg,0);
	    	  break;
	    	case 1:
	    		window.top.alert(msg,1);
	    	  break;
	    	case 2:
	    		window.top.alert(msg,2,fun1,fun2);
	    	  break;
	    	default:
	    		window.top.alert(msg);
    	}
    }else{
    	window.top.alert(msg);
    }
};
 
</script>

<s:if test="hasActionErrors()">
	<script type="text/javascript">
		var actionmsg = "";
	</script>
	<s:iterator value="actionErrors">
		<script type="text/javascript">
			actionmsg = actionmsg+'<s:property escape="false"/>'+"<br/>";
	    </script>
	</s:iterator>
	<script type="text/javascript">
		if (actionmsg != "") {
			startErrorAlert(actionmsg);
		}
	</script>
</s:if>


<s:if test="hasActionMessages()">
	<script type="text/javascript">
		var actionmsg = "";
	</script>
	<s:iterator value="actionMessages">
		<script type="text/javascript">
			actionmsg = actionmsg+'<s:property escape="false"/>'+"<br />";
	    </script>
	</s:iterator>
	<script type="text/javascript">
		if (actionmsg != "") {
			startCompareAlert(actionmsg);
		}
	</script>
</s:if>


<s:if test="hasFieldErrors()">
	<script type="text/javascript">
		var msg = "";
	</script>
	<s:iterator value="fieldErrors">
		<script type="text/javascript">
			msg = msg+"<s:property escape="false"/>"+"<br />";
	    </script>
	</s:iterator>
	<script type="text/javascript">
		if (msg != "") {
			startErrorAlert(msg);
		}
	</script>
</s:if>

