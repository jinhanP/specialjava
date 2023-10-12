package Cart;

import Musicitem.Music;

public class CartItem {
	private Music itemMusic; // musics info 음악 정보
	private String musicID; // music id 음악ID
	private int quantity; // number of music 곡 수
	private int totalPrice; // total price of musics 음원 총가격

	public CartItem() {
	}

	public CartItem(Music itemMusic) {
		this.itemMusic = itemMusic;
		this.musicID = itemMusic.getMusicId();
		this.quantity = 1;
		updateTotalPrice();
	}

	public Music getItemMusic() {
		return itemMusic;
	}

	public void setItemMusic(Music itemMusic) {
		this.itemMusic = itemMusic;
	}

	public String getMusicID() {
		return musicID;
	}

	public void setMusicID(String musicID) {
		this.musicID = musicID;
		this.updateTotalPrice();
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
		this.updateTotalPrice();
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void updateTotalPrice() {
		this.totalPrice = this.itemMusic.getUnitPrice() * this.quantity;
	}
}