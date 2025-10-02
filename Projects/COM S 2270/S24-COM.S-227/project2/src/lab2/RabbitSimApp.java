package lab2;

import plotter.SimulationPlotter;

public class RabbitSimApp 
{
    public static void main(String[] args) 
    {
        /**
         *  Create an instance of the RabbitModels
         */
        RabbitModel myModel = new RabbitModel();
        
        /**
         *  Create an instance of the SimulationPlotter
         */
        SimulationPlotter plotter = new SimulationPlotter();

        /**
         *  Start the simulation with your model
         */
        plotter.simulate(myModel);
        
    }
}