package enh.db.pages;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import utilities.HtmlReportUtil;

public class AV_2307_SensorATA_OnBlock_OffBlock_SensorATD_SG_HYD_Validation{
	public static int totalScheduledArrival =0;
	public static int totalScheduledDeparture= 0;
	public static int notNullSensorATA=0;
	public static int notNullOnBlockTime= 0;
	public static int notNullOffBlockTime=0;
	public static int notNullSensorATD =0;
	public static int totalFlightsContainAll= 0;
	
	public static ArrayList<String> sensorATAIsNullList = new ArrayList<String>();
	public static ArrayList<String> onBlockIsNullList = new ArrayList<String>();
	public static ArrayList<String> offBlockIsNullList = new ArrayList<String>();
	public static ArrayList<String> sensorATDIsNullList= new ArrayList<String>();
	public static ArrayList<String> status0List = new ArrayList<String>();
	public static ArrayList<String> status1List = new ArrayList<String>();
	public static StringBuilder emailbody = new StringBuilder();
	public static StringBuilder email_report_SensorATA_OnBlock_OffBlock_SensorATD_For_Hyd1 = new StringBuilder();
	public static StringBuilder email_report_SensorATA_OnBlock_OffBlock_SensorATD_For_Hyd2 = new StringBuilder();
	public static StringBuilder email_report_SensorATA_OnBlock_OffBlock_SensorATD_For_Hyd3 = new StringBuilder();
	public static StringBuilder email_report_SensorATA_OnBlock_OffBlock_SensorATD_For_Hyd4 = new StringBuilder();
	public static StringBuilder email_report_SensorATA_OnBlock_OffBlock_SensorATD_For_Hyd5 = new StringBuilder();
	public static StringBuilder email_report_SensorATA_OnBlock_OffBlock_SensorATD_For_Hyd6 = new StringBuilder();
	
