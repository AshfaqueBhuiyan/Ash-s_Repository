package lab2;

/**
 * Try out the Basketball class.
 */
public class BasketballTest
{
  /**
   * Entry point.
   */
  public static void main(String[] args)
  {
    // Construct an instance and examine its attributes
    Basketball b = new Basketball(4.0);
    System.out.println(b.getDiameter());
    System.out.println(b.isDribbleable());
    
    // Construct another instance with a diameter of 6
    Basketball b2 = new Basketball(6.0);
    
    // Inflate the first one
    b.inflate();
    
    // First one is now dribbleable
    System.out.println("First basketball: " + b.isDribbleable());
    
    // Second one is unchanged
    System.out.println("Second basketball: " + b2.isDribbleable());
    
    // Deflate the first one
    b.deflate();

    // Check the dribbleable status of the first one after deflation
    System.out.println("First basketball after deflation: " + b.isDribbleable());

    // For thorough testing, inflate and then deflate the second basketball
    b2.inflate();
    b2.deflate();

    // Check the dribbleable status of the second one after inflation and deflation
    System.out.println("Second basketball after inflation and deflation: " + b2.isDribbleable());
  }
}
