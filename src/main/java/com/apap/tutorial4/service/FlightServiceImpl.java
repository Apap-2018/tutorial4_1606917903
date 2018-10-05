package com.apap.tutorial4.service;

import com.apap.tutorial4.model.FlightModel;
import com.apap.tutorial4.model.PilotModel;
import com.apap.tutorial4.repository.FlightDB;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * FlightServiceImpl
 * @author nasya
 */
@Service
@Transactional
public class FlightServiceImpl implements FlightService {
	@Autowired
	private FlightDB flightDb;
	private List<FlightModel> archiveFlight;
	
	public FlightServiceImpl() {
		archiveFlight = new ArrayList<>();
	}
	
	@Override
	public void addFlight(FlightModel flight) {
		flightDb.save(flight);
	}

	@Override
	public void deleteFlight(FlightModel flight) {
		// TODO Auto-generated method stub
		flightDb.delete(flight);
	}

	@Override
	public FlightModel getFlightById(long id) {
		return flightDb.findById(id);
	}

	@Override
	public List<FlightModel> getFlightList() {
		// TODO Auto-generated method stub
		archiveFlight = flightDb.findAll();
		return  archiveFlight;
	}

}