	public static void SensorATA_LessThanOnBlock_LessThanOffBlock_LessThanSensorATD_SG_HYD_Report(int operationunit, String airline,String environment) throws Exception{
	
		
		ResultSet result = DBWrapper.Connect("SELECT count(*) FROM `DailyFlightScheduleArrival_GMR`where \r\n" + 
				"date(IFNULL(sta, mediator_sta))='"+SQL_Queries.yesterDate()+"' and operationunit= "+operationunit+" and flightnumber like '%"+airline+"%'",environment);
		while (result.next())
		{				
			totalScheduledArrival = result.getInt("count(*)");
			System.out.println(totalScheduledArrival);
		}	
		
		ResultSet result1 = DBWrapper.Connect("SELECT count(*) FROM `DailyFlightScheduleDeparture_GMR` where \r\n" +
				"date(IFNULL(std, mediator_std))='"+SQL_Queries.yesterDate()+"' and operationunit= "+operationunit+" and flightnumber like '%"+airline+"%'",environment);
		while (result1.next())
		{				
			totalScheduledDeparture = result1.getInt("count(*)");
			System.out.println(totalScheduledDeparture);
		}
		
		ResultSet result2 = DBWrapper.Connect("SELECT count(*) FROM `DailyFlightSchedule_Merged` where \r\n" + 
		"gmrpk_arrival in (SELECT gmrpk FROM `DailyFlightScheduleArrival_GMR`where date(IFNULL(sta, mediator_sta))='"+SQL_Queries.yesterDate()+"' and operationunit= "+operationunit+" and flightnumber like '%"+airline+"%') \r\n" + 
		"and sensor_ata is not null",environment);
				while (result2.next())
				{				
					notNullSensorATA = result2.getInt("count(*)");
					System.out.println(notNullSensorATA);
				}
				
		ResultSet result3 = DBWrapper.Connect("SELECT logid, flightnumber_arrival, on_block_time, off_block_time, sensor_atd FROM `DailyFlightSchedule_Merged` where\r\n" + 
		"gmrpk_arrival in (SELECT gmrpk FROM `DailyFlightScheduleArrival_GMR`where date(IFNULL(sta, mediator_sta))='"+SQL_Queries.yesterDate()+"' and operationunit= "+operationunit+" and flightnumber like '%"+airline+"%') \r\n" + 
		"and (sensor_ata is null and on_block_time is not null and off_block_time is not null and sensor_atd is not null)",environment);
				while (result3.next())
				{				
					String str_LogID = result3.getString("logid");
					String str_flightNumber_Arrival= result3.getString("flightnumber_arrival");
					String str_On_Block_Time = result3.getString("on_block_time");
					String str_Off_Block_Time = result3.getString("off_block_time");
					String str_Sensor_ATD = result3.getString("sensor_atd");
					sensorATAIsNullList.add(str_LogID);
				}
		
		ResultSet result4 = DBWrapper.Connect("SELECT count(*) FROM `EquipActivityLogs` where flight_pk in (SELECT logid FROM `DailyFlightSchedule_Merged` \r\n" + 
		"where date(IFNULL(sta, mediator_sta))='"+SQL_Queries.yesterDate()+"' and operationunit = "+operationunit+" and flightno like '%"+airline+"%'and on_block_time is not null )\r\n" + 
		"and operationname = 'onb'",environment);
		while (result4.next())
		{				
			notNullOnBlockTime = result4.getInt("count(*)");
			System.out.println(notNullOnBlockTime);
		}
		
		ResultSet result5 = DBWrapper.Connect("SELECT logid, flightnumber_arrival, sensor_ata, off_block_time, sensor_atd FROM `DailyFlightSchedule_Merged` where gmrpk_arrival in (SELECT gmrpk FROM `DailyFlightScheduleArrival_GMR`\r\n "
		+ "where date(IFNULL(sta, mediator_sta))='"+SQL_Queries.yesterDate()+"' and operationunit= "+operationunit+" and flightnumber like '%"+airline+"%') and on_block_time is null \r\n"
				+ "and (sensor_ata is not null and off_block_time is not null and sensor_atd is not null)",environment);
		while (result5.next())
		{	
			String str_LogID = result5.getString("logid");
			String str_flightNumber_Arrival= result5.getString("flightnumber_arrival");
			String str_Sensor_ATA = result5.getString("sensor_ata");
			String str_Off_Block_Time = result5.getString("off_block_time");
			String str_Sensor_ATD = result5.getString("sensor_atd");
			
			onBlockIsNullList.add(str_LogID);
		}
		
		ResultSet result6 = DBWrapper.Connect("SELECT count(*) FROM `EquipActivityLogs` where flight_pk in (SELECT logid FROM `DailyFlightSchedule_Merged`\r\n "+
				 "where date(IFNULL(std, mediator_std))='"+SQL_Queries.yesterDate()+"' and operationunit = "+operationunit+" and off_block_time is not null and flightnumber_departure like '%"+airline+"%' ) \r\n"
				 		+ "and operationname = 'ofb' order by flightno",environment);
		while (result6.next())
		{				
			notNullOffBlockTime = result6.getInt("count(*)");
			System.out.println(notNullOffBlockTime);			
		}
		
		ResultSet result7 = DBWrapper.Connect("SELECT logid, flightnumber_departure, sensor_ata, on_block_time, sensor_atd FROM `DailyFlightSchedule_Merged` where gmrpk_departure in (SELECT gmrpk FROM `DailyFlightScheduleDeparture_GMR` \r\n"
		+ "where date(IFNULL(std, mediator_std))='"+SQL_Queries.yesterDate()+"' and operationunit= "+operationunit+" and flightnumber like '%"+airline+"%') and off_block_time is null \r\n"
		+ "and(sensor_ata is not null and on_block_time is not null and sensor_atd is not null)",environment);
				while (result7.next())
				{	
					String str_LogID = result7.getString("logid");
					String str_flightNumber_Departure= result7.getString("flightnumber_departure");
					String str_Sensor_ATA = result7.getString("sensor_ata");
					String str_On_Block_Time = result7.getString("on_block_time");
					String str_Sensor_ATD = result7.getString("sensor_atd");
					
					offBlockIsNullList.add(str_LogID);
				}
	
		ResultSet result8 = DBWrapper.Connect("SELECT count(*) FROM `DailyFlightSchedule_Merged` where \r\n " +
		"gmrpk_departure in (SELECT gmrpk FROM `DailyFlightScheduleDeparture_GMR` where date(IFNULL(std, mediator_std))='"+SQL_Queries.yesterDate()+"' \r\n "
		+ "and operationunit= "+operationunit+" and flightnumber like '%"+airline+"%') and sensor_atd is not null",environment);
				while (result8.next())
				{				
					notNullSensorATD = result8.getInt("count(*)");
					System.out.println(notNullSensorATD);
				}
		
		ResultSet result9 = DBWrapper.Connect("SELECT logid, flightnumber_departure, sensor_ata, on_block_time, off_block_time FROM `DailyFlightSchedule_Merged` where \r\n" + 
		"gmrpk_departure in (SELECT gmrpk FROM `DailyFlightScheduleDeparture_GMR`where date(IFNULL(std, mediator_std))='"+SQL_Queries.yesterDate()+"' and operationunit= "+operationunit+" and flightnumber like '%"+airline+"%') \r\n" + 
		"and (sensor_atd is null and on_block_time is not null and off_block_time is not null and sensor_ata is not null)",environment);
		while (result9.next())
		{	
			String str_LogID = result9.getString("logid");
			String str_flightNumber_Departure= result9.getString("flightnumber_departure");
			String str_Sensor_ATA = result9.getString("sensor_ata");
			String str_On_Block_Time = result9.getString("on_block_time");
			String str_Off_Block_Time = result9.getString("off_block_time");
			
			sensorATDIsNullList.add(str_LogID);
		}
						
		ResultSet result10 = DBWrapper.Connect("SELECT count(*) FROM `DailyFlightSchedule_Merged` where gmrpk_departure in \r\n" + 
		"(SELECT gmrpk FROM `DailyFlightScheduleDeparture_GMR` where date(IFNULL(std, mediator_std))='"+SQL_Queries.yesterDate()+"' and operationunit ="+operationunit+" and flightnumber like '%"+airline+"%')\r\n" + 
		"and (sensor_ata is not null and on_block_time is not null and off_block_time is not null and sensor_atd is not null)",environment);
		while (result10.next())
		{				
			totalFlightsContainAll = result10.getInt("count(*)");
			System.out.println(totalFlightsContainAll);
		}
		
		ResultSet result11 = DBWrapper.Connect("SELECT logid, flightnumber_departure, sensor_ata, on_block_time, Off_block_time, sensor_atd, (case when ((Sensor_ATA < On_Block_Time AND Sensor_ATA < Off_Block_Time) AND (Sensor_ATD > On_Block_Time AND Sensor_ATD > Off_Block_Time)) then 1 else 0 end) as Status\r\n" + 
		"FROM `DailyFlightSchedule_Merged` where gmrpk_departure in (SELECT gmrpk FROM `DailyFlightScheduleDeparture_GMR` where date(IFNULL(std, mediator_std))='"+SQL_Queries.yesterDate()+"' \r\n" + 
		"and operationunit ="+operationunit+" and flightnumber like '%"+airline+"%') and (sensor_ata is not null and on_block_time is not null and off_block_time is not null and sensor_atd is not null)",environment);
		while (result11.next()){	
			            
			String str_LogID = result11.getString("logid");			
			String str_flight_NumberDeparture = result11.getString("flightnumber_departure");		
			String str_flight_Sensor_ATA = result11.getString("Sensor_ATA");			
			String str_flight_On_Block_Time = result11.getString("On_Block_Time");			
			String str_flight_Off_Block_Time = result11.getString("Off_Block_Time");			
			String str_flight_Sensor_ATD = result11.getString("Sensor_ATD");			
			String str_status = result11.getString("Status");
			
			if(str_status.contains("1"))
			{
				status1List.add(str_LogID);
			}
			if(str_status.contains("0"))
			{
				status0List.add(str_LogID);
			}
		}
		//HtmlReportUtil.stepInfo("<b style=\"color:purple;\"> Airport : GMR_HYD :</b> <b style=\"color:green;\">Total No. of flights Scheduled Departure (based on STD or Mediator-STD) = "+ totalScheduledDeparture +"</b>");
		//HtmlReportUtil.stepInfo("<b style=\"color:green;\">Total No. of flights contains all (Sensor_ATA, On_Block, Off_Block and Sensor_ATD) = "+ totalFlightsContainAll +"</b>");
		//HtmlReportUtil.stepInfo("<b style=\"color:green;\">Total No. of flights for which Sensor_ATA < On_Block < Off_Block < Sensor_ATD = "+ status1List.size() +"</b>");
		//HtmlReportUtil.stepInfo("<b style=\"color:red;\">Total No. of flights for which order is not like Sensor_ATA < On_Block < Off_Block < Sensor_ATD  = "+ status0List.size() +"</b>");
		
		email_report_SensorATA_OnBlock_OffBlock_SensorATD_For_Hyd1.append("<style>table#t01, th, td {border: 1px solid black;border-collapse: collapse;}table#t01 th{background-color:#80e5ff; } table#t01 tr:nth-child(even) {background-color: #f2f2f2;} table#t01 tr:nth-child(odd) { background-color: #DFEDEC;}table#t01 th, td {padding: 5px;}table#t01 th,td {text-align: center;} table#t01 caption {color: #008ae6;font-weight: bold;}</style>");
		email_report_SensorATA_OnBlock_OffBlock_SensorATD_For_Hyd1.append("<h4 align=\"center\" style=\"color:#008ae6;\">Airport Name : GMR-Hyderabad</h4>");
		email_report_SensorATA_OnBlock_OffBlock_SensorATD_For_Hyd1.append("<h4 align=\"center\" style=\"color:#008ae6;\">Executed For :sensor-ATA, OnBlock, OffBlock, Sensor-ATD</h4><h5 align=\"center\" style=\"color:#008ae6;\" >Execution Time: "+SQL_Queries.todayDayDateTime()+" </h5>");
		email_report_SensorATA_OnBlock_OffBlock_SensorATD_For_Hyd1.append("<h5 align=\"center\" style=\"color:#008ae6;\" >Environment: "+environment+" </h5>");

		email_report_SensorATA_OnBlock_OffBlock_SensorATD_For_Hyd1.append("<table style=\"width:100%\" id=\"t01\"><tr><th style=\"width:10%\"><b>Date</b></th><th style=\"width:15%\"><b>Total No. of Flights Scheduled Arrival</b></th> <th style=\"width:15%\"><b> Total No. of flights Scheduled Departure</b></th>"
		 		+ "<th style=\"width:15%\"><b>Total Flights with Landing time</b></th><th style=\"width:20%\"><b>Total Flights with Airborne time</b></th>"
		 		+ " </tr>");
		email_report_SensorATA_OnBlock_OffBlock_SensorATD_For_Hyd1.append(" <tr> <td><b>"+SQL_Queries.yesterDate()+"</b></td> <td><b>"+totalScheduledArrival+"</b></td>"
		 		+ " <td> <b style=\"color:green;\">"+totalScheduledDeparture+"</b></td> <td><b style=\"color:green;\">"+notNullSensorATA+"</b></td> <td><b style=\"color:green;\">"+notNullSensorATD+"</b></td>");			 	
		email_report_SensorATA_OnBlock_OffBlock_SensorATD_For_Hyd1.append("</table>");
		
		emailbody.append("<style>table#t01, th, td {border: 1px solid black;border-collapse: collapse;}table#t01 th{background-color:#80e5ff; } table#t01 tr:nth-child(even) {background-color: #f2f2f2;} table#t01 tr:nth-child(odd) { background-color: #DFEDEC;}table#t01 th, td {padding: 5px;}table#t01 th,td {text-align: center;} table#t01 caption {color: #008ae6;font-weight: bold;}</style>");

		emailbody.append("<h4 align=\"center\" style=\"color:#008ae6;\">Executed For :sensor-ATA, OnBlock, OffBlock, Sensor-ATD for GMR-Hyderabad</h4>");


		emailbody.append("<table style=\"width:100%\" id=\"t01\"><tr><th style=\"width:10%\"><b>Date</b></th><th style=\"width:15%\"><b>Total No. of Flights Scheduled Arrival</b></th> <th style=\"width:15%\"><b> Total No. of flights Scheduled Departure</b></th>"
		 		+ "<th style=\"width:15%\"><b>Total Flights with Landing time</b></th><th style=\"width:20%\"><b>Total Flights with Airborne time</b></th>"
		 		+ " </tr>");
		emailbody.append(" <tr> <td><b>"+SQL_Queries.yesterDate()+"</b></td> <td><b>"+totalScheduledArrival+"</b></td>"
		 		+ " <td> <b style=\"color:green;\">"+totalScheduledDeparture+"</b></td> <td><b style=\"color:green;\">"+notNullSensorATA+"</b></td> <td><b style=\"color:green;\">"+notNullSensorATD+"</b></td>");			 	
		emailbody.append("</table>");
		email_report_SensorATA_OnBlock_OffBlock_SensorATD_For_Hyd2.append("<br><br>");
		email_report_SensorATA_OnBlock_OffBlock_SensorATD_For_Hyd2.append("<style>table#t01, th, td {border: 1px solid black;border-collapse: collapse;}table#t01 th{background-color:#80e5ff;} table#t01 tr:nth-child(even) {background-color: #f2f2f2;} table#t01 tr:nth-child(odd) { background-color: #DFEDEC;}table#t01 th, td {padding: 5px;}table#t01 th,td {text-align: center;} table#t01 caption {color: #008ae6;font-weight: bold;}</style>");
		email_report_SensorATA_OnBlock_OffBlock_SensorATD_For_Hyd2.append("<table style=\"width:100%\" id=\"t01\"><caption> No. of flights for which OnBlock, OffBlock and Airborne time is available but No Landing time </caption><tr>"
				+ "<th style=\"width:15%\"><b>logID</b></th> "
				+ "<th style=\"width:15%\"><b>FlightNumber_Arrival</b></th>"
		 		+ "<th style=\"width:15%\"><b>On_BlocK_Time</b></th>"
		 		+ "<th style=\"width:20%\"><b>Off_BlocK_Time</b></th>"
		 		+ "<th style=\"width:20%\"><b>Sensor_ATD</b></th>"
		 		+ " </tr>");
		if (sensorATAIsNullList.size()>0) {
		ResultSet result31 = DBWrapper.Connect("SELECT logid, flightnumber_arrival, on_block_time, off_block_time, sensor_atd FROM `DailyFlightSchedule_Merged` where \r\n" + 
				"flightArrivalId in (SELECT logid FROM `DailyFlightScheduleArrival_GMR`where date(IFNULL(sta, mediator_sta))='"+SQL_Queries.yesterDate()+"' and operationunit= "+operationunit+" and flightnumber like '%"+airline+"%') \r\n" + 
				"and (sensor_ata is null and on_block_time is not null and off_block_time is not null and sensor_atd is not null)",environment);
						while (result31.next())
						{				
							String str_LogID = result31.getString("logid");
							String str_flightNumber_Arrival= result31.getString("flightnumber_arrival");
							String str_On_Block_Time = result31.getString("on_block_time");
							String str_Off_Block_Time = result31.getString("off_block_time");
							String str_Sensor_ATD = result31.getString("sensor_atd");
							
								email_report_SensorATA_OnBlock_OffBlock_SensorATD_For_Hyd2.append(" <tr> <td><b style=\"color:red;\">"+str_LogID+"</b></td> <td><b style=\"color:red;\">"+str_flightNumber_Arrival+"</b></td>"
							 		+ " <td> <b style=\"color:red;\">"+str_On_Block_Time+"</b></td> <td><b style=\"color:red;\">"+str_Off_Block_Time+"</b></td> <td><b style=\"color:red;\">"+str_Sensor_ATD+"</b></td></tr>");	
							}
						}
							else {
								email_report_SensorATA_OnBlock_OffBlock_SensorATD_For_Hyd2.append("<tr><td colspan=\"5\"><b style=\"color:red;\">No values found </b></td></tr>");	
							}
						
						email_report_SensorATA_OnBlock_OffBlock_SensorATD_For_Hyd2.append("</table>");
		
			email_report_SensorATA_OnBlock_OffBlock_SensorATD_For_Hyd3.append("<br><br>");
			email_report_SensorATA_OnBlock_OffBlock_SensorATD_For_Hyd3.append("<style>table#t01, th, td {border: 1px solid black;border-collapse: collapse;}table#t01 th{background-color:#80e5ff; } table#t01 tr:nth-child(even) {background-color: #f2f2f2;} table#t01 tr:nth-child(odd) { background-color: #DFEDEC;}table#t01 th, td {padding: 5px;}table#t01 th,td {text-align: center;} table#t01 caption {color: #008ae6;font-weight: bold;}</style>");
			email_report_SensorATA_OnBlock_OffBlock_SensorATD_For_Hyd3.append("<table style=\"width:100%\" id=\"t01\"><caption> No. of flights for which Landing, OffBlock and Airborne time is available but No On Block time </caption><tr>"
								+ "<th style=\"width:15%\"><b>logID</b></th> "
								+ "<th style=\"width:15%\"><b>FlightNumber_Arrival</b></th>"
						 		+ "<th style=\"width:15%\"><b>Sensor_ATA</b></th>"
						 		+ "<th style=\"width:20%\"><b>Off_BlocK_Time</b></th>"
						 		+ "<th style=\"width:20%\"><b>Sensor_ATD</b></th>"
						 		+ " </tr>");
			if (onBlockIsNullList.size()>0) {
			ResultSet result51 = DBWrapper.Connect("SELECT logid, flightnumber_arrival, sensor_ata, off_block_time, sensor_atd FROM `DailyFlightSchedule_Merged` where gmrpk_arrival in (SELECT gmrpk FROM `DailyFlightScheduleArrival_GMR`\r\n "
					+ "where date(IFNULL(sta, mediator_sta))='"+SQL_Queries.yesterDate()+"' and operationunit= "+operationunit+" and flightnumber like '%"+airline+"%') and on_block_time is null \r\n"
							+ "and (sensor_ata is not null and off_block_time is not null and sensor_atd is not null)",environment);
								while (result51.next())
								{	
									String str_LogID = result51.getString("logid");
									String str_flightNumber_Arrival= result51.getString("flightnumber_arrival");
									String str_Sensor_ATA = result51.getString("sensor_ata");
									String str_Off_Block_Time = result51.getString("off_block_time");
									String str_Sensor_ATD = result51.getString("sensor_atd");
									
									email_report_SensorATA_OnBlock_OffBlock_SensorATD_For_Hyd3.append(" <tr> <td><b style=\"color:red;\">"+str_LogID+"</b></td> <td><b style=\"color:red;\">"+str_flightNumber_Arrival+"</b></td>"
										 		+ " <td> <b style=\"color:red;\">"+str_Sensor_ATA+"</b></td> <td><b style=\"color:red;\">"+str_Off_Block_Time+"</b></td> <td><b style=\"color:red;\">"+str_Sensor_ATD+"</b></td></tr>");	
								}
							}
							else {
									email_report_SensorATA_OnBlock_OffBlock_SensorATD_For_Hyd3.append("<tr><td colspan=\"5\"><b style=\"color:red;\">No values found </b></td></tr>");	
								}
								email_report_SensorATA_OnBlock_OffBlock_SensorATD_For_Hyd3.append("</table>");
			
								
			email_report_SensorATA_OnBlock_OffBlock_SensorATD_For_Hyd4.append("<br><br>");
			email_report_SensorATA_OnBlock_OffBlock_SensorATD_For_Hyd4.append("<style>table#t01, th, td {border: 1px solid black;border-collapse: collapse;}table#t01 th{background-color:#80e5ff; } table#t01 tr:nth-child(even) {background-color: #f2f2f2;} table#t01 tr:nth-child(odd) { background-color: #DFEDEC;}table#t01 th, td {padding: 5px;}table#t01 th,td {text-align: center;} table#t01 caption {color: #008ae6;font-weight: bold;}</style>");
			email_report_SensorATA_OnBlock_OffBlock_SensorATD_For_Hyd4.append("<table style=\"width:100%\" id=\"t01\"><caption> No. of flights for which Landing, OnBlock and Airborne time is available but No Off Block time </caption><tr>"
													+ "<th style=\"width:15%\"><b>logID</b></th> "
													+ "<th style=\"width:15%\"><b>FlightNumber_Departure</b></th>"
											 		+ "<th style=\"width:15%\"><b>Sensor_ATA</b></th>"
											 		+ "<th style=\"width:20%\"><b>On_BlocK_Time</b></th>"
											 		+ "<th style=\"width:20%\"><b>Sensor_ATD</b></th>"
											 		+ " </tr>");
			if (offBlockIsNullList.size()>0) {
			ResultSet result71 = DBWrapper.Connect("SELECT logid, flightnumber_departure, sensor_ata, on_block_time, sensor_atd FROM `DailyFlightSchedule_Merged` where gmrpk_departure in (SELECT gmrpk FROM `DailyFlightScheduleDeparture_GMR` \r\n "
			+ "where date(IFNULL(std, mediator_std))='"+SQL_Queries.yesterDate()+"' and operationunit= "+operationunit+" and flightnumber like '%"+airline+"%') and off_block_time is null \r\n"
			+ "and(sensor_ata is not null and on_block_time is not null and sensor_atd is not null)",environment);
								while (result71.next())
								{	
									String str_LogID = result71.getString("logid");
									String str_flightNumber_Departure= result71.getString("flightnumber_departure");
									String str_Sensor_ATA = result71.getString("sensor_ata");
									String str_On_Block_Time = result71.getString("on_block_time");
									String str_Sensor_ATD = result71.getString("sensor_atd");
									
									email_report_SensorATA_OnBlock_OffBlock_SensorATD_For_Hyd4.append(" <tr> <td><b style=\"color:red;\">"+str_LogID+"</b></td> <td><b style=\"color:red;\">"+str_flightNumber_Departure+"</b></td>"
										 		+ " <td> <b style=\"color:red;\">"+str_Sensor_ATA+"</b></td> <td><b style=\"color:red;\">"+str_On_Block_Time+"</b></td> <td><b style=\"color:red;\">"+str_Sensor_ATD+"</b></td></tr>");	
									}
						}
									else
									{
										email_report_SensorATA_OnBlock_OffBlock_SensorATD_For_Hyd4.append("<tr><td colspan=\"5\"><b style=\"color:red;\">No values found </b></td></tr>");	
									}
								email_report_SensorATA_OnBlock_OffBlock_SensorATD_For_Hyd4.append("</table>");
								
		email_report_SensorATA_OnBlock_OffBlock_SensorATD_For_Hyd5.append("<br><br>");
		email_report_SensorATA_OnBlock_OffBlock_SensorATD_For_Hyd5.append("<style>table#t01, th, td {border: 1px solid black;border-collapse: collapse;}table#t01 th{background-color:#80e5ff; } table#t01 tr:nth-child(even) {background-color: #f2f2f2;} table#t01 tr:nth-child(odd) { background-color: #DFEDEC;}table#t01 th, td {padding: 5px;}table#t01 th,td {text-align: center;} table#t01 caption {color: #008ae6;font-weight: bold;}</style>");
		email_report_SensorATA_OnBlock_OffBlock_SensorATD_For_Hyd5.append("<table style=\"width:100%\" id=\"t01\"><caption> No. of flights for which Landing, OnBlock and Off_block time is available but No Airborne time </caption><tr>"
																		+ "<th style=\"width:15%\"><b>logID</b></th> "
																		+ "<th style=\"width:15%\"><b>FlightNumber_Departure</b></th>"
																 		+ "<th style=\"width:15%\"><b>Sensor_ATA</b></th>"
																 		+ "<th style=\"width:20%\"><b>On_BlocK_Time</b></th>"
																 		+ "<th style=\"width:20%\"><b>Off_BlocK_Time</b></th>"
																 		+ " </tr>");
		
		if (sensorATDIsNullList.size()>0) {
		ResultSet result91 = DBWrapper.Connect("SELECT logid, flightnumber_departure, sensor_ata, on_block_time, off_block_time FROM `DailyFlightSchedule_Merged` where \r\n" + 
				"gmrpk_departure in (SELECT gmrpk FROM `DailyFlightScheduleDeparture_GMR`where date(IFNULL(std, mediator_std))='"+SQL_Queries.yesterDate()+"' and operationunit= "+operationunit+" and flightnumber like '%"+airline+"%') \r\n" + 
				"and (sensor_atd is null and on_block_time is not null and off_block_time is not null and sensor_ata is not null)",environment);
						while (result91.next())
							{	
								String str_LogID = result91.getString("logid");
								String str_flightNumber_Departure= result91.getString("flightnumber_departure");
								String str_Sensor_ATA = result91.getString("sensor_ata");
								String str_On_Block_Time = result91.getString("on_block_time");
								String str_Off_Block_Time = result91.getString("off_block_time");
					
									email_report_SensorATA_OnBlock_OffBlock_SensorATD_For_Hyd5.append(" <tr> <td><b style=\"color:red;\">"+str_LogID+"</b></td> <td><b style=\"color:red;\">"+str_flightNumber_Departure+"</b></td>"
									 		+ " <td> <b style=\"color:red;\">"+str_Sensor_ATA+"</b></td> <td><b style=\"color:red;\">"+str_On_Block_Time+"</b></td> <td><b style=\"color:red;\">"+str_Off_Block_Time+"</b></td></tr>");	
								}
						}
								else
								{
									email_report_SensorATA_OnBlock_OffBlock_SensorATD_For_Hyd5.append("<tr><td colspan=\"5\"><b style=\"color:red;\">No values found </b></td></tr>");	
								}
							
						email_report_SensorATA_OnBlock_OffBlock_SensorATD_For_Hyd5.append("</table>");
		
			email_report_SensorATA_OnBlock_OffBlock_SensorATD_For_Hyd6.append("<br><br>");
			email_report_SensorATA_OnBlock_OffBlock_SensorATD_For_Hyd6.append("<style>table#t01, th, td {border: 1px solid black;border-collapse: collapse;}table#t01 th{background-color:#80e5ff; } table#t01 tr:nth-child(even) {background-color: #f2f2f2;} table#t01 tr:nth-child(odd) { background-color: #DFEDEC;}table#t01 th, td {padding: 5px;}table#t01 th,td {text-align: center;} table#t01 caption {color: #008ae6;font-weight: bold;}</style>");
			email_report_SensorATA_OnBlock_OffBlock_SensorATD_For_Hyd6.append("<table style=\"width:100%\" id=\"t01\"><caption> No. of flights for which order is NOT like LANDING time < ONBLOCK time < OFFBLOCK time < AIRBORNE time  </caption><tr>"
																						+ "<th style=\"width:15%\"><b>logID</b></th> "
																						+ "<th style=\"width:15%\"><b>FlightNumber_Departure</b></th>"
																				 		+ "<th style=\"width:15%\"><b>Sensor_ATA</b></th>"
																				 		+ "<th style=\"width:20%\"><b>On_BlocK_Time</b></th>"
																				 		+ "<th style=\"width:20%\"><b>Off_BlocK_Time</b></th>"
																				 		+ "<th style=\"width:20%\"><b>Sensor_ATD</b></th>"
																				 		+ " </tr>");
			if(status0List.size()>0)
			{
			ResultSet result111 = DBWrapper.Connect("SELECT logid, flightnumber_departure, sensor_ata, on_block_time, Off_block_time, sensor_atd, (case when ((Sensor_ATA < On_Block_Time AND Sensor_ATA < Off_Block_Time) AND (Sensor_ATD > On_Block_Time AND Sensor_ATD > Off_Block_Time)) then 1 else 0 end) as Status\r\n" + 
					"FROM `DailyFlightSchedule_Merged` where gmrpk_departure in (SELECT gmrpk FROM `DailyFlightScheduleDeparture_GMR` where date(IFNULL(std, mediator_std))='"+SQL_Queries.yesterDate()+"' \r\n" + 
					"and operationunit ="+operationunit+" and flightnumber like '%"+airline+"%') and (sensor_ata is not null and on_block_time is not null and off_block_time is not null and sensor_atd is not null)",environment);
					while (result111.next()){	
						            
						String str_LogID = result111.getString("logid");			
						String str_flight_NumberDeparture = result111.getString("flightnumber_departure");		
						String str_flight_Sensor_ATA = result111.getString("Sensor_ATA");			
						String str_flight_On_Block_Time = result111.getString("On_Block_Time");			
						String str_flight_Off_Block_Time = result111.getString("Off_Block_Time");			
						String str_flight_Sensor_ATD = result111.getString("Sensor_ATD");			
						String str_status = result111.getString("Status");
		if(str_status.contains("0")) {
						email_report_SensorATA_OnBlock_OffBlock_SensorATD_For_Hyd6.append(" <tr> <td><b style=\"color:red;\">"+str_LogID+"</b></td> <td><b style=\"color:red;\">"+str_flight_NumberDeparture+"</b></td>"
							 		+ " <td> <b style=\"color:red;\">"+str_flight_Sensor_ATA+"</b></td> <td><b style=\"color:red;\">"+str_flight_On_Block_Time+"</b></td> <td><b style=\"color:red;\">"+str_flight_Off_Block_Time+"</b></td>"
							 				+ "<td><b style=\"color:red;\">"+str_flight_Sensor_ATD+"</b></td></tr>");		
		
		}		
		}	
				}
			else {
				email_report_SensorATA_OnBlock_OffBlock_SensorATD_For_Hyd6.append("<tr><td colspan=\"5\"><b style=\"color:red;\">No values found </b></td></tr>");	
			}
			email_report_SensorATA_OnBlock_OffBlock_SensorATD_For_Hyd6.append("</table>");
		

			HtmlReportUtil.test.log(LogStatus.INFO, email_report_SensorATA_OnBlock_OffBlock_SensorATD_For_Hyd1.toString());
			HtmlReportUtil.testHist.log(LogStatus.INFO, email_report_SensorATA_OnBlock_OffBlock_SensorATD_For_Hyd1.toString());
			
			ExtentTest child0 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:green;\" align=\"center\">Total Flights Scheduled for Arrival ("+SQL_Queries.yesterDate()+"): "+totalScheduledArrival+"</b>");
			child0.log(LogStatus.INFO, "<b style=\"color:green;\" align=\"center\">Total Flights Scheduled for Arrival ("+SQL_Queries.yesterDate()+"): "+totalScheduledArrival+"</b>");			
			 ExtentTest child00 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:green;\" align=\"center\">Total Flights Scheduled for Arrival ("+SQL_Queries.yesterDate()+"): "+totalScheduledArrival+"</b>");
			 child00.log(LogStatus.INFO, "<b style=\"color:green;\" align=\"center\">Total Flights Scheduled for Arrival ("+SQL_Queries.yesterDate()+"): "+totalScheduledArrival+"</b>");

			 ExtentTest child1 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:green;\" align=\"center\">Total Flights Scheduled for Departure ("+SQL_Queries.yesterDate()+"): "+totalScheduledDeparture+"</b>");
			 child1.log(LogStatus.INFO, "<b style=\"color:green;\" align=\"center\">Total Flights Scheduled for Departure ("+SQL_Queries.yesterDate()+"): "+totalScheduledDeparture+"</b>");			
			 ExtentTest child11 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:green;\" align=\"center\">Total Flights Scheduled for Departure ("+SQL_Queries.yesterDate()+"): "+totalScheduledDeparture+"</b>");
			 child11.log(LogStatus.INFO, "<b style=\"color:green;\" align=\"center\">Total Flights Scheduled for Departure ("+SQL_Queries.yesterDate()+"): "+totalScheduledDeparture+"</b>"); 
			 
			 ExtentTest child2 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:green;\" align=\"center\">Total Flights with Landing time : "+notNullSensorATA+"</b>");
			 child2.log(LogStatus.INFO, "<b style=\"color:green;\" align=\"center\">Total Flights with Landing time : "+notNullSensorATA+"</b>");			
			 ExtentTest child22 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:green;\" align=\"center\">Total Flights with Landing time : "+notNullSensorATA+"</b>");
			 child22.log(LogStatus.INFO, "<b style=\"color:green;\" align=\"center\">Total Flights with Landing time : "+notNullSensorATA+"</b>"); 
			 
			 ExtentTest child3 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:red;\" align=\"center\"> Total flights OnBlock, OffBlock and Airborne time is available but No Landing time : "+sensorATAIsNullList.size()+"</b>");
			 child3.log(LogStatus.INFO, email_report_SensorATA_OnBlock_OffBlock_SensorATD_For_Hyd2.toString());
			 ExtentTest child33 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:red;\" align=\"center\"> Total flights OnBlock, OffBlock and Airborne time is available but No Landing time: "+sensorATAIsNullList.size()+"</b>");
			 child33.log(LogStatus.INFO, email_report_SensorATA_OnBlock_OffBlock_SensorATD_For_Hyd2.toString());
			 
			 ExtentTest child4 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:green;\" align=\"center\">Total Flights with OnBlock time : "+notNullOnBlockTime+"</b>");
			 child4.log(LogStatus.INFO, "<b style=\"color:green;\" align=\"center\">Total Flights with OnBlock time : "+notNullOnBlockTime+"</b>");			
			 ExtentTest child44 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:green;\" align=\"center\">Total Flights with OnBlock time : "+notNullOnBlockTime+"</b>");
			 child44.log(LogStatus.INFO, "<b style=\"color:green;\" align=\"center\">Total Flights with OnBlock time : "+notNullOnBlockTime+"</b>"); 
			 
			 ExtentTest child5 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:red;\" align=\"center\"> Total flights Landing, OffBlock and Airborne time is available but No On Block time : "+onBlockIsNullList.size()+"</b>");
			 child5.log(LogStatus.INFO, email_report_SensorATA_OnBlock_OffBlock_SensorATD_For_Hyd3.toString());
			 ExtentTest child55 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:red;\" align=\"center\"> Total flights Landing, OffBlock and Airborne time is available but No On Block time : "+onBlockIsNullList.size()+"</b>");
			 child55.log(LogStatus.INFO, email_report_SensorATA_OnBlock_OffBlock_SensorATD_For_Hyd3.toString());
			 
			 ExtentTest child6 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:green;\" align=\"center\">Total Flights with OffBlock time : "+notNullOffBlockTime+"</b>");
			 child6.log(LogStatus.INFO, "<b style=\"color:green;\" align=\"center\">Total Flights with OffBlock time : "+notNullOffBlockTime+"</b>");			
			 ExtentTest child66 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:green;\" align=\"center\">Total Flights with OffBlock time : "+notNullOffBlockTime+"</b>");
			 child66.log(LogStatus.INFO, "<b style=\"color:green;\" align=\"center\">Total Flights with OffBlock time : "+notNullOffBlockTime+"</b>"); 
			 
			 ExtentTest child7 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:red;\" align=\"center\"> Total flights Landing, OnBlock and Airborne time is available but No Off Block time : "+offBlockIsNullList.size()+"</b>");
			 child7.log(LogStatus.INFO, email_report_SensorATA_OnBlock_OffBlock_SensorATD_For_Hyd4.toString());
			 ExtentTest child77 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:red;\" align=\"center\"> Total flights Landing, OnBlock and Airborne time is available but No Off Block time : "+offBlockIsNullList.size()+"</b>");
			 child77.log(LogStatus.INFO, email_report_SensorATA_OnBlock_OffBlock_SensorATD_For_Hyd4.toString());
			 
			 ExtentTest child8 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:green;\" align=\"center\">Total Flights with Airborne time : "+notNullSensorATD+"</b>");
			 child8.log(LogStatus.INFO, "<b style=\"color:green;\" align=\"center\">Total Flights with Airborne time : "+notNullSensorATD+"</b>");			
			 ExtentTest child88 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:green;\" align=\"center\">Total Flights with Airborne time : "+notNullSensorATD+"</b>");
			 child88.log(LogStatus.INFO, "<b style=\"color:green;\" align=\"center\">Total Flights with Airborne time : "+notNullSensorATD+"</b>"); 
			 
			 ExtentTest child9 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:red;\" align=\"center\"> Total flights Landing, OnBlock and OffBlock time is available but No Airborne time : "+sensorATDIsNullList.size()+"</b>");
			 child9.log(LogStatus.INFO, email_report_SensorATA_OnBlock_OffBlock_SensorATD_For_Hyd5.toString());
			 ExtentTest child99 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:red;\" align=\"center\"> Total flights Landing, OnBlock and OffBlock time is available but No Airborne time : "+sensorATDIsNullList.size()+"</b>");
			 child99.log(LogStatus.INFO, email_report_SensorATA_OnBlock_OffBlock_SensorATD_For_Hyd5.toString());
			 
			 ExtentTest child10 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:green;\" align=\"center\">Total Flights with LANDING time < ONBLOCK time < OFFBLOCK time < AIRBORNE time  : "+status1List.size()+"</b>");
			 child10.log(LogStatus.INFO, "<b style=\"color:green;\" align=\"center\">Total Flights with LANDING time < ONBLOCK time < OFFBLOCK time < AIRBORNE time : "+status1List.size()+"</b>");			
			 ExtentTest child101 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:green;\" align=\"center\">Total Flights with LANDING time < ONBLOCK time < OFFBLOCK time < AIRBORNE time : "+status1List.size()+"</b>");
			 child101.log(LogStatus.INFO, "<b style=\"color:green;\" align=\"center\">Total Flights with LANDING time < ONBLOCK time < OFFBLOCK time < AIRBORNE time : "+status1List.size()+"</b>"); 
			 
			 ExtentTest child111 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:red;\" align=\"center\"> Total Flights NOT like LANDING time < ONBLOCK time < OFFBLOCK time < AIRBORNE time : "+status0List.size()+"</b>");
			 child111.log(LogStatus.INFO, email_report_SensorATA_OnBlock_OffBlock_SensorATD_For_Hyd6.toString());
			 ExtentTest child1111 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:red;\" align=\"center\"> Total Flights NOT like LANDING time < ONBLOCK time < OFFBLOCK time < AIRBORNE time : "+status0List.size()+"</b>");
			 child1111.log(LogStatus.INFO, email_report_SensorATA_OnBlock_OffBlock_SensorATD_For_Hyd6.toString());
			 
			 HtmlReportUtil.test.appendChild(child0).appendChild(child1).appendChild(child2).appendChild(child3).appendChild(child4).appendChild(child5).appendChild(child6).appendChild(child7).appendChild(child8).appendChild(child9).appendChild(child10).appendChild(child111);
			 HtmlReportUtil.testHist.appendChild(child00).appendChild(child11).appendChild(child22).appendChild(child33).appendChild(child44).appendChild(child55).appendChild(child66).appendChild(child77).appendChild(child88).appendChild(child99).appendChild(child101).appendChild(child1111);
			 
			 
			 DBWrapper.dbConnectionClose();	
}
}

	