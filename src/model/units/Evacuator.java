package model.units;

import model.events.WorldListener;
import simulation.Address;

public class Evacuator extends PoliceUnit {

	public Evacuator(String unitID, Address location, int stepsPerCycle, WorldListener worldlistener, int maxCapacity) {

		super(unitID, location, stepsPerCycle , worldlistener , maxCapacity);

	}
	
	
	public void treat()
	{
		//should implement here...

		super.treat();
	}

}
