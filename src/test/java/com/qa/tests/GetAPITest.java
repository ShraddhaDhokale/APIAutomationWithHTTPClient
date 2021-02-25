package com.qa.tests;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.client.RestClient;
import com.qa.restapi.TestBase;

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
	
	@BeforeMethod
	public void setup() throws IOException{
		testBase = new TestBase();
		endpointUrl = prop.getProperty("url");
		serviceUrl = prop.getProperty("serviceUrl");
		url = endpointUrl + serviceUrl;
	}
	
	@Test
	public void getAPITest() throws ClientProtocolException, IOException{
		restClient = new RestClient();
		restClient.get(url);
	}
	
}
