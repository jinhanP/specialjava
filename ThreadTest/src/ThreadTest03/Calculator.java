package ThreadTest03;

public class Calculator {
	//멤버변수
		private int memory;
		//생성자
		public Calculator() {
			super();
		}
		//겟터,셋터
		public int getMemory() {//데이터 모아져있는곳
			return memory;
		}
		public synchronized void setMemory(int memory) {
			this.memory = memory;
			//switching 발생(강제로 스위칭을 발생시키기위해서 2초시간을 부여함) //실행->실행 대기 반복 ->switching 만나면 일시정지 ->실행대기
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName()+"="+this.memory);
		}
	}
