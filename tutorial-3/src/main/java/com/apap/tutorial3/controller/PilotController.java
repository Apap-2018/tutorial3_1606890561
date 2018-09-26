package com.apap.tutorial3.controller;

import com.apap.tutorial3.model.PilotModel;
import com.apap.tutorial3.service.PilotService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class PilotController {
	@Autowired
	private PilotService pilotService;
	
	@RequestMapping("/pilot/add")
	public String add(@RequestParam(value = "id", required = true) String id,
			@RequestParam(value = "licenseNumber", required = true) String licenseNumber,
			@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "flyHour", required = true) int flyHour) {
		
		PilotModel pilot = new PilotModel(id, licenseNumber, name, flyHour);
		pilotService.addPilot(pilot);
		
		return "add";
	}
	
	@RequestMapping("/pilot/view")
	public String view(@RequestParam("licenseNumber") String licenseNumber, Model model) {
		//String result = "view-pilot";
		PilotModel archive = pilotService.getDetailPilotByLicenseNumber(licenseNumber);

	    model.addAttribute("pilot", archive);
		return "view-pilot";
	}
	
	@RequestMapping("/pilot/viewall")
	public String viewall(Model model) {
		List<PilotModel> archive = pilotService.getPilotList();
		model.addAttribute("listPilot", archive);
		return "viewall-pilot";
	}
	
	@RequestMapping("/pilot/view/license-number/{licenseNumber}")
	public String path(@PathVariable String licenseNumber, Model model) {
		String result = "view-pilot";
		PilotModel archive = pilotService.getDetailPilotByLicenseNumber(licenseNumber);
		if (archive == null) result = "error";
		else model.addAttribute("pilot", archive);
		return result;
	}
	
	@RequestMapping("/pilot/update/license-number/{licenseNumber}/fly-hour/{flyHour}")
	public String changeFlyHour(@PathVariable String licenseNumber, @PathVariable int flyHour, Model model) {
		String result = "change-flyHour";
		PilotModel archive = pilotService.getDetailPilotByLicenseNumber(licenseNumber);
		if (archive == null) result = "error";
		else {
			archive.setFlyHour(flyHour);
			model.addAttribute("pilot", archive);
		}
		return result;
	}
	
	@RequestMapping("/pilot/delete/id/{id}")
	public String deleteById(@PathVariable String id) {
		String result = "deleteById";
		PilotModel archive = pilotService.delete(id);
		if(archive == null) result = "error"; 
		return result;
	}
	
}
