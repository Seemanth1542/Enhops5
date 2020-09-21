package enh.db.pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import utilities.HtmlReportUtil;


public class AV_2405_Read_scheduledFlights_andcheck_thecoverage_ScheduledFlights_Vs_FlightsDetected_ScheduledFlights_Vs_FuelActivityFormed_BSSPL{
	
	public static XSSFWorkbook workbook;
	public static FileInputStream file;
	public static XSSFSheet sheet;
	public static XSSFRow row;
	
	public static ArrayList<String> list_flightNumberFromExcel = new ArrayList<String>();
	public static ArrayList list_ATAIsNull = new ArrayList();
	public static ArrayList list_OnBlockTimeIsNull = new ArrayList();
	public static ArrayList list_OffBlockTimeIsNull = new ArrayList();
	public static ArrayList list_ATDIsNull = new ArrayList();
	public static ArrayList list_ContainsAll= new ArrayList();
	public static ArrayList list_AllAreNull = new ArrayList();
	
	public static HashSet<String> list_logID = new HashSet<String>();
	public static HashSet<String> list_NoflelogID = new HashSet<String>();
	public static HashSet<String> list_Flight_pk_Found = new HashSet<String>();
	public static HashSet<String> list_Flight_pk_Not_Found = new HashSet<String>();
	public static HashSet<String> list_iosl = new HashSet<String>();
	
	public static ArrayList<String> list_flightnumber_Arrival= new ArrayList<String>();
	public static ArrayList<String> list_flightnumber_Departure= new ArrayList<String>();
	public static ArrayList<String> list_flight_ATA= new ArrayList<String>();
	public static ArrayList<String> list_flight_OnBlockTime= new ArrayList<String>();
	public static ArrayList<String> list_flight_OffBlockTime= new ArrayList<String>();
	public static ArrayList<String> list_flight_ATD= new ArrayList<String>();
	
	
	public static int ATAIsNull=0;
	public static int OnBlockIsNull=0;
	public static int OffBlockIsNull=0;
	public static int ATDIsNull=0;
	public static int devIdUtilizedForFueling=0;
	public static StringBuilder emailbody = new StringBuilder();
	
	public static StringBuilder email_report_ATA_OnBlock_OffBlock_ATD_For_DIAL_BSSPL1 = new StringBuilder();
	public static StringBuilder email_report_ATA_OnBlock_OffBlock_ATD_For_DIAL_BSSPL2 = new StringBuilder();
	public static StringBuilder email_report_ATA_OnBlock_OffBlock_ATD_For_DIAL_BSSPL3 = new StringBuilder();
	public static StringBuilder email_report_ATA_OnBlock_OffBlock_ATD_For_DIAL_BSSPL4 = new StringBuilder();
	public static StringBuilder email_report_ATA_OnBlock_OffBlock_ATD_For_DIAL_BSSPL5 = new StringBuilder();
	public static StringBuilder email_report_ATA_OnBlock_OffBlock_ATD_For_DIAL_BSSPL6 = new StringBuilder();
	
