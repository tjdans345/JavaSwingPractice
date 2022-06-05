package bubble.test.ex17;

// 케릭터 행동에 제약을 주기위한 인터페이스
public interface Moveable {
	
	public abstract void left();
	public abstract void right();
	public abstract void up();
//	public abstract void down();
	// (default) -> 자바 높은 버젼부터 나온 문법이다
	// default를 사용하면 인터페이스도 몸체가 있는 메서드를 만들 수 있다. -> 다중 상속이 안되는 자바의 특징을 개선하기 위해서 나온 문법
	// 그렇기 때문에 Adapter문법 보다는 default를 사용하는 것이 좋다.
    default public void down() {};
    default public void attack() {};

}
