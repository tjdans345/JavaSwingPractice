package bubble.test.ex16;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Bubble extends JLabel implements Moveable {

	// 의존성 콤포지션
	private Player player;
	private Enemy enemy;
	private BackgroundBubbleService backgroundBubbleService;
	private BubblerFrame mContext;

	// 위치 상태 값
	private int x;
	private int y;

	// 움직임 상태
	// 상태는 행위에 따라서 변경이 된다.
	private boolean left; // boolean은 isLeft 이런식으로 함수가 만들어진다. (lombok 특징)
	private boolean right;
	private boolean up;

	// 적군을 맞춘 상태private ImageIcon
	private int state; // 0(물방울), 1(적을 가둔 물방울)

	private ImageIcon bubble; // 물방울
	private ImageIcon bubbled; // 적을 가둔 물방울
	private ImageIcon bomb; // 물방울이 터진 상태

	

	public Bubble(BubblerFrame mContext) {
		this.mContext = mContext;
		this.player = mContext.getPlayer();
		this.enemy = mContext.getEnemy();
		initObject();
		initSetting();
	}

	private void initObject() {
		bubble = new ImageIcon("images/bubble.png");
		bubbled = new ImageIcon("images/bubbled.png");
		bomb = new ImageIcon("images/bomb.png");
		
		backgroundBubbleService = new BackgroundBubbleService(this);
	}

	private void initSetting() {
		left = false;
		right = false;
		up = false;

		x = player.getX();
		y = player.getY();

		setIcon(bubble);
		setSize(50, 50);

		// 최초 물방울 상태
		state = 0;
	}

	@Override
	public void left() {
		left = true;
		
		for (int i = 0; i < 400; i++) {
			x--;
			setLocation(x, y);
			if(backgroundBubbleService.leftWallCheck()) {
				left = false;
				break;
			}
			
			if((Math.abs(x - enemy.getX()) < 10 ) && 
					(Math.abs(y - enemy.getY()) > 0 && Math.abs(y - enemy.getY()) < 50)	 ) {
				if(enemy.getState() == 0 ) {
					System.out.println("물방울이 적군과 충돌 하였습니다.");
					attack();
					break;
				}
			}
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		up();
	}

	@Override
	public void right() {
		right = true;
		for (int i = 0; i < 400; i++) {
			x++;
			setLocation(x, y);
			if(backgroundBubbleService.rightWallCheck()) {
				right = false;
				break;
			}
			
			// 아군과 적군으 거리가 10의 차이가 나면
			if((Math.abs(x - enemy.getX()) < 10 ) && 
					(Math.abs(y - enemy.getY()) > 0 && Math.abs(y - enemy.getY()) < 50)	 ) {
				if(enemy.getState() == 0 ) {
					System.out.println("물방울이 적군과 충돌 하였습니다.");
					attack();
					break;
				}
			}
			
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		up();
	}
	
	

	@Override
	public void up() {
		up = true;  
		while(up) {
			y--;
			setLocation(x, y);
			
			if(backgroundBubbleService.topWallCheck ()) {
				break;
			}
			
			try {
				if(state == 0) { // 기본 물방울
					Thread.sleep(1);
				} else { // 적을 가둔 물방울
					Thread.sleep(10);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if(state == 0) {
			clearBubble(); // 천장에 버블이 도착하고나서 3초 후에 메모리에서 소멸 
		}
		

}
	
	@Override
	public void attack() {
		state = 1; 
		enemy.setState(1);
		
		setIcon(bubbled);
		mContext.remove(enemy);
		mContext.repaint();
	}
	

	
	private void clearBubble() {
		try {
			Thread.sleep(3000);
			setIcon(bomb);
			Thread.sleep(500);
			mContext.remove(this); // BubbleFrame의 bubble이 메모리에서 소멸된다.
			mContext.repaint(); // BubbleFrame의 전체를 다시 그린다. ( 메모리에서 없는 것은 그리지 않는다.
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	



}
