package com.chinaunicom.myiot.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpUtilsOld {
	public static final Logger logger = LoggerFactory.getLogger(HttpUtilsOld.class);

	public static String HttpPost(String requestURL, String requestParam) throws Exception {
		String result = "";
		StringBuffer resultBuffer = new StringBuffer();
		logger.debug("调用接口Url:" + requestURL);
		
		try {
			URL url = new URL(requestURL);

			HttpURLConnection hc = (HttpURLConnection) url.openConnection();

			hc.setRequestProperty("Charset", "utf-8");

			hc.setDoOutput(true);

			hc.setDoInput(true);

			hc.setRequestMethod("POST");

			OutputStreamWriter outputStream = new OutputStreamWriter(hc.getOutputStream(), "UTF-8");

			outputStream.write(requestParam.toString());

			outputStream.flush();

			outputStream.close();

			// 获取返回的数据
			BufferedReader in = new BufferedReader(new InputStreamReader(hc.getInputStream(), "UTF-8"));
			String line;
			while ((line = in.readLine()) != null) {
				// result += line;
				resultBuffer.append(line);
			}
			result = resultBuffer.toString();
			in.close();

			hc.disconnect();
		} catch (Exception e) {
			logger.error("调用API异常：", e);
			throw new Exception("调用API异常： " + e.getMessage());
		}
		return result;
	}

	public static void HttpGet(String requestURL) throws Exception {
		String result = "";
		StringBuffer resultBuffer = new StringBuffer();
		logger.debug("调用接口Url:" + requestURL);
		
		try {
			URL url = new URL(requestURL);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.connect();
			// 获取返回的数据
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
			String line;
			while ((line = in.readLine()) != null) {
				resultBuffer.append(line);
			}
			result = resultBuffer.toString();
			in.close();
			connection.disconnect();
		} catch (Exception e) {
			logger.error("调用接口异常：", e);
			throw new Exception("调用接口异常： " + e.getMessage());
		}
		//return result;
	}
	
}
