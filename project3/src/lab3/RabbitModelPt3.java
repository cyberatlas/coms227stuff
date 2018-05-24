package lab3;

import java.util.Random;

/**
 * A RabbitModel is used to simulate the growth
 * of a population of rabbits. 
 */
public class RabbitModelPt3
{
	private int currentPop;
  // TODO - add instance variables as needed
	public Random rand = new Random();
  /**
   * Constructs a new RabbitModel.
   */

  public RabbitModelPt3()
  {
   currentPop = 500;
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
	
	currentPop = (currentPop +rand.nextInt(11));
	
  }
  
  /**
   * Sets or resets the state of the model to the 
   * initial conditions.
   */
  public void reset()
  {
    // TODO
	currentPop = 500;
  }
}