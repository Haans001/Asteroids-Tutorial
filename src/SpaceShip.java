import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;

@SuppressWarnings("serial")
public class SpaceShip extends Polygon {
	
	private double xVelocity = 0 , yVelocity = 0;
	int gbWidth = Main.boardWidth, gbHeight = Main.boardHeight;
	
	private double centerX = gbWidth/2, centerY = gbHeight/2;
	
	public static int[] polXArray = {-13,14,-13,-5,-13};
	public static int[] polYArray = {-15, 0, 15, 0,-15};
	
	private int shipWidth = 27, shipHeight = 30;
	
	private double uLeftXPos = getXCenter() + this.polXArray[0];
	private double uLeftYPos = getYCenter() + this.polYArray[0];
	
	private double rotationAngle = 0, movingAngle = 0;
	
	public SpaceShip() {
		
		super(polXArray,polYArray,5);	
	}
	
	public double getXCenter() {return centerX;}
	public double getYCenter() {return centerY;}
	
	public void setXCenter(double xCent) {this.centerX = xCent;}
	public void setYCenter(double yCent) {this.centerY = yCent;}
	
	public void incraseXPos(double incAmt) {this.centerX+= incAmt;}
	public void incraseYPos(double incAmt) {this.centerY+= incAmt;}
	
	
	public double getxVelocity() {return xVelocity;}
	public void setxVelocity(double xVelocity) {this.xVelocity = xVelocity;}
	public double getyVelocity() {return yVelocity;}
	public void setyVelocity(double yVelocity) {this.yVelocity = yVelocity;}

	public double getuLeftXPos() {return uLeftXPos;}
	public void setuLeftXPos(double uLeftXPos) {this.uLeftXPos = uLeftXPos;}
	public double getuLeftYPos() {return uLeftYPos;}
	public void setuLeftYPos(double uLeftYPos) {this.uLeftYPos = uLeftYPos;}

	
	public int getShipWidth() {return shipWidth;}
	public int getShipHeight() {return shipHeight;}
	
	public void incraseXVeloctiy(double xVel) {this.xVelocity+=xVel;}
	public void incraseYVeloctiy(double yVel) {this.yVelocity+=yVel;}
	
	public void decraseXVeloctiy(double xVel) {this.xVelocity-=xVel;}
	public void decraseYVeloctiy(double yVel) {this.xVelocity-=yVel;}
	
	public double getMovingAngle() {return this.movingAngle;}
	public void setMovingAngle(double movAngle) {this.movingAngle = movAngle;}
	
	public void incraseMovingAngle(double movAngle) {this.movingAngle+=movAngle;}
	
	public double ShipXMovingAngle(double xMoveAngle) {
		
		return (double) (Math.cos(xMoveAngle * Math.PI/180));	
		
	}
	
	public double ShipYMovingAngle(double yMoveAngle) {
		
		return (double) (Math.sin(yMoveAngle * Math.PI/180));	
		
	}
	
	public double getRotationAngle() {return this.rotationAngle;}
	
	
	public void incraseRotationAngle() {
		
		if(this.rotationAngle>=355) this.rotationAngle = 0;
		
		else this.rotationAngle +=5;
	}
	
	public void decraseRotationAngle() {
		
		if(this.rotationAngle<0) this.rotationAngle = 355;
		
		else this.rotationAngle -=5;
	}
	
	public Rectangle getBounds() {
		
		return new Rectangle(getShipWidth()-13, getShipHeight()-15, getShipWidth(), getShipHeight());
	}
	
	
	public void move() {
		
		this.incraseXPos(this.getxVelocity());
		
		if(this.getXCenter()<0) this.setXCenter(this.gbWidth);
		else if(this.getXCenter()>gbWidth) this.setXCenter(0);

		this.incraseYPos(this.getyVelocity());
		
		if(this.getYCenter()<0) this.setYCenter(this.gbHeight);
		else if(this.getYCenter()>gbHeight) this.setYCenter(0);
		
		
		if(GamePanel.keyHeld && GamePanel.keyHeldCode == KeyEvent.VK_D) {
			
			this.incraseRotationAngle();
			System.out.println("Ship Angle " + this.getRotationAngle());
			
		}else if(GamePanel.keyHeld && GamePanel.keyHeldCode == KeyEvent.VK_A) {
			
			this.decraseRotationAngle();
			System.out.println("Ship Angle " + this.getRotationAngle());
			
		}else if(GamePanel.keyHeld && GamePanel.keyHeldCode == KeyEvent.VK_W) {
			
				this.setMovingAngle(this.getRotationAngle());
				
				this.incraseXVeloctiy(this.ShipXMovingAngle(this.getMovingAngle())*0.1);
				this.incraseYVeloctiy(this.ShipYMovingAngle(this.getMovingAngle())*0.1);
		}
		
			
		
	}
	
	
	public void draw(Graphics2D g) {
		
		AffineTransform transform = new AffineTransform();
		
		g.setTransform(transform);
		g.translate(this.getXCenter(), this.getYCenter());
		
		g.rotate(Math.toRadians(this.getRotationAngle()));
		g.draw(this);
		
		this.move();
	}
	
	
	
}
