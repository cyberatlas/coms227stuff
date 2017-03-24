
package ui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import hw3.FlowGame;
import hw3.Util;

/**
 * Panel for launching a file dialog and starting a new instance
 * of the Flow Free game.
 * @author smkautz
 */
public class ChooseButtonPanel extends JPanel
{
  /**
   * Suppress compiler warning.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Button to initiate file selection.
   */
  private JButton button;
  
  /**
   * Panel for the associated game.  This reference is needed in order
   * to resize the panel when a new game is selected.
   */
  private GamePanel gamePanel;
  
  /**
   * Score panel for the associated game.  This reference is needed in order
   * to resize the panel when a new game is selected.
   */
  private ScorePanel scorePanel;

  /**
   * Constructs the file selection button panel.
   * @param gamePanel
   * @param scorePanel
   */
  public ChooseButtonPanel(GamePanel gamePanel, ScorePanel scorePanel)
  {
    this.gamePanel = gamePanel;
    this.scorePanel = scorePanel;
    button = new JButton("Choose from file");
    this.add(button);
    button.addActionListener(new ChooseButtonHandler());
  }

  private class ChooseButtonHandler implements ActionListener
  {
    @Override
    public void actionPerformed(ActionEvent event)
    {
      gamePanel.pauseTimer();

      // open a file dialog
      JFileChooser chooser = new JFileChooser(System.getProperty("user.home"));
      int result = chooser.showOpenDialog(null);
      String msg = null;
      ArrayList<FlowGame> games = null;
      FlowGame game = null;
      
      if (result == JFileChooser.APPROVE_OPTION)
      {
        File f = chooser.getSelectedFile();
        try
        {
          // read the file
          games = Util.readFile(f.getAbsolutePath());
          if (games.size() > 0)
          {
            // there was at least one valid descriptor, create a 
            // dialog for selecting which game to play
            ArrayList<String> gameNames = new ArrayList<String>();
            int count = 0;
            for (FlowGame g : games)
            {
              String name = count + ": " + g.getWidth() + " x " + g.getHeight();
              gameNames.add(name);
              count += 1;
            }
            Object[] possibilities = gameNames.toArray(new String[0]);
            String s = (String)JOptionPane.showInputDialog(
                null, 
                "Choose your game",
                "Choose game", 
                JOptionPane.PLAIN_MESSAGE, 
                null, // icon
                possibilities, 
                gameNames.get(0));

            // First part of string should be index of game
            if ((s != null) && (s.length() > 0))
            {
              //System.out.println("Selected: " + s);
              int index = Integer.parseInt(s.substring(0, s.indexOf(":")));
              game = games.get(index);
            }

            if (game != null)
            {
              // new game, reset the game panel
              gamePanel.reset(game);
              
              // ...and resize everything
              int width = game.getWidth();
              int height = game.getHeight();
              Dimension d = new Dimension(width * GameMain.SIZE, height * GameMain.SIZE);
              gamePanel.setPreferredSize(d);
              d = new Dimension(width * GameMain.SIZE, 3 * GameMain.SIZE);
              scorePanel.setPreferredSize(d);
              d = new Dimension(width * GameMain.SIZE, GameMain.SIZE);
              ChooseButtonPanel.this.setPreferredSize(d);

              // need to reset preferred size of main panel too
              JPanel mainPanel = (JPanel) ChooseButtonPanel.this.getParent();
              int newWindowHeight = gamePanel.getPreferredSize().height +
                  scorePanel.getPreferredSize().height +
                  ChooseButtonPanel.this.getPreferredSize().height;
              int newWindowWidth = gamePanel.getPreferredSize().width;
              mainPanel.setPreferredSize(new Dimension(newWindowWidth, newWindowHeight));

              // now we can resize the window with the pack() method
              JFrame frame = (JFrame) SwingUtilities.getRoot(ChooseButtonPanel.this);
              frame.pack();
              frame.setVisible(true);
              gamePanel.reset(game);
            }
            else
            {
              msg = "No valid descriptors in file";
            }
          }
          else
          {
            msg = "Unable to create game from selection";
          }
        }
        catch (FileNotFoundException ex)
        {
          msg = ex.toString();
        }
        catch (Exception e)
        {
          msg = "Unexpected error: " + e.toString();
        }
      }
      else
      {
        msg = "No file selected";
      }

      if (game == null && msg != null)
      {
        JOptionPane.showMessageDialog(null, msg);
        
        // resume default game
        gamePanel.resumeTimer();
      }

    }
  }
}
