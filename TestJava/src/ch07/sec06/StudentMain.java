package ch07.sec06;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
import java.util.ArrayList;

public class StudentMain {
	public static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		HashMap<String, Student> hsMap = new HashMap<String, Student>();
		String[] array = new String[4];

		System.out.println("학생 이름, 학과, 학번, 학점평균 입력하세요");
		for (int i = 0; i < 4; i++) {
			System.out.printf(">>");
			array = scan.nextLine().split(",");
			String name = array[0].trim();
			String department = array[1].trim();
			int num = Integer.parseInt(array[2].trim());
			double credit = Double.parseDouble(array[3].trim());
			hsMap.put(name, new Student(name, department, num, credit));

		}
		hsMap.forEach((name, student) -> System.out.println("이름:" + name + "\n" + "학과:" + student.getDepartment() + "\n"
				+ "학번:" + student.getNum() + "\n" + "학점평균:" + student.getCredit()+
				"\n==============================="));
		

		while (true) {
			System.out.print("학생이름(종료:exit)>>");
			String name = scan.nextLine().trim();
			if (name.equals("exit") || name.equals("종료")) {
				System.out.println("학점 평균을 종료합니다.");
				break;
			}
			if (hsMap.get(name) != null) {
				System.out.printf("%s >> %s \n", name, hsMap.get(name).toString());
			} else {
				System.out.printf("%s >> %s \n", name, "해당되는 이름이 없습니다.");
			}

		}
		System.out.println("\nthe end");
		scan.close();
	}
}