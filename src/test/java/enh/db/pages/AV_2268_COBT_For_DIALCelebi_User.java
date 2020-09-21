package enh.db.pages;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.Minutes;
import org.joda.time.Seconds;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utilities.HtmlReportUtil;


public class AV_2268_COBT_For_DIALCelebi_User {
	
	 public static ArrayList<String> cobtIsNotNull= new ArrayList<String>();
	 public static ArrayList<String> cobtIsNotNull2= new ArrayList<String>();
	 public static ArrayList<String> ofBlockIsNotNull= new ArrayList<String>();
	 public static ArrayList<String> cobtTimeDiff= new ArrayList<String>();	
	 public static HashSet<String> countCobtChange1= new HashSet<String>();	
	 public static HashSet<String> countCobtChange2= new HashSet<String>();	
	 public static StringBuilder email_COBT_For_DIALCelebi_User5= new StringBuilder();
	 public static StringBuilder emailbody= new StringBuilder();
	 public static StringBuilder email_COBT_For_DIALCelebi_User6= new StringBuilder();	 
	 public static StringBuilder email_COBT_For_DIALCelebi_User7= new StringBuilder();
	 public static StringBuilder email_COBT_For_DIALCelebi_User8= new StringBuilder();
	 public static StringBuilder email_COBT_For_DIALCelebi_User9= new StringBuilder();
	 public static StringBuilder email_COBT_For_DIALCelebi_User10= new StringBuilder();
	 public static StringBuilder email_COBT_For_DIALCelebi_User11= new StringBuilder();
	 public static JSONObject AV_2268_COBT_For_DIALCelebi_UserJson = new JSONObject();
	 public static JSONArray AV_2268_COBT_For_DIALCelebi_UserArray = new JSONArray();
	 public static JSONObject COBT_For_DIALCelebi_DataJson = new JSONObject();
	 public static JSONArray Json_TotalFlightsofEntity = new JSONArray();
	 public static JSONArray Json_COBTisNOTdetected = new JSONArray();
	 public static JSONArray Json_OFFBLOCKisNOTdetected = new JSONArray();	 
	 public static JSONArray Json_differenceOfActualOffBlockAndFinalCOBTdiffIsGreaterThan5Minutes = new JSONArray();
	 	
