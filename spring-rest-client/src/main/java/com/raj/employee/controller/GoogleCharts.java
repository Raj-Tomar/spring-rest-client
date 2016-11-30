package com.raj.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.raj.employee.dto.KeyValueDto;
import com.raj.employee.service.ChartAndGraphService;

@Controller
public class GoogleCharts {

	@Autowired
	ChartAndGraphService chartService;
	
	@RequestMapping(value="/googleCharts", method=RequestMethod.GET)
	public String googlePieChart(Model model){
		return "googleCharts";
	}
	
	@RequestMapping(value="/googlePieChartData", method=RequestMethod.POST)
	@ResponseBody
	public List<KeyValueDto> googlePieChartData(){
		List<KeyValueDto> list = null;
		try {
			list = chartService.areaWiseCountries();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
