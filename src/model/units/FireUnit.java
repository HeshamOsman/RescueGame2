package model.units;

import model.events.WorldListener;
import simulation.Address;

public abstract class FireUnit extends Unit {

	public FireUnit(String unitID, Address location, int stepsPerCycle , WorldListener worldlistener) {

		super(unitID, location, stepsPerCycle , worldlistener);

	}
	
	public void treat()
	{
		//should implement here...
		super.treat();
	}

}
