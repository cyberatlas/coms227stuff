package main;

import graph.GraphMap;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 * Control for a Desktop application.
 * @author Brian Nakayama
 * @see main.View
 */
public class Control implements MouseMotionListener{

	/**
	 * The model that this control updates.
	 */
	private GraphMap graphMap;
	
	public Control(GraphMap graphMap){
		this.graphMap = graphMap;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.awt.event.MouseMotionListener#mouseDragged(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
		
	}

	/*
	 * (non-Javadoc)
	 * @see java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		graphMap.updateMouse(e.getX(), e.getY());
	}

}
