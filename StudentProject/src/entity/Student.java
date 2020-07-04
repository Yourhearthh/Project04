package entity;

public class Student {
	private String number;
	private String stuName;
	private String sex;
	private String profession;
	private String clazz;
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getStuName() {
		return stuName;
	}
	public void setName(String stuName) {
		stuName = this.stuName;
	}
	public String getSex() {
		return sex;
	}
	public void setAge(String sex) {
		this.sex = sex;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	public String getClazz() {
		return clazz;
	}
	public void setClazz(String clazz) {
		this.clazz = clazz;
	}
	
	public String toString(){
		return "Student[学号="+number+",姓名="+stuName+",性别="+sex+",专业="+profession+",班级="+clazz+"]";
		
	}
	

	
	public Student(){
		super();
	}
	public Student(String number, String stuName, String sex, String profession, String clazz) {
		super();
		this.number=number;
		this.stuName=stuName;
		this.sex=sex;
		this.profession=profession;
		this.clazz=clazz;
		
	}

}
