package simulation;

import java.io.BufferedReader;

import java.io.FileReader;
import java.util.ArrayList;

import model.disasters.Disaster;
import model.disasters.Fire;
import model.disasters.GasLeak;
import model.disasters.Infection;
import model.disasters.Injury;
import model.infrastructure.ResidentialBuilding;
import model.people.Citizen;
import model.people.CitizenState;
import model.units.Ambulance;
import model.units.DiseaseControlUnit;
import model.units.Evacuator;
import model.units.FireTruck;
import model.units.GasControlUnit;
import model.units.Unit;
import model.units.UnitState;
import model.events.*;

public class Simulator implements WorldListener {

	private int currentCycle;
	private ArrayList<ResidentialBuilding> buildings;
	private ArrayList<Citizen> citizens;
	private ArrayList<Unit> emergencyUnits;
	
	private ArrayList<Disaster> plannedDisasters;
	private ArrayList<Disaster> executedDisasters;
	private Address[][] world;
	private SOSListener emergencyService;
	private WorldListener worldListener;

	public Simulator(SOSListener emergencyService) throws Exception {
		
		buildings = new ArrayList<ResidentialBuilding>();
		citizens = new ArrayList<Citizen>();
		emergencyUnits = new ArrayList<Unit>();
		plannedDisasters = new ArrayList<Disaster>();
		executedDisasters = new ArrayList<Disaster>();
		worldListener = new WorldListener() {
			
			@Override
			public void assignAddress(Simulatable sim, int x, int y) {
				// TODO Auto-generated method stub
				
			}
		};

		world = new Address[10][10];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				world[i][j] = new Address(i, j);
			}
		}

		loadUnits("units.csv");
		loadBuildings("buildings.csv");
		loadCitizens("citizens.csv");
		loadDisasters("disasters.csv");

		for (int i = 0; i < buildings.size(); i++) {

			ResidentialBuilding building = buildings.get(i);
			for (int j = 0; j < citizens.size(); j++) {

				Citizen citizen = citizens.get(j);
				if (citizen.getLocation() == building.getLocation())
					building.getOccupants().add(citizen);

			}
		}
	}

	private void loadUnits(String path) throws Exception {

		BufferedReader br = new BufferedReader(new FileReader(path));
		String line = br.readLine();

		while (line != null) {

			String[] info = line.split(",");
			String id = info[1];
			int steps = Integer.parseInt(info[2]);

			switch (info[0]) {

			case "AMB":
				emergencyUnits.add(new Ambulance(id, world[0][0], steps , worldListener));
				break;

			case "DCU":
				emergencyUnits.add(new DiseaseControlUnit(id, world[0][0], steps , worldListener));
				break;

			case "EVC":
				emergencyUnits.add(new Evacuator(id, world[0][0], steps,worldListener, Integer.parseInt(info[3])));
				break;

			case "FTK":
				emergencyUnits.add(new FireTruck(id, world[0][0], steps ,worldListener));
				break;

			case "GCU":
				emergencyUnits.add(new GasControlUnit(id, world[0][0], steps , worldListener));
				break;

			}

			line = br.readLine();
		}

		br.close();
	}

	private void loadBuildings(String path) throws Exception {

		BufferedReader br = new BufferedReader(new FileReader(path));
		String line = br.readLine();

		while (line != null) {

			String[] info = line.split(",");
			int x = Integer.parseInt(info[0]);
			int y = Integer.parseInt(info[1]);

			buildings.add(new ResidentialBuilding(world[x][y]));

			line = br.readLine();

		}
		br.close();
	}

	private void loadCitizens(String path) throws Exception {

		BufferedReader br = new BufferedReader(new FileReader(path));
		String line = br.readLine();

		while (line != null) {

			String[] info = line.split(",");
			int x = Integer.parseInt(info[0]);
			int y = Integer.parseInt(info[1]);
			String id = info[2];
			String name = info[3];
			int age = Integer.parseInt(info[4]);

			citizens.add(new Citizen(world[x][y], id, name, age , worldListener));

			line = br.readLine();

		}
		br.close();
	}

	private void loadDisasters(String path) throws Exception {

		BufferedReader br = new BufferedReader(new FileReader(path));
		String line = br.readLine();

		while (line != null) {

			String[] info = line.split(",");
			int startCycle = Integer.parseInt(info[0]);
			ResidentialBuilding building = null;
			Citizen citizen = null;

			if (info.length == 3)
				citizen = getCitizenByID(info[2]);
			else {

				int x = Integer.parseInt(info[2]);
				int y = Integer.parseInt(info[3]);
				building = getBuildingByLocation(world[x][y]);

			}

			switch (info[1]) {

			case "INJ":
				plannedDisasters.add(new Injury(startCycle, citizen));
				break;

			case "INF":
				plannedDisasters.add(new Infection(startCycle, citizen));
				break;

			case "FIR":
				plannedDisasters.add(new Fire(startCycle, building));
				break;

			case "GLK":
				plannedDisasters.add(new GasLeak(startCycle, building));
				break;
			}

			line = br.readLine();
		}
		br.close();
	}

	private Citizen getCitizenByID(String id) {

		for (int i = 0; i < citizens.size(); i++) {
			if (citizens.get(i).getNationalID().equals(id))
				return citizens.get(i);
		}

		return null;
	}

	private ResidentialBuilding getBuildingByLocation(Address location) {

		for (int i = 0; i < buildings.size(); i++) {
			if (buildings.get(i).getLocation() == location)
				return buildings.get(i);
		}
		

		return null;
	}
	
	
	public void assignAddress(Simulatable sim, int x , int y)
	{
		//should implement here...
		
		if (sim instanceof Unit) {
			Unit d = (Unit) sim;
			d.setLocation(world[x][y]);
		}
		
		if (sim instanceof Citizen) {
			Citizen d = (Citizen) sim;
			d.setLocation(world[x][y]);
		}
		
	}
	
	 public boolean checkGameOver()
	 {
		//should implement here...
		 
		 if(plannedDisasters.size() > 0)
		 {
			 return false;
		 }
		 
		/* for(Disaster e : plannedDisasters)
		 {
			 if(e.isActive())
			 {
				 return false;
			 }
		 }*/
		 
		 
		 for(Unit e : emergencyUnits)
		 {
			 if(e.getState() == UnitState.IDLE)
			 {
				 return false;
			 }
		 }
		 
		 
		 for(Citizen e : citizens)
		 {
			 if(e.getState() != CitizenState.DECEASED)
			 {
				 return false;
			 }
		 }
		 
		 
		 for(ResidentialBuilding e : buildings)
		 {
			 if(e.getStructuralIntegrity() > 0)
			 {
				 return false;
			 }
		 }
		 
		 
		 return true;
	 }
	
	 public int calculateCasualties()
	 {
		//should implement here...
		 
		 int casualties = 0;
		 
		 for(Citizen e : citizens)
		 {
			 if(e.getState() == CitizenState.DECEASED)
			 {
				 casualties++;
			 }
		 }
		 
		 return casualties;
	 }
	 
	 public void nextCycle()
	 {
		//should implement here...
		 currentCycle++;
		 
		 
		/* for (Disaster b : plannedDisasters)
		 {
			 b.cycleStep();
		 }
		 
		 for (Unit b : emergencyUnits)
		 {
			b.cycleStep(); 
		 }
		 
		 for (Disaster b : executedDisasters)
		 {
			 b.cycleStep();
		 }
		 
		 for (ResidentialBuilding b : buildings)
		 {
			 b.cycleStep();
		 }
		 
		 for (Citizen b : citizens)
		 {
			 b.cycleStep();
		 }*/
		 
		
	 
	 }
	 
	public ArrayList<Unit> getEmergencyUnits()
	{
		return this.emergencyUnits;
	}
	
	public void setEmergencyService(SOSListener emergencyService)
	{
		this.emergencyService = emergencyService;
	}
	
}
