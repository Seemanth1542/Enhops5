package enh.db.cases;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import utilities.HtmlReportUtil;

public class Scheduled_And_Sensor_ATA_DIAL_Delhi {
	
	public static int totalScheduledArrival=0;
	public static int notNullSensorATA =0;
	
	public static int onBlockFromSensor=0;
			
	public static ArrayList<String> sensorATA_NullList = new ArrayList<String>();
	public static ArrayList<String> onBlockFromSchedule_List = new ArrayList<String>();
	
	public static ArrayList<String> status0List = new ArrayList<String>();
	public static ArrayList<String> status1List = new ArrayList<String>();
	
	public static StringBuilder email_report_Scheduled_And_Sensor_ATA_For_Delhi1 = new StringBuilder();
	public static StringBuilder email_report_Scheduled_And_Sensor_ATA_For_Delhi2 = new StringBuilder();
	public static StringBuilder email_report_Scheduled_And_Sensor_ATA_For_Delhi3 = new StringBuilder();
	public static StringBuilder email_report_Scheduled_And_Sensor_ATA_For_Delhi4 = new StringBuilder();
	
	public static void scheduledAndSensorATAForDelhi_Report(int operationunit) throws Exception{
		
		ResultSet result = DBWrapper.Connect("SELECT count(*) FROM `DailyFlightScheduleArrival_GMR` where date(IFNULL(sta,eta))= '"+SQL_Queries.yesterDate()+"' and operationunit= "+operationunit+"");
		while (result.next())
		{				
			totalScheduledArrival = result.getInt("count(*)");
			System.out.println(totalScheduledArrival);
		}	
		
		ResultSet result2 = DBWrapper.Connect("SELECT count(*) FROM `DailyFlightSchedule_Merged` where gmrpk_arrival in \r\n"
				+ "(SELECT gmrpk FROM `DailyFlightScheduleArrival_GMR`where date(IFNULL(sta,eta))= '"+SQL_Queries.yesterDate()+"' and operationunit= "+operationunit+") and sensor_ata is not null");
				while (result2.next())
				{				
					notNullSensorATA = result2.getInt("count(*)");
					System.out.println(notNullSensorATA);
				}
		
		ResultSet result3 = DBWrapper.Connect("SELECT logid, flightnumber_arrival, sta, eta, ata FROM `DailyFlightSchedule_Merged` where gmrpk_arrival in "
				+ "(SELECT gmrpk FROM `DailyFlightScheduleArrival_GMR`where date(IFNULL(sta,eta))= '"+SQL_Queries.yesterDate()+"' and operationunit= "+operationunit+") and sensor_ata is null");
				while (result3.next())
				{
					String str_LogID = result3.getString("logid");
					String str_flightNumber_Arrival= result3.getString("flightnumber_arrival");
					String str_flight_STA = result3.getString("sta");
					String str_flight_ETA = result3.getString("eta");
					String str_flight_ATA = result3.getString("ata");
							
						sensorATA_NullList.add(str_LogID);
				}
						
		ResultSet result4 = DBWrapper.Connect("SELECT count(*) FROM `EquipActivityLogs` where flight_pk in (SELECT logid FROM `DailyFlightSchedule_Merged`\r\n" + 
		" where date(IFNULL(sta,eta))= '"+SQL_Queries.yesterDate()+"' and operationunit= "+operationunit+" and on_block_time is not null) and operationname = 'onb'");
						while (result4.next())
						{				
							onBlockFromSensor = result4.getInt("count(*)");
							System.out.println(onBlockFromSensor);
						}
		
		ResultSet result5 = DBWrapper.Connect("SELECT logid, flightnumber_arrival, sensor_ATA, On_block_time, (case when (sensor_ATA < On_Block_Time) then 1 else 0 end) as Status, \r\n"
								+ "CONCAT('',TIMEDIFF(sensor_ata, on_block_time)) as difference FROM `DailyFlightSchedule_Merged` where date(IFNULL(sta,eta))= '"+SQL_Queries.yesterDate()+"'\r\n "
								+ "and operationunit = "+operationunit+" and (sensor_ata is not null and On_block_time is not null)");
						while (result5.next())
						{				
							String str_LogID = result5.getString("logid");
							String str_flight_NumberArrival= result5.getString("flightnumber_arrival");
							String str_flight_SensorATA= result5.getString("sensor_ata");
							String str_flight_OnBlock= result5.getString("on_block_time");
							String str_status= result5.getString("status");
							String str_difference_between_OnBlockAndSensorATA= result5.getString("difference");
							
							if (str_status.contains("1"))
							{
								status1List.add(str_LogID);
								
							}
							else if (str_status.contains("0"))
							{
								status0List.add(str_LogID);
							}		
						}
		email_report_Scheduled_And_Sensor_ATA_For_Delhi1.append("<style>table#t01, th, td {border: 1px solid black;border-collapse: collapse;}table#t01 th{background-color:#80e5ff; color: white;} table#t01 tr:nth-child(even) {background-color: #f2f2f2;} table#t01 tr:nth-child(odd) { background-color: #DFEDEC;}table#t01 th, td {padding: 5px;}table#t01 th,td {text-align: center;} table#t01 caption {color: #336600;font-weight: bold;}</style>");
		email_report_Scheduled_And_Sensor_ATA_For_Delhi1.append("<h4 align=\"center\" style=\"color:#336600;\">Airport Name : DIAL-Delhi</h4>");
		email_report_Scheduled_And_Sensor_ATA_For_Delhi1.append("<h4 align=\"center\" style=\"color:#336600;\">Executed For :Scheduled and Sensor-ATA</h4><h5 align=\"center\" style=\"color:#336600;\" >Execution Time: "+SQL_Queries.todayDayDateTime()+" </h5>");
		email_report_Scheduled_And_Sensor_ATA_For_Delhi1.append("<table style=\"width:100%\" id=\"t01\"><tr><th style=\"width:10%\"><b>Date</b></th><th style=\"width:15%\"><b>Total No. of Flights Scheduled Arrival</b></th>"
								+ " <th style=\"width:15%\"><b>No. of flights detected by Sensor</b></th>"
						 		+ "<th style=\"width:15%\"><b>No. of flights NOT detected by Sensor</b></th>"
						 		+ "<th style=\"width:20%\"><b>No. of flights On-Block time is detected by Sensor</b></th>"
						 		+ " </tr>");
		email_report_Scheduled_And_Sensor_ATA_For_Delhi1.append(" <tr> <td><b>"+SQL_Queries.yesterDate()+"</b></td> <td><b>"+totalScheduledArrival+"</b></td>"
						 		+ " <td> <b style=\"color:green;\">"+notNullSensorATA+"</b></td> <td><b style=\"color:red;\">"+sensorATA_NullList.size()+"</b></td> <td><b style=\"color:green;\">"+onBlockFromSensor+"</b></td></tr></table>");			 	
		
		email_report_Scheduled_And_Sensor_ATA_For_Delhi2.append("<br><br>");
		email_report_Scheduled_And_Sensor_ATA_For_Delhi2.append("<style>table#t01, th, td {border: 1px solid black;border-collapse: collapse;}table#t01 th{background-color:#80e5ff; color: white;} table#t01 tr:nth-child(even) {background-color: #f2f2f2;} table#t01 tr:nth-child(odd) { background-color: #DFEDEC;}table#t01 th, td {padding: 5px;}table#t01 th,td {text-align: center;} table#t01 caption {color: #336600;font-weight: bold;}</style>");
		email_report_Scheduled_And_Sensor_ATA_For_Delhi2.append("<table style=\"width:100%\" id=\"t01\"><caption> Total Flights LANDING (Not Detected by Flight Sensor but detected from other data source) </caption><tr>"
								+ "<th style=\"width:10%\"><b>LogID</b></th>"
								+ "<th style=\"width:15%\"><b>ArrivalFlight No.</b></th> "
								+ "<th style=\"width:15%\"><b>STA</b></th>"
						 		+ "<th style=\"width:15%\"><b>ETA</b></th>"
						 		+ "<th style=\"width:20%\"><b>ATA</b></th>"
						 		+ " </tr>");
		if (sensorATA_NullList.size()>0) {
		ResultSet result31 = DBWrapper.Connect("SELECT logid, flightnumber_arrival, sta, eta, ata FROM `DailyFlightSchedule_Merged` where gmrpk_arrival in "
				+ "(SELECT gmrpk FROM `DailyFlightScheduleArrival_GMR`where date(IFNULL(sta,eta))= '"+SQL_Queries.yesterDate()+"' and operationunit= "+operationunit+") and sensor_ata is null");
				while (result3.next())
				{
					String str_LogID = result31.getString("logid");
					String str_flightNumber_Arrival= result31.getString("flightnumber_arrival");
					String str_flight_STA = result31.getString("sta");
					String str_flight_ETA = result31.getString("eta");
					String str_flight_ATA = result31.getString("ata");
							
				}
		}
	DBWrapper.dbConnectionClose();
	}
	
}	
		

	