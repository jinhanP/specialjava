package Cart;

import java.util.ArrayList;

import Musicitem.Music;

public interface CartInterface {
    void printMusicList(ArrayList<Music> musicList) ; // print music list
    boolean isCartInMusic(String id); // // check overlap
                                     // if musicID in cartItemList, increase number of same music
    void insertMusic(Music p); // register musics info in CartItem
    void removeCart(int numId); // remove, num :numId
    void deleteMusic(); // remove all;
}
