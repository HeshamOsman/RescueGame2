package model.people;

import simulation.Address;
import simulation.Rescuable;
import simulation.Simulatable;
import model.disasters.Disaster;

import model.events.*;

public class Citizen implements Rescuable, Simulatable {

	private CitizenState state;
	private Disaster disaster;
	private String name;
	private String nationalID;
	private int age;
	private int hp;
	private int bloodLoss;
	private int toxicity;
	private Address location;

	private SOSListener emergencyService;
	private WorldListener worldListener;
	
	
	public Citizen(Address location, String nationalID, String name, int age , WorldListener worldlistener) {

		this.name = name;
		this.nationalID = nationalID;
		this.age = age;
		this.location = location;
		this.state = CitizenState.SAFE;
		this.hp = 100;
		this.worldListener = worldlistener;

	}
	
	
	public void cycleStep()
	{
		//should implements here..
		
		if(toxicity > 0 && toxicity < 30)
		{
			hp -= 5;
		}
		
		if(toxicity >= 30 && toxicity < 70)
		{
			hp -= 10;
		}
		
		if(toxicity == 100)
		{
			hp = 0;
			state = CitizenState.DECEASED;
		}
	}
	
	public void struckBy(Disaster d)
	{
		//should implements here..
		this.disaster = d;
		state = CitizenState.IN_TROUBLE;
		
		emergencyService.receiveSOSCall(this);
	}
	

	public CitizenState getState() {
		return state;
	}

	public void setState(CitizenState state) {
		this.state = state;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
		
		if(hp > 100)
		{
			this.hp = 100;
		}
		
		if(hp < 0)
		{
			this.hp = 0;
		}
	}

	public int getBloodLoss() {
		return bloodLoss;
	}

	public void setBloodLoss(int bloodLoss) {
		this.bloodLoss = bloodLoss;
		
		if(bloodLoss >= 100)
		{
			this.bloodLoss = 100;
			hp = 0;
			state = CitizenState.DECEASED;
		}
		
		if(bloodLoss < 0)
		{
			this.bloodLoss = 0;
		}
		
		
	}
	

	public int getToxicity() {
		return toxicity;
	}

	public void setToxicity(int toxicity) {
		this.toxicity = toxicity;
	}

	public Address getLocation() {
		return location;
	}

	public void setLocation(Address location) {
		this.location = location;
	}

	public Disaster getDisaster() {
		return disaster;
	}

	public String getNationalID() {
		return nationalID;
	}
	
	public void setEmergencyService(SOSListener emergencyService)
	{
		this.emergencyService = emergencyService;
	}
	
	public void setWorldListener(WorldListener worldListener)
	{
		this.worldListener =  worldListener;
	}
	
	public WorldListener getWorldListener()
	{
		return this.worldListener;
	}

}
