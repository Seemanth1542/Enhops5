package enh.db.cases;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import utilities.HtmlReportUtil;

public class AV_2293_Scheduled_And_Sensor_ATA_DIAL_Delhi {
	
	public static int totalScheduledArrival=0;
	public static int notNullSensorATA =0;
	
	public static int onBlockFromSensor=0;
			
	public static ArrayList<String> sensorATA_NullList = new ArrayList<String>();
	public static ArrayList<String> onBlockFromSchedule_List = new ArrayList<String>();
	
	public static ArrayList<String> status0List = new ArrayList<String>();
	public static ArrayList<String> status1List = new ArrayList<String>();
	public static StringBuilder emailbody = new StringBuilder();
	public static StringBuilder email_report_Scheduled_And_Sensor_ATA_For_Delhi1 = new StringBuilder();
	public static StringBuilder email_report_Scheduled_And_Sensor_ATA_For_Delhi2 = new StringBuilder();
	public static StringBuilder email_report_Scheduled_And_Sensor_ATA_For_Delhi3 = new StringBuilder();
	
	
	public static void scheduledAndSensorATAForDelhi_Report(int operationunit,String environment) throws Exception{
		
		ResultSet result = DBWrapper.Connect("SELECT count(*) FROM `DailyFlightScheduleArrival_GMR` where date(IFNULL(sta,eta))= '"+SQL_Queries.yesterDate()+"' and operationunit= "+operationunit+"",environment);
		while (result.next())
		{				
			totalScheduledArrival = result.getInt("count(*)");
			System.out.println(totalScheduledArrival);
		}	
		
		ResultSet result2 = DBWrapper.Connect("SELECT count(*) FROM `DailyFlightSchedule_Merged` where gmrpk_arrival in \r\n"
				+ "(SELECT gmrpk FROM `DailyFlightScheduleArrival_GMR`where date(IFNULL(sta,eta))= '"+SQL_Queries.yesterDate()+"' and operationunit= "+operationunit+") and sensor_ata is not null",environment);
				while (result2.next())
				{				
					notNullSensorATA = result2.getInt("count(*)");
					System.out.println(notNullSensorATA);
				}
		
		ResultSet result3 = DBWrapper.Connect("SELECT logid, flightnumber_arrival, sta, eta, ata FROM `DailyFlightSchedule_Merged` where gmrpk_arrival in "
				+ "(SELECT gmrpk FROM `DailyFlightScheduleArrival_GMR`where date(IFNULL(sta,eta))= '"+SQL_Queries.yesterDate()+"' and operationunit= "+operationunit+") and sensor_ata is null",environment);
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
		" where date(IFNULL(sta,eta))= '"+SQL_Queries.yesterDate()+"' and operationunit= "+operationunit+" and on_block_time is not null) and operationname = 'onb'",environment);
				while (result4.next())
				{				
					onBlockFromSensor = result4.getInt("count(*)");
					System.out.println(onBlockFromSensor);
				}
		
