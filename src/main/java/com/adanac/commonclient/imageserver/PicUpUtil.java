package com.adanac.commonclient.imageserver;

import java.io.File;

public class PicUpUtil {
	static private FileManagerPool s =FileManagerPool.getInterface();
	
	/**
	 * 获取图片url
	 * @param file
	 * @param fixName
	 * @return
	 */
	public static String getPicUrl(byte[] fileBuff,String fixName)
	{	
		if(fileBuff.length==0){
            return "ERROR"; 
        }
		String id="";
		try {
			id = s.uploadFile(fileBuff,fixName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return "ERROR"; 
		}
		return id;
	}
	
	public static String getPicUrl(File file,String fixName)
	{	
		if(!file.exists()){
            return "ERROR"; 
        }
		String id="";
		try {
			id = s.uploadFile(file,fixName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return "ERROR"; 
		}
		return id;
	}
	
	/**
	 * 提供图片ip
	 * @return
	 */
	public static String getPicIp()
	{	
		String ip="";
		try {
			ip = s.getIp();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return "ERROR"; 
		}
		return ip;
	}
	
	/**
	 * 提供图片port
	 * @return
	 */
	public static String getPicPort()
	{	
		int tempport=0;
		String port = ""; 
		try {
			tempport = s.getDownLoadPort();
			port = String.valueOf(tempport);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return "ERROR"; 
		}
		return port;
	}
}
