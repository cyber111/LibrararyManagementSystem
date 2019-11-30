package test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import dbutils.DBUtils;

public class SignIn {

	public void userSignIn() throws Exception {

		Scanner scanner = new Scanner(System.in);

		System.out.print("UserName :");
		String username = scanner.next();
		System.out.print("\n");

		System.out.print("Password :");
		Scanner scanner1 = new Scanner(System.in);
		String password = scanner1.next();

		String password1 = "";
		String email = "";

		int id = 0;

		Connection connection = DBUtils.getConnection();
		Statement statement = connection.createStatement();

		String sql = "SELECT * FROM users WHERE email='" + username + "' AND password = '" + password + "';";

		try (ResultSet rs1 = statement.executeQuery(sql)) {

//			System.out.println(sql);

			if (rs1.next()) {
				if (rs1.getString("role").equals("owner")) {
					password1 = rs1.getString("password");
					email = rs1.getString("email");
					id = rs1.getInt("id");
				}

			}
		}
		if (username.equals(email) && password.equals(password1)) {
			// callOwnerMenu class
			OwnerMenu oMenu = new OwnerMenu();
			oMenu.loadOwnerMethods(id);

		} else {

			Scanner sc = new Scanner(System.in);
			try (ResultSet rs = statement.executeQuery(sql)) {
				if (rs.next()) {
					if (rs.getString("role").equals("librarian")) {
						System.out.println("welcome Lirabrian");
						id = rs.getInt("id");
						new LibrarianMenu().loadLibrarianMethods(id);

					} else {
						id = rs.getInt("id");
						MemberMenu member = new MemberMenu();
						member.loadMemberMethods(id);
					}
				} else
					System.out.println("UserNotFpund");
			}

		}
		System.out.print("\n");
	}

}
