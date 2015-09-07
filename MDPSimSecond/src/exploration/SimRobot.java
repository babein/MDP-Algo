package exploration;
import map.MapData;
import map.CoveredMap;
import map.Cell;

import java.util.LinkedList;
import java.util.List;

public class SimRobot implements RobotData, MapData {

	private CoveredMap map = null;
	private int direction= EAST;

	// the position of the robot can only be from (0,0) to (18,13)
	private int pos_x= 2;
	private int pos_y= 2;
	
	private boolean previousRightBorder = false;
	private boolean previousLeftWall = false;
	
	private int sensorF;
	private int sensorL;
	private int sensorR;
	


	public SimRobot(int sensorF, int sensorL, int sensorR) {
		super();
		this.sensorF = sensorF;
		this.sensorL = sensorL;
		this.sensorR = sensorR;
	}


	void ForwardByOne() {
		switch (this.direction) {
		case EAST:
			this.pos_x++;
			break;
		case SOUTH:
			this.pos_y--;
			break;
		case WEST:
			this.pos_x--;
			break;
		case NORTH:
			this.pos_y++;
			break;
		}
		if(this.map!=null){
			this.map.repaint();
		}
	}


	void turnLeft() {
		switch (this.direction) {
		case EAST:
			this.direction = NORTH;
			break;
		case SOUTH:
			this.direction = EAST;
			break;
		case WEST:
			this.direction = SOUTH;
			break;
		case NORTH:
			this.direction = WEST;
			break;
		}	
		if(this.map!=null){
			this.map.repaint();
		}
	}
	void turnRight() {
		switch (this.direction) {
		case EAST:
			this.direction = SOUTH;
			break;
		case SOUTH:
			this.direction = WEST;
			break;
		case WEST:
			this.direction = NORTH;
			break;
		case NORTH:
			this.direction = EAST;
			break;
		}
		if(this.map!=null){
			this.map.repaint();
		}

	}

	void moveForward(int x) {
		for(int i=0;i<x;i++){
			this.ForwardByOne();
		}
	}
	void moveLeft(int x) {
		this.turnLeft();
		for(int i=0;i<x;i++){
			this.ForwardByOne();
		}	
	}
	void moveRight(int x) {
		this.turnRight();
		for(int i=0;i<x;i++){
			this.ForwardByOne();
		}
	}
	void turnBack() {
		this.turnLeft();
		this.turnLeft();
	}
	
	void senseFront(CoveredMap map) {
		Cell uncovered1 = null;
		Cell uncovered2 = null;
		Cell uncovered3 = null;
		for (int i = 0; i <= this.sensorF; i++) {
			switch (this.direction) {
			case EAST:
				uncovered1 = map.uncoverACell(this.pos_x + i, this.pos_y,
						this.getDirectionString());
				uncovered2 = map.uncoverACell(this.pos_x + i, this.pos_y - 1,
						this.getDirectionString());
				uncovered3 = map.uncoverACell(this.pos_x + i, this.pos_y + 1,
						this.getDirectionString());
				break;
			case SOUTH:
				uncovered1 = map.uncoverACell(this.pos_x + 1, this.pos_y + i,
						this.getDirectionString());
				uncovered2 = map.uncoverACell(this.pos_x, this.pos_y + i,
						this.getDirectionString());
				uncovered3 = map.uncoverACell(this.pos_x - 1, this.pos_y + i,
						this.getDirectionString());
				break;
			case WEST:
				uncovered1 = map.uncoverACell(this.pos_x - i, this.pos_y,
						this.getDirectionString());
				uncovered2 = map.uncoverACell(this.pos_x - i, this.pos_y - 1,
						this.getDirectionString());
				uncovered3 = map.uncoverACell(this.pos_x - i, this.pos_y + 1,
						this.getDirectionString());
				break;
			case NORTH:
				uncovered1 = map.uncoverACell(this.pos_x + 1, this.pos_y - i,
						this.getDirectionString());
				uncovered2 = map.uncoverACell(this.pos_x, this.pos_y - i,
						this.getDirectionString());
				uncovered3 = map.uncoverACell(this.pos_x - 1, this.pos_y - i,
						this.getDirectionString());
				break;
			}
			if (uncovered1.getColor() == WALL || uncovered2.getColor() == WALL || uncovered3.getColor() == WALL)
				break;
		}
	}

