package bubble.test.ex17;

import javax.swing.ImageIcon;

import javax.swing.JLabel;

import lombok.Getter;
import lombok.Setter;


//class Player -> new 가능한 애들!! 게임에 존재할 수 있음. (추상 메서드를 가질 수 없다.)
@Getter
@Setter
public class Enemy extends JLabel implements Moveable {

	private BubblerFrame mContext;
	
	// 위치 상태 값
	private int x;
	private int y;

	// 움직임 상태
	private boolean left; // boolean은 isLeft 이런식으로 함수가 만들어진다. (lombok 특징)
	private boolean right;
	private boolean up;
	private boolean down;
	
	private int state; // 0(살아 있는 상태), 1(물방울에 갇힌 상태
	
	// 적군의 방향
	private EnemyDirection enemyDirection;
	
	// 적군의 속도 상태
	private final int SPEED = 3;
	private final int JUMPSPEED = 1;

	private ImageIcon enemyR, enemyL;

	public Enemy(BubblerFrame mContext) {
		this.mContext = mContext;
		initObject();
		initSetting();
		initBackgroundEnemyService();
	}

	private void initObject() {
		enemyR = new ImageIcon("images/enemyR.png");
		enemyL = new ImageIcon("images/enemyL.png");
	}

	private void initSetting() {
		x = 480;
		y = 178;

		left = false;
		right = false;
		up = false; 
		down = false;
		
		enemyDirection = EnemyDirection.RIGHT;

		setIcon(enemyR);
		setSize(50, 50);
		setLocation(x, y);
	}
	
	private void initBackgroundEnemyService() {
//		new Thread(new BackgroundEnemyService(this)).start();
	}
	

	// 이벤트 핸들러
	@Override
	public void left() {
		enemyDirection = EnemyDirection.LEFT;
		left = true;
		
		// 2가지 일을 동시에 하려고 하면 무조건 쓰레드를 사용해야한다.
		new Thread(() -> {
			while (left) {
				setIcon(enemyL);
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
		enemyDirection = EnemyDirection.RIGHT;
		right = true;
		
		// 2가지 일을 동시에 하려고 하면 무조건 쓰레드를 사용해야한다.
		new Thread(() -> {
			while(right) {
				setIcon(enemyR);
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
