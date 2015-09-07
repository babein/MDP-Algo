package map;

import java.awt.Color;

public interface MapData {
	
	// size of the arena
	static final int COLS = 20;
	static final int ROWS = 15;
	
	//color of the wall
	static final Color WALL = Color.BLACK;
	
	//color of the free area
	static final Color FREE = Color.LIGHT_GRAY;
	
	//color of the start & goal
	static final Color START = Color.DARK_GRAY;
	
	//color of the unexplored area
	static final Color UNEXPLORED = Color.RED;
	
	//color of the robot front
	static final Color ROBOTF = Color.GREEN;
	
	//color of the robot back
	static final Color ROBOTB = Color.YELLOW;
	
	//color of the path
	static final Color PATH = Color.BLUE;
}
