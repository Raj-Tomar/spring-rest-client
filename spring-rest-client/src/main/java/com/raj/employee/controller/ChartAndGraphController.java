package com.raj.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.raj.employee.dto.CityDto;
import com.raj.employee.dto.CountryDto;
import com.raj.employee.service.ChartAndGraphService;

@Controller
public class ChartAndGraphController {
	
	@Autowired
	ChartAndGraphService chartService;
	
	@RequestMapping(value="/getAllCountryCode", method=RequestMethod.POST)
	@ResponseBody
	public List<String> getAllCountryCode(){
		List<String> list = null;
		try {
			list = chartService.getAllCountryCode();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@RequestMapping(value="/cityWisePopulation/{countryCode}", method=RequestMethod.POST)
	@ResponseBody
	public List<CityDto> cityWisePopulation(@PathVariable("countryCode")String countryCode){
		List<CityDto> list = null;
		try {
			list = chartService.cityWisePopulation(countryCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@RequestMapping(value="/getStateNames", method=RequestMethod.POST)
	@ResponseBody
	public List<String> getStateNames(){
		List<String> list = null;
		try {
			list = chartService.getStateNames();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@RequestMapping(value="/stateWisePopulation/{stateName}", method=RequestMethod.POST)
	@ResponseBody
	public List<CountryDto> stateWisePopulation(@PathVariable("stateName")String stateName){
		List<CountryDto> list = null;
		try {
			list = chartService.stateWisePopulation(stateName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@RequestMapping(value="/getPopulation", method=RequestMethod.POST)
	@ResponseBody
	public List<CityDto> getPopulation(){
		List<CityDto> list = null;
		try {
			list = chartService.getPopulation();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
