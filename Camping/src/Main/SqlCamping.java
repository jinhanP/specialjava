package Main;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import javax.management.RuntimeErrorException;

import java.sql.Connection;

public class SqlCamping {
	private static final SqlCamping ConnectDatabase = null;
	public static Scanner scan = new Scanner(System.in);

	// 1번 로드, 2번 연결 연결값 리턴
	public static Connection makeConnection() {
		String ur1 = "jdbc:oracle:thin:@127.0.0.1:1521/xe";
		String user = "hr";
		String pwd = "hr";
		Connection connection = null;
		try {
			// 1. 드라이버 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("OracleDriver 적재 성공");
			// 2. 오라클데이터베이스 연결
			connection = DriverManager.getConnection(ur1, user, pwd);
			System.out.println("오라클 접속 성공");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("오라클 적재 실패");
		} catch (SQLException e) {
			System.out.println("오라클 접속 실패");
			e.printStackTrace();
		}
		return connection;
	}

	public static void main(String[] args) {
		boolean stopFlag = false;
		while (!stopFlag) {
			System.out.println("1: select(보여주기) 2: insert(입력하기) 3:update(수정하기) 4:delete(삭제하기) 5: exit(종료)");
			System.out.printf(">>");
			int num = scan.nextInt();
			scan.nextLine();
			switch (num) {
			case 1:
				campingSelect();
				break;
			case 2:
				campingInsert();
				break;
			case 3:
				campingUpdate();
				break;
			case 4:
				campingDelete();
				break;
			case 5:
				stopFlag = true;
			}
			System.out.println("The end");
		}
	}

	private static void campingSelect() {
		Connection con = SqlCamping.makeConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// 3. CURD 실행
			String query = String.format("SELECT * FROM camping");
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			// 4. ResultSet을 화면에 출력
			while (rs.next()) {
				int campingId = rs.getInt("CAMPING_ID");
				String title = rs.getString("TITLE");
				String publisher = rs.getString("PUBLISHER");
				String year = rs.getString("YEAR");
				int price = rs.getInt("PRICE");
				String data = String.format("%-10d %-10s \t%-10s \t%-10s \t %-10d \n", campingId, title, publisher,
						year, price);
				System.out.println(data);
			}

		} catch (SQLException e) {
			System.out.println("prepareStatement 오류");
		} finally {
			try {
				rs.close();
				pstmt.close();
				con.close();
			} catch (SQLException e) {
			}
		}
	}

	public static void campingInsert() {
		// 1번 로드, 2번 연결 연결값 리턴
		Connection con = SqlCamping.makeConnection();

		PreparedStatement pstmt = null;
		try {
			System.out.println("텐트 아이디 입력>>");
			int camping = scan.nextInt();
			System.out.println("텐트이름 입력>>");
			String title = scan.nextLine().trim();
			scan.nextLine();
			System.out.println("회사명 입력>>");
			String publisher = scan.nextLine().trim();
			System.out.println("년도>>");
			String year = scan.nextLine().trim();
			System.out.println("가격>>");
			int price = scan.nextInt();
			scan.nextLine();
			// 3. CURD 실행
			String query = String.format("INSERT INTO camping VALUES (camping_id_seq.nextval, ?, ?, ?, ?)");
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, title);
			pstmt.setString(2, publisher);
			pstmt.setString(3, year);
			pstmt.setInt(4, price);
			int count = pstmt.executeUpdate();
			// 4. count 체크
			if (count != 1) {
				System.out.println("Insert 오류발생");
			} else {
				System.out.println("Insert 성공");
			}
		} catch (SQLException e) {
			System.out.println("Statement 오류");
		}finally {
			try {
				pstmt.close();
				con.close();
			} catch (SQLException e) {}
		}
	}

	private static void campingUpdate() {
		// 수정 대상을 보여준다
		campingSelect();

		Connection con = SqlCamping.makeConnection();
		PreparedStatement pstmt = null;
		// 3. crud 실행
		try {
			System.out.printf("캠핑텐트 아이디 입력>>");
			int campingId = scan.nextInt();
			scan.nextLine();
			System.out.printf("수정 캠핑텐트 입력>>");
			String title = scan.nextLine().trim();
			System.out.printf("캠핑텐트 회사명 입력>>");
			String publisher = scan.nextLine().trim();
			System.out.printf("캠핑텐트 연도 입력>>");
			String year = scan.nextLine().trim();
			System.out.printf("캠핑텐트 가격 입력>>");
			int price = scan.nextInt();
			scan.nextLine();

			String query = String
					.format("update camping set title = ?, publisher = ? , year = ? , price = ? where camping_id = ? ");
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, title);
			pstmt.setString(2, publisher);
			pstmt.setString(3, year);
			pstmt.setInt(4, price);
			pstmt.setInt(5, campingId);

			int count = pstmt.executeUpdate();
			// 4. count 체크
			if (count == 0) {
				System.out.printf("camping_id %d Update 오류 발생 \n", campingId);
			} else {
				System.out.printf("camping_id %d Update 성공 \n", campingId);
			}
		} catch (SQLException e) {
			System.out.println("statement 오류");
		} finally {
			try {
				pstmt.close();
				con.close();
			} catch (SQLException e) {
			}finally {
				try {
					
					pstmt.close();
					con.close();
				} catch (SQLException e) {}
			}
		}
	}

	private static Camping campingSelectCampingId(int campingId) {
		Connection con = SqlCamping.makeConnection();
		Statement pstmt = null;
		ResultSet rs = null;
		Camping camping = null;
		// 3. curd 실행
		try {
			pstmt = con.createStatement(); // umpor.java.sql할것
			String query = String.format("select * from camping where camping_id = %d", campingId);
			rs = pstmt.executeQuery(query);

			// 4. ResultSet 화면출력
			if (rs.next()) {
				int _campingID = rs.getInt("CAMPING_ID");
				String title = rs.getString("TITLE");
				String publisher = rs.getString("PUBLISHER");
				String year = rs.getString("YEAR");
				int price = rs.getInt("PRICE");

				camping = new Camping(_campingID, title, publisher, year, price);
				System.out.println(camping.toString());
			}
		} catch (SQLException e) {
			System.out.println("statement 오류");
		} finally {
			try {
				rs.close();
				pstmt.close();
				con.close();
			} catch (SQLException e) {

			}
		}
		return camping;
	}

	private static void campingDelete() {
		// 삭제 대상을 보여준다
		campingSelect();
		Connection con = SqlCamping.makeConnection();
		PreparedStatement pstmt = null;
		try {
			System.out.printf("삭제할 캠핑텐트 아이디 입력>>");
			int campingId = scan.nextInt();
			scan.nextLine();

			String query = String.format("delete from camping where camping_id  = ?");
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, campingId);

			int count = pstmt.executeUpdate();
			// 4. count 체크
			if (count == 0) {
				System.out.printf("camping_id %d는 삭제 대상이 아닙니다.\n", campingId);
			} else {
				System.out.println("delete 성공");
			}
		} catch (SQLException e) {
			System.out.println("statement 오류");
		} finally {
			try {
				pstmt.close();
				con.close();
			} catch (SQLException e) {
			}
		}
	}
}
