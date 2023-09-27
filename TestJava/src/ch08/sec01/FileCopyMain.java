package ch08.sec01;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileCopyMain {
	//파일 복사
	public static void main(String[] args) throws IOException {
		File src = new File("C:/Windows/system.ini");
		File dest = new File("C:/javatest10/mysystem.txt");
		
		FileReader fr = new FileReader(src);
		FileWriter fw = new FileWriter(dest);
		
		while(true) {
			int data = fr.read();
			if(data == -1) break;
			fw.write((char)data);
		}
		
		fr.close();
		fw.close();
		System.out.printf("%s 에서 %s 로 파일복사 완료했습니다.",src.getName(), dest.getName());
		System.out.println("\nthe end");
	}
}
