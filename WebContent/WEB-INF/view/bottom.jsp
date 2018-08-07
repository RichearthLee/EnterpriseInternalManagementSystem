<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
var contextPath = "<%=request.getContextPath()%>";
</script>
<script src="<%=request.getContextPath()%>/js/jquery-1.11.3.min.js"></script>
<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.js"></script>
<script src="<%=request.getContextPath()%>/bootstrap/plugin/bootstrap-paginator.js"></script>
<!-- 基础工具类方法的JS -->
<script src="<%=request.getContextPath()%>/js/UTIL.js"></script>
<!-- AdminLTE App (the fixed layout requires the slimscroll plugin)-->
<script src="<%=request.getContextPath()%>/AdminLTE/plugins/slimScroll/jquery.slimscroll.min.js"></script>
<script src="<%=request.getContextPath()%>/AdminLTE/js/app.min.js"></script>
<!-- layer v2.1 -->
<script src="<%=request.getContextPath()%>/plugins/layer-v2.1/layer/layer.js"></script>
<!-- datepicker 及 中文支持 -->
<script src="<%=request.getContextPath()%>/AdminLTE/plugins/datepicker/bootstrap-datepicker.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/AdminLTE/plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js" type="text/javascript"></script>
<!-- 文件上传组件 -->
<script src="<%=request.getContextPath()%>/js/uploadFile.js"></script>
<!-- 自动分页的插件 -->
<script src="<%=request.getContextPath() %>/AdminLTE/plugins/datatables/jquery.dataTables.min.js"></script>
<script src="<%=request.getContextPath() %>/AdminLTE/plugins/datatables/dataTables.bootstrap.min.js"></script>
<!--分页插件  -->
<script src="<%=request.getContextPath()%>/js/jquery.page.js"></script>
<!--T tree 插件  -->
<script  src="<%=request.getContextPath()%>/plugins/zTree_v3/js/jquery.ztree.core-3.5.js"></script>
<script src="<%=request.getContextPath()%>/plugins/zTree_v3/js/jquery.ztree.excheck-3.5.js"></script>
<script src="<%=request.getContextPath()%>/plugins/zTree_v3/js/jquery.ztree.exedit-3.5.js"></script>
<!-- 开关 Switch 控件 -->
<script src="<%=request.getContextPath()%>/bootstrap/switch/js/bootstrap-switch.min.js"></script>
	
 
 
<script type="text/javascript" >
	// 执行页面上 下拉框的数据字典   第一个参数是 数据字典类型，第二个参数是输入框的select绑定的id 
function selectdicType(dicType,dicSelect){
	 
   $.ajax({
  	url:"<%=request.getContextPath()%>/getSysDicInRedis.json",
  	data:{dicType:dicType},
  	dataType:"json",
  	type:"post",
  	success:function(data){
  		var dat = data.sysDictionaryList;
		 		$.each(dat,function(i, o) {
				// 添加节点，设置value和text创建选项
				var para=document.createElement("option");               //创建一个option节点
				var node=document.createTextNode(o.dicValue);
				para.setAttribute("value", o.dicKey);
				para.appendChild(node);
				var element=document.getElementById(dicSelect);
				element.appendChild(para);  
		 		});
 		  },
  	error:function(){
  		   layer.msg('数据字典加载失败！',  {time:500} );
  	}
  });
}; 


//执行页面上 table的数据字典   第一个参数是 数据字典key，也就是类的属性
function selectdicValue(dicKey){
	 var dicclass ="."+dicKey;
		  $.ajax({
				url:"<%=request.getContextPath()%>/getSysDicInRedis.json",
				data:{dicKey:dicKey},
				dataType:"json",
				type:"post",
			//	async:false,
				success:function(data){
					var dat = data.sysDictionaryList;
					 var result =  dat[0].dicValue;
				 
					 $(dicclass).html(result);
				},
				error:function(){
					   layer.msg('数据字典查询失败！',  {time:500} );
				}
			});  
};  


$(function( ) {

	
	   
	} ) ;



</script>