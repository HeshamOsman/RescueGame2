package model.infrastructure;

import java.util.ArrayList;

import model.disasters.Disaster;
import model.disasters.Fire;
import model.people.Citizen;
import simulation.Address;
import simulation.Rescuable;
import simulation.Simulatable;

import model.events.*;
public class ResidentialBuilding implements Rescuable, Simulatable {

	private Address location;
	private int structuralIntegrity;
	private int fireDamage;
	private int gasLevel;
	private int foundationDamage;
	private ArrayList<Citizen> occupants;
	private Disaster disaster;
	
	private SOSListener emergencyService;

	public ResidentialBuilding(Address location) {

		this.location = location;
		this.structuralIntegrity = 100;
		occupants = new ArrayList<Citizen>();
		
		
	}
	
	
	public void cycleStep()
	{
		//should implement here...
		
	}
	
	public void struckBy(Disaster d)
	{
		//should implement here...
		this.disaster = d;
		emergencyService.receiveSOSCall(this);
	}
	
	

	public int getStructuralIntegrity() {
		return structuralIntegrity;
	}

	public void setStructuralIntegrity(int structuralIntegrity) {
		this.structuralIntegrity = structuralIntegrity;
		
		if(structuralIntegrity < 0)
		{
			this.structuralIntegrity = 0;
		}
	}

	public int getFireDamage() {
		return fireDamage;
	}

	public void setFireDamage(int fireDamage) {
		this.fireDamage = fireDamage;
		if(fireDamage > 100)
		{
			this.fireDamage = 100;
		}
	}

	public int getGasLevel() {
		return gasLevel;
	}

	public void setGasLevel(int gasLevel) {
		this.gasLevel = gasLevel;
	}

	public int getFoundationDamage() {
		return foundationDamage;
	}

	public void setFoundationDamage(int foundationDamage) {
		
		
		this.foundationDamage = foundationDamage;
		
		if(this.foundationDamage >= 100)
		{
			structuralIntegrity = 0;
		}
		
	}

	public Address getLocation() {
		return location;
	}

	public ArrayList<Citizen> getOccupants() {
		return occupants;
	}

	public Disaster getDisaster() {
		return disaster;
	}
	
	public void setEmergencyService(SOSListener  emergencyService)
	{
		this.emergencyService = emergencyService;
	}

}
