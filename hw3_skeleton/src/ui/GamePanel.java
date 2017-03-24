package ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

import api.Cell;
import api.Flow;
import hw3.FlowGame;

/**
 * Main panel for the user interface a Flow Free game.
 * @author smkautz
 */
public class GamePanel extends JPanel
{ 
  /**
   * Suppress compiler warning.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Score panel associated with the game.
   */
  private ScorePanel scorePanel;

  /**
   * The javax.swing.Timer instance used to animate the UI.
   */
  private Timer timer;

  /**
   * The IGame instance for which this is the UI.
   */
  private FlowGame game;
  
  /**
   * System time when the game begins.
   */
  private long startTime;
  
  /**
   * Constructs a GamePanel with the given game associated ScorePanel.
   * @param game 
   *   the Game instance for which this is the UI
   * @param scorePanel
   *   panel for displaying scores associated with the game
   */
  public GamePanel(FlowGame game, ScorePanel scorePanel)
  {
    this.game = game;
    this.scorePanel = scorePanel;
    addMouseListener(new MyMouseListener());
    addMouseMotionListener(new MyMouseMotionListener());
    startTime = System.currentTimeMillis();
    timer = new Timer(1000 , new TimerCallback()); // once per second
    timer.start();
  }
  
  /**
   * Temporarily stop the timer.
   */
  public void pauseTimer()
  {
    timer.stop();
  }
  
  /**
   * Restart the timer.
   */
  public void resumeTimer()
  {
    startTime = System.currentTimeMillis();
    timer.start();
  }

  /**
   * Start over with a new game.
   * @param game
   */
  public void reset(FlowGame game)
  {
    this.game = game;
    scorePanel.reset();
    startTime = System.currentTimeMillis();
    timer.start();
  }
  
  // The paintComponent method is invoked by the Swing framework whenever
  // the panel needs to be rendered on the screen.  In this application,
  // repainting is normally triggered by the calls to the repaint() 
  // method in the timer callback and the mouse handlers

  @Override
  public void paintComponent(Graphics g)
  {
    // clear background
    g.setColor(GameMain.BACKGROUND_COLOR);
    g.fillRect(0, 0, getWidth(), getHeight());
    
    // first draw all the cell outlines once
    g.setColor(GameMain.GRID_COLOR);
    for (int row = 0; row < game.getHeight(); ++row)
    {
      for (int col = 0; col < game.getWidth(); ++col)
      {
        int x = GameMain.SIZE * col;
        int y = GameMain.SIZE * row;
        g.drawRect(x, y, GameMain.SIZE - 1, GameMain.SIZE - 1);
      }
    }     
      
    // paint flows
    Flow[] flows = game.getAllFlows();
    for (Flow f : flows)
    {
      Color color = f.getEndpoint(0).getColor();
      ArrayList<Cell> cells = f.getCells();
      
      // background colors
      if (cells.size() >= 2)
      {
        for (int i = 0; i < cells.size() - 1; ++i)
        {
          Cell src = cells.get(i);
          Cell dst = cells.get(i + 1);
          paintOneCell(g, src.getRow(), src.getCol(), color);
          paintOneCell(g, dst.getRow(), dst.getCol(), color);
        }       
      }
      else
      {
        for (int i = 0; i < 2; ++i)
        {
          Cell e = f.getEndpoint(i);
          paintOneCell(g, e.getRow(), e.getCol(), color);
        }          
      }      
    }
    
    for (Flow f : flows)
    {
      Color color = f.getEndpoint(0).getColor();
      ArrayList<Cell> cells = f.getCells();
      if (cells.size() >= 2)
      {
        for (int i = 0; i < cells.size() - 1; ++i)
        {
          Cell src = cells.get(i);
          Cell dst = cells.get(i + 1);
          makeLine(g, src.getCol(), src.getRow(), dst.getCol(), dst.getRow(), color);
        }       
      }
      for (int i = 0; i < 2; ++i)
      {
        Cell e = f.getEndpoint(i);
        paintOneCircle(g, e.getRow(), e.getCol(), color);
      }     
    }

        
  }
  
