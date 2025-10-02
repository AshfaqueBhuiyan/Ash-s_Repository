package lab2;

public class RabbitModel2
{
  private int population;

  public RabbitModel2()
  {
    population = 500;
  }  

  public int getPopulation()
  {
    return population;
  }
  
  public void simulateYear()
  {
    population /= 2;
  }
  
  public void reset()
  {
    population = 500;
  }
}