	void senseLeft(CoveredMap map) {
		Cell uncovered1 = null;
		Cell uncovered2 = null;
		Cell uncovered3 = null;
		for (int i = 0; i <= this.sensorL; i++) {
			switch (this.direction) {
			case EAST:
				uncovered1 = map.uncoverACell(this.pos_x + 1, this.pos_y - i,
						this.getDirectionString());
				uncovered2 = map.uncoverACell(this.pos_x, this.pos_y - i,
						this.getDirectionString());
				uncovered3 = map.uncoverACell(this.pos_x - 1, this.pos_y - i,
						this.getDirectionString());
				break;
			case SOUTH:
				uncovered1 = map.uncoverACell(this.pos_x + i, this.pos_y,
						this.getDirectionString());
				uncovered2 = map.uncoverACell(this.pos_x + i, this.pos_y - 1,
						this.getDirectionString());
				uncovered3 = map.uncoverACell(this.pos_x + i, this.pos_y + 1,
						this.getDirectionString());
				break;
			case WEST:
				uncovered1 = map.uncoverACell(this.pos_x + 1, this.pos_y + i,
						this.getDirectionString());
				uncovered2 = map.uncoverACell(this.pos_x, this.pos_y + i,
						this.getDirectionString());
				uncovered3 = map.uncoverACell(this.pos_x - 1, this.pos_y + i,
						this.getDirectionString());
				break;
			case NORTH:
				uncovered1 = map.uncoverACell(this.pos_x - i, this.pos_y,
						this.getDirectionString());
				uncovered2 = map.uncoverACell(this.pos_x - i, this.pos_y - 1,
						this.getDirectionString());
				uncovered3 = map.uncoverACell(this.pos_x - i, this.pos_y + 1,
						this.getDirectionString());
				break;
			}
			if (uncovered1.getColor() == WALL || uncovered2.getColor() == WALL)
				break;
		}
	}

	void senseRight(CoveredMap map) {
		Cell uncovered1 = null;
		Cell uncovered2 = null;
		Cell uncovered3 = null;
		for (int i = 0; i <= this.sensorR; i++) {
			switch (this.direction) {
			case EAST:
				uncovered1 = map.uncoverACell(this.pos_x + 1, this.pos_y + i,
						this.getDirectionString());
				uncovered2 = map.uncoverACell(this.pos_x, this.pos_y + i,
						this.getDirectionString());
				uncovered3 = map.uncoverACell(this.pos_x - 1, this.pos_y + i,
						this.getDirectionString());
				break;
			case SOUTH:
				uncovered1 = map.uncoverACell(this.pos_x - i, this.pos_y,
						this.getDirectionString());
				uncovered2 = map.uncoverACell(this.pos_x - i, this.pos_y - 1,
						this.getDirectionString());
				uncovered3 = map.uncoverACell(this.pos_x - i, this.pos_y + 1,
						this.getDirectionString());
				break;
			case WEST:
				uncovered1 = map.uncoverACell(this.pos_x + 1, this.pos_y - i,
						this.getDirectionString());
				uncovered2 = map.uncoverACell(this.pos_x, this.pos_y - i,
						this.getDirectionString());
				uncovered3 = map.uncoverACell(this.pos_x - 1, this.pos_y - i,
						this.getDirectionString());
				break;
			case NORTH:
				uncovered1 = map.uncoverACell(this.pos_x + i, this.pos_y,
						this.getDirectionString());
				uncovered2 = map.uncoverACell(this.pos_x + i, this.pos_y - 1,
						this.getDirectionString());
				uncovered3 = map.uncoverACell(this.pos_x + i, this.pos_y + 1,
						this.getDirectionString());
				break;
			}
			if (uncovered1.getColor() == WALL || uncovered2.getColor() == WALL)
				break;
		}
	}

	public void senseAll(CoveredMap map) {
		this.senseFront(map);
		this.senseLeft(map);
		this.senseRight(map);
	}

