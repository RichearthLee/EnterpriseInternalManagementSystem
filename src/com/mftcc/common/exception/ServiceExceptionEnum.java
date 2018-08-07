package com.mftcc.common.exception;

public enum ServiceExceptionEnum implements IExceptionEnum {
		 NULLPOINT("000001","系统资源未初始化"),
	     UNIQUE_KEY("10001", "主键约束错误"),
	     PHONENO_EXIST("00101", "该手机号码已经存在"),
	     CUSPROD_BASEINF("10002","客户产品为空"),
	     PROD_BASEINF("10003","产品信息不崔在")  ,
	     PROD_BASEINF_VERSION("10004","产品已是最新版本"),
	     CUS_BASE_CID("10005","客户id为空"),
	     CUS_BASE_ISNO("10006","客户不存在"),
	     PROD_BASEINF_ISNO("10007","更新包表中不催在该产品更新包"),
	     PROD_BASEINF_NEW("10008","更新包表中没有可更新版本"),
	     SEND_EAIL("10009","邮件发送失败"),
	     CUSPRED_BASEING_EXIST("10010","客户产品信息已经存在"),
	     ADD_CUS_MAIL("10011","注册客户和发送邮件必填字段为空"),
	     CUS_UPLOAD_RECORD("10012","插入更新记录参数出错"),
	     UPDATE_CONFIG_FILE_NOT_EXIST("10013","更新包的配置文件不存在"),
	     UPDATE_VERDESC_FILE_NOT_EXIST("10014","更新包的版本描述文件不存在");
	     
	     public String code;  
	     public String message;  
	       
	     private ServiceExceptionEnum(String code,String message){
	    	this.code = code;
	    	this.message = message;
	     }
	   
	     @Override  
	     public String getCode() {  
	         return code;  
	     }  
	   
	     @Override  
	     public String getMessage() {  
	         return message;  
	     }  

	     public ServiceExceptionEnum setMessage(String message) {
				this.message = message;
				return this;
		}
}
