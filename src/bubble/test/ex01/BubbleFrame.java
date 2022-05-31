package bubble.test.ex01;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;

public class BubbleFrame extends JFrame {
	
	public BubbleFrame() {
	
		setSize(1000, 640);
		getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(43, 88, 117, 29);
		getContentPane().add(btnNewButton);
		setVisible(true); // 그림을 그려라
		
	}
	
	public static void main(String[] args) {
		new BubbleFrame();
	}
}
