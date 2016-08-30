package com.adanac.commonclient.imageserver;

import java.io.File;
import java.io.IOException;

public class PicupTest {
	static private FileManagerPool s = FileManagerPool.getInterface();

	/**
	 * 提供图片url
	 * @param key
	 * @return
	 */

	public static void main(String[] args) {
		File file = new File("d:/pwd.png");
		try {
			System.out.print("http://" + getPicIp() + ":" + getPicPort() + "/" + s.uploadFile(file, "png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 提供图片ip
	 * @param key
	 * @return
	 */
	public static String getPicIp() {
		String ip = "";
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
	 * @param key
	 * @return
	 */
	public static String getPicPort() {
		int tempport = 0;
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
