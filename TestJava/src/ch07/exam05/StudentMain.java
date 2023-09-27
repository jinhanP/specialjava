package ch07.exam05;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class StudentMain {
	public static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		ArrayList<Student> al = new ArrayList<Student>();

		System.out.printf("4명의 학생 이름,학과,학번,학점평균을 입력>>\n");
		for (int i = 0; i < 4; i++) {
			System.out.printf(">>");
			String[] data = scan.nextLine().trim().split(",");
			al.add(new Student(data[0], data[1], Integer.parseInt(data[2]), Double.parseDouble(data[3])));
		}

		System.out.println("=================정보출력================");
		// iterator
		Iterator<Student> ir = al.iterator();
		while (ir.hasNext()) {
			System.out.printf("%s \n", ir.next());
		}

		System.out.println("=================정보출력================");
		while (true) {
			System.out.printf("학생이름>>");
			String name = scan.next().trim();
			if (name.equals("그만"))
				break;

			System.out.printf("학생번호>>");
			int id = Integer.parseInt(scan.next().trim());
			Student findStu = new Student(name, id);
			boolean isFind = al.contains(findStu);// name , id 만 비교
			if (isFind) {
				int index = al.indexOf(findStu);
				System.out.printf("%s \n", al.get(index));
			} else {
				System.out.printf("찾는 학생이 없습니다.\n");
			}
		}

		scan.close();
		System.out.println("the end");
	}
}
