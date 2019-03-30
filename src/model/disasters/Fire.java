package model.disasters;

import model.infrastructure.ResidentialBuilding;
import simulation.Rescuable;

public class Fire extends Disaster {

	public Fire(int startCycle, ResidentialBuilding target) {

		super(startCycle, target);

	}
	
	public void strike()
	{
		//should implement here...
		super.strike();
		
		ResidentialBuilding t = (ResidentialBuilding) super.getTarget();
		
		if(t.getFireDamage() < 70)
		{
			t.setFireDamage( t.getFireDamage() + 10);
		}
		else {
			t.setFireDamage( t.getFireDamage() + 7);
		}
		
	}
	
	public void cycleStep()
	{
		//should implement here...
		strike();
	}

}
