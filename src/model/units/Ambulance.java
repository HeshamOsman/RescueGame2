package model.units;

import model.events.WorldListener;
import simulation.Address;
import simulation.Rescuable;

public class Ambulance extends MedicalUnit {

	public Ambulance(String unitID, Address location, int stepsPerCycle , WorldListener worldlistener) {

		super(unitID, location, stepsPerCycle , worldlistener);

	}
	
	public void treat()
	{
		//should implement here...	
		super.getTarget().getDisaster().setActive(false);
		//super.treat();
		super.getTarget().getDisaster().setActive(true);
		
	}
	
	public void respond(Rescuable r)
	{
		super.respond(r);
	}

}
