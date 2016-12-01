package com.raj.util;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

public class WebServiceUtil {

	private static Logger LOGGER = Logger.getLogger(WebServiceUtil.class);

	
	/**
	 * @param inputJson
	 * @param url
	 * @param startPoint
	 * @param pageLimit
	 * @return
	 */
	public JSONObject getResponse(Object inputJson, String url, String startPoint, String pageLimit){
		JSONObject responseJSon = null;
		try {
			String webServiceResponse = webServiceCall(inputJson, url, startPoint, pageLimit); 
			responseJSon = new JSONObject( webServiceResponse );
			String status = responseJSon.getString("status");
			LOGGER.info("\nStatus : " + status);
			if(status.equals("1")){
				LOGGER.info("\nResponse Data:\n" + responseJSon);
				return responseJSon;
			} else {
				responseJSon = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Exception in Service Call: "+e.getMessage());
		}
		return responseJSon;
	}
	
	/**
	 * @param inputJson
	 * @param url
	 * @param startPoint
	 * @param pageLimit
	 * @return
	 */
	public String getStatus(Object inputJson, String url, String startPoint, String pageLimit){
		String status = "0";
		try {
			String webServiceResponse = webServiceCall(inputJson, url, startPoint, pageLimit);
			JSONObject responseJSon = new JSONObject( webServiceResponse );
			status = responseJSon.get("status").toString();
			LOGGER.info("\nStatus : " + status);
		} catch (Exception e) {
			LOGGER.error("Exception in Service Call: "+e.getMessage());
		}
		return status;
	}
	
	/**
	 * @param inputJson
	 * @param url
	 * @param startPoint
	 * @param pageLimit
	 * @return
	 */
	public static String webServiceCall(Object inputJson, String url, String startPoint, String pageLimit){
		String webServiceResponse = null;
		try {
			boolean isValidJson = false;
			if( inputJson instanceof JSONObject ) {
				isValidJson = true;
			} else if( inputJson instanceof JSONArray ){
				isValidJson = true;
			} else {
				LOGGER.info("\n Invalid Input \n");
				isValidJson = false;
			}
			if( isValidJson ){
				HttpHeaders headers = new HttpHeaders();
				RestTemplate restTemplate = new RestTemplate();
				JSONObject requestJson = new JSONObject();
				requestJson.put("requestData", inputJson );
				if( startPoint != null && !"".equals( startPoint ) )
					requestJson.put("startPoint", startPoint);
				if( pageLimit != null && !"".equals( pageLimit ) )
					requestJson.put("limit", pageLimit);
				if(LOGGER.isInfoEnabled())
					LOGGER.info("\nInput for " + url + " :\n" + requestJson);
				headers.setContentType(MediaType.APPLICATION_JSON);
				HttpEntity<String> entity = new HttpEntity<String>(requestJson.toString(), headers);
				webServiceResponse = restTemplate.postForObject(url, entity, String.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Exception in Service Call: "+e.getMessage());
		}
		return webServiceResponse;
	}
}
