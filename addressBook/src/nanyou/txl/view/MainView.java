package nanyou.txl.view;
/*
 * 
 * 视图层.用户看到的操作数据
 * 数据传递给controller层实现
 * 
 */

import java.util.Scanner;


import org.omg.CORBA.PUBLIC_MEMBER;

import nanyou.txl.dao.PersonDao;


public class MainView {
	
	PersonDao D=new PersonDao();
	//本程序运行界面,即用户界面
	public void run() {
		while(true){//保证用户不退出,则一直运行
			System.out.println("-----------------------------我的通讯录-------------------------");
			System.out.println("1.添加联系人    2.编辑联系人   3.删除联系人     4.查询联系人    5.退出系统\n");
			System.out.println("请输入操作的功能序号[1-5]:");
			//接受用户的选择
			Scanner scanner=new Scanner(System.in);
			int cho=scanner.nextInt();//接受选择
			//接收用户的选择,调用不同的功能
			switch (cho) {
			
			case 1:
				//调用DAO层添加联系人
				addPerson();
				break;
			
			case 2:
				//调用DAO层编辑联系人
				editPerson();
				break;
			
			case 3:
				//调用DAO层删除联系人
				deletPerson();
				break;
			
			case 4:
				//调用DAO层查询联系人
				selectPerson();
				break;
			
			case 5:
				//退出系统
				System.exit(0);
			default:
				System.out.println("输入错误,请重新选择");
				break;
			}
		}
		
		
		
	}
	
	//功能1,添加联系人
	public void addPerson(){
		D.adduser();
	}
	//功能2,修改联系人
	public void editPerson(){
		D.edituser();
	}
	//功能3,删除联系人
	public void deletPerson(){
		D.deletuser();
	}
	//功能4,查询联系人
	public void selectPerson(){
		System.out.println(" 1.精确查询 \n  2.显示所有(电话号码升序)\n 3.显示所有(电话号码降序)\n 4.显示所有(姓名升序)\n 5.显示所有(姓名降序) ");
		Scanner scanner=new Scanner(System.in);
		int cas=scanner.nextInt();
		switch (cas) {
		case 1:
			
			System.out.println("1.电话号码查询  2.姓名查询 3.查询ID");
			Scanner scan=new Scanner(System.in);
			int cass=scan.nextInt();
			if (cass==1) {
				D.selectforPhon();
			}
			if (cass==2){
				D.selectforname();
			}
			if (cass==3){
				D.selectforID();
			}else {
				System.out.println("输入错误");
			}

			break;
		case 2:
			D.selectAllforPhone();
			break;
		case 3:
			System.out.println("删除联系人:");
			D.selectAllforPhoneDESC();
			break;
		case 4:
			D.selectAllforName();
			break;
		case 5:
			D.selectAllforNameDESC();
			break;
		default:
			break;
		}
	}

	
	
}
