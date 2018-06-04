package com.elasticemail.app;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import javax.net.ssl.HttpsURLConnection;

// API version 2.42.0
public class API {
	
	public static String API_KEY = "";
	protected static String API_URI = "https://api.elasticemail.com/v2";

	protected <T> T httpPostFile(String targetURL, Iterable<FileData> fileData, Map<String, String> values, Class<T> returnType) throws Exception {
		if (targetURL == null) throw new IllegalArgumentException("targetURL");
		if (values == null) throw new IllegalArgumentException("values");
		if (returnType == null) throw new IllegalArgumentException("returnType");
		
	    HttpURLConnection connection = null;
	    URL url = null;
	    String urlParameters = null;
	    String urlParametersLength = null;
	    
	    try {
			url = new URL(targetURL);
			urlParameters = loadUrlParameters(values);
			urlParametersLength = Integer.toString(urlParameters.getBytes().length);
			String boundary = String.valueOf(System.currentTimeMillis());
			byte[] boundarybytes = ("\r\n--" + boundary + "\r\n").getBytes(Charset.forName("ASCII"));
			
			connection = (HttpURLConnection)url.openConnection();
		    connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
		    connection.setRequestMethod("POST");
		    connection.setRequestProperty("Connection", "Keep-Alive");
		    connection.setRequestProperty("Content-Length", "" + urlParametersLength);
		    connection.setUseCaches(false);
		    connection.setDoInput(true);
		    connection.setDoOutput(true);
			
			//Send request
			DataOutputStream wr = new DataOutputStream(connection.getOutputStream ());
			
            String formdataTemplate = "Content-Disposition: form-data; name=\"%s\"\r\n\r\n%s";
            for (String key : values.keySet())
            {
            	if (values.get(key) == null) continue;
                wr.write(boundarybytes, 0, boundarybytes.length);
                String formitem = String.format(formdataTemplate, key, values.get(key));
                byte[] formitembytes = formitem.getBytes(Charset.forName("UTF8"));
                wr.write(formitembytes, 0, formitembytes.length);
            }

            if(fileData != null){
                for(FileData file : fileData){
                    wr.write(boundarybytes, 0, boundarybytes.length);
                    String headerTemplate = "Content-Disposition: form-data; name=\"filefoobarname\"; filename=\"%s\"\r\nContent-Type: %s\r\n\r\n";
                    String header = String.format(headerTemplate, file.fileName, file.contentType);
                    byte[] headerbytes = header.getBytes(Charset.forName("UTF8"));
                    wr.write(headerbytes, 0, headerbytes.length);
                    wr.write(file.content, 0, file.content.length);
                }
            }

            byte[] trailer = ("\r\n--" + boundary + "--\r\n").getBytes(Charset.forName("ASCII"));
            wr.write(trailer, 0, trailer.length);
            wr.flush ();
			wr.close ();

			//Get Response	
			InputStream is = connection.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			String line;
			StringBuffer response = new StringBuffer(); 
			while((line = rd.readLine()) != null) {
			  response.append(line);
			  response.append('\r');
			}
			rd.close();
			APIResponse<T> apiResponse = new APIResponse<T>(response.toString(), returnType);
			if (!apiResponse.success) throw new RuntimeException(apiResponse.error);
			return apiResponse.data;
	  	      
        } catch (Exception e) { 
        	e.printStackTrace();
        	return null;
        	
        } finally {
    		if(connection != null) {
				connection.disconnect(); 
			}
        }
	}
	
	protected <T> T uploadValues(String targetURL, Map<String, String> values, Class<T> returnType) throws Exception {
		if (targetURL == null) throw new IllegalArgumentException("targetURL");
		if (values == null) throw new IllegalArgumentException("values");
		if (returnType == null) throw new IllegalArgumentException("returnType");
		
	    HttpsURLConnection connection = null;
	    URL url = null;
	    String urlParameters = null;
	    String urlParametersLength = null;
	    
	    try {
	      url = new URL(targetURL);
	      urlParameters = loadUrlParameters(values);
	      urlParametersLength = Integer.toString(urlParameters.getBytes().length);
	      
	      connection = (HttpsURLConnection)url.openConnection();
	      connection.setRequestMethod("POST");
	      connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	      connection.setRequestProperty("accept-encoding", "gzip, deflate"); 
	      connection.setRequestProperty("Content-Length", "" + urlParametersLength);
	      connection.setUseCaches (false);
	      connection.setDoInput(true);
	      connection.setDoOutput(true);

	      //Send request
	      DataOutputStream wr = new DataOutputStream(connection.getOutputStream ());
	      wr.writeBytes (urlParameters);
	      wr.flush ();
	      wr.close ();

	      //Get Response
	      InputStream is = connection.getInputStream();
	      Reader reader = null;
	      if ("gzip".equals(connection.getContentEncoding())) {
	         reader = new InputStreamReader(new GZIPInputStream(is));
	      } else {
	         reader = new InputStreamReader(is);
	      }
	      
	      BufferedReader rd = new BufferedReader(reader);
	      String line;
	      StringBuffer response = new StringBuffer(); 
	      while((line = rd.readLine()) != null) {
	        response.append(line);
	        response.append('\r');
	      }
	      rd.close();
	      APIResponse<T> apiResponse = new APIResponse<T>(response.toString(), returnType);
	      if (!apiResponse.success) throw new RuntimeException(apiResponse.error);
	      return apiResponse.data;

	    } catch (Exception e) {

	      e.printStackTrace();
	      return null;

	    } finally {

	      if(connection != null) {
	        connection.disconnect(); 
	      }
	    }
	}
	
	private String loadUrlParameters(Map<String, String> values) {
		StringBuilder sb = new StringBuilder();
		
		for (String key : values.keySet()) {
			if (sb.length() > 0) {
				sb.append("&");
			}
			String value = values.get(key);
			try {
				sb.append((key != null ? URLEncoder.encode(key, "UTF-8") : ""));
				sb.append("=");
				sb.append(value != null ? URLEncoder.encode(value, "UTF-8") : "");
			} catch (UnsupportedEncodingException e) {
				throw new RuntimeException("This method is not supported", e);
			}
			 
		}
		return sb.toString();
	}

	protected <T> String joinList(ArrayList<T> list){
		StringBuilder sb = new StringBuilder();
		
		for (T item : list){
			sb.append(item);
			sb.append(',');
		}
		
		sb.deleteCharAt(sb.length() - 1);
		
		return sb.toString();
	}
	protected <T> String joinList(Iterable<T> list){
		StringBuilder sb = new StringBuilder();
		
		for (T item : list){
			sb.append(item);
			sb.append(',');
		}
		
		sb.deleteCharAt(sb.length() - 1);
		
		return sb.toString();
	}
}