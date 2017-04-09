package examples;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import api.ITransform;
import mini2.ConwayTransform;

/**
 * Entry point for starting up an instance of LifeAnimator.
 */
public class LifeAnimatorMain
{
  public static void main(String[] args)
  {
    // create the initial grid
    //final int[][] test = createBlinker5x5();
    //final int[][] test = createGlider(7);
    final int[][] test = createGlider(40);
    
    final ITransform transform = new ConwayTransform();
    
    Runnable r = new Runnable()
    {
      public void run()
      {
        createAndShow(test, transform);
      }
    };
    SwingUtilities.invokeLater(r);
  }
  
  
  
  public static void createAndShow(int[][] data, ITransform transform)
  {
    LifeAnimator t = new LifeAnimator(data, transform);
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().add(t);
    frame.pack();
    frame.setVisible(true);
  }

  
  private static int[][] createBlinker5x5()
  {
    final int[][] test = {
        {0, 0, 0, 0, 0},
        {0, 0, 1, 0, 0},
        {0, 0, 1, 0, 0},
        {0, 0, 1, 0, 0},
        {0, 0, 0, 0, 0},
    };
    return test;
  }
  
  /**
   * Returns a size x size array with a glider in the upper left
   * corner.
   * @param size
   * @return
   */
  private static int[][] createGlider(int size)
  {
    int[][] test2 = {
        {1, 0, 1},
        {0, 1, 1},
        {0, 1, 0},
    };

    int[][] result = new int[size][size];
    
    // copy the "glider" pattern into upper left corner
    for (int row = 0; row < test2.length; row += 1)
    {
      for (int col = 0; col < test2[0].length; col += 1)
      {
        result[row][col] = test2[row][col];
      }
    }
    return result;
  }
}
