package enh.db.pages;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AV_2603_Check_number_of_duplicate_activities_Equipment_and_Flight {
	public static Timestamp flogdate = null;
	public static Timestamp tlogdate = null;
	public static String devid = null;
	public static void main(String args[]) throws SQLException {
		
		 ResultSet result1 = DBWrapper.Connect("SELECT * FROM `DailyFlightSchedule_Merged` where"
		 		+ " (date(atd)='2020-01-20' or date(sensor_atd)='2020-01-20') and operationunit=4","prod");
	
		 while (result1.next()){				
				String str_LogID = result1.getString("Logid");	
 
				 ResultSet result2 = DBWrapper.Connect("SELECT count(logid) FROM `EquipActivityLogs` where operationname= 'pcd' and flight_pk= "+str_LogID+"","prod");
				 while (result2.next()){					 
						int LogID_count = result2.getInt("count(logid)");	
						if(LogID_count>1) {
							System.out.println("===================  "+LogID_count+" - "+str_LogID+"==================");
							 ResultSet result3 = DBWrapper.Connect("SELECT * FROM `EquipActivityLogs` where operationname= 'pcd' and flight_pk= "+str_LogID+"","prod");
							 while (result3.next()){					 
								 String str_LogID1 = result3.getString("Logid");
								 String str_devid = result3.getString("devid");
								 Timestamp str_flogdate = result3.getTimestamp("flogdate");
								 Timestamp str_tlogdate = result3.getTimestamp("tlogdate");
								 String str_operationname = result3.getString("operationname");
								//System.out.println("1 : "+flogdate+" - "+tlogdate);
									System.out.println(str_LogID+" - "+str_LogID1+" - "+str_devid+" - "+str_flogdate+" - "+str_tlogdate+" - "+str_operationname);	 
	
								/* if(flogdate!=null && tlogdate!=null && devid!=null) {
								 if(str_devid.equalsIgnoreCase(devid)&&str_flogdate.equals(flogdate) ) {
									System.out.println("str_flogdate.equals(flogdate)");
									// System.out.println(str_LogID1+" - "+str_devid+" - "+str_flogdate+" - "+str_tlogdate+" - "+str_operationname);	 

								}
								if(str_devid.equalsIgnoreCase(devid)&&str_tlogdate.equals(tlogdate)) {
									System.out.println("str_tlogdate.equals(tlogdate)");
									// System.out.println(str_LogID1+" - "+str_devid+" - "+str_flogdate+" - "+str_tlogdate+" - "+str_operationname);	 
								}
								if((str_devid.equalsIgnoreCase(devid) && flogdate.before(str_flogdate) && tlogdate.before(str_tlogdate))) {
									System.out.println("flogdate.before(str_flogdate) && tlogdate.before(str_tlogdate))");
									// System.out.println(str_LogID1+" - "+str_devid+" - "+str_flogdate+" - "+str_tlogdate+" - "+str_operationname);	 

								}
								
								if((str_devid.equalsIgnoreCase(devid) && flogdate.after(str_flogdate)&&tlogdate.after(str_tlogdate))) {
									System.out.println(" flogdate.after(str_flogdate)&&tlogdate.after(str_tlogdate))");
									// System.out.println(str_LogID1+" - "+str_devid+" - "+str_flogdate+" - "+str_tlogdate+" - "+str_operationname);	 
								}
								if(str_devid.equalsIgnoreCase(devid) && flogdate.before(str_flogdate)&&tlogdate.after(str_tlogdate)) {
									System.out.println(" flogdate.before(str_flogdate)&&tlogdate.after(str_tlogdate))");
									// System.out.println(str_LogID1+" - "+str_devid+" - "+str_flogdate+" - "+str_tlogdate+" - "+str_operationname);	 
								}
								if((str_devid.equalsIgnoreCase(devid) && flogdate.after(str_flogdate)&&tlogdate.before(str_tlogdate))) {
									System.out.println(" flogdate.after(str_flogdate)&&tlogdate.before(str_tlogdate))");
									// System.out.println(str_LogID1+" - "+str_devid+" - "+str_flogdate+" - "+str_tlogdate+" - "+str_operationname);	 
								}*/
								 
									if(str_devid.equalsIgnoreCase(devid)) {
								if((str_flogdate.before(flogdate) && str_tlogdate.before(flogdate))
							||(str_flogdate.after(tlogdate)&& str_tlogdate.after(tlogdate))) {
									System.out.println("valid");
									//System.out.println((str_devid.equalsIgnoreCase(devid) && !str_flogdate.before(flogdate) && !str_tlogdate.before(flogdate));	 
								}
								else {
									System.out.println("in valid");
									// System.out.println(str_LogID+" - "+str_LogID1+" - "+str_devid+" - "+str_flogdate+" - "+str_tlogdate+" - "+str_operationname);	 

								}
								/*if(str_flogdate.equals(flogdate) ) {
									System.out.println("in valid");
									// System.out.println(str_LogID1+" - "+str_devid+" - "+str_flogdate+" - "+str_tlogdate+" - "+str_operationname);	 

								}
								if(str_tlogdate.equals(tlogdate)) {
									System.out.println("in valid");
									// System.out.println(str_LogID1+" - "+str_devid+" - "+str_flogdate+" - "+str_tlogdate+" - "+str_operationname);	 
								}*/
									}
								 
								 flogdate = str_flogdate;
								 tlogdate = str_tlogdate;
								 devid=str_devid;
								 //System.out.println("2 : "+flogdate+" - "+tlogdate +" - "+devid);
							 }
							 flogdate = null;
							 tlogdate = null;
							 devid=null;
							//System.out.println("3 : "+flogdate+" - "+tlogdate);
							 }else {
								// System.out.println("else  "+LogID_count+" - "+str_LogID);
							 }
						
				 }
	
	
		 }
		 DBWrapper.dbConnectionClose(); 
		 
	}

}