	public static void ScheduledVsFlightDetectedForBSSPL_Report(String Execlfilepath, String sheetName,String environment) throws IOException, SQLException {
	//HtmlReportUtil.stepInfo(sheetName);
		System.out.println(sheetName);
		
		 email_report_ATA_OnBlock_OffBlock_ATD_For_DIAL_BSSPL2.append("<br><br>");
		 email_report_ATA_OnBlock_OffBlock_ATD_For_DIAL_BSSPL2.append("<style>table#t01, th, td {border: 1px solid black;border-collapse: collapse;}table#t01 th{background-color:#80e5ff; } table#t01 tr:nth-child(even) {background-color: #f2f2f2;} table#t01 tr:nth-child(odd) { background-color: #DFEDEC;}table#t01 th, td {padding: 5px;}table#t01 th,td {text-align: center;} table#t01 caption {color: #008ae6;font-weight: bold;}</style>");
		 email_report_ATA_OnBlock_OffBlock_ATD_For_DIAL_BSSPL2.append("<table style=\"width:100%\" id=\"t01\"><caption> Total Flights without Landing time </caption><tr>"
													+ "<th style=\"width:15%\"><b>logID</b></th> "
													+ "<th style=\"width:15%\"><b>FlightNumber_Arrival</b></th>"
													+ "<th style=\"width:15%\"><b>FlightNumber_Departure</b></th>"
											 		+ "<th style=\"width:15%\"><b>On_BlocK_Time</b></th>"
											 		+ "<th style=\"width:20%\"><b>Off_BlocK_Time</b></th>"
											 		+ "<th style=\"width:20%\"><b>ATD</b></th>"
											 		+ " </tr>");
		 
		 email_report_ATA_OnBlock_OffBlock_ATD_For_DIAL_BSSPL3.append("<br><br>");
		 email_report_ATA_OnBlock_OffBlock_ATD_For_DIAL_BSSPL3.append("<style>table#t01, th, td {border: 1px solid black;border-collapse: collapse;}table#t01 th{background-color:#80e5ff; } table#t01 tr:nth-child(even) {background-color: #f2f2f2;} table#t01 tr:nth-child(odd) { background-color: #DFEDEC;}table#t01 th, td {padding: 5px;}table#t01 th,td {text-align: center;} table#t01 caption {color: #008ae6;font-weight: bold;}</style>");
		 email_report_ATA_OnBlock_OffBlock_ATD_For_DIAL_BSSPL3.append("<table style=\"width:100%\" id=\"t01\"><caption> Total Flights without OnBlock time </caption><tr>"
													+ "<th style=\"width:15%\"><b>logID</b></th> "
													+ "<th style=\"width:15%\"><b>FlightNumber_Arrival</b></th>"
													+ "<th style=\"width:15%\"><b>FlightNumber_Departure</b></th>"
											 		+ "<th style=\"width:15%\"><b>ATA</b></th>"
											 		+ "<th style=\"width:20%\"><b>Off_BlocK_Time</b></th>"
											 		+ "<th style=\"width:20%\"><b>ATD</b></th>"
											 		+ " </tr>");
		 
		 email_report_ATA_OnBlock_OffBlock_ATD_For_DIAL_BSSPL4.append("<br><br>");
		 email_report_ATA_OnBlock_OffBlock_ATD_For_DIAL_BSSPL4.append("<style>table#t01, th, td {border: 1px solid black;border-collapse: collapse;}table#t01 th{background-color:#80e5ff; } table#t01 tr:nth-child(even) {background-color: #f2f2f2;} table#t01 tr:nth-child(odd) { background-color: #DFEDEC;}table#t01 th, td {padding: 5px;}table#t01 th,td {text-align: center;} table#t01 caption {color: #008ae6;font-weight: bold;}</style>");
		 email_report_ATA_OnBlock_OffBlock_ATD_For_DIAL_BSSPL4.append("<table style=\"width:100%\" id=\"t01\"><caption> Total Flights without OffBlock time </caption><tr>"
													+ "<th style=\"width:15%\"><b>logID</b></th> "
													+ "<th style=\"width:15%\"><b>FlightNumber_Arrival</b></th>"
													+ "<th style=\"width:15%\"><b>FlightNumber_Departure</b></th>"
											 		+ "<th style=\"width:15%\"><b>ATA</b></th>"
											 		+ "<th style=\"width:20%\"><b>On_BlocK_Time</b></th>"
											 		+ "<th style=\"width:20%\"><b>ATD</b></th>"
											 		+ " </tr>");
		 
		 email_report_ATA_OnBlock_OffBlock_ATD_For_DIAL_BSSPL5.append("<br><br>");
		 email_report_ATA_OnBlock_OffBlock_ATD_For_DIAL_BSSPL5.append("<style>table#t01, th, td {border: 1px solid black;border-collapse: collapse;}table#t01 th{background-color:#80e5ff; } table#t01 tr:nth-child(even) {background-color: #f2f2f2;} table#t01 tr:nth-child(odd) { background-color: #DFEDEC;}table#t01 th, td {padding: 5px;}table#t01 th,td {text-align: center;} table#t01 caption {color: #008ae6;font-weight: bold;}</style>");
		 email_report_ATA_OnBlock_OffBlock_ATD_For_DIAL_BSSPL5.append("<table style=\"width:100%\" id=\"t01\"><caption> Total Flights without Airborne time </caption><tr>"
													+ "<th style=\"width:15%\"><b>logID</b></th> "
													+ "<th style=\"width:15%\"><b>FlightNumber_Arrival</b></th>"
													+ "<th style=\"width:15%\"><b>FlightNumber_Departure</b></th>"
											 		+ "<th style=\"width:15%\"><b>ATA</b></th>"
											 		+ "<th style=\"width:20%\"><b>On_BlocK_Time</b></th>"
											 		+ "<th style=\"width:20%\"><b>Off_BlocK_Time</b></th>"
											 		+ " </tr>");
		 
		 email_report_ATA_OnBlock_OffBlock_ATD_For_DIAL_BSSPL6.append("<br><br>");
		 email_report_ATA_OnBlock_OffBlock_ATD_For_DIAL_BSSPL6.append("<style>table#t01, th, td {border: 1px solid black;border-collapse: collapse;}table#t01 th{background-color:#80e5ff; } table#t01 tr:nth-child(even) {background-color: #f2f2f2;} table#t01 tr:nth-child(odd) { background-color: #DFEDEC;}table#t01 th, td {padding: 5px;}table#t01 th,td {text-align: center;} table#t01 caption {color: #008ae6;font-weight: bold;}</style>");
		 email_report_ATA_OnBlock_OffBlock_ATD_For_DIAL_BSSPL6.append("<table style=\"width:100%\" id=\"t01\"><caption> Total Flights without Fuel activity </caption><tr>"
													+ "<th style=\"width:15%\"><b>logID</b></th> "
													+ "<th style=\"width:20%\"><b>FlightNumber_Arrival</b></th>"
													+ "<th style=\"width:20%\"><b>FlightNumber_Departure</b></th>"
													+ "<th style=\"width:15%\"><b>ATA</b></th>"
											 		+ "<th style=\"width:15%\"><b>ATD</b></th>"
											 		+ "<th style=\"width:15%\"><b>Bay</b></th>"
											 		+ " </tr>");
		 
	 file = new FileInputStream(new File(Execlfilepath));
	 workbook = new XSSFWorkbook(file);
	 sheet = workbook.getSheet(sheetName);
	
	 String[][] dataFromSheet =new String[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		
	 for(int i=0;i<sheet.getLastRowNum();i++){			
				DataFormatter formatter = new DataFormatter();
				dataFromSheet[i][0] = formatter.formatCellValue(sheet.getRow(i+1).getCell(0));
				String FlightNumber=dataFromSheet[i][0].replace("-", " ");
				list_flightNumberFromExcel.add(FlightNumber);
				//System.out.println("FlightNumber: " +FlightNumber);
	 			/*}
	  for(int i=0; i<list_flightNumberFromExcel.size(); i++) {*/
				ResultSet result = DBWrapper.Connect("SELECT * FROM `DailyFlightSchedule_Merged`\r\n" + 
						"where date(IFNULL(std, etd))= '"+SQL_Queries.yesterDate()+"' and operationunit = 22 and \r\n"
						+ "flightnumber_departure like '%"+FlightNumber+"%'",environment);
				
				while(result.next()) {
					String logId=  result.getString("LogId");
					String flightnumber_Arrival = result.getString("flightnumber_arrival");
					String flightnumber_departure = result.getString("flightnumber_departure");
					String flight_ATA=  result.getString("ata");
					String flight_OnBlockTime = result.getString("On_block_time");
					String flight_OffBlockTime = result.getString("Off_block_time");
					String flight_ATD=  result.getString("atd");
					//System.out.println(logId+"-"+flightnumber_Arrival+"-"+flightnumber_departure+"-"+flight_ATA+"-"+flight_OnBlockTime+"-"+flight_OffBlockTime+"-"+flight_ATD);				
					list_logID.add(logId);
					list_NoflelogID.add(logId);
					if(flight_ATA==null || flight_ATA.isEmpty()) {
						ATAIsNull = ATAIsNull+1;
						System.out.println("flight_ATA==null || flight_ATA.isEmpty()");
						email_report_ATA_OnBlock_OffBlock_ATD_For_DIAL_BSSPL2.append(" <tr> <td><b style=\"color:red;\">"+logId+"</b></td> "
								+ "<td><b style=\"color:red;\">"+flightnumber_Arrival+"</b></td>"
						 		+ " <td> <b style=\"color:red;\">"+flightnumber_departure+"</b></td>"
						 		+ " <td><b style=\"color:red;\">"+flight_OnBlockTime+"</b></td>"
						 		+ " <td><b style=\"color:red;\">"+flight_OffBlockTime+"</b></td> "
						 		+ "<td><b style=\"color:red;\">"+flight_ATD+"</b></td></tr>");						
					}
					if(flight_OnBlockTime==null || flight_OnBlockTime.isEmpty()) {
						System.out.println("flight_OnBlockTime==null || flight_OnBlockTime.isEmpty()");
						OnBlockIsNull = OnBlockIsNull+1;
						email_report_ATA_OnBlock_OffBlock_ATD_For_DIAL_BSSPL3.append(" <tr> <td><b style=\"color:red;\">"+logId+"</b></td> "
								+ "<td><b style=\"color:red;\">"+flightnumber_Arrival+"</b></td>"
						 		+ " <td> <b style=\"color:red;\">"+flightnumber_departure+"</b></td>"
						 		+ " <td><b style=\"color:red;\">"+flight_ATA+"</b></td>"
						 		+ " <td><b style=\"color:red;\">"+flight_OffBlockTime+"</b></td> "
						 		+ "<td><b style=\"color:red;\">"+flight_ATD+"</b></td></tr>");
					}
					if(flight_OffBlockTime==null || flight_OffBlockTime.isEmpty()) {
						System.out.println("flight_OffBlockTime==null || flight_OffBlockTime.isEmpty()");
						OffBlockIsNull = OffBlockIsNull+1;
						email_report_ATA_OnBlock_OffBlock_ATD_For_DIAL_BSSPL4.append(" <tr> <td><b style=\"color:red;\">"+logId+"</b></td> "
								+ "<td><b style=\"color:red;\">"+flightnumber_Arrival+"</b></td>"
						 		+ " <td> <b style=\"color:red;\">"+flightnumber_departure+"</b></td>"
						 		+ " <td><b style=\"color:red;\">"+flight_ATA+"</b></td>"
						 		+ " <td><b style=\"color:red;\">"+flight_OnBlockTime+"</b></td> "
						 		+ "<td><b style=\"color:red;\">"+flight_ATD+"</b></td></tr>");
					}
					if(flight_ATD==null || flight_ATD.isEmpty()) {

						System.out.println("flight_ATD==null || flight_ATD.isEmpty()");

						ATDIsNull = ATDIsNull+1;
						email_report_ATA_OnBlock_OffBlock_ATD_For_DIAL_BSSPL5.append(" <tr> <td><b style=\"color:red;\">"+logId+"</b></td> "
								+ "<td><b style=\"color:red;\">"+flightnumber_Arrival+"</b></td>"
						 		+ " <td> <b style=\"color:red;\">"+flightnumber_departure+"</b></td>"
						 		+ " <td><b style=\"color:red;\">"+flight_ATA+"</b></td>"
						 		+ " <td><b style=\"color:red;\">"+flight_OnBlockTime+"</b></td> "
						 		+ "<td><b style=\"color:red;\">"+flight_OffBlockTime+"</b></td></tr>");
					}
				}	
			}
	  			if(ATAIsNull==0) {
	  				email_report_ATA_OnBlock_OffBlock_ATD_For_DIAL_BSSPL2.append("<tr><td colspan=\"6\"><b style=\"color:red;\">No values found </b></td></tr>");	
	  			}
	  			if(OnBlockIsNull==0) {
	  				email_report_ATA_OnBlock_OffBlock_ATD_For_DIAL_BSSPL3.append("<tr><td colspan=\"6\"><b style=\"color:red;\">No values found </b></td></tr>");	
	  			}
	  			if(OffBlockIsNull==0) {
	  				email_report_ATA_OnBlock_OffBlock_ATD_For_DIAL_BSSPL4.append("<tr><td colspan=\"6\"><b style=\"color:red;\">No values found </b></td></tr>");	
	  			}
	  			if(ATDIsNull==0) {
	  				email_report_ATA_OnBlock_OffBlock_ATD_For_DIAL_BSSPL5.append("<tr><td colspan=\"6\"><b style=\"color:red;\">No values found </b></td></tr>");	
	  			}
	  			email_report_ATA_OnBlock_OffBlock_ATD_For_DIAL_BSSPL2.append("</table>");
	  			email_report_ATA_OnBlock_OffBlock_ATD_For_DIAL_BSSPL3.append("</table>");
	  			email_report_ATA_OnBlock_OffBlock_ATD_For_DIAL_BSSPL4.append("</table>");
	  			email_report_ATA_OnBlock_OffBlock_ATD_For_DIAL_BSSPL5.append("</table>");
	  			
	  			for(String logID:list_logID ) {
	  			String SQL_Querry2= "SELECT * FROM `EquipActivityLogs` where flight_pk= '"+ logID+"' ";
	  			ResultSet result2 = DBWrapper.Connect(SQL_Querry2,environment);
	  			while(result2.next()) {
	  				String flight_pk=  result2.getString("flight_pk");
	  				String operationName=result2.getString("operationname");
	  				String flightno=  result2.getString("flightno");
	  				String devid=  result2.getString("devid");
	  				String flogdate=  result2.getString("flogdate");
	  				String tlogdate=  result2.getString("tlogdate");
	  				
	  				if((operationName.equalsIgnoreCase("FLE"))) {
	  					if(devid.contains("BSSPL")) {
	  				System.out.println(flight_pk+"-"+operationName+"-"+flightno+"-"+devid+"-"+flogdate+"-"+tlogdate);	
	  					list_Flight_pk_Found.add(flight_pk);  
	  					}
	  					if(devid.contains("IOSL")) {
	  						System.out.println(flight_pk+"-"+operationName+"-"+flightno+"-"+devid+"-"+flogdate+"-"+tlogdate);	
			  				list_iosl.add(flight_pk); 	
	  					}
	  			}
	  				else {
	  					list_Flight_pk_Not_Found.add(flight_pk);
	  			}
	  				
	  			}
	  			}
	  			System.out.println(list_Flight_pk_Found);
	  				list_NoflelogID.removeAll(list_Flight_pk_Found);
	  			
	  	System.out.println(list_logID.size());
	  	System.out.println(list_flightnumber_Arrival.size());
	  	System.out.println(list_flightnumber_Departure.size());
	  	System.out.println(list_flight_ATA.size());
	  	System.out.println(list_flight_OnBlockTime.size());
	  	System.out.println(list_flight_OffBlockTime.size());
	  	System.out.println(list_flight_ATD.size());
	  	
	  	
	  	System.out.println("Logid:" +list_logID);
		System.out.println("list_flightnumber_Arrival" +list_flightnumber_Arrival);
		System.out.println("list_flightnumber_Departure:" +list_flightnumber_Departure);
		System.out.println("list_flight_ATA:" +list_flight_ATA);
		System.out.println("list_flight_OnBlockTime:" +list_flight_OnBlockTime);
		System.out.println("list_flight_OffBlockTime:" +list_flight_OffBlockTime);
		System.out.println("list_flight_ATD:" +list_flight_ATD);
		
		
		System.out.println(ATAIsNull);	
		System.out.println(OnBlockIsNull);
		System.out.println(OffBlockIsNull);
		System.out.println(ATDIsNull);
		
		for(String NoFleLogId: list_NoflelogID) {
		String SQL_Querry3= "SELECT * FROM `DailyFlightSchedule_Merged`where date(IFNULL(std, etd))= '"+SQL_Queries.yesterDate()+"' and operationunit = 22 and logid ='"+NoFleLogId+"'";
			ResultSet result3 = DBWrapper.Connect(SQL_Querry3,environment);
			while(result3.next()) {
				String logId=  result3.getString("LogId");
				String flightnumber_Arrival = result3.getString("flightnumber_arrival");
				String flightnumber_departure = result3.getString("flightnumber_departure");
				String flight_ATA=  result3.getString("ata");
				String flight_OnBlockTime = result3.getString("On_block_time");
				String flight_OffBlockTime = result3.getString("Off_block_time");
				String flight_ATD=  result3.getString("atd");
				String flight_Bay=  result3.getString("bay");
				
				email_report_ATA_OnBlock_OffBlock_ATD_For_DIAL_BSSPL6.append(" <tr> <td><b style=\"color:red;\">"+logId+"</b></td> "
						+ "<td><b style=\"color:red;\">"+flightnumber_Arrival+"</b></td>"
				 		+ " <td> <b style=\"color:red;\">"+flightnumber_departure+"</b></td>"
				 		+ " <td><b style=\"color:red;\">"+flight_ATA+"</b></td>"
				 		+ "<td><b style=\"color:red;\">"+flight_ATD+"</b></td>"
				 		+ "<td><b style=\"color:red;\">"+flight_Bay+"</b></td>"
				 		+ "</tr>");
			}
		}
		email_report_ATA_OnBlock_OffBlock_ATD_For_DIAL_BSSPL6.append("</table>");
		ResultSet result0 = DBWrapper.Connect("SELECT count(distinct(devid)) FROM `EquipActivityLogs` where date(flogdate) = '"+SQL_Queries.yesterDate()+"' \r\n"
                + "and devid like '%BSSPL_DIAL_AFUEL%' and assigned=1 and operationname= 'FLE'",environment);
        while (result0.next())
        {
            devIdUtilizedForFueling = result0.getInt("count(distinct(devid))");
        }   
       
	 
	 email_report_ATA_OnBlock_OffBlock_ATD_For_DIAL_BSSPL1.append("<style>table#t01, th, td {border: 1px solid black;border-collapse: collapse;}table#t01 th{background-color:#80e5ff; } table#t01 tr:nth-child(even) {background-color: #f2f2f2;} table#t01 tr:nth-child(odd) { background-color: #DFEDEC;}table#t01 th, td {padding: 5px;}table#t01 th,td {text-align: center;} table#t01 caption {color: #008ae6;font-weight: bold;}</style>");
	 email_report_ATA_OnBlock_OffBlock_ATD_For_DIAL_BSSPL1.append("<h4 align=\"center\" style=\"color:#008ae6;\"> Airport Name : Delhi</h4>");
	 email_report_ATA_OnBlock_OffBlock_ATD_For_DIAL_BSSPL1.append("<h4 align=\"center\" style=\"color:#008ae6;\">Executed For :Bharat Stars Services Private Limited (BSSPL)  </h4><h5 align=\"center\" style=\"color:#008ae6;\" >Execution Time: "+SQL_Queries.todayDayDateTime()+" </h5>");
	 email_report_ATA_OnBlock_OffBlock_ATD_For_DIAL_BSSPL1.append("<h5 align=\"center\" style=\"color:#008ae6;\" >Environment: "+environment+" </h5>");

	 email_report_ATA_OnBlock_OffBlock_ATD_For_DIAL_BSSPL1.append("<table style=\"width:100%\" id=\"t01\"><tr>"
						+ "<th style=\"width:10%\"><b>Total Flights Scheduled for Departure</b></th>"
						+ "<th style=\"width:10%\"><b>Flights Departed</b></th>"
						+ "<th style=\"width:10%\"><b>Total Fuel bowsers utilized</b></th>"
						+ "<th style=\"width:10%\"><b>Flights with BSSPL Fuel Activity</b></th>"
						+ "<th style=\"width:10%\"><b>Flights with other Fuel Activity</b></th>"
						+ "<th style=\"width:10%\"><b>Flights without Fuel Activity</b></th>"
						+ "<th style=\"width:10%\"><b>Flights without Landing time</b></th> "
						+ "<th style=\"width:10%\"><b>Flights without ON-Block time</b></th>"
						+ "<th style=\"width:10%\"><b>Flights without Off-Block time</b></th>"
						+ "<th style=\"width:10%\"><b>Flights without Airborne time</b></th>"					
						+ " </tr>");
	 email_report_ATA_OnBlock_OffBlock_ATD_For_DIAL_BSSPL1.append(" <tr> "
						+ "<td><b>"+list_flightNumberFromExcel.size()+"</b></td> "
						+ "<td><b style=\"color:red;\">"+list_logID.size()+"</b></td>"
						+ "<td><b style=\"color:green;\">"+devIdUtilizedForFueling+"</b></td>"
						+ "<td><b style=\"color:red;\">"+list_Flight_pk_Found.size()+"</b></td>"
						+ "<td><b style=\"color:red;\">"+list_iosl.size()+"</b></td>"
					    + "<td><b style=\"color:red;\">"+list_NoflelogID.size()+"</b></td>"   
						+ "<td> <b style=\"color:red;\">"+ATAIsNull+"</b></td>"
						+ " <td> <b style=\"color:red;\">"+OnBlockIsNull+"</b></td> "
						+ "<td><b style=\"color:red;\">"+OffBlockIsNull+"</b></td> "
						+ "<td><b style=\"color:red;\">"+ATDIsNull+"</b></td>"		 					
	 					+ " </tr>");
	 email_report_ATA_OnBlock_OffBlock_ATD_For_DIAL_BSSPL1.append("</table>");
	 
	 emailbody.append("<style>table#t01, th, td {border: 1px solid black;border-collapse: collapse;}table#t01 th{background-color:#80e5ff; } table#t01 tr:nth-child(even) {background-color: #f2f2f2;} table#t01 tr:nth-child(odd) { background-color: #DFEDEC;}table#t01 th, td {padding: 5px;}table#t01 th,td {text-align: center;} table#t01 caption {color: #008ae6;font-weight: bold;}</style>");

	 emailbody.append("<h4 align=\"center\" style=\"color:#008ae6;\">Executed For :Bharat Stars Services Private Limited (BSSPL) for Delhi </h4>");


	 emailbody.append("<table style=\"width:100%\" id=\"t01\"><tr>"
				+ "<th style=\"width:10%\"><b>Total Flights Scheduled for Departure</b></th>"
				+ "<th style=\"width:10%\"><b>Flights Departed</b></th>"
				+ "<th style=\"width:10%\"><b>Total Fuel bowsers utilized</b></th>"
				+ "<th style=\"width:10%\"><b>Flights with BSSPL Fuel Activity</b></th>"
				+ "<th style=\"width:10%\"><b>Flights with other Fuel Activity</b></th>"
				+ "<th style=\"width:10%\"><b>Flights without Fuel Activity</b></th>"
				+ "<th style=\"width:10%\"><b>Flights without Landing time</b></th> "
				+ "<th style=\"width:10%\"><b>Flights without ON-Block time</b></th>"
				+ "<th style=\"width:10%\"><b>Flights without Off-Block time</b></th>"
				+ "<th style=\"width:10%\"><b>Flights without Airborne time</b></th>"					
				+ " </tr>");
	 emailbody.append(" <tr> "
				+ "<td><b>"+list_flightNumberFromExcel.size()+"</b></td> "
				+ "<td><b style=\"color:red;\">"+list_logID.size()+"</b></td>"
				+ "<td><b style=\"color:green;\">"+devIdUtilizedForFueling+"</b></td>"
				+ "<td><b style=\"color:red;\">"+list_Flight_pk_Found.size()+"</b></td>"
				+ "<td><b style=\"color:red;\">"+list_iosl.size()+"</b></td>"
			    + "<td><b style=\"color:red;\">"+list_NoflelogID.size()+"</b></td>"   
				+ "<td> <b style=\"color:red;\">"+ATAIsNull+"</b></td>"
				+ " <td> <b style=\"color:red;\">"+OnBlockIsNull+"</b></td> "
				+ "<td><b style=\"color:red;\">"+OffBlockIsNull+"</b></td> "
				+ "<td><b style=\"color:red;\">"+ATDIsNull+"</b></td>"		 					
				+ " </tr>");
	 emailbody.append("</table>");

				
	HtmlReportUtil.test.log(LogStatus.INFO, email_report_ATA_OnBlock_OffBlock_ATD_For_DIAL_BSSPL1.toString());
	HtmlReportUtil.testHist.log(LogStatus.INFO, email_report_ATA_OnBlock_OffBlock_ATD_For_DIAL_BSSPL1.toString());
	
	 ExtentTest child0 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:green;\" align=\"center\">Total Flights Scheduled for Departure ("+SQL_Queries.yesterDate()+"): "+list_flightNumberFromExcel.size()+"</b>");
	 child0.log(LogStatus.INFO, "<b style=\"color:green;\" align=\"center\">Total Flights Scheduled for Arrival ("+SQL_Queries.yesterDate()+"): "+list_flightNumberFromExcel+"</b>");			
	 ExtentTest child00 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:green;\" align=\"center\">Total Flights Scheduled for Departure ("+SQL_Queries.yesterDate()+"): "+list_flightNumberFromExcel.size()+"</b>");
	 child00.log(LogStatus.INFO, "<b style=\"color:green;\" align=\"center\">Total Flights Scheduled for Arrival ("+SQL_Queries.yesterDate()+"): "+list_flightNumberFromExcel+"</b>");
	
