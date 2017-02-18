package example;

/**
 * A Team is a simple data container for data representing
 * the relative skill levels of a team.  A Team is constructed
 * with a total of 100 "skill points" allocated among running,
 * passing, blocking, pass defense, and kicking.
 */
public class Team
{
  /**
   * Running skill points of this team.
   */
  private final int run;
  
  /**
   * Passing skill points of this team.
   */
  private final int pass;
  
  /**
   * Blocking skill points of this team.
   */
  private final int block;
  
  /**
   * Pass defense skill points of this team.
   */
  private final int defense;
  
  /**
   * Kicking skill points of this team.
   */
  private final int kick;
  
  /**
   * The name of this team.
   */
  private final String name;
  
  /**
   * Constructs a team with the given name and default skill points (points allocated
   * equally to all categories).
   * @param givenName
   *   name for this team
   */
  public Team(String givenName)
  {
    this(givenName, 20, 20, 20, 20, 20);
  }
  
  /**
   * Constructs a team with the given name and skill points.  If the given numbers do 
   * not add up to exactly 100, or if any are negative, actual values will be allocated
   * as closely as possible in the order given.
   * 
   * @param givenName
   *   name for this team
   * @param givenRunSkill
   *   points for running skill
   * @param givenPassSkill
   *   points for passing skill
   * @param givenBlockSkill
   *   points for blocking skill
   * @param givenPassDefenseSkill
   *   points for pass defense skill
   * @param givenKickSkill
   *   points for kicking skill
   */
  public Team(String givenName, int givenRunSkill, int givenPassSkill, int givenBlockSkill, int givenPassDefenseSkill, int givenKickSkill)
  {
    name = givenName;
    
    // initialize the values, starting at beginning, keeping values non-negative and the total <= 100
    int available = 100;
    this.run = Math.max(0, Math.min(available, givenRunSkill));
    available -= this.run;
    this.pass = Math.max(0, Math.min(available, givenPassSkill));
    available -= this.pass;
    this.block = Math.max(0, Math.min(available, givenBlockSkill));
    available -= this.block;
    this.defense = Math.max(0, Math.min(available, givenPassDefenseSkill));
    available -= this.defense;
    this.kick = available;  // whatever's left
    
  }
  
  /**
   * Returns this team's skill points for running.
   * @return
   *   running skill points
   */
  public int getRunSkill()
  {
    return run;
  }

  /**
   * Returns this team's skill points for passing.
   * @return
   *   passing skill points
   */
  public int getPassSkill()
  {
    return pass;
  }
  
  /**
   * Returns this team's skill points for blocking.
   * @return
   *   blocking skill points
   */
  public int getBlockSkill()
  {
    return block;
  }
  
  /**
   * Returns this team's skill points for pass defense.
   * @return
   *   pass defense skill points
   */
  public int getPassDefenseSkill()
  {
    return defense;
  }
  
  /**
   * Returns this team's skill points for kicking.
   * @return
   *   kicking skill points
   */
  public int getKickSkill()
  {
    return kick;
  }
  
  /**
   * Returns the name of this team.
   * @return
   *   name of this team
   */
  public String getName()
  {
    return name;
  }
}
