import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		JFrame frame = new JFrame("Pong: by John");
		Gameplay gameplay =  new Gameplay();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800,600);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.add(gameplay);
	
		

	}

}
