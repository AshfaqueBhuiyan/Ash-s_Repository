package lab2;

/**
 * The Fibonacci Sequence.
 */
public class RabbitModel4
{
  private int lastYear;
  private int yearBefore;
  private int population;

  public RabbitModel4()
  {
    lastYear = 1;
    yearBefore = 0;
    population = 1;
  }  

  public int getPopulation()
  {
    return population;
  }
  
  public void simulateYear()
  {
    population = lastYear + yearBefore;
    yearBefore = lastYear;
    lastYear = population;
  }
  
  public void reset()
  {
    lastYear = 1;
    yearBefore = 0;
    population = 1;
  }
}
