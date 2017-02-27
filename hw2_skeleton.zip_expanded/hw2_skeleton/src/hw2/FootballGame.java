package hw2;

/**
 * This class encapsulates the logic and state for a simplified
 * game of American football.
 *
 * @author Alexander Stevenson
 */

public class FootballGame {
    /**
     * Number of points awarded for a touchdown.
     */
    public static final int TOUCHDOWN_POINTS = 6;

    /**
     * Number of points awarded for a successful extra point attempt
     * after a touchdown.
     */
    public static final int EXTRA_POINTS = 1;

    /**
     * Number of points awarded for a field goal.
     */
    public static final int FIELD_GOAL_POINTS = 3;

    /**
     * Total length of the field from goal line to goal line, in yards.
     */
    public static final int FIELD_LENGTH = 100;

    /**
     * Initial position of the offensive team after receiving a kickoff.
     */
    public static final int STARTING_POSITION = 70;

    /**
     * Yards required to get a first down.
     */
    public static final int YARDS_FOR_FIRST_DOWN = 10;

    /**
     * Instance variable that holds the score for team 0
     */
    private int score0;
    /**
     * Variable that holds the score for team 1
     */
    private int score1;
    /**
     * Instance variable that holds the  ball's position, relative to the number of yards left, initially set to the starting position
     */
    private int position = STARTING_POSITION;

    /**
     * Variable that holds the number of yards left for a first down
     */
    private int yardFirst = YARDS_FOR_FIRST_DOWN;
    /**
     * Because this simulation doesn't believe in 2 point conversions, we have to use the scoreTD variable to see if a TD has been scored recently, if so, prevent another until there is at least a PAT
     */
    private boolean scoreTD;
    /**
     * Variable that holds which team is on offense
     */
    private int offense;

    /**
     * Variable that holds which down you are on, initially set to 1
     */
    private int down = 1;

    /**
     * The constructor for the object FootballGame, it takes no arguments
     */
    public FootballGame() {
        //initially sets the offense to 0 and the yards to first down to 10
        offense = 0;
        yardFirst = YARDS_FOR_FIRST_DOWN;


    }

    /**
     * Method called when an extra point is attempted. If successful the score goes up by 1, the other team gets the ball and the field resets
     * otherwise the other team just gets the ball, and the field resets from starting position
     *
     * @param success is the extra point successful?
     */
    public void extraPoint(boolean success) {
        if (getOffense() == 0) {
            //if successful then score0 goes up by 1, otherwise it remains the same
            score0 = (success == true) ? score0 + EXTRA_POINTS : score0;
        } else if (getOffense() == 1) {
            score1 = (success == true) ? score1 + EXTRA_POINTS : score1;
        }
        //Just switches the offense. So if it was team0 it becomes team 1 and vice versa
        offense = (offense == 1) ? 0 : 1;
        position = STARTING_POSITION;
        yardFirst = YARDS_FOR_FIRST_DOWN;
        down = 1;
        //If you score a TD then you have to at least attempt an extra point, after you do you can try to score a td again
        scoreTD = false;
    }

    /**
     * Checks to see if a field goal was successful or not, adds points accordingly. Resets the field if the team is successful. Switches who is on offense
     *
     * @param success Was the field goal good?
     */
    public void fieldGoal(boolean success) {
        //runs only if the field goal was successful and if it is team 1 that scored they will gets points and if it was team 2 they will get points
        if ((success == true) && (getOffense() == 1)) {
            score1 += FIELD_GOAL_POINTS;
            position = STARTING_POSITION;
        } else if ((success == true) && (getOffense() == 0)) {
            score0 += FIELD_GOAL_POINTS;
            position = STARTING_POSITION;

        } else {
            position = FIELD_LENGTH - position;
        }
        //Switches the team on offense and resets the number of yards needed for a first down
        offense = (offense == 1) ? 0 : 1;
        yardFirst = YARDS_FOR_FIRST_DOWN;
    }

    /**
     * Returns what down it is
     *
     * @return the down
     */
    public int getDown() {
        return down;
    }

    /**
     * Checks who is on offense
     *
     * @return the offensive team
     */
    public int getOffense() {
        return offense;
    }

    /**
     * Checks the score for a given team
     *
     * @param team input the team you want to check
     * @return the score for said team
     */
    public int getScore(int team) {
        if (team == 1) {
            return score1;
        }
        return score0;
    }

    /**
     * Checks how many yards there are until a first down
     *
     * @return the number of yards until a first down
     */
    public int getYardsToFirstDown() {
        return yardFirst;
    }

    /**
     * Checks how many yards there are until a touchdown
     *
     * @return the number of yards from the goal line
     */
    public int getYardsToGoalLine() {
        return position;
    }

    /**
     * Simulates a punt down the field and switches posession of the field and resets the position, down, and yards to first down
     *
     * @param yards takes in the number of yards the punt traveled
     */
    public void punt(int yards) {
        offense = (offense == 1) ? 0 : 1;
        // We change the position by picking min between 100 and the positon becuase there are no negative yards
        position = Math.min(FIELD_LENGTH - (position - yards), FIELD_LENGTH);
        yardFirst = YARDS_FOR_FIRST_DOWN;

    }

    /**
     * Simulates running or passing the ball, this method is like Atlas, it holds up the program. This is where touchdowns, turnovers, and moving the chains is checked.
     *
     * @param yard takes the number of yards the ball moved
     */
    public void runOrPass(int yard) {
        //Subtracts the progress from the position
        position -= yard;
        yardFirst -= yard;
        //checks to see if the football is in the endzone and makes sure that the team didn't just score a TD.
        if ((position <= 0) && (scoreTD == false)) {
            if (getOffense() == 0) {
                score0 += TOUCHDOWN_POINTS;
            }
            if (getOffense() == 1) {
                score1 += TOUCHDOWN_POINTS;
            }
            //Sets the boolean to true so that a touchdown cannot be scored again without an extra point being attempted
            scoreTD = true;
        }

        if (yardFirst < 1) {
            yardFirst = 10;
            down = 1;
        } else {
            down += 1;
        }

        if (down > 4) {
            offense = (offense == 1) ? 0 : 1;
            position = FIELD_LENGTH - position;
            down = 1;
            yardFirst = YARDS_FOR_FIRST_DOWN;
        }
//If the position is greater than 100 it resets it to 100
        position = (position > 100) ? 100 : position;

    }

}
