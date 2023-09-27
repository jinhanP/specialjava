package ch07.exam05;

import java.util.Objects;

public class Student {
	private String name;
	private String major;
	private int id;
	private double gpa;

	public Student() {
		this(null, null, 0, 0.0);
	}

	public Student(String name, int id) {
		this(name, null, id, 0.0);
	}

	public Student(String name, String major, int id, double gpa) {
		super();
		this.name = name;
		this.major = major;
		this.id = id;
		this.gpa = gpa;
	}

	public String getName() {
		return name;
	}

	public String getMajor() {
		return major;
	}

	public int getId() {
		return id;
	}

	public double getGpa() {
		return gpa;
	}

	@Override
	public String toString() {
		return "[name=" + name + ", major=" + major + ", id=" + id + ", gpa=" + gpa + "]";
	}

	@Override // 중복되지 않는 id를 쓴다. hashCode 와 equals 는 써야된다.
	public int hashCode() {
		return Objects.hash(this.name, this.id);
	}

	@Override // Object obj 원하는 객체가 없어서 return false
	public boolean equals(Object obj) {
		Student stu = null;
		if (!(obj instanceof Student)) {
			return false;
		}
		stu = (Student) obj;
		if (this.name.equals(stu.name) && this.id == stu.id) {
			return true;
		}
		return false;
	}
}
