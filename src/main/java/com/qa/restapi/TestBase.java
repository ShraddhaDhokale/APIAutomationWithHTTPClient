package com.qa.restapi;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestBase {
	
	public Properties prop;
	
	public TestBase() throws IOException{
		
		try{
			prop = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/com/qa/config/config.properties");
//			FileInputStream ip = new FileInputStream(System.getProperty("D:/training_java/restapi/src/main/java/com/qa/config/config.properties"));
			prop.load(ip);
		}catch (FileNotFoundException e){
			e.printStackTrace();
		}
	}

}