	public void moveWithPath(List<Cell> Path){
		
		
		LinkedList<Cell> path = new LinkedList<Cell>();
		LinkedList<Cell> Turning = new LinkedList<Cell>();
		LinkedList<Cell> Turning_Reversed = new LinkedList<Cell>();
		LinkedList<Cell> Turning_Reversed2 = new LinkedList<Cell>();
		boolean wentVertical=false;
		boolean goingVertical=false;
		
		switch (this.direction) {

		case EAST:
		case WEST:
			wentVertical = false;
			goingVertical = false;
			break;
		case NORTH:
		case SOUTH:
			wentVertical = true;
			goingVertical = true;
			break;
		}
		
		
		for(Cell cell : Path){
			path.push(cell);
		}
		Cell previous=null;
		Cell start = path.get(0);
		Cell end = path.get(path.size()-1);
		Turning.push(start);
		
		for(Cell currentcell : path){
			
			if(previous != null){
				if (currentcell.getCol() == previous.getCol()){
					goingVertical = true;
				}
					
				else if (currentcell.getRow() == previous.getRow())
					goingVertical = false;
			}
			
			if (wentVertical!=goingVertical){
				Turning.push(previous);
			}
				
			wentVertical = goingVertical;
			previous = currentcell;
		}
		Turning.push(end);
		
		for(Cell cell: Turning){
			Turning_Reversed.push(cell);
		}
		
		int weight[]= new int[Turning_Reversed.size()];
		int i=0;
		
		for(Cell cell : Turning_Reversed){
			System.out.println("turning at ("+cell.getCol()+","+cell.getRow()+")");
			
			if(previous != null){
				weight[i]=Math.abs(cell.getCol()+cell.getRow()-previous.getCol()-previous.getRow());
				i++;
			}
			previous = cell;
		}
		
		Turning_Reversed2=Turning_Reversed;
		Turning_Reversed2.pop();
		
		
		
		for(Cell cell : Turning_Reversed2){
			
			switch (this.direction) {
     
			case EAST:
				System.out.println("cell column: "+cell.getCol()+" row: "+cell.getRow());
				System.out.println("this column: "+this.pos_x+" y: "+this.pos_y);
				if(cell.getCol()+1>this.pos_x && cell.getRow()+1==this.pos_y){
					moveForward(weight[i]);
					i++;
				}
				else if (cell.getCol()+1<this.pos_x && cell.getRow()+1==this.pos_y){
					turnBack();
					moveForward(weight[i]);
					i++;
					
				}
				else if (cell.getRow()+1<this.pos_y && cell.getCol()+1==this.pos_x){
					moveLeft(weight[i]);
					i++;
				}
				else if (cell.getRow()+1>this.pos_y && cell.getCol()+1==this.pos_x){
					moveRight(weight[i]);
					i++;
				}
				else{
					
				}
					//do nothing
					
				break;
			case SOUTH:
				if(cell.getCol()+1>this.pos_x && cell.getRow()+1==this.pos_y){
					moveLeft(weight[i]);
					i++;
				}
				else if (cell.getCol()+1<this.pos_x && cell.getRow()+1==this.pos_y){
					moveRight(weight[i]);
					i++;
				}
				else if (cell.getRow()+1<this.pos_y && cell.getCol()+1==this.pos_x){
					turnBack();
					moveForward(weight[i]);
					i++;
				}
				else if (cell.getRow()+1>this.pos_y && cell.getCol()+1==this.pos_x){
					moveForward(weight[i]);
					i++;
				}
				else{
					
				}
					//do nothing
					
				break;
			case WEST:
				if(cell.getCol()+1>this.pos_x && cell.getRow()+1==this.pos_y){
					turnBack();
					moveForward(weight[i]);
					i++;
				}
				else if (cell.getCol()+1<this.pos_x && cell.getRow()+1==this.pos_y){
					moveForward(weight[i]);
					i++;
				}
				else if (cell.getRow()+1<this.pos_y && cell.getCol()+1==this.pos_x){
					moveRight(weight[i]);
					i++;
				}
				else if (cell.getRow()+1>this.pos_y && cell.getCol()+1==this.pos_x){
					moveLeft(weight[i]);
					i++;
				}
				else{
					
				}
					//do nothing
					
				break;
			case NORTH:
				if(cell.getCol()+1>this.pos_x && cell.getRow()+1==this.pos_y){
					moveRight(weight[i]);
					i++;
				}
				else if (cell.getCol()+1<this.pos_x && cell.getRow()+1==this.pos_y){
					moveLeft(weight[i]);
					i++;
				}
				else if (cell.getRow()+1<this.pos_y && cell.getCol()+1==this.pos_x){
					
					moveForward(weight[i]);
					i++;
				}
				else if (cell.getRow()+1>this.pos_y && cell.getCol()+1==this.pos_x){
					turnBack();
					moveForward(weight[i]);
					i++;
				}
				else{
					
				}
					//do nothing
					
				break;
			}
			
			map.repaint();
		}
	}
	
	public int getDirection() {
		return this.direction;
	}

	public int getPosX() {
		return this.pos_x;
	}

	public int getPosY() {
		return this.pos_y;
	}
	
	public void setPosX(int x){
		this.pos_x = x;
	}
	
	public void setPosY(int y){
		this.pos_y = y;
	}
	
	public void setDirection(String s){
		switch(s){
		case "EAST":
			this.direction = EAST;
			break;
		case "SOUTH":
			this.direction = SOUTH;
			break;
		case "WEST":
			this.direction = WEST;
			break;
		case "NORTH":
			this.direction = NORTH;
			break;
		}
		this.map.repaint();
	}

	public boolean getPreviousRightBorder() {
		return previousRightBorder;
	}
	
	public boolean getPreviousLeftWall() {
		return previousLeftWall;
	}


	public void setPreviousRightBorder(boolean b) {
		this.previousRightBorder = b;
	}

	public void setPreviousLeftWall(boolean b) {
		this.previousLeftWall = b;
	}
	public String getDirectionString() {
		switch (this.direction) {
		case EAST:
			return "east";
		case SOUTH:
			return "south";
		case WEST:
			return "west";
		case NORTH:
			return "north";
		default:
			return null;
		}
	}


	public CoveredMap getMap() {
		return map;
	}
	
	public int getXC() {
		return (this.pos_x - 1) * 33 + 15;
	}

	public int getYC() {
		return (this.pos_y - 1) * 33 + 15;
	}
	public void setMap(CoveredMap map) {
		this.map = map;
	}
}

