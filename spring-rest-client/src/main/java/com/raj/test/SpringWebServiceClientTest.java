package com.raj.test;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.raj.employee.dto.EmployeeDto;

public class SpringWebServiceClientTest {
	
	public static void main(String[] args) {
		EmployeeDto dto = new EmployeeDto();
		dto.setFirstName("Raj");
		dto.setLastName("Tomar");
		dto.setDesignation("Software Engineer");
		dto.setCompanyName("SpSoft");
		dto.setAge("26");
		JSONObject input = new JSONObject(dto);
		try {
			JSONObject reqData = new JSONObject();
			reqData.put("reqData", input);
			System.out.println(reqData.toString());
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> entity = new HttpEntity<String>(reqData.toString(), headers);
			String url = "http://localhost:8080/spring-rest/getAllEmployee";
			//url = "http://localhost:8080/spring-rest/saveOrUpdateEmployee";
			RestTemplate restTemplate = new RestTemplate();
			String result = restTemplate.postForObject(url, entity, String.class);
			System.out.println("Result From WebService:\n"+result);
		} catch (Exception e) {
			
		}
		
	}

}
