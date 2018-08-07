package com.mftcc.interior.report.filter.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;






import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;

/*import app.base.User;
import app.component.nmd.entity.ParmDic;
import app.component.pms.bo.PmsUserFilterBo;
import app.tech.oscache.CodeUtils;
import cn.mftcc.common.MessageEnum;
*/
import com.core.struts.ActionContext;
import com.dhcc.workflow.pvm.internal.util.StringUtil;
import com.mftcc.interior.report.filter.bean.CodeUtils;
import com.mftcc.interior.report.filter.bean.DateUtil;
import com.mftcc.interior.report.filter.bean.MessageEnum;
import com.mftcc.interior.report.filter.bean.ParmDic;
import com.mftcc.interior.report.filter.bean.PmsUserFilter;
import com.mftcc.interior.report.filter.bo.PmsUserFilterBo;
//@Controller
public class PmsUserFilterAction {
	private PmsUserFilter pmsUserFilter;
	private Map<String, Object> dataMap;
	private PmsUserFilterBo pmsUserFilterBo;
	private String filterNo;
	private String filterName;
	private String methodName;
	private String ajaxData;
	private String filterContent;
	private String useFlag;

	/*{
		ActionContext.initialize(ServletActionContext.getRequest(),
				ServletActionContext.getResponse());
	}*/
	@RequestMapping(value="/insert")
	public String insert() throws Exception {
		dataMap = new HashMap<String, Object>();
		String url = (String) ServletActionContext.getRequest().getSession()
				.getAttribute("helpAction");
		String jsp = (String) ServletActionContext.getRequest().getSession()
				.getAttribute("helpJsp");
		pmsUserFilter = new PmsUserFilter();
		pmsUserFilter.setOpNo("111"/*User.getRegNo(ServletActionContext.getRequest())*/);
		pmsUserFilter.setUrl(url);
		pmsUserFilter.setJsp(jsp);
		pmsUserFilter.setFilterNo(filterNo);
		pmsUserFilter.setFilterName(filterName);
		pmsUserFilter.setFilterContent(filterContent);
		pmsUserFilter.setUseFlag(useFlag);
		PmsUserFilter puf = new PmsUserFilter();
		puf = pmsUserFilterBo.getById(pmsUserFilter);
		if (puf == null) {
			pmsUserFilter.setLstModTime(DateUtil.getDateTime());
			pmsUserFilter.setUseFlag("0");
			pmsUserFilter.setOptType("2");
			pmsUserFilterBo.insert(pmsUserFilter);
		} else {
			pmsUserFilterBo.update(pmsUserFilter);
		}
		dataMap.put("msg", MessageEnum.SUCCEED_SAVE.getMessage());
		return "success";
	}
	@RequestMapping(value="/delete")
	public String delete() throws Exception {
		String url = (String) ServletActionContext.getRequest().getSession()
				.getAttribute("helpAction");
		String jsp = (String) ServletActionContext.getRequest().getSession()
				.getAttribute("helpJsp");
		pmsUserFilter = new PmsUserFilter();
		pmsUserFilter.setOpNo("111"/*User.getRegNo(ServletActionContext.getRequest())*/);
		pmsUserFilter.setUrl(url);
		pmsUserFilter.setJsp(jsp);
		pmsUserFilter.setFilterNo(filterNo);
		pmsUserFilterBo.delete(pmsUserFilter);
		return "success";
	}
	public String getById() throws Exception {
		dataMap = new HashMap<String, Object>();
		String url = (String) ServletActionContext.getRequest().getSession()
				.getAttribute("helpAction");
		String jsp = (String) ServletActionContext.getRequest().getSession()
				.getAttribute("helpJsp");
		pmsUserFilter = new PmsUserFilter();
		pmsUserFilter.setOpNo("111"/*User.getRegNo(ServletActionContext.getRequest())*/);
		pmsUserFilter.setUrl(url);
		pmsUserFilter.setJsp(jsp);
		pmsUserFilter.setFilterNo(filterNo);
		pmsUserFilter = pmsUserFilterBo.getById(pmsUserFilter);
		if (pmsUserFilter != null) {
			dataMap.put("json", pmsUserFilter.getFilterContent());
		} else {
			dataMap.put("json", "{}");
		}
		return "success";
	}
	@RequestMapping(value="/findByList")
	public String findByList() throws Exception {
		dataMap = new HashMap<String, Object>();
		String url = (String) ServletActionContext.getRequest().getSession()
				.getAttribute("helpAction");
		String jsp = (String) ServletActionContext.getRequest().getSession()
				.getAttribute("helpJsp");
		pmsUserFilter = new PmsUserFilter();
		pmsUserFilter.setOpNo("111"/*User.getRegNo(ServletActionContext.getRequest())*/);
		pmsUserFilter.setUrl(url);
		pmsUserFilter.setJsp(jsp);
		List<PmsUserFilter> pufList = pmsUserFilterBo.findByList(pmsUserFilter);
		JSONArray dicArray = JSONArray.fromObject(pufList);
		List<PmsUserFilter> topList = new ArrayList<PmsUserFilter>();
		List<PmsUserFilter> moreList = new ArrayList<PmsUserFilter>();
		for (int i = 0; i < pufList.size(); i++) {
			if ("1".equals(pufList.get(i).getUseFlag())) {
				topList.add(pufList.get(i));
			} else {
				moreList.add(pufList.get(i));
			}
		}
		JSONArray topArray = JSONArray.fromObject(topList);
		JSONArray moreArray = JSONArray.fromObject(moreList);
		for (int i = 0; i < dicArray.size(); i++) {
			dicArray.getJSONObject(i).put("opNo",
					dicArray.getJSONObject(i).getString("opNo"));
			dicArray.getJSONObject(i).put("id",
					dicArray.getJSONObject(i).getString("filterNo"));
			dicArray.getJSONObject(i).put("name",
					dicArray.getJSONObject(i).getString("filterName"));
			dicArray.getJSONObject(i).put("pId", "0");
			dicArray.getJSONObject(i).put("open", true);
		}
		for (int i = 0; i < topArray.size(); i++) {
			topArray.getJSONObject(i).put("opNo",
					topArray.getJSONObject(i).getString("opNo"));
			topArray.getJSONObject(i).put("id",
					topArray.getJSONObject(i).getString("filterNo"));
			topArray.getJSONObject(i).put("name",
					topArray.getJSONObject(i).getString("filterName"));
			topArray.getJSONObject(i).put("pId", "0");
			topArray.getJSONObject(i).put("open", true);
		}
		for (int i = 0; i < moreArray.size(); i++) {
			moreArray.getJSONObject(i).put("opNo",
					moreArray.getJSONObject(i).getString("opNo"));
			moreArray.getJSONObject(i).put("id",
					moreArray.getJSONObject(i).getString("filterNo"));
			moreArray.getJSONObject(i).put("name",
					moreArray.getJSONObject(i).getString("filterName"));
			moreArray.getJSONObject(i).put("pId", "0");
			moreArray.getJSONObject(i).put("open", true);
		}

		dataMap.put("json", dicArray);
		dataMap.put("topjson", topArray);
		dataMap.put("morejson", moreArray);
		// dataMap.put("toplistSize", topList.size());
		dataMap.put("morelistSize", moreList.size());
		return "success";
	}

