package ProjectBookMarket;

import java.util.Scanner;

public class WelcomeBookMarket {
	public static final int NUM_BOOK = 3;
	public static final int NUM_ITEM = 8;
	public static Cartltem[] cartItem = new Cartltem[NUM_BOOK];
	public static int cartCount = 0;
	public static User user;
	public static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		String name; // 고객 이름
		int num; // 연락처
		int menunNum; // 메뉴 번호
		String[][] bookInfoList = new String[NUM_BOOK][NUM_ITEM]; // 도서 정보 목록 배열

		System.out.println("Book Market 고객 정보 입력");
		System.out.printf("이름을 입력해주세요:");
		name = scan.nextLine();
		System.out.printf("연락처를 입력해주세요:");
		num = scan.nextInt();
		
		user = new User(name,num);

		boolean stop = false;

		while (!stop) {

			menuIntroduction();

			System.out.print("메뉴 번호를 선택해주세요 :");
			menunNum = scan.nextInt();

			if (menunNum < 1 || menunNum > 9) {
				System.out.println("1부터 9까지의 숫자를 입력하세요.");
			} else {
				switch (menunNum) {
				case 1:
//						System.out.println("현재 고객 정보");
//						System.out.println("이름 :"+name + ", 연락처 :"+num);
					menuGuseInfo(name, num);
					break;
				case 2:
//						System.out.println("2. 장바구니 상품 목록 보기 :" );
					menuCarItemList();
					break;
				case 3:
//						System.out.println("3. 장바구니 비우기 :");
					menuCartClear();
					break;
				case 4:
//						System.out.println("4. 장바구니 항목 추가하기 :");
					menuCartAddItem(bookInfoList);
					break;
				case 5:
//						System.out.println("5. 장바구니에 항목 수량 줄이기");
					menuCarRemoveItemCount();
					break;
				case 6:
//						System.out.println("6. 장바구니 항목 삭제하기");
					menuCarRemoveItem();
					break;
				case 7:
//						System.out.println("7. 영수증 표기하기");
					menuCartBill();
					break;
				case 8:
//						System.out.println("8. 종료");
					menuExit();
					stop = true;
					break;
				case 9: 
					menuAdminLogin();
					break;

				}
			}
		} // end while

	}

	private static void menuIntroduction() {
		System.out.println("====================================");
		System.out.println("\t\t" + "Welcome to Book Market!");

		System.out.println("====================================");
		System.out.println(" 1. 고객 정보 확인하기 \t         4. 장바구니에 항목 추가하기");
		System.out.println(" 2. 장바구니 상품 목록 보기 \t 5. 장바구니에 항목 수량 줄이기");
		System.out.println(" 3. 장바구니 비우기 \t         6. 장바구니의 항목 삭제하기");
		System.out.println(" 7. 영수증 표시하기 \t         8. 종료");
		System.out.println(" 9. 관리자 로그인");

		System.out.println("====================================");

	}

	private static void menuGuseInfo(String name, int num) {
		System.out.println("현재 고객 정보 ");
//		System.out.println("이름 : " + name + " | 연락처 : " + num);
//		Customer customer = new Customer(name, num);
		System.out.println("이름 : " + user.getName() + " | 연락처 :" + user.getPhone());
		
	}

	private static void menuCarItemList() {
		System.out.println("2. 장바구니 상품 목록 :");
		System.out.println("---------------------------------------------------------------");
		System.out.println("    도서 ID \t|      수량 \t|       합계");
		for (int i = 0; i < cartCount; i++) {
			System.out.print("     " + cartItem[i].getBookID() + "  \t|  ");
			System.out.print("     " + cartItem[i].getQuantiy() + "  \t|  ");
			System.out.print("     " + cartItem[i].getTotalPrice());
			System.out.println("     ");
		}
		System.out.println("---------------------------------------------------------------");
	}

	private static void menuCartClear() {
		System.out.println("3. 장바구니 비우기");

	}

	// 장바구니에 항목 추가하기
	public static void menuCartAddItem(String[][] book) {

		bookList(book); // 도서 정보가 저장
		// 도서 정보 출력
		for (int i = 0; i < NUM_BOOK; i++) {
			for (int j = 0; j < NUM_ITEM; j++)
				System.out.print(book[i][j] + " | ");
			System.out.println("");
		}
		boolean stop = false;

		while (!stop) {
			Scanner scan = new Scanner(System.in);
			System.out.print("장바구니에 추가할 도서의 ID를 입력하세요 :");
			String inputStr = scan.nextLine();

			boolean flag = false; // 일치 여부
			int numId = -1; // 인덱스 번호

			for (int i = 0; i < NUM_BOOK; i++) {
				// 인덱스 번호와 일치 여부 변수의 값을 변경한다.
				if (inputStr.equals(book[i][0])) {
					numId = i;
					flag = true;
					break;
				}
			}
			// 일치하면 장바구니 추가 여부를 묻기
			if (flag) {
				System.out.println("장바구니에 추가하겠습니까? Y | N");
				inputStr = scan.nextLine();

				if (inputStr.toUpperCase().equals("Y") || inputStr.toUpperCase().equals("y")) {
					System.out.println(book[numId][0] + "도서가 장바구니에 추가되었습니다.");
					// 장바구니에 넣기
					if (!isCartInBook(book[numId][0])) {
						cartItem[cartCount++] = new Cartltem(book[numId]);
					}
				}
				stop = true;
			} else
				System.out.println("다시 입력해 주세요");
		}
	}

	private static void menuCarRemoveItemCount() {
		System.out.println("5. 장바구니에 항목 수량 줄이기");

	}

	private static void menuCarRemoveItem() {
		System.out.println("6. 장바구니의 항목 삭제하기");

	}

	private static void menuCartBill() {
		System.out.println("7. 영수증 표시하기");

	}

	private static void menuExit() {
		System.out.println("8. 종료");

	}

	private static void bookList(String[][] book) {
		book[0][0] = "book1";
		book[0][1] = "ISBN 978-89-01-26726-5";

		book[0][2] = "빅 히스토리";
		book[0][3] = "33000";

		book[0][4] = "데이비드 크리스천";

		book[0][5] = "우주와 지구, 인간을 하나로 잇는 새로운 역사";

		book[0][6] = "인문 교양";
		book[0][7] = "2022/12/23";

		book[1][0] = "book2";
		book[1][1] = "ISBN 979-11-6921-062-1";
		book[1][2] = "SICP";
		book[1][3] = "45000";

		book[1][4] = "헤럴드 에이블슨, 류광";

		book[1][5] = "컴퓨터 프로그래밍의 구조와 해석";

		book[1][6] = "개발 방법론";
		book[1][7] = "2022/12/30";

		book[2][0] = "book3";
		book[2][1] = "ISBN 978-89-6626-366-0";
		book[2][2] = "러스트 프로그래밍";
		book[2][3] = "35000";

		book[2][4] = "팀 맥나마라, 장연호";

		book[2][5] = "러스트는 시스템 프로그래밍에 적합한 언어";

		book[2][6] = "프로그래밍 언어";
		book[2][7] = "2022/07/08";
	}

	// 도서 ID 확인
	public static boolean isCartInBook(String bookId) {
		boolean flag = false;
		for (int i = 0; i < cartCount; i++) {
			if (bookId == cartItem[i].getBookID()) {
				cartItem[i].setQuantiy(cartItem[i].getQuantiy() + 1);
				flag = true;
			}
		}
		return flag;
	}
	
	// 관리자 로그인 정보 확인 매서드
	public static void menuAdminLogin() {
		System.out.println("관리자 정보를 입력하세요");
		
		Scanner scan = new Scanner(System.in);
		System.out.print("아이디 : ");
		String adminId = scan.next();
		
		System.out.print("비밀번호 : ");
		String adminPW = scan.next();
		
		Admin admin = new Admin(user.getName(),user.getPhone());
		if(adminId.equals(admin.getId()) && adminPW.equals(admin.getPassword()))  {
			System.out.println("이름 :"+admin.getName() + "|\t 연락처 :"+ admin.getPhone());
			System.out.println("아이디 :"+admin.getId() + "|\t 비밀번호 :"+ admin.getPassword());
		}else {
			System.out.println("관리자 정보가 일치하지 않습니다.");
		}
	}
}
