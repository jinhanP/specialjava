package Cart;

import java.util.ArrayList;

import Musicitem.Music;

public interface CartInterface {//장바구니 인터페이스
    void printMusicList(ArrayList<Music> musicList) ; // print music list 음악 목록
    boolean isCartInMusic(String id); // // check overlap 겹침을 체크
    
                                     // if musicID in cartItemList, increase number of same music
    								// 장바구니 항목 목록에 있는 music ID인 경우, 동일한 음악의 수를 늘립니다
    
    void insertMusic(Music p); // register musics info in CartItem 장바구니 항목에 음악 정보 등록
    void removeCart(int numId); // remove, num :numId 제거 
    void deleteMusic(); // remove all; 모두 삭제
}
