
/*
 * @author: lingyun
 * @date:5/9/2015
 */


import java.awt.Point;
import java.util.ArrayList;

public class OptimalPath {


	private  int nX;
	private  int nY;
	private Point start;//top left point of robot(0,0)
	private Point goal;//top left point of goal
	private  int distance[][];
	private boolean tree[][];
	private ArrayList shortestPath;
	private boolean accessible[][];
	private int stepCost=1;
	private  ArrayList <Point> queue;

	public OptimalPath(int x,int y,Point start,Point goal){

		initialiseData(x, y, start, goal);	
		setObstacle();
		checkSetting(start, goal);
		printData();
		int step=1;
		Point cur=new Point();
		ArrayList<Point> list=new ArrayList<Point>();
		
		while(!(queue.size()==0)){
			cur.setLocation(getTheNextPt());
			list=getSurroundingPt(cur,1,true);
			queue.addAll(list);			
			queue.remove(cur);
			assignCostForSurroundingPt(cur,list);
		}
		//printMapData();		
		shortestPath=getOptimalPath(goal);
		printOptimalPath();
	}
	private void printData() {
		// TODO Auto-generated method stub
		System.out.println();
		System.out.println("Robot size:3 X 3");
		System.out.println("top left of start:"+start.x+","+start.y);
		System.out.println("top left of goal:"+goal.x+","+goal.y);
		System.out.println("start/goal:3 X 3");
		System.out.println();
	}
	private void checkSetting(Point start, Point goal) {
		boolean settingCorrect=true;
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				if(accessible[start.x+i][start.y+i]&&			
						accessible[goal.x+i][goal.y+i]&&
						settingCorrect)
					settingCorrect=true;
				else
				{settingCorrect=false;
				break;}		
			}
		}
		if (!settingCorrect){
			for(int i=0;i<3;i++){	
				for(int j=0;j<3;j++){
					accessible[start.x+i][start.y+i]=true;
					accessible[goal.x+i][goal.y+i]=true;
				}
			}
		}
	}
	private void printMapData() {
		for(int i=0;i<nY;i++){
			System.out.println();
			for(int j=0;j<nX;j++){
				int dd=distance[j][i];
				System.out.printf( "%-5d ", dd);
			}
		}
	}
	private void initialiseData(int x, int y, Point start, Point goal) {
		this.nX=x;
		this.nY=y;
		this.start=start;
		this.goal=goal;
		this.distance=new int[x][y];
		this.tree=new boolean[x][y];
		this.accessible=new boolean[x][y];
		
		for(int i=0;i<nY;i++){
			for(int j=0;j<nX;j++){
				distance[j][i]=1000;
				tree[j][i]=false;
				accessible[j][i]=true;
			}
		}
		distance[start.x][start.y]=0;	
		tree[start.x][start.y]=true;	
		queue=new ArrayList<Point> ();	
		queue.add(start);
	}
	private void setObstacle() {
		for(int j=0;j<5;j++){
			accessible[j][5]=false;
		}
		for(int j=8;j<nY-1;j++){
			accessible[j][5]=false;
		}
		for(int j=5;j<nY-4;j++){
			accessible[j][11]=false;
		}
		for(int i=9;i<nX;i++){
			accessible[i][0]=false;
		}
		accessible[5][12]=false;
		accessible[15][6]=false;
		accessible[15][7]=false;
		accessible[15][10]=false;
		accessible[15][11]=false;
		accessible[13][10]=false;
		accessible[14][4]=false;
		accessible[15][4]=false;
		accessible[16][4]=false;
	}
	private Point getTheNextPt() {
		// TODO Auto-generated method stub
		int min=10000;
		Point ptWithLowestCost=new Point();
		for(Point p:queue)
			if (distance[p.x][p.y]<min){
				min=distance[p.x][p.y];
				ptWithLowestCost.setLocation(p);;
			}

		return ptWithLowestCost;
	}
	public ArrayList<Point> getSurroundingPt(Point p,int step,boolean unvisited){
		ArrayList list=new ArrayList();
		int x=p.x;int y=p.y;
		for(int i=y-step;i<=y+step;i++){
			for(int j=x-step;j<=x+step;j++){
				if (i==x&&j==y)continue;
				if (i<0||i>=this.nY||j<0||j>=this.nX)continue;
				if (tree[j][i]==true&&unvisited)continue;				
				if (accessibility(new Point(j,i),new Point(p))==false)continue;
				if (i==y||j==x){
					list.add(new Point(j,i));
				}
			}
		}
		return list;
	}
	private boolean accessibility(Point nextP,Point curPoint) {
		// TODO Auto-generated method stub
		int x=nextP.x;
		int y=nextP.y;
		int direction= getDirection(nextP,curPoint);
		if (accessible[nextP.x][nextP.y]==false)return false;
		if (x<0||x+2>=nX||y<0||y+2>=nY)return false;
		if (direction==0){
			return(accessible[x][y]&&
					accessible[x+1][y]&&
					accessible[x+2][y]);	
		}
		else if (direction==2){
			return(accessible[x][y+2]&&
					accessible[x+1][y+2]&&
					accessible[x+2][y+2]);	
		}
		else if (direction==1){
			return(accessible[x+2][y]&&
					accessible[x+2][y+1]&&
					accessible[x+2][y+2]);	
		}
		else if (direction==-1){
			return(accessible[x][y]&&
					accessible[x][y+1]&&
					accessible[x][y+2]);	
		}
		return false;
	}
	public void assignCostForSurroundingPt(Point cur,
			ArrayList<Point> surroundingPts){

		for(Point p:surroundingPts){
			if (tree[p.x][p.y]==false)
			{			
				distance[p.x][p.y]=distance[cur.x][cur.y]+stepCost;
				tree[p.x][p.y]=true;
			}
		}

	}
	public ArrayList<Point> getOptimalPath(Point goal){
		ArrayList<Point> pathTemp=new ArrayList<Point>();
		ArrayList<Point> surroundingPt=new ArrayList<Point>();
		Point cur=new Point(goal);
		Point temp = null;
		int curDirection=0;
		int preDirection=0;
		int tempDirection=0;
		int cost;
		int curTurnCost;
		int minTurnCost;
		int maxStep=nX*nY+100;
		int count=0;
		pathTemp.add(goal);
		while(!cur.equals(start)&&count<maxStep){
			count++;
			cost=distance[cur.x][cur.y];
			surroundingPt=getSurroundingPt(cur,1,false);
			minTurnCost=3;
			tempDirection=preDirection;
			for(Point p:surroundingPt){
				curDirection=getDirection(p,new Point(cur.x,cur.y));
				if (distance[p.x][p.y]!=cost-stepCost)continue;
				curTurnCost = getTurningCost(tempDirection, curDirection);
				if (curTurnCost<minTurnCost)
				{				
					temp=new Point(p.x, p.y);		
					minTurnCost=curTurnCost;
					preDirection=curDirection;
				}
			}	
			cur=new Point(temp.x,temp.y);
			pathTemp.add(cur);
		}

		return pathTemp;
	}
	private int getTurningCost(int curDirection, int preDirection) {
		if (curDirection==preDirection)
			return 0;
		else if (Math.abs(curDirection)==Math.abs(preDirection))
			return 2;
		else
			return Math.abs(Math.abs(curDirection)-Math.abs(preDirection));
	}

	public int getDirection(Point next, Point cur) {
		// TODO Auto-generated method stub
		int direction=0;
		if(cur.equals(next))return 3;
		if (!(cur.x==next.x||cur.y==next.y))return 3;//diagonal 

		else if (cur.x==next.x){
			if(cur.y>next.y)direction=0;//up
			else direction=2;//down
		}
		else{
			if(cur.x<next.x)direction=1;//right
			else direction=-1;//left
		}
		return direction;
	}
	public void printOptimalPath(){
		Point temp=new Point();
		for(int i=0;i<nY;i++){
			System.out.print("\n");
			for(int j=0;j<nX;j++){
				temp.setLocation(j, i);
				if (temp.equals(start)&&shortestPath.contains(temp))
					System.out.print(" S ");
				else if (temp.equals(goal)&&shortestPath.contains(temp))
					System.out.print(" G ");
				else if(accessible[temp.x][temp.y]==false)
					System.out.print(" X ");
				else if (shortestPath!=null&&shortestPath.contains(temp)){
					System.out.print(" - ");
				}
				else{
					System.out.print(" m ");
				}
			}
		}
	}






}
