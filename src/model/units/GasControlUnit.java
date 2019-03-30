package model.units;

import model.events.WorldListener;
import simulation.Address;

public class GasControlUnit extends FireUnit {

	public GasControlUnit(String unitID, Address location, int stepsPerCycle, WorldListener worldlistener) {

		super(unitID, location, stepsPerCycle , worldlistener);

	}
	
	public void treat()
	{
		//should implement here...
		
	}

}
