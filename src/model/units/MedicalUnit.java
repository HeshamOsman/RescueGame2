package model.units;

import java.lang.Thread.State;

import model.events.WorldListener;
import model.people.Citizen;
import simulation.Address;
import simulation.Rescuable;

public abstract class MedicalUnit extends Unit {

	private int healingAmount;
	private int treatmentAmount;

	public MedicalUnit(String unitID, Address location, int stepsPerCycle , WorldListener worldlistener) {

		super(unitID, location, stepsPerCycle , worldlistener);
		healingAmount = 10;
		treatmentAmount = 10;

	}
	
	public int getTreatmentAmount()
	{
		return this.treatmentAmount;
	}
	
	public void treat()
	{
		//should implement here...
		super.treat();

	}
	
	 public void heal() 
	 {
		//should implement here...
		 Citizen c = (Citizen)super.getTarget();
		 c.setHp(c.getHp() + healingAmount);	
		 super.setState(UnitState.IDLE);
	 }
	 
	 public void respond(Rescuable r)
		{
		 super.respond(r);
		}

}
