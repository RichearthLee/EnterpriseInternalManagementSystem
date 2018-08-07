
package com.mftcc.interior.pact.service;

import com.mftcc.interior.pact.bean.Ipage;




public interface IOrganizationService {
		
	/**
	 * 方法描述： 导出合同信息
	 * @param ipage
	 * @return 导出文件相对tomcat/file_upload的路径
	 * @throws Exception
	 * String
	 * @author Cuizk
	 * @date 2016-1-21 上午10:51:59
	 */
	public String exportExcelPactStatic(Ipage ipage) throws Exception;
	
	/**
	 * 方法描述： 导出合同收款预警信息
	 * @param ipage 
	 * @return 导出文件相对tomcat/file_upload的路径
	 * @throws Exception
	 * String
	 * @author Cuizk
	 * @date 2016-1-21 下午4:18:36
	 */
	public String exportPactWarning(Ipage ipage) throws Exception;
	
	/**
	 * 方法描述： 删除文件，不需要事务
	 * @param destFile
	 * @throws Exception
	 * void
	 * @author Cuizk
	 * @date 2016-1-28 上午9:40:47
	 */
	public void doDeleteFile(String destFile) throws Exception;
	
	public String exportTichengMonthPact(Ipage ipage) throws Exception;
	
	public String exportTichengQuarterPact(Ipage ipage) throws Exception;

	
}
