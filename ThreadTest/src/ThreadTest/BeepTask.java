package ThreadTest;

import java.awt.Toolkit;

public class BeepTask extends Thread {
	@Override // 오버라이딩
	public void run() {
		// 컴퓨터에서 소리가 5번 나온다.
		Toolkit toolkit = Toolkit.getDefaultToolkit();

		try {
			// 둘중에 하나는 스레드를 써서 둘 다 5초만에 끝내준다.(CPU시간을 효율적으로)

			// 종을 다섯번 울리는 일 맡아서 한다. (5초) 기다려야된다. 출력이 위로가면 작업하는데 (10초) 걸린다.
			Thread.sleep(1000); // 1000 = 1초간 지연
			toolkit.beep();
			Thread.sleep(1000); // 1000 = 1초간 지연
			toolkit.beep();
			Thread.sleep(1000); // 1000 = 1초간 지연
			toolkit.beep();
			Thread.sleep(1000); // 1000 = 1초간 지연
			toolkit.beep();
		} catch (InterruptedException e) {
			System.out.println(e.toString());
		} finally {
			System.out.println("작업 스레드 종료");
		}
	}
}
