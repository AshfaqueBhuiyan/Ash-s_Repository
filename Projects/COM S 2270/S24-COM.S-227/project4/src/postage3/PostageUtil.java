package postage3;

public class PostageUtil
{
  /**
   * Returns the cost of postage for a letter of the given weight.
   * @param weight
   *   given weight in ounces
   * @return
   *   cost of postage for the weight
   */
	public static double computePostage(double weight) {
        double cost = 0.47;
        if (weight > 1) {
            cost += Math.ceil(weight - 1) * 0.21;
            if (weight > 3.5) {
                cost += 0.47; // This adds an additional flat rate for weights over 3.5 ounces.
            }
        }
        return cost;
    }
}
