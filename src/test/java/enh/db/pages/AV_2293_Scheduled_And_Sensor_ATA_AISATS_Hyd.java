package enh.db.pages;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import utilities.HtmlReportUtil;

public class AV_2293_Scheduled_And_Sensor_ATA_AISATS_Hyd {
	
	public static String totalScheduledArrival=null;
	public static int totalFlightsActuallyArrived = 0;
	public static int onBlockFromCV =0;
	public static String notNullSensorATA =null;
	
	public static String onBlockFromSensor=null;	
		
	public static ArrayList<String> sensorATA_NullList = new ArrayList<String>();
	public static ArrayList<String> onBlockFromOtherDataSource_list = new ArrayList<String>();
	
	public static ArrayList<String> status0List = new ArrayList<String>();
	public static ArrayList<String> status1List = new ArrayList<String>();
	public static StringBuilder emailbody = new StringBuilder();
	public static StringBuilder email_report_Scheduled_And_Sensor_ATA_For_AISATS_Hyd1 = new StringBuilder();
	public static StringBuilder email_report_Scheduled_And_Sensor_ATA_For_AISATS_Hyd2 = new StringBuilder();
	public static StringBuilder email_report_Scheduled_And_Sensor_ATA_For_AISATS_Hyd3 = new StringBuilder();
	public static StringBuilder email_report_Scheduled_And_Sensor_ATA_For_AISATS_Hyd4 = new StringBuilder();
	
		
	public static void scheduled_And_Sensor_ATA_For_AISATS_Hyderabad_Report(int operationunit, String environment) throws Exception {
		
	String querry_result=	"SELECT count(*) FROM `DailyFlightScheduleArrival_GMR`where \r\n" 
		+ "date(IFNULL(sta,mediator_sta))= '"+SQL_Queries.yesterDate()+"' and operationunit= "+operationunit+" and flightnumber regexp \r\n"
				+ "('G9|AI|9I|CX|EY|FZ|G8|J9|9W|S2|SV|MI|UL|TG|UK|WY')";
	System.out.println(querry_result);	
	ResultSet result = DBWrapper.Connect(querry_result,environment);
		while (result.next())
		{				
			totalScheduledArrival = result.getString("count(*)");
			System.out.println(totalScheduledArrival);
		}
		
		String querry_result1="SELECT count(*) FROM `DailyFlightSchedule_Merged` where \r\n" +
				"gmrpk_arrival in (SELECT gmrpk FROM `DailyFlightScheduleArrival_GMR`where date(IFNULL(sta,mediator_sta))= '"+SQL_Queries.yesterDate()+"' and operationunit="+operationunit+" \r\n"
				+ "and flightnumber regexp ('G9|AI|9I|CX|EY|FZ|G8|J9|9W|S2|SV|MI|UL|TG|UK|WY')) and sensor_ata is not null";
				ResultSet result1 = DBWrapper.Connect(querry_result1,environment);
				while (result1.next())
				{				
					totalFlightsActuallyArrived = result1.getInt("count(*)");
					System.out.println(totalFlightsActuallyArrived);
				}
		
		String querry_result2="SELECT count(*) FROM `DailyFlightSchedule_Merged` where \r\n" +
		"gmrpk_arrival in (SELECT gmrpk FROM `DailyFlightScheduleArrival_GMR`where date(IFNULL(sta,mediator_sta))= '"+SQL_Queries.yesterDate()+"' and operationunit="+operationunit+" \r\n"
		+ "and flightnumber regexp ('G9|AI|9I|CX|EY|FZ|G8|J9|9W|S2|SV|MI|UL|TG|UK|WY')) and sensor_ata is not null";
		ResultSet result2 = DBWrapper.Connect(querry_result2,environment);
		while (result2.next())
		{				
			notNullSensorATA = result2.getString("count(*)");
			System.out.println(notNullSensorATA);
		}
		String querry_result3="SELECT logid, flightnumber, sta, eta, ata FROM `DailyFlightScheduleArrival_GMR`where \r\n" +
		"date(IFNULL(sta,mediator_sta))= '"+SQL_Queries.yesterDate()+"' and operationunit= "+operationunit+" and \r\n "
				+ "flightnumber regexp ('G9|AI|9I|CX|EY|FZ|G8|J9|9W|S2|SV|MI|UL|TG|UK|WY') and sensor_ata is null";
		ResultSet result3 = DBWrapper.Connect(querry_result3,environment);
		while (result3.next())
		{
			String str_LogID = result3.getString("logid");
			String str_flight_Number= result3.getString("flightnumber");
			String str_flight_STA = result3.getString("sta");
			String str_flight_ETA = result3.getString("eta");
			String str_flight_ATA = result3.getString("ata");
			
			sensorATA_NullList.add(str_LogID);
		}		
		String querry_result4="SELECT count(*) FROM `EquipActivityLogs` where flight_pk in (SELECT logid FROM `DailyFlightSchedule_Merged` \r\n" + 
		"where date(IFNULL(sta,mediator_sta))= '"+SQL_Queries.yesterDate()+"'  and operationunit = "+operationunit+" and flightnumber_arrival regexp ('G9|AI|9I|CX|EY|FZ|G8|J9|9W|S2|SV|MI|UL|TG|UK|WY') \r\n"
		+ "and on_block_time is not null) and operationname = 'onb' and type = 'aircraft' order by flightno";
		ResultSet result4 = DBWrapper.Connect(querry_result4,environment);
		while (result4.next())
		{				
			onBlockFromSensor = result4.getString("count(*)");
			System.out.println(onBlockFromSensor);
		}
		String querry_result5="SELECT flight_pk, flightno FROM `EquipActivityLogs` where flight_pk in (SELECT logid FROM `DailyFlightSchedule_Merged` \r\n"
		+ "where date(IFNULL(sta,mediator_sta))= '"+SQL_Queries.yesterDate()+"' and operationunit = "+operationunit+" and flightnumber_arrival regexp ('G9|AI|9I|CX|EY|FZ|G8|J9|9W|S2|SV|MI|UL|TG|UK|WY') and on_block_time is null) \r\n"
		+ "and operationname = 'onb' and type = 'cv' order by flightno";
		ResultSet result5 = DBWrapper.Connect(querry_result5,environment);
		while (result5.next())
		{				
			String str_Flight_PK = result5.getString("flight_pk");
			String str_flight_Number= result5.getString("flightno");
			onBlockFromOtherDataSource_list.add(str_Flight_PK);	
		}
				
		String querry_result6="SELECT logid, flightnumber_arrival, sensor_ATA, On_block_time, (case when (sensor_ATA < On_Block_Time) then 1 else 0 end) as Status, \r\n"
		+ "CONCAT('',TIMEDIFF(sensor_ata, on_block_time)) as difference FROM `DailyFlightSchedule_Merged` where date(IFNULL(sta,mediator_sta))= '"+SQL_Queries.yesterDate()+"' \r\n"
		+ "and operationunit = "+operationunit+" and flightnumber_arrival regexp ('G9|AI|9I|CX|EY|FZ|G8|J9|9W|S2|SV|MI|UL|TG|UK|WY') \r\n"
		+ "and (sensor_ata is not null and On_block_time is not null) order by flightnumber_arrival";
		ResultSet result6 = DBWrapper.Connect(querry_result6,environment);
		while (result6.next())
		{				
			String str_LogID = result6.getString("logid");
			String str_flight_NumberArrival= result6.getString("flightnumber_arrival");
			String str_flight_SensorATA= result6.getString("sensor_ata");
			String str_flight_OnBlock= result6.getString("on_block_time");
			String str_status= result6.getString("status");
			String str_difference_between_OnBlockAndSensorATA= result6.getString("difference");
			
			if (str_status.contains("1"))
			{
				status1List.add(str_LogID);
				
			}
			else if (str_status.contains("0"))
			{
				status0List.add(str_LogID);
			}		
		}	
		
		String querry_result7="SELECT count(*) FROM `EquipActivityLogs` where flight_pk in (SELECT logid FROM `DailyFlightSchedule_Merged` \r\n"
				+ "where date(IFNULL(sta,mediator_sta))= '"+SQL_Queries.yesterDate()+"' and operationunit = "+operationunit+" and flightnumber_arrival regexp ('G9|AI|9I|CX|EY|FZ|G8|J9|9W|S2|SV|MI|UL|TG|UK|WY')) \r\n"
				+ "and operationname = 'onb' and type = 'cv' order by flightno";
				ResultSet result7 = DBWrapper.Connect(querry_result7,environment);
				while (result7.next())
				{				
					onBlockFromCV = result7.getInt("count(*)");
				}
		email_report_Scheduled_And_Sensor_ATA_For_AISATS_Hyd1.append("<style>table#t01, th, td {border: 1px solid black;border-collapse: collapse;}table#t01 th{background-color:#80e5ff; } table#t01 tr:nth-child(even) {background-color: #f2f2f2;} table#t01 tr:nth-child(odd) { background-color: #DFEDEC;}table#t01 th, td {padding: 5px;}table#t01 th,td {text-align: center;} table#t01 caption {color: #008ae6;font-weight: bold;}</style>");
		email_report_Scheduled_And_Sensor_ATA_For_AISATS_Hyd1.append("<h4 align=\"center\" style=\"color:#008ae6;\">User Name : AISATS-Hyderabad</h4>");
		email_report_Scheduled_And_Sensor_ATA_For_AISATS_Hyd1.append("<h4 align=\"center\" style=\"color:#008ae6;\">Executed For :Scheduled and Sensor-ATA</h4><h5 align=\"center\" style=\"color:#008ae6;\" >Execution Time: "+SQL_Queries.todayDayDateTime()+" </h5>");
		email_report_Scheduled_And_Sensor_ATA_For_AISATS_Hyd1.append("<h5 align=\"center\" style=\"color:#008ae6;\" >Environment: "+environment+" </h5>");

		email_report_Scheduled_And_Sensor_ATA_For_AISATS_Hyd1.append("<table style=\"width:100%\" id=\"t01\"><tr>"
				+ "<th style=\"width:10%\"><b>Date</b></th>"
				+ "<th style=\"width:15%\"><b>Total No. of Flights Scheduled Arrival</b></th>"
				+ "<th style=\"width:15%\"><b>Total No. of Flights Actually Arrived</b></th>"
				+ " <th style=\"width:15%\"><b>No. of flights detected by Sensor</b></th>"
		 		+ "<th style=\"width:15%\"><b>No. of flights NOT detected by Sensor</b></th>"
		 		+ "<th style=\"width:15%\"><b>No. of flights On-Block time is detected by Sensor</b></th>"
		 		+ "<th style=\"width:15%\"><b>No. of flights On-Block time is detected by CV</b></th>"
		 		+ " </tr>");
		email_report_Scheduled_And_Sensor_ATA_For_AISATS_Hyd1.append(" <tr> <td><b>"+SQL_Queries.yesterDate()+"</b></td>"
				+ " <td><b>"+totalScheduledArrival+"</b></td>"
				+ " <td><b>"+totalFlightsActuallyArrived+"</b></td>"
		 		+ " <td> <b style=\"color:green;\">"+notNullSensorATA+"</b></td> "
		 		+ "<td><b style=\"color:red;\">"+sensorATA_NullList.size()+"</b></td> "
		 		+ "<td><b style=\"color:green;\">"+onBlockFromSensor+"</b></td> "
		 		+ "<td><b style=\"color:red;\">"+onBlockFromCV+"</b></td></tr></table>");			 	
		emailbody.append("<style>table#t01, th, td {border: 1px solid black;border-collapse: collapse;}table#t01 th{background-color:#80e5ff; } table#t01 tr:nth-child(even) {background-color: #f2f2f2;} table#t01 tr:nth-child(odd) { background-color: #DFEDEC;}table#t01 th, td {padding: 5px;}table#t01 th,td {text-align: center;} table#t01 caption {color: #008ae6;font-weight: bold;}</style>");
		emailbody.append("<h4 align=\"center\" style=\"color:#008ae6;\">Executed For :Scheduled and Sensor-ATA for AISATS-Hyderabad</h4>");
		emailbody.append("<table style=\"width:100%\" id=\"t01\"><tr>"
				+ "<th style=\"width:10%\"><b>Date</b></th>"
				+ "<th style=\"width:15%\"><b>Total No. of Flights Scheduled Arrival</b></th>"
				+ "<th style=\"width:15%\"><b>Total No. of Flights Actually Arrived</b></th>"
				+ " <th style=\"width:15%\"><b>No. of flights detected by Sensor</b></th>"
		 		+ "<th style=\"width:15%\"><b>No. of flights NOT detected by Sensor</b></th>"
		 		+ "<th style=\"width:15%\"><b>No. of flights On-Block time is detected by Sensor</b></th>"
		 		+ "<th style=\"width:15%\"><b>No. of flights On-Block time is detected by CV</b></th>"
		 		+ " </tr>");
		emailbody.append(" <tr> <td><b>"+SQL_Queries.yesterDate()+"</b></td>"
				+ " <td><b>"+totalScheduledArrival+"</b></td>"
				+ " <td><b>"+totalFlightsActuallyArrived+"</b></td>"
		 		+ " <td> <b style=\"color:green;\">"+notNullSensorATA+"</b></td> "
		 		+ "<td><b style=\"color:red;\">"+sensorATA_NullList.size()+"</b></td> "
		 		+ "<td><b style=\"color:green;\">"+onBlockFromSensor+"</b></td> "
		 		+ "<td><b style=\"color:red;\">"+onBlockFromCV+"</b></td></tr></table>");			 	
		
		email_report_Scheduled_And_Sensor_ATA_For_AISATS_Hyd2.append("<br><br>");
		email_report_Scheduled_And_Sensor_ATA_For_AISATS_Hyd2.append("<style>table#t01, th, td {border: 1px solid black;border-collapse: collapse;}table#t01 th{background-color:#80e5ff;} table#t01 tr:nth-child(even) {background-color: #f2f2f2;} table#t01 tr:nth-child(odd) { background-color: #DFEDEC;}table#t01 th, td {padding: 5px;}table#t01 th,td {text-align: center;} table#t01 caption {color: #008ae6;font-weight: bold;}</style>");
		email_report_Scheduled_And_Sensor_ATA_For_AISATS_Hyd2.append("<table style=\"width:100%\" id=\"t01\"><caption> Total Flights LANDING (Not Detected by Flight Sensor but detected from other data source) </caption><tr><th style=\"width:10%\"><b>LogID</b></th>"
				+ "<th style=\"width:15%\"><b>ArrivalFlight No.</b></th> "
				+ "<th style=\"width:15%\"><b>STA</b></th>"
		 		+ "<th style=\"width:15%\"><b>ETA</b></th>"
		 		+ "<th style=\"width:20%\"><b>ATA</b></th>"
		 		+ " </tr>");
		
		if (sensorATA_NullList.size()>0) {
		String querry_result31= "SELECT logid, flightnumber, sta, eta, ata FROM `DailyFlightScheduleArrival_GMR`where \r\n" +
		"date(IFNULL(sta,mediator_sta))= '"+SQL_Queries.yesterDate()+"' and operationunit= "+operationunit+" and flightnumber regexp ('G9|AI|9I|CX|EY|FZ|G8|J9|9W|S2|SV|MI|UL|TG|UK|WY') \r\n"
		+ " and sensor_ata is null";
		ResultSet result31 = DBWrapper.Connect(querry_result31,environment);
		while (result31.next())
		{
			String str_LogID = result31.getString("logid");
			String str_flight_Number= result31.getString("flightnumber");
			String str_flight_STA = result31.getString("sta");
			String str_flight_ETA = result31.getString("eta");
			String str_flight_ATA = result31.getString("ata");
			
			email_report_Scheduled_And_Sensor_ATA_For_AISATS_Hyd2.append(" <tr> <td><b style=\"color:red;\">"+str_LogID+"</b></td> <td><b style=\"color:red;\">"+str_flight_Number+"</b></td>"
				 		+ " <td> <b style=\"color:red;\">"+str_flight_STA+"</b></td> <td><b style=\"color:red;\">"+str_flight_ETA+"</b></td> <td><b style=\"color:red;\">"+str_flight_ATA+"</b></td></tr>");	
		}
		}
		else{
			email_report_Scheduled_And_Sensor_ATA_For_AISATS_Hyd2.append("<tr><td colspan=\"5\"><b style=\"color:red;\">No values found </b></td></tr>");	
			}
		email_report_Scheduled_And_Sensor_ATA_For_AISATS_Hyd2.append("</table>");
		
		email_report_Scheduled_And_Sensor_ATA_For_AISATS_Hyd3.append("<br><br>");
		email_report_Scheduled_And_Sensor_ATA_For_AISATS_Hyd3.append("<style>table#t01, th, td {border: 1px solid black;border-collapse: collapse;}table#t01 th{background-color:#80e5ff;} table#t01 tr:nth-child(even) {background-color: #f2f2f2;} table#t01 tr:nth-child(odd) { background-color: #DFEDEC;}table#t01 th, td {padding: 5px;}table#t01 th,td {text-align: center;} table#t01 caption {color: #008ae6;font-weight: bold;}</style>");
		email_report_Scheduled_And_Sensor_ATA_For_AISATS_Hyd3.append("<table style=\"width:100%\" id=\"t01\"><caption> Total Flights - ONBLOCK (Not Detected by Flight Sensor but detected from other data source)</caption><tr>"
				+ "<th style=\"width:10%\"><b>Flight_PK</b></th>"
				+ "<th style=\"width:15%\"><b>Departure Flight No.</b></th> "
				+ " </tr>");
		
		if (onBlockFromOtherDataSource_list.size()>0) {
		String querry_result51="SELECT flight_pk, flightno FROM `EquipActivityLogs` where flight_pk in (SELECT logid FROM `DailyFlightSchedule_Merged` \r\n" + 
				"where date(IFNULL(sta,mediator_sta))= '"+SQL_Queries.yesterDate()+"' and operationunit = "+operationunit+" and flightnumber_arrival regexp ('G9|AI|9I|CX|EY|FZ|G8|J9|9W|S2|SV|MI|UL|TG|UK|WY') and on_block_time is null) \r\n" + 
				"and operationname = 'onb' and type = 'cv' order by flightno";
		ResultSet result51 = DBWrapper.Connect(querry_result51,environment);
		while (result51.next())
		{				
			String str_Flight_PK = result51.getString("flight_pk");
			String str_flight_Number= result51.getString("flightno");
		
			email_report_Scheduled_And_Sensor_ATA_For_AISATS_Hyd3.append(" <tr> <td><b style=\"color:red;\">"+str_Flight_PK+"</b></td> <td><b style=\"color:red;\">"+str_flight_Number+"</b></td></tr>");	
		}
		}
		else{
			email_report_Scheduled_And_Sensor_ATA_For_AISATS_Hyd3.append("<tr><td colspan=\"2\"><b style=\"color:red;\">No values found </b></td></tr>");	
			}		
		email_report_Scheduled_And_Sensor_ATA_For_AISATS_Hyd3.append("</table>");
		
		email_report_Scheduled_And_Sensor_ATA_For_AISATS_Hyd4.append("<br><br>");
		email_report_Scheduled_And_Sensor_ATA_For_AISATS_Hyd4.append("<style>table#t01, th, td {border: 1px solid black;border-collapse: collapse;}table#t01 th{background-color:#80e5ff; } table#t01 tr:nth-child(even) {background-color: #f2f2f2;} table#t01 tr:nth-child(odd) { background-color: #DFEDEC;}table#t01 th, td {padding: 5px;}table#t01 th,td {text-align: center;} table#t01 caption {color: #008ae6;font-weight: bold;}</style>");
		email_report_Scheduled_And_Sensor_ATA_For_AISATS_Hyd4.append("<table style=\"width:100%\" id=\"t01\"><caption> Total Flights - LANDING time detected by flight Sensor is greater than On-Block</caption><tr>"
				+ "<th style=\"width:10%\"><b>LogID</b></th>"
				+ "<th style=\"width:15%\"><b>Arrival Flight No.</b></th> "
				+ "<th style=\"width:15%\"><b>Sensor_ATA</b></th> "
				+ "<th style=\"width:15%\"><b>On_Block_time</b></th> "
				+ "<th style=\"width:15%\"><b>Difference between On_Block_time and Sensor_ATA</b></th> "
				+ " </tr>");
		System.out.println(status0List.size()+"hfkugfewiuhfdiuewgdiuewhbdwegduewhdbbd");
		
		if (status0List.size()>0) {
		String querry_result61="SELECT logid, flightnumber_arrival, sensor_ATA, On_block_time, (case when (sensor_ATA < On_Block_Time) then 1 else 0 end) as Status, \r\n" + 
		"CONCAT('',TIMEDIFF(sensor_ata, on_block_time)) as difference FROM `DailyFlightSchedule_Merged` where date(IFNULL(sta,mediator_sta))= '"+SQL_Queries.yesterDate()+"' \r\n" + 
		"and operationunit = "+operationunit+" and flightnumber_arrival regexp ('G9|AI|9I|CX|EY|FZ|G8|J9|9W|S2|SV|MI|UL|TG|UK|WY') \r\n"
		+ "and (sensor_ata is not null and On_block_time is not null) order by flightnumber_arrival";
	
		ResultSet result61 = DBWrapper.Connect(querry_result61,environment);
		while (result61.next())
		{	
			String str_LogID = result61.getString("logid");
			String str_flight_NumberArrival= result61.getString("flightnumber_arrival");
			String str_flight_SensorATA= result61.getString("sensor_ata");
			String str_flight_OnBlock= result61.getString("on_block_time");
			String str_status= result61.getString("status");
			String str_difference_between_OnBlockAndSensorATA= result61.getString("difference");
			if(str_status.contains("0")) {
				email_report_Scheduled_And_Sensor_ATA_For_AISATS_Hyd4.append(" <tr> <td><b style=\"color:red;\">"+str_LogID+"</b></td>"
						+ " <td><b style=\"color:red;\">"+str_flight_NumberArrival+"</b></td>"
						+ " <td> <b style=\"color:red;\">"+str_flight_SensorATA+"</b></td>"
						+ " <td><b style=\"color:red;\">"+str_flight_OnBlock+"</b></td> "
						+ "<td><b style=\"color:red;\">"+str_difference_between_OnBlockAndSensorATA+"</b></td></tr>");
			}
			}
		}
			else {				
				email_report_Scheduled_And_Sensor_ATA_For_AISATS_Hyd4.append("<tr><td colspan=\"5\"><b style=\"color:red;\">No values found </b></td></tr>");
			}
		
		email_report_Scheduled_And_Sensor_ATA_For_AISATS_Hyd4.append("</table>");
		
		HtmlReportUtil.test.log(LogStatus.INFO, email_report_Scheduled_And_Sensor_ATA_For_AISATS_Hyd1.toString());
		HtmlReportUtil.testHist.log(LogStatus.INFO, email_report_Scheduled_And_Sensor_ATA_For_AISATS_Hyd1.toString());
		 
		 ExtentTest child0 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:green;\" align=\"center\">Total Flights Scheduled for Arrival ("+SQL_Queries.yesterDate()+"): "+totalScheduledArrival+"</b>");
		child0.log(LogStatus.INFO, "<b style=\"color:green;\" align=\"center\">Total Flights Scheduled for Arrival ("+SQL_Queries.yesterDate()+"): "+totalScheduledArrival+"</b>");			
		 ExtentTest child00 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:green;\" align=\"center\">Total Flights Scheduled for Arrival ("+SQL_Queries.yesterDate()+"): "+totalScheduledArrival+"</b>");
		 child00.log(LogStatus.INFO, "<b style=\"color:green;\" align=\"center\">Total Flights Scheduled for Arrival ("+SQL_Queries.yesterDate()+"): "+totalScheduledArrival+"</b>");
				
		 ExtentTest child01 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:green;\" align=\"center\">Total Flights Actually Arrived : "+totalFlightsActuallyArrived+"</b>");
		 child01.log(LogStatus.INFO, "<b style=\"color:green;\" align=\"center\">Total Flights Actually Arrived : "+totalFlightsActuallyArrived+"</b>");			
		 ExtentTest child001 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:green;\" align=\"center\">Total Flights Actually Arrived : "+totalFlightsActuallyArrived+"</b>");
		 child001.log(LogStatus.INFO, "<b style=\"color:green;\" align=\"center\">Total Flights Actually Arrived : "+totalFlightsActuallyArrived+"</b>");			 
		 
		ExtentTest child1 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:green;\" align=\"center\"> Total Flights LANDING (Detected by Flight Sensor): "+notNullSensorATA+"</b>");
		 child1.log(LogStatus.INFO, "<b style=\"color:green;\" align=\"center\"> Total Flights LANDING (Detected by Flight Sensor): "+notNullSensorATA+"</b>");
		 ExtentTest child11 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:green;\" align=\"center\"> Total Flights LANDING (Detected by Flight Sensor): "+notNullSensorATA+"</b>");
		 child11.log(LogStatus.INFO, "<b style=\"color:green;\" align=\"center\"> Total Flights LANDING (Detected by Flight Sensor): "+notNullSensorATA+"</b>");
		 
		 ExtentTest child2 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:red;\" align=\"center\"> Total Flights LANDING (Not Detected by Flight Sensor but detected from other data source): "+sensorATA_NullList.size()+"</b>");
		 child2.log(LogStatus.INFO, email_report_Scheduled_And_Sensor_ATA_For_AISATS_Hyd2.toString());
		 ExtentTest child22 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:red;\" align=\"center\"> Total Flights LANDING (Not Detected by Flight Sensor but detected from other data source): "+sensorATA_NullList.size()+"</b>");
		 child22.log(LogStatus.INFO, email_report_Scheduled_And_Sensor_ATA_For_AISATS_Hyd2.toString());
		 	 
		 ExtentTest child3 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:green;\" align=\"center\"> Total Flights - ONBLOCK (Detected by Flight Sensor): "+onBlockFromSensor+"</b>");
		 child3.log(LogStatus.INFO, "<b style=\"color:green;\" align=\"center\"> Total Flights - ONBLOCK (Detected by Flight Sensor): "+onBlockFromSensor+"</b>");
		 ExtentTest child33 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:green;\" align=\"center\"> Total Flights - ONBLOCK (Detected by Flight Sensor): "+onBlockFromSensor+"</b>");
		 child33.log(LogStatus.INFO, "<b style=\"color:green;\" align=\"center\"> Total Flights - ONBLOCK (Detected by Flight Sensor):: "+onBlockFromSensor+"</b>");
		 
		 ExtentTest child4 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:red;\" align=\"center\"> Total Flights - ONBLOCK (Not Detected by Flight Sensor but detected from other data source): "+onBlockFromOtherDataSource_list.size()+"</b>");
		 child4.log(LogStatus.INFO, email_report_Scheduled_And_Sensor_ATA_For_AISATS_Hyd3.toString());
		 ExtentTest child44 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:red;\" align=\"center\"> Total Flights - ONBLOCK (Not Detected by Flight Sensor but detected from other data source):: "+onBlockFromOtherDataSource_list.size()+"</b>");
		 child44.log(LogStatus.INFO, email_report_Scheduled_And_Sensor_ATA_For_AISATS_Hyd3.toString());
		 
		ExtentTest child5 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:green;\" align=\"center\">Total Flights- LANDING time detected by Flight Sensor less than ONBLOCK: "+status1List.size()+"</b>");
		child5.log(LogStatus.INFO, "<b style=\"color:green;\" align=\"center\">Total Flights- LANDING time detected by Flight Sensor less than ONBLOCK: "+status1List.size()+"</b>");			
		ExtentTest child55 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:green;\" align=\"center\">Total Flights- LANDING time detected by Flight Sensor less than ONBLOCK: "+status1List.size()+"</b>");
		child55.log(LogStatus.INFO, "<b style=\"color:green;\" align=\"center\">Total Flights- LANDING time detected by Flight Sensor less than ONBLOCK: "+status1List.size()+"</b>");
			 	 
		 ExtentTest child6 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:red;\" align=\"center\"> Total Flights- LANDING time detected by Flight Sensor is greater than ONBLOCK: "+status0List.size()+"</b>");
		 child6.log(LogStatus.INFO, email_report_Scheduled_And_Sensor_ATA_For_AISATS_Hyd4.toString());
		 ExtentTest child66 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:red;\" align=\"center\"> Total Flights- LANDING time detected by Flight Sensor is greater than ONBLOCK : "+status0List.size()+"</b>");
		 child66.log(LogStatus.INFO, email_report_Scheduled_And_Sensor_ATA_For_AISATS_Hyd4.toString());

		 HtmlReportUtil.test.appendChild(child0).appendChild(child01).appendChild(child1).appendChild(child2).appendChild(child3).appendChild(child4).appendChild(child5).appendChild(child6);
		 HtmlReportUtil.testHist.appendChild(child00).appendChild(child001).appendChild(child11).appendChild(child22).appendChild(child33).appendChild(child44).appendChild(child55).appendChild(child66);
		
		 DBWrapper.dbConnectionClose();
		}
				
	}

	
