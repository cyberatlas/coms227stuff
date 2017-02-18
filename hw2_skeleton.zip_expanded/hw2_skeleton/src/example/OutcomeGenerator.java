package example;

import java.util.Random;

/**
 * This class is used to generate outcomes of plays for a simulation
 * of a simplified American football game.  An OutcomeGenerator is constructed
 * with two teams and an instance of <code>java.util.Random</code>.  Outcomes
 * are generated using randomness along with the skill points of the two teams.
 * The formulas used are completely ad hoc and have no scientific or statistical basis
 * whatsoever.
 */
public class OutcomeGenerator
{
  /**
   * First team, referred to as "team 0".
   */
  private Team team0;
  
  /**
   * Second team, referred to as "team 1".
   */
  private Team team1;
  
  /**
   * Random number generator to be used for this simulation.
   */
  private Random rand;
  
  /**
   * Constructs an OutcomeGenerator for the given teams, using the 
   * given instance of <code>Random</code>.
   * @param givenTeam0
   *   team 0
   * @param givenTeam1
   *   team 1
   * @param givenRand
   *   random number generator to use
   */
  public OutcomeGenerator(Team givenTeam0, Team givenTeam1, Random givenRand)
  {
    team0 = givenTeam0;
    team1 = givenTeam1;
    rand = givenRand;
  }
  
  /**
   * Returns the name for the indicated team.
   * @param index
   *   0 or 1
   * @return
   *   name for team 0 if 0 is given, name for team 1 otherwise
   */
  public String getTeamName(int index)
  {
    if (index == 0)
    {
      return team0.getName();
    }
    else
    {
      return team1.getName();
    }
  }
  
  /**
   * Gets the distance gained (possibly negative) of a running play 
   * in which the given team is the offense.
   * @param offense
   *   offensive team for the play (0 or 1)
   * @return
   *   number of yards gained (possibly negative)
   */
  public int getRunDistance(int offense)
  {
    // assume an average gain of 1 yard, adjusted by 30% of the difference in the two
    // teams' running and blocking skill points.
    double expected = 1;
    double adjust;
    if (offense == 0)
    {
      adjust = (team0.getRunSkill() - team1.getBlockSkill()) * 0.3;
    }
    else
    {
      adjust =  (team1.getRunSkill() - team0.getBlockSkill()) * 0.3;
    }
    expected += adjust;
    
    // use a normal distribution with mean 'expected' and standard deviation 3
    double d = rand.nextGaussian() * 3 + expected;
    return (int) Math.round(d);
  }
  
  /**
   * Returns the result of attempting a pass by the given team.
   * @param offense
   *   offensive team for the play (0 or 1)
   * @param yardsAttempted
   *   number of yards of the attempted pass
   * @return
   *   true if the pass succeeded, false otherwise
   */
  public boolean getPassResult(int offense, int yardsAttempted)
  {     
    // basic probability based on distance is 50% for a 30 yard pass
    double distanceFactor = (80 - yardsAttempted) / 100.0;
    distanceFactor = Math.max(0.0, Math.min(1.0, distanceFactor));
 
    // adjust up or down by 30% based on team's passing skill vs
    // other team's defensive skill
   double skillAdjust;
   if (offense == 0)
   {
     skillAdjust = (team0.getPassSkill() - team1.getPassDefenseSkill()) / 20.0;
   }
   else
   {
     skillAdjust = (team1.getPassSkill() - team0.getPassDefenseSkill()) / 20.0;
   }
   skillAdjust = Math.max(-.30, Math.min(.30, skillAdjust));
   double p = distanceFactor + skillAdjust;
   
   // clamp between 0 and 95% overall
   p = Math.max(0.0, Math.min(0.95, p));
   
   double flip = rand.nextDouble();
   return flip < p;
  }
  
  /**
   * Returns the result of a field goal attempt by the given team.
   * @param offense
   *   offensive team for the play (0 or 1)
   * @param yardsAttempted
   *   number of yards of the field goal attempt
   * @return
   *   true if the attempt is successful, false otherwise
   */
  public boolean getFieldGoalResult(int offense, int yardsAttempted)
  {
    // basic probability based on distance is 50% at the 40 yard line
    double distanceFactor = (90 - yardsAttempted) / 100.0;
    distanceFactor = Math.max(0.0, Math.min(1.0, distanceFactor));

    // adjustment up or down by up to 30% depends on 
    // team's kicking skill
    double skillAdjust = 0;
    if (offense == 0)
    {
     skillAdjust = (team0.getKickSkill() - 20) / 30.0;
    }
    else
    {
      skillAdjust = (team1.getKickSkill() - 20) / 30.0;
    }
    
    // clamp adjustment to plus or minus 30%
    skillAdjust = Math.max(-0.3, Math.min(0.3, skillAdjust));
    double p = distanceFactor + skillAdjust;
    
    // clamp between 0 and 95% overall
    p = Math.max(0.0, Math.min(0.95, p));
    double flip = rand.nextDouble();
    return flip < p;
  }
  
  /**
   * Returns the net distance of a punt by the given team.  This 
   * is affected by the kicking skill of the offense and the running
   * skill of the defense.  The value returned is never negative.
   * @param offense
   *   offensive team for the play (0 or 1)
   * @return
   *   net distance of a punt, but not less than zero
   */
  public int getPuntDistance(int offense)
  {
    // assume an expected value of 50 yards, adjusted by the relative
    // kicking skill and running skill of the two teams
    double expected = 50;
    double adjust;
    if (offense == 0)
    {
      adjust = (team0.getKickSkill() - team1.getRunSkill()) * 5;
    }
    else
    {
      adjust = (team1.getKickSkill() - team0.getRunSkill()) * 5;
    }
    expected += adjust;
    
    // normal distribution with mean 'expected' and standard deviation 20
    double d = rand.nextGaussian() * 20 + expected;
    
    // we don't allow distance to be negative
    d = Math.max(0, d);
    return (int) Math.round(d);
  }
  
  /**
   * Returns the result of an extra point attempt by the given team.
   * @param offense
   *   offensive team for the play (0 or 1)
   * @return
   *   true if the extra point succeeded, false otherwise
   */
  public boolean getExtraPointResult(int offense)
  {
    // probability 75%, adjusted 20% up or down by team's kicking skill
    double p = .75;
    
    double adjust;
    if (offense == 0)
    {
      adjust = (team0.getKickSkill() - 20) / 50.0;
    }
    else
    {
      adjust = (team1.getKickSkill() - 20) / 50.0;
    }
    
    // clamp adjustment between -20% and 20%
    adjust = Math.max(-.20, Math.min(.20, adjust));
    p += adjust;
    
    double flip = rand.nextDouble();
    return flip < p;      
  }
}
