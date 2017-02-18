package example;

import java.util.Random;
import java.util.Scanner;

import hw2.FootballGame;

/**
 * Console-based user interface for a simplified American football game simulation.
 */
public class UI
{
  /**
   * Game state instance.
   */
  private FootballGame game;
 
  /**
   * Outcome generator for simulating game plays.
   */
  private OutcomeGenerator gen;

  /**
   * Instance of java.util.Scanner for reading input.
   */
  private Scanner scanner;

  
  /**
   * Entry point for starting the application.
   * @param args
   *   not used
   */
  public static void main(String[] args)
  {
    // To create a team, allocate 100 skill points among the various
    // stats described in the Team documentation.
    
    // a team with all around average stats
    Team t = new Team("Badgers", 20, 20, 20, 20, 20);
    
    // a team that is better at running and passing than at defense
    Team t1 = new Team("Skullcrushers", 30, 10, 30, 10, 20);
    
    // Create an OutcomeGenerator for the game using the two teams and 
    // an instance of Random.  Use of an explcit seed (in this case 42) 
    // means we can reproduce a given run.
    Random rand = new Random(42);
    OutcomeGenerator gen = new OutcomeGenerator(t, t1, rand);
    
    // Create the game state and start up a UI
    FootballGame g = new FootballGame();
    UI ui = new UI(g, gen);
    ui.runUI();
  }
  
  /**
   * Constructs an instance of the UI for the given game and generator. 
   * @param givenGame
   * @param givenGenerator
   */
  public UI(FootballGame givenGame, OutcomeGenerator givenGenerator)
  {
    game = givenGame;
    scanner = new Scanner(System.in);
    gen = givenGenerator;
  }
  
  /**
   * Main loop for running the UI.
   */
  public void runUI()
  {
    boolean done = false;
    while (!done)
    {
      display();
      String s = getUserInput();
      
      if (s.equals("a"))
      {
        doRun();
      }
      else if (s.equals("b"))
      {
        doPass(getPassDistance());
      }
      else if (s.equals("c"))
      {
        doFieldGoal();
      }
      else if (s.equals("d"))
      {
        doPunt();
      }
      else if (s.equals("q"))
      {
        done = true;
      }
      else
      {
        System.out.println("Please enter a, b, c, d, or q");
      }
    }
  }
  
  /**
   * Simulates a running play and updates game state.
   */
  private void doRun()
  {
    int yards = gen.getRunDistance(game.getOffense());
    System.out.println("Run distance: " + yards);
    updateAfterRunOrPass(yards);
  }
  
  /**
   * Simulates a passing play and updates game state.
   * @param distance
   */
  private void doPass(int distance)
  {
    boolean success = gen.getPassResult(game.getOffense(), distance);
    if (success)
    {
      System.out.println("Pass complete.");
    }
    else
    {
      System.out.println("Pass incomplete.");
      distance = 0;
    }
    updateAfterRunOrPass(distance);
  }
  
  /**
   * Simulates a field goal attempt and updates game state.
   */
  private void doFieldGoal()
  {
    boolean success = gen.getFieldGoalResult(game.getOffense(), game.getYardsToGoalLine());
    if (success)
    {
      System.out.println("Field goal is good.");
    }
    else
    {
      System.out.println("Field goal attempt failed.");
    }
    game.fieldGoal(success);
  }
  
  /**
   * Simulates a punt and updates game state.
   */
  private void doPunt()
  {
    int distance = gen.getPuntDistance(game.getOffense());
    System.out.println("Punt distance: " + distance);
    game.punt(distance);
  }
  
  /**
   * Helper method updates game state after a run or pass play.
   * If a touchdown occurs, simulates the extra point attempt.
   * @param yards
   *   number of yards gained as a result of the play
   */
  private void updateAfterRunOrPass(int yards)
  {
    int currentTeam = game.getOffense();
    int currentScore = game.getScore(currentTeam);
    
    // update game state
    game.runOrPass(yards);
    
    // was it a turnover, a touchdown, or a first down?
    if (game.getOffense() != currentTeam)
    {
      System.out.println("Turnover.");
    }
    else if (game.getScore(currentTeam) > currentScore)
    {
      currentScore = game.getScore(currentTeam);
      System.out.println("Touchdown!");
      
      // simulate the extra point attempt
      boolean success = gen.getExtraPointResult(currentTeam);
      if (success)
      {
        System.out.println("Extra point is good!");
      }
      else
      {
        System.out.println("Extra point missed.");
      }
      game.extraPoint(success);
    }
    else if (game.getDown() == 1)
    {
      System.out.println("First down.");
    }
  }

  /**
   * Displays a summary of the current state of the game.
   */
  private void display()
  {
    System.out.println();
    System.out.println(gen.getTeamName(0) + ": " + game.getScore(0) + "  " + gen.getTeamName(1) + ": " + game.getScore(1));
    System.out.println("Offense is " + gen.getTeamName(game.getOffense()));
    System.out.println("Down: " + game.getDown() + " Location: " + game.getYardsToGoalLine() + " Yards needed: " + game.getYardsToFirstDown());
    
  }
  
  /**
   * Displays a menu and allows the user to enter a choice.
   * @return
   *   first letter of user's entry
   */
  private String getUserInput()
  {
    System.out.println("  a) run");
    System.out.println("  b) pass");
    System.out.println("  c) field goal");
    System.out.println("  d) punt");
    System.out.println("  q) quit");
    System.out.print("Your choice: ");
    String s = scanner.next();
    return "" + s.trim().charAt(0);
  }
  
  /**
   * Displays a prompt and allows user to enter the number of yards
   * to attempt a pass.
   * @return
   *   number of yards as entered by user
   */
  private int getPassDistance()
  {
    System.out.print("Enter attempted pass distance: ");
    int distance = scanner.nextInt();
    return distance;
  }
  
}
