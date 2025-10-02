package lab2;

public class RabbitModelTest {
	
	public static void main(String[] args)
	  {
	    RabbitModel model = new RabbitModel();

	    // Check that the initial population is 2
	    System.out.println("Initial Population: " + model.getPopulation());
	    System.out.println("Expected 2");

	    // Simulate the passing of one year
	    model.simulateYear();
	    System.out.println("Population after 1 year: " + model.getPopulation());
	    System.out.println("Expected 3");

	    // Simulate another year
	    model.simulateYear();
	    System.out.println("Population after 2 years: " + model.getPopulation());
	    System.out.println("Expected 4");

	    // Reset the model
	    model.reset();
	    System.out.println("Population after reset: " + model.getPopulation());
	    System.out.println("Expected 2");
	  }
	}