package model.disasters;

import model.infrastructure.ResidentialBuilding;

public class GasLeak extends Disaster {

	public GasLeak(int startCycle, ResidentialBuilding target) {

		super(startCycle, target);

	}
	
	public void strike()
	{
		//should implement here...
		
		ResidentialBuilding t = (ResidentialBuilding) super.getTarget();
		t.setGasLevel(t.getGasLevel() + 10);
	}
	
	public void cycleStep()
	{
		//should implement here...
		strike();
	}

}
