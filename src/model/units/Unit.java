package model.units;

import simulation.Address;
import simulation.Rescuable;
import simulation.Simulatable;
import model.disasters.Collapse;
import model.disasters.Disaster;
import model.events.*;
public abstract class Unit implements Simulatable , SOSResponder {

	private String unitID;
	private UnitState state;
	private Address location;
	private Rescuable target;
	private int distanceToTarget;
	private int stepsPerCycle;
	
	private WorldListener worldListener;

	public Unit(String unitID, Address location, int stepsPerCycle , WorldListener worldlistener) {

		this.unitID = unitID;
		this.location = location;
		this.stepsPerCycle = stepsPerCycle;
		this.state = UnitState.IDLE;
		this.worldListener = worldlistener;

	}
	
	
	public  void cycleStep()
	{
		//should implement here...
			if(state == UnitState.RESPONDING)
			{
			  distanceToTarget -= stepsPerCycle;
			}
			
			if(state == UnitState.IDLE)
			{
			}
			
			treat();

			
	}
	
	public void treat()
	{
		//should implement here...
		//target.getDisaster().setActive(false);
	
	}
	
	 public void heal() 
	 {
		//should implement here...
	 }
	
	public void jobsDone()
	{
		//should implement here...
	}
	
	public void respond(Rescuable r)
	{
		//should implement here...
		this.target = r;
		this.state = UnitState.RESPONDING;
		
		//update distance..	
		distanceToTarget -= stepsPerCycle;
		
	     Address p1 = r.getLocation();
	     int d = (location.getX() - p1.getX()) + (location.getY() - p1.getY());
	     distanceToTarget = d;
	}
	

	public UnitState getState() {
		return state;
	}

	public void setState(UnitState state) {
		this.state = state;
	}

	public Address getLocation() {
		return location;
	}

	public void setLocation(Address location) {
		this.location = location;
	}

	public String getUnitID() {
		return unitID;
	}

	public Rescuable getTarget() {
		return target;
	}

	public int getStepsPerCycle() {
		return stepsPerCycle;
	}
	
	public void setWorldListener (WorldListener worldListener)
	{
		this.worldListener =  worldListener;
	}
	
	public WorldListener getWorldListener ()
	{
		return this.worldListener;
	}
	
	public void setDistanceToTarget(int distanceToTarget)
	{
		this.distanceToTarget = distanceToTarget;
	}

}
