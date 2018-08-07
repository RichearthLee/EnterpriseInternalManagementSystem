<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
		<%@ page import="java.util.List"%>
		<%@page import="com.mftcc.interior.sys.bean.SysUser"%>
		
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="/WEB-INF/view/head.jsp" %>
<%@include file="/WEB-INF/view/bottom.jsp" %>
<title>Add PactInfo</title>

<style type="text/css">
	.form-horizontal .form-group{
		margin-right: 0px;
		margin-left: 0px;
	}
	.myinput{
		width:80%;
		display:inline;
	}
	.mybtn{
		text-align: left;
		border-color: white;
		background: white;
		box-shadow: none;
		padding:0px 0px;
	}
</style>
</head>
<body>
 <button type="button" class="btn btn-primary" onclick="getlink();">新增菜单</button>

	<%List<SysUser> userList = (List<SysUser>)request.getAttribute("userList"); %>
	<div style="margin-top: 20px"></div>
	<div style="min-height: 400px;" >
		 <form  class="form-horizontal" id="form">
			<div class="box-bady">
				<div style="display: none;">
					<input type="text" name="customerId" id="customerId">
					<input type="text" name="beforesale" id="beforesale">
					
					
				</div>
				<label class="col-sm-2 control-label">合同编号</label>
					<div class="col-sm-4" >
						<input class="form-control myinput" name="pactId" maxlength="50" placeholder="">
					</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label">客户名称</label>
					<div class="col-sm-4">
						<div class="input-group" style="width:80%;">
						<input id="customerId" name="customerId" class="form-control" style="display:none">
							<input id="customerName" name="customerName" class="form-control">
							<span class="input-group-btn">
                     			 <button type="button" class="btn btn-default" id="customer">
                     			 	<i class="glyphicon glyphicon-zoom-in"></i>
                     			 	<font color="red">*</font>
                     			 </button>
                    		</span>
						</div>
						
					</div>
					
					
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">公司地址</label>
					<div class="col-sm-4">
						<input id="location" name="location" class="form-control myinput">
					</div>
					<label class="col-sm-2 control-label">办公电话</label>
					<div class="col-sm-4">
						<input id="tetephone" name="tetephone"
							class="form-control myinput">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">客户联系人</label>
					<div class="col-sm-4">
						<div class="input-group" style="width:80%;">
							<input id="linkman1" name="linkman1" class="form-control">
							<span class="input-group-btn">
                     			 <button type="button" class="btn btn-default" id="linkman">
                     			 	<i class="glyphicon glyphicon-zoom-in"></i>
                     			 	<font color="red">*</font>
                     			 </button>
                    		</span>
						</div>
					</div>
					<label class="col-sm-2 control-label">联系人职务</label>
					<div class="col-sm-4" >
						<input class="form-control myinput" id="linkmanTitle"  name="linkmanTitle" maxlength="50" placeholder="">
					
				</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label">手机号码</label>
					<div class="col-sm-4">
						<input id="mobilephone" name="mobilephone"
							class="form-control myinput">
							<input id="marketerId" name="marketerId"
							class="form-control myinput" style="display:none" />
					</div>
					<label class="col-sm-2 control-label">联系邮箱</label>
					<div class="col-sm-4">
						<input id="email" name="email" class="form-control myinput">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">合同金额</label>
					<div class="col-sm-4">
						<input id="pactFee" name="pactFee" class="form-control myinput" number="number_pactFee">
						<font color="red">*</font>
					</div>
					<label class="col-sm-2 control-label">客户返款金额</label>
					<div class="col-sm-4">
						<input id="customerMoney" name="customerMoney" class="form-control myinput" number="number_customerMoney">
						<font color="red">*</font>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">产品类别</label>
					<div class="col-sm-4">
						<select id="productName" name="productName"
							class="form-control myinput">
							<option>小贷</option>
							<option>担保</option>
							<option>p2p</option>
							<option>小贷+移动端</option>
							<option>p2p+移动端</option>
							<option>定制化小贷</option>
							<option>定制化p2p</option>
							<option>源代码</option>
						</select> <font color="red">*</font>
					</div>
					<label class="col-sm-2 control-label">合同签约时间</label>
					<div class="col-sm-4">
						<input id="pactStartDate" name="pactStartDate" class="form-control myinput">
						<font color="red">*</font>
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-2 control-label">合同结束时间</label>
					<div class="col-sm-4">
						<input id="pactOverDate" name="pactOverDate" class="form-control myinput">
						<font color="red">*</font>
					</div>
					<label class="col-sm-2 control-label">收费服务开始时间</label>
					<div class="col-sm-4">
						<input id="feeDate" name="feeDate" class="form-control myinput">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">售前技术人员</label>
					<div class="col-sm-4">
						<div class="input-group" style="width:80%;">
							<input id="beforesaleName"  type="text"
								class="form-control" style="background: white" readonly="readonly">
							<span class="input-group-btn">
                     			 <button type="button" class="btn btn-default " id="beforesaleBtn">
                     			 	<i class="glyphicon glyphicon-zoom-in"></i>
                     			 </button>
                    		</span>
						</div>
					</div>
					<label class="col-sm-2 control-label">合同关注人</label>
					<div class="col-sm-4 ">
						<input id="focusPersion" name="focusPersion" style="display: none;">
						<div class="input-group" style="width:80%;">
							<input id="focusPersionName"  class="form-control"  style="background: white;" readonly="readonly">
							<span class="input-group-btn">
                     			 <button type="button" class="btn btn-default" id="focusPersion-Btn">
                     			 	<i class="glyphicon glyphicon-zoom-in"></i>
                     			 </button>
                    		</span>
						</div>
						
					</div>
					
				</div>
			
			<div class="form-group" id="money_0">
				    <div class="col-sm-3">
				    	<input id="payCount_0" name="paymentPlanList[0].paymentCount" value="1" style="display: none;">
				    </div>
					<label style="text-align: right;">首批款收款时间为</label><select name="paymentPlanList[0].paymentStartDate" >
							<option value="1">合同签订后</option>
							<option value="2">实施完成后</option>
						</select>
					<input  style="width:50px;" name="paymentPlanList[0].paymentDay" number="number_0">
    				<label >天内</label>
    				<label>&nbsp; &nbsp;&nbsp;</label>
	     			<label >收款比例为总金额的</label>
					<input  style="width:50px;" name="paymentPlanList[0].paymentPercent" number="number_0" percent="percent_0">
					<label>%</label>
			</div>
			<div class="form-group" id="money_1">
				    <div class="col-sm-3" style="text-align: right;">
				    	<input name="paymentPlanList[1].paymentCount" value="2" style="display: none;">
				    	<button id="newpayment" type="button" class="btn btn-info btn-sm"><span class="glyphicon glyphicon-plus"></span>新增</button>
				    </div>
					<label style="text-align: right;">二批款收款时间为</label>
					<select name="paymentPlanList[1].paymentStartDate">
							<option value="1">合同签订后</option>
							<option value="2">实施完成后</option>
					</select>
					<input   style="width:50px;" name="paymentPlanList[1].paymentDay" number="number_1">
    				<label >天内</label>
    				<label>&nbsp; &nbsp;&nbsp;</label>
	     			<label >收款比例为总金额的</label>
					<input  style="width:50px;" name="paymentPlanList[1].paymentPercent" number="number_1" percent="percent_1">
					<label>%</label>
			</div>
			<!-- <div class="form-group" id="money_2">
				    <div class="col-sm-3" style="text-align: right;">
				    	<input name="paymentPlanList[2].paymentCount" value="3" style="display: none;">
				    	<button id="newpayment" type="button" class="btn btn-info btn-sm"><span class="glyphicon glyphicon-plus"></span>新增</button>
				    </div>
					<label style="text-align: right;">三批款收款时间为</label>
					<select name="paymentPlanList[2].paymentStartDate">
							<option value="1">合同签订后</option>
							<option value="2">实施完成后</option>
					</select>
					<input   style="width:50px;"  name="paymentPlanList[2].paymentDay" number="number_2">
    				<label >天内</label>
    				<label>&nbsp; &nbsp;&nbsp;</label>
	     			<label >收款比例为总金额的</label>
					<input  style="width:50px;" name="paymentPlanList[2].paymentPercent" number="number_2" percent="percent_2">
					<label>%</label>
			</div> -->
			
			<div class="form-group">
				<label class="col-sm-2 control-label">合同相关说明:</label>
				<textarea id="description" name="description" class="form-control"
					rows="3" placeholder="200字以内..." style="width:80%;" maxlength="200"></textarea>
			</div>
			
			<div class="form-group" style="text-align: center;">
				<!-- <input  name="pactFileList[0].pactFilepath" id="filepath_0" style="border:0;background: transparent;" readonly="readonly"> -->
				<button class="btn btn-info" type="button" id="upload"><span class="glyphicon glyphicon-upload"></span>上传合同附件</button>
			</div>
		</div>
			
	
		
	<div class="box-footer" style="text-align: center;">
		<button type="submit" id="submit" class="btn btn-success pull-center" style="width: 65px;">确定</button>
		<label>&#12288;</label>
		<button type="button" id="cancel" class="btn btn-default" style="width: 65px;">取消</button>
	</div>
	</form> 
	<div class="box-body" id="user-select" style="display: none;">
		<div class="form-group">
			<div class="checkbox" id="noSaleBefore-div" style="display: none;">
				<label> 
					<input type="checkbox" name="userSelect" value="" data-username="无售前技术人员">无售前技术人员
				</label>
			</div>
		<%for(int i=0 ;i<userList.size(); i++) {%>
			<div class="checkbox">
				<label> 
					<input type="checkbox" name="userSelect" value="<%=userList.get(i).getOpNo()%>" data-username="<%=userList.get(i).getOpName() %>"><%=userList.get(i).getOpName() %>
				</label>
			</div>
		<%} %>
		</div>
	</div>
	
