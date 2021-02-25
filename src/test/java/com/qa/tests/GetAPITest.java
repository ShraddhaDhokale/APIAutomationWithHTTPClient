package com.qa.tests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.client.RestClient;
import com.qa.restapi.TestBase;
import com.qa.util.TestUtil;

import junit.framework.Assert;

public class GetAPITest extends TestBase{
	
	public GetAPITest() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	TestBase testBase;
	String endpointUrl;
	String serviceUrl;
	String url;
	RestClient restClient;
	CloseableHttpResponse closeablehttpResponse;
	
	@BeforeMethod
	public void setup() throws IOException{
		testBase = new TestBase();
		endpointUrl = prop.getProperty("url");
		serviceUrl = prop.getProperty("serviceUrl");
		url = endpointUrl + serviceUrl;
	}
	
	@Test(priority=1)
	public void getAPITestWithoutHeader() throws ClientProtocolException, IOException{
		restClient = new RestClient();
		closeablehttpResponse = restClient.get(url);
		
		//2. Status code
		int statusCode = closeablehttpResponse.getStatusLine().getStatusCode();
		System.out.println("The status code is ===== "+statusCode);
		
		Assert.assertEquals(RESPONSE_CODE_200, statusCode);
		
		//3. JSON Response
		String responseString = EntityUtils.toString(closeablehttpResponse.getEntity(), "UTF-8");
		
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("The Response JSON is ====== "+responseJson);
		
		
		//single values(JSON objects):
		String perPageValue = TestUtil.getValueByJPath(responseJson, "/per_page");
		System.out.println("the value of per page is ====> "+perPageValue);
		Assert.assertEquals("6", perPageValue);
		
		String totalValue = TestUtil.getValueByJPath(responseJson, "/total");
		System.out.println("the value of per page is ====> "+totalValue);
		Assert.assertEquals("12", totalValue);
		
		//JSON arrays:
		String lastName = TestUtil.getValueByJPath(responseJson, "/data[0]/last_name");
		String id = TestUtil.getValueByJPath(responseJson, "/data[0]/id");
		String avatar = TestUtil.getValueByJPath(responseJson, "/data[0]/avatar");
		String firstName = TestUtil.getValueByJPath(responseJson, "/data[0]/first_name");
		
		System.out.println(lastName);
		System.out.println(id);
		System.out.println(avatar);
		System.out.println(firstName);
		
		//4. All Headers
		Header[] headersArray = closeablehttpResponse.getAllHeaders();
		HashMap<String, String> allHeaders = new HashMap<String, String>();
		for (Header header:headersArray){
			allHeaders.put(header.getName(), header.getValue());
		}
		System.out.println("Headers Array ======="+allHeaders);
	}
	
	
	@Test(priority=2)
	public void getAPITestWithHeader() throws ClientProtocolException, IOException{
		restClient = new RestClient();
		
		HashMap<String, String> hedaerMap = new HashMap<String, String>();
		hedaerMap.put("Content-Type", "application/json");
		
		
		closeablehttpResponse = restClient.get(url);
		
		//2. Status code
		int statusCode = closeablehttpResponse.getStatusLine().getStatusCode();
		System.out.println("The status code is ===== "+statusCode);
		
		Assert.assertEquals(RESPONSE_CODE_200, statusCode);
		
		//3. JSON Response
		String responseString = EntityUtils.toString(closeablehttpResponse.getEntity(), "UTF-8");
		
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("The Response JSON is ====== "+responseJson);
		
		
		//single values(JSON objects):
		String perPageValue = TestUtil.getValueByJPath(responseJson, "/per_page");
		System.out.println("the value of per page is ====> "+perPageValue);
		Assert.assertEquals("6", perPageValue);
		
		String totalValue = TestUtil.getValueByJPath(responseJson, "/total");
		System.out.println("the value of per page is ====> "+totalValue);
		Assert.assertEquals("12", totalValue);
		
		//JSON arrays:
		String lastName = TestUtil.getValueByJPath(responseJson, "/data[0]/last_name");
		String id = TestUtil.getValueByJPath(responseJson, "/data[0]/id");
		String avatar = TestUtil.getValueByJPath(responseJson, "/data[0]/avatar");
		String firstName = TestUtil.getValueByJPath(responseJson, "/data[0]/first_name");
		
		System.out.println(lastName);
		System.out.println(id);
		System.out.println(avatar);
		System.out.println(firstName);
		
		//4. All Headers
		Header[] headersArray = closeablehttpResponse.getAllHeaders();
		HashMap<String, String> allHeaders = new HashMap<String, String>();
		for (Header header:headersArray){
			allHeaders.put(header.getName(), header.getValue());
		}
		System.out.println("Headers Array ======="+allHeaders);
	}
	
	
}
