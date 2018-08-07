package com.mftcc.interior.oa.leave;

import java.sql.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;






public class test {

	public static void main(String[] args) {
	
		
		float a=(float) 22.00;
		float b=a/60;
		DecimalFormat  fnum  =   new  DecimalFormat("##0.00");    
		String   dd=fnum.format(b);   
		System.err.println(dd);
	}

}
