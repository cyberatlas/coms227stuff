package lab9;

import java.util.ArrayList;

/**
 * A Thing represents something that could either be a birthday present,
 * or it could be a box containing more Thing objects.
 */
public class Thing
{
  /**
   * Contents of this thing, if it's a box.
   */
  private ArrayList<Thing> contents;
  
  /**
   * True if this thing is a present, false if it's a box.
   */
  private boolean isPresent;
  
  /**
   * String description of this thing, if it's a present.
   */
  private String description;
  
  /**
   * Constructs a thing that is a present with the given description.
   * @param givenDescription
   */
  public Thing(String givenDescription)
  {
    description = givenDescription;
    isPresent = true;
    contents = null;
  }
  
  /**
   * Constructs a thing that is an empty box.
   */
  public Thing()
  {
    description = "box!";
    isPresent = false;
    contents = new ArrayList<Thing>();
  }
  
  /**
   * Adds a thing to this thing's contents.  This thing must
   * be a box for this to work.
   * @param t
   */
  public void addThing(Thing t)
  {
    contents.add(t);
  }
  
  /**
   * Returns true if this thing is a present, false if it's a box.
   * @return
   */
  public boolean isPresent()
  {
    return isPresent;
  }
  
  /**
   * Returns a list of the contents of this thing, or null if
   * it's not a box.
   * @return
   */
  public ArrayList<Thing> getContents()
  {
    return contents;
  }
  
  /**
   * Returns the description of this thing.
   * @return
   */
  public String getDescription()
  {
    return description;
  }
}