package Main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.Scanner;

import Cart.Cart;
import Cart.CartItem;
import Exceptoin.CartException;
import Member.Admin;
import Member.User;
import Musicitem.Music;

public class Musicplay {
	static Cart cart = new Cart();
	static User user; // user

	public static void main(String[] args) throws IOException { // 예외 처리
		Scanner input = new Scanner(System.in);
		String userName; // name
		int userMobile; // phoneNum
		int numberSelection; // menu number
		BufferedReader in = null;
		BufferedWriter out = null;
		Socket socket = null;
		Scanner scan = new Scanner(System.in); // scanner 객체 생성

		Scanner scanner = new Scanner(System.in);
		ArrayList<Music> musicInfoList;
		System.out.println("Music Store Market");
		try {
			socket = new Socket(" ", 9999); // 클라이언트 소켓 생성.서버에 연결
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			while (true) {
				System.out.println("보내기>>"); // 프롬프트
				String outputMessage = scan.nextLine(); // 키보드에서 한 행 읽기
				if (outputMessage.equals("bye")) {
					out.write(outputMessage + "\n"); // "bey"문자열 전송
					out.flush();
					break; // 사용자가 "bye"를 입력한 경우 서버로 전송 후 실행 종료
				}

				out.write(outputMessage + "\n");// 문자열 전송
				out.flush();// out의 스트림 버퍼에 있는 모든 문자열 전송
				String inputMessage = in.readLine();// 서버로부터 한 행 수신
				System.out.println("서버:" + inputMessage);

				System.out.println("Music Market 고객 정보 입력");
				System.out.print("당신의 이름을 입력하세요 : "); // input name
				userName = input.next();
				System.out.print("연락처를 입력하세요 : "); // input phoneNum
				userMobile = input.nextInt();

				user = new User(userName, userMobile);

				boolean quit = false; // loop control 그룹을 여러 번 실행

				while (!quit) {
					menuIntroduction();
					try {
						System.out.print("메뉴 번호를 선택해주세요 :");
						numberSelection = input.nextInt();
						if (numberSelection < 1 || numberSelection > 10) {
							System.out.println("1부터 8까지의 숫자를 입력하세요.");
						} else {

							switch (numberSelection) {
							case 1:
								// user info 게스트 정보
								menuGuestInfo();
								break;
							case 2:
								// view item in cartList 카트 목록에서 항목 보기
								menuCartItemList();
								break;
							case 3:
								// clear cart 장바구니 지우기
								menuCartClear();
								break;
							case 4:
								// add item in cart 장바구니 물건을 추가하다
								musicInfoList = new ArrayList<>(); // music instance list
								menuCartAddItem(musicInfoList);
								break;
							case 5:
								// decrease number of item in cart 장바구니 품목 수를 줄이다
								menuCartRemoveItemCount();
								break;
							case 6:
								// print bill 청구서
								menuCartBill();
								break;
							case 7:
								// check admin info 관리자 정보를 확인
								menureserve();
								break;
							case 8:
								// Login 로그인
								menuAdminLogin();
								break;
							case 9:
								// exit 나가기
								quit = true;
								menuExit();
								break;
							}
						}

					} catch (CartException e) { // 장바구니 예외처리
						System.out.println(e.getMessage());
						quit = true;

					} catch (Exception e) {
						System.out.println("잘못된 메뉴 선택으로 종료합니다.");
						quit = true;
					}

				}
			}
		} catch (UnknownHostException e) { // 알 수 없는 호스트 예외
			System.out.println(e.getMessage());
		} finally {
			try {
				scan.close();
				if (socket != null)
					socket.close();// 클라이언트 소켓 닫기
			} catch (IOException e) {
				System.out.println("서버와 채팅 중 오류가 발생했습니다");
			}
		}
	}

	// print menu info 메뉴 정보 소개
	public static void menuIntroduction() {
		System.out.println("***************************************************");
		System.out.println("\t\t" + "Music Play List Store");
		System.out.println("***************************************************");
		System.out.println(" 1. 고객 정보 확인하기 \t4. Playlist에 항목 추가하기");
		System.out.println(" 2. Playlist 상품 목록 보기\t5. Playlist에 항목 삭제");
		System.out.println(" 3. Playlist 모든 항목 삭제\t6. 영수증 표시하기");
		System.out.println(" 7. 노래 상품 예약 \t\t8. 관리자 로그인");
		System.out.println(" 9. 종료");
		System.out.println("***************************************************");

	}

	// print guestInfo 게스트 정보 소개
	public static void menuGuestInfo() {
		System.out.println("현재 고객 정보  ");

		System.out.println("이름 : " + user.getName() + " , 연락처 : " + user.getPhone());
	}

	// print Cart ItemList 항목 목록
	public static void menuCartItemList() {
		if (Cart.cartCount >= 0) {
			cart.printCart();
		}
	}

