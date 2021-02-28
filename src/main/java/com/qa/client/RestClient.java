package com.qa.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClient {
	
	CloseableHttpResponse closeablehttpResponse;

	//1. GET Method
	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException{
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);//http get request
		closeablehttpResponse = httpClient.execute(httpGet);//hit the GET url
		return closeablehttpResponse;
	}
	
	//GET method with headers (method overloading)
	public CloseableHttpResponse get(String url, HashMap<String, String> headerMap) throws ClientProtocolException, IOException{
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);//http get request
		
		for(Map.Entry<String, String> entry : headerMap.entrySet()){
			httpGet.addHeader(entry.getKey(), entry.getValue());
		}
		
		closeablehttpResponse = httpClient.execute(httpGet);//hit the GET url
		return closeablehttpResponse;
	}
	
	//POST method
	public CloseableHttpResponse post(String url, String entityString, HashMap<String, String> headerMap) throws ClientProtocolException, IOException{
		CloseableHttpClient httpClient = HttpClients.createDefault(); //create client
		HttpPost httpPost = new HttpPost(url); //hit the url --> post request
		httpPost.setEntity(new StringEntity(entityString)); //for payload
		
		//add headers in the header 
		for(Map.Entry<String, String> entry: headerMap.entrySet()){
			httpPost.addHeader(entry.getKey(), entry.getValue());;
		}
		
		closeablehttpResponse = httpClient.execute(httpPost);
		return closeablehttpResponse;
		
		
		
	}
	
}
