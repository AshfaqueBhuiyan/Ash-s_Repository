package lab2;

import java.util.Random;

public class RabbitModel3
{
  private int population;
  private Random rand;

  public RabbitModel3()
  {
    population = 0;
    rand = new Random();
  }  

  public int getPopulation()
  {
    return population;
  }
  
  public void simulateYear()
  {
    population += rand.nextInt(10); // Random number between 0 and 9
  }
  
  public void reset()
  {
    population = 0;
  }
}
