package Musicitem;

public abstract class Item {
    String musicId; // music ID
    String sing; // sing
    String name; // music name
    int unitPrice; // music price


    public Item() {
    }

    public Item(String musicId, String sing, String name, int unitPrice) {
        this.musicId = musicId;
        this.sing = sing;
        this.name = name;
        this.unitPrice = unitPrice;
    }
    public abstract String getMusicId();
    public abstract String getSing();
    public abstract String getName();
    public abstract int getUnitPrice();
    public abstract void setMusicId(String musicId);
    public abstract void setSing(String sing);
    public abstract void setName(String name);
    public abstract void setUnitPrice(int unitPrice);
}