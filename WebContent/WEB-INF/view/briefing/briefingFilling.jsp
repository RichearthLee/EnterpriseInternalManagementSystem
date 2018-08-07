<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 
<html>
  <head>
    <title>提交简报</title>
    <%@include file="/WEB-INF/view/head.jsp" %>
    <%@include file="/WEB-INF/view/bottom.jsp" %>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
     <script src="<%=request.getContextPath()%>/AdminLTE/plugins/iCheck/icheck.min.js"></script>
  <style>
  
   p {
	color:black;
	font-weight:600;
	margin-top:20px;
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
   
    <from class="form-horizontal" id="form_1" method="post">
    
      
      
          <p>名称：</p>
          <input id="briefingName"  name="briefingName"  class="form-control myinput" />
      


      
      
          <p>1. 今天完成了哪些工作？最有成就的是什么？</p>
          <input id="briefingQ1"  name="briefingQ1" class="form-control myinput" />
      

      
      
          <p>2. 明天你计划做什么？要完成什么目标？</p>
          <input id="briefingQ2"  name="briefingQ2" class="form-control myinput" />
      

      
      
          <p>3. 今天工作你遇到的困难是什么？希望得到哪些帮助？</p>
          <input id="briefingQ3"  name="briefingQ3" class="form-control myinput" />
      

    
      
          <p>*4. 今天工作中，有什么想要与团队分享的？</p>
          <input id="briefingQ4"  name="briefingQ4" class="form-control myinput"  />
      
      
     

      <button id="bt_1" type="button"  class="btn btn-success pull-center">提交</button>
      <button id="bt_2" type="button"  class="btn btn-success pull-center">保存草稿</button>


 </from>

      <div id="dl_1" class="droplist">
      <p><span>审评人：</span></p>
        <select id="st_1" name="briefingReviewer">
          <option value="王五">王五</option>
          <option value="赵六">赵六</option>
        </select>
      </div>


      
      <div id="dl_2" class="droplist">
      <p><span>谁可以看：</span></p>
        <select id="st_2" name="briefingInsider">
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
  		var bfdata = $("input,select,button").serialize() ;
  		bfdata =decodeURIComponent(bfdata,true);
	  	   bfdata = bfdata+"&briefingState=未读";

    	$.post("<%=request.getContextPath()%>/briefinginfoinsert.json" ,
    			bfdata,function(data) {},"json");  
    		 
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
    		//window.location.href = contextPath+"/employeeinfodelete/" + employeeId ;
    		 //序列化中文时之所以乱码是因为.serialize()调用了encodeURLComponent方法将数据编码了
            //原因：.serialize()自动调用了encodeURIComponent方法将数据编码了   
            //解决方法：调用decodeURIComponent(XXX,true);将数据解码 
          /*   var username = ${sessionScope.user.userNo};
  		     alert(username); */
  		   var bfdata = $("input,select").serialize() ;
  		   
  	  		bfdata =decodeURIComponent(bfdata,true);
  	  	   bfdata = bfdata+"&briefingState=草稿";
  	  		
  	  		//alert(bfdata);

  	    	$.post("<%=request.getContextPath()%>/briefinginfoinsert.json" ,
  	    			bfdata  ,function(data) {},"json"); 
  	    	
   
    		layer.msg("保存成功",{time:1500},function(){window.location.reload();});		
    	} 
    	else
    	{
    		layer.msg("已取消",{time:1500},function(){layer.closeAll("loading");});
    		
    		return;
    	}
    	
    	
    });
 
    
 /*    $("#bt_1").click(function(){
    layer.msg('也可以这样', {
    	       btn: ['明白了', '知道了']
    	       ,yes: function(index, layero){
    	            layer.msg("按钮1回调,参数是:"+index)
    	       }
    	      ,btn2: function(index, layero){
    	        //按钮【按钮二】的回调
    	           layer.msg("按钮2回调,参数是:"+index)
    	           return false //开启该代码可禁止点击该按钮关闭
    	     }
    	 });
  
    }); */
  
  </script>
  </body>

</html>
