package examples;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import api.ITransform;
import mini2.ConwayTransform;
import mini2.GridUtil;

/**
 * Basic animation screen for diplaying a grid based on
 * a 2d array of 0's and 1's.  The grid is updated every
 * INTERVAL milliseconds by calling the GridUtil.applyAll
 * method on the supplied transform.
 * 
 * You would normally use this class by running a main
 * class such as LifeAnimatorMain.
 */
public class LifeAnimator  extends JPanel
{
  private static final int INTERVAL = 500; // half second delay between updates
  private static final Color COLOR = Color.BLUE;
  private static final Color BACKGROUND = Color.LIGHT_GRAY;
  private static final int INITIAL_SIZE = 600;
  
 
  private int[][] data;
  private ITransform transform;
  private int scale = 1;
  private int gridSize = 1;
  private Timer timer;
  private int width;
  private int height;
  private int canvasWidth;
  private int canvasHeight;
  private boolean running;
  
  JComponent theCanvas;
  JScrollPane pane;
  JPanel buttonPanel;
  private JTextField scaleField;
  private JTextField gridSizeField;
  private JButton startStopButton;
  
  private class MyCanvas extends JComponent
  {
    // here is where the stuff all gets painted
    public void paintComponent(Graphics graphics)
    {
      Graphics2D g = (Graphics2D) graphics; 
      // main display stuff
      display(g, true);  
    }

  }
  
  public LifeAnimator(int[][] givenData, ITransform givenTransform)
  {
    data = givenData;
    transform = givenTransform;
    width = givenData[0].length;
    height = givenData.length;
    int scaleW = INITIAL_SIZE / width;
    int scaleH = INITIAL_SIZE / height;
    scale = Math.max(1, Math.min(scaleW, scaleH));  
    canvasWidth = Math.min(width * scale, INITIAL_SIZE);
    canvasHeight = Math.min(height * scale, INITIAL_SIZE);
    
    theCanvas = new MyCanvas();
    buttonPanel = new JPanel();
    
    JLabel label = new JLabel("Scale:");
    buttonPanel.add(label);
    scaleField = new JTextField(""+scale, 10);
    scaleField.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent arg0)
      {
        String scaleText = scaleField.getText();
        try
        {
          scale = Integer.parseInt(scaleText);
        }
        catch (NumberFormatException e)
        {
          System.out.println(e + ": " + scaleText);
        }
        canvasWidth = width * scale;
        canvasHeight = height * scale;
        //System.out.println("Setting canvas size " + canvasWidth);
        theCanvas.setPreferredSize(new Dimension(canvasWidth, canvasHeight));

        repaint();
      }
    });
    buttonPanel.add(scaleField);   
        
    JLabel label3 = new JLabel("Grid :");
    buttonPanel.add(label3);
    gridSizeField = new JTextField(""+gridSize, 10);
    gridSizeField.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent arg0)
      {
        try
        {
          gridSize = Integer.parseInt(gridSizeField.getText());
        }
        catch (NumberFormatException e)
        {
          System.out.println(e + ": " + gridSizeField.getText());
        }
        repaint();
      }
    });
    buttonPanel.add(gridSizeField);   
    
    startStopButton = new JButton("Start");
    startStopButton.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent arg0)
      {
        if (running)
        {
          running = false;
          timer.stop();
          startStopButton.setText("Start");
        }
        else
        {
          running = true;
          timer.restart();
          startStopButton.setText("Stop");          
        }
      }     
    });
    buttonPanel.add(startStopButton);
    
    setLayout(new BorderLayout());
    
    theCanvas.setPreferredSize(new Dimension(canvasWidth, canvasHeight));
    pane = new JScrollPane(theCanvas);
    //pane.getViewport().setViewPosition(new java.awt.Point(0, canvasHeight));

    add(pane, BorderLayout.CENTER);
    add(buttonPanel, BorderLayout.SOUTH);
    
    timer = new Timer(INTERVAL, new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        data = GridUtil.applyAll(data, new ConwayTransform());
        repaint();
      }
    });
  }
  
  
  
  public void display(Graphics2D g, boolean useScale)
  {
    display(g, useScale, 0, Color.BLACK);
  }

  // main drawing stuff called from paintComponent
  public void display(Graphics2D g, boolean useScale, int border, Color borderColor)
  {
    g.setColor(BACKGROUND);
    g.fillRect(0, 0, canvasWidth, canvasHeight);
    
    int y = 0;
    for (int i = 0; i < height; ++i)
    {
      //System.out.println(y + ", " + (canvasHeight - y - scale - border));
      int x = 0;
      for (int j = 0; j < width; ++j)
      {
        Color c = null;
        int index = data[i][j];
        if (index > 0)
        {
          c = COLOR;
        }

        if (c != null)
        {
          g.setColor(c);

          if (scale > 1)
          {
//            g.drawRect(x + border, canvasHeight - y - scale - border, scale - 1, scale - 1);
//            g.fillRect(x + border, canvasHeight - y - scale - border, scale, scale);
            g.drawRect(x + border, y + border, scale - 1, scale - 1);
            g.fillRect(x + border, y + border, scale, scale);

          }
          else
          {
//            g.drawRect(x + border, canvasHeight - y - scale + border, scale, scale);
            g.drawRect(x + border, y + border, scale, scale);

          }
        }

        x += scale;
      }
      y += scale;
    }
    
    // draw grids using rectangles, kind of lame
    if (gridSize > 0 && scale > 1)
    {
      Color c = Color.yellow;         
      g.setColor(c);
      y = 0;
      for (int i = 0; i < height; ++i)
      {
        int x = 0;
        for (int j = 0; j < width; ++j)
        {
//          g.drawRect(x, canvasHeight - y - gridSize * scale, gridSize * scale, gridSize * scale);
          g.drawRect(x, y, gridSize * scale, gridSize * scale);

          x += gridSize * scale;
        }
        y += gridSize * scale;
        
      }      
    }
    
    // tell scroll pane that canvas is resized
    theCanvas.revalidate();

  }
  

}
