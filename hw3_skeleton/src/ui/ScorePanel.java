package ui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

/**
 * Panel for displaying the score in a simple video game.
 * @author smkautz
 */
public class ScorePanel extends JPanel
{  
  /**
   * Suppress compiler warning.
   */
  private static final long serialVersionUID = 1L;
  
  /**
   * Format string for displaying score.
   */
  private static final String SCORE_FORMAT = "Time: %1d";
  
  /**
   * Format string for displaying final score.
   */
  private static final String DONE_FORMAT = "Solved in: %1d";

  /**
   * Score to be displayed.
   */
  private int score;

  /**
   * Flag indicating that the game is over.
   */
  private boolean done;
  
  /**
   * Sets the score to be displayed in this panel.
   * @param newScore
   *   score to be displayed
   */
  public void updateScore(int newScore)
  {
    this.score = newScore;
    repaint();
  }

  /**
   * Resets the score to zero and clears the 'done' flag.
   */
  public void reset()
  {
    done = false;
    score = 0;
    repaint();
  }
  
  /**
   * Sets the 'done' flag.
   */
  public void gameOver()
  {
    done = true;
    repaint();
  }
  
  @Override
  public void paintComponent(Graphics g)
  {
    Dimension d = getPreferredSize();
    ((Graphics2D) g).setBackground(Color.WHITE);
    //g.clearRect(0, 0, d.width, d.height);
    g.clearRect(0, 0, getWidth(), getHeight());
    Font font = new Font(Font.SANS_SERIF, Font.PLAIN, GameMain.SCORE_FONT);
    g.setFont(font);
    FontMetrics metrics = g.getFontMetrics(font);

    String text;
    if (!done)
    {
      text = String.format(SCORE_FORMAT, score);
    }
    else
    {
      text = String.format(DONE_FORMAT, score);
    }
    int width = metrics.stringWidth(text);
    int x = (d.width - width) / 2;
    int y = (d.height) / 2; 

    g.drawString(text, x, y);
  }
}
