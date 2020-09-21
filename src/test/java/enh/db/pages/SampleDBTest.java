package enh.db.pages;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import org.testng.annotations.Test;

import com.mysql.jdbc.ResultSetMetaData;

public class SampleDBTest {
	public static String airlines3;
	public static Connection con;
	public static Statement stmt;
	public static String logid;
	public static ResultSet result4 ;
	public static ResultSet result2 ;
	public static ResultSet result3;
	public static ResultSet result5;
	public static int size;
	public static ArrayList list = new ArrayList();
	public static ArrayList list2 = new ArrayList();
	public static ArrayList list3 = new ArrayList();
	public static Date time1=null;
	public static Date time2=null;
	

	public static void bdTest() throws SQLException{
		/* con = DriverManager.getConnection(
				"jdbc:mysql://avileap-test.ckfsniqh1gly.us-west-2.rds.amazonaws.com:3306/AviLeap", "AviLeap_Read",
				"AviLeap_Read"); */
		con = DriverManager.getConnection(
				"jdbc:mysql://avileapuat.ckfsniqh1gly.us-west-2.rds.amazonaws.com:3306/AviLeap", "AviLeap_Read",
				"AviLeap_Read");
		
		 stmt = con.createStatement();
		 ResultSet result = stmt.executeQuery("SELECT * FROM COBTConfig WHERE Enabled =1 AND Airport =22");	
		while(result.next()){
			String airlines= result.getString("AirlinesList");
			//System.out.println(airlines);
			String airlines1=airlines.replace("\",\"", "|");
			//System.out.println(airlines1);
			String airlines2=airlines1.replace("\"]", "");
			//System.out.println(airlines2);
			airlines3=airlines2.replace("[\"", "");
			System.out.println(airlines3);
			break;
		}
		
				 result2 = stmt.executeQuery("SELECT * FROM DailyFlightSchedule_Merged WHERE COBT IS NOT NULL AND DATE( Off_Block_Time ) = '2019-11-14' and operationunit=22 ");
				while(result2.next()){
					//System.out.println("==========================================" );
					
					 logid= result2.getString("LogId");		 
					 list.add(logid);
					 //System.out.println(list);
				}
				for(int i= 0; i<list.size() ; i++){
				 result4 = stmt.executeQuery("SELECT * FROM COBTTimeline WHERE flightpk = "+list.get(i)+"");
				result4.last();
				//System.out.println("cobt count of flight "+list.get(i)+" is"+ result4.getRow());
				}
				for(int i= 0; i<list.size() ; i++){
					 result5 = stmt.executeQuery("SELECT * FROM COBTTimeline WHERE flightpk = "+list.get(i)+" ORDER BY `Id` DESC");
					 while(result5.next()){
							//System.out.println("==========================================" );
							
						 float offset3 = result5.getFloat("COBTOffset");
							//System.out.println("cobt value   "+offset3);		 
							list3.add(offset3);
							break;
						}
					
					}
				/* for(int k=0;k<list3.size();k++){
					 System.out.println("first value "+ list3.get(k));
				 }*/
				for(int i= 0; i<list.size() ; i++){
					System.out.println(" flight id "+list.get(i) );
			  result3 = stmt.executeQuery("SELECT * FROM COBTTimeline WHERE flightpk = "+list.get(i)+" ORDER BY `Id` DESC");
			 
				while(result3.next()){
					//System.out.println("==========================================" );
					 float offset = (float) list3.get(i); //299.367f;
						System.out.println("last value       " +offset);	
						float offset1 = result3.getFloat("COBTOffset");
				System.out.println("cobt value   "+offset1);	
				System.out.println("difference: "+(offset - offset1));	
				if(offset - offset1>20){
					System.out.println(list.get(i) + "  flight cobt difference is greaterthan 20 min");
					 list2.add(list.get(i));
					break;
				}
			
				}
				//break;
				}
				System.out.println("count "+list2.size());
			//	DBWrapper.dbConnectionClose();
				con.close();
	}
	
	public static void main(String args[]) throws SQLException {
		//time1="2020-01-21 03:40:22.0";
		
		
		
	}
	

}
