package exploration;

import exploration.SimRobot;
import map.Cell;
import map.MapData;
import map.CoveredMap;
import exploration.RobotData;

import java.math.BigInteger;

//Uses left wall hugging algo
public class Explorer implements RobotData, MapData{
	
	private SimRobot robot;
	private int timeLimit;
	private CoveredMap coveredMap;
	
	//-------------TEST--------------//
	private int[][] map;
	//-------------TEST-------------//
	
	public Explorer(SimRobot robot, int timeLimit, CoveredMap coveredMap){
		this.robot = robot;
		this.timeLimit = timeLimit;
		this.coveredMap = coveredMap;
	}
	
	//-------------TEST-------------//
	public Explorer(SimRobot robot, int timeLimit, int map[][], CoveredMap coveredMap){
		this.robot = robot;
		this.timeLimit = timeLimit;
		this.map = map;
		this.coveredMap = coveredMap;
	}
	//-------------TEST------------//
	
	public void go(){
		explore();
//		String p1 = generateP1();
//		String p2 = generateP2(p1);
	}
	
	public void explore(){
		int timer = 0;
		
		//--------------TEST------------//
		System.out.print("(0,0),");
		robot.senseAll(coveredMap);
		do{
			if(isLeftWall() || isLeftBorder()){
				while(!isFrontWall() && !isFrontBorder() && (isLeftWall() || isLeftBorder())){
					robot.moveForward(1);
					if(robot.getPosX()==0 && robot.getPosY()==0)
						break;
				}
				
				if(robot.getPosX()==0 && robot.getPosY()==0)
					break;
				
				if(!isLeftWall() && !isLeftBorder()){
					robot.turnLeft();
					robot.moveForward(1);
					if(robot.getPosX()==0 && robot.getPosY()==0)
						break;
				}
				
				else{
					robot.turnRight();
					if(isFrontWall() || isFrontBorder()){
						robot.turnRight();
					}
					else{
						robot.moveForward(1);
						if(robot.getPosX()==0 && robot.getPosY()==0)
							break;
						if(!isLeftWall() && !isLeftBorder()){
							robot.turnLeft();
							robot.moveForward(1);
						}
					}
				}
				
			}
			
			//------------------TEST-----------------------//
			/*
			if(robot.getCurrentX()==1 && robot.getCurrentY()==1){
				System.out.print("(1,1)");
				break;
			}
			*/
			//------------------TEST----------------------//
			
				
		}while(timer<=timeLimit && (robot.getPosX()!=0 || robot.getPosY()!=0));
		
		System.out.println();
		
	}
	
	private boolean isRightWall(int[][] map) {
		//--------------TEST--------------//
		int direction = robot.getDirection();
		int x = robot.getPosX();
		int y = robot.getPosY();
		
		switch(direction){
		case EAST:
			if(y!=0 && map[y-1][x] == 1)
				return true;
			break;
			
		case SOUTH:
			if(x!=0 && map[y][x-1] == 1)
				return true;
			break;
			
		case WEST:
			if(y!=ROWS-1 && map[y+1][x] == 1)
				return true;
			break;
			
		case NORTH:
			if(x!=COLS-1 && map[y][x+1] == 1)
				return true;
			break;
		}
		return false;
		//-------------TEST-------------//
	}

	//returns true if border is in front
	private boolean isFrontBorder() {
		//--------------TEST--------------//
		int direction = robot.getDirection();
		int x = robot.getPosX();
		int y = robot.getPosY();
		
		switch(direction){
		case EAST:
			if(x==COLS-1)
				return true;
			break;
			
		case SOUTH:
			if(y==0)
				return true;
			break;
			
		case WEST:
			if(x==0)
				return true;
			break;
			
		case NORTH:
			if(y==ROWS-1)
				return true;
			break;
		}
		return false;
		//-------------TEST-------------//
	}

	//returns true only if wall is in front, not border
	private boolean isFrontWall() {
		//--------------TEST--------------//
		int direction = robot.getDirection();
		int x = robot.getPosX();
		int y = robot.getPosY();
		
		switch(direction){
		case EAST:
			if(x!=COLS-1 && map[y][x+1]==1)
				return true;
			break;
			
		case SOUTH:
			if(y!=0 && map[y-1][x]==1)
				return true;
			break;
			
		case WEST:
			if(x!=0 && map[y][x-1]==1)
				return true;
			break;
			
		case NORTH:
			if(y!=ROWS-1 && map[y+1][x]==1)
				return true;
			break;
		}
		return false;
		//-------------TEST-------------//
	}

	public boolean isLeftWall(){
		//--------------TEST--------------//
		int direction = robot.getDirection();
		int x = robot.getPosX();
		int y = robot.getPosY();
		
		switch(direction){
		case EAST:
			if(y!=ROWS-1 && map[y+1][x]==1)
				return true;
			break;
			
		case SOUTH:
			if(x!=COLS-1 && map[y][x+1]==1)
				return true;
			break;
			
		case WEST:
			if(y!=0 && map[y-1][x]==1)
				return true;
			break;
			
		case NORTH:
			if(x!=0 && map[y][x-1]==1)
				return true;
			break;
		}
		return false;
		//-------------TEST-------------//
	}
	
	public boolean isLeftBorder(){
		//--------------TEST--------------//
		int direction = robot.getDirection();
		int x = robot.getPosX();
		int y = robot.getPosY();
		
		switch(direction){
		case EAST:
			if(y==ROWS-1)
				return true;
			break;
			
		case SOUTH:
			if(x==COLS-1)
				return true;
			break;
			
		case WEST:
			if(y==0)
				return true;
			break;
			
		case NORTH:
			if(x==0)
				return true;
			break;
		}
		return false;
		//-------------TEST-------------//

	}	
	
//	public String generateP1(){
//		String binary = "11";
//		for(int i = 0; i < ROWS; i++){
//			for(int j = 0; j < COLS; j++){
//				binary = binary + coveredMap[i][j].getIsExplored();
//			}
//		}
//		binary = binary + "11";
//		
//		BigInteger temp = new BigInteger(binary,2);
//		String hex = temp.toString(16);		
//		
//		System.out.println("P1: ");
//		System.out.println("Binary: " + binary);
//		System.out.println("Hex: " + hex);
//		
//		return binary;
//	}
//	
//	public String generateP2(String p1){
//		String binary = "";
//		
//		for(int i = 2; i < ROWS*COLS + 2; i++){
//			if(p1.substring(i,i+1).equals("1")){
//				int row = (i-2)/COLS;
//				int column = (i-2)%COLS;
//				binary = binary + coveredMap[row][column].getIsObstacle();
//			}
//		}
//		
//		int numPadding = binary.length()%8;
//		for(int i = 0; i < numPadding; i++){
//			binary = binary + "0";
//		}
//		
//		BigInteger temp = new BigInteger(binary,2);
//		String hex = temp.toString(16);		
//		
//		System.out.println("P2: ");
//		System.out.println("Binary: " + binary);
//		System.out.println("Hex: " + hex);
//		
//		return binary;
//	}

}
