<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/report1/UIplug/rcswitcher-master/js/rcswitcher.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/report1/UIplug/rcswitcher-master/css/rcswitcher.min.css" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/report1/UIplug/select3/js/select3-full.js"></script>
	<style type="text/css">
.ztree li span.button.switch.level0 {visibility:hidden; width:1px;}
.ztree li ul.level0 {padding:0; background:none;}
	</style>
<script src="${pageContext.request.contextPath}/report1/component/sys/js/moment.min.js"></script>
<%--bootstrapjs--%>
		<!-- <div id="ckl">
				<div id="filter_list">
					<div class="my_filter_title">
						<h4>我的筛选</h4>
					</div>
					<ul class="ztree" id="my_filter">
	
					</ul>
					<div class="my_filter_add" onclick="showFilterBox();">
						<i class="i i-jia1" ></i>
						<h4>添加筛选项</h4>
					</div>
				</div>
		</div> -->
		<div class="modal fade bs-example-modal-lg my-filter-box" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header" style="margin-bottom:0px;">
						<button type="button" id="close" class="close"><i class="i i-x5"></i></button>
						<h4 class="modal-title text-center" id="myModalLabel3">筛选项管理</h4>
					</div>
					<div class="modal-footer padding_bottom_60" id="my_filter_tree">
						<div class="row clearfix row-div">
							<div class="myselect-div">
								<ul class="ztree" id="filter_item_set">
								</ul>
							</div>
						</div>
						<div class="row clearfix padding_top_20">
							<div class="padding_left_40">
								<div class="filterName-div form-group col-sm-12">
									<label for="filterName" class="col-sm-1 control-label">筛选名称 <font color="red">*</font></label>
									<div class="col-sm-11">
										<input type="text" class="form-control" maxlength="50" id="filterName" placeholder="50字以内">
										<input type="hidden" id="filterNo"/>
									</div>
								</div>
								<div class="clearfix"></div>
								<ul class="ztree" id="my_filter_group">
								
								</ul>
								<p class="default-tip">系统默认项，不允许编辑</p>
							</div>
						</div>
					</div>
					<div class="formRowCenter" >
						<input type="button" class="save" id="save_filter_db" value="保存" >
						<input type="button" class="cancel" id="del_filter_item" value="删除" >
					</div>
			</div>
		</div>
		<script type="text/javascript">
			$("#my_filter_tree").mCustomScrollbar();
		</script>
