package hw4;

import graph.Cell;
import state.Snake;
import state.SnakeSegment;
import state.State;

import java.awt.*;

import static main.Config.MAX_SNAKE_TIMER;
import static main.Config.endGame;

/**
 * Created by ruski on 4/16/2017.
 */
public class SnakeHead implements State, Snake {

    public int length = 4;
    private int timer = 0;

    @Override
    public int getLength() {
        return length;
    }


    @Override
    public void handle(Cell cell) {


        timer++;
        if (timer >= MAX_SNAKE_TIMER) timer = 0;
        if (timer == 0) {
            //Cell c = ((c = cell.getRandomCloser()) != null) ? c : ((c=cell.getRandomOpen()) != null) ? c : null;
            Cell c = cell.getRandomCloser();
            if (c == null) {
                c = cell.getRandomOpen();
                if (c == null) endGame(length);
            }

            if (c.getState() instanceof Food) {
                length++;
            }
            cell.moveState(c);
            cell.setState(new SnakeSegment(this));
        }
    }


    /**
     * Returns the color of the snakehead
     *
     * @return the color object with the random color I made up
     */
    @Override
    public Color getColor() {
        Color snakeHeadColor = new Color(.42f, .42f, .42f);
        return snakeHeadColor;
    }

    /**
     * Checks to if the snakehead can pass through itself
     *
     * @return false
     */
    @Override
    public boolean isPassable() {
        return false;
    }

    /**
     * Return the character representation of a snakehead
     *
     * @return 'S'
     */
    @Override
    public char toChar() {
        return 'S';
    }
}
