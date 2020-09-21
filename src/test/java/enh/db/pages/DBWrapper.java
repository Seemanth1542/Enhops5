package enh.db.pages;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBWrapper {

	public static Connection con;

	public static ResultSet Connect(String dB_Queru, String environment) throws SQLException {

		if (environment.equalsIgnoreCase("prod"))
		{
			//System.out.println("prod url");
			// Prod url DB
			con = DriverManager.getConnection(
					"jdbc:mysql://avileapuat.ckfsniqh1gly.us-west-2.rds.amazonaws.com:3306/AviLeap", "AviLeap_Read",
					"AviLeap_Read");
		}
		// Test url DB
		if (environment.equalsIgnoreCase("test")) {
			//System.out.println("test url");
			con = DriverManager.getConnection(
					"jdbc:mysql://avileap-test.ckfsniqh1gly.us-west-2.rds.amazonaws.com:3306/AviLeap", "AviLeap_Read",
					"AviLeap_Read");
		}
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(dB_Queru);
		return rs;
	}

	public static void dbConnectionClose() throws SQLException {
		con.close();
	}

}