	public String getTableName() throws Exception {
		dataMap = new HashMap<String, Object>();
		List<PmsUserFilter> pufList = pmsUserFilterBo.getTableName();
		JSONArray dicArray = JSONArray.fromObject(pufList);
		for (int i = 0; i < dicArray.size(); i++) {
			dicArray.getJSONObject(i).put("id",
					dicArray.getJSONObject(i).getString("filterNo"));
			dicArray.getJSONObject(i).put("name",
					dicArray.getJSONObject(i).getString("filterName"));
			dicArray.getJSONObject(i).put("pId", "0");
			dicArray.getJSONObject(i).put("open", true);
		}
		dataMap.put("json", dicArray);
		return "success";
	}

	public String getTableColumn() throws Exception {
		dataMap = new HashMap<String, Object>();
		List<PmsUserFilter> pufList = pmsUserFilterBo
				.getTableColumn(filterName);
		JSONArray dicArray = JSONArray.fromObject(pufList);
		for (int i = 0; i < dicArray.size(); i++) {
			dicArray.getJSONObject(i).put(
					"id",
					underlineToCamel2(dicArray.getJSONObject(i).getString(
							"filterNo")));
			dicArray.getJSONObject(i).put("name",
					dicArray.getJSONObject(i).getString("filterName"));
			dicArray.getJSONObject(i).put("pId", "0");
			dicArray.getJSONObject(i).put("open", true);
		}
		dataMap.put("json", dicArray);
		return "success";
	}

