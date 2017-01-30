package com.raj.employee.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.raj.employee.dto.EmployeeDto;
import com.raj.employee.service.EmployeeService;
import com.raj.project.environment.ProjectConfiguration;
import com.raj.util.WebServiceUtil;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	WebServiceUtil webService;
	private static Logger LOGGER = Logger.getLogger(EmployeeServiceImpl.class);
	
	@Override
	public String saveOrUpdateEmployee(EmployeeDto dto) {
		JSONObject input = new JSONObject();
		input.put("emp", new JSONObject(dto));
		String status = "0";
		try {
			String url = ProjectConfiguration.serviceUrl + "/saveOrUpdateEmployee";
			status = webService.getStatus(input, url, null, null);
		} catch (Exception e) {
			LOGGER.error("Exception: "+e.getMessage());
		}
		return status;
	}

	@Override
	public List<EmployeeDto> getAllEmployee() {
		List<EmployeeDto> list = new ArrayList<EmployeeDto>();
		try {
			String url = ProjectConfiguration.serviceUrl + "/getAllEmployee";
			JSONObject jObj = webService.getResponse(new JSONObject(), url, null, null);
			if(null != jObj){
				JSONArray jArray = jObj.getJSONArray("empList");
				for(int i=0; i<jArray.length(); i++){
					EmployeeDto dto = new Gson().fromJson(jArray.get(i).toString(), EmployeeDto.class);
					list.add(dto);
				}
				 LOGGER.info("Total Employees: "+list.size());
			}
		} catch (Exception e) {
			LOGGER.error("Exception: "+e.getMessage());
		}
		return list;
	}

	@Override
	public EmployeeDto getEmployeeById(String id) {
		EmployeeDto dto = null;
		JSONObject input = new JSONObject();
		input.put("empId", id);
		try {
			String url = ProjectConfiguration.serviceUrl + "/getEmployeeById";
			JSONObject jObj = webService.getResponse(input, url, null, null);
			if(null != jObj){
				dto = new Gson().fromJson(jObj.get("emp").toString(), EmployeeDto.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Exception: "+e.getMessage());
		}
		return dto;
	}

	@Override
	public String deleteEmployee(String id) {
		String status = null;
		JSONObject input = new JSONObject();
		input.put("empId", id);
		try {
			String url = ProjectConfiguration.serviceUrl + "/deleteEmployee";
			status = webService.getStatus(input, url, null, null);
		} catch (Exception e) {
			LOGGER.error("Exception: "+e.getMessage());
		}
		return status;
	}

}
