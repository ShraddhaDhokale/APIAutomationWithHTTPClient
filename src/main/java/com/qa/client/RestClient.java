package com.qa.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
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
	
	public CloseableHttpResponse get(String url, HashMap<String, String> headerMap) throws ClientProtocolException, IOException{
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);//http get request
		
		for(Map.Entry<String, String> entry : headerMap.entrySet()){
			httpGet.addHeader(entry.getKey(), entry.getValue());
		}
		
		closeablehttpResponse = httpClient.execute(httpGet);//hit the GET url
		return closeablehttpResponse;
	}
	
}
