package Member;

public class Admin extends Customer { // 관리자 생성

	private String id = "Admin";

	private String password = "Admin1234";

	public Admin(String name, int phone) {

		super(name, phone);

	}

// get admin id, password 관리자 ID, 암호

	public String getId() {

		return id;

	}

	public String getPassword() {

		return password;

	}

}