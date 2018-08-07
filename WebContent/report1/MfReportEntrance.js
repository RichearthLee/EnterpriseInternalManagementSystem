top.brNoData;
var queryConditionTop = 0;
var queryConditionLeft = 0;
var underQueryConditionTop = 0;
var inputClickFlag = false;
var ReportEntrance = function(window,$){
	var _init = function(){
		$("body").mCustomScrollbar({
			advanced:{
				updateOnContentResize:true
			}
		});	
		
		$.each(tatolList,function(i,cusTable){
			var itemId = cusTable.itemId;
			var flag = cusTable.attentionFlag;
			if(flag == "1"){//为选中
				$("#"+itemId).css("display","block");
			}else{//已选中		
				$("#"+itemId).css("display","none");
			}
		});
		
		$("#addQuery").click(function(){
			top.itemId="";
			top.flag=false;
			top.window.openBigForm("MfReportFilterAction_getFilter.action?funcType="+"1","台账查询设置",function(){
				if(top.flag){
						_addItemCallBack("1");
					}
				});
		});
		$("#addTotalQuery").click(function(){
			top.itemId="";
			top.flag=false;
			top.window.openBigForm("MfReportFilterAction_getFilter.action?funcType="+"3","统计查询设置",function(){
				if(top.flag){
						_addItemCallBack("3");
					}
				});
		});
		
		//控制部门和操作员的只读属性
		if(funRoleType == '1'){//本人
			$('#brNo').attr("readonly","readonly");
			$('#brName').attr("readonly","readonly");
			$('#opNo').attr("readonly","readonly");
			$('#opName').attr("readonly","readonly");
		}else if(funRoleType == '2'){//本部门
			$('#brNo').attr("readonly","readonly");
			$('#brName').attr("readonly","readonly");
		}
		
		//部门弹窗显示
		$('#brName').on('click', function(){
			if(funRoleType != '1' && funRoleType != '2'){//非本人或本部门时
				openDialogShowValue('1','brNo','brName');
			}
		});
		
		//管户机构弹窗显示
		$('#manageBrName').on('click', function(){
			if(funRoleType != '1' && funRoleType != '2'){//非本人或本部门时
				openDialogShowValue('12','manageBrNo','manageBrName');
			}
		});
		
		//操作员弹窗显示
		$('#opName').on('click', function(){
			if(funRoleType != '1'){//非本人
				openDialogShowValue('2','opNo','opName');
			}
		});
		//客户经理弹窗显示
		$('#manageOpName').on('click', function(){
			if(funRoleType != '1'){//非本人
				openDialogShowValue('13','manageOpNo','manageOpName');
			}
		});
		
		//数据类型弹窗显示
		$('#numType').on('click', function(){
			openDialogShowValue('10','numTypeNo','numType');
		});
		
		//客户类型弹窗显示
		$('#cusType').on('click', function(){
			openDialogShowValue('3','cusTypeNo','cusType');
		});
			//业务品种弹窗显示
		$('#kindType').on('click', function(){
			openDialogShowValue('4','kindNo','kindType');
		});
		 
		
		//借据选择
		$('#appName').on('click', function(){
			openDialogShowValue('7','fincId','appName');
		});

		//客户姓名
		$('#cusName').on('click', function(){
			openDialogShowValue('8','cusNo','cusName');
		});
		
		//押品类别弹窗显示 
		$('#pledgeClass').on('click', function(){
			openDialogShowValue('5','pledgeClassNo','pledgeClass');
		});
		
		//信息来源弹窗显示
		$('#dataSource').on('click', function(){
			openDialogShowValue('6','dataSourceNo','dataSource');
		});
		
		//渠道名称
		$('#channelSourceName').on('click', function(){
			openDialogShowValue('9','channelSourceNo','channelSourceName');
		});
		
		//单击台账查询,统计报表区域,隐藏条件
		$('.row.info-title').click(function(){
			//$('.search_con').hide();
		});
		
		$('.leftBorderDiv').click(function(){
			_clearAllInputValue();//清空数据
			_hideQueryTr();
			_dealSaveFlag();
			//var X = $(this).offset().top;
			//var parentX = $(this).parent().parent().position().top;
			var parentX = $(this).parent().position().top;
			var Y = $(this).offset().left+1;
			$('.ui-btn-menu .search_con').css({position:"absolute"});
			$('.ui-btn-menu .search_con').css("left",Y-1);
			var infoContentId = $(this).parents(".row .info-content").attr('id');
			if(infoContentId == 'StandingBookReportDiv'){//台账查询
				$('.ui-btn-menu .search_con').css("top",parentX+68);
			}else if(infoContentId == 'countReportDiv'){//统计报表
				$('.ui-btn-menu .search_con').css("top",parentX+68);
			}
			var reportId = $(this).attr('reportid');
			_showQueryMessage(reportId);
		
			_showFormConditionVal(reportId);
			_popQueryDiv($('.ui-btn-menu .menu-btn'));
			$('.search_con').show();
			iObj = $(this);
			queryConditionTop = $(this).offset().top;
			underQueryConditionTop = $(this).offset().top+65;
			queryConditionLeft = Y;
			if(reportId=='report-loan-loanStatus' && $('#month').val()==''){//贷款情况汇总表中,月份默认上个月
				$('#month').val(lastMonth);
			}
			if(reportId=='report-loan-loanCnt' && $('#month').val()==''){//贷款累放累月月报表,月份默认当前月
				$('#month').val(currMonth);
			}
		});
		
		//隐藏查询条件div
		$('body').click(function(e){
			if($('.modal-content').length<=0 && !inputClickFlag){
				var queryConditionBottom = queryConditionTop+65;
				var queryConditionRight = queryConditionLeft+65;
				e = e || window.event;
	            _xx = e.pageX || e.clientX + document.body.scroolLeft;//获取鼠标坐标
	            _yy = e.pageY || e.clientY + document.body.scrollTop;
	            var inConditionFlag = false;
				if(_xx>=queryConditionLeft && _xx<=queryConditionRight 
						&& _yy>=queryConditionTop && _yy<=queryConditionBottom){//判断鼠标位置是不是在图标div里
					inConditionFlag = true;
				}else{
					//不在图标div里，再判断是不是在条件div里
					var divTop = underQueryConditionTop;
					var divBottom = divTop+$('.search_con').height()+32;
					queryConditionRight = queryConditionLeft+340;
					if(_xx>=queryConditionLeft && _xx<=queryConditionRight 
							&& _yy>=divTop && _yy<=divBottom){//判断鼠标位置是不是在条件div里
						inConditionFlag = true;
					}
					queryConditionTop = divTop;
					queryConditionBottom = divBottom;
				}
				if(!inConditionFlag){
					$('.search_con').hide();
				}
			}
			//风险日报城市弹出层
			if($('.pops-bg').css('display')!='block'){
				inputClickFlag = false;
			}
		});
		
		$("input").click(function(){
			inputClickFlag = true;
		});
		
		cintyInit();
		
		$("input[name=cityName]").click(function(){
			$(".pops-value").click();
			inputClickFlag = true;
		});
		
		
		
	};
	var _openInit = function(){
		//控制部门和操作员的只读属性
		if(funRoleType == '1'){//本人
			$('#brNo').attr("readonly","readonly");
			$('#brName').attr("readonly","readonly");
			$('#opNo').attr("readonly","readonly");
			$('#opName').attr("readonly","readonly");
		}else if(funRoleType == '2'){//本部门
			$('#brNo').attr("readonly","readonly");
			$('#brName').attr("readonly","readonly");
		}
		
		//部门弹窗显示
		$('#brNoButton').on('click', function(){
			if(funRoleType != '1' && funRoleType != '2'){//非本人或本部门时
				openDialogShowValueSearch('1','brNo','brName');
			}
		});
		
		$('#brNoDiv').on('click', function(){
			if(funRoleType != '1' && funRoleType != '2'){//非本人或本部门时
				openDialogShowValueSearch('1','brNo','brName');
			}
		});
		
		//管户机构弹窗显示
		$('#manageBrNoButton').on('click', function(){
			if(funRoleType != '1' && funRoleType != '2'){//非本人或本部门时
				openDialogShowValueSearch('12','manageBrNo','manageBrName');
			}
		});
		$('#manageBrNoDiv').on('click', function(){
			if(funRoleType != '1' && funRoleType != '2'){//非本人或本部门时
				openDialogShowValueSearch('12','manageBrNo','manageBrName');
			}
		});
		
		//操作员弹窗显示
		$('#opNoButton').on('click', function(){
			if(funRoleType != '1'){//非本人
				openDialogShowValueSearch('2','opNo','opName');
			}
		});
		$('#opNoButtonDiv').on('click', function(){
			if(funRoleType != '1'){//非本人
				openDialogShowValueSearch('2','opNo','opName');
			}
		});
		//客户经理弹窗显示
		$('#manageOpNoButton').on('click', function(){
			if(funRoleType != '1'){//非本人
				openDialogShowValueSearch('13','manageOpNo','manageOpName');
			}
		});
		
		$('#manageOpNoDiv').on('click', function(){
			if(funRoleType != '1'){//非本人
				openDialogShowValueSearch('13','manageOpNo','manageOpName');
			}
		});
		//客户姓名
		$('#cusNameButton').on('click', function(){
			openDialogShowValue('8','cusNo','cusName');
		});
		$('#cusNameDiv').on('click', function(){
			openDialogShowValue('8','cusNo','cusName');
		});
		//渠道名称
		$('#channelSourceNameButton').on('click', function(){
			openDialogShowValue('9','channelSourceNo','channelSourceName');
		});
		$('#channelSourceNameDiv').on('click', function(){
			openDialogShowValue('9','channelSourceNo','channelSourceName');
		});

		//数据类型弹窗显示
		$('#numType').on('click', function(){
			openDialogShowValue('10','numTypeNo','numType');
		});
		//客户类型弹窗显示
		$('#cusTypeButton').on('click', function(){
			openDialogShowValue('3','cusTypeNo','cusType');
		});
		$('#cusTypeDiv').on('click', function(){
			openDialogShowValue('3','cusTypeNo','cusType');
		});
			//业务品种弹窗显示
		$('#kindTypeButton').on('click', function(){
			openDialogShowValueSearch('4','kindNo','kindType');
		});
		 
		$('#kindTypeDiv').on('click', function(){
			openDialogShowValueSearch('4','kindNo','kindType');
		});
		 
		
		//借据选择
		$('#appNameButton').on('click', function(){
			openDialogShowValue('7','fincId','appName');
		});
		$('#appNameDiv').on('click', function(){
			openDialogShowValue('7','fincId','appName');
		});
		 
		//押品类别弹窗显示 
		$('#pledgeClassButton').on('click', function(){
			openDialogShowValue('5','pledgeClassNo','pledgeClass');
		});
		$('#pledgeClassDiv').on('click', function(){
			openDialogShowValue('5','pledgeClassNo','pledgeClass');
		});
		
		//信息来源弹窗显示
		$('#dataSourceButton').on('click', function(){
			openDialogShowValue('6','dataSourceNo','dataSource');
		});
		$('#dataSourceDiv').on('click', function(){
			openDialogShowValue('6','dataSourceNo','dataSource');
		});

		//单击台账查询,统计报表区域,隐藏条件
		$('.row.info-title').click(function(){
			//$('.search_con').hide();
		});
		
//		$('.leftBorderDiv').click(function(){
			_clearAllInputValue();//清空数据
			_hideQueryTr();
			_dealSaveFlag();
			_showOpenQueryMessage(reportId);
			_showOpenFormConditionVal(reportId);
			_popQueryDiv($('.ui-btn-menu .menu-btn'));
			$('.search_con').show();
			iObj = $(this);
//			queryConditionTop = $(this).offset().top;
//			underQueryConditionTop = $(this).offset().top+65;
//			queryConditionLeft = Y;
			if(reportId=='report-loan-loanStatus' && $('#month').val()==''){//贷款情况汇总表中,月份默认上个月
				$('#month').val(lastMonth);
			}
			if(reportId=='report-loan-loanCnt' && $('#month').val()==''){//贷款累放累月月报表,月份默认当前月
				$('#month').val(currMonth);
			}
//		});
		$("input").click(function(){
			inputClickFlag = true;
		});
		cintyInit();
		$("input[name=cityName]").click(function(){
			$(".pops-value").click();
			inputClickFlag = true;
		});
	};
	//弹出列表,回显值
	var openDialogShowValue = function(itemsno,noObj,nameObj){
		openConditionListDialog(itemsno, function(data){
			//debugger;
			if(data){
				if(data.no!=''){
					if(noObj=='brNo'){//选择部门时,清空操作员信息
						if(data.no!=$('#'+noObj).val()){
							$('#opNo').val('');
							$('#opName').val('');
						}
					}
					$('#'+noObj).val(data.no);
					$('#'+nameObj).val(data.name);
					top.brNoData = '';
				}else{
					$('#'+noObj).val('');
					$('#'+nameObj).val('');
				}
			}
		});
	};
	//弹出列表,回显值
	var openDialogShowValueSearch = function(itemsno,noObj,nameObj){
		openConditionListDialog(itemsno, function(data){
			//debugger;
			if(data){
				if(data.no!=''){
					if(noObj=='brNo'){//选择部门时,清空操作员信息
						if(data.no!=$('#'+noObj).val()){
							$('#opNo').val('');
							$('#opName').val('');
											
						}
					}
					$('#'+noObj).val(data.no);
					$('#'+nameObj).val(data.name);
					var name=data.name.split(",");
					var html="";
					for ( var int = 0; int < name.length; int++) {
						html=html+"<span>"+name[int]+"</span>";
					}
					$('#'+nameObj).next().html(html);
					top.brNoData = '';
					_resetOpNo();
					
					
					$("li").each(function(index, element){
						debugger;
						 //设置是否显示更多
						if(!$(this).is(":hidden")){
							
							var tmpWidth=$(this).width();
							tmpWidth=tmpWidth*0.78;
							var tmpLen=0;
							$(this).find("span").each(function(){
								//debugger;
								tmpLen+=$(this).width();
							});
							
							if(tmpWidth>=tmpLen){
								$(this).find("a").css("visibility","hidden");
							}else{
								$(this).find("a").css("visibility","visible");
							}
						}
						
					});
				}else{
					$('#'+noObj).val('');
					$('#'+nameObj).val('');
					
					

					if(noObj=='brNo'){//选择部门时,清空操作员信息
						if(data.no!=$('#'+noObj).val()){
							$('#opNo').val('');
							$('#opName').val('');
											
						}
					}
				
					var html="&nbsp;</br>&nbsp;";
				
					$('#'+nameObj).next().html(html);
					top.brNoData = '';
					_resetOpNo();
					
					
				
				}
			}
		});
	};
	//保存查询条件
	var _saveSqlCondition = function(reportId){
			var url = "MfReportQueryConditionUserActionAjax_saveReoprtSqlCondition.action";
			var subArr=[];
			$("input").each(function(index, element){
			
				if($(this).attr('saveFlag') == '1' && $(this).parent().css("display")!='none' ){
					var subObj = {};
					if($(this).attr('noFlag') == '1'){
						subObj.value= $(this).val();
						subObj.nameValue= $(this).next().val();
					}else{
						subObj.value= $(this).val();
						subObj.nameValue=$(this).val();
					}
					var inputId = $(this).attr('id');
					if(inputId == 'date' || inputId == 'beginDate' || inputId == 'endDate'){
						subObj.value= $(this).val().replace(new RegExp("-","gm"),"");//替换日期之间的横杠
					}
					subArr.push(subObj);
				}
			});
		    var condition = JSON.stringify(subArr);
			//LoadingAnimate.start();
			jQuery.ajax({
				url:url,
				data:{reportId:reportId,sqlCondition:condition},
				type:"POST",
				dataType:"json",
				beforeSend:function(){  
				},success:function(data){
					//LoadingAnimate.stop();
					if(data.flag == "success"){
					}else if(data.flag == "error"){
					}
				},error:function(data){
					//LoadingAnimate.stop();
				}
			});
	};
	
	//查询追加条件,同时弹出报表
	var _reportQuery = function(type,obj,id,title){
		if(type=='1'){
			_openBase(obj,id,title);
		}else{
			_openReport(obj,id,title);
		}
	};
	
	//展示查询条件值
	_showFormConditionVal = function(id){
		jQuery.ajax({
			url:"MfReportQueryConditionUserActionAjax_showFormConditionVal.action",
			data:{reportId:id},
			type:"POST",
			dataType:"json",
			async: false,
			beforeSend:function(){  
			},success:function(data){
				if(data.flag == "success"){
					var result = data.result;
					debugger;
					if(result!=''){
						var jsonObj =  JSON.parse(result);//转换为json对象
						var num = 0;
						$("input").each(function(index, element){
							if($(this).attr('noFlag') == '0' && $(this).parent().css("display")!='none') {
								if($(this).attr('saveFlag') == '0'){
									$(this).prev().val(jsonObj[num].value);
									$(this).val(jsonObj[num].nameValue);									var inputId = $(this).attr('id');
								}else{
									var inputId = $(this).attr('id');
									if(inputId == 'date' || inputId == 'beginDate' || inputId == 'endDate'){
										$(this).val(_formatDate(jsonObj[num].value));
									}else{
										$(this).val(jsonObj[num].value);
									}
								
								}
								num++;
							}
						});
					}
				}else if(data.flag == "error"){
				}
			},error:function(data){
			}
		});
	};
	//展示查询条件值
	_showOpenFormConditionVal = function(id){
		jQuery.ajax({
			url:"MfReportQueryConditionUserActionAjax_showFormConditionVal.action",
			data:{reportId:id},
			type:"POST",
			dataType:"json",
			async: false,
			beforeSend:function(){  
			},success:function(data){
				if(data.flag == "success"){
					var result = data.result;
					if(result!=''){
						var jsonObj =  JSON.parse(result);//转换为json对象
						var num = 0;
						
						$("input").each(function(index, element){
							if($(this).attr('noFlag') == '0' &&( $(this).parent().css("display")!='none' || $(this).parent().attr("showflag")=='1')) {
								if($(this).attr('saveFlag') == '0'){
									$(this).prev().val(jsonObj[num].value);
									$(this).val(jsonObj[num].nameValue);
									var name=jsonObj[num].nameValue.split(",");
									var html="";
									
									for ( var int = 0; int < name.length; int++) {
										html=html+"<span>"+name[int]+"</span>";
									}
									if(jsonObj[num].nameValue==""){
										
										$(this).next().html("&nbsp;</br>&nbsp;");
									}else{
										
										$(this).next().html(html);
									}
								}else{
									var inputId = $(this).attr('id');
									if(inputId == 'date' || inputId == 'beginDate' || inputId == 'endDate'){
										$(this).val(_formatDate(jsonObj[num].value));
									}else{
										$(this).val(jsonObj[num].value);
									}
								
								}
								
								num++;
							}
						});
						
						$("li").each(function(index, element){
							 //设置是否显示更多
							if(!$(this).is(":hidden")){
								
								var tmpWidth=$(this).width();
								tmpWidth=tmpWidth*0.78;
								var tmpLen=0;
								$(this).find("span").each(function(){
									//debugger;
									tmpLen+=$(this).width();
								});
								
								if(tmpWidth>=tmpLen){
									$(this).find("a").css("visibility","hidden");
								}else{
									$(this).find("a").css("visibility","visible");
								}
							}
							
						});
					}
					
				}else if(data.flag == "error"){
				}
			},error:function(data){
			}
		});
	};
	var _formatDate = function (date) {
			if(date.length==8){
				return date.substring(0,4)+"-"+date.substring(4,6)+"-"+date.substring(6,8);
			}else{
				return date;
			}
		}

	var _openBase = function(obj,id,title){
		var jspUrl = "/factor/component/report/MfReportEntranceOpen.jsp?&reportId="+id+"&reporttype=D"+"&funRoleType="+funRoleType;
				switch (id) {
				case "report-cus-basedata":
					top.openBigForm(jspUrl+"&uid=bdcbb4ca4eb25b4aba342fc88262aaef","员工请假信息查询",function(){},"90","90");
					break;
				case "report-pledge-basedata":
					top.openBigForm(jspUrl+"&uid=6166aad9086ed0a9928b2fea15467e10","押品基本信息查询",function(){},"90","90");			
					break;
				case "pactSts":
					top.openBigForm(jspUrl+"&uid=0ec0169a0a3304f8344b787beab14a01","合同状态查询",function(){},"90","90");
					break;
				case "report-bo-handle-stage":
					top.openBigForm(jspUrl+"&uid=535620a2b1cfbe650fd875f11adb6ed7","贷款办理阶段查询",function(){},"90","90");
					break;				
				case "report-cus-class":
					top.openBigForm(jspUrl+"&uid=c40a78d685e2979113f1afa5f6cba65a","客户分类查询",function(){},"90","90");
					break;				
				case "report-get-detail":
					top.openBigForm(jspUrl+"&uid=abe36298b963240eb3693fd7a686af29","业务回收明细查询",function(){},"90","90");
					break;
				case "bussFee":
					top.openBigForm(jspUrl+"&uid=6ae8625d982dec652cdf96d6a3836c2e","业务费用情况查询",function(){},"90","90");
					break;
				case "report-bo-enddate":
					top.openBigForm(jspUrl+"&uid=e0c7e339365500361ef98ddacd03aaba","业务到期查询",function(){},"90","90");
					break;
				case "bussBal":
					top.openBigForm(jspUrl+"&uid=0cb800b35cd34676477419a4fe85a233","业务余额情况查询",function(){},"90","90");
					break;
				case "report-overbo-detail":
					top.openBigForm(jspUrl+"&uid=194391b549870fae91035736e1e2aa4c","逾期业务明细统计表",function(){},"90","90");
					break;
				case "report-assue-data-account":
					top.openBigForm(jspUrl+"&uid=d78b57d87e179c1100cc41ca09d2a4ba","业务担保信息统计表",function(){},"90","90");
					break;	
				case "report-expect-return-money":
					top.openBigForm(jspUrl+"&uid=e3a900d46e83201fc8f091929b315d1a&currDate="+currDate,"预期还款查询",function(){},"90","90");
					break;
				case "lendingStatics":
					top.openBigForm(jspUrl+"&reportId="+id+"&type=java","放款统计汇总表",function(){},"90","90");
//					top.openBigForm("MfReportLoanAction_getPutListByCus.action?reportId="+id,"放款统计汇总表",function(){} ,"85","85");
					break;
				case "report-app-detail":
					top.openBigForm(jspUrl+"&uid=5efadb06a6b9be8d05425f9183c58555&flagA=0&flagB=0","项目审批时效明细表",function(){},"90","90");
					break;
				case "report-prtSaleYear":
					top.openBigForm(jspUrl+"&reportId="+id+"&type=java",title,function(){},"90","90");
					//top.openBigForm("MfProSaleYearlyAction_proSaleStatYealy.action?reportId="+id,title,function(){},"90","90");
					break;
				case "report-empOpStat":
					top.openBigForm(jspUrl+"&reportId="+id+"&type=java","员工业务受理质量表",function(){},"90","90");
//					top.openBigForm("MfEmpOpStatAction_empOpStat.action?reportId="+id,'员工业务受理质量表',function(){},"90","90");
					break;
				case "report-appEfficiency":
					top.openBigForm(jspUrl+"&reportId="+id+"&type=java","审批人员时效分析表",function(){},"90","90");
					//top.openBigForm("MfAppEfficiencyAction_appEfficiency.action?reportId="+id,'审批人员时效分析表',function(){},"90","90");
					break;
				case "report-asset-protect":
					top.openBigForm(jspUrl+"&uid=a1bfbab9fdfd84147e784be6a39fdd33","资产保全查询",function(){},"90","90");
					break;
				case "report-project-collection":
					top.openBigForm(jspUrl+"&reportId="+id+"&type=java","项目催收情况",function(){},"90","90");
					//top.openBigForm("MfReportRecallAction_getRecallList.action?reportId="+id,"项目催收情况",function(){} ,"90","90");
					break;
				case "report-cus-black-list":
					top.openBigForm("WangxinRepAction_getBlackList.action?reportId="+id,title,function(){} ,"90","90");
					break;
				case "report-wangxin-fengkong-yhdwd":
					top.openBigForm("WangxinRepAction_yhdwdList.action?reportId="+id,title,function(){} ,"90","90");
					break;
				case "report-wangxin-fengkong-xjdrb":
					top.openBigForm("WangxinRepAction_xjdrbList.action?reportId="+id,title,function(){} ,"90","90");
					break;
				case "report-wangxin-fengkong-xjdrbApp":
					top.openBigForm("WangxinRepAction_xjdrbListApp.action?reportId="+id,title,function(){} ,"90","90");
					break;
				case "report-wangxin-fengkong-cwfl":
					top.openBigForm(jspUrl+"&uid=3786a764dabe8fe56d17f4a56f845a29","网信-现金贷财务分录",function(){},"90","90");
					break;
				case "report-wangxin-fengkong-xjdljsj":
					top.openBigForm("WangxinRepAction_xjdljsjList.action?reportId="+id,title,function(){} ,"90","90");//现金贷累计数据
					break;
				case "report-wangxin-fengkong-xjdljsjApp":
					top.openBigForm("WangxinRepAction_xjdljsjListApp.action?reportId="+id,title,function(){} ,"90","90");//现金贷累计数据(APP导流)
					break;
				case "report-wangxin-fengkong-xjdzb":
					top.openBigForm("WangxinRepAction_xjdzbList.action?reportId="+id,title,function(){} ,"90","90");//现金贷周报
					break;
				case "report-wangxin-fengkong-xjdzbApp":
					top.openBigForm("WangxinRepAction_xjdzbListApp.action?reportId="+id,title,function(){} ,"90","90");//现金贷周报(APP导流)
					break;
				case "report-wangxin-fengkong-azcrwdsj":
					top.openBigForm("WangxinRepAction_azcrwdsjList.action?reportId="+id,title,function(){} ,"90","90");//按照注册日维度数据
					break;
				case "report-wangxin-fengkong-azcrwdsjApp":
					top.openBigForm("WangxinRepAction_azcrwdsjListApp.action?reportId="+id,title,function(){} ,"90","90");//按照注册日维度数据(APP导流)
					break;
				case "report-wangxin-fengkong-xjdrbgl":
					top.openBigForm("WangxinRepAction_xjdrbglList.action?reportId="+id,title,function(){} ,"90","90");//现金贷日报概览
					break;
				case "report-wangxin-fengkong-xjdrbglSummary":
					top.openBigForm("WangxinRepAction_xjdrbglSummaryList.action?reportId="+id,title,function(){} ,"90","90");//现金贷日报概览累计数据
					break;
				case "report-wangxin-fengkong-xjdyhldzh":
					top.openBigForm("WangxinRepAction_xjdyhldzhList.action?reportId="+id,title,function(){} ,"90","90");//用户转化漏斗(图形分析)
					break;
				case "report-wangxin-fengkong-xjdyhldzhsj":
					top.openBigForm("WangxinRepAction_xjdyhldzhsjList.action?reportId="+id,title,function(){} ,"90","90");//用户转化漏斗(数据分析)
					break;
				case "report-wangxin-fengkong-xjdsxtgl":
					top.openBigForm("WangxinRepAction_xjdsxtglList.action?reportId="+id,title,function(){} ,"90","90");//不同注册日用户授信通过率
					break;
				case "report-wangxin-fengkong-xjdtxcgbl":
					top.openBigForm("WangxinRepAction_xjdtxcgblList.action?reportId="+id,title,function(){} ,"90","90");//不同注册日用户提现成功比率
					break;
				case "report-wangxin-fengkong-xjdfkbl":
					top.openBigForm("WangxinRepAction_xjdfkblList.action?reportId="+id,title,function(){} ,"90","90");//不同注册日用户放款比率
					break;
				case "report-wangxin-fengkong-xjdhmdmzl":
					top.openBigForm("WangxinRepAction_xjdhmdmzlList.action?reportId="+id,title,function(){} ,"90","90");//不同注册日用户黑名单命中率
					break;
				case "report-wangxin-daihou-dwdzcqkbg":
					top.openBigForm("WangxinRepAction_dwdzcqkbgList.action?reportId="+id,title,function(){} ,"90","90");//多维度资产情况报告
					break;
				case "report-wangxin-fengkong-khjkxq":
					top.openBigForm("WangxinRepAction_khjkxqList.action?reportId="+id,title,function(){} ,"90","90");//凡易贷客户借款详情
					break;
				case "report-wangxin-fengkong-drfk":
					top.openBigForm("WangxinRepAction_drfkList.action?reportId="+id,title,function(){} ,"90","90");//凡易贷当日放款
					break;
				case "report-wangxin-fengkong-drhk":
					top.openBigForm("WangxinRepAction_drhkList.action?reportId="+id,title,function(){} ,"90","90");//凡易贷当日还款
					break;
				case "report-wangxin-fengkong-sxwtx":
					top.openBigForm("WangxinRepAction_sxwtxList.action?reportId="+id,title,function(){} ,"90","90");//凡易贷授信未提现名单
					break;
				case "report-qingsuan-ptdz":
					top.openBigForm(jspUrl+"&uid=5efadb06a6b9be8d05425f9183c58901a","平台对账表",function(){},"90","90");
					break;
				case "report-qingsuan-hkjh":
					top.openBigForm(jspUrl+"&uid=5efadb06a6b9be8d05425f9183c58902a","还款计划表",function(){},"90","90");
					break;
				case "report-wangxin-daihou-zcztgk":
					top.openBigForm("WangxinRepAction_zcztgkList.action?reportId="+id,title,function(){} ,"90","90");//资产整体概况
					break;
				case "report-loan-yuqi":
					top.openBigForm(jspUrl+"&reportId="+id+"&type=java",title,function(){},"90","90");
					break;
				case "report-risk-daily-report":
					top.openBigForm(jspUrl+"&reportId="+id+"&type=java",title,function(){},"90","90");
					break;
				case "report-hbxd-pact":
					top.openBigForm(jspUrl+"&reportId="+id+"&type=java",title,function(){},"90","90");//华北小贷金融监管合同业务数据查询。
					break;

				case "report-hbxd-finc":
					top.openBigForm(jspUrl+"&reportId="+id+"&type=java",title,function(){},"90","90");//华北小贷金融监管借据业务数据查询。
					break;
				case "report-hbxd-repay":
					top.openBigForm(jspUrl+"&reportId="+id+"&type=java",title,function(){},"90","90");//华北小贷金融监管还款业务数据查询。
					break;

				case "report-credit-query":
					top.openBigForm("MfReportCustomerAction_getCreditQueryList.action?reportId="+id,"征信查询台账",function(){},"90","90");//华北小贷金融监管合同业务数据查询。
					break;
				case "report-loan-overall-find":
					top.openBigForm(jspUrl+"&uid=6bac390c372b1afe451fcf288f020307","贷款综合查询",function(){},"90","90");
					break;
				case "report-jinJian-count":
					top.openBigForm(jspUrl+"&reportId="+id+"&type=java",title,function(){},"90","90");
					break;
				case "report-loan-JSfkmx":
					top.openBigForm(jspUrl+"&reportId="+id+"&type=java",title,function(){},"90","90");//金山放款明细表
					break;
				case "report-loan-JSskmx":
					top.openBigForm(jspUrl+"&reportId="+id+"&type=java",title,function(){},"90","90");//金山收款明细表
					break;
				case "report-loan-JSsryc":
					top.openBigForm(jspUrl+"&reportId="+id+"&type=java",title,function(){},"90","90");//金山收入预测表
					break;
				case "report-loan-JSyqandwy":
					top.openBigForm(jspUrl+"&reportId="+id+"&type=java",title,function(){},"90","90");//逾期&违约汇总表
					break;
				case "report_stock_statistics":
					top.openBigForm(jspUrl+"&reportId="+id+"&type=java",title,function(){},"90","90");
					break;
				case "report-loan-yango":
					top.openBigForm(jspUrl+"&reportId="+id+"&type=java",title,function(){},"90","90");
					break;
				// 铁甲报表-begin
				case "report-tjw-program-approve"://项目审批时效表
					top.openBigForm(jspUrl+"&reportId="+id+"&type=java",title,function(){},"90","90");
					break;
				case "report-tjw-post-time"://岗位时效表
					top.openBigForm(jspUrl+"&reportId="+id+"&type=java",title,function(){},"90","90");
					break;
				case "report-tjw-employee-quality"://员工受理业务质量表
					top.openBigForm(jspUrl+"&reportId="+id+"&type=java",title,function(){},"90","90");
					break;
				case "report-tjw-repay-detail"://贷后还款明细
					top.openBigForm(jspUrl+"&reportId="+id+"&type=java",title,function(){},"90","90");
					break;
				case "report-tjw-over-due"://逾期业务明细
					top.openBigForm(jspUrl+"&reportId="+id+"&type=java",title,function(){},"90","90");
					break;
				case "report-tjw-pledge-device"://抵押设备基本信息查询
					top.openBigForm(jspUrl+"&reportId="+id+"&type=java",title,function(){},"90","90");
					break;
				case "report-tjw-cus-info"://客户基本信息
					top.openBigForm(jspUrl+"&reportId="+id+"&type=java",title,function(){},"90","90");
					break;
				// 铁甲报表-end
				default:
					_showTips(obj);
					break;
				}
	};
	
	var _openThis = function(id){
		$.ajax({
			type:"get",
			url:"MfReportFilterActionAjax_getById.action?id="+id,
			success: function(data){
				var query_content = encodeURIComponent(data.query_content);
				var query_name = data.query_name;
				var query_class = data.query_class;
				if(query_class == "cus"){
					top.openBigForm("MfReportCustomerAction_getCusList.action?ajaxData="+query_content,query_name,function(){},'90','90');	
				}else if(query_class == "bus"){
					top.openBigForm("MfReportCustomerAction_getBusList.action?ajaxData="+query_content,query_name,function(){},"90","90");
				}else if(query_class == "finc"){
					top.openBigForm("MfReportCustomerAction_getFincList.action?ajaxData="+query_content,query_name,function(){},"90","90");
				}else if(query_class == "pledge"){
					top.openBigForm("MfReportCustomerAction_getPledgeList.action?ajaxData="+query_content,query_name,function(){},"90","90");
				}
			}
		});	
	};
	
	var tipsTimeoutId;
	var _showTips = function (obj) {
		top.LoadingAnimate.stop();
		var d = dialog({
			id : "oaInBuilding",
			content : "正在建设中，敬请期待。",
			padding : "3px"
		}).show(obj);
		if (tipsTimeoutId) {
			clearTimeout(tipsTimeoutId);
		}
		tipsTimeoutId = setTimeout(function() {
			d.close().remove();
		}, 1000);
	};
	
	var _openTuDemo = function(parm,title){
		if (parm !="") {
			var url = contextPath + '/component/report/MfReportDemo.jsp?parm='+parm;
			top.openBigForm(url,title,function(){},'80','85');
			return;
		}
		_showTips(this);
		return;
	};
	var _openReport = function(obj,parm,title){
		var jspUrl = "/factor/component/report/MfReportEntranceOpen.jsp?&reportId="+parm+"&reporttype=D";
		var url= '';
		switch(parm){
			case "report-loan-overall-time":
				top.openBigForm(jspUrl+"&uid=6bac390c372b1afe451fcf288f020309","贷款台账",function(){},"90","90");
				break;
			case "report-loan-taizhang":
				top.openBigForm(jspUrl+"&uid=6bac390c372b1afe451fcf288f020308","授信台账",function(){},"90","90");//阳光银行出账台账查询
				break;
			case "report-pact-enddate":
				url= jspUrl+'&uid=3e008f6ddf0419ba884d877ee6bf3e92&flagA=0&flagB=0&flagC=0';
				break;
			case "12":
				url= jspUrl+'&uid=042089842fda9181f89857bcd327374e&flagA=0&flagB=0';
				break;		
			case "18":
				url=jspUrl+'&uid=b9c67ef6dc02cefc68ca2154b887d1ff&flagA=0&flagB=0';
				break;
			case "15":
				url=jspUrl+'&uid=490d89e4c67a38c055e3cc5d019b53a1&flagA=0&flagB=0';
				break;	
			case "report-loan-repayCount":
				url=jspUrl+'&uid=a3cae1b8e70bb14ad39fbfea0ce87c1f';
				break;
			case "report-five-class-account":
				url=jspUrl+'&uid=6c55a87342d3056659d2a5b8ddc610a6';
				break;
			case "report-service-account-month":
				top.openBigForm(jspUrl+"&reportId="+parm+"&type=java",title,function(){},"90","90");
//				top.openBigForm("MfReportRepayCountAction_getBussinessMonth.action?reportId="+parm,title,function(){} ,"90","90");
				break;
			case "report-data-source":
				top.openBigForm(jspUrl+"&reportId="+parm+"&type=java",title,function(){},"90","90");
				//top.openBigForm("MfBalAnalysisAction_getSourceInfo.action?reportId="+parm,title,function(){} ,"85","85");
				break;
			case "report-loan-loanCnt":
				top.openBigForm(jspUrl+"&reportId="+parm+"&type=java&currMonth="+currMonth,title,function(){},"90","90");
//				top.openBigForm("MfReportLoanAccuAction_getLoanAccuReportList.action?reportId="+parm,title,function(){} ,"85","85");
				break;
			case "report-loan-busStatistics":
				top.openBigForm(jspUrl+"&reportId="+parm+"&type=java",title,function(){},"90","90");
//				top.openBigForm("MfReportLoanStatusAction_getMfReportBusStatistics.action?reportId="+parm,title,function(){} ,"85","85");
				break;
			case "report-loan-loanStatus":
				top.openBigForm(jspUrl+"&reportId="+parm+"&type=java&lastMonth="+lastMonth,title,function(){},"90","90");
//				top.openBigForm("MfReportLoanStatusAction_getMfReportStatusList.action?reportId="+parm,title,function(){} ,"85","85");
				break;
			case "report-loan-balAccount":
				top.openBigForm(jspUrl+"&reportId="+parm+"&type=java",title,function(){},"90","90");
//				top.openBigForm("MfBalAnalysisAction_getBalAnalysisList.action?reportId="+parm,title,function(){} ,"85","85");
				break;
			case "report-loan-loanCountVoutype":
				top.openBigForm(jspUrl+"&reportId="+parm+"&type=java",title,function(){},"90","90");
//				top.openBigForm("MfReportLoanAction_getPutListByVouType.action?reportId="+parm,title,function(){} ,"85","85");
				break;
			case "report-loan-loanCountCustype":
				top.openBigForm(jspUrl+"&reportId="+parm+"&type=java",title,function(){},"90","90");
//				top.openBigForm("MfReportLoanAction_getPutListByCus.action?reportId="+parm,title,function(){} ,"85","85");
				break;
			case "report-credit-count":
				top.openBigForm(jspUrl+"&reportId="+parm+"&type=java",title,function(){},"90","90");
//				top.openBigForm("MfReportLoanAction_getPutList.action?reportId="+parm,title,function(){} ,"85","85");
				break;
			case "report-prtSaleYear":
				top.openBigForm(jspUrl+"&reportId="+parm+"&type=java",title,function(){},"90","90");
//				top.openBigForm("MfProSaleYearlyAction_proSaleStatYealy.action?reportId="+parm,title,function(){} ,"85","85");
				break;
			case "report-empOpStat":
				top.openBigForm(jspUrl+"&reportId="+parm+"&type=java","员工业务受理质量表",function(){},"90","90");
//				top.openBigForm("MfEmpOpStatAction_empOpStat.action?reportId="+parm,title,function(){},"85","85");
				break;
			case "report-appEfficiency":
				top.openBigForm("MfAppEfficiencyAction_appEfficiency.action?reportId="+parm,title,function(){},"85","85");
				break;
			case "report-yangguangbank-hkmxb":
				top.openBigForm(jspUrl+"&uid=5efadb06a6b9be8d05425f9183c58904a","还款明细表",function(){},"90","90");
				break;
			//铁甲-begin
			case "report-tjw-count-collect"://统计(汇总)
				top.openBigForm(jspUrl+"&reportId="+parm+"&type=java",title,function(){},"90","90");
				break;
			case "report-tjw-count-detail"://统计(明细)
				top.openBigForm(jspUrl+"&reportId="+parm+"&type=java",title,function(){},"90","90");
				break;
			case "report-tjw-count-over_rate"://全国逾期率
				top.openBigForm(jspUrl+"&reportId="+parm+"&type=java",title,function(){},"90","90");
				break;
			//铁甲-end
			default :
				_showTips(obj);
				return false;
		}
		if(url != ''){
			top.openBigForm(url,title,function(){});
		}
	};
	var _openJIanGuan = function(parm,title){
		var url = contextPath + '/component/report/MfReportDemo2.jsp?parm='+parm;		
		top.openBigForm(url,title,function(){},'90','90');
	};
	var _addItemCallBack = function(funcType){
		$.ajax({
			url : "MfReportFilterActionAjax_getListPageAjax.action",
			data : {
				funcType :funcType,			
			},
			type : 'post',
			dataType : 'json',
			beforeSend:function(){  
			},success:function(data){
				LoadingAnimate.stop();
				if(data.flag == "success"){
					var tatolList = data.unattention;
					$.each(tatolList,function(i,cusTable){
						var itemId = cusTable.itemId;
						var flag = cusTable.attentionFlag;
						if(flag == "1"){//为选中
							$("#"+itemId).css("display","block");
						}else{//已选中		
							$("#"+itemId).css("display","none");
						}
					});
				}else if(data.flag == "error"){
					 alert(data.msg,0);
				}
			},error:function(data){
				LoadingAnimate.stop(); 
				alert("操作失败！",0);
			}
		});
		
	};
	
	var _popQueryDiv = function(obj){
		if(obj.hasClass("ui-btn-dis")) {
			return false;
	    }
		$(obj).parent().toggleClass('ui-btn-menu-cur');
	};
	
	
	//查询条件弹窗选择
	var openConditionListDialog = function(txType, callback){
		var brNoDef = $('#brNo').val();
		var opNoDef = $('#opNo').val();
		var cusTypeDef = $('#cusTypeNo').val();
		var kindTypeDef = $('#kindNo').val();
		var fincIdDef = $('#fincId').val();
		var pledgeClassDef = $('#pledgeClassNo').val();
		var dataSourceDef = $('#dataSourceNo').val();
		var cusNoDef = $('#cusNo').val();
		var channelSourceNoDef = $('#channelSourceNo').val();
		var manageBrNoDef = $('#manageBrNo').val();
		var manageOpNoDef = $('#manageOpNo').val();
		top.createShowDialog(encodeURI(contextPath + '/component/report/sqlConditionDialog.jsp?txType=' + txType+"&brNoDef="+brNoDef+"&opNoDef="+opNoDef+"&pledgeClassDef="+pledgeClassDef+"&cusTypeDef="+cusTypeDef+"&kindTypeDef="+kindTypeDef+"&fincIdDef="+fincIdDef+"&dataSourceDef="+dataSourceDef+"&cusNoDef="+cusNoDef+"&channelSourceNoDef="+channelSourceNoDef+"&manageBrNoDef="+manageBrNoDef+"&manageOpNoDef="+manageOpNoDef), "选择",'450px','600px',function(){
			if(callback&&typeof(callback)=="function"){
				callback.call(this,top.brNoData);
			}
		});
	};
	
	//清空查询条件文本框内容
	var _clearAllInputValue = function(){
		$("input").each(function(index, element){
			$(this).val('');
		});
	};
	
	//隐藏查询条件
	var _hideQueryTr = function(obj){
		$("ul[id=more-conditions]").children().hide();
		$('#cusNoLi').hide();
		$('#numTypeLi').hide();
	};
	
	//保存标志 
	var _dealSaveFlag = function(obj){
		 $('#brNo').attr('saveFlag','0');
		 $('#opNo').attr('saveFlag','0');
		 $('#pledgeClass').attr('saveFlag','0');
		 $('#cusType').attr('saveFlag','0');
		 $('#kindType').attr('saveFlag','0');
		 $('#dataSource').attr('saveFlag','0');
		 $('#beginDate').attr('saveFlag','0');
		 $('#endDate').attr('saveFlag','0');
		 $('#date').attr('saveFlag','0');
		 $('#month').attr('saveFlag','0');
		 $('#year').attr('saveFlag','0');
		 $('#fincId').attr('saveFlag','0');
		 $('#cusNo').attr('saveFlag','0');
	};
	
	//显示查询条件
	var _showQueryMessage = function(reportId){
		wholeReportId = reportId;
		jQuery.ajax({
			url:"MfReportQueryConditionConfigActionAjax_getConditonShowByIdAjax.action",
			data:{reportId:reportId},
			type:"POST",
			dataType:"json",
			async:false,
			beforeSend:function(){  
			},success:function(data){
				if(data.flag == "success"){
					var result = data.result;
					if(result!=''){
						var jsonObj =  JSON.parse(result);//转换为json对象
						for(var i in jsonObj){
							var name = jsonObj[i].name;
							var liId = jsonObj[i].liId;
							var labelId = jsonObj[i].labelId;
							var lableName = jsonObj[i].lableName;
							if(name == 'dateInterval'){//时间间隔处理
								$('#beginDate').attr('saveFlag','1');
								$('#endDate').attr('saveFlag','1');
							}else{
								$('#'+name).attr('saveFlag','1');
							}
							if(name == 'cityNo'){
								$('#cityName').show();
							}
							
							if(lableName.length>4){
								$('#'+labelId).css('line-height','20px');
								lableName = lableName.substring(0,4)+"\n"+lableName.substring(4,lableName.length);
							}
							$('#'+labelId).html(lableName+":");
							$('#'+liId).show();
						}
					}
				}else if(data.flag == "error"){
				}
			},error:function(data){
			}
		});
	};
	//显示查询条件
	var _showOpenQueryMessage = function(reportId){
		wholeReportId = reportId;
		jQuery.ajax({
			url:"MfReportQueryConditionConfigActionAjax_getConditonShowByIdAjax.action",
			data:{reportId:reportId},
			type:"POST",
			dataType:"json",
			async:false,
			beforeSend:function(){  
			},success:function(data){
				if(data.flag == "success"){
					var result = data.result;
					if(result!=''){
						var jsonObj =  JSON.parse(result);//转换为json对象
						//debugger;
						for(var i in jsonObj){
							var name = jsonObj[i].name;
							var liId = jsonObj[i].liId;
							var labelId = jsonObj[i].labelId;
							var lableName = jsonObj[i].lableName;
							if(name == 'dateInterval'){//时间间隔处理
								$('#beginDate').attr('saveFlag','1');
								$('#endDate').attr('saveFlag','1');
							}else{
								$('#'+name).attr('saveFlag','1');
							}
//							$('#'+liId).css({ "cssText": "float:left !important;margin-left:10px" });
							if(i<3){
								$('#'+liId).show();
								$('#'+labelId).html(lableName+":");
							}else{
								$('#'+liId).attr('showFlag','1');
								$('#'+labelId).html(lableName+":");
							}
						}
						
						//设置展开和收起的图片是否展示  如果有隐藏的项，显示展示。
						//debugger;
						var opnCntNow=0;
						$("li").each(function(index, element){
							
							
							if($(this).attr("showflag")== '1'){
								opnCntNow++;
							}
							
							
						});
						if(opnCntNow>0){
							$("#op_more_tr").show();
						}
					}
				}else if(data.flag == "error"){
				}
			},error:function(data){
			}
		});
	};
	//日期验证
	var _validateSave = function(reportId){
		if($('#brNoLi').css("display")!='none'){
			var brNo = $('#brNo').val();
			if(brNo==''){
				alert(top.getMessage("FIRST_SELECT_FIELD", {"field":"部门"}),1); 
				return false;
			}
		}
		if($('#opNoLi').css("display")!='none'){
			var opNo = $('#opNo').val();
			if(opNo==''){
				var opHtml = $('#opNoLable').html().replace(":","");
				alert(top.getMessage("FIRST_SELECT_FIELD", {"field":opHtml}),1); 
				return false;
			}
		}
		if($('#dateIntervalLi').css("display")!='none'){
			var beginDate = $('#beginDate').val();
			var endDate = $('#endDate').val();
			if(beginDate!='' && endDate!=''){
				beginDate = beginDate.replace(new RegExp("-","gm"),"");//替换日期之间的横杠
				endDate = endDate.replace(new RegExp("-","gm"),"");//替换日期之间的横杠
				if(beginDate>endDate){
					alert(top.getMessage("NOT_FORM_TIME", {"timeOne":"开始日期" , "timeTwo": "结束日期"}),1); 
					return false;
				}
			}
		}
		return true;
	};
	
	//保存查询条件
	var _reportSave= function(){
		if(_validateSave(wholeReportId)){
			$('.search_con').hide();
			_saveSqlCondition(wholeReportId);
		}
	};
	
	//重置查询条件
	var _resetQueryInput= function(){
		$("input").each(function(index, element){
			var objId = $(this).attr("id");
			if(objId == 'brNo' || objId == 'brName' || objId == 'opNo' || objId == 'opName'){
				if($(this).attr("readonly") != 'readonly'){
					$(this).val('');
					$("#opNoDiv").html("");
				}
			}else{
				$(this).val('');
			}
		});
	};
	var _resetOpNo = function(){
		$("#opNoDiv").html("");
		$("#opNoDiv").css("height:42px");
	}
	//重置查询条件
	var _showCalendarDlg = function(obj){
		var objId = $(obj).attr('id');
		if(wholeReportId=='report-expect-return-money' && (objId == 'beginDate' || objId == 'endDate' )){
			var minDate = currDate.substring(0, 4)+"-"+currDate.substring(4,6)+"-"+currDate.substring(6,8);
			fPopUpCalendarDlg({
				isclear: false,
				max: '2099-12-31 23:59:59', //最大日期
				choose:function(data){
				}	
			});
		}else if(wholeReportId=='report-prtSaleYear'){
			fPopUpCalendarDlg({
				isclear: false,
				dateFormat:"YYYY",
				choose:function(data){
					$('#year').val(data.substring(0,4));
				}	
			});
		}else{
			fPopUpCalendarDlg(obj);
		}
		var laydate_box_Left = parseFloat($('#laydate_box').css("left").replace("px",""));//日期控件
		var laydate_box_width = parseFloat($('#laydate_box').width());//日期控件宽度
		var row_info_content_width =  parseFloat($('.row.info-content').eq(0).width())+30;//日期控件宽度
		if(laydate_box_Left+laydate_box_width>row_info_content_width){
			laydate_box_Left = parseFloat(laydate_box_Left)-116;
			$('#laydate_box').css("left",laydate_box_Left+"px");
		}
	};
	//保存查询条件
	var _openSaveSqlCondition = function(){
		debugger;
		if(_validateSave(wholeReportId)){
			var url = "MfReportQueryConditionUserActionAjax_reportOpenQuery.action";
			var subArr=[];
			$("input").each(function(index, element){
				if($(this).attr('saveFlag') == '1' && ( $(this).parent().css("display")!='none' || $(this).parent().attr("showflag")=='1') ){
					var subObj = {};
					if($(this).attr('noFlag') == '1'){
						subObj.value= $(this).val();
						subObj.nameValue= $(this).next().val();
					}else{
						subObj.value= $(this).val();
						subObj.nameValue=$(this).val();
					}
					var inputId = $(this).attr('id');
					if(inputId == 'date' || inputId == 'beginDate' || inputId == 'endDate'){
						subObj.value= $(this).val().replace(new RegExp("-","gm"),"");//替换日期之间的横杠
					}
					subArr.push(subObj);
				}
			
			});
		    var condition = JSON.stringify(subArr);
			jQuery.ajax({
				url:url,
				data:{reportId:reportId,sqlCondition:condition,type:type},
				type:"POST",
				dataType:"json",
				 async:false,
				beforeSend:function(){  
				},success:function(data){
					if(data.flag == "success"){
						if(type!='java'){
							$('#sqlCondition').val(data.querySqlCondition);
							$('#btnSearchForm').submit();
						}else{
							$('#sqlMap').val(data.querySqlCondition);
							$('#btnSearchForm').submit();
						}
						
					}else if(data.flag == "error"){
					}
				},error:function(data){
				}
			});
		}
	};
	
	//城市区域选择组件
	var cintyInit = function(){
		var setting = {
			check: {
				enable: true,
				chkStyle: "checkbox",
				chkboxType: { "Y": "ps", "N": "ps" }
			}
		};
		$("input[name=cityName]:visible").popupSelection({
				ajaxUrl : "NmdAreaActionAjax_getAreaListAllAjax.action",
				searchOn : true,//启用搜索
				multiple : true,//单选
				ztree : true,
				ztreeSetting : setting,
				title : "城市",
				changeCallback : function (elem) {
				 	var areaNos=elem.data("values").val();
					var nodes = elem.data("treeNode");
					var address = "";
					for(var i = 0 ; i < nodes.length; i++){
						var addressTmp=nodes[i].name;
						var parNode =  nodes[i].getParentNode();
						while(parNode) {
							addressTmp=parNode.name+addressTmp;
							parNode=parNode.getParentNode();
						}
						address += "," + addressTmp;
					}
					address = address.substring(1);
					BASE.removePlaceholder($("#cityName"));
					$("#cityName").val(address);
					if(areaNos != null){
						var reg = /[\s\|]/g;
						areaNos = areaNos.replace(reg,",");
					}
					$("#cityNo").val(areaNos);
			}, 
		});
	};
	//获取javaBean报表的请求地址
	var _getJavaUrl = function(id){
		switch (id) {
		case "lendingStatics":
			javaUrl="MfReportLoanAction_getPutListByCus.action?reportId="+id;
			break;
		case "report-prtSaleYear":
			javaUrl="MfProSaleYearlyAction_proSaleStatYealy.action?reportId="+id;
//			top.openBigForm("MfProSaleYearlyAction_proSaleStatYealy.action?reportId="+id,title,function(){},"90","90");
			break;
		case "report-empOpStat":
			javaUrl = "MfEmpOpStatAction_empOpStat.action?reportId="+id;
			break;
		case "report-appEfficiency":
			javaUrl = "MfAppEfficiencyAction_appEfficiency.action?reportId="+id;
			break;
		case "report-project-collection":
			javaUrl = "MfReportRecallAction_getRecallList.action?reportId="+id;
			break;
		case "report-credit-count":
			javaUrl = "MfReportLoanAction_getPutList.action?reportId="+id;
			break;
		case "report-service-account-month":
			javaUrl = "MfReportRepayCountAction_getBussinessMonth.action?reportId="+id;
			break;
		case "report-data-source":
			javaUrl = "MfBalAnalysisAction_getSourceInfo.action?reportId="+id;
			//top.openBigForm("MfBalAnalysisAction_getSourceInfo.action?reportId="+parm,title,function(){} ,"85","85");
			break;
		case "report-loan-loanCnt":
			javaUrl = "MfReportLoanAccuAction_getLoanAccuReportList.action?reportId="+id;
//			top.openBigForm("MfReportLoanAccuAction_getLoanAccuReportList.action?reportId="+parm,title,function(){} ,"85","85");
			break;
		case "report-loan-busStatistics":
			javaUrl = "MfReportLoanStatusAction_getMfReportBusStatistics.action?reportId="+id;
//			top.openBigForm("MfReportLoanStatusAction_getMfReportBusStatistics.action?reportId="+parm,title,function(){} ,"85","85");
			break;
		case "report-loan-loanStatus":
			javaUrl = "MfReportLoanStatusAction_getMfReportStatusList.action?reportId="+id;
//			top.openBigForm("MfReportLoanStatusAction_getMfReportStatusList.action?reportId="+parm,title,function(){} ,"85","85");
			break;
		case "report-loan-balAccount":
			javaUrl = "MfBalAnalysisAction_getBalAnalysisList.action?reportId="+id;
//			top.openBigForm("MfBalAnalysisAction_getBalAnalysisList.action?reportId="+parm,title,function(){} ,"85","85");
			break;
		case "report-loan-loanCountVoutype":
			javaUrl = "MfReportLoanAction_getPutListByVouType.action?reportId="+id;
//			top.openBigForm("MfReportLoanAction_getPutListByVouType.action?reportId="+parm,title,function(){} ,"85","85");
			break;
		case "report-loan-loanCountCustype":
			javaUrl = "MfReportLoanAction_getPutListByCus.action?reportId="+id;
//			top.openBigForm("MfReportLoanAction_getPutListByCus.action?reportId="+parm,title,function(){} ,"85","85");
			break;

		case "report-hbxd-pact":
			javaUrl = "HbxdEfcdmPactDataAction_getListPage.action?reportId="+id;//华北小贷金融监管合同业务数据查询
			break;
		case "report-loan-yuqi":
			javaUrl = "YangGuangYQRepAction_yuqiList.action?reportId="+id;
			break;
		case "report-hbxd-finc":
			javaUrl = "HbxdEfcdmFincDataAction_getListPage.action?reportId="+id;//华北小贷金融监管借据业务数据查询
			break;
	    case "report-hbxd-repay":
		    javaUrl = "HbxdEfcdmRepayDataAction_getListPage.action?reportId="+id;//华北小贷金融监管还款业务数据查询
		    break;
	    case "report-loan-JSfkmx":
			javaUrl = "JinSankingSoftAction_JSfkmxList.action?reportId="+id;//金山放款明细表
			break;
	    case "report-loan-JSskmx":
	    	javaUrl = "JinSankingSoftAction_JSskmxList.action?reportId="+id;//金山收款明细表
	    	break;
	    case "report-loan-JSsryc":
	    	javaUrl = "JinSankingSoftAction_JSsrycList.action?reportId="+id;//金山收入预测表
	    	break;
	    case "report-loan-JSyqandwy":
	    	javaUrl = "JinSankingSoftAction_JSyqandwyList.action?reportId="+id;//逾期&违约汇总表
	    	break;
	    case "report-jinJian-count":
		    javaUrl = "YanGoReportAction_getJinJianReportList.action?reportId="+id;//每日进件
			break;
	    case "report-risk-daily-report":
	    	javaUrl = "YanGoReportAction_getRiskDailyReportList.action?reportId="+id;//风险日报
	    	break;
	    case "report_stock_statistics":
	    	javaUrl = "YanGoReportAction_getStockStatisticsReportList.action?reportId="+id;//每日存量统计
	    	break;
	    case "report-loan-yango":
	    	javaUrl = "YanGoReportAction_getLoanInfoList.action?reportId="+id+"&uid=2b97eaa2efa4cf2734601cc6fee578c4";//贷款信息查询
	    	break;
	    //铁甲网-begin
	    case "report-tjw-program-approve"://项目审批时效表
	    	javaUrl = "TjwReportAction_getApproveTimeList.action?reportId="+id;
			break;
		case "report-tjw-post-time"://岗位时效表
			javaUrl = "TjwReportAction_getPostTimeList.action?reportId="+id;
			break;
		case "report-tjw-employee-quality"://员工受理业务质量表
			javaUrl = "TjwReportAction_getEmployeeQualityList.action?reportId="+id;
			break;
		case "report-tjw-repay-detail"://贷后还款明细
			javaUrl = "TjwReportAction_getRepayDetailList.action?reportId="+id;
			break;
		case "report-tjw-over-due"://逾期业务明细
			javaUrl = "TjwReportAction_getOverdueList.action?reportId="+id;
			break;
		case "report-tjw-pledge-device"://抵押设备基本信息查询
			javaUrl = "TjwReportAction_getPledgeDeviceList.action?reportId="+id;
			break;
		case "report-tjw-cus-info"://客户基本信息
			javaUrl = "TjwReportAction_getCusInfoList.action?reportId="+id;
			break;
		case "report-tjw-count-collect"://统计(汇总)
			javaUrl = "TjwReportAction_getCountCollectList.action?reportId="+id;
			break;
		case "report-tjw-count-detail"://统计(明细)
			javaUrl = "TjwReportAction_getCountDetailList.action?reportId="+id;
			break;
		case "report-tjw-count-over_rate"://全国逾期率
			javaUrl = "TjwReportAction_getOverRateList.action?reportId="+id;
			break;
	    //铁甲网-end
	    }
		
		
	};
	//获取javaBean报表的请求地址
	var _getMoreData = function(obj){
		var moreFlag=$(obj).data("flag");
		if(moreFlag==undefined){
			moreFlag=0;
		}
		
		if(moreFlag==0){
			obj.parentElement.parentElement.style="height:auto";
			var height = $(obj).parent().parent().height();
			$(obj).parent().parent().find("label").first().height(height);
			/*$('#brNoLabel').height(126);*/
			$(obj).data("flag", "1");
			$(obj).html("收起");
		}else{
			obj.parentElement.parentElement.style="height:42px";
			$(obj).data("flag", "0");
			$(obj).html("更多");
		}
	};
	var _getMoreLi = function(obj){
		var moreFlag=$(obj).data("flag");
		if(moreFlag==undefined){
			moreFlag=0;
		}
	
		if(moreFlag==1){
			//如果是收起
			var tmpCnt=0;
			$("li").each(function(index, element){
				debugger;
				if($(this).attr('showFlag') == '0'&&!$(this).is(":hidden")){
					if(tmpCnt>0){
						$(this).attr('showFlag',"1");
					
					}
					tmpCnt++;
				}
				
			});
		}
		
		
		$("li").each(function(index, element){
			if($(this).attr('showFlag') == '1'){
				if(moreFlag==0){
					//展开
					//$(obj).data("flag", "1");
					$(this).show();
					$("#shouqi_icon").show();
					$("#zhankai_icon").hide();
					
				}else{//收起
					//$(obj).data("flag", "0");
					$(this).hide();
					//保留一行
					$("#shouqi_icon").hide();
					$("#zhankai_icon").show();
				}
			
			}
			
		});
		
		if(moreFlag==0){
			$("li").each(function(index, element){
				debugger;
				 //设置是否显示更多
				if(!$(this).is(":hidden")){
					
					var tmpWidth=$(this).width();
					tmpWidth=tmpWidth*0.78;
					var tmpLen=0;
					$(this).find("span").each(function(){
						//debugger;
						tmpLen+=$(this).width();
					});
					
					if(tmpWidth>=tmpLen){
						$(this).find("a").css("visibility","hidden");
					}else{
						$(this).find("a").css("visibility","visible");
					}
				}
				
			});
		}
	};
	return {
		init:_init,
		openBase:_openBase,
		openThis:_openThis,
		openTuDemo:_openTuDemo,
		openReport:_openReport,
		openJIanGuan:_openJIanGuan,
		saveSqlCondition:_saveSqlCondition,
		reportQuery:_reportQuery,
		showFormConditionVal:_showFormConditionVal,
		validateSave:_validateSave,
		reportSave:_reportSave,
		resetQueryInput:_resetQueryInput,
		showCalendarDlg:_showCalendarDlg,
		openInit:_openInit,
		openSaveSqlCondition:_openSaveSqlCondition,
		getJavaUrl:_getJavaUrl,
		getMoreData:_getMoreData,
		getMoreLi:_getMoreLi,
	};
}(window,jQuery);