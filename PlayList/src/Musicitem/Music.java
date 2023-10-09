package Musicitem;

public class Music extends Item {
    private String author;
    private String description;
    private String category;
    private String releaseDate;

    public Music() {
    }
    public Music(String musicId, String sing, String name, int unitPrice) {
        super(musicId, sing, name, unitPrice);
    }
    public Music(String musicId, String sing, String name, int unitPrice, String
            author, String description, String category, String releaseDate) {
        super(musicId, sing, name, unitPrice);
        this.author = author;
        this.description = description;
        this.category = category;
        this.releaseDate = releaseDate;
    }

    @Override
    public String getMusicId() {
        return musicId;
    }
    @Override
    public String getSing() {
        return sing;
    }
    @Override
    public String getName() {
        return name;
    }
    @Override
    public int getUnitPrice() {
        return unitPrice;
    }
    @Override
    public void setMusicId(String musicId) {
        this.musicId = musicId;
    }
    @Override
    public void setSing(String sing) {
        this.sing = sing;
    }
    @Override
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getReleaseDate() {
        return releaseDate;
    }
    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }


}