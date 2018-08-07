package  com.mftcc.interior.report.filter.control;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/*import app.component.common.EntityUtil;
import app.component.sys.bo.SysMsgConfigBo;
import app.component.sys.entity.SysMsgConfig;
import app.util.toolkit.Ipage;*/
import com.mftcc.interior.report.filter.bean.MessageEnum;
import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;
import com.core.struts.taglib.JsonFormUtil;
import com.core.struts.taglib.JsonTableUtil;

/**
 * Title: SysMsgConfigAction.java
 * Description:
 * @author:@dhcc.com.cn
 * @Sat May 07 02:29:40 GMT 2016
 **/
//@Controller
public class SysMsgConfigAction /*extends BaseFormBean */{
//	private static final long serialVersionUID = 9196454891709523438L;
//	//注入SysMsgConfigBo
//	private SysMsgConfigBo sysMsgConfigBo;
//	//全局变量
//	private SysMsgConfig sysMsgConfig;
//	private List<SysMsgConfig> sysMsgConfigList;
//	private String msgNo;		
//	private String tableType;
//	private String tableId;
//	private int pageNo;
//	private String query;
//	//异步参数
//	private String ajaxData;
	private Map<String,Object> dataMap;
//	//表单变量
//	private FormData formsys0001;
//	private FormData formsys0002;
//	private FormData formsys0003;
//	private FormService formService = new FormService();
//	
//	public SysMsgConfigAction(){
//		query = "";
//	}

