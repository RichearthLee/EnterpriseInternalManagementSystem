
var pageNumber=10;
var contextPath;
function InitPactList(getcontextPath){
	contextPath = getcontextPath;
	var ipage =  "&currPageNo=1&pageNumber="+pageNumber;
	$.post(contextPath+"/interfaceTestPage.json",ipage,function(data){
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
			var change=o.ischange;
			if(change>0){
				htmlstr += "<tr>"
					+	"<td>"+o.id+"</td>"
					+	"<td>"+o.method+"</td>"
					+	"<td>"+o.url+"</td>"
					+	"<td><span style='color:red;'>异常</span></td>"
					+	"<td><a href='javascript:void(0);' onclick='toDetail(\""+o.id+"\");' >详情</a></td>"
					+	"</tr>";
			}			
			$("tbody").html(htmlstr)
			
		});
	    
	    $.each(data,function(i,o){	
			var change=o.ischange;
			 if(change==0){
					htmlstr += "<tr>"
						+	"<td>"+o.id+"</td>"
						+	"<td>"+o.method+"</td>"
						+	"<td>"+o.url+"</td>"
						+	"<td>正常</td>"
						+	"<td><a href='javascript:void(0);' onclick='toDetail(\""+o.id+"\");' >详情</a></td>"
						+	"</tr>";				
				}	
			$("tbody").html(htmlstr)
			
		});
	   
		

}

function getdata(page){
	var ipage = "&pageNumber="+pageNumber+"&currPageNo="+ page;
	$.post(contextPath+"/interfaceTestPage.json",ipage,function(data){
		tbodyInit(data.ipage.dataList);
		
	},"json");
};
function toDetail(id) {
	layer.load();
	window.location.href = contextPath+"/interfaceDetail/"+id;
}