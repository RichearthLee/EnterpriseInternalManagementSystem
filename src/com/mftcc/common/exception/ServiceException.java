package com.mftcc.common.exception;

public class ServiceException extends RuntimeException{  
	private static final long serialVersionUID = 1L;
	private ServiceExceptionEnum serviceExceptionEnum;  
	   public ServiceException(ServiceExceptionEnum serviceExceptionEnum){  
	           this.serviceExceptionEnum = serviceExceptionEnum;  
	   }  
	   public ServiceExceptionEnum getServiceExceptionEnum(){  
	       return serviceExceptionEnum;  
	   }  
}
