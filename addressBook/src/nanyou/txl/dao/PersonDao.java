package nanyou.txl.dao;


/**
 * 持久层,将联系人持久化
 */
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.dbutils.QueryRunner;

import nanyou.txl.domain.Person;
import nanyou.txl.tools.DataSourceUtils;

import javax.sql.DataSource;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;


//实现对联系人表的增删改查操作
//用DBUTILS工具类完成
public class PersonDao {
	QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
	
	/*
	 * 查询并打印联系人
	 * 通过查询语句 打印联系人数据[关键功能]
	 */
	public void printPerson(String sqlString){
		List <Person>list=null;
		try {
			list= qr.query(sqlString, new BeanListHandler<>(Person.class));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("sql异常");
		}
		if (list.size()==0||list.get(0).getId()==0) {
			System.out.println("查无此人!");
			
		}
		else {
			System.out.println("ID\t姓名\t电话\t\t地址\t\t邮箱");
			//遍历集合,输出信息
			for(Person bean : list) {
				System.out.println(bean.getId()+"\t"+bean.getUsername()+"\t"+bean.getPhone()+"\t"+bean.getAddress()+"\t\t"+bean.getPostbox());
			}
		}
		System.out.println("\n");//换行
	}
	
	//查询所有联系人
	public void selectAllforPhone() {
		// 调用控制层,查询所有账务数据
		String sqlString="SELECT * FROM `addressbook`.`book` ORDER BY `username`;";
		printPerson(sqlString);
	}
	public void selectAllforPhoneDESC() {
		// 调用控制层,查询所有账务数据
		String sqlString="SELECT * FROM `addressbook`.`book` ORDER BY `username` DESC;";
		printPerson(sqlString);
	}
	public void selectAllforNameDESC(){
		String sqlString="SELECT * FROM `addressbook`.`book` ORDER BY `username` DESC;";
		printPerson(sqlString);
	}
	public void selectAllforName(){
		String sqlString="SELECT * FROM `addressbook`.`book` ORDER BY `username`;";
		printPerson(sqlString);
	}
	
	
	//通过名字查询
	public void selectforname() {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		System.out.print("请输入需要查询的姓名:");
		String name = in.next();
		String sql = "select * from book where username= '"+name+"'";
		
		printPerson(sql);
	}
	
	
	//通过ID查询
	public int selectforID() {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		System.out.println("请输入ID");
		int id =in.nextInt();
		String sql = "select * from book where id="+id;
		printPerson(sql);
		return id;
	}
	
	
	//通过电话查询联系人
	public void selectforPhon() {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		System.out.println("请输入要查询的电话:");
		long id =in.nextLong();
		String sql = "select * from book where phone="+id;
		printPerson(sql);
	}
	
	
	
	//增加联系人
	public void adduser() {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		Person per = new Person();
		
		System.out.print("请输入联系人姓名：\n");
		per.setUsername(in.next());
		System.out.print("请输入电话：\n");
		per.setPhone(in.nextLong());
		System.out.print("请输入地址：\n");
		per.setAddress(in.next());
		System.out.print("请输入邮箱：\n");
		per.setPostbox(in.next());
		
		String sql = "insert into book (username,phone,address,postbox) values(?,?,?,?)";
		
		try {
			Object[] params = {per.getUsername(),per.getPhone(),per.getAddress(),per.getPostbox()};
			qr.update(sql,params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("添加失败\n");
		}
		System.out.println("添加成功！\n");
	}
	
	/**
	 * 修改联系人
	 * 
	 */
	
	public void edituser() {
		// TODO Auto-generated method stub
		selectAllforPhone();
		System.out.println("请确认要修改的联系人ID:\n");
		
		Scanner in = new Scanner(System.in);
		int id =in.nextInt();//选择联系人ID
		Person per = new Person();
		
		System.out.print("请输入联系人姓名：\n");
		per.setUsername(in.next());
		System.out.print("请输入电话：\n");
		per.setPhone(in.nextLong());
		System.out.print("请输入地址：\n");
		per.setAddress(in.next());
		System.out.print("请输入邮箱：\n");
		per.setPostbox(in.next());
	
		String sql = "update book set username=?,phone=?,address=?,postbox=? where id=?";
		try {
			Object[] params = {per.getUsername(),per.getPhone(),per.getAddress(),per.getPostbox(),id};
			qr.update(sql,params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("修改失败");
		}
		System.out.println("修改信息成功！\n");
	}
	
	//删除联系人
	public void deletuser() {
		// TODO Auto-generated method stub
		selectAllforPhone();
		
		
		Scanner in = new Scanner(System.in);
	
		int id=selectforID();
		
		
		System.out.println("确定删除?输入 y/yes 或者返回主菜单\n");//确认删除
		String cho =in.next();
		cho.toLowerCase();   //输入转换为小写
		if (cho.equals("yes")||cho.equals("y")) {
			String sql = "delete from book where id="+id;
			try {
				qr.update(sql);
			} catch (SQLException e) {
				System.out.println("删除失败\n");
				throw new RuntimeException(e);
			}
			
			System.out.println("删除成功\n");
		
		}else {
			System.out.println("取消删除,返回主菜单\n");
		}
		
	}

	
}
