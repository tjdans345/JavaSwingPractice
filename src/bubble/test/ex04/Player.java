package bubble.test.ex04;

import javax.swing.ImageIcon;

import javax.swing.JLabel;

import lombok.Getter;
import lombok.Setter;


//class Player -> new 가능한 애들!! 게임에 존재할 수 있음. (추상 메서드를 가질 수 없다.)
@Getter
@Setter
public class Player extends JLabel implements Moveable {

	// 위치 상태 값
	private int x;
	private int y;

	// 움직임 상태
	private boolean left; // boolean은 isLeft 이런식으로 함수가 만들어진다. (lombok 특징)
	private boolean right;
	private boolean up;
	private boolean down;

	private ImageIcon playerR, playerL;

	public Player() {
		initObject();
		initSetting();
	}

	private void initObject() {
		playerR = new ImageIcon("images/playerR.png");
		playerL = new ImageIcon("images/playerL.png");
	}

	private void initSetting() {
		x = 55;
		y = 535;

		left = false;
		right = false;
		up = false;
		down = false;

		setIcon(playerR);
		setSize(50, 50);
		setLocation(x, y);
	}

	@Override
	public void left() {
		System.out.println("left");
		left = true;
		new Thread(() -> {
			while (left) {
				setIcon(playerL);
				x = x - 1;
				setLocation(x, y);
				try {
					Thread.sleep(10); // 0.01초 간격을 준다.
				} catch (InterruptedException e) {
					e.printStackTrace();
				} // 
			}
		}).start();

	}

	@Override
	public void right() {
		right = true;
		System.out.println("right");
		new Thread(() -> {
			while(right) {
				setIcon(playerR);
				x = x + 1;
				setLocation(x, y);
				try {
					Thread.sleep(10); // 0.01초 간격을 준다.
				} catch (InterruptedException e) {
					e.printStackTrace();
				} // 
			}
		}).start();

	}

	@Override
	public void up() {
		y = y - 10;
		setLocation(x, y);

	}

	@Override
	public void down() {
		y = y + 10;
		setLocation(x, y);
		// TODO Auto-generated method stub

	}

}
