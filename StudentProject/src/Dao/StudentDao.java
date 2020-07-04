package Dao;

import java.util.List;

import entity.Student;

public interface StudentDao {
	public List<Student> getAllList();
	public boolean addStudent(Student student);
	public boolean deleteStudent(Student student);
	public boolean updateStudent(Student student);

}
