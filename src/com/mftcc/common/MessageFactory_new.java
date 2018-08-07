package com.mftcc.common;
/**
 * 
 */
import java.io.File;

import com.dingxinsoft.phoneMessage.IPhoneMessage;
import com.dingxinsoft.phoneMessage.impl.EmayPhoneMessageImpl;
import com.dingxinsoft.phoneMessage.impl.MandaoPhoneMessageImpl;
import com.dingxinsoft.phoneMessage.impl.MeifubaoMessageImpl;
import com.dingxinsoft.phoneMessage.impl.XuanWuKeJiPhoneMessageImpl;
import com.mftcc.common.util.PropertiesUtil;


/**
 * 
 * 类名： MessageFactory
 * 描述：
 * @author WuLiGang
 * @date 2016-2-25 下午5:24:53
 *
 */
public class MessageFactory_new {
	
	private static IPhoneMessage phoneMessage;
	private static File file = null;

	/**
	 * 
	 * 方法描述： 
	 * @return
	 * IPhoneMessage
	 * @author WuLiGang
	 * @date 2016-2-25 下午5:24:57
	 */
	public synchronized static IPhoneMessage getPhoneMessageImpl(){
		if(phoneMessage==null){
				String type = PropertiesUtil.getValue("impl");
				System.out.println("----type="+type);
				if("emay".equals(type)){
					phoneMessage = new EmayPhoneMessageImpl(file);
				}else if("mandao".equals(type)){ //漫道
					phoneMessage = new MandaoPhoneMessageImpl(file);
				}else if("dingxin".equals(type)){ //鼎信
					System.out.println("######使用鼎信华铭短信接口#####");
					phoneMessage = new DingxinPhoneMessage_newImpl(file);
				}else if("meifubao".equals(type)){ //美富宝
					phoneMessage = new MeifubaoMessageImpl(file);
				}else if("xuanwukeji".equals(type)){ //玄武科技
					phoneMessage = new XuanWuKeJiPhoneMessageImpl(file);
				}	
		}
		return phoneMessage;
	}
	
}
