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

    }

    @Override
    public Color getColor() {
        return null;
    }

    @Override
    public boolean isPassable() {
        return false;
    }

    @Override
    public char toChar() {
        return 0;
    }
}