	 ExtentTest child01 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:green;\" align=\"center\">Total Flights Departed ("+SQL_Queries.yesterDate()+"): "+list_logID.size()+"</b>");
	 child01.log(LogStatus.INFO, "<b style=\"color:green;\" align=\"center\">Total Flights Departed ("+SQL_Queries.yesterDate()+"): "+list_logID+"</b>");			
	 ExtentTest child001 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:green;\" align=\"center\">Total Flights Departed ("+SQL_Queries.yesterDate()+"): "+list_logID.size()+"</b>");
	 child001.log(LogStatus.INFO, "<b style=\"color:green;\" align=\"center\">Total Flights Departed ("+SQL_Queries.yesterDate()+"): "+list_logID.size()+"</b>");
	 
	 ExtentTest child111 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:green;\" align=\"center\">Total Flights with BSSPL & other Fueling activity : </b>");
	 child111.log(LogStatus.INFO, "<b style=\"color:green;\" align=\"center\">Total Flights with Fueling activity BSSPL: "+list_Flight_pk_Found +" IOSL "+list_iosl+"</b>");			
	 ExtentTest child1111 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:green;\" align=\"center\">Total Flights with Fueling activity :</b>");
	 child1111.log(LogStatus.INFO, "<b style=\"color:green;\" align=\"center\">Total Flights with Fueling activity : "+list_Flight_pk_Found.size()+"</b>");
	 
	 ExtentTest child1 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:red;\" align=\"center\"> Total Flights without Landing time : "+ATAIsNull+"</b>");
	 child1.log(LogStatus.INFO, email_report_ATA_OnBlock_OffBlock_ATD_For_DIAL_BSSPL2.toString());
	 ExtentTest child11 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:red;\" align=\"center\"> Total Flights without Landing time : "+ATAIsNull+"</b>");
	 child11.log(LogStatus.INFO, email_report_ATA_OnBlock_OffBlock_ATD_For_DIAL_BSSPL2.toString());
	 
	 ExtentTest child2 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:red;\" align=\"center\"> Total Flights without OnBlock time : "+OnBlockIsNull+"</b>");
	 child2.log(LogStatus.INFO, email_report_ATA_OnBlock_OffBlock_ATD_For_DIAL_BSSPL3.toString());
	 ExtentTest child22 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:red;\" align=\"center\"> Total Flights without OnBlock time : "+OnBlockIsNull+"</b>");
	 child22.log(LogStatus.INFO, email_report_ATA_OnBlock_OffBlock_ATD_For_DIAL_BSSPL3.toString());
	 
	 ExtentTest child3 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:red;\" align=\"center\"> Total Flights without OffBlock time : "+OffBlockIsNull+"</b>");
	 child3.log(LogStatus.INFO, email_report_ATA_OnBlock_OffBlock_ATD_For_DIAL_BSSPL4.toString());
	 ExtentTest child33 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:red;\" align=\"center\"> Total Flights without OffBlock time : "+OffBlockIsNull+"</b>");
	 child33.log(LogStatus.INFO, email_report_ATA_OnBlock_OffBlock_ATD_For_DIAL_BSSPL4.toString());
	 
	 ExtentTest child4 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:red;\" align=\"center\"> Total Flights with Airborne time : "+ATDIsNull+"</b>");
	 child4.log(LogStatus.INFO, email_report_ATA_OnBlock_OffBlock_ATD_For_DIAL_BSSPL5.toString());
	 ExtentTest child44 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:red;\" align=\"center\"> Total Flights with Airborne time : "+ATDIsNull+"</b>");
	 child44.log(LogStatus.INFO, email_report_ATA_OnBlock_OffBlock_ATD_For_DIAL_BSSPL5.toString());
	 
	 ExtentTest child5 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:red;\" align=\"center\">Total Flights without Fuel activity: "+list_NoflelogID.size()+"</b>");
	 child5.log(LogStatus.INFO, email_report_ATA_OnBlock_OffBlock_ATD_For_DIAL_BSSPL6.toString());			
	 ExtentTest child55 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:red;\" align=\"center\">Total Flights without Fuel activity: "+list_NoflelogID.size()+"</b>");
	 child55.log(LogStatus.INFO, email_report_ATA_OnBlock_OffBlock_ATD_For_DIAL_BSSPL6.toString());
	 
	 
	 HtmlReportUtil.test.appendChild(child0).appendChild(child01).appendChild(child111).appendChild(child1).appendChild(child2).appendChild(child3).appendChild(child4).appendChild(child5);
	 HtmlReportUtil.testHist.appendChild(child00).appendChild(child001).appendChild(child1111).appendChild(child11).appendChild(child22).appendChild(child33).appendChild(child44).appendChild(child55);	 
	 DBWrapper.dbConnectionClose();
		
	}
}
