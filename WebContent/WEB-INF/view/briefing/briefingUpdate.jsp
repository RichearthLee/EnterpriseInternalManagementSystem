<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    	<%@ page import="java.util.List"%>
		<%@page import="com.mftcc.interior.oa.briefing.bean.BriefingInfo"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>briefingUpdate</title>
<% BriefingInfo briefingInfo = (BriefingInfo)request.getAttribute("briefingInfo"); %>
<%@include file="/WEB-INF/view/head.jsp" %>
    <%@include file="/WEB-INF/view/bottom.jsp" %>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
     <script src="<%=request.getContextPath()%>/AdminLTE/plugins/iCheck/icheck.min.js"></script>
  <style>
  h2{
  color:black;
  font-weight:600;
  text-align: center;
  margin:5px;
  }
   p {
	color:black;
	font-weight:600;
	margin-top:10px;
	}
	#briefingName{
	width:100px;
	}

   input {
	width:700px;
	height:20px;
   }
   button{
   height:40px;
   width:120px;
   margin-bottom:30px;
   margin-top:20px;
   margin-left:60px; 
   border-radius:10px;
   background-color: #2D93CA;
   }
  select{
  width:120px;
  height:27px;
  border-radius:5px;
  }
  .droplist{
  margin-top:70px;
  margin-left:30px;
  float:left;
  }
  </style>
  
  
  
  </head>
  <body>
  <div id="context" class="content">
  
  <div><h2>编辑草稿</h2></div>
   
    <from class="form-horizontal" id="form_1" method="post">
     <p>编号：<%=briefingInfo.getBriefingId() %></p>
    
      
      
          <p>名称：</p>
          <input id="briefingName" value="<%=briefingInfo.getBriefingName() %>" name="briefingName"  class="form-control myinput" />
      


      
      
          <p>1. 今天完成了哪些工作？最有成就的是什么？</p>
          <input id="briefingQ1" value="<%=briefingInfo.getBriefingQ1() %>"  name="briefingQ1" class="form-control myinput" />
      

      
      
          <p>2. 明天你计划做什么？要完成什么目标？</p>
          <input id="briefingQ2" value="<%=briefingInfo.getBriefingQ2() %>"  name="briefingQ2" class="form-control myinput" />
      

      
      
          <p>3. 今天工作你遇到的困难是什么？希望得到哪些帮助？</p>
          <input id="briefingQ3" value="<%=briefingInfo.getBriefingQ3() %>"  name="briefingQ3" class="form-control myinput" />
      

    
      
          <p>*4. 今天工作中，有什么想要与团队分享的？</p>
          <input id="briefingQ4" value="<%=briefingInfo.getBriefingQ4() %>" name="briefingQ4" class="form-control myinput"  />
      
      
     

      <button id="bt_1" type="button" class="btn btn-success pull-center">提交</button>
      <button id="bt_2" type="button" class="btn btn-success pull-center">保存草稿</button>


 </from>

      <div id="dl_1" class="droplist">
      <p><span>审评人：</span></p>
        <select id="st_1" name="briefingReviewer">
          <option style="background-color:#AAAAAA" value="<%=briefingInfo.getBriefingReviewer()%>"><%=briefingInfo.getBriefingReviewer()%></option>
          <option value="王五">王五</option>
          <option value="赵六">赵六</option>
        </select>
      </div>


      
      <div id="dl_2" class="droplist">
      <p><span>谁可以看：</span></p>
        <select id="st_2" name="briefingInsider" >
          <option value="<%=briefingInfo.getBriefingInsider()%>"><%=briefingInfo.getBriefingInsider()%></option>
          <option value="张三">张三</option>
          <option value="李四">李四</option>
        </select>
      </div>
      
   
    
    </div>
  <script src="<%=request.getContextPath()%>/AdminLTE/plugins/iCheck/icheck.min.js"></script>
  <script type="text/javascript">
    
/*   $("#briefing_Createtime").value(function show(){
	   var mydate = new Date();
	   var str = mydate.toLocaleDateString();
	   str += mydate.toLocaleTimeString();
	   return str;
	  });
   */
  $("#bt_1").click(function(){
  	
  	layer.load();
  	if(confirm("确认提交吗")){
  		//window.location.href = contextPath+"/employeeinfodelete/" + employeeId 
  		var bfdata = $("input,select").serialize() ;
  		bfdata =decodeURIComponent(bfdata,true);
  		
  		bfdata +="&briefingId="+<%=briefingInfo.getBriefingId()%>+"&briefingState=未读";
  		alert(bfdata);

    	$.post("<%=request.getContextPath()%>/briefinginfoupdate.json" ,
    			bfdata  ,function(data) {},"json");  
    		 
  		layer.msg("提交成功",{time:1500},function(){window.location.reload();});
  		//window.location.reload();
  	} 
  	else
  	{
  		  layer.msg("已取消",{time:1500},function(){layer.closeAll("loading");});
  		  return;
  	}
  	 	
  }); 
  
  
  $("#bt_2").click(function(){
    	layer.load();
    	if(confirm("确认保存吗")){
    		
    	
   
    		layer.msg("保存成功",{time:1500},function(){window.location.reload();});		
    	} 
    	else
    	{
    		layer.msg("已取消",{time:1500},function(){layer.closeAll("loading");});
    		
    		return;
    	}
    	
    	
    });
 
    
 
  
  </script>
  </body>

</html>
