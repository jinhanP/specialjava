package ThreadTest03;
//5번 스레드 객체 만들기
public class User2 extends Thread{
	//멤버변수
	private Calculator calculator;//5번
	//디생
	public User2(String name) {
		super();
		this.setName(name);
	}

	public void setCalculator(Calculator calculator) {
		this.calculator = calculator;
	}

	@Override
	public void run() {//6번 //실행
		super.run();
		this.calculator.setMemory(50);
	}
}