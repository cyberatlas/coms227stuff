package api;

import java.awt.Color;

/**
 * Immutable object representing a row and column in a grid,
 * along with an associated color.
 */
public class Cell
{ 
  /**
   * Additional constant for a dark red color.
   */
  public static final Color SCARLET = new Color(122, 35, 48);
  
  /**
   * Additional constant for a purple color.
   */
  public static final Color VIOLET = new Color(153, 51, 255);
  
  /**
   * Additional constant for a dark green color.
   */
  public static final Color FOREST = new Color(0, 153, 51);
  
  /**
   * Row for this cell.
   */
  private final int row;
  
  /**
   * Column for this cell.
   */
  private final int col;
  
  /**
   * Color for this cell.
   */
  private final Color color;
  
  /**
   * Constructs a Cell from a position and color.
   * @param row
   *   the row for this Cell
   * @param col
   *   the column for this Cell
   * @param color
   *   the color for this cell
   */
  public Cell(int row, int col, Color color)
  {
    this.row = row;
    this.col = col;
    this.color = color;
  }
  
  /**
   * Convenience constructor creates a Cell from a position
   * and a character representing a color.  Allowable characters 
   * are R, G, B, C, Y, M, O, P, S, V, F (Red, Green, Blue, Cyan, Yellow, Magenta,
   * Orange, Pink, Scarlet, Violet, Forest). For any other character, this cell's
   * color will default to <code>java.awt.Color.WHITE</code>.
   * @param row
   *   the row for this Cell
   * @param col
   *   the column for this Cell
   * @param ch
   *   character representing the color for this cell
   */
  public Cell(int row, int col, char ch)
  {
    this(row, col, getColor(ch));
  }
  
  /**
   * Returns the row.
   * @return
   *   row for this cell
   */
  public int getRow()
  {
    return row;
  }
  
  /**
   * Returns the column.
   * @return
   *   column for this cell
   */
  public int getCol()
  {
    return col;
  }
  
  /**
   * Returns the color.
   * @return
   *   color for this cell
   */
  public Color getColor()
  {
    return color;
  }
  
  /**
   * Returns true if the given row and column are the same
   * as this cell's row and column.
   * @param row
   *   a row number
   * @param col
   *   a column number
   * @return
   *   true if the given row and column are the same
   * as this cell's row and column, false otherwise
   */
  public boolean positionMatches(int row, int col)
  {
    return this.row == row && this.col == col;
  }

  /**
   * Returns true if the given color is the same as this cell's color.
   * @param c
   *   given color
   * @return
   *   true if the given color is the same as this cell's color, false 
   *   otherwise
   */
  public boolean colorMatches(Color c)
  {
    return this.color.equals(c);
  }
  
  /**
   * Determines whether the given object is equal to this one.
   * @param obj
   *   any object
   * @return
   *   true if the given object is a Cell with the same row, column
   * and color as this Cell.
   */
  @Override
  public boolean equals(Object obj)
  {
    if (obj.getClass() != Cell.class) return false;
    Cell other = (Cell) obj;
    return this.row == other.row && this.col == other.col && this.color.equals(other.color);
  }

  /**
   * Returns a string representation of this Cell.
   * @return
   *   a string representation of this Cell
   */
  @Override
  public String toString()
  {
    return "(" + row + ", " + col + ", " + getColorChar(color) + ")";
  }
  
  /**
   * Returns a Color determined by the given character.  Allowable characters 
   * are R, G, B, C, Y, M, O, P, S, V, F (Red, Green, Blue, Cyan, Yellow, Magenta,
   * Orange, Pink, Scarlet, Violet, Forest). For any other character, this method
   * returns <code>java.awt.Color.WHITE</code>.
   * @param ch
   *   any character
   * @return
   *   Color object determined by the given character.
   */
  public static Color getColor(char ch)
  {
    switch (ch)
    {
      case 'R': return Color.RED;
      case 'G': return Color.GREEN;
      case 'B': return Color.BLUE;
      case 'C': return Color.CYAN;
      case 'Y': return Color.YELLOW;
      case 'M': return Color.MAGENTA;
      case 'O': return Color.ORANGE;
      case 'P': return Color.PINK;
      case 'S': return SCARLET;
      case 'V': return VIOLET;
      case 'F': return FOREST;
    }
    return Color.WHITE;
  }
  
  /**
   * Returns a character abbreviation for the given color.
   * @param color
   *   a Color object
   * @return
   *   one-letter abbreviation for the given color, or 'X' if the
   *   color is not recognized
   */
  public static char getColorChar(Color color)
  {
    if (color.equals(Color.RED))
    {
      return 'R';
    }
    if (color.equals(Color.GREEN))
    {
      return 'G';
    }
    if (color.equals(Color.BLUE))
    {
      return 'B';
    }
    if (color.equals(Color.CYAN))
    {
      return 'C';
    }
    if (color.equals(Color.YELLOW))
    {
      return 'Y';
    }
    if (color.equals(Color.MAGENTA))
    {
      return 'M';
    }
    if (color.equals(Color.ORANGE))
    {
      return 'O';
    }
    if (color.equals(Color.PINK))
    {
      return 'P';
    }
    if (color.equals(SCARLET))
    {
      return 'S';
    }
    if (color.equals(VIOLET))
    {
      return 'V';
    }
    if (color.equals(FOREST))
    {
      return 'F';
    }
    
    return 'X';
  }
}