		ResultSet result5 = DBWrapper.Connect("SELECT logid, flightnumber_arrival, sensor_ATA, On_block_time, (case when (sensor_ATA < On_Block_Time) then 1 else 0 end) as Status,\r\n" + 
				"CONCAT('',TIMEDIFF(sensor_ata, on_block_time)) as difference FROM `DailyFlightSchedule_Merged` where date(IFNULL(sta,eta))= '"+SQL_Queries.yesterDate()+"' and \r\n " +
				"operationunit= "+operationunit+" and (sensor_ata is not null and On_block_time is not null)",environment);

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
							if (str_status.contains("0"))
							{
								status0List.add(str_LogID);
							}		
				}
		email_report_Scheduled_And_Sensor_ATA_For_Delhi1.append("<style>table#t01, th, td {border: 1px solid black;border-collapse: collapse;}table#t01 th{background-color:#80e5ff;} table#t01 tr:nth-child(even) {background-color: #f2f2f2;} table#t01 tr:nth-child(odd) { background-color: #DFEDEC;}table#t01 th, td {padding: 5px;}table#t01 th,td {text-align: center;} table#t01 caption {color: #008ae6;font-weight: bold;}</style>");
		email_report_Scheduled_And_Sensor_ATA_For_Delhi1.append("<h4 align=\"center\" style=\"color:#008ae6;\">Airport Name : DIAL-Delhi</h4>");
		email_report_Scheduled_And_Sensor_ATA_For_Delhi1.append("<h4 align=\"center\" style=\"color:#008ae6;\">Executed For :Scheduled and Sensor-ATA</h4><h5 align=\"center\" style=\"color:#008ae6;\" >Execution Time: "+SQL_Queries.todayDayDateTime()+" </h5>");
		email_report_Scheduled_And_Sensor_ATA_For_Delhi1.append("<h5 align=\"center\" style=\"color:#008ae6;\" >Environment: "+environment+" </h5>");

		email_report_Scheduled_And_Sensor_ATA_For_Delhi1.append("<table style=\"width:100%\" id=\"t01\"><tr><th style=\"width:10%\"><b>Date</b></th><th style=\"width:15%\"><b>Total No. of Flights Scheduled Arrival</b></th>"
								+ " <th style=\"width:15%\"><b>No. of flights detected by Sensor</b></th>"
						 		+ "<th style=\"width:15%\"><b>No. of flights NOT detected by Sensor</b></th>"
						 		+ "<th style=\"width:20%\"><b>No. of flights On-Block time is detected by Sensor</b></th>"
						 		+ " </tr>");
		email_report_Scheduled_And_Sensor_ATA_For_Delhi1.append(" <tr> <td><b>"+SQL_Queries.yesterDate()+"</b></td> <td><b>"+totalScheduledArrival+"</b></td>"
						 		+ " <td> <b style=\"color:green;\">"+notNullSensorATA+"</b></td> <td><b style=\"color:red;\">"+sensorATA_NullList.size()+"</b></td> <td><b style=\"color:green;\">"+onBlockFromSensor+"</b></td></tr></table>");			 	
		emailbody.append("<style>table#t01, th, td {border: 1px solid black;border-collapse: collapse;}table#t01 th{background-color:#80e5ff;} table#t01 tr:nth-child(even) {background-color: #f2f2f2;} table#t01 tr:nth-child(odd) { background-color: #DFEDEC;}table#t01 th, td {padding: 5px;}table#t01 th,td {text-align: center;} table#t01 caption {color: #008ae6;font-weight: bold;}</style>");
	
		emailbody.append("<h4 align=\"center\" style=\"color:#008ae6;\">Executed For :Scheduled and Sensor-ATA for DIAL-Delhi</h4>");


		emailbody.append("<table style=\"width:100%\" id=\"t01\"><tr><th style=\"width:10%\"><b>Date</b></th><th style=\"width:15%\"><b>Total No. of Flights Scheduled Arrival</b></th>"
								+ " <th style=\"width:15%\"><b>No. of flights detected by Sensor</b></th>"
						 		+ "<th style=\"width:15%\"><b>No. of flights NOT detected by Sensor</b></th>"
						 		+ "<th style=\"width:20%\"><b>No. of flights On-Block time is detected by Sensor</b></th>"
						 		+ " </tr>");
		emailbody.append(" <tr> <td><b>"+SQL_Queries.yesterDate()+"</b></td> <td><b>"+totalScheduledArrival+"</b></td>"
						 		+ " <td> <b style=\"color:green;\">"+notNullSensorATA+"</b></td> <td><b style=\"color:red;\">"+sensorATA_NullList.size()+"</b></td> <td><b style=\"color:green;\">"+onBlockFromSensor+"</b></td></tr></table>");			 	
	
		email_report_Scheduled_And_Sensor_ATA_For_Delhi2.append("<br><br>");
		email_report_Scheduled_And_Sensor_ATA_For_Delhi2.append("<style>table#t01, th, td {border: 1px solid black;border-collapse: collapse;}table#t01 th{background-color:#80e5ff; } table#t01 tr:nth-child(even) {background-color: #f2f2f2;} table#t01 tr:nth-child(odd) { background-color: #DFEDEC;}table#t01 th, td {padding: 5px;}table#t01 th,td {text-align: center;} table#t01 caption {color: #008ae6;font-weight: bold;}</style>");
		email_report_Scheduled_And_Sensor_ATA_For_Delhi2.append("<table style=\"width:100%\" id=\"t01\"><caption> Total Flights LANDING (Not Detected by Flight Sensor but detected from other data source) </caption><tr>"
								+ "<th style=\"width:10%\"><b>LogID</b></th>"
								+ "<th style=\"width:15%\"><b>ArrivalFlight No.</b></th> "
								+ "<th style=\"width:15%\"><b>STA</b></th>"
						 		+ "<th style=\"width:15%\"><b>ETA</b></th>"
						 		+ "<th style=\"width:20%\"><b>ATA</b></th>"
						 		+ " </tr>");
		if (sensorATA_NullList.size()>0) {
		ResultSet result31 = DBWrapper.Connect("SELECT logid, flightnumber_arrival, sta, eta, ata FROM `DailyFlightSchedule_Merged` where gmrpk_arrival in "
				+ "(SELECT gmrpk FROM `DailyFlightScheduleArrival_GMR`where date(IFNULL(sta,eta))= '"+SQL_Queries.yesterDate()+"' and operationunit= "+operationunit+") and sensor_ata is null",environment);
				while (result31.next())
				{
					String str_LogID = result31.getString("logid");
					String str_flightNumber_Arrival= result31.getString("flightnumber_arrival");
					String str_flight_STA = result31.getString("sta");
					String str_flight_ETA = result31.getString("eta");
					String str_flight_ATA = result31.getString("ata");
					email_report_Scheduled_And_Sensor_ATA_For_Delhi2.append(" <tr> <td><b style=\"color:red;\">"+str_LogID+"</b></td> <td><b style=\"color:red;\">"+str_flightNumber_Arrival+"</b></td>"
					 		+ " <td> <b style=\"color:red;\">"+str_flight_STA+"</b></td> <td><b style=\"color:red;\">"+str_flight_ETA+"</b></td> <td><b style=\"color:red;\">"+str_flight_ATA+"</b></td></tr>");				
				}
				}
		else {
			email_report_Scheduled_And_Sensor_ATA_For_Delhi2.append("<tr><td colspan=\"5\"><b style=\"color:red;\">No values found </b></td></tr>");
		}
		email_report_Scheduled_And_Sensor_ATA_For_Delhi2.append("</table>");
		
		email_report_Scheduled_And_Sensor_ATA_For_Delhi3.append("<br><br>");
		email_report_Scheduled_And_Sensor_ATA_For_Delhi3.append("<style>table#t01, th, td {border: 1px solid black;border-collapse: collapse;}table#t01 th{background-color:#80e5ff;} table#t01 tr:nth-child(even) {background-color: #f2f2f2;} table#t01 tr:nth-child(odd) { background-color: #DFEDEC;}table#t01 th, td {padding: 5px;}table#t01 th,td {text-align: center;} table#t01 caption {color: #008ae6;font-weight: bold;}</style>");
		email_report_Scheduled_And_Sensor_ATA_For_Delhi3.append("<table style=\"width:100%\" id=\"t01\"><caption> Total Flights - LANDING time detected by flight Sensor is greater than On-Block</caption><tr>"
				+ "<th style=\"width:10%\"><b>LogID</b></th>"
				+ "<th style=\"width:15%\"><b>Arrival Flight No.</b></th> "
				+ "<th style=\"width:15%\"><b>Sensor_ATA</b></th> "
				+ "<th style=\"width:15%\"><b>On_Block_time</b></th> "
				+ "<th style=\"width:15%\"><b>Difference between On_Block_time and Sensor_ATA</b></th> "
				+ " </tr>");
		if (status0List.size()>0) {
		ResultSet result51 = DBWrapper.Connect("SELECT logid, flightnumber_arrival, sensor_ATA, On_block_time, (case when (sensor_ATA < On_Block_Time) then 1 else 0 end) as Status, \r\n"
					+ "CONCAT('',TIMEDIFF(sensor_ata, on_block_time)) as difference FROM `DailyFlightSchedule_Merged` where date(IFNULL(sta,eta))= '"+SQL_Queries.yesterDate()+"'\r\n "
					+ "and operationunit = "+operationunit+" and (sensor_ata is not null and On_block_time is not null)",environment);
				while (result51.next())
					{				
						String str_LogID = result51.getString("logid");
						String str_flight_NumberArrival= result51.getString("flightnumber_arrival");
						String str_flight_SensorATA= result51.getString("sensor_ata");
						String str_flight_OnBlock= result51.getString("on_block_time");
						String str_status= result51.getString("status");
						String str_difference_between_OnBlockAndSensorATA= result51.getString("difference");
						
						if(str_status.contains("0")) {
							email_report_Scheduled_And_Sensor_ATA_For_Delhi3.append(" <tr> <td><b style=\"color:red;\">"+str_LogID+"</b></td>"
										+ " <td><b style=\"color:red;\">"+str_flight_NumberArrival+"</b></td>"
										+ " <td> <b style=\"color:red;\">"+str_flight_SensorATA+"</b></td>"
										+ " <td><b style=\"color:red;\">"+str_flight_OnBlock+"</b></td> "
										+ "<td><b style=\"color:red;\">"+str_difference_between_OnBlockAndSensorATA+"</b></td></tr>");
							}
					}
		}
				else {				
					email_report_Scheduled_And_Sensor_ATA_For_Delhi3.append("<tr><td colspan=\"5\"><b style=\"color:red;\">No values found </b></td></tr>");
				}			
		email_report_Scheduled_And_Sensor_ATA_For_Delhi3.append("</table>");
		
		HtmlReportUtil.test.log(LogStatus.INFO, email_report_Scheduled_And_Sensor_ATA_For_Delhi1.toString());
		HtmlReportUtil.testHist.log(LogStatus.INFO, email_report_Scheduled_And_Sensor_ATA_For_Delhi1.toString());
		 
		 ExtentTest child0 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:green;\" align=\"center\">Total Flights Scheduled for Arrival ("+SQL_Queries.yesterDate()+"): "+totalScheduledArrival+"</b>");
		 child0.log(LogStatus.INFO, "<b style=\"color:green;\" align=\"center\">Total Flights Scheduled for Arrival ("+SQL_Queries.yesterDate()+"): "+totalScheduledArrival+"</b>");			
		 ExtentTest child00 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:green;\" align=\"center\">Total Flights Scheduled for Arrival ("+SQL_Queries.yesterDate()+"): "+totalScheduledArrival+"</b>");
		 child00.log(LogStatus.INFO, "<b style=\"color:green;\" align=\"center\">Total Flights Scheduled for Arrival ("+SQL_Queries.yesterDate()+"): "+totalScheduledArrival+"</b>");
	
		 ExtentTest child1 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:green;\" align=\"center\"> Total Flights LANDING (Detected by Flight Sensor): "+notNullSensorATA+"</b>");
		 child1.log(LogStatus.INFO, "<b style=\"color:green;\" align=\"center\"> Total Flights LANDING (Detected by Flight Sensor): "+notNullSensorATA+"</b>");
		 ExtentTest child11 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:green;\" align=\"center\"> Total Flights LANDING (Detected by Flight Sensor): "+notNullSensorATA+"</b>");
		 child11.log(LogStatus.INFO, "<b style=\"color:green;\" align=\"center\"> Total Flights LANDING (Detected by Flight Sensor): "+notNullSensorATA+"</b>");
		 
		 ExtentTest child2 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:red;\" align=\"center\"> Total Flights LANDING (Not Detected by Flight Sensor but detected from other data source): "+sensorATA_NullList.size()+"</b>");
		 child2.log(LogStatus.INFO, email_report_Scheduled_And_Sensor_ATA_For_Delhi2.toString());
		 ExtentTest child22 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:red;\" align=\"center\"> Total Flights LANDING (Not Detected by Flight Sensor but detected from other data source): "+sensorATA_NullList.size()+"</b>");
		 child22.log(LogStatus.INFO, email_report_Scheduled_And_Sensor_ATA_For_Delhi2.toString());
		 	 
		 ExtentTest child3 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:green;\" align=\"center\"> Total Flights - ONBLOCK (Detected by Flight Sensor): "+onBlockFromSensor+"</b>");
		 child3.log(LogStatus.INFO, "<b style=\"color:green;\" align=\"center\"> Total Flights - ONBLOCK (Detected by Flight Sensor): "+onBlockFromSensor+"</b>");
		 ExtentTest child33 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:green;\" align=\"center\"> Total Flights - ONBLOCK (Detected by Flight Sensor): "+onBlockFromSensor+"</b>");
		 child33.log(LogStatus.INFO, "<b style=\"color:green;\" align=\"center\"> Total Flights - ONBLOCK (Detected by Flight Sensor):: "+onBlockFromSensor+"</b>");
		 
		 ExtentTest child4 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:green;\" align=\"center\">Total Flights- LANDING time detected by Flight Sensor less than ONBLOCK: "+status1List.size()+"</b>");
		 child4.log(LogStatus.INFO, "<b style=\"color:green;\" align=\"center\">Total Flights- LANDING time detected by Flight Sensor less than ONBLOCK: "+status1List.size()+"</b>");			
		 ExtentTest child44 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:green;\" align=\"center\">Total Flights- LANDING time detected by Flight Sensor less than ONBLOCK: "+status1List.size()+"</b>");
		 child44.log(LogStatus.INFO, "<b style=\"color:green;\" align=\"center\">Total Flights- LANDING time detected by Flight Sensor less than ONBLOCK: "+status1List.size()+"</b>");
				 	 
		 ExtentTest child5 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:red;\" align=\"center\"> Total Flights- LANDING time detected by Flight Sensor is greater than ONBLOCK: "+status0List.size()+"</b>");
		 child5.log(LogStatus.INFO, email_report_Scheduled_And_Sensor_ATA_For_Delhi3.toString());
		 ExtentTest child55 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:red;\" align=\"center\"> Total Flights- LANDING time detected by Flight Sensor is greater than ONBLOCK : "+status0List.size()+"</b>");
		 child55.log(LogStatus.INFO, email_report_Scheduled_And_Sensor_ATA_For_Delhi3.toString());
		
		 HtmlReportUtil.test.appendChild(child0).appendChild(child1).appendChild(child2).appendChild(child3).appendChild(child4).appendChild(child5);
		 HtmlReportUtil.testHist.appendChild(child00).appendChild(child11).appendChild(child22).appendChild(child33).appendChild(child44).appendChild(child55);
		 DBWrapper.dbConnectionClose();
	}
	
}	
		

	