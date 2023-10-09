package Cart;

import Musicitem.Music;

public class CartItem {
	private Music itemMusic; // musics info
    private  String musicID; // music id
    private  int quantity; // number of music
    private  int totalPrice; // total price of books

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