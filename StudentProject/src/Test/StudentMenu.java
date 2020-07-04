package Test;

import java.util.Scanner;

import Biz.StudentBizImpl;
import entity.Admin;

public class StudentMenu {
	Scanner input=new Scanner(System.in);
	StudentBizImpl stuimpl=new StudentBizImpl();
	Admin adntemp=null;
	public StudentMenu(){
		stuimpl.sdi.init();
		stuimpl.adi.init();
		
	}
	//登录菜单方法
	public void showmenu(){
		while(true){
			System.out.println("欢迎登录学生管理系统");
			System.out.println("--------------");
			System.out.println("1、登录");
			System.out.println("2、注册");
			System.out.println("3、退出");
			int search=input.nextInt();
			switch(search){
			case 1://登录
				System.out.println("请输入管理员账户和密码");
				System.out.println("请输入用户名：");
				String adid=input.next();
				System.out.println("请输入密码：");
				String adpwd=input.next();
				boolean isloginsuccess=stuimpl.Admin(adid, adpwd);
				if(isloginsuccess==true){
					showLoginMenu();
				}
				continue;
			case 2://注册
				System.out.println("注册");
    			System.out.println("请输入用户名");
    			String username1=input.next();
    			System.out.println("请输入密码");
    			String password1=input.next();
    			stuimpl.regist(username1, password1);
    			continue;
			case 3://退出
				System.out.println("欢迎下次使用！");
    			System.exit(1);
    			continue;
			default:
					break;
			}
		}
	}
	private void showLoginMenu() {
		while(true){
			System.out.println("----欢迎使用学生管理系统----");
			System.out.println("----------------------");
			System.out.println("1、查询所有学生");
			System.out.println("2、根据学生姓名查询");
			System.out.println("3、增加学生");
			System.out.println("4、修改学生");
			System.out.println("5、删除学生");
			System.out.println("6、返回上一级菜单");
			System.out.println("------------");
			System.out.println("请选择你的服务项目");
			int select=input.nextInt();
			switch(select){
			case 1://显示所有学生
				ShowAllstudent();
				break;
			case 2://选择学生姓名查询
				selectstuName();
				break;
			case 3://增加学生
				insertStudent();
				break;
			case 4://修改学生
				Updatecount();
				break;
			case 5://删除学生
				deletestu();
				break;
			case 6://退出程序
				System.out.println("感谢您的使用！欢迎下次使用！");
				showmenu();
				break;
			default:
				showLoginMenu();
					break;
			}
		}
		
	}
	//显示全部学生
	private void ShowAllstudent() {
		stuimpl.showALLStudent();
		
	}
	//根据学生姓名查询
	public void selectstuName(){
		System.out.println("请输入学生姓名：");
		String stuName=input.next();
		stuimpl.stuname(stuName);
	}
	
	public void insertStudent(){
		System.out.println("新增学生");
		
		System.out.println("请输入学生学号");
		String number=input.next();
		System.out.println("请输入学生姓名");
		String stuName=input.next();
		System.out.println("请输入学生性别");
		String sex=input.next();
		System.out.println("请输入学生专业");
		String profession=input.next();
		System.out.println("请输入学生班级");
		String clazz=input.next();
		
		stuimpl.addstudent(number, stuName, sex, profession, clazz);
		
	}
	//修改学生学号信息
	public void Updatecount(){
		System.out.println("请输入学生学号：");
		String number=input.next();
		stuimpl.updatecount(number);
		stuimpl.addstudent(number);
	}
	
	public void deletestu(){
		System.out.println("准备删除学生：");
		System.out.println("请输入要删除的学生的学号：");
		String is=input.next();
		stuimpl.deletestudent(is);
		
		
	}
	
	
	
	
	
	
}
