package hw4;

import graph.Cell;
import state.State;

import java.awt.*;

/**
 * Created by Ruski on 4/14/2017.
 */
public class Wall implements State {
    public Wall() {


    }

    @Override
    public void handle(Cell cell) {
        //TODO
    }

    /**
     * Returns the color white because walls are white
     *
     * @return color object which is white
     */
    @Override
    public Color getColor() {
        //White is a combination of all colors so we use 100% of each
        Color wallColor = new Color(1f, 1f, 1f);
        return wallColor;
    }

    /**
     * Boolean that checks if one can pass through a wall or not
     *
     * @return false because you cannot pass through a wall
     */
    @Override
    public boolean isPassable() {
        return false;
    }

    /**
     * Gets the character representation of the wall
     *
     * @return '#' which represents a wall
     */
    @Override
    public char toChar() {
        return '#';
    }
}