	public static String underlineToCamel2(String param) {
		param = param.toLowerCase();
		if (param == null || "".equals(param.trim())) {
			return "";
		}
		StringBuilder sb = new StringBuilder(param);
		Matcher mc = Pattern.compile("_").matcher(param);
		int i = 0;
		while (mc.find()) {
			int position = mc.end() - (i++);
			// String.valueOf(Character.toUpperCase(sb.charAt(position)));
			sb.replace(position - 1, position + 1,
					sb.substring(position, position + 1).toUpperCase());
		}
		return sb.toString();
	}

	public String getParmDic() throws Exception {
		dataMap = new HashMap<String, Object>();
		List<PmsUserFilter> pufList = pmsUserFilterBo.getParmDic(filterName);
		JSONArray dicArray = JSONArray.fromObject(pufList);
		for (int i = 0; i < dicArray.size(); i++) {
			dicArray.getJSONObject(i).put("id",
					dicArray.getJSONObject(i).getString("filterNo"));
			dicArray.getJSONObject(i).put("name",
					dicArray.getJSONObject(i).getString("filterName"));
			dicArray.getJSONObject(i).put("pId", "0");
			dicArray.getJSONObject(i).put("open", true);
		}
		dataMap.put("json", dicArray);
		return "success";
	}

	public String getCacheParmDic() throws Exception {
		dataMap = new HashMap<String, Object>();
		CodeUtils cu = new CodeUtils();
		List<ParmDic> list = (List<ParmDic>) cu.getCacheByKeyName(filterName);
		JSONArray dicArray = JSONArray.fromObject(list);
		dataMap.put("json", dicArray);
		return "success";
	}

	public String getCacheSelectFromDB() throws Exception {
		dataMap = new HashMap<String, Object>();
		List<ParmDic> list = pmsUserFilterBo.getCacheSelectFromDB(filterName,
				methodName);
		JSONArray dicArray = JSONArray.fromObject(list);
		dataMap.put("json", dicArray);
		return "success";
	}

	public String getSomeCacheParmDic() throws Exception {
		dataMap = new HashMap<String, Object>();
		CodeUtils cu = new CodeUtils();
		List<ParmDic> list = new ArrayList();
		if (!StringUtil.isEmpty(filterName)) {
			String[] keyName = filterName.split("@");
			for (int i = 0, j = keyName.length; i < j; i++) {
				list.addAll((List<ParmDic>) cu.getCacheByKeyName(keyName[i]));
			}
		}
		JSONArray dicArray = JSONArray.fromObject(list);
		dataMap.put("json", dicArray);
		return "success";
	}

	public String update() throws Exception {
		return "success";
	}

	public String updateStsAjax() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),
				ServletActionContext.getResponse());
		dataMap = new HashMap<String, Object>();
		try {
			// pmsUserFilter.setLastModTime(DateUtil.getDateTime());
			pmsUserFilterBo.update(pmsUserFilter);
			dataMap.put("flag", "success");
			dataMap.put("msg", MessageEnum.SUCCEED_UPDATE.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			dataMap.put("flag", "error");
			dataMap.put("msg", MessageEnum.FAILED_UPDATE.getMessage());
			throw e;
		}
		return "success";
	}

	public PmsUserFilterBo getPmsUserFilterBo() {
		return pmsUserFilterBo;
	}

	public void setPmsUserFilterBo(PmsUserFilterBo pmsUserFilterBo) {
		this.pmsUserFilterBo = pmsUserFilterBo;
	}

	public PmsUserFilter getPmsUserFilter() {
		return pmsUserFilter;
	}

	public void setPmsUserFilter(PmsUserFilter pmsUserFilter) {
		this.pmsUserFilter = pmsUserFilter;
	}

	public Map<String, Object> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}

	public String getAjaxData() {
		return ajaxData;
	}

	public void setAjaxData(String ajaxData) {
		this.ajaxData = ajaxData;
	}

	public String getFilterNo() {
		return filterNo;
	}

	public void setFilterNo(String filterNo) {
		this.filterNo = filterNo;
	}

	public String getFilterName() {
		return filterName;
	}

	public void setFilterName(String filterName) {
		this.filterName = filterName;
	}

	public String getFilterContent() {
		return filterContent;
	}

	public void setFilterContent(String filterContent) {
		this.filterContent = filterContent;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getUseFlag() {
		return useFlag;
	}

	public void setUseFlag(String useFlag) {
		this.useFlag = useFlag;
	}

}
