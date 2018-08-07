package com.mftcc.interior.sys.bean;
import java.io.Serializable;
import java.util.ArrayList;

/**
* Title: SysMenu.java
* Description:
* @author：@dhcc.com.cn
* @Wed Jan 23 11:31:27 CST 2013
* @version：1.0
**/

public class SysMenu implements Serializable {
	
	
	private String  menuNo;//菜单编号
	private String  menuName;//菜单名称
	private String  menuUrl;//菜单url
	private String  menuParent;//父菜单编号
	private String  menuLev;//菜单级别
	private String  menuSts;//菜单状态 启用1，停用0
	private Integer  groupSeq;//组内序号

	private String  menuTain;//预留字段

	private ArrayList<SysMenu> children;//子菜单
	
	
	public SysMenu() {
		this.menuName = "";
		this.menuNo = "";
		this.menuUrl = "";
		this.menuParent = "";
		this.menuLev = "0";
		this.menuSts = "0";
		this.children = new ArrayList<SysMenu>();
	}
	
	public SysMenu(String menuName, String menuNo, String menuUrl, String menuParent, String menuLev,String menuSts, ArrayList<SysMenu> children) {
		this.menuName = menuName;
		this.menuNo = menuNo;
		this.menuUrl = menuUrl;
		this.menuParent = menuParent;
		if(children!=null){
			this.children = children;
		}else{
			this.children = new ArrayList<SysMenu>();
		}
		this.menuLev=menuLev;
		this.menuSts = menuSts;
	}
	
	
	public void reInit(SysMenu node){
		this.menuName = node.getMenuName();
		this.menuNo=node.getMenuNo();
		this.menuParent=node.getMenuParent();
		this.menuUrl = node.getMenuUrl();
		this.children = node.getChildren();
		this.menuSts = node.getMenuSts();
		this.menuLev = node.getMenuLev();
	}
	
	public void addChildren(SysMenu child){
		this.children.add(child);
	}
	
	public String toJson(){
		StringBuffer str = new StringBuffer();
		if(this.children!=null && this.children.size()>=1){
			for(int i=0;i<this.children.size();i++){
				str.append(this.children.get(i).toJson());
				if(i+1<this.children.size()){
					str.append(",");
				}
			}
		}
		String result = "";
		if("0".equals(menuLev)){
			return "{'checked':"+true+",'text':'"+menuName+"','id':'"+menuNo+"','url':'"+menuUrl+"','Sts':'"+menuSts+"','lvl':'"+menuLev+"','parent':'"+menuParent+"','open':'true','children':["+str.toString()+"]}";
		}
		return "{'checked':"+true+",'text':'"+menuName+"','id':'"+menuNo+"','url':'"+menuUrl+"','Sts':'"+menuSts+"','lvl':'"+menuLev+"','parent':'"+menuParent+"','children':["+str.toString()+"]}";
	}
	/**
	 * @return 组内序号
	 */
	 public Integer getGroupSeq() {
	 	return groupSeq;
	 }
	 /**
	 * @设置 组内序号
	 * @param groupSeq
	 */
	 public void setGroupSeq(Integer groupSeq) {
	 	this.groupSeq = groupSeq;
	 }
 
	 /**
		 * @return 菜单编号
		 */
	public String getMenuNo() {
		return menuNo;
	}

	public void setMenuNo(String menuNo) {
		this.menuNo = menuNo;
	}

	/**
	 * @return 菜单名称
	 */
	 public String getMenuName() {
	 	return menuName;
	 }
	 /**
	 * @设置 菜单名称
	 * @param menuName
	 */
	 public void setMenuName(String menuName) {
	 	this.menuName = menuName;
	 }
	/**
	 * @return 菜单url
	 */
	 public String getMenuUrl() {
	 	return menuUrl;
	 }
	 /**
	 * @设置 菜单url
	 * @param menuUrl
	 */
	 public void setMenuUrl(String menuUrl) {
	 	this.menuUrl = menuUrl;
	 }
	/**
	 * @return 父菜单编号
	 */
	 public String getMenuParent() {
	 	return menuParent;
	 }
	 /**
	 * @设置 父菜单编号
	 * @param menuParent
	 */
	 public void setMenuParent(String menuParent) {
	 	this.menuParent = menuParent;
	 }
	/**
	 * @return 菜单级别
	 */
	 public String getMenuLev() {
	 	return menuLev;
	 }
	 /**
	 * @设置 菜单级别
	 * @param menuLev
	 */
	 public void setMenuLev(String menuLev) {
	 	this.menuLev = menuLev;
	 }
	/**
	 * @return 菜单状态 启用1，停用0
	 */
	 public String getMenuSts() {
	 	return menuSts;
	 }
	 /**
	 * @设置 菜单状态 启用1，停用0
	 * @param menuSts
	 */
	 public void setMenuSts(String menuSts) {
	 	this.menuSts = menuSts;
	 }

	public ArrayList<SysMenu> getChildren() {
		return children;
	}

	public void setChildren(ArrayList<SysMenu> children) {
		this.children = children;
	}

	public String getMenuTain() {
		return menuTain;
	}

	public void setMenuTain(String menuTain) {
		this.menuTain = menuTain;
	}
	 
}
