package com.raj.employee.serviceImpl;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.raj.employee.dto.CityDto;
import com.raj.employee.dto.CountryDto;
import com.raj.employee.dto.KeyValueDto;
import com.raj.employee.service.ChartAndGraphService;
import com.raj.project.configuration.ProjectConfiguration;
import com.raj.util.WebServiceUtil;

@Service
public class ChartAndGraphServiceImpl implements ChartAndGraphService{

	@Autowired
	WebServiceUtil webService;
	private static Logger LOGGER = Logger.getLogger(ChartAndGraphServiceImpl.class); 

	@Override
	public List<KeyValueDto> areaWiseCountries() {
		List<KeyValueDto> list = new ArrayList<KeyValueDto>();
		try {
			String url = ProjectConfiguration.serviceUrl + "/areaWiseCountries";
			JSONObject jObj = webService.getResponse(new JSONObject(), url, null, null);
			if(null != jObj){
				JSONArray jArray = jObj.getJSONArray("keyValue");
				for(int i=0; i<jArray.length(); i++){
					KeyValueDto dto = new Gson().fromJson(jArray.get(i).toString(), KeyValueDto.class);
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
		List<String> list = new ArrayList<String>();
		try {
			String url = ProjectConfiguration.serviceUrl + "/getAllCountryCode";
			JSONObject jObj = webService.getResponse(new JSONObject(), url, null, null);
			if(null != jObj){
				JSONArray jArray = jObj.getJSONArray("countryCode");
				for(int i=0; i<jArray.length(); i++){
					String code = new Gson().fromJson(jArray.get(i).toString(), String.class);
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
		List<CityDto> list = new ArrayList<CityDto>();
		JSONObject input = new JSONObject();
		input.put("countryCode", countryCode);
		try {
			String url = ProjectConfiguration.serviceUrl + "/cityWisePopulation";
			JSONObject jObj = webService.getResponse(input, url, null, null);
			if(null != jObj){
				JSONArray jArray = jObj.getJSONArray("cityPopulation");
				for(int i=0; i<jArray.length(); i++){
					CityDto dto = new Gson().fromJson(jArray.get(i).toString(), CityDto.class);
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
		List<String> list = new ArrayList<String>();
		try {
			String url = ProjectConfiguration.serviceUrl + "/getStateNames";
			JSONObject jObj = webService.getResponse(new JSONObject(), url, null, null);
			if(null != jObj){
				JSONArray jArray = jObj.getJSONArray("stateNames");
				for(int i=0; i<jArray.length(); i++){
					JsonReader reader = new JsonReader(new StringReader(jArray.getString(i).toString()));
					reader.setLenient(true);
					String stateName = new Gson().fromJson(reader, String.class);
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
		List<CountryDto> list = new ArrayList<CountryDto>();
		JSONObject input = new JSONObject();
		input.put("stateName", stateName);
		try {
			String url = ProjectConfiguration.serviceUrl + "/stateWisePopulation";
			JSONObject jObj = webService.getResponse(input, url, null, null);
			if(null != jObj){
				JSONArray jArray = jObj.getJSONArray("statePopulation");
				for(int i=0; i<jArray.length(); i++){
					CountryDto dto = new Gson().fromJson(jArray.get(i).toString(), CountryDto.class);
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

	@Override
	public List<CityDto> getPopulation() {
		List<CityDto> list = new ArrayList<CityDto>();
		try {
			String url = ProjectConfiguration.serviceUrl + "/getPopulation";
			JSONObject jObj = webService.getResponse(new JSONObject(), url, null, null);
			String status = jObj.getString("status");
			if(status.equals("1")){
				JSONArray jArray = jObj.getJSONArray("population");
				for(int i=0; i<jArray.length(); i++){
					CityDto dto = new Gson().fromJson(jArray.get(i).toString(), CityDto.class);
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