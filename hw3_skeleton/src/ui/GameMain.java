package ui;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import api.Cell;
import api.Flow;
import hw3.FlowGame;



/**
 * Main class for a GUI for a Flow Free game sets up a 
 * GamePanel instance in a frame.
 * @author smkautz
 */
public class GameMain
{
  /**
   * Cell size in pixels.
   */
  public static final int SIZE = 40; 

  /**
   * Dot size in pixels, must be less than or equal to SIZE.
   */
  public static final int DOT_SIZE = 20; 

  /**
   * Line width in pixels.
   */
  public static final int LINE_SIZE = 6;
  
  /**
   * Font size for displaying score.
   */
  public static final int SCORE_FONT = 24; 

  /**
   * Background color.
   */
  public static final Color BACKGROUND_COLOR = Color.BLACK;

  /**
   * Color for cell outlines.
   */
  public static final Color GRID_COLOR = Color.DARK_GRAY;
  
  /**
   * Helper method for instantiating the components.  This
   * method should be executed in the context of the Swing
   * event thread only.
   */
  private static void create()
  {
    // sample descriptors
//    String[] testgrid = {
//      "GR-R",
//      "--GB",
//      "B---"
//    };
//    
//    String[] testgrid2 = {
//      "------",
//      "-OR-G-",
//      "BG-OR-",
//      "------",
//      "B-----",
//    };

    // EDIT HERE TO CHANGE THE GAME BEING CREATED
    FlowGame game;

    // a simple game
    Flow[] flows = new Flow[3];
    flows[0] = new Flow(new Cell(0, 0, 'G'), new Cell(1, 2, 'G'));
    flows[1] = new Flow(new Cell(0, 1, 'R'), new Cell(0, 3, 'R'));
    flows[2] = new Flow(new Cell(2, 0, 'B'), new Cell(1, 3, 'B'));
    game = new FlowGame(flows, 4, 3);
    
//      Flow[] flows = new Flow[4];
//      flows[0] = new Flow(new Cell(1, 1, 'O'), new Cell(2, 3, 'O'));
//      flows[1] = new Flow(new Cell(1, 2, 'R'), new Cell(2, 4, 'R'));
//      flows[2] = new Flow(new Cell(1, 4, 'G'), new Cell(2, 1, 'G'));
//      flows[3] = new Flow(new Cell(2, 0, 'B'), new Cell(4, 0, 'B'));
//      game = new FlowGame(flows, 6, 5);

    // calls the constructor based on string descriptors
//    game = new FlowGame(testgrid);
//    game = new FlowGame(testgrid2);

      // create the three UI panels
      ScorePanel scorePanel = new ScorePanel();
      GamePanel panel = new GamePanel(game, scorePanel);
      ChooseButtonPanel choosePanel = new ChooseButtonPanel(panel, scorePanel);
      
      // arrange the panels vertically
      JPanel mainPanel = new JPanel();
      mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
      mainPanel.add(choosePanel);
      mainPanel.add(scorePanel);
      mainPanel.add(panel);

      // put main panel in a window
      JFrame frame = new JFrame("Com S 227 Flow Game");
      frame.getContentPane().add(mainPanel);

      // give panels a nonzero size
      Dimension d = new Dimension(game.getWidth() * GameMain.SIZE, game.getHeight() * GameMain.SIZE);   
      panel.setPreferredSize(d);
      d = new Dimension(game.getWidth() * GameMain.SIZE, 3 * GameMain.SIZE);   
      scorePanel.setPreferredSize(d);
      d = new Dimension(game.getWidth() * GameMain.SIZE, GameMain.SIZE);   
      choosePanel.setPreferredSize(d);
      frame.pack();

      // we want to shut down the application if the 
      // "close" button is pressed on the frame
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      // rock and roll...
      frame.setVisible(true);
  }
  
  /**
   * Entry point.  Main thread passed control immediately
   * to the Swing event thread.
   * @param args not used
   */
  public static void main(String[] args)
  {
    Runnable r = new Runnable()
    {
      public void run()
      {
        create();
      }
    };
    SwingUtilities.invokeLater(r);
  }
}
