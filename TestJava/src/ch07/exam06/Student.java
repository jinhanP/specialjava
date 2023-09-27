package ch07.exam06;

import java.util.Objects;

public class Student {
	private String name;
	private  double credit;
	
	
	public Student() {
		this(null, 0.0);
	}


	public Student(String name, double credit) {
		super();
		this.name = name;
		this.credit = credit;
	}


	public String getName() {
		return name;
	}


	public double getCredit() {
		return credit;
	}

	@Override
	public int hashCode() {
		return Objects.hash(credit, name);
	}


	@Override
	public boolean equals(Object obj) { 
		Student stu = null; // 객채 만들어주고
		if(!(obj instanceof Student)) { //부인 자식 타입
			return false;
		}
		stu = (Student) obj;
		if(this.name.equals(stu.name) && this.credit == stu.credit) {
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "[name=" + name + ", credit=" + credit + "]";
	}
	
}
