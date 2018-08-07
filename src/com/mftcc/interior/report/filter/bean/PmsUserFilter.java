package com.mftcc.interior.report.filter.bean;

import com.mftcc.interior.report.common.BaseDomain;



public class PmsUserFilter extends BaseDomain{
		private String opNo;//用户号
		private String url;//请求URL
		private String jsp;//请求返回JSP
		private String filterNo;//筛选编号
		private String filterName;//筛选名称
		private String filterContent;//筛选数据结构
		private String useFlag;//启用禁用标志0-禁用 1-启用
		private String lstModTime;//最后修改时间
		private String optType;//筛选项的操作类型0-默认项不允许隐藏不允许编辑1-默认项允许隐藏不允许编辑2-自定义项可隐藏可编辑
		
		
		public String getOpNo() {
			return opNo;
		}
		public void setOpNo(String opNo) {
			this.opNo = opNo;
		}
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		public String getJsp() {
			return jsp;
		}
		public void setJsp(String jsp) {
			this.jsp = jsp;
		}
		public String getFilterNo() {
			return filterNo;
		}
		public void setFilterNo(String filterNo) {
			this.filterNo = filterNo;
		}
		public String getFilterContent() {
			return filterContent;
		}
		public void setFilterContent(String filterContent) {
			this.filterContent = filterContent;
		}
		public String getFilterName() {
			return filterName;
		}
		public void setFilterName(String filterName) {
			this.filterName = filterName;
		}
		public String getUseFlag() {
			return useFlag;
		}
		public void setUseFlag(String useFlag) {
			this.useFlag = useFlag;
		}
		public String getLstModTime() {
			return lstModTime;
		}
		public void setLstModTime(String lstModTime) {
			this.lstModTime = lstModTime;
		}
		public String getOptType() {
			return optType;
		}
		public void setOptType(String optType) {
			this.optType = optType;
		}
	
}
