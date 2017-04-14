package graph;

import java.util.Iterator;

public class GraphMapIterator implements Iterator<Cell>{

	private GraphMap graphMap;
	private int x = 0;
	private int y = 0;
	
	public GraphMapIterator(GraphMap graphMap){
		this.graphMap = graphMap;
	}
	
	@Override
	public boolean hasNext() {
		return y < graphMap.getCells().length;
	}

	@Override
	public Cell next() {
		Cell current = graphMap.getCells()[y][x];
		
		if (x < graphMap.getCells()[y].length - 1){
			x++;
		} else {
			y++;
			x = 0;
		}
		return current;
	}

	@Override
	public void remove() {
		graphMap.getCells()[y][x].setState(null);
	}

}
