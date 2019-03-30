package model.units;

import java.util.ArrayList;

import simulation.Address;
import model.events.WorldListener;
import model.infrastructure.ResidentialBuilding;
import model.people.Citizen;

public abstract class PoliceUnit extends Unit {

	private ArrayList<Citizen> passengers;
	private int maxCapacity;
	private int distanceToBase;
	

	public PoliceUnit(String unitID, Address location, int stepsPerCycle , WorldListener worldlistener , int maxCapacity) {

		super(unitID, location, stepsPerCycle , worldlistener);
		passengers = new ArrayList<Citizen>();
		this.maxCapacity = maxCapacity;

	}

	public int getDistanceToBase() {
		return distanceToBase;
	}

	public void setDistanceToBase(int distanceToBase) {
		this.distanceToBase = distanceToBase;
	}

	public int getMaxCapacity() {
		return maxCapacity;
	}
	
	
	public ArrayList<Citizen> getPassengers()
	{
		return this.passengers;
	}
	
	
	private int current_indx = 0;
	public  void cycleStep()
	{
		//should implement here...
		super.cycleStep();
		
	     
	}
	
	public void treat()
	{
		//should implement here...
		
	}
	

}
