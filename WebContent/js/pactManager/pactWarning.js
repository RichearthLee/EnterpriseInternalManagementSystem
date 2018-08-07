var pageNumber=10;
var path;
function InitPactWarning(path1){
	path = path1;
	var ipage = $("#select-form").serialize() + "&currPageNo=1&pageNumber="+pageNumber;
	$.post(path+"/getPactWarningPage.json",ipage,function(data){
		$("#page-div").createPage({
	        pageCount:data.ipage.totalPage,
	        current:1,
	        backFn:function(p){
	            getdata(p);
        	}
		});
		tbodyInit(data.ipage.dataList);
		
	},"json");
};

function tbodyInit(data){
	
	$("tbody").empty();
	var htmlstr = "";
	$.each(data,function(i,o){
	/*	if(o.paymentState == '1'){
			o.paymentState = '未收';
		}else{
			o.paymentState = '收部分';
		}*/
		htmlstr += "<tr>"
				+	"<td>"+o.customerName+"</td>"
				+	"<td>"+o.productName+"</td>"
				+	"<td>"+o.pactFee+"</td>"
				+	"<td>"+o.paypercentPlan+"</td>"
				+	"<td>"+o.paymentDay+"</td>"
				+	"<td>"+o.marketerName+"</td>"
				+	"</tr>";
	});
	$("tbody").html(htmlstr);
	
};

function getdata(page){
	var ipage = $("#select-form").serialize() + "&pageNumber="+pageNumber+"&currPageNo="+ page;
	$.post(path+"/getPactWarningPage.json",ipage,function(data){
		tbodyInit(data.ipage.dataList);
		
	},"json");
};

$("#select-form").submit(function(){
	var ipage = $("#select-form").serialize() + "&pageNumber="+pageNumber;	
	$.post(path+"/getPactWarningPage.json",ipage,function(data){
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

function onExportPactWarning(){
	var index = layer.open({
		content : "正在导出合同收款预警信息，请勿关闭或刷新页面"
	});
	var ipage = $("#select-form").serialize();
	$.post(path+"/exportPactWarning.json",ipage,function(data){
		layer.close(index);
		if(data.errorcode == 'error'){
			layer.msg("导出合同收款预警信息出错",{time:1500});
		}else if(data.errorcode == '0'){
			layer.msg("不存在符合条件的记录",{time:1500});	
		}else {
			window.open(path+"/download?filePath="+data.destFile);
		}
	},"json");
	
};