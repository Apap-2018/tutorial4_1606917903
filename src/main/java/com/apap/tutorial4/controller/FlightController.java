package com.apap.tutorial4.controller;

import com.apap.tutorial4.model.FlightModel;
import com.apap.tutorial4.model.PilotModel;
import com.apap.tutorial4.service.FlightService;
import com.apap.tutorial4.service.PilotService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * FlightController
 * @author nasya
 */
@Controller
public class FlightController {
	@Autowired
	private FlightService flightService;
	
	@Autowired
	private PilotService pilotService;
	
	@RequestMapping(value = "/flight/add/{licenseNumber}", method = RequestMethod.GET)
	private String add(@PathVariable(value = "licenseNumber") String licenseNumber, Model model) {
		FlightModel flight = new FlightModel();
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		flight.setPilot(pilot);
		
		model.addAttribute("flight", flight);
		return "addFlight";
	}
	
	@RequestMapping(value = "/flight/add", method = RequestMethod.POST)
	private String addFligthSubmit(@ModelAttribute FlightModel flight) {
		flightService.addFlight(flight);
		return "add";
	}
	
	@RequestMapping(value = "/flight/delete/{id}", method = RequestMethod.GET)
	private String deletePilot(@PathVariable(value = "id") Long id, Model model) {
		FlightModel flight = flightService.getFlightById(id);
		flight.getPilot().getPilotFlight().remove(flight);
		flightService.deleteFlight(flight);
		return "delete";
	}
	
	@RequestMapping(value = "/flight/update/{id}", method = RequestMethod.GET)
	private String updateFligt(@PathVariable(value = "id") long id, Model model) {
		/*PilotModel pilot = new PilotModel();
		pilot.setLicenseNumber(licenseNumber);
		model.addAttribute("pilot", pilot);*/
		model.addAttribute("flight", flightService.getFlightById(id));
		return "updateFlight";
	}
	
	@RequestMapping(value = "/flight/update", method = RequestMethod.POST)
	private String updateFlightSubmit(@ModelAttribute FlightModel flight) {
		FlightModel archive = flightService.getFlightById(flight.getId());
		archive.setDestination(flight.getDestination());
		archive.setFlightNumber(flight.getFlightNumber());
		archive.setOrigin(flight.getOrigin());
		archive.setTime(flight.getTime());
		flightService.addFlight(archive);
		return "update";
	}
	
	@RequestMapping("/flight/view")
	private String viewFlightById(@RequestParam(value = "flightNumber") String flightNumber, Model model) {
		List<FlightModel> archive = flightService.getFlightList();
		List<FlightModel> result = new ArrayList<>();
		for(int i=0; i < archive.size(); i++) {
			FlightModel temp = archive.get(i);
			if(temp.getFlightNumber().equals(flightNumber)) {
				result.add(temp);
			}
		}
		if(!result.isEmpty()) {
			model.addAttribute("listFlight", result);
			return "view-flight";
		}
		else {
			String errorMessage = "Flight dengan flight number "+flightNumber+" tidak ditemukan";
			model.addAttribute("errorMessage", errorMessage);
			return "error-page";
		}
	}
	/*
	@RequestMapping("/flight/view")
	private String viewFlightById(@RequestParam(value = "id") long id, Model model) {
		FlightModel archive = flightService.getFlightById(id);
		if(archive != null) {
			PilotModel pilot = archive.getPilot();
			model.addAttribute("flight", archive);
			model.addAttribute("pilot", pilot);
			return "view-flight";
		}
		else {
			String errorMessage = "Flight dengan id "+id+" tidak ditemukan";
			model.addAttribute("errorMessage", errorMessage);
			return "error-page";
		}		
	}*/
	
	/*
	@RequestMapping(value = "/flight/delete", method = RequestMethod.POST)
	private String deleteFlight(@ModelAttribute FlightModel flight) {
		flight.getPilot().getPilotFlight().remove(flight);
		flightService.deleteFlight(flight);
		return "delete";
	}*/
	/*
	 * PilotModel pilot = flight.getPilot();
		for (int i = 0; i < pilot.getPilotFlight().size(); i++) {
			FlightModel fl = pilot.getPilotFlight().get(i);
			if(fl.equals(flight)) {
				pilot.getPilotFlight().remove(index)
			}
		}
	 */
}
