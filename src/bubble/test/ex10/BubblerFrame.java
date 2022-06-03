package bubble.test.ex10;

import java.awt.Container;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * @author meteor
 *
 */

public class BubblerFrame extends JFrame {

	private JLabel backgroundMap;
	private Player player;

	public BubblerFrame() {
		initObject();
		initSetting();
		initListner();
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
	
	private void initListner() {
		// adapter 패턴
		addKeyListener(new KeyAdapter() {
			
			// 키보드 클릭 이벤트 핸들러
			@Override
			public void keyPressed(KeyEvent e) {
				
				switch(e.getKeyCode()) {
					case KeyEvent.VK_LEFT: 
						if(!player.isLeft() && !player.isLeftWallCrash()) {
							player.left();
						}
						
						break;
					case  KeyEvent.VK_RIGHT: 
						if(!player.isRight() && !player.isRightWallCrash()) {
							player.right();
						}
						break;
					case  KeyEvent.VK_UP: 
						if(!player.isUp() && !player.isDown()) {
							player.up();
						}
						break;
				}
			}
			
			// 키보드 해제 이벤트 핸들러
			@Override
			public void keyReleased(KeyEvent e) {
				switch (e.getKeyCode()) {
					case KeyEvent.VK_LEFT: 
						player.setLeft(false);
						break;
						
					case KeyEvent.VK_RIGHT: 	
						player.setRight(false);
						break; 
						
					case KeyEvent.VK_SPACE:
						Bubble bubble = new Bubble(player);
						add(bubble);
						break;
				}
			}
			
		});
	}

	public static void main(String[] args) {
		new BubblerFrame();
	}

}
