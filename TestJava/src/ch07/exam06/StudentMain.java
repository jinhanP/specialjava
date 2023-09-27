package ch07.exam06;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class StudentMain {
	public static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		HashMap<String, Student> hm = new HashMap<String, Student>();

		System.out.println("미래장학금 관리 시스템입니다.");

		// 입력
		hm.put("적당히", new Student("적당히", 3.1));
		hm.put("나탈락", new Student("나탈락", 2.4));
		hm.put("최고조", new Student("최고조", 4.3));
		hm.put("상당히", new Student("상당히", 3.9));
		hm.put("고득점", new Student("고득점", 4.0));

		// 선발기준점수 입력
		System.out.printf("장학생 선발 학점 기준입력>>");

		double score = scan.nextDouble();

		Set<String> keyset = hm.keySet();
		for (String data : keyset) {
			Student stu = hm.get(data);
			if (stu.getCredit() >= score) {
				System.out.printf("장학생:%s  ", stu.getName());
			}
		}
		scan.close();
		System.out.println("\nthe end");
	}
}
