package ThreadTest02;

public class ThreadMain {

	public static void main(String[] args) {
		System.out.println("메인 스레드 시작");

		for (int i = 0; i < 10; i++) {
			Thread thread = new CalculateThread("작업 스레드" + i);
			if (i == 9) {
				thread.setPriority(Thread.MAX_PRIORITY);
			} else {
				thread.setPriority(Thread.MIN_PRIORITY);
			} 
			thread.start();
		}
		System.out.println("메인 스레드 종료");
	}
}

