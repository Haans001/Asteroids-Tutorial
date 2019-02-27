import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
public class GamePanel extends JComponent {
	
	public ArrayList<Rock> rocks = new ArrayList<Rock>();
	
	int width = Main.boardWidth;
	int height = Main.boardHeight;
	
	public static boolean keyHeld = false;
	
	public static int keyHeldCode; 
	
	int[] polyXArray = Rock.sPolyXArray;
	int[] polyYArray = Rock.sPolyYArray;
	
	SpaceShip player = new SpaceShip();
	
	
	public GamePanel() {
		// ustawienie nas³uchiwacza klawiszy
		this.setFocusable(true);
		this.addKeyListener(new ListenForPlayer());
		
		//  dodawanie ska³ do listy
		for(int i=0;i<10;i++) {
			int randomStartXPos = (int) (Math.random() * (width-40) +1);
			int randomStartYPos = (int) (Math.random() * (height-40) +1);	
			rocks.add(new Rock(Rock.getpolyXArray(randomStartXPos), Rock.getpolyYArray(randomStartYPos), 13, randomStartXPos, randomStartYPos));
		}
	}
	
	
	public void paint(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		// t³o
		g2d.setPaint(Color.black);
		g2d.fillRect(0,0,width,height);
		
		// ska³y
		g2d.setPaint(Color.white);
		for(Rock rock : rocks) {
			rock.move(rocks);
			g2d.draw(rock);
		}
		player.draw(g2d);
		
		
	}
	
	private class ListenForPlayer implements KeyListener{

		public void keyTyped(KeyEvent e) {}
		
		public void keyPressed(KeyEvent e) {
			
			if(e.getKeyCode() == KeyEvent.VK_W) {
				keyHeldCode = e.getKeyCode();
				keyHeld = true;
			}
			else if(e.getKeyCode() == KeyEvent.VK_D) {
				
				keyHeldCode = e.getKeyCode();
				keyHeld = true;
			}else if(e.getKeyCode() == KeyEvent.VK_A) {
				keyHeldCode = e.getKeyCode();
				System.out.println("rotate left");
				keyHeld = true;
			}
		}

		
		@Override
		public void keyReleased(KeyEvent e) {
			
			keyHeld = false;
			
		}
		
	}

}
