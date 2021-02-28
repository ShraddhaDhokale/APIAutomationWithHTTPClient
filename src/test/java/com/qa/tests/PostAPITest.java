package com.qa.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.client.RestClient;
import com.qa.data.Users;
import com.qa.restapi.TestBase;

import junit.framework.Assert;

public class PostAPITest extends TestBase {

	public PostAPITest() throws IOException {
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
	
	@Test
	public void postAPITest() throws JsonGenerationException, JsonMappingException, IOException{
		restClient = new RestClient();
		
		HashMap<String, String> hedaerMap = new HashMap<String, String>();
		hedaerMap.put("Content-Type", "application/json");
		
		//jackson API
		ObjectMapper mapper = new ObjectMapper();
		Users users = new Users("morpheus", "leader"); //expected users object
		
		//write object to json file
		mapper.writeValue(new File("D:/training_java/restapi/src/main/java/com/qa/data/users.json"), users);
		
		//java object to json string (marshelling)
		String usersJsonString = mapper.writeValueAsString(users);
		System.out.println(usersJsonString);
		
		closeablehttpResponse = restClient.post(url, usersJsonString, hedaerMap); //call the API
		
		//Validate response from API:
		//1. Status code:
		int StatusCode = closeablehttpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(testBase.RESPONSE_CODE_201, StatusCode);
			
		//2. JSON Response
		String responseString = EntityUtils.toString(closeablehttpResponse.getEntity(), "UTF-8");
		
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("The Response JSON is ====== "+responseJson);
		
		//json to java object:
		Users usersResObj = mapper.readValue(responseString, Users.class); //actual users object
		System.out.println(usersResObj);
		
		Assert.assertTrue(users.getName().equals(usersResObj.getName()));
		
		Assert.assertTrue(users.getJob().equals(usersResObj.getJob()));
		
		System.out.println(usersResObj.getId());
		System.out.println(usersResObj.getCreatedAt());
		
	}

}
