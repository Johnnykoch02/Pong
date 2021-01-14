
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.util.Random;
import java.awt.Rectangle;

import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.event.MouseInputListener;

public class Gameplay extends JPanel implements KeyListener, ActionListener, MouseInputListener {

	public static boolean running = false;
	private int delay = 7;
	private Timer timer;

	private Random r = new Random();

	private Ball ball;
	private int ballSpeedCheck = 0;
	private int ballX = 80;
	private int ballY = 80;
	private int ballXDir = (-1) * (r.nextInt(2) + 1);
	private int ballYDir = (-1) * (r.nextInt(2) + 1);
	private int p1X = 0;
	private int p1Y = 0;

	private int mX;
	private int mY;

	private boolean collisionDetected = false;

	private Player p1;
	 private AI ai;
	 private int guessAI = 300;
	private int rightScore = 0;
	private int leftScore = 0;
	private Color rightColor = new Color(255,153,153);
	private Color leftColor = new Color(153,204,255);

	

	public Gameplay() {
		// constructer
		running = true;
		addKeyListener(this);
		addMouseListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(true);
		timer = new Timer(delay, this);
		timer.start();
		p1 = new Player();
		p1.setYPos(p1Y);
		ball = new Ball();
		ball.setX(ballX);
		ball.setY(ballY);

	}

	public void paint(Graphics g) {
		g.setColor(new Color( 0, 50, 13));
		g.fillRect(0, 0, 800, 600);
		
		g.setColor(Color.white);
		g.fillRect(360, 120, 15, 350);

		g.setColor(rightColor);
		g.fillRect(p1X, p1Y, 25, 150);

		g.setColor(Color.MAGENTA);
		g.fillOval(ball.getX(), ball.getY(), 25, 25);

		g.setColor(leftColor);
		g.fillRect(760, guessAI, 25, 150);

		g.setColor(Color.white);
		g.setFont(new Font("Times Roman", Font.BOLD, 30));
		g.drawString("Score", 330, 35);

		g.setColor(Color.red);
		g.setFont(new Font("Times Roman", Font.BOLD + Font.ITALIC, 25));
		g.drawString("" + leftScore, 300, 55);

		g.setColor(Color.blue);
		g.setFont(new Font("Times Roman", Font.BOLD + Font.ITALIC, 25));
		g.drawString("" + rightScore, 440, 55);

		

		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (running) {
			// speedcheck
			if (ballXDir > 18 || ballXDir < -18 || ballYDir > 18 || ballYDir < -18) {
				ballSpeedCheck = 0;
			}

			ballX = ball.getX();
			ballY = ball.getY();

			if (ballX <= 0 || ballX >= 760) {
				ballXDir = -ballXDir;
				ballSpeedCheck++;
				if(ballX <=0)
				{
					rightScore++;
					guessAI = ball.getGuess(ballXDir, ballYDir, ball.getX(), ball.getY(), 760 );
				}//adds to Right Side Score
				if(ballX >= 760)
				{
					leftScore++;
					p1Y = ball.getGuess(ballXDir, ballYDir, ball.getX(), ball.getY(), 760 );
				}//adds to the Left Side Score
				if (ballXDir < 0 && ballSpeedCheck == 5) {
					ballXDir--;
					ballSpeedCheck = 0;
				}
				if (ballXDir > 0 && ballSpeedCheck == 5) {
					ballXDir++;
					ballSpeedCheck = 0;
				}
			}
			if (ballY <= 0 || ballY >= 540) {
				ballYDir = -ballYDir;
				if (ballYDir < 0 && ballSpeedCheck == 5) {
					ballYDir--;
					ballSpeedCheck = 0;
				}
				if (ballYDir > 0 && ballSpeedCheck == 5) {
					ballYDir++;
					ballSpeedCheck = 0;
				}
			}

			ballX += ballXDir;
			ball.setX(ballX);
			ballY += ballYDir;
			ball.setY(ballY);

			if (new Rectangle(p1X, p1Y, 25, 150).intersects(new Rectangle(ballX, ballY, 25, 25))) {
				ballXDir = -ballXDir;
				if (ballYDir < 0) {
					ballYDir--;
					
					guessAI = ball.getGuess(ballXDir, ballYDir, ball.getX(), ball.getY(), 760);
				}
				if (ballYDir > 0) {
					ballYDir++;
					
					guessAI = ball.getGuess(ballXDir, ballYDir, ball.getX(), ball.getY(), 760);
				}
			}
				if(new Rectangle(740, guessAI, 25, 150).intersects(new Rectangle(ballX, ballY, 25, 25))) {
					ballXDir = -ballXDir;
					if (ballYDir < 0) {
						ballYDir--;
						ballSpeedCheck = 3;
						p1Y = ball.getGuess(ballXDir, ballYDir, ball.getX(), ball.getY(), 760);
						p1Y-=25;
					}
					if (ballYDir > 0) {
						ballYDir++;
						ballSpeedCheck = 3;
						p1Y = ball.getGuess(ballXDir, ballYDir, ball.getX(), ball.getY(), 760);
						p1Y-=25;
					}

			}
		}

			if (ballXDir > 10)
				ballXDir = 10;
			else if (ballXDir < -10)
				ballXDir = -10;
			if (ballYDir > 10)
				ballYDir = 10;
			else if (ballYDir < -10)
				ballYDir = -10;


				if((ballXDir / ballYDir) > 3 || (ballXDir / ballYDir) < -3) {
					if(ballXDir < 0)
					ballXDir++;
					else if(ballXDir > 0)
					ballXDir--;
				}
				if((ballYDir / ballXDir) > 3 || (ballYDir / ballXDir) < -3) {
					if(ballYDir < 0)
					ballYDir++;
					else if(ballYDir > 0)
					ballYDir--;

				}

				
				//Construct a way for the pc to get a new guess by playing around with the FOR loop and the Balls Position
					//RIGHT SIDE****************
				if(ballX == 200 && ballXDir > 0)
				{
					guessAI = ball.getGuess(ballXDir, ballYDir, ball.getX(), ball.getY(), 760 - 200);
				
				//	guessAI -=20;

				}
				if (ballX == 400 && ballXDir > 0)
				{
				
					guessAI = ball.getGuess(ballXDir, ballYDir, ball.getX(), ball.getY(), 760 - 400);

				//	guessAI -= 20;

				}
					//LEFT SIDE***************
				if(ballX == 200 && ballXDir < 0)
				{
					p1Y = ball.getGuess(ballXDir, ballYDir, ball.getX(), ball.getY(), 760 - 200);
					p1Y-=25;
				//	guessAI -=20;

				}
				if (ballX == 400 && ballXDir < 0)
				{
				
					p1Y = ball.getGuess(ballXDir, ballYDir, ball.getX(), ball.getY(), 760 - 400);
					p1Y-=25;
				//	guessAI -= 20;

				}
				//p1Y-=25;
			repaint();
		}

	

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_W) {
			p1.move("up");
			p1Y = p1.getYPos();

		}
		if (e.getKeyCode() == KeyEvent.VK_S) {
			p1.move("down");
			p1Y = p1.getYPos();

		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		mY = e.getY();
		p1.setYPos(mY);
	}

	public boolean getCollisionStatus() {
	
		return collisionDetected;

	}
	
	public int getYDir() {

		return ballYDir;

	}

	public int getXDir() {

		return ballXDir;

	}
}
