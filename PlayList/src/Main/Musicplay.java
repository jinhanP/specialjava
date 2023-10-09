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
   
 public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String userName; // name
        int userMobile; // phoneNum
        int numberSelection; // menu number
        BufferedReader in = null;
		BufferedWriter out = null;
		ServerSocket listener = null;
		Socket socket = null;
		Scanner scanner = new Scanner(System.in);
        ArrayList<Music> musicInfoList;
        System.out.println("Music Store Market");
//        try {
//        	Scanner scan = new Scanner(System.in);
//			listener = new ServerSocket(9999); // 서버 소켓 생성
//			System.out.println("연결을 기다리고 있습니다.....");
//			socket = listener.accept(); // 클라이언트로부터 연결 요청 대기
//			System.out.println("연결되었습니다.");
//			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//			out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
//			while (true) {
//				String inputMessage = in.readLine(); // 클라이언트로부터 한 행 읽기
//				if (inputMessage.equalsIgnoreCase("bye")) {
//					System.out.println("클라이언트에서 bye로 연결을 종료하였음");
//					break; // "bye"를 받으면 연결 종료
//				}
//				System.out.println("클라이언트: " + inputMessage);
//				System.out.print("보내기>>"); // 프롬프트
//				String outputMessage = scanner.nextLine(); // 키보드에서 한 행 읽기
//				out.write(outputMessage + "\\n"); // 키보드에서 읽은 문자열 전송
//				out.flush(); // out의 스트림 버퍼에 있는 모든 문자열 전송
//			}
//		} catch (IOException e) {
//			System.out.println(e.getMessage());
//		} finally {
//			try {
//				scanner.close(); // scanner 닫기
//				socket.close(); // 통신용 소켓 닫기
//				listener.close(); // 서버 소켓 닫기
//			} catch (IOException e) {
//				System.out.println("클라이언트와 채팅 중 오류가 발생했습니다.");
//			}
//		}

        System.out.println("Music Market 고객 정보 입력");
        System.out.print("당신의 이름을 입력하세요 : "); // input name
        userName = input.next();
        System.out.print("연락처를 입력하세요 : "); // input phoneNum
        userMobile = input.nextInt();

       user = new User(userName,userMobile);


        boolean quit = false; // loop control


        while (!quit) {
            menuIntroduction();
            try{
                System.out.print("메뉴 번호를 선택해주세요 :");
                numberSelection = input.nextInt();
                if (numberSelection < 1 || numberSelection > 10) {
                    System.out.println("1부터 8까지의 숫자를 입력하세요.");
                } else {
                    switch (numberSelection) {
                        case 1:
                            // user info
                            menuGuestInfo();
                            break;
                        case 2:
                            // view item in cartList
                            menuCartItemList();
                            break;
                        case 3:
                            // clear cart
                            menuCartClear();
                            break;
                        case 4:
                            // add item in cart
                        	musicInfoList = new ArrayList<>(); // music instance list
                            menuCartAddItem(musicInfoList);
                            break;
                        case 5:
                            // decrease number of item in cart
                            menuCartRemoveItemCount();
                            break;
                        case 6:
                            // remove item in cart
                            menuCartRemoveItem();
                            break;
                        case 7:
                            // print bill
                            menuCartBill();
                            break;
                        case 8:
                            // exit
                            menuExit();
                            quit = true;
                            break;
                        case 9:
                            // check admin info
                            menureserve();
                            break;
                        case 10:
                        	// check admin info
                        	menuAdminLogin();
                        	break;
                    }

                }
            }catch(CartException e){
                System.out.println(e.getMessage());
                quit = true;

            }
            catch(Exception e){
                System.out.println("잘못된 메뉴 선택으로 종료합니다.");
                quit = true;
            }



        }
    }





	// print menu info
    public static void menuIntroduction() {
        System.out.println("***************************************************");
        System.out.println("\t\t" + "Music Play List Store");
        System.out.println("***************************************************");
        System.out.println(" 1. 고객 정보 확인하기 \t4. Playlist에 항목 추가하기");
        System.out.println(" 2. Playlist 상품 목록 보기\t5. Playlist에 항목 수량 줄이기");
        System.out.println(" 3. Playlist 비우기 \t6. Playlist에 항목 삭제하기");
        System.out.println(" 7. 영수증 표시하기 \t8. 종료");
        System.out.println(" 9. 노래 상품 예약  \t\t10. 관리자 로그인");
        System.out.println("***************************************************");

    }
    // print guestInfo
    public static void menuGuestInfo() {
        System.out.println("현재 고객 정보 : ");

        System.out.println("이름 : " + user.getName() + ", 연락처 : " +
                user.getPhone());
    }

    // print Cart ItemList
    public static void menuCartItemList() {
        if (Cart.cartCount >= 0) {
            cart.printCart();
        }
    }

    public static void menuCartClear() throws CartException{
        if (Cart.cartCount == 0) {
        //System.out.println("Playlist에 항목이 없습니다");
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

    public static void menuCartAddItem(ArrayList<Music> music) {
    	musicList(music);

        cart.printMusicList(music);
        boolean quit = false;
        while (!quit){
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
                if (inputStr.equalsIgnoreCase("Y") ) {
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

    public static void menuCartRemoveItemCount() throws CartException  {
        if (Cart.cartCount == 0) {
            throw new CartException("Playlist에 항목이 없습니다");
        }
       CartItem music;
        while(true){
        System.out.print("Playlist에서 수량을 줄일 노래 Id를 입력하세요.");
        Scanner input = new Scanner(System.in);
        String str = input.nextLine();

            Optional<CartItem> musicId = cart.cartItem.stream().
                    filter(item -> (item.getMusicID().equals(str))).
                    findAny();
            if(!(musicId.isPresent())){
                System.out.println("잘못된 id를 입력해주셨습니다. 다시 입력해주세요.");
                continue;
            }

            music = musicId.get();
            break;
        }


            if(music.getQuantity()>=2){
            	music.setQuantity(music.getQuantity()-1);
            }
            else {
                System.out.println("해당 노래는 현재 1개만 있습니다. 항목을 지웁니다.");
                cart.cartItem.remove(music);
            }




    }

    public static void menuCartRemoveItem() throws CartException {
        if (Cart.cartCount == 0) {
            throw new CartException("Playlist에 항목이 없습니다");
        }else {
            menuCartItemList();
            boolean quit = false;
            while (!quit) {
                System.out.print("Playlist에서 삭제할 노래 ID 를 입력하세요 :");
                Scanner input = new Scanner(System.in);
                String str = input.nextLine();
                boolean flag = false;
                int numId = -1;
                for (int i = 0; i < Cart.cartCount; i++) {
                    if (str.equals(cart.cartItem.get(i).getMusicID())) {
                        numId = i;
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    System.out.println("Playlist의 항목을 삭제하겠습니까? Y | N ");
                    str = input.nextLine();
                    if (str.equalsIgnoreCase("Y") ){
                        System.out.println(cart.cartItem.get(numId).getMusicID() + "노래가 Playlist에서 삭제되었습니다.");
                    }

                    quit = true;
                } else {
                    System.out.println("다시 입력해 주세요");
                }
            }
        }
    }



    public static void menuExit() {
        System.out.println("8. 종료");
    }
    public static void musicList(ArrayList<Music> music) {
        setFileToMusicList(music);
    }
    // add music in Cart
    public  static  boolean isCartInMusic(String musicId){

        return cart.isCartInMusic(musicId);
    }
    //print menu reserve
    private static void menureserve() {
    	Scanner scan = new Scanner(System.in);
    	int num;
        String name = null;
        String mail = null;
 
        while (true) {
            System.out.println("노래 상품 예약하세요(1~3)");

            // 예약할떄 입력
            System.out.print("이메일>> ");
            mail = scan.next();
            System.out.print("이름>> ");
            name = scan.next();
            System.out.print("번호>> ");
            num = scan.nextInt();
            if (num > 3) { 
               System.out.println("예약실패");
            }else {
            	System.out.printf(mail+"\n"+name +","+ num+ "번\t" +"예약완료");
            }
            System.out.println();
            return;
        }
    }

	// check admin info, add new music
    public  static void  menuAdminLogin(){
        System.out.println("관리자 정보를 입력하세요");
        Scanner input = new Scanner(System.in);
        System.out.print("아이디 : ");
        String adminId = input.next();
        System.out.print("비밀번호 : ");
        String adminPW = input.next();
        Admin admin = new Admin(user.getName(), user.getPhone());

        if (adminId.equals(admin.getId()) &&
                adminPW.equals(admin.getPassword())) {
            System.out.println("이름 : " + admin.getName() + ", 연락처 : " +
                    admin.getPhone());
            System.out.println("아이디 : " + admin.getId() + ", 비밀번호 : " +
                    admin.getPassword());
            String[] writeMusic = new String[8];
            System.out.println("노래 정보를 추가하겠습니까? Y | N "); // input y add new music
            String str = input.next();
            if (str.equalsIgnoreCase("Y")) {
                Date date = new Date();
                SimpleDateFormat formatter = new
                        SimpleDateFormat("yyMMddhhmmss");  // Today`s date
                String strDate = formatter.format(date);
                writeMusic[0] = "music" + strDate;  // info of new music
                System.out.println("노래 ID : " + writeMusic[0]);
                System.out.println("번호 부분만 입력 예) 123-12-12345-01-1");
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
                try {                                                //received information in a music to save it
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
                System.out.println("이름 : " + admin.getName() + ", 연락처 : " +
                        admin.getPhone());
                System.out.println("아이디 : " + admin.getId() + ", 비밀번호 : " +
                        admin.getPassword());
            }




        } else {
            System.out.println("관리자 정보가 일치하지 않습니다.");
        }


    }
    public static void menuCartBill() throws CartException{
        if(Cart.cartCount == 0){
            throw new CartException("Playlist에 항목이 없습니다");
        }else{
            System.out.println("다운받을 분은 고객정보와 같습니까? Y | N ");
            Scanner input = new Scanner(System.in);
            String str = input.nextLine();
            if(str.equalsIgnoreCase("Y")){
                System.out.println("이메일 입력해주세요");
                String address = input.nextLine();
                // after input shipping date, input order info in printBill

                printBill(user.getName(), String.valueOf(user.getPhone()),
                        address);
            }
            // input n, input new info
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
    // print order info(name, phone, address, shipping date)
    public static void printBill(String name, String phone, String address){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        String strDate = formatter.format(date);
        System.out.println();
        System.out.println("---------------다운 받을 고객 정보----------------");
        System.out.println("고객명 : " + name + " \t\t 연락처 : " + phone);
        System.out.println("이메일 : " + address + "\t 다운로드 : " + strDate);
        // print item in cart
        cart.printCart();
        // calculate item
        int sum = 0;
        for (int i = 0; i < Cart.cartCount; i++) {
            sum += cart.cartItem.get(i).getTotalPrice();
        }
        System.out.println("\t\t\t 주문 총금액 : " + sum + "원\n");
        System.out.println("----------------------------------------------");
        System.out.println();

    }
    // check number of music

    public static void  setFileToMusicList(ArrayList<Music> musicList){
        try {
            FileReader fr = new FileReader("music.txt");
            BufferedReader reader = new BufferedReader(fr);

            String musicId;
            String[] readMusic = new String[8]; // number of music`s info
            while ((musicId = reader.readLine()) != null) {
                if (musicId.contains("music")) {
                    readMusic[0] = musicId;
                    readMusic[1] = reader.readLine();
                    readMusic[2] = reader.readLine();
                    readMusic[3] = reader.readLine();
                    readMusic[4] = reader.readLine();
                    readMusic[5] = reader.readLine();
                    readMusic[6] = reader.readLine();
                    readMusic[7] = reader.readLine();
                }
                Music music = new Music(readMusic[0], readMusic[1], readMusic[2],
                        Integer.parseInt(readMusic[3]), readMusic[4], readMusic[5], readMusic[6],
                        readMusic[7]);
                musicList.add(music);
            }
            reader.close();
            fr.close();


        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
