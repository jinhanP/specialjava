package Cart;

import java.util.ArrayList;

import Musicitem.Music;

public class Cart  implements CartInterface{// 장바구니 인터페이스 구현
    public ArrayList<CartItem> cartItem = new ArrayList<CartItem>(); // 배열 목록 <카트 항목>
    public static int cartCount = 0;

    public Cart() {
    }

    @Override
    public void printMusicList(ArrayList<Music> musicList) {
        for (int i = 0; i < musicList.size(); i++) {
        	Music music = musicList.get(i);
            System.out.print(music.getMusicId() + " | ");
            System.out.print(music.getSing() + " | ");
            System.out.print(music.getName() + " | ");
            System.out.print(music.getUnitPrice() + " | ");
            System.out.print(music.getAuthor() + " | ");
            System.out.print(music.getDescription() + " | ");
            System.out.print(music.getCategory() + " | ");

            System.out.print(music.getReleaseDate());
            System.out.println("");
        }
    }
    @Override
    public boolean isCartInMusic(String musicId){
        // check overlap 겹침을 체크하다
        // if musicID in cartItemList, increase number of same music
    	// 장바구니 항목 목록에 있는 music ID인 경우, 동일한 음악의 수를 늘립니다
        boolean flag = false;
        for(int i = 0; i < cartItem.size(); i++){
            if (musicId.equals(cartItem.get(i).getMusicID())){
                cartItem.get(i).setQuantity(cartItem.get(i).getQuantity()+1);
                flag = true;
            }
        }
        return flag;
    }

    @Override
    public void insertMusic(Music music) {
        CartItem musicItem = new CartItem(music);
        cartItem.add(musicItem);
        cartCount = cartItem.size();

    }

    @Override
    public void removeCart(int numId) {
        cartItem.remove(numId);
        cartCount = cartItem.size();

    }

    @Override
    public void deleteMusic() {
        cartItem.clear();
        cartCount = 0;

    }
    public void printCart() { 
        System.out.println("Paly list 상품 목록 :");
        System.out.println("---------------------------------------------");
        System.out.println(" 노래 ID \t\t| 수량 \t| 합계");
        for( int i = 0; i < cartItem.size(); i++) {
            System.out.print(" " + cartItem.get(i).getMusicID() + " \t| ");
            System.out.print(" " + cartItem.get(i).getQuantity() + " \t| ");
            System.out.print(" " + cartItem.get(i).getTotalPrice());
            System.out.println(" ");
        }
        System.out.println("---------------------------------------------");
    }
}