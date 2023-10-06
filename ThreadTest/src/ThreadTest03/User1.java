package ThreadTest03;
//2번 스레드 객체 만들기
public class User1 extends Thread{
	//멤버변수
	private Calculator calculator;//3번 calculator
	//디생
	public User1(String name) {
		super();
		this.setName(name);
	}

	public void setCalculator(Calculator calculator) {
		this.calculator = calculator;
	}

	@Override
	public void run() {//4번
		super.run();
		this.calculator.setMemory(100);
	}
}