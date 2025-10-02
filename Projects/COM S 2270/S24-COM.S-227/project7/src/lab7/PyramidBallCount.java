package lab7;

public class PyramidBallCount {
  
  public static void main(String[] args) {
    int levels = 7;
    int totalBalls = getPyramidCount(levels);
    System.out.println("Total balls in a pyramid of " + levels + " levels: " + totalBalls);
  }

  /**
   * Calculates the total number of balls in a pyramid given the number of levels.
   */
  public static int getPyramidCount(int n) {
    if (n == 1) {
      // Base case: If there is only one level, it has only one ball
      return 1;
    } else {
      // Recursive case: Sum of balls in the current level and all levels above it
      return n * n + getPyramidCount(n - 1);
    }
  }
}
