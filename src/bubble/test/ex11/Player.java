package bubble.test.ex11;

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
	
	// 플레이어의 방향
	private PlayerDirection playerDirection;
	
	// 벽에 충돌한 상태
	private boolean leftWallCrash;
	private boolean rightWallCrash;
	
	// 플레이어 속도 상태
	private final int SPEED = 5;
	private final int JUMPSPEED = 3;

	private ImageIcon playerR, playerL;

	public Player() {
		initObject();
		initSetting();
		initBackgroundPlayService();
	}

	private void initObject() {
		playerR = new ImageIcon("images/playerR.png");
		playerL = new ImageIcon("images/playerL.png");
	}

	private void initSetting() {
		x = 80;
		y = 540;

		left = false;
		right = false;
		up = false;
		down = false;
		leftWallCrash = false;
		rightWallCrash = false;
		
		playerDirection = PlayerDirection.RIGHT;

		setIcon(playerR);
		setSize(50, 50);
		setLocation(x, y);
	}
	
	private void initBackgroundPlayService() {
		new Thread(new BackgroundPlayerService(this)).start();
	}


	// 이벤트 핸들러
	@Override
	public void left() {
		playerDirection = PlayerDirection.LEFT;
		left = true;
		
		// 2가지 일을 동시에 하려고 하면 무조건 쓰레드를 사용해야한다.
		new Thread(() -> {
			while (left) {
				setIcon(playerL);
				x = x - SPEED;
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
		playerDirection = PlayerDirection.RIGHT;
		right = true;
		
		// 2가지 일을 동시에 하려고 하면 무조건 쓰레드를 사용해야한다.
		new Thread(() -> {
			while(right) {
				setIcon(playerR);
				x = x + SPEED;
				setLocation(x, y);
				try {
					Thread.sleep(10); // 0.01초 간격을 준다.
				} catch (InterruptedException e) {
					e.printStackTrace();
				} // 
			}
		}).start();

	}

	// left + up 이 가능하다.
	// right + up 이 가능하다.
	// 즉 2가지 일을 동시에 하려고 하면 무조건 쓰레드를 사용해야한다.
	@Override
	public void up() {
		up = true;
		new Thread(() -> {
			// 끝이 있는 반복이기 때문에 for문을 돌린다.
			for(var i=0; i<130/JUMPSPEED; i++ ) {
				y = y - JUMPSPEED;
				setLocation(x, y);
				try {
					Thread.sleep(7);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			up = false;
			down();
		}).start();
		

	}

	@Override
	public void down() {
		down = true;
		new Thread(() -> {
			while(down) {
				y = y + JUMPSPEED;
				setLocation(x, y);
				try {
					Thread.sleep(5);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			down = false;
		}).start();

	}

}
