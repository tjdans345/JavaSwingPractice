package bubble.test.ex10;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Bubble extends JLabel {
	
	// 의존성 콤포지션
	private Player player;

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
	}

	private void initObject() {
		bubble = new ImageIcon("images/bubble.png");
		bubbled = new ImageIcon("images/bubbled.png");
		bomb = new ImageIcon("images/bomb.png");
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

}
