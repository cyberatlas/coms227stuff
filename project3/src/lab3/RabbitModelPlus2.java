package lab3;

/**
 * A RabbitModel is used to simulate the growth
 * of a population of rabbits. 
 */
public class RabbitModelPlus2
{
	private int currentPop;
  // TODO - add instance variables as needed

  /**
   * Constructs a new RabbitModel.
   */

  public RabbitModelPlus2()
  {
   currentPop = 2;
  }  
 
  /**
   * Returns the current number of rabbits.
   * @return
   *   current rabbit population
   */
  public int getPopulation()
  {
    // TODO - returns a dummy value so code will compile
    return currentPop;
  }
  
  /**
   * Updates the population to simulate the
   * passing of one year.
   */
  public void simulateYear()
  {
    // TODO
	currentPop = currentPop +1;
	
  }
  
  /**
   * Sets or resets the state of the model to the 
   * initial conditions.
   */
  public void reset()
  {
    // TODO
	currentPop = 2;
  }
}