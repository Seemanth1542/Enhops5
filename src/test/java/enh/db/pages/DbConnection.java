package enh.db.pages;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import utilities.ConfigReader;

public class DbConnection {
	public static Connection con;
	public static void main(String[] args) throws SQLException {
		con = DriverManager.getConnection(
				"jdbc:mysql://avileapuat.ckfsniqh1gly.us-west-2.rds.amazonaws.com:3306/AviLeap", "AviLeap_Read",
				"AviLeap_Read");
		
		Statement stmt = con.createStatement();
		//ResultSet rs = stmt.executeQuery("SELECT * FROM `DailyFlightSchedule_Merged`where date(std)= '2020-01-27' and operationunit = 22");
		ResultSet rs = stmt.executeQuery("SELECT * FROM `DailyFlightSchedule_Merged` where operationunit = 22");
		while(rs.next()) {
			
			String logid=rs.getString("logid");
			//String flightNumber_Arrival=rs.getString("FlightNumber_Arrival");
			//String gmrpk_Arrival=rs.getString("GMRPK_Arrival");
			System.out.println(logid);
			//System.out.println(flightNumber_Arrival);
		}
		
	}
}
