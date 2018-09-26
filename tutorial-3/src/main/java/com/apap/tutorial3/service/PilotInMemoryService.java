package com.apap.tutorial3.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.apap.tutorial3.model.PilotModel;

/**
 * 
 * PilotINMemoryService
 *
 */
@Service	
public class PilotInMemoryService implements PilotService{
	private List<PilotModel> archivePilot;
	
	public PilotInMemoryService() {
		archivePilot = new ArrayList<>();
	}
	
	@Override
	public void addPilot(PilotModel pilot) {
		// TODO Auto-generated method stub
		archivePilot.add(pilot);
	}

	@Override
	public List<PilotModel> getPilotList() {
		// TODO Auto-generated method stub
		return archivePilot;
	}

	@Override
	public PilotModel getDetailPilotByLicenseNumber(String licenseNumber) {
		// TODO Auto-generated method stub
		PilotModel result = null; 
		for(int i = 0; i < archivePilot.size(); i++) {
			if(archivePilot.get(i).getLicenseNumber().equalsIgnoreCase(licenseNumber)) {
				result = archivePilot.get(i);
			}
		}
		return result;
	}

	@Override
	public PilotModel delete(String id) {
		// TODO Auto-generated method stub
		PilotModel res = null;
		for(PilotModel pilot: archivePilot) {
			if(pilot.getId().equals(id)) {
				res = pilot;
			}
		}
		return res;
	}
	
	
} 


