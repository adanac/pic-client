package com.adanac.commonclient.imageserver;

import java.io.File;
import java.io.IOException;

public class FileManagerPoolTest extends Thread  {
	
	FileManagerPool s =FileManagerPool.getInterface();
    public void run() {
        printMsg();// 从多线程调用，因此会打印该线程名称
    }
 
    public void printMsg() {
        // 获得运行此代码的线程的引用
        Thread t = Thread.currentThread();
        String name = t.getName();
        String p;
		try {
			p = s.uploadFile(new File("D:\\2.gif"));
			System.out.println("name : " + name+"id : "+p);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    }
 
    public static void main(String[] args) {
    	FileManagerPoolTest t1 = new FileManagerPoolTest();
    	FileManagerPoolTest t2 = new FileManagerPoolTest();
    	FileManagerPoolTest t3 = new FileManagerPoolTest();
        t1.start();
        t2.start();
        t3.start();
 
        t1.printMsg(); // 从主线程调用，因此会打印main
 
    }

	
//	
////	AtomicInteger count = new AtomicInteger();
//
//	static long stime = 0;
//	//launch thread count
//	private int thread=10;
//	//per thread hold count
//	private int perCount=3;
//	
//	//per thread hold count
//	private int i=0;
//	
//	public static void main(String[] args) {	
//
//	FileManagerPoolTest t=new FileManagerPoolTest();
//	while(true){
//		try {
//			t.mutiUP();
//			Thread.sleep(1000*60*30);//三十分钟
////			Thread.sleep(1000*60);//一分钟
//			t.thread=Math.round(5);
//			System.out.println();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//	}
//}
//	
//	public void mutiUP() {
//		for (i = 0; i < thread; i++) {
//			new Thread() {
//				@Override
//				public void run() {
//					try {
//						System.runFinalization();
//						for (int j = 0; j < perCount; j++) {
//							
//							String p = s.uploadFile(new File("D:\\2.gif"));
//							System.out.println("name:"+i+","+j+"id:"+p);
//						}
//				//		statTime();
//					} catch (IOException e) {
//						e.printStackTrace();
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//				}
//			}.start();
//		}
//	}
}
