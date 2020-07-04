package Impl;

import java.util.ArrayList;
import java.util.List;

import Dao.StudentDao;
import entity.Student;

public class StudentDaoImpl implements StudentDao{
	List<Student> studentlist=new ArrayList<Student>();
	public void init(){
		Student student1=new Student("20161001","包不同","男","计算机","一班");
		Student student2=new Student("20171002","黄大海","男","畜牧业","二班");
		Student student3=new Student("20141003","邱大地","女","电子信息","一班");
		Student student4=new Student("20151004","钟小欢","女","行政管理","二班班");
		Student student5=new Student("20181005","古发头","女","商务管理","三班");
		Student student6=new Student("20161006","成大厦","男","计算机","一班");
		
		studentlist.add(student1);
		studentlist.add(student2);
		studentlist.add(student3);
		studentlist.add(student4);
		studentlist.add(student5);
		studentlist.add(student6);
	}

	@Override
	public List<Student> getAllList() {
		return studentlist;
	}

	@Override
	public boolean addStudent(Student student) {
		return studentlist.add(student);
	}

	@Override
	public boolean deleteStudent(Student student) {
		return false;
	}

	@Override
	public boolean updateStudent(Student student) {
		return false;
	}

}
