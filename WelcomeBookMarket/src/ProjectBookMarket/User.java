package ProjectBookMarket;

public class User extends Customer { //사용자
	public User(String name, int phone) {
		super(name,phone);
	}

	public User(String name, int phone, String address) {
		super(name, phone, address);
	}
	
}
