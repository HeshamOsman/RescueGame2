package model.disasters;

import model.infrastructure.ResidentialBuilding;
import model.people.Citizen;

public class Injury extends Disaster {

	public Injury(int startCycle, Citizen target) {

		super(startCycle, target);

	}
	
	public void strike()
	{
		//should implement here...
		super.strike();
		
		Citizen t = (Citizen) super.getTarget();
		t.setBloodLoss(30);
	}
	
	public void cycleStep()
	{
		//should implement here...
		strike();
	}

}
