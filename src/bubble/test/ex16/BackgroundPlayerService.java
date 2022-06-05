package bubble.test.ex16;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

// 메인스레드 바쁨 -> 키보드 이벤트를 처리하기 바쁨.
// Player 움직임 관찰
public class BackgroundPlayerService implements Runnable{ // 하나의 새로운 쓰레드로 만들어주기 위해서 Runnable를 implements 해준다.
	
	private BufferedImage image;
	private Player player;
	
	public BackgroundPlayerService(Player player) {
		this.player = player;
		try {
			image = ImageIO.read(new File("images/backgroundMapService.png"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		while(true) {
			// 색상 확인 (플레이어 위치에 따른)
			Color leftColor = new Color(image.getRGB(player.getX()-10, player.getY()+25));
			Color rightColor = new Color(image.getRGB(player.getX()+50+15, player.getY()+25));
			// -2가 나온다는 뜻은 바닥에 색깔이 없어 흰색이라는 말이다
			int bottomColor = image.getRGB(player.getX()+10, player.getY()+50)
										+ image.getRGB(player.getX()+50 - 10, player.getY()+50);
			
			// 바닥 충돌 확인
			if(bottomColor != -2) {
				player.setDown(false);
			} else { // -2 일때 실행됨 => 내 바닥색깔이 하얀색이라는 것 즉 공중에 떠있다는 말
				if(!player.isUp() && !player.isDown()) {
					player.down();
				}
			}
			
			// 외벽 충돌 확인
			if(leftColor.getRed() == 255 && leftColor.getGreen() == 0 && leftColor.getBlue() ==0) {
				player.setLeftWallCrash(true);
				player.setLeft(false);
			} else if(rightColor.getRed() == 255 && rightColor.getGreen() == 0 && rightColor.getBlue() ==0) {
				player.setRightWallCrash(true);
				player.setRight(false);
			} else {
				player.setLeftWallCrash(false);
				player.setRightWallCrash(false);
			}
			
			try {
				Thread.sleep(10);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}

		
	}
	
	

}
