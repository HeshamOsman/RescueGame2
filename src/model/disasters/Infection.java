package model.disasters;

import model.infrastructure.ResidentialBuilding;
import model.people.Citizen;

public class Infection extends Disaster {

	public Infection(int startCycle, Citizen target) {

		super(startCycle, target);

	}
	
	
	public void strike()
	{
		//should implement here...
		
		Citizen t = (Citizen) super.getTarget();
		t.setToxicity( t.getToxicity() +  15);
	}
	
	public void cycleStep()
	{
		//should implement here...
		strike();
	}

}
