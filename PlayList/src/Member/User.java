package Member;

public class User extends Customer {

	// construtor User 생성자 사용자

	public User(String name, int phone) {

		super(name, phone);

	}

	public User(String name, int phone, String address) {

		super(name, phone, address);

	}

}