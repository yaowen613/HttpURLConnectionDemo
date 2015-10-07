package com.jay.example.httpurlconnectiondemo.util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class PostUtils {
	
	public static String LOGIN_URL = "http://172.16.2.54:8080/HttpTest/ServletForPost";
	
	public static String LoginByPost(String number,String passwd)
	{
		String msg = "";
		try{
			HttpURLConnection conn = (HttpURLConnection) new URL(LOGIN_URL).openConnection();
			//设置请求方式,请求超时信息
			conn.setRequestMethod("POST");
			conn.setReadTimeout(5000);
			conn.setConnectTimeout(5000);
			//设置运行输入,输出:
			conn.setDoOutput(true);
			conn.setDoInput(true);
			//Post方式不能缓存,需手动设置为false
			conn.setUseCaches(false);
			//我们请求的数据:
			String data = "passwd="+ URLEncoder.encode(passwd, "UTF-8")+
					"&number="+ URLEncoder.encode(number, "UTF-8");
			//这里可以写一些请求头的东东...
			//获取输出流
			OutputStream out = conn.getOutputStream();
			out.write(data.getBytes());
			out.flush();
			 if (conn.getResponseCode() == 200) {  
	                // 获取响应的输入流对象  
	                InputStream is = conn.getInputStream();  
	                // 创建字节输出流对象  
	                ByteArrayOutputStream message = new ByteArrayOutputStream();  
	                // 定义读取的长度  
	                int len = 0;  
	                // 定义缓冲区  
	                byte buffer[] = new byte[1024];  
	                // 按照缓冲区的大小，循环读取  
	                while ((len = is.read(buffer)) != -1) {  
	                    // 根据读取的长度写入到os对象中  
	                	message.write(buffer, 0, len);  
	                }  
	                // 释放资源  
	                is.close();  
	                message.close();  
	                // 返回字符串  
	                msg = new String(message.toByteArray());  
	                return msg;
			 }
		}catch(Exception e){e.printStackTrace();}
		return msg;
	}
}
