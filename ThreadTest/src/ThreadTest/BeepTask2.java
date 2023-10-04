package ThreadTest;

import java.awt.Toolkit;

public class BeepTask2 implements Runnable {
	@Override
	public void run() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();

		// 둘중에 하나는 스레드를 써서 둘 다 5초만에 끝내준다.(CPU시간을 효율적으로)

		// 종을 다섯번 울리는 일 맡아서 한다. (5초) 기다려야된다. 출력이 위로가면 작업하는데 (10초) 걸린다.
		try {
			toolkit.beep();
			Thread.sleep(1000); // 1000 = 1초간 지연
			toolkit.beep();
			Thread.sleep(1000); // 1000 = 1초간 지연
			toolkit.beep();
			Thread.sleep(1000); // 1000 = 1초간 지연
			toolkit.beep();
			Thread.sleep(1000); // 1000 = 1초간 지연
			toolkit.beep();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			System.out.println("작업 스레드 종료");
		}

	}
}
