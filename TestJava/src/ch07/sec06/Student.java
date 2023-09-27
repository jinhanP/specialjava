package ch07.sec06;

import java.util.Objects;

public class Student {
	private String name;
    private String department;
    private int num;
    private double credit;

    public Student() {
    	 super();
    }

    public Student(String name, String department, int num, double credit) {
        super();
        this.name = name;
		this.department = department;
		this.num = num;
		this.credit = credit;
    }

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public double getCredit() {
		return credit;
	}

	public void setCredit(double credit) {
		this.credit = credit;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
	
    @Override
	public boolean equals(Object obj) {
    	Student student = new Student();
    	if(obj instanceof Student) {
    			student = (Student)obj;
    	}else return false;
		return(this.credit == student.credit && this.department.equals(student.department)&& this.name.equals(name)
				&& this.num == student.num);
	}

	@Override
    public String toString() {
        return "Student [name=" + name + ", department=" + department + ", num=" + num + ", credit=" + credit + "]";
    }
}