  /**
   * Draws line from center of first cell to center of second
   */
  private void makeLine(Graphics g, int col1, int row1, int col2, int row2, Color color)
  {
    int s = GameMain.SIZE;
    int x1 = col1 * s + s / 2;
    int y1 = row1 * s + s / 2;
    int x2 = col2 * s + s / 2;
    int y2 = row2 * s + s / 2;
    g.setColor(color);
    ((Graphics2D) g).setStroke(new BasicStroke(GameMain.LINE_SIZE));
    g.drawLine(x1, y1, x2, y2);
  }
  
  /**
   * Renders an endpoint.
   * 
   * @param g the Swing graphics context
   * @param row y-coordinate of the cell to render
   * @param col x-coordinate of the cell to render
   * @param t the icon to render, normally used
   *   to determine the color with which to render the cell
   */
  private void paintOneCircle(Graphics g, int row, int col, Color color)
  {
    // scale everything up by the SIZE
    int x = GameMain.SIZE * col;
    int y = GameMain.SIZE * row;
    int offset = (GameMain.SIZE - GameMain.DOT_SIZE) / 2;
    g.setColor(color);
    g.fillOval(x + offset, y + offset, GameMain.DOT_SIZE, GameMain.DOT_SIZE);
  }

  /**
   * Renders background of a single cell of the grid.
   * 
   * @param g the Swing graphics context
   * @param row y-coordinate of the cell to render
   * @param col x-coordinate of the cell to render
   * @param color the color to render
   */
  private void paintOneCell(Graphics g, int row, int col, Color color)
  {
    // scale everything up by the SIZE
    int x = GameMain.SIZE * col;
    int y = GameMain.SIZE * row;
    Color mutedColor = color.darker().darker();
    g.setColor(mutedColor);
    g.fillRect(x, y, GameMain.SIZE, GameMain.SIZE);
    g.setColor(GameMain.GRID_COLOR);
    g.drawRect(x, y, GameMain.SIZE - 1, GameMain.SIZE - 1);  
  }

  /**
   * Callback for timer events.  The actionPerformed method
   * is invoked each time the timer fires and the call to
   * repaint() at the bottom of the method causes the panel
   * to be redrawn.
   */
  private class TimerCallback implements ActionListener
  {
    @Override
    public void actionPerformed(ActionEvent arg0)
    {
      long millis = System.currentTimeMillis() - startTime;
      int seconds = (int) millis / 1000;
      scorePanel.updateScore(seconds);
      repaint();
    }
  }
  
  /**
   * Callback for mouse events.
   */
  private class MyMouseListener implements MouseListener
  {

    @Override
    public void mouseClicked(MouseEvent event)
    {
    }

    @Override
    public void mousePressed(MouseEvent event)
    {
      if (!game.isComplete())
      {     
        int row = event.getY() / GameMain.SIZE;
        int col = event.getX() / GameMain.SIZE;
        game.startFlow(row, col);
      }
      repaint();
    }

    @Override
    public void mouseReleased(MouseEvent event)
    {
      game.endFlow();    
      repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e)
    {
    }

    @Override
    public void mouseExited(MouseEvent e)
    {
    }
  }
  
  /**
   * Callback for mouse motion events.
   */
  private class MyMouseMotionListener implements MouseMotionListener
  {

    @Override
    public void mouseDragged(MouseEvent e)
    {
      if (!game.isComplete()) 
      {
        int row = e.getY() / GameMain.SIZE;
        int col = e.getX() / GameMain.SIZE;


        if (row >= 0 && col >= 0 && row < game.getHeight() && col < game.getWidth())
        {
          game.addCell(row, col);
        }
        
        if (game.isComplete())
        {
          timer.stop();
          scorePanel.gameOver();
        }

      }
      repaint();

    }

    @Override
    public void mouseMoved(MouseEvent e)
    {
    }
    
  }
  

}
