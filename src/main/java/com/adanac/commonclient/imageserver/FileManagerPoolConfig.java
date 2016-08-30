package com.adanac.commonclient.imageserver;

import java.io.Serializable;
import java.util.ResourceBundle;

public interface FileManagerPoolConfig extends Serializable  {
	ResourceBundle bundle = ResourceBundle.getBundle("conf/fastdfs");
		
	public static final  String connnectString = bundle.getString("connnectString").trim();
	public static final  String downLoadString = bundle.getString("downLoadString").trim();

	public static final  int port = Integer.parseInt(bundle.getString("port").trim());
	public static final  int size = Integer.parseInt(bundle.getString("size").trim());
	public static final  int downLoadPort = Integer.parseInt(bundle.getString("downLoadPort").trim());
	public static final  int waitTimes = Integer.parseInt(bundle.getString("waitTimes").trim());
}