	 @SuppressWarnings("unchecked")
	public static void cOBT_For_DIALCelebi_User2(String environment) throws Exception{
		 String str_cobtDiff=null;
		 int countCobtChange=0;
	
		 
		 AV_2268_COBT_For_DIALCelebi_UserJson.put("title", "AV_2268_COBT_For_DIALCelebi_User");
		 AV_2268_COBT_For_DIALCelebi_UserJson.put("feature", "COBT");
		 AV_2268_COBT_For_DIALCelebi_UserJson.put("subFeature", "DB - Validate Accuracy of COBT for DIAL-CELEBI");
		 AV_2268_COBT_For_DIALCelebi_UserJson.put("entity", 4);
		 AV_2268_COBT_For_DIALCelebi_UserJson.put("airport", 4);
		 AV_2268_COBT_For_DIALCelebi_UserJson.put("reportedOn", SQL_Queries.yesterDate());
		 email_COBT_For_DIALCelebi_User9.append("<style>table#t01, th, td {border: 1px solid black;border-collapse: collapse;}table#t01 th{background-color:#80e5ff; } table#t01 tr:nth-child(even) {background-color: #f2f2f2;} table#t01 tr:nth-child(odd) { background-color: #DFEDEC;}table#t01 th, td {padding: 5px;}table#t01 th,td {text-align: center;} table#t01 caption {color: #008ae6;font-weight: bold;}</style>");
		 email_COBT_For_DIALCelebi_User9.append("<table style=\"width:100%\" id=\"t01\"><h4><caption> COBT detected flights</caption></h4>"
		 		+ "<tr><th style=\"width:15%\">Flight PK</th> <th style=\"width:20%\">FlightNumber</th><th style=\"width:25%\">COBT</th>"
			 		+ "<th style=\"width:25%\">Off_Block_Time</th><th style=\"width:15%\">Number of Times COBT Changed for flight</th></tr>");		
			ResultSet result = DBWrapper.Connect(SQL_Queries.strQuery_6,environment);
			while (result.next()){				
				String str_LogID = result.getString("Logid");	
				String str_FlightNumber_Arrival = result.getString("FlightNumber_Arrival");
				String str_cobt = result.getString("cobt");
				cobtIsNotNull.add(str_cobt);
				String str_Off_Block_Time = result.getString("Off_Block_Time");				
				//HtmlReportUtil.stepInfo("");
				//System.out.println("| "+ str_LogID +" | "+ str_FlightNumber_Arrival +" | "+ str_cobt +" | "+ str_Off_Block_Time +" | ");
				System.out.println(" ");
				/*HtmlReportUtil.stepInfo("<b style=\"color:red;\"> FlightNumber:-</b><b style=\"color:red;\"> "+str_FlightNumber_Arrival
						+" </b>|| <b style=\"color:red;\">Cobt:-</b><b style=\"color:red;\"> "+ str_cobt 
						+"<b> || <b style=\"color:red;\">Off Block:- </b> <b style=\"color:red;\">"+ str_Off_Block_Time +"  </b>");*/
				}
			System.out.println("Total Flights with cobt is null:- "+cobtIsNotNull.size());
			 String strQuery_66 ="SELECT Logid, FlightNumber_Arrival,FlightNumber_Departure, cobt, Off_Block_Time FROM `DailyFlightSchedule_Merged` \r\n" + 
					"where (date(atd)='"+SQL_Queries.yesterDate()+"' or date(sensor_atd)='"+SQL_Queries.yesterDate()+"') and operationunit = 22 and \r\n" + 
					"FlightNumber_Arrival regexp 'TK|EY|CX|QR|ET|AK|KC|JL|KE|PS|AC|AF|AZ|BA|SU|IA|SV|UL|T5|FZ|KQ|I5'\r\n" + 
					"and cobt is not  null";
			 ResultSet result0 = DBWrapper.Connect(strQuery_66, environment);
			while (result0.next()){				
				String str_LogID = result0.getString("Logid");	
				String str_FlightNumber_Arrival = result0.getString("FlightNumber_Arrival");
				String str_FlightNumber_Departure = result0.getString("FlightNumber_Departure");
				String str_cobt = result0.getString("cobt");
				cobtIsNotNull2.add(str_cobt);
				String str_Off_Block_Time = result0.getString("Off_Block_Time");
				
				ResultSet result00 = DBWrapper.Connect("SELECT cobtchange FROM `COBTTimeline`"
						+ " where flightpk="+str_LogID+"",environment);
				while(result00.next()) {
				String str_countCobtChange=result00.getString("cobtchange");
				 if(!str_countCobtChange.equalsIgnoreCase("0")){
					 countCobtChange=countCobtChange+1; 
				 }
			}
				email_COBT_For_DIALCelebi_User9.append("<tr><td><b style=\"color:green;\">"+str_LogID+"</b></td>"
						+ "<td><b style=\"color:green;\">"+str_FlightNumber_Arrival+" - "+str_FlightNumber_Departure+"</b></td>"
						+ "<td><b style=\"color:green;\">"+str_cobt+"</b></td>"
						+ "<td><b style=\"color:green;\">"+str_Off_Block_Time+"</b></td>"
						+ "<td><b style=\"color:green;\">"+countCobtChange+"</b></td></tr>");
				countCobtChange=0;
				}
			email_COBT_For_DIALCelebi_User9.append("</table>");
			System.out.println("Total Flights with cobt is not null:- "+cobtIsNotNull2.size());
			ResultSet result1 = DBWrapper.Connect(SQL_Queries.strQuery_7,environment);
			while (result1.next()){
				
				String str_LogID = result1.getString("Logid");	
				String str_FlightNumber_Arrival = result1.getString("FlightNumber_Arrival");
				String str_cobt = result1.getString("cobt");
				
				String str_Off_Block_Time = result1.getString("Off_Block_Time");
				ofBlockIsNotNull.add(str_Off_Block_Time);
				//HtmlReportUtil.stepInfo("");
				//System.out.println("| "+ str_LogID +" | "+ str_FlightNumber_Arrival +" | "+ str_cobt +" | "+ str_Off_Block_Time +" | ");
				System.out.println(" ");
				/*HtmlReportUtil.stepInfo("<b style=\"color:red;\"> FlightNumber:-</b><b style=\"color:red;\"> "+str_FlightNumber_Arrival
						+" </b>|| <b style=\"color:red;\">Cobt:-</b><b style=\"color:red;\"> "+ str_cobt 
						+"<b> || <b style=\"color:red;\">Off Block:- </b> <b style=\"color:red;\">"+ str_Off_Block_Time +"  </b>");*/
		
			}
			System.out.println("Total Flights with off block time is null:- "+ofBlockIsNotNull.size());
			//HtmlReportUtil.stepInfo("<b style=\"color:green;\"> No of Flights for which off block time is null:- "+ofBlockIsNotNull.size() +"</b>");
			ResultSet result2 =DBWrapper.Connect(SQL_Queries.strQuery_8,environment);
			while (result2.next()){
				String str_LogID = result2.getString("Logid");	
				String str_FlightNumber_Arrival = result2.getString("FlightNumber_Arrival");
				String str_cobt = result2.getString("cobt");
				String str_Off_Block_Time = result2.getString("Off_Block_Time");
				String str_cobtTimeDiff = result2.getString("CONCAT('',TIMEDIFF(Off_Block_Time, cobt))");
				int str_cobtSecDiff1 = result2.getInt("TIME_TO_SEC(TIMEDIFF(Off_Block_Time, cobt))");
				int str_cobtSecDiff2 = result2.getInt("TIME_TO_SEC(TIMEDIFF(cobt, Off_Block_Time))");
				cobtTimeDiff.add(str_Off_Block_Time);
				System.out.println("| "+ str_LogID +" | "+ str_FlightNumber_Arrival +" | "+ str_cobt +" | "+ str_Off_Block_Time +" | "+ str_cobtTimeDiff +" | ");
				JSONObject record = new JSONObject();
				record.put("logid",str_LogID);
				record.put("flightNumberArrival", str_FlightNumber_Arrival);
				record.put("COBT", str_cobt);
				record.put("OffBlockTime",str_Off_Block_Time);
				
				Json_TotalFlightsofEntity.add(record);
				
				//HtmlReportUtil.stepInfo("");
				if(str_cobtSecDiff1>300 || str_cobtSecDiff2>300  ) {
				/*HtmlReportUtil.stepInfo("<b style=\"color:red;\"> FlightNumber:-</b><b style=\"color:red;\"> "+str_FlightNumber_Arrival
						+" </b>|| <b style=\"color:red;\">Cobt:-</b><b style=\"color:red;\"> "+ str_cobt 
						+"<b> || <b style=\"color:red;\">Off Block:- </b> <b style=\"color:red;\">"+ str_Off_Block_Time 
						+" </b>|| <b style=\"color:red;\">Cobt Diff:-</b><b style=\"color:red;\"> "+ str_cobtTimeDiff +"  </b>");
				}else {
					/*HtmlReportUtil.stepInfo("<b style=\"color:green;\"> FlightNumber:-</b><b style=\"color:green;\"> "+str_FlightNumber_Arrival
							+" </b>|| <b style=\"color:green;\">Cobt:-</b><b style=\"color:green;\"> "+ str_cobt 
							+"<b> || <b style=\"color:green;\">Off Block:- </b> <b style=\"color:green;\">"+ str_Off_Block_Time 
							+" </b>|| <b style=\"color:green;\">Cobt Diff:-</b><b style=\"color:green;\"> "+ str_cobtTimeDiff +"  </b>");	
				*/
				}
				
			}
			System.out.println("Total Flights  - "+cobtTimeDiff.size());
			//HtmlReportUtil.stepInfo("<b style=\"color:green;\"> Total number of Flights: - "+cobtTimeDiff.size() +"</b>");
			//HtmlReportUtil.stepInfo("<b style=\"color:red;\">  No. of Flights for which cobt is null::- "+cobtIsNotNull.size() +"</b>");
			//HtmlReportUtil.stepInfo("<b style=\"color:red;\"> No of Flights for which off block time is null:- "+ofBlockIsNotNull.size() +"</b>");		
				
			ResultSet result3 = DBWrapper.Connect(SQL_Queries.strQuery_9,environment);
			while (result3.next()){			
				str_cobtDiff = result3.getString("count(CONCAT('',TIMEDIFF(Off_Block_Time, cobt)))");		
				//HtmlReportUtil.stepInfo("<b style=\"color:red;\"> No. of Flights for which difference between offblock and COBT is > 5mins :- "+str_cobtDiff +"</b>");			
				System.out.println(" No. of Flights for which difference between offblock and COBT is > 5mins :- "+str_cobtDiff);
			}
						
			 email_COBT_For_DIALCelebi_User5.append("<style>table#t01, th, td {border: 1px solid black;border-collapse: collapse;}table#t01 th{background-color:#80e5ff; } table#t01 tr:nth-child(even) {background-color: #f2f2f2;} table#t01 tr:nth-child(odd) { background-color: #DFEDEC;}table#t01 th, td {padding: 5px;}table#t01 th,td {text-align: center;} table#t01 caption {color: #008ae6;font-weight: bold;}</style>");

			email_COBT_For_DIALCelebi_User5.append("<h4 align=\"center\" style=\"color:#008ae6;\">Executed For :COBT For DIALCelebi</h4><h5 align=\"center\" style=\"color:#008ae6;\" >Execution Time: "+SQL_Queries.todayDayDateTime()+" </h5>");
			email_COBT_For_DIALCelebi_User5.append("<h5 align=\"center\" style=\"color:#008ae6;\" >Environment: "+environment+" </h5>");

			 email_COBT_For_DIALCelebi_User5.append("<table style=\"width:100%\" id=\"t01\"><tr><th style=\"width:15%\"><b>Date</b></th><th style=\"width:15%\"><b>Total No. of Flights</b></th><th style=\"width:15%\"><b>COBT detected</b></th> <th style=\"width:15%\"><b>COBT  Not detected</b></th>"
			 		+ "<th style=\"width:15%\"><b>OFFBLOCK not detected</b></th><th style=\"width:15%\"><b>Actual OFFBLOCK & COBT diff  > 5mins</b></th>"
			 		+ " </tr>");
			 email_COBT_For_DIALCelebi_User5.append(" <tr> <td><b>"+SQL_Queries.yesterDate()+"</b></td> <td><b>"+cobtTimeDiff.size()+"</b></td>"
			 		+ " <td> <b style=\"color:green;\">"+cobtIsNotNull2.size()+"</b></td>"
			 		+ " <td> <b style=\"color:red;\">"+cobtIsNotNull.size()+"</b></td>"
			 		+ " <td><b style=\"color:red;\">"+ofBlockIsNotNull.size()+"</b></td> <td><b style=\"color:red;\">"+str_cobtDiff+"</b></td>  </tr></table>");			 
			 emailbody.append("<style>table#t01, th, td {border: 1px solid black;border-collapse: collapse;}table#t01 th{background-color:#80e5ff; } table#t01 tr:nth-child(even) {background-color: #f2f2f2;} table#t01 tr:nth-child(odd) { background-color: #DFEDEC;}table#t01 th, td {padding: 5px;}table#t01 th,td {text-align: center;} table#t01 caption {color: #008ae6;font-weight: bold;}</style>");

			 emailbody.append("<h4 align=\"center\" style=\"color:#008ae6;\">Executed For :COBT For DIALCelebi</h4>");

			 emailbody.append("<table style=\"width:100%\" id=\"t01\"><tr><th style=\"width:15%\"><b>Date</b></th><th style=\"width:15%\"><b>Total No. of Flights</b></th><th style=\"width:15%\"><b>COBT detected</b></th> <th style=\"width:15%\"><b>COBT  Not detected</b></th>"
				 		+ "<th style=\"width:15%\"><b>OFFBLOCK not detected</b></th><th style=\"width:15%\"><b>Actual OFFBLOCK & COBT diff  > 5mins</b></th>"
				 		+ " </tr>");
			 emailbody.append(" <tr> <td><b>"+SQL_Queries.yesterDate()+"</b></td> <td><b>"+cobtTimeDiff.size()+"</b></td>"
				 		+ " <td> <b style=\"color:green;\">"+cobtIsNotNull2.size()+"</b></td>"
				 		+ " <td> <b style=\"color:red;\">"+cobtIsNotNull.size()+"</b></td>"
				 		+ " <td><b style=\"color:red;\">"+ofBlockIsNotNull.size()+"</b></td> <td><b style=\"color:red;\">"+str_cobtDiff+"</b></td>  </tr></table>");			 

			 // email_COBT_For_DIALCelebi_User5.append("</body></html>");
			// HtmlReportUtil.test.log(LogStatus.INFO, email_COBT_For_DIALCelebi_User5.toString());
			//	HtmlReportUtil.testHist.log(LogStatus.INFO, email_COBT_For_DIALCelebi_User5.toString());
				
			 email_COBT_For_DIALCelebi_User6.append("<style>table#t01, th, td {border: 1px solid black;border-collapse: collapse;}table#t01 th{background-color:#80e5ff; } table#t01 tr:nth-child(even) {background-color: #f2f2f2;} table#t01 tr:nth-child(odd) { background-color: #DFEDEC;}table#t01 th, td {padding: 5px;}table#t01 th,td {text-align: center;} table#t01 caption {color: #008ae6;font-weight: bold;}</style>");
			 email_COBT_For_DIALCelebi_User6.append("<table style=\"width:100%\" id=\"t01\"><h4><caption> COBT not detected flights</caption></h4>"
			 		+ "<tr><th style=\"width:25%\">LogID</th> <th style=\"width:25%\">FlightNumber_Arrival</th>"
				 		+ "<th style=\"width:25%\">COBT</th><th style=\"width:25%\">Off_Block_Time</th></tr>");
			 if(cobtIsNotNull.size()>0) {
			 ResultSet result6 = DBWrapper.Connect(SQL_Queries.strQuery_6,environment);
				while (result6.next()){
					
					String str_LogID = result6.getString("Logid");	
					String str_FlightNumber_Arrival = result6.getString("FlightNumber_Arrival");
					String str_cobt = result6.getString("cobt");
					String str_Off_Block_Time = result6.getString("Off_Block_Time");				
					email_COBT_For_DIALCelebi_User6.append("<tr><td><b style=\"color:red;\">"+str_LogID+"</b></td><td><b style=\"color:red;\">"+str_FlightNumber_Arrival+"</b></td>"
							+ "<td><b style=\"color:red;\">"+str_cobt+"</b></td><td><b style=\"color:red;\">"+str_Off_Block_Time+"</b></td></tr>");
					JSONObject record = new JSONObject();
					record.put("logid",str_LogID);
					record.put("flightNumberArrival", str_FlightNumber_Arrival);
					record.put("COBT", str_cobt);
					record.put("OffBlockTime",str_Off_Block_Time);
					
					Json_COBTisNOTdetected.add(record);
				
				}
	 }else {
			email_COBT_For_DIALCelebi_User6.append("<tr><td colspan=\"5\"><b style=\"color:red;\" >No values found</b></td></tr>");

	 }
				email_COBT_For_DIALCelebi_User6.append("</table>");
				//email_COBT_For_DIALCelebi_User6.append("</body></html>");
				// email_COBT_For_DIALCelebi_User.append("</body></html>");
				 email_COBT_For_DIALCelebi_User7.append("<style>table#t01, th, td {border: 1px solid black;border-collapse: collapse;}table#t01 th{background-color:#80e5ff; } table#t01 tr:nth-child(even) {background-color: #f2f2f2;} table#t01 tr:nth-child(odd) { background-color: #DFEDEC;}table#t01 th, td {padding: 5px;}table#t01 th,td {text-align: center;} table#t01 caption {color: #008ae6;font-weight: bold;}</style>");

				 email_COBT_For_DIALCelebi_User7.append("<table style=\"width:100%\" id=\"t01\"><h4><caption> OFFBLOCK not detected flights</caption></h4>"
					 		+ "<tr><th style=\"width:25%\">LogID</th> <th style=\"width:25%\">FlightNumber_Arrival</th>"
						 		+ "<th style=\"width:25%\">COBT</th><th style=\"width:25%\">Off_Block_Time</th></tr>");
				 if(ofBlockIsNotNull.size()>0) {
				 ResultSet result7 = DBWrapper.Connect(SQL_Queries.strQuery_7,environment);
					while (result7.next()){
						
						String str_LogID = result7.getString("Logid");	
						String str_FlightNumber_Arrival = result7.getString("FlightNumber_Arrival");
						String str_cobt = result7.getString("cobt");						
						String str_Off_Block_Time = result7.getString("Off_Block_Time");
						JSONObject record = new JSONObject();
						record.put("logid",str_LogID);
						record.put("flightNumberArrival", str_FlightNumber_Arrival);
						record.put("COBT", str_cobt);
						record.put("OffBlockTime",str_Off_Block_Time);					
						Json_OFFBLOCKisNOTdetected.add(record);
						email_COBT_For_DIALCelebi_User7.append("<tr><td><b style=\"color:red;\">"+str_LogID+"</b></td><td><b style=\"color:red;\">"+str_FlightNumber_Arrival+"</b></td>"
								+ "<td><b style=\"color:red;\">"+str_cobt+"</b></td><td><b style=\"color:red;\">"+str_Off_Block_Time+"</b></td></tr>");
					}
				 }else {
						email_COBT_For_DIALCelebi_User7.append("<tr><td colspan=\"5\"><b style=\"color:red;\" >No values found</b></td></tr>");	
				 }
					email_COBT_For_DIALCelebi_User7.append("</table>");
					 //email_COBT_For_DIALCelebi_User7.append("</body></html>");
					 email_COBT_For_DIALCelebi_User8.append("<style>table#t01, th, td {border: 1px solid black;border-collapse: collapse;}table#t01 th{background-color:#80e5ff; } table#t01 tr:nth-child(even) {background-color: #f2f2f2;} table#t01 tr:nth-child(odd) { background-color: #DFEDEC;}table#t01 th, td {padding: 5px;}table#t01 th,td {text-align: center;} table#t01 caption {color: #008ae6;font-weight: bold;}</style>");

					 email_COBT_For_DIALCelebi_User8.append("<table style=\"width:100%\" id=\"t01\"><h4><caption> OFFBLOCK & COBT DIFF > 5MINS</caption></h4>"
						 		+ "<tr><th style=\"width:10%\">LogID</th> <th style=\"width:25%\">FlightNumber_Arrival</th>"
							 		+ "<th style=\"width:25%\">COBT</th><th style=\"width:25%\">Off_Block_Time</th><th style=\"width:15%\">COBT Diff</th></tr>");
					 if(!str_cobtDiff.equals("0")) {
					 ResultSet result8 =DBWrapper.Connect(SQL_Queries.strQuery_8,environment);
						while (result8.next()){
							String str_LogID = result8.getString("Logid");	
							String str_FlightNumber_Arrival = result8.getString("FlightNumber_Arrival");
							String str_cobt = result8.getString("cobt");							
							String str_Off_Block_Time = result8.getString("Off_Block_Time");
							String str_cobtTimeDiff = result8.getString("CONCAT('',TIMEDIFF(Off_Block_Time, cobt))");
							int str_cobtSecDiff1 = result8.getInt("TIME_TO_SEC(TIMEDIFF(Off_Block_Time, cobt))");
							int str_cobtSecDiff2 = result8.getInt("TIME_TO_SEC(TIMEDIFF(cobt, Off_Block_Time))");
						if(str_cobtSecDiff1>300 || str_cobtSecDiff2>300  ) {
							JSONObject record = new JSONObject();
							record.put("logid",str_LogID);
							record.put("flightNumberArrival", str_FlightNumber_Arrival);
							record.put("COBT", str_cobt);
							record.put("OffBlockTime",str_Off_Block_Time);
							record.put("cobtTimeDiff",str_cobtTimeDiff);
							Json_differenceOfActualOffBlockAndFinalCOBTdiffIsGreaterThan5Minutes.add(record);
							email_COBT_For_DIALCelebi_User8.append("<tr><td><b style=\"color:red;\">"+str_LogID+"</b></td><td><b style=\"color:red;\">"+str_FlightNumber_Arrival+"</b></td>"
									+ "<td><b style=\"color:red;\">"+str_cobt+"</b></td><td><b style=\"color:red;\">"+str_Off_Block_Time+"</b></td><td><b style=\"color:red;\">"+str_cobtTimeDiff+"</b></td></tr>");							
							}else {  
							}
							
						}}else {
							email_COBT_For_DIALCelebi_User8.append("<tr><td colspan=\"5\"><b style=\"color:red;\" >No values found</b></td></tr>");	
		
						}
			email_COBT_For_DIALCelebi_User8.append("</table>");
			
			 email_COBT_For_DIALCelebi_User10.append("<style>table#t01, th, td {border: 1px solid black;border-collapse: collapse;}table#t01 th{background-color:#80e5ff; } table#t01 tr:nth-child(even) {background-color: #f2f2f2;} table#t01 tr:nth-child(odd) { background-color: #DFEDEC;}table#t01 th, td {padding: 5px;}table#t01 th,td {text-align: center;} table#t01 caption {color: #008ae6;font-weight: bold;}</style>");

			 email_COBT_For_DIALCelebi_User10.append("<table style=\"width:100%\" id=\"t01\"><h4><caption> Activity COBT that has difference > 5 minutes to OffBlock (Actual)</caption></h4>"
				 		+ "<tr><th style=\"width:10%\">LogID</th> <th style=\"width:15%\">ActivityCode</th>"
					 	+ "<th style=\"width:30%\">COBT value w.r.t ActivityCode</th><th style=\"width:25%\">Off_Block_Time</th><th style=\"width:30%\">COBT Diff</th></tr>");
			 email_COBT_For_DIALCelebi_User11.append("<style>table#t01, th, td {border: 1px solid black;border-collapse: collapse;}table#t01 th{background-color:#80e5ff; } table#t01 tr:nth-child(even) {background-color: #f2f2f2;} table#t01 tr:nth-child(odd) { background-color: #DFEDEC;}table#t01 th, td {padding: 5px;}table#t01 th,td {text-align: center;} table#t01 caption {color: #008ae6;font-weight: bold;}</style>");

			 email_COBT_For_DIALCelebi_User11.append("<table style=\"width:100%\" id=\"t01\"><h4><caption> Activity COBT that has difference < 5 minutes to OffBlock (Actual)</caption></h4>"
				 		+ "<tr><th style=\"width:10%\">LogID</th> <th style=\"width:15%\">ActivityCode</th>"
					 	+ "<th style=\"width:30%\">COBT value w.r.t ActivityCode</th><th style=\"width:25%\">Off_Block_Time</th><th style=\"width:30%\">COBT Diff</th></tr>");

			
			String strQuery ="select a.LogId, a.Off_Block_Time,b.cobt,b.pts, CONCAT('',TIMEDIFF(a.Off_Block_Time, b.cobt)), TIME_TO_SEC(TIMEDIFF(a.Off_Block_Time, b.cobt)) FROM `DailyFlightSchedule_Merged` a inner join (select * from  COBTTimeline where Id in (SELECT max(Id) FROM COBTTimeline where pts is not null GROUP BY FlightPK, PTS)) b on a.LogId = b.flightpk where (date(a.atd)='"+SQL_Queries.yesterDate()+"' or date(a.sensor_atd)='"+SQL_Queries.yesterDate()+"') and a.operationunit = 22 and a.FlightNumber_Arrival regexp 'TK|EY|CX|QR|ET|AK|KC|JL|KE|PS|AC|AF|AZ|BA|SU|IA|SV|UL|T5|FZ|KQ|I5' and a.cobt is not  null and a.Off_Block_Time is not null";
			
			ResultSet resultt = DBWrapper.Connect(strQuery, environment);
				while (resultt.next()){	
				
					String str_LogID = resultt.getString("a.Logid");	
					String str_cobtt=resultt.getString("b.cobt");
					String str_pts=resultt.getString("b.pts");
					String str_Off_Block_Time = resultt.getString("a.Off_Block_Time");
					String difference = resultt.getString("CONCAT('',TIMEDIFF(a.Off_Block_Time, b.cobt))");
					int differenceInSec = resultt.getInt("TIME_TO_SEC(TIMEDIFF(a.Off_Block_Time, b.cobt))");
					
						 if(differenceInSec>300  ) {
							 countCobtChange1.add(str_LogID);
					email_COBT_For_DIALCelebi_User10.append("<tr><td ><b style=\"color:red;\">"+str_LogID+"</b></td>"						
							+ "<td><b style=\"color:red;\">"+str_pts+"</b></td>"
							+ "<td><b style=\"color:red;\">"+str_cobtt+"</b></td>"
							+ "<td><b style=\"color:red;\">"+str_Off_Block_Time+"</b></td>"
							+ "<td><b style=\"color:red;\">"+difference+ " </b></td></tr>");	
						 }else {
							 countCobtChange2.add(str_LogID);
							 email_COBT_For_DIALCelebi_User11.append("<tr><td ><b style=\"color:green;\">"+str_LogID+"</b></td>"						
										+ "<td><b style=\"color:green;\">"+str_pts+"</b></td>"
										+ "<td><b style=\"color:green;\">"+str_cobtt+"</b></td>"
										+ "<td><b style=\"color:green;\">"+str_Off_Block_Time+"</b></td>"
										+ "<td><b style=\"color:green;\">"+ difference+"</b></td></tr>");	 
						 }
						 
					
					
	 
					}
				email_COBT_For_DIALCelebi_User10.append("</table>");
				email_COBT_For_DIALCelebi_User11.append("</table>");
			// email_COBT_For_DIALCelebi_User8.append("</body></html>");
			//HtmlReportUtil.stepInfo(email_COBT_For_DIALCelebi_User5.toString());
			 HtmlReportUtil.test.log(LogStatus.INFO, email_COBT_For_DIALCelebi_User5.toString());
			 HtmlReportUtil.testHist.log(LogStatus.INFO, email_COBT_For_DIALCelebi_User5.toString());
			 
			 ExtentTest child0 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:green;\" align=\"center\">Total Flights of Entity: "+cobtTimeDiff.size()+"</b>");
			 child0.log(LogStatus.INFO, "<b style=\"color:green;\" >Total Flights of Entity: "+cobtTimeDiff.size()+"</b>");
			 ExtentTest child00 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:green;\" align=\"center\">Total Flights of Entity: "+cobtTimeDiff.size()+"</b>");
			 child00.log(LogStatus.INFO,"<b style=\"color:green;\" >Total Flights of Entity: "+cobtTimeDiff.size()+"</b>");
			 	
			
	    	ExtentTest child1 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:red;\" align=\"center\">Total Flights For which COBT is NOT detected: "+cobtIsNotNull.size()+"</b>");
			 child1.log(LogStatus.INFO, email_COBT_For_DIALCelebi_User6.toString());
			 ExtentTest child11 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:red;\" align=\"center\">Total Flights For which COBT is NOT detected: "+cobtIsNotNull.size()+"</b>");
			 child11.log(LogStatus.INFO, email_COBT_For_DIALCelebi_User6.toString());
			 
			 ExtentTest child2 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:red;\" align=\"center\">Total Flights For which OFF BLOCK is NOT detected: "+ofBlockIsNotNull.size()+"</b>");
			 child2.log(LogStatus.INFO, email_COBT_For_DIALCelebi_User7.toString());
			 ExtentTest child12 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:red;\" align=\"center\">Total Flights For which OFF BLOCK is NOT detected: "+ofBlockIsNotNull.size()+"</b>");
			 child12.log(LogStatus.INFO, email_COBT_For_DIALCelebi_User7.toString());
			 
			 ExtentTest child3 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:red;\" align=\"center\">Total No of flights for which difference of Actual OffBlock and final COBT diff is > 5 Minutes: "+str_cobtDiff+"</b>");
			 child3.log(LogStatus.INFO, email_COBT_For_DIALCelebi_User8.toString());			 
			 ExtentTest child13 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:red;\" align=\"center\">Total No of flights for which difference of Actual OffBlock and final COBT diff is > 5 Minutes: "+str_cobtDiff+"</b>");
			 child13.log(LogStatus.INFO, email_COBT_For_DIALCelebi_User8.toString());
			 
			 ExtentTest child10 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:green;\" align=\"center\">Total Flights For which COBT is detected: "+cobtIsNotNull2.size()+"</b>");
			 child10.log(LogStatus.INFO, email_COBT_For_DIALCelebi_User9.toString());
			 ExtentTest child110 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:green;\" align=\"center\">Total Flights For which COBT is detected: "+cobtIsNotNull2.size()+"</b>");
			 child110.log(LogStatus.INFO, email_COBT_For_DIALCelebi_User9.toString());
			 
			 ExtentTest child30 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:green;\" align=\"center\">Activity COBT that has difference less than 5 minutes to OffBlock (Actual):"+countCobtChange2.size()+" </b>");
			 child30.log(LogStatus.INFO, email_COBT_For_DIALCelebi_User11.toString());			 
			 ExtentTest child130 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:green;\" align=\"center\">Activity COBT that has difference less than 5 minutes to OffBlock  (Actual): "+countCobtChange2.size()+" </b>");
			 child130.log(LogStatus.INFO, email_COBT_For_DIALCelebi_User11.toString());
			 
			 ExtentTest child030 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:red;\" align=\"center\">Activity COBT that has difference greater than 5 minutes to OffBlock (Actual): "+countCobtChange1.size()+"</b>");
			 child030.log(LogStatus.INFO, email_COBT_For_DIALCelebi_User10.toString());			 
			 ExtentTest child0130 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:red;\" align=\"center\">Activity COBT that has difference greater than 5 minutes to OffBlock (Actual): "+countCobtChange1.size()+"</b>");
			 child0130.log(LogStatus.INFO, email_COBT_For_DIALCelebi_User10.toString());

			 
			 HtmlReportUtil.test.appendChild(child0).appendChild(child10).appendChild(child1).appendChild(child2).appendChild(child3).appendChild(child30).appendChild(child030);
			 HtmlReportUtil.testHist.appendChild(child0).appendChild(child110).appendChild(child11).appendChild(child12).appendChild(child13).appendChild(child130).appendChild(child0130);
			 
			 COBT_For_DIALCelebi_DataJson.put("Total Flights of Entity", Json_TotalFlightsofEntity);
			 COBT_For_DIALCelebi_DataJson.put("Cobt not detected",Json_COBTisNOTdetected);
			 COBT_For_DIALCelebi_DataJson.put("OffBlock not detected",Json_OFFBLOCKisNOTdetected);
			 COBT_For_DIALCelebi_DataJson.put("Difference Of Actual OffBlock and FinalCOBT is > 5 Minutes",Json_differenceOfActualOffBlockAndFinalCOBTdiffIsGreaterThan5Minutes);
			 AV_2268_COBT_For_DIALCelebi_UserJson.put("data", COBT_For_DIALCelebi_DataJson);
			 AV_2268_COBT_For_DIALCelebi_UserArray.add(AV_2268_COBT_For_DIALCelebi_UserJson);			 
			 System.out.println("\nJSON Object: " + AV_2268_COBT_For_DIALCelebi_UserArray);	
			 
			/* RestAssured.baseURI="http://192.168.2.205:8086";
				RequestSpecification request=RestAssured.given();
				request.header("Content-Type","application/json"); 
				
				request.body(AV_2268_COBT_For_DIALCelebi_UserArray);
				Response response=request.post("/reports/records");
				int code=response.getStatusCode();
				System.out.println("Status code :-"+code);
				String res=response.asString();
				System.out.println("Responce :-"+res);
				System.out.println("Responce :-"+response.prettyPrint());	
				// Header named Content-Type
			    String contentType = response.header("Content-Type");
			    System.out.println("Content-Type value: " + contentType);
				Headers allHeaders = response.headers();		 
				 // Iterate over all the Headers
			    for(Header header : allHeaders)
				 {
				 System.out.println("Key: " + header.getName() + " Value: " + header.getValue());
				 }
				
*/
			
			 
			 DBWrapper.dbConnectionClose();	
			
	 }
}
