import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.ArrayList;


public class Rock extends Polygon {
	
	int uLeftXPos, uLeftYPos;
	int xDir=1, yDir=1;
	
	int[] polyXArray,polyYArray;
	int width = Main.boardWidth,height = Main.boardHeight;
	int rockWidth = 35;
	int rockHeight = 31;
	
	public static int[] sPolyXArray = {10,17,26,34,27,36,26,14,8,1,5,1,10,10};
	public static int[] sPolyYArray = {0,5,1,8,13,20,31,28,31,19,20,22,16,7};
	
	public Rock(int[] polyXArray, int[] polyYArray,int pointsInPoly, int randomX,int randomY) {
		
		super(polyXArray,polyYArray,pointsInPoly);
		
		this.xDir = (int) (Math.random() *4 +1);
		this.yDir = (int) (Math.random() *4 +1);
		
		this.uLeftXPos = randomX;
		this.uLeftYPos = randomY;
	}
	
	//  Poruszanie siê ska³
	public void move(ArrayList<Rock> rocks) {
		
		checkForCollisionWithRocks(rocks);
		
		int uLeftXPos = super.xpoints[0];
		int uLeftYPos = super.ypoints[0];
		
		if(uLeftXPos<0 || (uLeftXPos +25 ) >width) xDir = -xDir;
		if(uLeftYPos<0 || (uLeftYPos +25 ) >height) yDir = -yDir;
		
		for(int i=0;i<super.xpoints.length;i++) {
			super.xpoints[i] += xDir;
			super.ypoints[i] += yDir;
		}
	}
	
	
	// Collision with rocks detector
	private void checkForCollisionWithRocks(ArrayList<Rock> rocks) {
		
		Rectangle currentRect = this.getBounds();
		for(Rock rock: rocks) {
			
			Rectangle otherRock = rock.getBounds();
			
			if(rock!=this && otherRock.intersects(currentRect)) {
				
				int tempXDir = this.xDir;
				int tempYDir = this.yDir;
				
				this.xDir = rock.xDir;
				this.yDir = rock.yDir;
				
				rock.xDir = tempXDir;
				rock.yDir = tempYDir;
			}
		}
		
	}


	public static int[] getpolyXArray(int randomStartXPos) {
		
		int[] tempPolyXArray = (int[])sPolyXArray.clone();
		
		for(int i=0;i<tempPolyXArray.length;i++) {
			tempPolyXArray[i]+= randomStartXPos;
		}
		return tempPolyXArray;
	}
	
	
		public static int[] getpolyYArray(int randomStartYPos) {
		
		int[] tempPolyYArray = (int[])sPolyYArray.clone();
		
		for(int i=0;i<tempPolyYArray.length;i++) {
			tempPolyYArray[i]+= randomStartYPos;
		}
		return tempPolyYArray;
	}
		
		// zwraca rozmiary poligonu w postaci kwadratu
		public Rectangle getBounds() {
			return new Rectangle(super.xpoints[0],super.ypoints[0],rockWidth,rockHeight); 
		}
	
}
