package com.common;

import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.Properties;
public class ComDefine {
	static private Properties prop;
	static{
		loads();
	}
	synchronized static public void loads(){
  if(prop == null)
  {
		InputStream is = ComDefine.class.getResourceAsStream("/ComDefine.properties");
		prop= new Properties();
		try {
			prop.load(is);
		}
		catch (Exception e) {
			System.err.println("���ܶ�ȡ�����ļ�.��ȷ��ComDefine.properties��srcĿ¼��.");
		}
  }
	}
	public static String getDefine(String strType){
		if(prop==null)loads();
		return prop.getProperty(strType);
	}
	
	public static String getMsg(String strType,Object arr[]){
		String pattern = getDefine(strType);
		MessageFormat format = new MessageFormat(pattern, Locale.CHINA);
		String result = format.format(arr);
		return result;
	}
	
	public static void main(String[] args) {
		Object arr[] ={"用户"};
		System.out.print(getMsg("100001",arr));
	}
}