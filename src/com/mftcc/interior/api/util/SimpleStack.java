package com.mftcc.interior.api.util;

import java.util.Map;

import com.alibaba.fastjson.JSON;
 

public class SimpleStack {
	
	 private static String getLevelStr(int level) {
	        StringBuffer levelStr = new StringBuffer();
	        for (int levelI = 0; levelI < level; levelI++) {
	            levelStr.append("\t");
	        }
	        return levelStr.toString();
	 }
	 
	  public Map jsonmap(String str) {
		  
		    Map maps = (Map)JSON.parse(str);  
		    System.out.println("这个是用JSON类来解析JSON字符串!!!");  
		    for (Object map : maps.entrySet()){  
		        System.out.println(((Map.Entry)map).getKey()+"     " + ((Map.Entry)map).getValue());  
		    } 
		    return maps;
		    }
	  public StringBuffer jsonshow(String s) throws Exception {
		        //json 字符串
		  SimpleStack ss = new SimpleStack(5);
		        int level = 0;
		        //存放格式化的json字符串
		        StringBuffer jsonForMatStr = new StringBuffer();
		        for(int index=0;index<s.length();index++)//将字符串中的字符逐个按行输出
		        {
		            //获取s中的每个字符
		            char c = s.charAt(index);
//		          System.out.println(s.charAt(index));
		             
		            //level大于0并且jsonForMatStr中的最后一个字符为\n,jsonForMatStr加入\t
		            if (level > 0 && '\n' == jsonForMatStr.charAt(jsonForMatStr.length() - 1)) {
		                jsonForMatStr.append(getLevelStr(level));
//		                System.out.println("123"+jsonForMatStr);
		            }
		            //遇到"{"和"["要增加空格和换行，遇到"}"和"]"要减少空格，以对应，遇到","要换行
		            switch (c) {
		            case '{':
		            	ss.push(c);
		            	 break;
		            case ':':
		            	 
		            	ss.push(c);
		            	
		            	 break;
		            	 
		            case '[':
		                jsonForMatStr.append(c + "\n");
		                level++;
		                ss.push(c);
		                break;
		            case ',':
		                jsonForMatStr.append(c + "\n");            
		                break;
		            case '}':
		            case ']':
		                jsonForMatStr.append("\n");
		                level--;
		                jsonForMatStr.append(getLevelStr(level));
		                jsonForMatStr.append(c);
		                break;
		            default:
		                jsonForMatStr.append(c);
		                break;
		            }
		        }
		        System.out.println(jsonForMatStr);
		        
		        return jsonForMatStr;
		 
		    }
		     
		    
	private int top = -1;  
    private Object[] objs;  
      
    public SimpleStack(int capacity) throws Exception{  
        if(capacity < 0)  
            throw new Exception("Illegal capacity:"+capacity);  
        objs = new Object[capacity];  
    }  
      
    public void push(Object obj) throws Exception{  
        if(top == objs.length - 1)  
            throw new Exception("Stack is full!");  
        objs[++top] = obj;  
    }  
      
    public Object pop() throws Exception{  
        if(top == -1)  
            throw new Exception("Stack is empty!");  
        return objs[top--];  
    }  
      
    public void dispaly(){  
        System.out.print("bottom -> top: | ");  
        for(int i = 0 ; i <= top ; i++){  
            System.out.print(objs[i]+" | ");  
        }  
        System.out.print("\n");  
    } 
    public String dispalytop(){  
        System.out.print("bottom -> top: | ");  
      /*  for(int i = 0 ; i <= top ; i++){  
            System.out.print(objs[i]+" | ");  
        } */ 
        System.out.print(objs[0]+" | ");   
        return (String) objs[0];
    } 
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		   String str = "{\"code\":10000,\"msg\":null,\"data\":{\"id\":\"7aa0eb56-1026-4497-a42e-4c39f5e3dcf1\",\"topicId\":\"0876ab84-a478-417b-91bc-849843c191a5\",\"title\":null,\"commentId\":null,\"content\":\"" +
	                "开发者平台自动化测试：针对帖子发表评论" +
	                "\",\"images\":\"\",\"time\":\"2017-10-15 18:09:56\",\"userId\":\"61028f94-de92-4c65-aad3-2fc8614e1d34\",\"userName\":\"devautotest\",\"commentNum\":0,\"status\":0}}";
	     
		
		SimpleStack s = new SimpleStack(5);  
		    s.jsonshow(str);

        s.push(1);  
        s.push(2);  
        s.dispaly();  
        System.out.println(" 弹出 "+s.pop());  
        s.dispaly();  
        s.push(99);  
        s.dispaly();  
        s.push(99); 
        s.dispaly(); 
	}

}
