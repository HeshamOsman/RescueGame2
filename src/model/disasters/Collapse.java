package model.disasters;

import model.infrastructure.ResidentialBuilding;

public class Collapse extends Disaster {

	public Collapse(int startCycle, ResidentialBuilding target) {

		super(startCycle, target);

	}
	
	public void strike()
	{
		//should implement here...
		
		ResidentialBuilding t = (ResidentialBuilding) super.getTarget();
		t.setFoundationDamage(t.getFoundationDamage() + 10);
	}
	
	
	public void cycleStep()
	{
		//should implement here...
		strike();
		
	}

}
