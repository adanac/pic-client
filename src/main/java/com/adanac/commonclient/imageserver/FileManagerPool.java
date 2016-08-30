package com.adanac.commonclient.imageserver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.adanac.commonclient.fastdfs_client.StorageClient1;
import com.adanac.commonclient.fastdfs_client.StorageServer;
import com.adanac.commonclient.fastdfs_client.TrackerServer;
import com.adanac.commonclient.imageserver.pool.ConnectionPool;

public class FileManagerPool implements FileManagerPoolConfig {

	private static final long serialVersionUID = 1L;

	private ConnectionPool pool = null;

	private static FileManagerPool fileManager = null;

	private FileManagerPool() {
		// 获得连接池
		pool = new ConnectionPool(connnectString, port, size);
	}

	public static FileManagerPool getInterface() {
		if (fileManager == null) {
			fileManager = new FileManagerPool();
		}
		return fileManager;
	}

	public String uploadFile(File file) throws IOException, Exception {
		return uploadFile(file, getFileExtName(file.getName()));
	}

	public String uploadFile(File file, String suffix) throws IOException, Exception {
		byte[] fileBuff = getFileBuffer(file);
		return send(fileBuff, suffix);
	}

	public String uploadFile(byte[] fileBuff, String suffix) throws IOException, Exception {
		return send(fileBuff, suffix);
	}

	private String send(byte[] fileBuff, String fileExtName) throws IOException, Exception {
		String upPath = null;
		TrackerServer trackerServer = null;
		try {
			trackerServer = pool.checkout(waitTimes);

			StorageServer storageServer = null;
			StorageClient1 client1 = new StorageClient1(trackerServer, storageServer);
			upPath = client1.upload_file1(fileBuff, fileExtName, null);
			pool.checkin(trackerServer);
		} catch (InterruptedException e) {
			// 确实没有空闲连接,并不需要删除与fastdfs连接
			throw e;
		} catch (NullPointerException e) {
			throw e;
		} catch (Exception e) {
			// 发生io异常等其它异常，默认删除这次连接重新申请
			e.printStackTrace();
			pool.drop(trackerServer);
			throw e;
		}
		return upPath;
	}

	private String getFileExtName(String name) {
		String extName = null;
		if (name != null && name.contains(".")) {
			extName = name.substring(name.lastIndexOf(".") + 1);
		}
		return extName;
	}

	private byte[] getFileBuffer(File file) {
		byte[] fileByte = null;
		try {
			FileInputStream fis = new FileInputStream(file);
			fileByte = new byte[fis.available()];
			fis.read(fileByte);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileByte;
	}

	public boolean deleteFile(String fileId) throws IOException, Exception {
		boolean result = false;
		TrackerServer trackerServer = null;
		try {
			trackerServer = pool.checkout(waitTimes);
			StorageServer storageServer = null;
			StorageClient1 client1 = new StorageClient1(trackerServer, storageServer);
			result = client1.delete_file1(fileId) == 0 ? true : false;
			pool.checkin(trackerServer);
		} catch (Exception e) {
			pool.drop(trackerServer);
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	public byte[] getFileByID(String fileId) throws IOException, Exception {
		byte[] result = null;
		TrackerServer trackerServer = null;
		try {
			trackerServer = pool.checkout(waitTimes);
			StorageServer storageServer = null;
			StorageClient1 client1 = new StorageClient1(trackerServer, storageServer);
			result = client1.download_file1(fileId);
			pool.checkin(trackerServer);
		} catch (Exception e) {
			pool.drop(trackerServer);
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	public String getIp() {
		return downLoadString;
	}

	public int getPort() {
		return port;
	}

	public int getDownLoadPort() {
		return downLoadPort;
	}
}
