package Biz;

import java.util.List;

import Dao.StudentDao;
import Impl.AdminDaoImpl;
import Impl.StudentDaoImpl;
import entity.Admin;
import entity.Student;

public class StudentBizImpl {
	public StudentDaoImpl sdi=new StudentDaoImpl();
	public AdminDaoImpl adi=new AdminDaoImpl();
	List<Admin> admList=adi.AdminList();
	List<Student> stulist=sdi.getAllList();
	Student student=new Student();
	
	public Admin getAdminbyld(String adid){
		for(int i=0;i<admList.size();i++){
			Admin admtemp=admList.get(i);
			if(admtemp.getAdid().equals(adid)){
				return admtemp;
			}
		}
		return null;
	}
	
	//查询所有学生
	public void showALLStudent(){
		int sum=0;
		System.out.println("目前系统有："+stulist.size()+"个学生");
		for(int i=0;i<stulist.size();i++){
			Student stutemp=stulist.get(i);
			System.out.println(stutemp.toString());
		}
	}
	//业务逻辑登录功能
	public boolean Admin(String adid,String adpwd){
		for(int i=0;i<admList.size();i++){
			Admin adtemp=admList.get(i);
			if(adid.equals(adtemp.getAdid())&&adpwd.equals(adtemp.getAdpwd())){
				System.out.println("信息正确，进入系统！");
				return true;
			}else{
				System.out.println("用户名或者密码错误！");
			}
		}
		
		return false;
		
	}
	//根据学生姓名查询学生信息
	public boolean stuname(String stuName){
		for(int i=0;i<stulist.size();i++){
			Student stutemp=stulist.get(i);
			if(stutemp.getStuName().equals(stuName)){
				System.out.println(stutemp.toString());
			}
		}
		return false;
		
	}
	//增加学生
	public boolean addstudent(String number,String stuName,String sex,String profession,String clazz){
		Student student=new Student(number,stuName,sex,profession,clazz);
		for(int i=0;i<stulist.size();i++){
			Student stutemp=stulist.get(i);
			if(stutemp.getNumber().equals(number)||stutemp.getStuName().equals(stuName)){
				System.out.println("您输入的学生已经存在！");
				return false;
			}
		}
		sdi.addStudent(student);
		System.out.println("新增成功！");
		return true;
		
		
	}
	//修改学生信息
	public boolean updatecount(String number){
		for(int i=0;i<stulist.size();i++){
			Student stutemp=stulist.get(i);
			if(stutemp.getNumber().equals(number)){
				System.out.println("修改成功！");
			}
		}
		return false;
		
	}
	//删除学生
	public boolean deletestudent(String number){
		for(int i=0;i<stulist.size();i++){
			Student stutemp=stulist.get(i);
			if(stutemp.getNumber().equals(number)){
				stulist.remove(i);
				System.out.println("删除成功！");
			}else{
				System.out.println("没有找到你要删除的学生学号");
				return true;
			}
		}
		return false;
		
	}
	//注册
	public void regist(String adid,String adpwd){
		Admin admin = new Admin(admList.size()+1,adid,adpwd);
		adi.insert(admin);
	}

	public void addstudent(String stuName) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
	
	
	
	

}
