package ProjectBookMarket;

public class Cartltem {
	private String[] itemBook = new String[8];
	private String bookID; //도서 ID
	private int quantity;	//도서 수량
	private int totalPrice;	//도서 합계 가격
	
	
	public Cartltem() {
		
	}
	
	public Cartltem(String[] itemBook) {
		this.itemBook = itemBook;
		this.bookID =  itemBook[0];
		this.quantity = 1;
		updateTotalPrice();
	}

	public String[] getItemBook() {
		return itemBook;
	}

	public void setItemBook(String[] itemBook) {
		this.itemBook = itemBook;
	}

	public String getBookID() {
		return bookID;
	}

	public void setBookID(String bookID) {
		this.bookID = bookID;
	}

	public int getQuantiy() {
		return quantity;
	}

	public void setQuantiy(int quantiy) {
		this.quantity = quantiy;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	private void updateTotalPrice() {
		totalPrice = Integer.parseInt(this.itemBook[3]) * this.quantity;
	}

}
