import java.io.IOException;
import java.sql.*;

import java.util.Scanner;

public class EditUser {
	
	private static Connection connection;

	public static void setConnection(Connection conn) {
		connection = conn;
	}


	public static void main(String[] args) {

		String dbSys = null;
		Scanner in = null;
		try {
			in = new Scanner(System.in);
			System.out.println("Please enter information for connection to the database");
			dbSys = readEntry(in, "Using Oracle (o) or MySql (m)? ");

		} catch (IOException e) {
			System.out.println("Problem with user input, please try again\n");
			System.exit(1);
		}
		// Prompt the user for connect information
		String username = null;
		String password = null;
		String connStr = null;
		String yesNo;
		try {
			if (dbSys.equals("o")) {
				username = readEntry(in, "Oracle username: ");
				password = readEntry(in, "Oracle password: ");
				yesNo = readEntry(in, "use canned Oracle connection string (y/n): ");
				if (yesNo.equals("y")) {
					String host = readEntry(in, "host: ");
					String port = readEntry(in, "port (often 1521): ");
					String sid = readEntry(in, "sid (site id): ");
					connStr = "jdbc:oracle:thin:@" + host + ":" + port + ":" + sid;
				} else {
					connStr = readEntry(in, "connection string: ");
				}
			} else if (dbSys.equals("m")) {// MySQL--
				username = readEntry(in, "MySQL username: ");
				password = readEntry(in, "MySQL password: ");
				yesNo = readEntry(in, "use canned MySql connection string (y/n): ");
				if (yesNo.equals("y")) {
					String host = readEntry(in, "host: ");
					String port = readEntry(in, "port (often 3306): ");
					String db = username + "db";
					connStr = "jdbc:mysql://" + host + ":" + port + "/" + db;
				} else {
					connStr = readEntry(in, "connection string: ");
				}
			}
		} catch (IOException e) {
			System.out.println("Problem with user input, please try again\n");
			System.exit(3);
		}
		System.out.println("using connection string: " + connStr);
		System.out.print("Connecting to the database...");
		Connection conn = null;
		try {
			conn = getConnected(connStr, username, password);
			UserDB.setConnection(conn);  // let UserDB know connection
			addMember(in, conn);
		} catch (SQLException e) {
			System.out.println("Problem with JDBC Connection\n");
			printSQLException(e);
			System.exit(4);
		} finally {
			closeConnection(conn);
		}
	}
	
	
	static void addMember(Scanner in, Connection conn) throws SQLException {
		String email=null;
		String firstname=null;
		String lastname=null;
		System.out.println("To add a member to the email list, enter name and email as follows:");
		
		try {
			
			email = readEntry(in, "Enter email: ");
			
		} catch (IOException e) {
			System.out.println("Problem with user input, please try again\n");
			return;
		}
		
		User user=new User(firstname,lastname,email);
		// validate  the parameters
		if (UserDB.emailExists(user.getEmail())) {
			
			user=UserDB.selectUser(email);
			System.out.println("User's First name : "+user.getFirstName());
				
		
		String yesNo;
         try {
			    yesNo = readEntry(in, "Do you want to change the First Name? (y/n): ");
				if (yesNo.equals("y")) {
					firstname = readEntry(in, "Enter new first name : ");
					email=user.getEmail();
					UserDB.UpdateFN(firstname,user.getEmail());
					user=UserDB.selectUser(email);
					System.out.println("Updated first name: "+user.getFirstName());
					System.out.println("User's Last Name : "+user.getLastName());
					yesNo = readEntry(in, "Do you want to change the Last Name? (y/n): ");
					if (yesNo.equals("y")) {
						lastname = readEntry(in, "Enter new last name : ");
						email=user.getEmail();
						UserDB.UpdateLN(lastname,user.getEmail());
						user=UserDB.selectUser(email);
						System.out.println("Updated Last name: "+user.getLastName());
						}
						else if(yesNo.equals("")){ }
						else if(yesNo.equals("n")){}
					
				} 
				else if(yesNo.equals("") || yesNo.equals("n")){
					System.out.println("User's Last Name : "+user.getLastName());
					try{
						yesNo = readEntry(in, "Do you want to change the Last Name? (y/n): ");
				if (yesNo.equals("y")) {
					lastname = readEntry(in, "Enter new last name : ");
					UserDB.UpdateLN(lastname,user.getEmail());
					user=UserDB.selectUser(email);
					System.out.println("Updated Last name: "+user.getLastName());
					}
					else if(yesNo.equals("")){ }
					else if(yesNo.equals("n")){}
					}
					
					catch (IOException e) {
						System.out.println("Problem with user input, please try again\n");
						System.exit(3);
					}
				}
					else{}
		}
				
		catch (IOException e) {
			System.out.println("Problem with user input, please try again\n");
			System.exit(3);
		}
		finally{}}
		else{System.out.println("Email does not exist");}}
	   
		
	
	public static void UpdateFN(String firstname1,String email) throws SQLException{
		
		String query="Update userdb set firstname=? where email=?";
		PreparedStatement ps = null;
		try{
			System.out.println("this is this :"+email+firstname1);
			if (UserDB.emailExists(email)) {
				System.out.println("lodo lai lyo");
			}
			else{ps = connection.prepareStatement(query);
				
		  ps.setString(1, firstname1);
	      ps.setString(2, email);
	      
	      ps.executeUpdate();
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
		finally{closePreparedStatement(ps);}
		
	}
	
	

		public static void closePreparedStatement(Statement ps) {
		try {
			if (ps != null) {
				ps.close();
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	public static void closeResultSet(ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
}
	public static Connection getConnected(String connStr, String user, String password) throws SQLException {

		System.out.println("using connection string: " + connStr);
		System.out.print("Connecting to the database...");
		System.out.flush();

		// Connect to the database
		Connection conn = DriverManager.getConnection(connStr, user, password);
		System.out.println("connected.");
		return conn;
	}
	
	public static void closeConnection(Connection conn) {
		if (conn != null) {
			try {
				conn.close(); // this also closes the Statement and
								// ResultSet, if any
			} catch (SQLException e) {
				System.out
						.println("Problem with closing JDBC Connection\n");
				printSQLException(e);
			}
		}
	}

	// print out all exceptions connected to e by nextException or getCause
	static void printSQLException(SQLException e) {
		// SQLExceptions can be delivered in lists (e.getNextException)
		// Each such exception can have a cause (e.getCause, from Throwable)
		while (e != null) {
			System.out.println("SQLException Message:" + e.getMessage());
			Throwable t = e.getCause();
			while (t != null) {
				System.out.println("SQLException Cause:" + t);
				t = t.getCause();
			}
			e = e.getNextException();
		}
	}

	// super-simple prompted input from user
	public static String readEntry(Scanner in, String prompt) throws IOException {
		System.out.print(prompt);
		return in.nextLine().trim();
	}
	
}
	

