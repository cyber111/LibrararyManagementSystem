package test;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Scanner;

import dbutils.DBUtils;
import pojo.User;

public class SignUp 
{
		public void userSignUp() throws Exception
		{
			Connection connection = DBUtils.getConnection();
			Statement statement = connection.createStatement();
			Scanner sc = new Scanner(System.in);
			User user = new User();
			System.out.println("Enter Name");
			user.setName(sc.next());
			System.out.println("Enter Email");
			sc= new Scanner(System.in);
			user.setEmail(sc.next());
			System.out.println("Enter Phone");
			user.setPhone(sc.nextLong());
			System.out.println("Enter Password");
			sc= new Scanner(System.in);
			user.setPassword(sc.next());
			
			String sql = "INSERT INTO users(name, email, phone, password, role) VALUES ('"+user.getName()+"','" +user.getEmail() +"','"+user.getPhone()+"','"+user.getPassword()+"','"+ user.getRole()+"');";
			

			statement.executeUpdate(sql);
		}
}
