package test;

import java.util.Scanner;

import java.sql.*;

//import com.mysql.cj.xdevapi.Statement;

import dbutils.DBUtils;
import pojo.Book;
import pojo.Librarian;

public class OwnerMenu {
	// public void hello() {
	// System.out.println("HelloOwner");
	// }

	static Scanner sc = new Scanner(System.in);

	public static menuOption menuList() {
		System.out.println("0.Sign Out");
		System.out.println("1.Edi Profile");
		System.out.println("2.Edit Password");
		System.out.println("3.Fine report");
		System.out.println("4.Fees Report");
		System.out.println("5.Sunject wise copies report");
		System.out.println("6.Appoint Librarian");
		System.out.println("7.Find book by Name");
		System.out.println("8.Change Rack");

		return menuOption.values()[sc.nextInt()];

	}

	public enum menuOption {
		EXIT, EDITPROFILE, EDITPASSWORD, FINEREPORT, FEESREPORT, SUBJECTREPORT, APPLIBRARIAN, FINDBOOKBYNAME, CHANGERACK
	}

	public void loadOwnerMethods(int id) throws Exception {
		menuOption choice;
		while ((choice = OwnerMenu.menuList()) != null) {

			switch (choice) {
			case EXIT:
				System.out.println("Loged Out");
				new HomePage().loadUsers();
				break;
			case EDITPROFILE:
				OwnerMenu.editProfile(id);
				break;
			case EDITPASSWORD:
				OwnerMenu.editPassword(id);
				break;
			case FINEREPORT:
				OwnerMenu.fineReport();
				break;
			case FEESREPORT:
				OwnerMenu.feesReport();
				break;
			case APPLIBRARIAN:
				try {
					OwnerMenu.addLibrian();
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case FINDBOOKBYNAME:
				OwnerMenu.findBookByName();
				break;
			case CHANGERACK:
				OwnerMenu.changeRack();
				break;
			default:
				break;
			}
		}
	}

	private static void changeRack() throws Exception 
	{
		Connection connection = DBUtils.getConnection();
		Statement statement = connection.createStatement();
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter book id");
		int bookId = sc.nextInt();
		System.out.println("Enter new rack");
		int rack  = sc.nextInt();
		String sql = "update copies set rack = '"+rack+"'1 where bookid ="+bookId+"";
		statement.execute(sql);
		System.out.println("Rack updated");
		
	}

	private static void feesReport() throws Exception {

		Connection connection = DBUtils.getConnection();
		Statement statement = connection.createStatement();
		String sql = "select sum(amount) as sum from payments ";
		ResultSet rs = statement.executeQuery(sql);
		if (rs.next()) {
			System.out.println("Fees recorded is " + rs.getString("sum"));
		}
	}

	public static void fineReport() throws Exception {
		Connection connection = DBUtils.getConnection();
		Statement statement = connection.createStatement();
		String sql = "select sum(fine_amount) from issuerecord as sum";
		ResultSet rs = statement.executeQuery(sql);
		if (rs.next()) {
			System.out.println("Fine recorded is " + rs.getString("sum(fine_amount)"));
		}

	}

	public static void addLibrian() throws Exception {
		Connection connection = DBUtils.getConnection();
		Statement statement = connection.createStatement();
		Scanner sc = new Scanner(System.in);

		Librarian lib = new Librarian();
		System.out.println("Enter Name :");
		lib.setName(sc.next());
		System.out.println("EnterEmail :");
		lib.setEmail(sc.next());
		System.out.println("Enter Phone :");
		lib.setPhone(sc.nextLong());
		System.out.println("Enter Password");
		String password = sc.next();
		lib.setPassword(password);
		// Libraianslist.add(lib);
		String sql = "INSERT INTO users(name, email, phone, password, role) VALUES ('" + lib.getName() + "','"
				+ lib.getEmail() + "','" + lib.getPhone() + "','" + lib.getPassword() + "','" + lib.getRole() + "');";

		statement.executeUpdate(sql);

	}

	public static void findBookByName() throws Exception {
		Connection connection = DBUtils.getConnection();
		Statement statement = connection.createStatement();
		System.out.println("Enter the Name of book");
		sc = new Scanner(System.in);
		String name = sc.nextLine();
		String sql = "select * from books where name = '" + name + "'";
		try (ResultSet rs = statement.executeQuery(sql);) {

			while (rs.next()) {
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

	public static void editPassword(int id) throws Exception {

		Connection connection = DBUtils.getConnection();
		Statement statement = connection.createStatement();
		System.out.println("Enter tnew password");
		Scanner sc = new Scanner(System.in);
		String newPassword = sc.next();
		String sql = "UPDATE users SET  password = '" + newPassword + "' where id = '" + id + "'";
		statement.executeUpdate(sql);
		System.out.println("Passowrd Successfully Updated");
	}

	public static void editProfile(int id) throws Exception {
		Connection connection = DBUtils.getConnection();
		Statement statement = connection.createStatement();
		Scanner sc = new Scanner(System.in);
		System.out.println("Select what you want to edit ");
		int choice = 100;

		while (choice != 0) {
			System.out.println("0.Exit ");
			System.out.println("1.Name ");
			System.out.println("2.Email");
			System.out.println("3.Phone");
			choice = sc.nextInt();
			if (choice == 1) {
				System.out.println("Enter new name");
				sc = new Scanner(System.in);
				String newName = sc.next();
//				String sql1 = "select id from users where name = '"+newName+"'";
				String sql = "UPDATE users SET  name = '" + newName + "' where id = '" + id + "'";
				statement.executeUpdate(sql);
				System.out.println("Edited SuccessFully");

			} else if (choice == 2) {
				System.out.println("Enter new Email");
				sc = new Scanner(System.in);
				String newEmail = sc.next();
				String sql = "UPDATE users SET  email = '" + newEmail + "' where id = '" + id + "'";
				statement.executeUpdate(sql);
				System.out.println("Edited SuccessFully");
			} else if (choice == 3) {
				System.out.println("Enter new Phone");
				sc = new Scanner(System.in);
				String newPhone = sc.next();
				String sql = "UPDATE users SET  phone = '" + newPhone + "' where id = '" + id + "'";
				statement.executeUpdate(sql);
				System.out.println("Edited SuccessFully");
			}
		}
	}

}
