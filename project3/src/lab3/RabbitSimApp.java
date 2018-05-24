package lab3;

import plotter.SimulationPlotter;

public class RabbitSimApp {

	public static void main(String[] args) {
		SimulationPlotter plotter = new SimulationPlotter();
		RabbitModel myModel = new RabbitModel();
		/*myModel.simulateYear();
		myModel.simulateYear();
		myModel.simulateYear();
		myModel.simulateYear();*/
		//myModel.simulateYear();
		System.out.println(myModel.getPopulation());
	    plotter.simulate(myModel);

	}		
}