</body>
 
		
	  <div  id="selectcus" class="hide">
		  <table class="table table-hover">
			<thead>
				<tr>
					<th>联系人名称</th>
				</tr>
			</thead>
			<tbody >
			</tbody>
		</table>
	 </div>
	 
	 
</html>


<%--  <script src="<%=request.getContextPath()%>/AdminLTE/plugins/iCheck/icheck.min.js"></script>
 --%> 
<script type="text/javascript">
	$("#focusPersion-Btn").click(function(){
		layer.open({
			type : 1,
			area : [ '300px', '280px' ],
			title : '请选择合同关注人', //不显示标题
			btn : [ '确定', '取消' ],
			content : $("#user-select"), //捕获的元素
			success : function(lay, index)
			{
				$("input[type=checkbox]").attr("checked",false);
			},
			yes: function(index){
				
				var length = $("input:checkbox:checked").length;
				
					var focusPersion ="";
					var focusPersionName = "";
					$("input:checkbox:checked").each(function(i){
						if(i == length-1){
							focusPersion = focusPersion + $(this).val();
							focusPersionName = focusPersionName + $(this).data("username");
						}else{
							focusPersion = focusPersion + $(this).val() + ",";
							focusPersionName = focusPersionName + $(this).data("username") + ",";
						}
						
					});
					$("#focusPersion").val(focusPersion);
					$("#focusPersionName").val(focusPersionName);
					layer.close(index);
				
						
			},
			end : function(index) {
				layer.close(index);
			}
		});
		
	});
	$(function(){
		$("#pactStartDate").datepicker();
		$("#pactOverDate").datepicker();
		
		$("#feeDate").datepicker();
	});
	
	$("#cancel").click(function(){
		window.location.href = "<%=request.getContextPath()%>/pactListInfo";
	});
	
	$("#submit").click(function(){
		if($("#pactId").val() == "" ||$("#customerName").val() == "" || $("#pactStartDate").val() == "" || $("#pactOverDate").val() == "" || $("#pactFee").val() == "" || $("#customerMoney").val() == "")
		{	
			layer.msg("必填项不能为空",{time:1500});
			return false;
		}
		/* if((parseFloat($("#pactStartDate").val())>=parseFloat($("#pactOverDate").val())) || (parseFloat($("#pactStartDate").val())>=parseFloat($("#feeDate").val())) )
		{	
			layer.msg("请确认合同结束时间或合同签约时间晚于合同签约时间！",{time:3000});
			return false;
		} */
		if($("#customerName").val().length > 50 || $("#email").val().length > 20 || $("#tetephone").val().length > 20 || $("#mobilephone").val().length > 15)
		{
			layer.msg("存在内容长度过长的输入",{time:1500});
			return false;
		}
		var number = true ;
		$("input[number^=number]").each(function(){
			var value = $(this).val();
			if( $.isNumeric(value) && value >=0)
			{
				//number = true;
			}else{
				number = false;
				layer.msg("合同金额、客户返款金额和收款计划信息请填入数字",{time:2000});
				return false;
			}
		});
		if(!number)
		{
			return false;	
		}
		var allPercent = 0;
		$("input[percent^=percent]").each(function(){
			allPercent = allPercent + parseFloat($(this).val());
		});
		if(allPercent != 100)
		{
			layer.msg("请确认收款计划金额比例达到100%",{time:2500});
			return false;
		}
		var mydata = $("form").serialize() ;//+ "&paymentPlanList="+paymentPlan+" ";
		
		$.post("pactinfoinsert.json",  mydata , function(data) {
			if (data.errorcode == 0) {
				layer.alert(data.errormessage);
				return false;
			}else{
				layer.msg("保存完成！",{time:1500},function(){
					window.location.href = "pactListInfo";
				});
			}
		}, "json");
		
		return false;	
	}); 
	 $("#beforesaleBtn").click(function(){
		 layer.open({
				type : 1,
				area : [ '300px', '280px' ],
				title : '请选择售前技术人员', //不显示标题
				btn : [ '确定', '取消' ],
				content : $("#user-select"), //捕获的元素
				success : function(lay, index)
				{
					$("input[type=checkbox]").attr("checked",false);
					$("#noSaleBefore-div").css('display','block');
				},
				yes: function(index){
					
					var length = $("input:checkbox:checked").length;
					
						var beforeSale ="";
						var beforeSaleName = "";
						$("input:checkbox:checked").each(function(i){
							if(i == length-1){
								beforeSale = beforeSale + $(this).val();
								beforeSaleName = beforeSaleName + $(this).data("username");
							}else{
								beforeSale = beforeSale + $(this).val() + ",";
								beforeSaleName = beforeSaleName + $(this).data("username") + ",";
							}
							
						});
						$("#beforesale").val(beforeSale);
						$("#beforesaleName").val(beforeSaleName);
						$("#noSaleBefore-div").css('display','none');
						layer.close(index);
					
							
				},
				end : function(index) {
					$("#noSaleBefore-div").css('display','none');
					layer.close(index);
				}
			});
	}); 
	 $("#linkman").click(function(){
		 
		 var cusId =$("#customerId").val();
		 $.ajax({
				url:"<%=request.getContextPath()%>/getAllLink.json",
				data:{customerId:cusId},
				dataType:"json",
				type:"post",
				success:function(data){
				 var data=data.data;
				// alert(data[0].linkName);
				 $("tbody").empty();
					var htmlstr = "";
					$.each(data,function(i,o){
						htmlstr += "<tr>"
					/* 	+ "	<td ><input type='hidden' id = '"+o.phone+"' >"+ o.phone+"</td>" */
						 /* getEmail getPhone getTelphone getTitle */
						/*  onclick='update(\""+o.empNo+"\",\""+o.yearMonth+"\");' >修改</a></td>" */ 
					 +	"<td><a href='javascript:void(0);' onclick='getlink(\""+o.linkName+"\",\""+o.phone+"\",\""+o.title+"\",\""+o.telphone+"\");' > "+o.linkName+"</a> </td>"
					 			+  "</tr>";
					});
				 $("tbody").html(htmlstr);
				},
				error:function(){
					   layer.msg('添加客户失败！',  {time:500} );
				}
			});   // ajax 结束
			
			
			$('#selectcus').removeClass("hide");
			
			   index =  layer.open({
						    type: 1,
					        title: '选择联系人',
					        offset:'50px',
					        maxmin: true,
					        shadeClose: true, //点击遮罩关闭层
					        area : ['40%' , '50%'],
							content : $('#selectcus'), //捕获的元素
							yes: function(index, layero){
							    //do something
							    layer.close(index); //如果设定了yes回调，需进行手工关闭
							  },
							cancel : function(index) {
								layer.close(index);
								$('#selectcus').addClass("hide"); //取消继续隐藏
							} 
						});
		 
		});     //  click 结束
		 
		 var index ;
		function getlink(linkName,phone,title,telphone) {
			
		 
			 $("#linkman1").val(linkName);
        	$("#tetephone").val(phone);
        	$("#mobilephone").val(telphone);
        	$("#linkmanTitle").val(title);
        	
        	layer.close(index);
			$('#selectcus').addClass("hide"); //取消继续隐藏
			

		}
	 
	 
	 $("#customer").click(function(){
		  layer.open({
		        type: 2,
		        title: '选择客户',
		        offset:'50px',
		        maxmin: true,
		        shadeClose: true, //点击遮罩关闭层
		        area : ['80%' , '90%'],
		        
		        content: '<%=request.getContextPath()%>/getcustomerinfo',
		        success: function(){
		        	top.customerbean = null;
		        },
		        end: function(){
		        	if (!top.customerbean) {
						return;
					}
		        	$("#customerId").val(top.customerbean.customerId);
		        	$("#customerName").val(top.customerbean.customerName).attr("readonly", true);	 
		        	$("#location").val(top.customerbean.location);
		        }
		    });
	}); 
	$("#newpayment").click(function(){
		var length = $("div[id^=money]").length;
		var current = length -1;
		re = new RegExp("0","g");
		var htmlStr = $("div[id=money_0]").prop("outerHTML").replace(re,""+length+"");
		var htmlStr = htmlStr.replace("首","下");
		$("div[id=money_"+current+"]").after(htmlStr); //.attr("id","money_"+length+"");
		$("input[id=payCount_"+length+"]").val(length+1);
		
	});
	
	$("#upload").on("click",function(){
		uploadOneFile(function (file) {
			// 获取到上传文件保存后的地址。
			if(file){
				var length = $("input[id^=filepath]").length;
				htmlStr = '<input name="pactFileList[0].pactFilepath" id="filepath_0" style="display:none;" readonly="readonly"> <i id="show_0" class="fa fa-fw fa-file-text-o"></i>';
				
				re = new RegExp("0","g");
				htmlStr = htmlStr.replace(re,""+length+"");
				$("#upload").after(htmlStr); 
				$("input[id=filepath_"+length+"]").val(file);
				$("i[id=show_"+length+"]").attr({"title":file});
			}
		});
	});
	
 </script>