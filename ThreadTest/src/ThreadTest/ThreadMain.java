package ThreadTest;

import java.awt.Toolkit;

public class ThreadMain {
	// main 스레드 시작 과 끝
	public static void main(String[] args) throws InterruptedException {
//			beepTask 객체 생성
//			스레드 1번방식============================== 강제로 오버라이딩 해줘야되고
//			Thread thread = new BeepTask();
//			thread.start();

//			스레드 2번 방식============================= 오버라이딩 해줘야된다
//			Runnable runnable = new BeepTask2();
//			Thread thread = new Thread(runnable);
//			thread.start();

		// 스레드 3번 방식(Thread임시 객체를 만들어 준다)=============================
//			Thread thread = new Thread() {
//				@Override
//				public void run() {
//					super.run();
//					Toolkit toolkit = Toolkit.getDefaultToolkit();
//					try {
//						Thread.sleep(1000); // 1000 = 1초간 지연
//						toolkit.beep();
//						Thread.sleep(1000); // 1000 = 1초간 지연
//						toolkit.beep();
//						Thread.sleep(1000); // 1000 = 1초간 지연
//						toolkit.beep();
//						Thread.sleep(1000); // 1000 = 1초간 지연
//						toolkit.beep();
//					} catch (InterruptedException e) {
//						System.out.println(e.toString());
//					} finally {
//						System.out.println("작업 스레드 종료");
//					}
//				}
//				
//			};
//			thread.start();

		// 스레드 4번 방식(Runnable 임시객체 만들어 준다)=====================================
//			Thread thread1 = new Thread(new Runnable() {
//				@Override
//				public void run() {
//					Toolkit toolkit = Toolkit.getDefaultToolkit();
//					try {
//						Thread.sleep(1000); // 1000 = 1초간 지연
//						toolkit.beep();
//						Thread.sleep(1000); // 1000 = 1초간 지연
//						toolkit.beep();
//						Thread.sleep(1000); // 1000 = 1초간 지연
//						toolkit.beep();
//						Thread.sleep(1000); // 1000 = 1초간 지연
//						toolkit.beep();
//					} catch (InterruptedException e) {
//						System.out.println(e.toString());
//					} finally {
//						System.out.println("작업 스레드 종료");
//					}
//				}
//			});
//			thread1.start();

		// 스레드 5번 방식(Runnable 임시객체 람다식)===================
//			Thread thread2 = new Thread(()-> {
//				Toolkit toolkit = Toolkit.getDefaultToolkit();
//					try {
//						Thread.sleep(1000); // 1000 = 1초간 지연
//						toolkit.beep();
//						Thread.sleep(1000); // 1000 = 1초간 지연
//					toolkit.beep();
//						Thread.sleep(1000); // 1000 = 1초간 지연
//						toolkit.beep();
//						Thread.sleep(1000); // 1000 = 1초간 지연
//						toolkit.beep();
//					} catch (InterruptedException e) {
//						System.out.println(e.toString());
//					} finally {
//						System.out.println("작업 스레드 종료");
//					}
//				}
//			);
//			thread2.start();

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

		// 출력을 다섯번 진행하는 업무를 맡아서 한다. (5초) 기다려야된다.
		System.out.println("떙");
		Thread.sleep(1000); // 1000 = 1초간 지연
		System.out.println("떙");
		Thread.sleep(1000); // 1000 = 1초간 지연
		System.out.println("떙");
		Thread.sleep(1000); // 1000 = 1초간 지연
		System.out.println("떙");
		Thread.sleep(1000); // 1000 = 1초간 지연
		System.out.println("떙");

		System.out.println("메인 스레드 종료");

	}

}
