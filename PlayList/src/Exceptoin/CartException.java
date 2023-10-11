package Exceptoin;

public class CartException extends Exception{ //장바구니 예외 확장
    public CartException(String str) {
        super(str);
    }
}