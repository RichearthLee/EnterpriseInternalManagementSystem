<%@page import="com.mftcc.common.util.StringUtil"%>
<%@page import="java.util.*"%>
<%@page import="com.mftcc.interior.pact.bean.PayProgressInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>pactProgressList</title>
<%@include file="/WEB-INF/view/head.jsp" %>
</head>

<body>

  <section class="content-header">
  <form id="search-form">
	<div class="col-sm-8">
	</div>
	
        <div class="col-sm-4 input-group">
          <input type="text" name="searchFiled" class="form-control" placeholder="客户名称、客户经理">
              <span class="input-group-btn">
                <button type="submit" name="search" id="search-btn" class="btn btn-default"><i class="fa fa-search"></i>
                </button>
              </span>
        </div>
      </form>
	</section>
	<div>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>客户名称</th>
					<th>客户经理</th>
					<th>金额</th>
					<th>汇款时间</th>
					<th>汇款人</th>
					<th>银行网点</th>
					<th>凭证</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>

			</tbody>
		</table>
		<div id="page-div" style="text-align: right;">
	</div>
	

	<%@include file="/WEB-INF/view/bottom.jsp" %>
	<script>
	var pageNumber=10;
	$(function(){
		var ipage = $("#search-form").serialize() + "&currPageNo=1&pageNumber="+pageNumber;
		$.post("<%=request.getContextPath()%>/payProgress/getPayProgressPage.json",ipage,function(data){
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
		$.each(data,function(i,o){
			var receiptPath = "";
			var confirm = "";
			if(o.financialResult == 1 ){
				
			}else{
				confirm = "<a href='javaScript:void(0);' onclick='onConfirm(\""+o.pactId+"\",\""+o.payyet+"\",\""+o.proceedMoney+"\",\"" + o.customerMoney + "\");'>确认</a>";	
			}
			if(o.receiptPath == null || o.receiptPath == "" ){
				
			}else{
				receiptPath = '<a class="fa fa-fw fa-file-text-o" target="_blank" href="<%=request.getContextPath()%>/download?filePath=' +o.receiptPath+ '"title="'+o.receiptPath+'">';
			}
			
			htmlstr += "<tr>"
					+	"<td>"+o.customerName+"</td>"
					+	"<td>"+o.marketerId+"</td>"
					+	"<td>"+o.proceedMoney+"</td>"
					+	"<td>"+o.remitDate+"</td>"
					+	"<td>"+o.remitPersion+"</td>"
					+	"<td>"+o.remitBank+"</td>"
					+	"<td>"+receiptPath+"</td>"
					+	"<td>"+confirm+"</td>"
					+   "</tr>";
		});
		$("tbody").html(htmlstr);
	};

	function getdata(page){
		var ipage = $("#search-form").serialize() + "&pageNumber="+pageNumber+"&currPageNo="+ page;
		$.post("<%=request.getContextPath()%>/payProgress/getPayProgressPage.json",ipage,function(data){
			tbodyInit(data.ipage.dataList);
			
		},"json");
	};
	$("#search-form").submit(function(){
		var ipage = $("#search-form").serialize() + "&pageNumber="+pageNumber;	
		$.post("<%=request.getContextPath()%>/payProgress/getPayProgressPage.json",ipage,function(data){
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
	
	function onConfirm(pactId,payyet,proceedMoney, customerMoney){
		 //$("#pactId").val(pactId);
		//$("#payyet").val(payyet);
		var mydata = "pactId=" + pactId + "&payyet="+ payyet + "&proceedMoney=" + proceedMoney + "&customerMoney=" + customerMoney;
		//var mydata = $("#payProgressConfirm").serialize();
		$.post("<%=request.getContextPath()%>/payProgress/confirmPayprogress.json", mydata,
			function(data) {
				if (data.errorcode == 0) {
				 	layer.alert("确认失败"+data.errormessage);
					return false;
				} else {
				 	layer.msg("确认成功",{time:1000},function(){
				 		var ipage = $("#search-form").serialize() + "&currPageNo=1&pageNumber="+pageNumber;
						$.post("<%=request.getContextPath()%>/payProgress/getPayProgressPage.json",ipage,function(data){
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
				}
			
		}, "json");
		/* layer.open({
			type : 1,
			//offset:'50',
			//area : [ '20%', '35%' ],
			title : '款项确认', //不显示标题
			btn : [ '确定', '取消' ],
			content : $("#confirm"), //捕获的元素
			success : function(lay, index)
			{
			},
			yes: function(index){
				
					
			}
		});  */
		
	};
	
	</script>
</body>
</html>