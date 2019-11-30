package test;

import java.util.Scanner;

public class HomePage 
{
	static Scanner sc = new Scanner(System.in);
	public static menuOption menuList()
	{
		System.out.println("0.");
		System.out.println("1.Sign In");
		System.out.println("2.Sign Up");	
		return menuOption.values()[sc.nextInt()];

	}

	public enum menuOption 
	{
		EXIT, SIGNIN, SIGNUP;
	}
	
	public void loadUsers() throws Exception
	{
		menuOption choice;
		while((choice = HomePage.menuList()) != null)
		{

			switch (choice) {
			case EXIT :
				break;
			case SIGNIN:
				SignIn signIn  = new SignIn();
				signIn.userSignIn();
				// CHECK USER NAME FROM DATABSE I.E. ROLE OF THAT USER
				break;
			case SIGNUP:
				new SignUp().userSignUp();
				//CALL SIGN UP
				//register
				break;
				

			default:
				break;
			}
		}
	}

}