	public static void menuCartClear() throws CartException { // 장바구니 항목 삭제
		if (Cart.cartCount == 0) {
			throw new CartException("Playlist에 항목이 없습니다");
		} else {
			System.out.println("Playlist에 모든 항목을 삭제하겠습니까? Y | N ");
			Scanner input = new Scanner(System.in);
			String str = input.nextLine();
			if (str.equalsIgnoreCase("Y")) {
				System.out.println("Playlist에 모든 항목을 삭제했습니다");
				cart.deleteMusic();
			}
		}
	}

	public static void menuCartAddItem(ArrayList<Music> music) { // Playlist 추가
		musicList(music);

		cart.printMusicList(music);
		boolean quit = false;
		while (!quit) {
			Scanner input = new Scanner(System.in);
			System.out.print("Playlist에 추가할 노래의 ID를 입력하세요:");
			String inputStr = input.nextLine();
			boolean flag = false; // 일치 여부
			int numId = -1; // 인덱스 번호
			for (int i = 0; i < music.size(); i++) {
				if (inputStr.equals(music.get(i).getMusicId())) {
					numId = i;
					flag = true;
					break;
				}
			}
			// 일치하면 Playlist 추가 여부를 묻는다.
			if (flag) {
				System.out.println("Playlist에 추가하겠습니까? Y | N ");
				inputStr = input.nextLine();
				if (inputStr.equalsIgnoreCase("Y")) {
					System.out.println(music.get(numId).getMusicId() + " 노래가 Playlist에 추가되었습니다.");

					if (!isCartInMusic(music.get(numId).getMusicId())) {
						cart.insertMusic(music.get(numId));
					}
				}
				quit = true;

			} else
				System.out.println("다시 입력해 주세요");

		}

	}

	// Playlist 항목 수량 줄이기
	public static void menuCartRemoveItemCount() throws CartException {
		if (Cart.cartCount == 0) {
			throw new CartException("Playlist에 항목이 없습니다");
		}
		CartItem music;
		while (true) {
			System.out.print("Playlist에서 수량을 줄일 노래 Id를 입력하세요.");
			Scanner input = new Scanner(System.in);
			String str = input.nextLine();

			Optional<CartItem> musicId = cart.cartItem.stream().filter(item -> (item.getMusicID().equals(str)))
					.findAny();
			if (!(musicId.isPresent())) {
				System.out.println("잘못된 Id를 입력해주셨습니다. 다시 입력해주세요.");
				continue;
			}

			music = musicId.get();
			break;
		}

		if (music.getQuantity() >= 2) {
			music.setQuantity(music.getQuantity() - 1);
		} else {
			System.out.println("노래 삭제완료.");
			cart.cartItem.remove(music);
		}

	}


	public static void menuExit() { // 메뉴 종료

		System.out.println("메뉴 종료");
	}

	public static void musicList(ArrayList<Music> music) { // MusicList 배열 목록
		setFileToMusicList(music);
	}

	// add music in Cart 장바구니 음악 추가
	public static boolean isCartInMusic(String musicId) {

		return cart.isCartInMusic(musicId);
	}

	// print menu reserve 메뉴 예약
	private static void menureserve() {
		Scanner scan = new Scanner(System.in);
		int num;
		String name = null;
		String mail = null;

		while (true) {
			System.out.println("노래 상품 예약하세요");

			// 예약할떄 입력
			System.out.print("이메일>> ");
			mail = scan.next();
			System.out.print("이름>> ");
			name = scan.next();
			System.out.print("노래 번호(1~3)>> ");
			num = scan.nextInt();
			if (num > 3) {
				System.out.println("예약실패");
			} else {
				System.out.printf(mail + "\n" + name + "," + num + "번\t" + "예약완료", "Playlist1");
			}
			System.out.println();
			return;
		}
	}