	/**
	 * 获取提示消息模板的json字符串。
	 * @author LiuYF
	 * @return
	 */
	@RequestMapping(value="/getMessageEnumJSONAjax")
	public String getMessageEnumJSONAjax() {
		dataMap = new HashMap<String, Object>(); 
		dataMap.put("messageObj", MessageEnum.getObjStrForJS());
		dataMap.put("flag", "success");
		//return SUCCESS;
		return "success";
	}
	/**
	 * 列表打开页面请求
	 * @return
	 * @throws Exception
	 */
//	public String getListPage() throws Exception {
//		ActionContext.initialize(ServletActionContext.getRequest(),
//				ServletActionContext.getResponse());
//		return "getListPage";
//	}
//	/***
//	 * 列表数据查询
//	 * @return
//	 * @throws Exception
//	 */
//	public String findByPageAjax() throws Exception {
//		ActionContext.initialize(ServletActionContext.getRequest(),
//				ServletActionContext.getResponse());
//		dataMap = new HashMap<String, Object>(); 
//		sysMsgConfig = new SysMsgConfig();
//		try {
//			sysMsgConfig.setCustomQuery(ajaxData);//自定义查询参数赋值
//			sysMsgConfig.setCriteriaList(sysMsgConfig, ajaxData);//我的筛选
//			//this.getRoleConditions(sysMsgConfig,"1000000001");//记录级权限控制方法
//			Ipage ipage = this.getIpage();
//			ipage.setPageNo(pageNo);//异步传页面翻页参数
//			//自定义查询Bo方法
//			ipage = sysMsgConfigBo.findByPage(ipage, sysMsgConfig);
//			JsonTableUtil jtu = new JsonTableUtil();
//			String  tableHtml = jtu.getJsonStr(tableId,tableType,(List)ipage.getResult(), ipage,true);
//			ipage.setResult(tableHtml);
//			dataMap.put("ipage",ipage);
//			/**
//			 	ipage.setResult(tableHtml);
//				dataMap.put("ipage",ipage);
//				需要改进的方法
//				dataMap.put("tableData",tableHtml);
//			 */
//		} catch (Exception e) {
//			e.printStackTrace();
//			dataMap.put("flag", "error");
//			dataMap.put("msg", e.getMessage());
//			throw e;
//		}
//		return SUCCESS;
//	}
//	
//	
//	public String inputAjax() throws Exception {
//		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
//		dataMap = new HashMap<String, Object>(); 
//		sysMsgConfig = new SysMsgConfig();	
//		formsys0002 = formService.getFormData("sys0002");
//		getObjValue(formsys0002,sysMsgConfig);
//		JsonFormUtil jfu = new JsonFormUtil();
//		String  formHtml = jfu.getJsonStr(formsys0002,"bigFormTag",query);
//		dataMap.put("formHtml",formHtml);
//		return SUCCESS;
//	}
//	/**
//	 * AJAX新增
//	 * @return
//	 * @throws Exception
//	 */
//	public String insertAjax() throws Exception {
//		ActionContext.initialize(ServletActionContext.getRequest(),
//				ServletActionContext.getResponse());
//		dataMap = new HashMap<String, Object>(); 
//		try{
//			formsys0002 = formService.getFormData("sys0002");
//			getFormValue(formsys0002, getMapByJson(ajaxData));
//			if(this.validateFormData(formsys0002)){
//				sysMsgConfig = new SysMsgConfig();
//				setObjValue(formsys0002, sysMsgConfig);
//				SysMsgConfig smc = null;
//				smc = sysMsgConfigBo.getById(sysMsgConfig);
//				if(smc==null){
//					sysMsgConfigBo.insert(sysMsgConfig);
//					dataMap.put("flag", "success");
//					dataMap.put("msg", MessageEnum.SUCCEED_INSERT.getMessage());
//				}else{
//					dataMap.put("flag", "error");
//					dataMap.put("msg", "消息编号已存在！");
//				}
//			}else{
//				dataMap.put("flag", "error");
//				dataMap.put("msg",this.getFormulavaliErrorMsg());
//			}
//		}catch(Exception e){
//			e.printStackTrace();
//			dataMap.put("flag", "error");
//			dataMap.put("msg",MessageEnum.ERROR_INSERT.getMessage());
//			throw e;
//		}
//		return SUCCESS;
//	}
//	/**
//	 * ajax 异步 单个字段或多个字段更新
//	 * @return
//	 * @throws Exception
//	 */
//	public String updateAjaxByOne() throws Exception{
//		ActionContext.initialize(ServletActionContext.getRequest(),
//				ServletActionContext.getResponse());
//		dataMap = new HashMap<String, Object>();
//		formsys0002 = formService.getFormData("sys0002");
//		getFormValue(formsys0002, getMapByJson(ajaxData));
//		SysMsgConfig sysMsgConfigJsp = new SysMsgConfig();
//		setObjValue(formsys0002, sysMsgConfigJsp);
//		sysMsgConfig = sysMsgConfigBo.getById(sysMsgConfigJsp);
//		if(sysMsgConfig!=null){
//			try{
//				sysMsgConfig = (SysMsgConfig)EntityUtil.reflectionSetVal(sysMsgConfig, sysMsgConfigJsp, getMapByJson(ajaxData));
//				sysMsgConfigBo.update(sysMsgConfig);
//				dataMap.put("flag", "success");
//				dataMap.put("msg", MessageEnum.SUCCEED_SAVE.getMessage());
//			}catch(Exception e){
//				e.printStackTrace();
//				dataMap.put("flag", "error");
//				dataMap.put("msg",MessageEnum.ERROR_INSERT.getMessage());
//				throw e;
//			}
//		}else{
//			dataMap.put("flag", "error");
//			dataMap.put("msg",MessageEnum.FAILED_SAVE_CONTENT.getMessage("编号不存在"));
//		}
//		return SUCCESS;
//	}
//	
//	public String updateInputAjax() throws Exception {
//		ActionContext.initialize(ServletActionContext.getRequest(),
//				ServletActionContext.getResponse());
//		dataMap = new HashMap<String, Object>(); 
//		formsys0003 = formService.getFormData("sys0003");
//		sysMsgConfig = new SysMsgConfig();
//		sysMsgConfig.setMsgNo(msgNo);
//		sysMsgConfig = sysMsgConfigBo.getById(sysMsgConfig);
//		getObjValue(formsys0003,sysMsgConfig);
//		JsonFormUtil jfu = new JsonFormUtil();
//		String  formHtml = jfu.getJsonStr(formsys0003,"bigFormTag",query);
//		dataMap.put("formHtml",formHtml);
//		return SUCCESS;
//	}
//	/**
//	 * AJAX更新保存
//	 * @return
//	 * @throws Exception
//	 */
//	public String updateAjax() throws Exception {
//		ActionContext.initialize(ServletActionContext.getRequest(),
//				ServletActionContext.getResponse());
//		dataMap = new HashMap<String, Object>(); 
//		sysMsgConfig = new SysMsgConfig();
//		try{
//			formsys0003 = formService.getFormData("sys0003");
//			getFormValue(formsys0003, getMapByJson(ajaxData));
//			if(this.validateFormData(formsys0003)){
//				sysMsgConfig = new SysMsgConfig();
//				setObjValue(formsys0003, sysMsgConfig);
//				sysMsgConfigBo.update(sysMsgConfig);
//				dataMap.put("flag", "success");
//				dataMap.put("msg",MessageEnum.SUCCEED_UPDATE.getMessage());
//			}else{
//				dataMap.put("flag", "error");
//				dataMap.put("msg",this.getFormulavaliErrorMsg());
//			}
//		}catch(Exception e){
//			e.printStackTrace();
//			dataMap.put("flag", "error");
//			dataMap.put("msg",MessageEnum.FAILED_UPDATE.getMessage());
//			throw e;
//		}
//		return SUCCESS;
//	}
//	
//	/**
//	 * AJAX获取查看
//	 * @return
//	 * @throws Exception
//	 */
//	public String getByIdAjax() throws Exception {
//		ActionContext.initialize(ServletActionContext.getRequest(),
//				ServletActionContext.getResponse());
//		dataMap = new HashMap<String, Object>(); 
//		formsys0002 = formService.getFormData("sys0002");
//		sysMsgConfig = new SysMsgConfig();
//		sysMsgConfig.setMsgNo(msgNo);
//		sysMsgConfig = sysMsgConfigBo.getById(sysMsgConfig);
//		getObjValue(formsys0002,sysMsgConfig);
//		JsonFormUtil jfu = new JsonFormUtil();
//		String  formHtml = jfu.getJsonStr(formsys0002,"bigFormTag",query);
//		dataMap.put("formHtml",formHtml);
//		return SUCCESS;
//	}
//	/**
//	 * Ajax异步删除
//	 * @return
//	 * @throws Exception
//	 */
//	public String deleteAjax() throws Exception{
//		ActionContext.initialize(ServletActionContext.getRequest(),
//				ServletActionContext.getResponse());
//		dataMap = new HashMap<String, Object>(); 
//		sysMsgConfig = new SysMsgConfig();
//		sysMsgConfig.setMsgNo(msgNo);
//		try {
//			sysMsgConfigBo.delete(sysMsgConfig);
//			dataMap.put("flag", "success");
//			dataMap.put("msg",MessageEnum.SUCCEED.getMessage());
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//			dataMap.put("flag", "error");
//			dataMap.put("msg",MessageEnum.ERROR.getMessage());
//			throw e;
//		}
//		return SUCCESS;
//	}
//	/**
//	 * 新增页面
//	 * @return
//	 * @throws Exception
//	 */
//	public String input() throws Exception{
//		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
//		formsys0002 = formService.getFormData("sys0002");
//		return "input";
//	}
//	/***
//	 * 新增
//	 * @return
//	 * @throws Exception
//	 */
//	public String insert() throws Exception{
//		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
//		 formsys0002 = formService.getFormData("sys0002");
//		 getFormValue(formsys0002);
//		 sysMsgConfig = new SysMsgConfig();
//		 setObjValue(formsys0002, sysMsgConfig);
//		 sysMsgConfigBo.insert(sysMsgConfig);
//		 getObjValue(formsys0002, sysMsgConfig);
//		 this.addActionMessage("保存成功");
//		 sysMsgConfigList = (List<SysMsgConfig>)sysMsgConfigBo.findByPage(this.getIpage(), sysMsgConfig).getResult();
//		return "insert";
//	}
//	/**
//	 * 查询
//	 * @return
//	 * @throws Exception
//	 */
//	public String getById() throws Exception{
//		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
//		 formsys0001 = formService.getFormData("sys0001");
//		 getFormValue(formsys0001);
//		 sysMsgConfig = new SysMsgConfig();
//		sysMsgConfig.setMsgNo(msgNo);
//		 sysMsgConfig = sysMsgConfigBo.getById(sysMsgConfig);
//		 getObjValue(formsys0001, sysMsgConfig);
//		return "query";
//	}
//	/**
//	 * 删除
//	 * @return
//	 * @throws Exception
//	 */
//	public String delete() throws Exception {
//		ActionContext.initialize(ServletActionContext.getRequest(),
//				ServletActionContext.getResponse());
//		sysMsgConfig = new SysMsgConfig();
//		sysMsgConfig.setMsgNo(msgNo);
//		sysMsgConfigBo.delete(sysMsgConfig);
//		return getListPage();
//	}
//	/**
//	 * 新增校验
//	 * @return
//	 * @throws Exception
//	 */
//	public void validateInsert() throws Exception{
//		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
//		 formsys0002 = formService.getFormData("sys0002");
//		 getFormValue(formsys0002);
//		 boolean validateFlag = this.validateFormData(formsys0002);
//	}
//	/**
//	 * 修改校验
//	 * @return
//	 * @throws Exception
//	 */
//	public void validateUpdate() throws Exception{
//		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
//		 formsys0002 = formService.getFormData("sys0002");
//		 getFormValue(formsys0002);
//		 boolean validateFlag = this.validateFormData(formsys0002);
//	}
//	
//	public SysMsgConfigBo getSysMsgConfigBo() {
//		return sysMsgConfigBo;
//	}
//	public void setSysMsgConfigBo(SysMsgConfigBo sysMsgConfigBo) {
//		this.sysMsgConfigBo = sysMsgConfigBo;
//	}
//	public SysMsgConfig getSysMsgConfig() {
//		return sysMsgConfig;
//	}
//	public void setSysMsgConfig(SysMsgConfig sysMsgConfig) {
//		this.sysMsgConfig = sysMsgConfig;
//	}
//	public Map<String, Object> getDataMap() {
//		return dataMap;
//	}
//	public void setDataMap(Map<String, Object> dataMap) {
//		this.dataMap = dataMap;
//	}
//	public List<SysMsgConfig> getSysMsgConfigList() {
//		return sysMsgConfigList;
//	}
//	public void setSysMsgConfigList(List<SysMsgConfig> sysMsgConfigList) {
//		this.sysMsgConfigList = sysMsgConfigList;
//	}
//	public String getQuery() {
//		return query;
//	}
//	public void setQuery(String query) {
//		this.query = query;
//	}
//	public int getPageNo() {
//		return pageNo;
//	}
//	public void setPageNo(int pageNo) {
//		this.pageNo = pageNo;
//	}
//	public String getAjaxData() {
//		return ajaxData;
//	}
//	public void setAjaxData(String ajaxData) {
//		this.ajaxData = ajaxData;
//	}
//	public String getTableType() {
//		return tableType;
//	}
//	public void setTableType(String tableType) {
//		this.tableType = tableType;
//	}
//	public String getTableId() {
//		return tableId;
//	}
//	public void setTableId(String tableId) {
//		this.tableId = tableId;
//	}
//	public FormData getFormsys0002() {
//		return formsys0002;
//	}
//	public void setFormsys0002(FormData formsys0002) {
//		this.formsys0002 = formsys0002;
//	}
//	public FormData getFormsys0001() {
//		return formsys0001;
//	}
//	public void setFormsys0001(FormData formsys0001) {
//		this.formsys0001 = formsys0001;
//	}
//	public void setMsgNo(String msgNo){
//		this.msgNo = msgNo;
//	}		
//	public String getMsgNo(){
//		return msgNo;
//	}
//
//	public FormData getFormsys0003() {
//		return formsys0003;
//	}
//
//	public void setFormsys0003(FormData formsys0003) {
//		this.formsys0003 = formsys0003;
//	}
	
}
