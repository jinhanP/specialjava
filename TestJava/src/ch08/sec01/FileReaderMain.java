package ch08.sec01;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileReaderMain {

    public static void main(String[] args) {
        try {
            FileReader fr = new FileReader("C:/javatest10/javatest.txt");
            while(true) {
                int data = fr.read();
                if(data == -1) {
                    break; 
                }
                System.out.print((char)data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("The end");
    }
}