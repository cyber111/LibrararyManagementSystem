package test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import dbutils.DBUtils;
import pojo.Book;
import pojo.Librarian;


public class LibrarianMenu {
	static Scanner sc = new Scanner(System.in);

	public static menuOption menuList() {
		System.out.println("0.Sign Out");
		System.out.println("1.Edi Profile");
		System.out.println("2.Edit Password");
		System.out.println("3.Subject wise copies report");
		System.out.println("4.Find book by Name");
		System.out.println("5.Edit Book");
		System.out.println("6.Add Book");

		return menuOption.values()[sc.nextInt()];

	}

	public enum menuOption {
		EXIT, EDITPROFILE, EDITPASSWORD, SUBJECTREPORT, FINDBOOKBYNAME, EDITBOOK, ADDBOOK
	}

	public void loadLibrarianMethods(int id) throws Exception {
		menuOption choice;
		while ((choice = LibrarianMenu.menuList()) != null) {

			switch (choice) {
			case EXIT:
				System.out.println("Loged Out");
				new HomePage().loadUsers();
				break;
			case EDITPROFILE:
				LibrarianMenu.editProfile(id);
				break;
			case EDITPASSWORD:
				LibrarianMenu.editPassword(id);
				break;
			case SUBJECTREPORT:
				break;
			case FINDBOOKBYNAME:
				OwnerMenu.findBookByName();
				break;
			case EDITBOOK:
				LibrarianMenu.editBook(id);
				break;
			case ADDBOOK:
				LibrarianMenu.addBook();
				break;
			default:
				break;
			}
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
		String sql = "INSERT INTO books (name, email, phone, password, role) VALUES ('" + lib.getName() + "','"
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
				System.out.println(sql);
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

	public static void addBook() throws Exception
	{
		Connection connection = DBUtils.getConnection();
		Statement statement = connection.createStatement();
		Book book = new Book();
		System.out.println("Enter Name");
		sc= new Scanner(System.in);
		book.setName(sc.next());
		System.out.println("Enter Author");
		sc= new Scanner(System.in);
		book.setAuthor(sc.next());
		System.out.println("Enter Subject");
		sc= new Scanner(System.in);
		book.setSubject(sc.next());
		System.out.println("Enter Price");
		sc= new Scanner(System.in);
		book.setPrice(sc.nextFloat());
		System.out.println("Enter Isbn");
		sc= new Scanner(System.in);
		book.setIsbn(sc.nextLong());

//		String sql = "INSERT INTO books (name, author, subject, price, isbn) VALUES ('"+book.getName()+"','"+book.getAuthor()+"','"+book.getSubject()+
//		"','"+book.getPrice()+"','"+book.getIsbn()+"';";

//		statement.executeUpdate(sql);
		
	}

	public static void editBook(int id) throws Exception {
		Connection connection = DBUtils.getConnection();
		Statement statement = connection.createStatement();
		Scanner sc = new Scanner(System.in);

		int choice = 100;
		System.out.println("Enter the Name of book which you want to edit");
		sc = new Scanner(System.in);
		String name = sc.nextLine();
		String sql1 = "select * from books where name = '" + name + "'";
		int bookid = 0;
		try (ResultSet rs = statement.executeQuery(sql1);) {
			if (rs.next()) {
				bookid = rs.getInt("id");
			} else
				System.out.println("Book is not there");

		}
		System.out.println("Select what you want to edit ");
		while (choice != 0) {
			System.out.println("0.Exit ");
			System.out.println("1.Name ");
			System.out.println("2.Author");
			System.out.println("3.Subject");
			System.out.println("4.Price");
			System.out.println("5.isbn");
			choice = sc.nextInt();
			if (choice == 1) {
				System.out.println("Enter new name");
				sc = new Scanner(System.in);
				String newName = sc.next();
				String sql = "UPDATE books SET  name = '" + newName + "' where id = '" + bookid + "'";
				statement.executeUpdate(sql);
				System.out.println("Edited SuccessFully");

			} else if (choice == 2) {
				System.out.println("Enter new Author");
				sc = new Scanner(System.in);
				String newAuthor = sc.next();
				String sql = "UPDATE books SET  author = '" + newAuthor + "' where id = '" + bookid + "'";
				statement.executeUpdate(sql);
				System.out.println("Edited SuccessFully");
			} else if (choice == 3) {
				System.out.println("Enter new Subject");
				sc = new Scanner(System.in);
				String newSubject = sc.next();
				String sql = "UPDATE users SET  phone = '" + newSubject + "' where id = '" + bookid + "'";
				statement.executeUpdate(sql);
				System.out.println("Edited SuccessFully");
			} else if (choice == 4) {
				System.out.println("Enter new Price");
				sc = new Scanner(System.in);
				String newPrice = sc.next();
				String sql = "UPDATE users SET  phone = '" + newPrice + "' where id = '" + bookid + "'";
				statement.executeUpdate(sql);
				System.out.println("Edited SuccessFully");
			} else if (choice == 5) {
				System.out.println("Enter new isbn");
				sc = new Scanner(System.in);
				String newIsbn = sc.next();
				String sql = "UPDATE users SET  phone = '" + newIsbn + "' where id = '" + bookid + "'";
				statement.executeUpdate(sql);
				System.out.println("Edited SuccessFully");
			}
		}
	}
}