	// check admin info, add new music 관리자 정보 확인, 새 음악 추가
	public static void menuAdminLogin() {
		System.out.println("관리자 정보를 입력하세요");
		Scanner input = new Scanner(System.in);
		System.out.print("아이디 : ");
		String adminId = input.next();
		System.out.print("비밀번호 : ");
		String adminPW = input.next();
		Admin admin = new Admin(user.getName(), user.getPhone());

		if (adminId.equals(admin.getId()) && adminPW.equals(admin.getPassword())) {
			System.out.println("이름 : " + admin.getName() + ", 연락처 : " + admin.getPhone());
			System.out.println("아이디 : " + admin.getId() + ", 비밀번호 : " + admin.getPassword());
			String[] writeMusic = new String[8];
			System.out.println("노래 정보를 추가하겠습니까? Y | N "); // input y add new music 새로운 음악을 입력
			String str = input.next();
			if (str.equalsIgnoreCase("Y")) {
				Date date = new Date();
				SimpleDateFormat formatter = new SimpleDateFormat("yyMMddhhmmss"); // Today`s date 오늘날짜
				String strDate = formatter.format(date);
				writeMusic[0] = "music" + strDate; // info of new music 새로운 음악에 대한 정보
				System.out.println("노래 ID :" + writeMusic[0]);
				String str1 = input.nextLine();
				System.out.print("SING : ");
				writeMusic[1] = input.nextLine();
				writeMusic[1] = "SING " + writeMusic[1];
				
				System.out.print("곡명 : ");
				writeMusic[2] = input.nextLine();
				System.out.print("가격(숫자) : ");
				writeMusic[3] = input.nextLine();
				System.out.print("가수 : ");
				writeMusic[4] = input.nextLine();
				System.out.print("소속사 : ");
				writeMusic[5] = input.nextLine();
				System.out.print("장르 : ");
				writeMusic[6] = input.nextLine();
				System.out.print("발매일(예:2023/01/01): ");
				writeMusic[7] = input.nextLine();

				try { // received information in a music to save it 저장하기 위해 음악으로 정보를 받기
					FileWriter fw = new FileWriter("music.txt", true);

					for (int i = 0; i < 8; i++) {
						fw.write(writeMusic[i] + "\n");
					}

					fw.close();
					System.out.println("새 노래 정보가 저장되었습니다.");
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			} else {
				System.out.println("이름 : " + admin.getName() + ", 연락처 : " + admin.getPhone());
				System.out.println("아이디 : " + admin.getId() + ", 비밀번호 : " + admin.getPassword());
			}

		} else {
			System.out.println("관리자 정보가 일치하지 않습니다.");
		}

	}

	public static void menuCartBill() throws CartException {
		if (Cart.cartCount == 0) {
			throw new CartException("Playlist에 항목이 없습니다");
		} else {
			System.out.println("다운받을 분은 고객정보와 같습니까? Y | N ");
			Scanner input = new Scanner(System.in);
			String str = input.nextLine();
			if (str.equalsIgnoreCase("Y")) {
				System.out.println("이메일 입력해주세요");
				String address = input.nextLine();
				// 이름,Download,email input shipping date, input order info in printBill
				// 이름,Download,이메일 입력후 주문정보를 입력합니다

				printBill(user.getName(), String.valueOf(user.getPhone()), address);
			}
			// input n, input new info 새 정보 입력
			else {
				System.out.print("다운받을 고객명을 입력하세요 ");
				String name = input.nextLine();
				System.out.print("다운받을 고객의 연락처를 입력하세요 ");
				String phone = input.nextLine();
				System.out.print("다운받을 고객의 이메일을 입력해주세요 ");
				String address = input.nextLine();
				// 주문 처리 후 영수증 출력 메서드 호출
				printBill(name, phone, address);
			}

		}
	}

	// print order info(name, phone, address, shipping date) 주문정보 출력(이름, 연락처, 이메일,
	// 다운로드)
	public static void printBill(String name, String phone, String address) {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		String strDate = formatter.format(date);
		System.out.println();
		System.out.println("---------------다운 받을 고객 정보----------------");
		System.out.println("고객명 : " + name + " \t\t 연락처 : " + phone);
		System.out.println("이메일 : " + address + "\t 다운로드 : " + strDate);
		// print item in cart 장바구니 물건 생성
		cart.printCart();
		// calculate item 항목을 계산
		int sum = 0;
		for (int i = 0; i < Cart.cartCount; i++) {
			sum += cart.cartItem.get(i).getTotalPrice();
		}
		System.out.println("\t\t\t 주문 총금액 : " + sum + "원\n");
		System.out.println("----------------------------------------------");
		System.out.println();

	}

	// 노래 정보 목록을 파일에서 읽어 노래의 개수를 알아내는 메서드
	private static int totalFileToMusicList() {
		try {
			FileReader fr = new FileReader("music.txt");
			BufferedReader reader = new BufferedReader(fr);

			String str;
			int num = 0; // 노래의 개수
			while ((str = reader.readLine()) != null) {
				if (str.contains("SING")) {
					++num;
				}
			}
			reader.close();
			fr.close();
			return num;
		} catch (Exception e) {
			System.out.println(e);
		}
		return 0;
	}

	// check number of music 음악 수를 체크

	public static void setFileToMusicList(ArrayList<Music> musicList) {
		try {
			FileReader fr = new FileReader("music.txt");
			BufferedReader reader = new BufferedReader(fr);

			String musicId;
			String[] readMusic = new String[8]; // number of music`s info 음악 정보의 수
			int count = 0;

			while ((musicId = reader.readLine()) != null) {

				readMusic[0] = musicId;
				readMusic[1] = reader.readLine();
				;
				readMusic[2] = reader.readLine();
				;
				readMusic[3] = reader.readLine();
				;
				readMusic[4] = reader.readLine();
				;
				readMusic[5] = reader.readLine();
				;
				readMusic[6] = reader.readLine();
				;
				readMusic[7] = reader.readLine();
				;

				Music music = new Music(readMusic[0], readMusic[1], readMusic[2], Integer.parseInt(readMusic[3]),
						readMusic[4], readMusic[5], readMusic[6], readMusic[7]);

				musicList.add(music);
			}
			reader.close();
			fr.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
}