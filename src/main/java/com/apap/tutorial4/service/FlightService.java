package com.apap.tutorial4.service;

import java.util.List;

import com.apap.tutorial4.model.FlightModel;
import com.apap.tutorial4.model.PilotModel;

/**
 * FlightService
 * @author nasya
 */
public interface FlightService {
	void addFlight(FlightModel flight);
	void deleteFlight(FlightModel flight);
	FlightModel getFlightById(long id);
	List<FlightModel> getFlightList();
}
