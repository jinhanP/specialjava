package ThreadTest03;

public class CalculatorMain {

	public static void main(String[] args) {
		//1번 공유자원객체를 만든다.
		Calculator calculator = new Calculator();
		
		//작업 스레드 user1, user2 생성한다.
		User1 user1 = new User1("user1");//2번 //new 스레드 객체 생성
		user1.setCalculator(calculator);//3번
		user1.start();//4번 switching 발생 대기 //실행 대기 
		
		User2 user2 = new User2("user2");//5번
		user2.setCalculator(calculator);
		user2.start();//6번 switching 발생 대기
		
		System.out.println("메인 스레드 종료");

	}

}