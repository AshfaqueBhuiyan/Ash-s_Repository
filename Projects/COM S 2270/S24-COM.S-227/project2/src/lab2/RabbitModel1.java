package lab2;

public class RabbitModel1
{
  private int population;
  private int year;

  public RabbitModel1()
  {
    population = 0;
    year = 0;
  }  

  public int getPopulation()
  {
    return population;
  }
  
  public void simulateYear()
  {
    year++;
    if (year % 5 == 0) {
        population = 0;
    } else {
        population++;
    }
  }
  
  public void reset()
  {
    population = 0;
    year = 0;
  }
}
