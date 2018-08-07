package com.mftcc.interior.report.filter.bean;



/**
 * @对象说明 常量定义和缓存获取方法
 * @author wangshanfang
 * @date Sep 19, 2009
 * @修改说明
 */
public class CachecodeUtil {
	//缓存常量
	public final static int REFRESHPERIOD = -1; // 缓存过期时间
	public final static String KEYPREFIX = "CMSII"; //缓存在内存中命名前缀
	
	//数据常量
	public final static int CACHE_DATADICT=1;	//数据字典
	public final static int CACHE_MENU=2;		//菜单
	public final static int CACHE_CMSORG=3;		//CMS机构
	public final static int CACHE_DHCORG=4;		//核算机构
	public final static int CACHE_PMSBIZ=5;		//功能权限管理
	public final static int FUN_DATA_RANG=6;	//数据权限配置
	public final static int DESCTEMP=8;		//业务描述模板
	public final static int MSGCONFIG=9;		//消息配置描述
	public final static int APPROVER_PAS_FST_CHECK = 10;	//乘用车-分配审批人(初审员)
	public final static int APPROVER_PAS_DUE_CHECK = 11;	//乘用车-分配审批人(放款初审)
	public final static int APPROVER_COM_FST_CHECK = 12;	//商用车-分配审批人(初审员)
	public final static int APPROVER_COM_DUE_CHECK = 13;	//商用车-分配审批人(放款初审)
	public final static int APPROVER_COM_SUP_FST_CHECK = 14;	//商用车(供货商模式)-分配审批人(初审员)
	public final static int APPROVER_COM_SUP_DUE_CHECK = 15;	//商用车(供货商模式)-分配审批人(放款初审)
	public final static int APPROVER_EXT_FST_CHECK = 16;	//展期申请-分配审批人(初审员)
	public final static int MENUS = 208;
	public final static int SECURITY = 9;		//安全审计
	public final static int SYS_GLOBAL_STATUS = 999;	//系统状态
	public final static int SYS_GLOBAL_DOC_SIZE = 998;	//文件大小限制
	public final static String FAST_MENU_STR = "FASTMENU";
	public final static String SYS_GLOBAL_DOC_SIZE_STR = "SYS_GLOBAL_DOC_SIZE_STR";
	public final static String SYS_GLOBAL_STATUS_STR = "SYS_GLOBAL_STATUS_STR";
	public final static String APPROVER_PAS_FST_CHECK_STR = "APPROVER_PAS_FST_CHECK";
	public final static String APPROVER_PAS_DUE_CHECK_STR = "APPROVER_PAS_DUE_CHECK";
	public final static String APPROVER_COM_FST_CHECK_STR = "APPROVER_COM_FST_CHECK";
	public final static String APPROVER_COM_DUE_CHECK_STR = "APPROVER_COM_DUE_CHECK";
	public final static String APPROVER_COM_SUP_FST_CHECK_STR = "APPROVER_COM_SUP_FST_CHECK";
	public final static String APPROVER_COM_SUP_DUE_CHECK_STR = "APPROVER_COM_SUP_DUE_CHECK";
	public final static String APPROVER_EXT_FST_CHECK_STR = "APPROVER_EXT_FST_CHECK";
	
}
