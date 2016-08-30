package com.adanac.commonclient.imageserver.pool;

import java.util.logging.Logger;

public class ImageServerPoolSysout {
	
	private static Logger logger=Logger.getLogger("ImageServerPoolSysout");
	
	public static void info(Object o){
		logger.info(o.toString());
	}
	public static void warn(Object o) {
		logger.warning(o.toString());
	}

}
