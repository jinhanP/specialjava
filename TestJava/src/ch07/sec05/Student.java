package ch07.sec05;

import java.util.Objects;

public class Student {
	//멤버변수
	private int id;
	private String tel;
	//생성자(디폴트 생성자,매생)
	public Student() {
		this(0,null);
	}
	public Student(int id, String tel) {
		super();
		this.id = id;
		this.tel = tel;
	}
	
	//멤버함수(연산 , 겟터 , 셋터 , 오버라이딩)
	public int getId() {
		return id;
	}
	
	public String getTel() {
		return tel;
	}
	@Override
	public boolean equals(Object obj) {
		Student stu = (Student)obj;
		if(this.id == stu.id) {
			return true;
		}else {
			return false;
		}
	}
	@Override
	public int hashCode() {
		return Objects.hash(id+tel);
	}
	
	//출력함수
	@Override
	public String toString() {
		return "Student [id=" + id + ", tel=" + tel + "]";
	}
	
	
}
