package com.framework.core.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class FileUtils {
	
	public static String getFileExt(String fileName) {
		if (fileName != null && fileName.contains(".")) {
			return fileName.substring(fileName.lastIndexOf(".") + 1);
		}
		return "";
	}
	
	/**
	 * 文件下载
	 * 
	 * @param filePath
	 * @param fileName
	 * @param request
	 * @param response
	 */
	public static void downLoadFile(String filePath, String fileName, HttpServletRequest request, HttpServletResponse response) {
		FileInputStream fileInputStream = null;
		try {
			File file = new File(filePath);
			long fileLength = file.length();
			// 设置下载参数
			fileName = encodeFilename(request, fileName);
			response.reset();
			response.setHeader("Content-Disposition", "attachment; " + fileName);
			response.addHeader("Content-Length", "" + fileLength);
			response.setContentType("application/octet-stream;charset=UTF-8");
			// response.setContentType("application/x-msdownload");

			fileInputStream = new FileInputStream(file);
			OutputStream out = response.getOutputStream();

			// 读文件
			int iBufferSize = 65000;
			byte[] buffer = new byte[65000];
			long lReadLen = 0;
			while (lReadLen < fileLength) {
				int lRead = fileInputStream.read(buffer, 0, iBufferSize);
				lReadLen += lRead;
				out.write(buffer, 0, lRead);
			}
			out.flush();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
				}
			}
		}
	}

	/**
	 * 文件下载解决文件名乱码问题，对文件名进行字符转换
	 * 
	 * @param request
	 * @param fileName
	 * @return
	 */
	public static String encodeFilename(HttpServletRequest request, String fileName) {
		String rtn = "";
		try {
			String userAgent = request.getHeader("USER-AGENT");
			String newFileName = URLEncoder.encode(fileName, "UTF8");
			// 如果没有UA，则默认使用IE的方式进行编码，因为毕竟IE还是占多数的
			rtn = "filename=\"" + newFileName + "\"";

			if (userAgent != null) {
				userAgent = userAgent.toLowerCase();
				// IE浏览器，只能采用URLEncoder编码
				if (userAgent.indexOf("msie") != -1) {
					rtn = "filename=\"" + newFileName + "\"";
				}
				// Opera浏览器只能采用filename*
				else if (userAgent.indexOf("opera") != -1) {
					rtn = "filename*=UTF-8''" + newFileName;
				}
				// Safari浏览器，只能采用ISO编码的中文输出
				else if (userAgent.indexOf("safari") != -1) {
					rtn = "filename=\"" + new String(fileName.getBytes("UTF-8"), "ISO8859-1") + "\"";
				}
				// Chrome浏览器，只能采用MimeUtility编码或ISO编码的中文输出
				else if (userAgent.indexOf("applewebkit") != -1) {
					// new_filename = MimeUtility.encodeText(fileName, "UTF8", "B"); 备注：应用服务器可能采用自带jdk
					newFileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
					rtn = "filename=\"" + newFileName + "\"";
				}
				// FireFox浏览器，可以使用MimeUtility或filename*或ISO编码的中文输出
				else if (userAgent.indexOf("mozilla") != -1) {
					rtn = "filename*=UTF-8''" + newFileName;
				}
			}

		} catch (UnsupportedEncodingException e) {
			try {
				rtn = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
			} catch (UnsupportedEncodingException e1) {
				// e1.printStackTrace();
			}
			// e.printStackTrace();
		}
		return rtn;
	}
    
	  
}
