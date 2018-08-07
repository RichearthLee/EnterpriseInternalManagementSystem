package com.mftcc.method.bean;

import java.util.List;

import com.mftcc.common.util.StringUtil;

public class Ipage {
	public  int pageNumber;
	// 当前的排序字段
	private  String softFiled;
	//数据库中从此行开始查询
	private int searchStart;
	// 当前的查询字段
	private  String searchFiled;
	// 当前的显示的页数
	private int currPageNo;
	// 总页数
	private int totalPage;
	//总记录数
	private int allRecord;
	// 是否有下一页
	private  String  hasNext;
	 // 是否有上一页
	private  String hasPrevious;
	// 分页显示的数据
	
	private String id;		//当前id
	private List<?> dataList;
	private String softType;
	private String parm0 = ""; 	 //备用参数，也可以做查询条件的参数使用，直接传入到 ibatis的配置文件中使用
	private String parm1 = "";   // ""  避免空指针异常
	private String parm2 = "";
	private String parm3 = "";
	private String parm4 = "";
	private String parm5 = "";
	//test
	private int startRow;
	private int endNum;
	
	
	
	@Override
	public String toString() {
		return "Ipage [pageNumber=" + pageNumber + ", softFiled=" + softFiled
				+ ", searchStart=" + searchStart + ", searchFiled="
				+ searchFiled + ", currPageNo=" + currPageNo + ", totalPage="
				+ totalPage + ", allRecord=" + allRecord + ", hasNext="
				+ hasNext + ", hasPrevious=" + hasPrevious + ", id=" + id
				+ ", dataList=" + dataList + ", softType=" + softType
				+ ", parm0=" + parm0 + ", parm1=" + parm1 + ", parm2=" + parm2
				+ ", parm3=" + parm3 + ", parm4=" + parm4 + ", parm5=" + parm5
				+ ", startRow=" + startRow + ", endNum=" + endNum + "]";
	}
	
	public int getOldEndNum() {
		return getStartRow() + this.dataList.size();
	}
	public int getStartRow() {
		if(this.getCurrPageNo() == 0){
			return 1;
		}
		if(currPageNo<0){
			currPageNo = 0;
		}
		if(currPageNo>totalPage){
			currPageNo = totalPage;
		}
		return (this.getCurrPageNo()-1) * this.getDataList().size() + 1;
	}
	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}
	public int getEndNum() {
		return endNum;
	}
	public void setEndNum(int endNum) {
		this.endNum = endNum;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Ipage(){};
	public List<?> getDataList() {
		return dataList;
	}


	public void setDataList(List<?> dataList) {
		this.dataList = dataList;
	}


/**
 * 功能:初始化总页数


 * 参数:totalRecord 总记录数 
 * 日期:2012-11-08
 * 作者:乾之轩


 */
	public  void InitTotalPage(String totalRecord)   {
		
		
		if(pageNumber==0 || StringUtil.isEmpty(totalRecord)){
			hasNext = "0";
			hasPrevious ="0";
			throw new NullPointerException("分页参数错误,总记录数或每页显示的条数为空!");
		}
		
		// 总记录数
		int _totalRecord = Integer.parseInt(totalRecord);
		this.allRecord = _totalRecord;
		// 每页显示的条数


		//int _pageNumber = pageNumber;
		if(pageNumber==0){
			hasNext = "0";
			hasPrevious ="0";
			throw new RuntimeException("每页显示的条数为空");
		}
		if(_totalRecord==0){
			hasNext = "0";
			hasPrevious ="0";
			totalPage = 0;
		}else{
			int tempTotalRecord =  _totalRecord/pageNumber;
			if(_totalRecord%pageNumber>0){
				tempTotalRecord = tempTotalRecord+1;
			}
			if(tempTotalRecord==1){
				hasNext = "0";
				hasPrevious ="0";
			}else{
				hasNext = "1";
				hasPrevious ="0";
			}
			totalPage = tempTotalRecord;
		}
	}
	
	

	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public String getSoftFiled() {
		return softFiled;
	}
	public void setSoftFiled(String softFiled) {
		this.softFiled = softFiled;
	}
	public String getSearchFiled() {
		return searchFiled;
	}
	public void setSearchFiled(String searchFiled) {
		this.searchFiled = searchFiled;
	}

	public int getCurrPageNo() {
		return currPageNo;
	}
	public void setCurrPageNo(int currPageNo) {
		this.currPageNo = currPageNo;
	}
	
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public String getHasNext() {
		return hasNext;
	}
	public void setHasNext(String hasNext) {
		this.hasNext = hasNext;
	}
	public String getHasPrevious() {
		return hasPrevious;
	}
	public void setHasPrevious(String hasPrevious) {
		this.hasPrevious = hasPrevious;
	}
	public int getAllRecord() {
		return allRecord;
	}
	public void setAllRecord(int allRecord) {
		this.allRecord = allRecord;
	}
	public String getSoftType() {
		return softType;
	}
	public void setSoftType(String softType) {
		this.softType = softType;
	}
	public String getParm0() {
		return parm0;
	}
	public void setParm0(String parm0) {
		this.parm0 = parm0;
	}
	public String getParm1() {
		return parm1;
	}
	public void setParm1(String parm1) {
		this.parm1 = parm1;
	}
	public String getParm2() {
		return parm2;
	}
	public void setParm2(String parm2) {
		this.parm2 = parm2;
	}
	public String getParm3() {
		return parm3;
	}
	public void setParm3(String parm3) {
		this.parm3 = parm3;
	}
	public String getParm4() {
		return parm4;
	}
	public void setParm4(String parm4) {
		this.parm4 = parm4;
	}
	public String getParm5() {
		return parm5;
	}
	public void setParm5(String parm5) {
		this.parm5 = parm5;
	}
	
	public int getSearchStart() {
		return searchStart;
	}
	public void setSearchStart(int searchStart) {
		this.searchStart = searchStart;
	}
}
