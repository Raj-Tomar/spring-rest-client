package com.raj.employee.serviceImpl;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.raj.employee.dto.CityDto;
import com.raj.employee.dto.CountryDto;
import com.raj.employee.dto.KeyValueDto;
import com.raj.employee.service.ChartAndGraphService;
import com.raj.project.configuration.ProjectConfiguration;

@Service
public class ChartAndGraphServiceImpl implements ChartAndGraphService{

	private static Logger LOGGER = Logger.getLogger(ChartAndGraphServiceImpl.class); 

	@Override
	public List<KeyValueDto> areaWiseCountries() {
		HttpHeaders headers = null;
		HttpEntity<String> entity = null;
		RestTemplate restTemplate = null;
		String url = null;
		JSONObject input = null;
		JSONObject requestData = null;
		Gson gson = null;
		String serviceResponse = null;

		List<KeyValueDto> list = new ArrayList<KeyValueDto>();
		input = new JSONObject();
		try {
			requestData = new JSONObject();
			requestData.put("requestData",input);
			headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			entity = new HttpEntity<String>(requestData.toString(), headers);
			url = ProjectConfiguration.serviceUrl + "/areaWiseCountries";
			restTemplate = new RestTemplate();
			serviceResponse = restTemplate.postForObject(url, entity, String.class);
			JSONObject jObj = new JSONObject(serviceResponse);
			String status = jObj.getString("status");
			if(status.equals("1")){
				gson = new Gson();
				JSONArray jArray = jObj.getJSONArray("keyValue");
				for(int i=0; i<jArray.length(); i++){
					KeyValueDto dto = gson.fromJson(jArray.get(i).toString(), KeyValueDto.class);
					list.add(dto);
				}
				if(LOGGER.isInfoEnabled())
					LOGGER.info("Total Recoreds: "+list.size());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<String> getAllCountryCode() {
		HttpHeaders headers = null;
		HttpEntity<String> entity = null;
		RestTemplate restTemplate = null;
		String url = null;
		JSONObject input = null;
		JSONObject requestData = null;
		Gson gson = null;
		String serviceResponse = null;

		List<String> list = new ArrayList<String>();
		input = new JSONObject();
		try {
			requestData = new JSONObject();
			requestData.put("requestData", input);
			headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			entity = new HttpEntity<String>(requestData.toString(), headers);
			url = ProjectConfiguration.serviceUrl + "/getAllCountryCode";
			restTemplate = new RestTemplate();
			serviceResponse = restTemplate.postForObject(url, entity, String.class);
			JSONObject jObj = new JSONObject(serviceResponse);
			String status = jObj.getString("status");
			if(status.equals("1")){
				gson = new Gson();
				JSONArray jArray = jObj.getJSONArray("countryCode");
				for(int i=0; i<jArray.length(); i++){
					String code = gson.fromJson(jArray.get(i).toString(), String.class);
					list.add(code);
				}
				if(LOGGER.isInfoEnabled())
					LOGGER.info("Total Countries: "+list.size());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<CityDto> cityWisePopulation(String countryCode) {
		HttpHeaders headers = null;
		HttpEntity<String> entity = null;
		RestTemplate restTemplate = null;
		String url = null;
		JSONObject input = null;
		JSONObject requestData = null;
		Gson gson = null;
		String serviceResponse = null;

		List<CityDto> list = new ArrayList<CityDto>();
		input = new JSONObject();
		input.put("countryCode", countryCode);
		try {
			requestData = new JSONObject();
			requestData.put("requestData", input);
			headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			entity = new HttpEntity<String>(requestData.toString(), headers);
			url = ProjectConfiguration.serviceUrl + "/cityWisePopulation";
			restTemplate = new RestTemplate();
			serviceResponse = restTemplate.postForObject(url, entity, String.class);
			JSONObject jObj = new JSONObject(serviceResponse);
			String status = jObj.getString("status");
			if(status.equals("1")){
				gson = new Gson();
				JSONArray jArray = jObj.getJSONArray("cityPopulation");
				for(int i=0; i<jArray.length(); i++){
					CityDto dto = gson.fromJson(jArray.get(i).toString(), CityDto.class);
					list.add(dto);
				}
				if(LOGGER.isInfoEnabled())
					LOGGER.info("Total States: "+list.size());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<String> getStateNames() {
		HttpHeaders headers = null;
		HttpEntity<String> entity = null;
		RestTemplate restTemplate = null;
		String url = null;
		JSONObject input = null;
		JSONObject requestData = null;
		Gson gson = null;
		String serviceResponse = null;

		List<String> list = new ArrayList<String>();
		input = new JSONObject();
		try {
			requestData = new JSONObject();
			requestData.put("requestData", input);
			headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			entity = new HttpEntity<String>(requestData.toString(), headers);
			url = ProjectConfiguration.serviceUrl + "/getStateNames";
			restTemplate = new RestTemplate();
			serviceResponse = restTemplate.postForObject(url, entity, String.class);
			JSONObject jObj = new JSONObject(serviceResponse);
			String status = jObj.getString("status");
			if(status.equals("1")){
				gson = new Gson();
				JSONArray jArray = jObj.getJSONArray("stateNames");
				for(int i=0; i<jArray.length(); i++){
					JsonReader reader = new JsonReader(new StringReader(jArray.getString(i).toString()));
					reader.setLenient(true);
					String stateName = gson.fromJson(reader, String.class);
					list.add(stateName);
				}
				if(LOGGER.isInfoEnabled())
					LOGGER.info("Total States: "+list.size());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<CountryDto> stateWisePopulation(String stateName) {
		HttpHeaders headers = null;
		HttpEntity<String> entity = null;
		RestTemplate restTemplate = null;
		String url = null;
		JSONObject input = null;
		JSONObject requestData = null;
		Gson gson = null;
		String serviceResponse = null;

		List<CountryDto> list = new ArrayList<CountryDto>();
		input = new JSONObject();
		input.put("stateName", stateName);
		try {
			requestData = new JSONObject();
			requestData.put("requestData", input);
			headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			entity = new HttpEntity<String>(requestData.toString(), headers);
			url = ProjectConfiguration.serviceUrl + "/stateWisePopulation";
			restTemplate = new RestTemplate();
			serviceResponse = restTemplate.postForObject(url, entity, String.class);
			JSONObject jObj = new JSONObject(serviceResponse);
			String status = jObj.getString("status");
			if(status.equals("1")){
				gson = new Gson();
				JSONArray jArray = jObj.getJSONArray("statePopulation");
				for(int i=0; i<jArray.length(); i++){
					CountryDto dto = gson.fromJson(jArray.get(i).toString(), CountryDto.class);
					list.add(dto);
				}
				if(LOGGER.isInfoEnabled())
					LOGGER.info("Total Cities: "+list.size());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
