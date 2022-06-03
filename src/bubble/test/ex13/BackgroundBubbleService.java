package bubble.test.ex13;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

// 메인스레드 바쁨 -> 키보드 이벤트를 처리하기 바쁨.
// Player 움직임 관찰
public class BackgroundBubbleService{ // 하나의 새로운 쓰레드로 만들어주기 위해서 Runnable를 implements 해준다.
	
	private BufferedImage image;
	private Bubble bubble;
	
	public BackgroundBubbleService(Bubble bubble) {
		this.bubble = bubble;
		try {
			image = ImageIO.read(new File("images/backgroundMapService.png"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean leftWallCheck() {
		Color leftColor = new Color(image.getRGB(bubble.getX()-10, bubble.getY()+25));
		if(leftColor.getRed() == 255 && leftColor.getGreen() == 0 && leftColor.getBlue() ==0) {
			return true;
		} 
		return false;
	}
	
	public boolean rightWallCheck() {
		Color rightColor = new Color(image.getRGB(bubble.getX()+50+15, bubble.getY()+25));
		if(rightColor.getRed() == 255 && rightColor.getGreen() == 0 && rightColor.getBlue() ==0) {
			return true;
		} 
		return false;
	}
	
	public boolean topWallCheck() {
		Color topColor = new Color(image.getRGB(bubble.getX()+25, bubble.getY()-10));
		if(topColor.getRed() == 255 && topColor.getGreen() == 0 && topColor.getBlue() ==0) {
			return true;
		} 
		return false;
	}

}
