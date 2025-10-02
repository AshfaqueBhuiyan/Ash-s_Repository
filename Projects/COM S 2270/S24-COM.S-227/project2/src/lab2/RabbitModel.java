package lab2;

/**
 * A RabbitModel is used to simulate the growth
 * of a population of rabbits. 
 */
public class RabbitModel
{
  // Instance variable to hold the current rabbit population
	  private int population;
  
  /**
   * Constructs a new RabbitModel.
   */
  public RabbitModel()
  {
    // Initialize the population to 2 at the start
	    population = 2;
  }  
 
  /**
   * Returns the current number of rabbits.
   * @return
   *   current rabbit population
   */
  public int getPopulation()
  {
    // Return the current population
	    return population;
  }
  
  /**
   * Updates the population to simulate the
   * passing of one year.
   */
  public void simulateYear()
  {
    // Increase the population by 1 each year
	    population += 1;
  }
  
  /**
   * Sets or resets the state of the model to the 
   * initial conditions.
   */
  public void reset()
  {
    // Reset the population to 2
	    population = 2;
  }
}
