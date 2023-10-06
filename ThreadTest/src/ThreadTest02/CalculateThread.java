package ThreadTest02;

public class CalculateThread extends Thread{
	public CalculateThread(String name) {
		// 스레드 이름 정한다.
		this.setName(name);
	}

	@Override
	public void run() {
		super.run();
		// 20억번 반복을 하다가 자신의 스레드 이름을 출력
		for (int i = 0; i < 2_000_000_000; i++) {

		}
		System.out.println(getName() + "작업종료");
	}
}
