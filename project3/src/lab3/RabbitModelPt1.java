package lab3;

/**
 * A RabbitModel is used to simulate the growth
 * of a population of rabbits. 
 */
public class RabbitModelPt1
{
	private int currentPop;
  // TODO - add instance variables as needed

  /**
   * Constructs a new RabbitModel.
   */

  public RabbitModelPt1()
  {
   currentPop = 0;
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
	currentPop = ((currentPop +1)%5);
	
  }
  
  /**
   * Sets or resets the state of the model to the 
   * initial conditions.
   */
  public void reset()
  {
    // TODO
	currentPop = 0;
  }
}