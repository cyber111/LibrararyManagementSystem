package test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import dbutils.DBUtils;
import pojo.Book;

//import test.OwnerMenu.menuOption;

public class MemberMenu {
	static Scanner sc = new Scanner(System.in);

	public static menuOption menuList()
	{
		System.out.println("0.Sign Out");
		System.out.println("1.Edit Profile");
		System.out.println("2.Edit Password");
		System.out.println("3.Find Book");
		System.out.println("4.Check Availibility");



		return menuOption.values()[sc.nextInt()];

	}

	public enum menuOption 
	{
		EXIT, EDITPROFILE, EDITPASSWORD, FINDBOOK, CHECKAVALIBILITY
	}

	public void loadMemberMethods(int id) throws Exception
	{
		menuOption choice;
		while((choice = MemberMenu.menuList()) != null)
		{

			switch (choice) {
			case EXIT :
				System.out.println("Loged Out");
				new HomePage().loadUsers();
				break;
			case EDITPROFILE:
				MemberMenu.editProfile(id);
				break;
			case EDITPASSWORD:
				MemberMenu.editPassword(id);
				break;
			case FINDBOOK:
				MemberMenu.findBookByName();
				break;
			case CHECKAVALIBILITY:
				MemberMenu.checkAvalibility();
				break;

			}
		}
	}

	public static void editProfile(int id) throws Exception
	{
		Connection connection = DBUtils.getConnection();
		Statement statement = connection.createStatement();
		Scanner sc = new Scanner(System.in);
		System.out.println("Select what you want to edit ");
		int choice = 100;
		
		while(choice != 0)
		{
			System.out.println("0.Exit ");
			System.out.println("1.Name ");
			System.out.println("2.Email");
			System.out.println("3.Phone");
			choice = sc.nextInt();
			if(choice == 1)
			{
				System.out.println("Enter new name");
				sc = new Scanner(System.in);
				String newName = sc.next();
//				String sql1 = "select id from users where name = '"+newName+"'";
				String sql = "UPDATE users SET  name = '"+newName+"' where id = '"+ id +"'";
				statement.executeUpdate(sql);
				System.out.println("Edited SuccessFully");
				
			}
			else if(choice == 2)
			{
				System.out.println("Enter new Email");	
				sc = new Scanner(System.in);
				String newEmail = sc.next();
				String sql = "UPDATE users SET  email = '"+newEmail+"' where id = '"+ id +"'";
				statement.executeUpdate(sql);
				System.out.println("Edited SuccessFully");
			}
			else if(choice == 3)
			{
				System.out.println("Enter new Phone");
				sc = new Scanner(System.in);
				String newPhone = sc.next();
				String sql = "UPDATE users SET  phone = '"+newPhone+"' where id = '"+ id +"'";
				statement.executeUpdate(sql);
				System.out.println("Edited SuccessFully");
			}
		}
	}

	public static void editPassword(int id) throws Exception
	{
		Connection connection = DBUtils.getConnection();
		Statement statement = connection.createStatement();
		System.out.println("Enter tnew password");
		Scanner sc = new Scanner(System.in);
		String newPassword = sc.next();
		String sql = "UPDATE users SET  password = '"+newPassword+"' where id = '"+ id +"'";
		statement.executeUpdate(sql);
		System.out.println("Passowrd Successfully Updated");
		
		
		
	}
	public static void findBookByName() throws Exception
	{
		Connection connection = DBUtils.getConnection();
		Statement statement = connection.createStatement();
		System.out.println("Enter the Name of book");
		sc = new Scanner(System.in);
		String name = sc.nextLine();
		String sql = "select * from books where name = '"+name+"'";
		try( ResultSet rs = statement.executeQuery(sql);)
		{			

			while(rs.next())
			{
				Book book = new Book();
				book.setAuthor(rs.getString("author"));
				book.setName(rs.getString("name"));
				book.setIsbn(rs.getLong("isbn"));
				book.setSubject(rs.getString("subject"));
				book.setPrice(rs.getFloat("price"));

				System.out.println(book.toString());
			}
		}
	}
	public static void checkAvalibility() throws Exception 
	{
		Connection connection = DBUtils.getConnection();
		Statement statement = connection.createStatement();
		System.out.println("Enter the Name of book");
		sc = new Scanner(System.in);
		String name = sc.nextLine();
		String sql = "select * from books where name = '"+name+"'";
		try( ResultSet rs = statement.executeQuery(sql);)
		{			

			if(rs.next())
				System.out.println("Avaolable");
			else
				System.out.println("Not Available");
		}



	}
}
