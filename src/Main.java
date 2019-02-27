import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
public class Main extends JFrame {

	public static final int boardWidth = 1000;
	public static final int boardHeight = 1000;
	
	
	public static void main(String[] args) {

		new Main();

	}
	
	public Main() {
		
		this.setSize(boardWidth,boardHeight);
		this.setTitle("Asteroids");
		this.setDefaultCloseOperation(3);
		
		GamePanel gamePanel = new GamePanel();
		
		this.add(gamePanel, BorderLayout.CENTER);
		
		// Stworzenie watku ktory rendruje grafike
		
		ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(5);
		executor.scheduleAtFixedRate(new RepaintBoard(this), 0L, 20L, TimeUnit.MILLISECONDS);
		
		this.setVisible(true);
		
	}
	

}

class RepaintBoard implements Runnable{
	
	Main theBoard;
	
	public RepaintBoard(Main board) {
		this.theBoard = board;
	}

	@Override
	public void run() {
		theBoard.repaint();
		
	}
	
}



