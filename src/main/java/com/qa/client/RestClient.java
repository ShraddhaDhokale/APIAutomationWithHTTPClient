package com.qa.client;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClient {

	//1. GET Method
	public void get(String url) throws ClientProtocolException, IOException{
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);//http get request
		CloseableHttpResponse closeablehttpResponse = httpClient.execute(httpGet);//hit the GET url
		
	//2. Status code
	int statusCode = closeablehttpResponse.getStatusLine().getStatusCode();
	System.out.println("The status code is ===== "+statusCode);
	
	//3. JSON Response
	String responseString = EntityUtils.toString(closeablehttpResponse.getEntity(), "UTF-8");
	
	JSONObject responseJson = new JSONObject(responseString);
	System.out.println("The Response JSON is ====== "+responseJson);
	
	//4. All Headers
	Header[] headersArray = closeablehttpResponse.getAllHeaders();
	HashMap<String, String> allHeaders = new HashMap<String, String>();
	for (Header header:headersArray){
		allHeaders.put(header.getName(), header.getValue());
	}
	System.out.println("Headers Array ======="+allHeaders);
		
		
		
	}
}
