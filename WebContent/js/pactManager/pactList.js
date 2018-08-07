var pageNumber=10;
var contextPath;
function InitPactList(getcontextPath){
	contextPath = getcontextPath;
	var ipage = $("form").serialize() + "&currPageNo=1&pageNumber="+pageNumber;
	$.post(contextPath+"/getPactListPage.json",ipage,function(data){
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
		htmlstr += "<tr>"
				+	"<td>"+o.customerName+"</td>"
				+	"<td>"+o.linkman+"</td>"
				+	"<td>"+o.productName+"</td>"
				+	"<td>"+o.pactFee+"</td>"
				+	"<td>"+o.paypercent+"%</td>"
				+	"<td>"+o.pactDate+"</td>"
				+	"<td>"+o.marketerName+"</td>"
				+	"<td><a href='javascript:void(0);' onclick='toDetail(\""+o.pactId+"\",\""+o.customerId+"\");' >详情</a></td>"
				+	"</tr>";
	});
	$("tbody").html(htmlstr);
}

function getdata(page){
	var ipage = $("form").serialize() + "&pageNumber="+pageNumber+"&currPageNo="+ page;
	$.post(contextPath+"/getPactListPage.json",ipage,function(data){
		tbodyInit(data.ipage.dataList);
		
	},"json");
};
/*$("#search-btn").click(function(){
	var ipage = $("form").serialize() + "&pageNumber="+pageNumber;	
	$.post(contextPath+"/getPactListPage.json",ipage,function(data){
		$("#page-div").createPage({
	        pageCount:data.ipage.totalPage,
	        current:1,
	        backFn:function(p){
	            getdata(p);
        	}
		});
		tbodyInit(data.ipage.dataList);
		},"json");
});*/

$("#selectPact-form").submit(function(){
	var ipage = $("#selectPact-form").serialize() + "&pageNumber="+pageNumber;	
	$.post(contextPath+"/getPactListPage.json",ipage,function(data){
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

function selectPact(){
	var ipage = $("form").serialize() + "&pageNumber="+pageNumber;	
	$.post(contextPath+"/getPactListPage.json",ipage,function(data){
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
$("#newpact").click(function(){
	window.location.href = "pactinfoadd";
});
$("#newcustomer").click(function(){
	window.location.href = "customerAdd";
});
function toDetail (pactId, customerId) {
	layer.load();
	window.location.href = contextPath+"/pactFullInfo/" + pactId + "/" + customerId;
}