package bubble.test.ex02;

import java.awt.Container;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


/**
 * @author meteor
 *
 */
public class BubblerFrame  extends JFrame{
	
	private JLabel backgroundMap;
	private Player player;
	
	public BubblerFrame() {
		initObject();
		initSetting();
		setVisible(true);
	}
	

	
	private void initObject() {
		backgroundMap = new JLabel(new ImageIcon("images/backgroundMap.png"));
		setContentPane(backgroundMap);
		
		player = new Player();
		// 플레이어를 덧 붙여줌
		add(player);
		
		
		
		
//		backgroundMap.setSize(100, 100);
//		backgroundMap.setLocation(300, 300);
//		backgroundMap.setSize(1000, 600);
//		add(backgroundMap); // JFrame에 JLabel이 그려진다. add는 그림을 그려주는 메서드
	}
	
	private void initSetting() {
		setSize(1000, 640);
		setLayout(null); // absolute 레이아웃 (자유롭게 그림을 그릴 수 있다.)
		
		
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // x버튼으로 창을 끌 때 JVM 같이 종료하기
	}
	
	
	
	public static void main(String[] args) {
		new BubblerFrame();
	}
	
	
	
	
	
}
