package lab7;

public class ArrayMax {
  
  public static void main(String[] args) {
    int[] test = {1, 3, 5, 7, 9, 2, 4, 6, 8, 0};
    int result = findMax(test, 0, test.length - 1);
    System.out.println("The maximum value is: " + result);
  }

  /**
   * Finds the maximum value in an array using divide-and-conquer strategy.
   */
  public static int findMax(int[] arr, int start, int end) {
    if (start == end) {
      return arr[start];
    } else {
      int mid = (start + end) / 2;
      int maxLeft = findMax(arr, start, mid);
      int maxRight = findMax(arr, mid + 1, end);
      return Math.max(maxLeft, maxRight);
    }
  }
}
