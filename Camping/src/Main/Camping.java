package Main;

	import java.util.Objects;


	public class Camping {


		//멤버변수 
			private int campingId;
			private String title;
			private String publisher;
			private String year;
			private int price;
			//생성자
			public Camping(int campingId, String title, String publisher, String year, int price) {
				super();
				this.campingId = campingId;
				this.title = title;
				this.publisher = publisher;
				this.year = year;
				this.price = price;
			}
			//겟터,셋터
			public int getCampingId() {
				return campingId;
			}
			public String getTitle() {
				return title;
			}
			public String getPublisher() {
				return publisher;
			}
			public String getYear() {
				return year;
			}
			public int getPrice() {
				return price;
			}
			//hashcode, equals
			@Override
			public int hashCode() {
				return Objects.hash(campingId);
				
			}
			@Override
			public boolean equals(Object obj) {
				Camping camping = null;
				if(!(obj instanceof Camping)) {
					return false;
				}
				camping =(Camping)obj;
				return camping.campingId == this.campingId;
			}
			//toString
			@Override
			public String toString() {
				return String.format("update camping set title = '%s',publisher ='%s',year ='%s',price = %d where camping_id = %d; ",campingId,title,publisher,year,price);
			}
			//seriallize
			
			
		}
