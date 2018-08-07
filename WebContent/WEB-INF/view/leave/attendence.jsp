<%@page import="java.util.Map"%>
<%@page import="com.mftcc.common.util.StringUtil"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>leaveList</title>
<%@include file="/WEB-INF/view/head.jsp" %>
</head>

<body>
<section class="content-header">
    <form id="select-form">
    <div class="col-sm-4">
		 <a href="javascript:void(0)" class="btn btn-info" iconCls="icon-import" plain="true" onclick="openUploadFileDialog()">用模版批量导入数据</a>
	</div>
	
	<div class="col-sm-4">
	</div>
	
        <div class="col-sm-3 input-group">
          <input type="text" name="searchFiled" class="form-control" placeholder="姓名">
              <span class="input-group-btn">
                <button type="submit" name="search" id="search-btn" class="btn btn-default"><i class="fa fa-search"></i>
                </button>
              </span>
        </div>
      </form>
	</section>
	<section class="content">
	<div>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>日期</th>
					<th>姓名</th>
					<th>工作时数</th>
					<th>迟到次数</th>
					<th>早退次数</th>
				    <th>加班时数</th>
				    <th>出差情况</th>
				    <th>旷工</th>
				    <th>出勤情况</th>
				    
				</tr>
			</thead>
			<tbody>
				
			</tbody>
		</table>
		<div id="page-div" style="text-align: right;">
	</div>
	
	<div id="dlg2"    class="hide"  style="padding:10px 20px" >
	<form id="uploadForm" action="<%=request.getContextPath()%>/upload" method="post" enctype="multipart/form-data">
	
      	 上传文件：  <input type="file" id="file" name="file"> <br/>
      	 <input  id="yearmonth" type="text" name="yearmonth" placeholder="日期格式：XXXX-XX"  ><br/><br/>
       <a href="javascript:void(0)" class="btn btn-info"  onclick="uploadFile('#uploadForm')">上传</a>
	
     </form>  			 
	</div>
	
	</section>
	<%@include file="/WEB-INF/view/bottom.jsp" %>
	
	
	<script src="<%=request.getContextPath()%>/AdminLTE/plugins/iCheck/icheck.min.js"></script>
	
	<script type="text/javascript">
	var pageNumber=10;
	$(function(){
		var ipage ="currPageNo=1&pageNumber="+pageNumber;
		$.post("<%=request.getContextPath()%>/getAttendenceListPage.json",ipage,function(data){
			$("#page-div").createPage({
		        pageCount:data.ipage.totalPage,
		        current:1,
		        backFn:function(p){
		            getdata(p);
	        	}
			});
			tbodyInit(data.ipage.dataList);
		},"json");
	});
	function tbodyInit(data){
		$("tbody").empty();
		var htmlstr = "";
		/* 生成页面信息 */
		$.each(data,function(i,o){
				htmlstr += "<tr>"
					+	"<td>"+o.yearMonth+"</td>"
					+	"<td>"+o.empName+"</td>"
					+	"<td>"+o.workTimeTotal+"</td>"
					+	"<td>"+o.lateTimes+"</td>"
					+	"<td>"+o.eLeaveTimes+"</td>"
					+	"<td>"+o.sExtraWork+"</td>"
					+	"<td>"+o.goOut+"</td>"
					+	"<td>"+o.neglect+"</td>"
					+	"<td>"+o.attendenceDays+"</td>"
					+	"<td><a href='javascript:void(0);' onclick='update(\""+o.empNo+"\",\""+o.yearMonth+"\");' >修改</a></td>"
		});																//\ ""+o.leaveNo+"\"  \'"+o.empNo+"','"+o.yearMonth+"' \
		$("tbody").html(htmlstr);
	};

	function getdata(page){
		var ipage = "pageNumber="+pageNumber+"&currPageNo="+ page;
		$.post("<%=request.getContextPath()%>/getAttendenceListPage.json",ipage,function(data){
			tbodyInit(data.ipage.dataList);
		},"json");
	};
	$("#select-form").click(function(){
		var ipage = $("#select-form").serialize() + "&pageNumber="+pageNumber;	
		$.post("<%=request.getContextPath()%>/getAttendenceListPage.json",ipage,function(data){
			
			$("#page-div").createPage({
		        pageCount:data.ipage.totalPage,
		        current:1,
		        backFn:function(p){
		        	getdata(p);
	        	}
			});
			tbodyInit(data.ipage.dataList);
		},"json");
		return false;
	});
	
	
	
	
	function update(empNo,yearMonth){
	 	
		return window.location.href='attendenceInfo?empNo=' + empNo + '&yearMonth=' + yearMonth;
	}
	
	$("#addleave").click(function(){
		window.location.href = "addleave";
	});
	
	function openUploadFileDialog(){
	//	$("#dlg2").dialog('open').dialog('setTitle','批量导入数据');
	
		$('#dlg2').removeClass("hide");
					 
		  layer.open({
			type : 1,
			shade : [ 0.5 ],
			area : [ '500px', '300px' ], //显示空间
			content : $('#dlg2') 
		 		 
		});
		
	}
	
	 function uploadFile(obj){
		 var url = $(obj).attr("action");
		 var formData = new FormData($(obj)[0]);  
		 $.ajax({  
		     url : url,  
		     type : "POST",  
		     data :  formData, 
		     async: false,  
	         cache: false,  
	         contentType: false,  
	         processData: false,  
		     success : function(data) {  
		    	 
		    	 $('#dlg2').addClass("hide");
		    	 window.location.href = "attendence";
		     },  
		     error : function(data) {  
		    	 $('#dlg2').addClass("hide");
		    	 window.location.href = "attendence";
		     }  
		});
	}
	
	
	 
	
	</script>
	
	
</body>
</html>