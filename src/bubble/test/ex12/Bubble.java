package bubble.test.ex12;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Bubble extends JLabel implements Moveable {

	// 의존성 콤포지션
	private Player player;
	private BackgroundBubbleService backgroundBubbleService;

	// 위치 상태 값
	private int x;
	private int y;

	// 움직임 상태
	private boolean left; // boolean은 isLeft 이런식으로 함수가 만들어진다. (lombok 특징)
	private boolean right;
	private boolean up;

	// 적군을 맞춘 상태private ImageIcon
	private int state; // 0(물방울), 1(적을 가둔 물방울)

	private ImageIcon bubble; // 물방울
	private ImageIcon bubbled; // 적을 가둔 물방울
	private ImageIcon bomb; // 물방울이 터진 상태

	

	public Bubble(Player player) {
		this.player = player;
		initObject();
		initSetting();
		initThread();
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
				break;
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
				break;
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
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	private void initThread() {
		// 버블은 쓰레드가 하나만 필요하다.
		new Thread(() -> {
			if (player.getPlayerDirection() == PlayerDirection.LEFT) {
				left();
			} else {
				right();
			}
		}).start();
	}

}
