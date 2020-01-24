package enh.db.cases;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import utilities.HtmlReportUtil;

public class AV_2272_CELEBI_DELHI_Verify_Pushback_Activities {
	
	public static ArrayList<String> list_totalActivePushbackDevices = new ArrayList<String>();
	public static ArrayList<String> list_PushbackDevicesNotUtilized = new ArrayList<String>();
	public static ArrayList<String> List_totalPushbackDevicesUtilized = new ArrayList<String>();
	
	public static ArrayList<String> list_totalFlightsScheduledForDeparture = new ArrayList<String>();
	public static ArrayList<String> list_scheduledDepartureFoundInFlightMaster= new ArrayList<String>();
	public static ArrayList<String> list_scheduledDepartureNarrowbody= new ArrayList<String>();
	public static ArrayList<String> list_scheduledDepartureWidebody= new ArrayList<String>();
	public static ArrayList<String> list_scheduledDepartureBombardier= new ArrayList<String>();
	
	public static ArrayList<String> list_totalFlightsActuallyDeparted= new ArrayList<String>();
	public static ArrayList<String> list_actuallyDepartedNarrowbody= new ArrayList<String>();
	public static ArrayList<String> list_actuallyDepartedWidebody= new ArrayList<String>();
	public static ArrayList<String> list_actuallyDepartedBombardier= new ArrayList<String>();
	
	public static HashSet<String> set_actuallyDepartureFoundInFlightMaster= new HashSet<String>();
	public static HashSet<String> set_actuallyDepartedUnknown= new HashSet<String>();
	
	public static HashSet<String> set_totalForNarrowBody= new HashSet<String>();
	public static HashSet<String> set_totalForWideBody= new HashSet<String>();
	public static HashSet<String> set_totalForBombardier= new HashSet<String>();
	public static HashSet<String> set_totalForUnknown= new HashSet<String>();
	
	public static int correctPushbackSequenceNB =0;
	public static int incorrectPushbackSequenceNB =0;
	public static int correctPushbackSequenceWB =0;
	public static int incorrectPushbackSequenceWB= 0;
	public static int correctPushbackSequenceBombardier =0;
	public static int incorrectPushbackSequenceBombardier =0;
	public static int correctPushbackSequenceOthers =0;
	public static int incorrectPushbackSequenceOthers =0;
	
	public static HashSet<String> set_BothPushbackAndPBTNotFormedForNarrowbody = new HashSet<String>();
	public static HashSet<String> set_PushbackFormedForNarrowbody = new HashSet<String>();
	public static HashSet<String> set_PushbackFormedButPBTNotFormedForNarrowbody = new HashSet<String>();
	public static HashSet<String> set_BothPushbackAndPBTFormedForNarrowbody = new HashSet<String>();
	public static HashSet<String> set_PBTFormedButPushbackNotFormedForNarrowbody = new HashSet<String>();
	public static HashSet<String> set_PBTFormedForNarrowbody = new HashSet<String>();
	
	public static HashSet <String> set_PushbackFormedForWidebody= new HashSet<String>();
	public static HashSet <String> set_PBTFormedForWidebody = new HashSet<String>();
	public static HashSet <String> set_PushbackFormedButPBTNotFormedForWidebody= new HashSet<String>();
	public static HashSet <String> set_PBTFormedButPushbackNotFormedForWidebody= new HashSet<String>();
	public static HashSet <String> set_BothPushbackAndPBTNotFormedForWidebody= new HashSet<String>();
	public static HashSet <String> set_BothPushbackAndPBTFormedForWidebody= new HashSet<String>();
	
	public static HashSet <String> set_PushbackFormedForBombardier= new HashSet<String>();
	public static HashSet <String> set_PBTFormedForBombardier = new HashSet<String>();
	public static HashSet <String> set_PushbackFormedButPBTNotFormedForBombardier= new HashSet<String>();
	public static HashSet <String> set_PBTFormedButPushbackNotFormedForBombardier= new HashSet<String>();
	public static HashSet <String> set_BothPushbackAndPBTNotFormedForBombardier= new HashSet<String>();
	public static HashSet <String> set_BothPushbackAndPBTFormedForBombardier= new HashSet<String>();
	
	public static HashSet <String> set_PushbackFormedForUnknown= new HashSet<String>();
	public static HashSet <String> set_PBTFormedForUnknown = new HashSet<String>();
	public static HashSet <String> set_PushbackFormedButPBTNotFormedForUnknown= new HashSet<String>();
	public static HashSet <String> set_PBTFormedButPushbackNotFormedForUnknown= new HashSet<String>();
	public static HashSet <String> set_BothPushbackAndPBTNotFormedForUnknown= new HashSet<String>();
	public static HashSet <String> set_BothPushbackAndPBTFormedForUnknown= new HashSet<String>();
	
	public static Timestamp flogdateForPBT = null;
	public static Timestamp flogdateForPushback = null;
	public static Timestamp tlogdateForPBT = null;
	public static Timestamp tlogdateForPushback = null;
	
	public static StringBuilder email_report_Pushback_And_PBT_For_CELEBI_Delhi1 = new StringBuilder();
	public static StringBuilder email_report_Pushback_And_PBT_For_CELEBI_Delhi2 = new StringBuilder();
	public static StringBuilder email_report_Pushback_And_PBT_For_CELEBI_Delhi3 = new StringBuilder();
	public static StringBuilder email_report_Pushback_And_PBT_For_CELEBI_Delhi4 = new StringBuilder();
	public static StringBuilder email_report_Pushback_And_PBT_For_CELEBI_Delhi5 = new StringBuilder();
	public static StringBuilder email_report_Pushback_And_PBT_For_CELEBI_Delhi6 = new StringBuilder();
	public static StringBuilder email_report_Pushback_And_PBT_For_CELEBI_Delhi7 = new StringBuilder();
	public static StringBuilder email_report_Pushback_And_PBT_For_CELEBI_Delhi8 = new StringBuilder();
	public static StringBuilder email_report_Pushback_And_PBT_For_CELEBI_Delhi9 = new StringBuilder();
	public static StringBuilder email_report_Pushback_And_PBT_For_CELEBI_Delhi10 = new StringBuilder();
	public static StringBuilder email_report_Pushback_And_PBT_For_CELEBI_Delhi11 = new StringBuilder();
	public static StringBuilder email_report_Pushback_And_PBT_For_CELEBI_Delhi12 = new StringBuilder();
	public static StringBuilder email_report_Pushback_And_PBT_For_CELEBI_Delhi13 = new StringBuilder();
	public static StringBuilder email_report_Pushback_And_PBT_For_CELEBI_Delhi14 = new StringBuilder();
	public static StringBuilder email_report_Pushback_And_PBT_For_CELEBI_Delhi15 = new StringBuilder();
	public static StringBuilder email_report_Pushback_And_PBT_For_CELEBI_Delhi16 = new StringBuilder();
	public static StringBuilder email_report_Pushback_And_PBT_For_CELEBI_Delhi17 = new StringBuilder();
	public static StringBuilder email_report_Pushback_And_PBT_For_CELEBI_Delhi18 = new StringBuilder();
	public static StringBuilder email_report_Pushback_And_PBT_For_CELEBI_Delhi19 = new StringBuilder();
	public static StringBuilder email_report_Pushback_And_PBT_For_CELEBI_Delhi20 = new StringBuilder();
	public static StringBuilder email_report_Pushback_And_PBT_For_CELEBI_Delhi21 = new StringBuilder();
	public static StringBuilder email_report_Pushback_And_PBT_For_CELEBI_Delhi22 = new StringBuilder();
	
 public static void VerifyPushbackActivities_for_CELEBI_Delhi(int operationunit, String devId, String environment) throws SQLException
 {
	 email_report_Pushback_And_PBT_For_CELEBI_Delhi2.append("<br><br>");
	 email_report_Pushback_And_PBT_For_CELEBI_Delhi2.append("<style>table#t01, th, td {border: 1px solid black;border-collapse: collapse;}table#t01 th{background-color:#80e5ff; } table#t01 tr:nth-child(even) {background-color: #f2f2f2;} table#t01 tr:nth-child(odd) { background-color: #DFEDEC;}table#t01 th, td {padding: 5px;}table#t01 th,td {text-align: center;} table#t01 caption {color: #008ae6;font-weight: bold;}</style>");
	 email_report_Pushback_And_PBT_For_CELEBI_Delhi2.append("<table style=\"width:100%\" id=\"t01\"><caption> Total Number of Pushback tractors NOT Utilized</caption><tr>"
							+ "<th style=\"width:15%\"><b>Device ID</b></th> "
							+ " </tr>");
	 email_report_Pushback_And_PBT_For_CELEBI_Delhi3.append("<br><br>");
	 email_report_Pushback_And_PBT_For_CELEBI_Delhi3.append("<style>table#t01, th, td {border: 1px solid black;border-collapse: collapse;}table#t01 th{background-color:#80e5ff; } table#t01 tr:nth-child(even) {background-color: #f2f2f2;} table#t01 tr:nth-child(odd) { background-color: #DFEDEC;}table#t01 th, td {padding: 5px;}table#t01 th,td {text-align: center;} table#t01 caption {color: #008ae6;font-weight: bold;}</style>");
	 email_report_Pushback_And_PBT_For_CELEBI_Delhi3.append("<table style=\"width:100%\" id=\"t01\"><caption> Flights with all Pushback activities (Pushback Arrival & Pushback) For Narrow Body : </caption><tr>"
							+ "<th style=\"width:20%\"><b>Log ID</b></th> "
							+ "<th style=\"width:20%\"><b>Flight Number Departure</b></th> "
							+ " </tr>");
	 email_report_Pushback_And_PBT_For_CELEBI_Delhi4.append("<br><br>");
	 email_report_Pushback_And_PBT_For_CELEBI_Delhi4.append("<style>table#t01, th, td {border: 1px solid black;border-collapse: collapse;}table#t01 th{background-color:#80e5ff; } table#t01 tr:nth-child(even) {background-color: #f2f2f2;} table#t01 tr:nth-child(odd) { background-color: #DFEDEC;}table#t01 th, td {padding: 5px;}table#t01 th,td {text-align: center;} table#t01 caption {color: #008ae6;font-weight: bold;}</style>");
	 email_report_Pushback_And_PBT_For_CELEBI_Delhi4.append("<table style=\"width:100%\" id=\"t01\"><caption> Flights with NO Pushback Arrival & NO Pushback activities For Narrow Body : </caption><tr>"
							+ "<th style=\"width:20%\"><b>LogID</b></th> "
							+ "<th style=\"width:20%\"><b>Flight Number Departure</b></th> "
							+ " </tr>");
	 email_report_Pushback_And_PBT_For_CELEBI_Delhi5.append("<br><br>");
	 email_report_Pushback_And_PBT_For_CELEBI_Delhi5.append("<style>table#t01, th, td {border: 1px solid black;border-collapse: collapse;}table#t01 th{background-color:#80e5ff; } table#t01 tr:nth-child(even) {background-color: #f2f2f2;} table#t01 tr:nth-child(odd) { background-color: #DFEDEC;}table#t01 th, td {padding: 5px;}table#t01 th,td {text-align: center;} table#t01 caption {color: #008ae6;font-weight: bold;}</style>");
	 email_report_Pushback_And_PBT_For_CELEBI_Delhi5.append("<table style=\"width:100%\" id=\"t01\"><caption> Flights with Pushback Arrival but NO Pushback activities For Narrow Body : </caption><tr>"
							+ "<th style=\"width:20%\"><b>Log ID</b></th> "
							+ "<th style=\"width:20%\"><b>Flight Number Departure</b></th> "
							+ " </tr>");
	 email_report_Pushback_And_PBT_For_CELEBI_Delhi6.append("<br><br>");
	 email_report_Pushback_And_PBT_For_CELEBI_Delhi6.append("<style>table#t01, th, td {border: 1px solid black;border-collapse: collapse;}table#t01 th{background-color:#80e5ff; } table#t01 tr:nth-child(even) {background-color: #f2f2f2;} table#t01 tr:nth-child(odd) { background-color: #DFEDEC;}table#t01 th, td {padding: 5px;}table#t01 th,td {text-align: center;} table#t01 caption {color: #008ae6;font-weight: bold;}</style>");
	 email_report_Pushback_And_PBT_For_CELEBI_Delhi6.append("<table style=\"width:100%\" id=\"t01\"><caption> Flights with Pushback but NO Pushback Arrival activities For Narrow Body : </caption><tr>"
							+ "<th style=\"width:20%\"><b>Log ID</b></th> "
							+ "<th style=\"width:20%\"><b>Flight Number Departure</b></th> "
							+ " </tr>");
		
	 email_report_Pushback_And_PBT_For_CELEBI_Delhi7.append("<br><br>");
	 email_report_Pushback_And_PBT_For_CELEBI_Delhi7.append("<style>table#t01, th, td {border: 1px solid black;border-collapse: collapse;}table#t01 th{background-color:#80e5ff; } table#t01 tr:nth-child(even) {background-color: #f2f2f2;} table#t01 tr:nth-child(odd) { background-color: #DFEDEC;}table#t01 th, td {padding: 5px;}table#t01 th,td {text-align: center;} table#t01 caption {color: #008ae6;font-weight: bold;}</style>");
	 email_report_Pushback_And_PBT_For_CELEBI_Delhi7.append("<table style=\"width:100%\" id=\"t01\"><caption> Flights with Pushback activity with incorrect sequence For Narrow Body : </caption><tr>"
			 				+ "<th style=\"width:10%\"><b>Flight PK</b></th> "
			 				+ "<th style=\"width:10%\"><b>Flight Number</b></th> "
			 				+ "<th style=\"width:10%\"><b>DevID</b></th> "
			 				+ "<th style=\"width:15%\"><b>PBT (Start)</b></th> "
			 				+ "<th style=\"width:15%\"><b>Pushback (Start)</b></th> "
			 				+ "<th style=\"width:20%\"><b>PBT (End)</b></th> "
			 				+ "<th style=\"width:20%\"><b>Pushback (End)</b></th> "
			 				+ " </tr>");
	 email_report_Pushback_And_PBT_For_CELEBI_Delhi8.append("<br><br>");
	 email_report_Pushback_And_PBT_For_CELEBI_Delhi8.append("<style>table#t01, th, td {border: 1px solid black;border-collapse: collapse;}table#t01 th{background-color:#80e5ff; } table#t01 tr:nth-child(even) {background-color: #f2f2f2;} table#t01 tr:nth-child(odd) { background-color: #DFEDEC;}table#t01 th, td {padding: 5px;}table#t01 th,td {text-align: center;} table#t01 caption {color: #008ae6;font-weight: bold;}</style>");
	 email_report_Pushback_And_PBT_For_CELEBI_Delhi8.append("<table style=\"width:100%\" id=\"t01\"><caption> Flights with all Pushback activities (Pushback Arrival & Pushback) For Wide Body : </caption><tr>"
							+ "<th style=\"width:20%\"><b>Log ID</b></th> "
							+ "<th style=\"width:20%\"><b>Flight Number Departure</b></th> "
							+ " </tr>");
	 email_report_Pushback_And_PBT_For_CELEBI_Delhi9.append("<br><br>");
	 email_report_Pushback_And_PBT_For_CELEBI_Delhi9.append("<style>table#t01, th, td {border: 1px solid black;border-collapse: collapse;}table#t01 th{background-color:#80e5ff; } table#t01 tr:nth-child(even) {background-color: #f2f2f2;} table#t01 tr:nth-child(odd) { background-color: #DFEDEC;}table#t01 th, td {padding: 5px;}table#t01 th,td {text-align: center;} table#t01 caption {color: #008ae6;font-weight: bold;}</style>");
	 email_report_Pushback_And_PBT_For_CELEBI_Delhi9.append("<table style=\"width:100%\" id=\"t01\"><caption> Flights with NO Pushback Arrival & NO Pushback activities For Wide Body : </caption><tr>"
							+ "<th style=\"width:20%\"><b>LogID</b></th> "
							+ "<th style=\"width:20%\"><b>Flight Number Departure</b></th> "
							+ " </tr>");
	 email_report_Pushback_And_PBT_For_CELEBI_Delhi10.append("<br><br>");
	 email_report_Pushback_And_PBT_For_CELEBI_Delhi10.append("<style>table#t01, th, td {border: 1px solid black;border-collapse: collapse;}table#t01 th{background-color:#80e5ff; } table#t01 tr:nth-child(even) {background-color: #f2f2f2;} table#t01 tr:nth-child(odd) { background-color: #DFEDEC;}table#t01 th, td {padding: 5px;}table#t01 th,td {text-align: center;} table#t01 caption {color: #008ae6;font-weight: bold;}</style>");
	 email_report_Pushback_And_PBT_For_CELEBI_Delhi10.append("<table style=\"width:100%\" id=\"t01\"><caption> Flights with Pushback Arrival but NO Pushback activities For Wide Body : </caption><tr>"
							+ "<th style=\"width:20%\"><b>Log ID</b></th> "
							+ "<th style=\"width:20%\"><b>Flight Number Departure</b></th> "
							+ " </tr>");
	 email_report_Pushback_And_PBT_For_CELEBI_Delhi11.append("<br><br>");
	 email_report_Pushback_And_PBT_For_CELEBI_Delhi11.append("<style>table#t01, th, td {border: 1px solid black;border-collapse: collapse;}table#t01 th{background-color:#80e5ff; } table#t01 tr:nth-child(even) {background-color: #f2f2f2;} table#t01 tr:nth-child(odd) { background-color: #DFEDEC;}table#t01 th, td {padding: 5px;}table#t01 th,td {text-align: center;} table#t01 caption {color: #008ae6;font-weight: bold;}</style>");
	 email_report_Pushback_And_PBT_For_CELEBI_Delhi11.append("<table style=\"width:100%\" id=\"t01\"><caption> Flights with Pushback but NO Pushback Arrival activities For Wide Body : </caption><tr>"
							+ "<th style=\"width:20%\"><b>Log ID</b></th> "
							+ "<th style=\"width:20%\"><b>Flight Number Departure</b></th> "
							+ " </tr>");
		
	 email_report_Pushback_And_PBT_For_CELEBI_Delhi12.append("<br><br>");
	 email_report_Pushback_And_PBT_For_CELEBI_Delhi12.append("<style>table#t01, th, td {border: 1px solid black;border-collapse: collapse;}table#t01 th{background-color:#80e5ff; } table#t01 tr:nth-child(even) {background-color: #f2f2f2;} table#t01 tr:nth-child(odd) { background-color: #DFEDEC;}table#t01 th, td {padding: 5px;}table#t01 th,td {text-align: center;} table#t01 caption {color: #008ae6;font-weight: bold;}</style>");
	 email_report_Pushback_And_PBT_For_CELEBI_Delhi12.append("<table style=\"width:100%\" id=\"t01\"><caption> Flights with Pushback activity with incorrect sequence For Wide Body : </caption><tr>"
							+ "<th style=\"width:10%\"><b>Flight PK</b></th> "
							+ "<th style=\"width:10%\"><b>Flight Number</b></th> "
							+ "<th style=\"width:10%\"><b>DevID</b></th> "
							+ "<th style=\"width:15%\"><b>PBT (Start)</b></th> "
							+ "<th style=\"width:15%\"><b>Pushback (Start)</b></th> "
							+ "<th style=\"width:20%\"><b>PBT (End)</b></th> "
							+ "<th style=\"width:20%\"><b>Pushback (End)</b></th> "
							+ " </tr>");
		
	 email_report_Pushback_And_PBT_For_CELEBI_Delhi13.append("<br><br>");
	 email_report_Pushback_And_PBT_For_CELEBI_Delhi13.append("<style>table#t01, th, td {border: 1px solid black;border-collapse: collapse;}table#t01 th{background-color:#80e5ff; } table#t01 tr:nth-child(even) {background-color: #f2f2f2;} table#t01 tr:nth-child(odd) { background-color: #DFEDEC;}table#t01 th, td {padding: 5px;}table#t01 th,td {text-align: center;} table#t01 caption {color: #008ae6;font-weight: bold;}</style>");
	 email_report_Pushback_And_PBT_For_CELEBI_Delhi13.append("<table style=\"width:100%\" id=\"t01\"><caption> Flights with all Pushback activities (Pushback Arrival & Pushback) For Bombardier : </caption><tr>"
							+ "<th style=\"width:20%\"><b>Log ID</b></th> "
							+ "<th style=\"width:20%\"><b>Flight Number Departure</b></th> "
							+ " </tr>");
	 email_report_Pushback_And_PBT_For_CELEBI_Delhi14.append("<br><br>");
	 email_report_Pushback_And_PBT_For_CELEBI_Delhi14.append("<style>table#t01, th, td {border: 1px solid black;border-collapse: collapse;}table#t01 th{background-color:#80e5ff; } table#t01 tr:nth-child(even) {background-color: #f2f2f2;} table#t01 tr:nth-child(odd) { background-color: #DFEDEC;}table#t01 th, td {padding: 5px;}table#t01 th,td {text-align: center;} table#t01 caption {color: #008ae6;font-weight: bold;}</style>");
	 email_report_Pushback_And_PBT_For_CELEBI_Delhi14.append("<table style=\"width:100%\" id=\"t01\"><caption> Flights with NO Pushback Arrival & NO Pushback activities For Bombardier : </caption><tr>"
							+ "<th style=\"width:20%\"><b>LogID</b></th> "
							+ "<th style=\"width:20%\"><b>Flight Number Departure</b></th> "
							+ " </tr>");
	 email_report_Pushback_And_PBT_For_CELEBI_Delhi15.append("<br><br>");
	 email_report_Pushback_And_PBT_For_CELEBI_Delhi15.append("<style>table#t01, th, td {border: 1px solid black;border-collapse: collapse;}table#t01 th{background-color:#80e5ff; } table#t01 tr:nth-child(even) {background-color: #f2f2f2;} table#t01 tr:nth-child(odd) { background-color: #DFEDEC;}table#t01 th, td {padding: 5px;}table#t01 th,td {text-align: center;} table#t01 caption {color: #008ae6;font-weight: bold;}</style>");
	 email_report_Pushback_And_PBT_For_CELEBI_Delhi15.append("<table style=\"width:100%\" id=\"t01\"><caption> Flights with Pushback Arrival but NO Pushback activities For Bombardier : </caption><tr>"
							+ "<th style=\"width:20%\"><b>Log ID</b></th> "
							+ "<th style=\"width:20%\"><b>Flight Number Departure</b></th> "
							+ " </tr>");
	 email_report_Pushback_And_PBT_For_CELEBI_Delhi16.append("<br><br>");
	 email_report_Pushback_And_PBT_For_CELEBI_Delhi16.append("<style>table#t01, th, td {border: 1px solid black;border-collapse: collapse;}table#t01 th{background-color:#80e5ff; } table#t01 tr:nth-child(even) {background-color: #f2f2f2;} table#t01 tr:nth-child(odd) { background-color: #DFEDEC;}table#t01 th, td {padding: 5px;}table#t01 th,td {text-align: center;} table#t01 caption {color: #008ae6;font-weight: bold;}</style>");
	 email_report_Pushback_And_PBT_For_CELEBI_Delhi16.append("<table style=\"width:100%\" id=\"t01\"><caption> Flights with Pushback but NO Pushback Arrival activities For Bombardier : </caption><tr>"
							+ "<th style=\"width:20%\"><b>Log ID</b></th> "
							+ "<th style=\"width:20%\"><b>Flight Number Departure</b></th> "
							+ " </tr>");
		
	 email_report_Pushback_And_PBT_For_CELEBI_Delhi17.append("<br><br>");
	 email_report_Pushback_And_PBT_For_CELEBI_Delhi17.append("<style>table#t01, th, td {border: 1px solid black;border-collapse: collapse;}table#t01 th{background-color:#80e5ff; } table#t01 tr:nth-child(even) {background-color: #f2f2f2;} table#t01 tr:nth-child(odd) { background-color: #DFEDEC;}table#t01 th, td {padding: 5px;}table#t01 th,td {text-align: center;} table#t01 caption {color: #008ae6;font-weight: bold;}</style>");
	 email_report_Pushback_And_PBT_For_CELEBI_Delhi17.append("<table style=\"width:100%\" id=\"t01\"><caption> Flights with Pushback activity with incorrect sequence For Bombardier : </caption><tr>"
			 				+ "<th style=\"width:10%\"><b>Flight PK</b></th> "
			 				+ "<th style=\"width:10%\"><b>Flight Number</b></th> "
			 				+ "<th style=\"width:10%\"><b>DevID</b></th> "
			 				+ "<th style=\"width:15%\"><b>PBT (Start)</b></th> "
			 				+ "<th style=\"width:15%\"><b>Pushback (Start)</b></th> "
			 				+ "<th style=\"width:20%\"><b>PBT (End)</b></th> "
			 				+ "<th style=\"width:20%\"><b>Pushback (End)</b></th> "
			 				+ " </tr>");
	 email_report_Pushback_And_PBT_For_CELEBI_Delhi18.append("<br><br>");
	 email_report_Pushback_And_PBT_For_CELEBI_Delhi18.append("<style>table#t01, th, td {border: 1px solid black;border-collapse: collapse;}table#t01 th{background-color:#80e5ff; } table#t01 tr:nth-child(even) {background-color: #f2f2f2;} table#t01 tr:nth-child(odd) { background-color: #DFEDEC;}table#t01 th, td {padding: 5px;}table#t01 th,td {text-align: center;} table#t01 caption {color: #008ae6;font-weight: bold;}</style>");
	 email_report_Pushback_And_PBT_For_CELEBI_Delhi18.append("<table style=\"width:100%\" id=\"t01\"><caption> Flights with all Pushback activities (Pushback Arrival & Pushback) For Others : </caption><tr>"
							+ "<th style=\"width:20%\"><b>Log ID</b></th> "
							+ "<th style=\"width:20%\"><b>Flight Number Departure</b></th> "
							+ " </tr>");
	 email_report_Pushback_And_PBT_For_CELEBI_Delhi19.append("<br><br>");
	 email_report_Pushback_And_PBT_For_CELEBI_Delhi19.append("<style>table#t01, th, td {border: 1px solid black;border-collapse: collapse;}table#t01 th{background-color:#80e5ff; } table#t01 tr:nth-child(even) {background-color: #f2f2f2;} table#t01 tr:nth-child(odd) { background-color: #DFEDEC;}table#t01 th, td {padding: 5px;}table#t01 th,td {text-align: center;} table#t01 caption {color: #008ae6;font-weight: bold;}</style>");
	 email_report_Pushback_And_PBT_For_CELEBI_Delhi19.append("<table style=\"width:100%\" id=\"t01\"><caption> Flights with NO Pushback Arrival & NO Pushback activities For Others : </caption><tr>"
							+ "<th style=\"width:20%\"><b>LogID</b></th> "
							+ "<th style=\"width:20%\"><b>Flight Number Departure</b></th> "
							+ " </tr>");
	 email_report_Pushback_And_PBT_For_CELEBI_Delhi20.append("<br><br>");
	 email_report_Pushback_And_PBT_For_CELEBI_Delhi20.append("<style>table#t01, th, td {border: 1px solid black;border-collapse: collapse;}table#t01 th{background-color:#80e5ff; } table#t01 tr:nth-child(even) {background-color: #f2f2f2;} table#t01 tr:nth-child(odd) { background-color: #DFEDEC;}table#t01 th, td {padding: 5px;}table#t01 th,td {text-align: center;} table#t01 caption {color: #008ae6;font-weight: bold;}</style>");
	 email_report_Pushback_And_PBT_For_CELEBI_Delhi20.append("<table style=\"width:100%\" id=\"t01\"><caption> Flights with Pushback Arrival but NO Pushback activities For Others : </caption><tr>"
							+ "<th style=\"width:20%\"><b>Log ID</b></th> "
							+ "<th style=\"width:20%\"><b>Flight Number Departure</b></th> "
							+ " </tr>");
	 email_report_Pushback_And_PBT_For_CELEBI_Delhi21.append("<br><br>");
	 email_report_Pushback_And_PBT_For_CELEBI_Delhi21.append("<style>table#t01, th, td {border: 1px solid black;border-collapse: collapse;}table#t01 th{background-color:#80e5ff; } table#t01 tr:nth-child(even) {background-color: #f2f2f2;} table#t01 tr:nth-child(odd) { background-color: #DFEDEC;}table#t01 th, td {padding: 5px;}table#t01 th,td {text-align: center;} table#t01 caption {color: #008ae6;font-weight: bold;}</style>");
	 email_report_Pushback_And_PBT_For_CELEBI_Delhi21.append("<table style=\"width:100%\" id=\"t01\"><caption> Flights with Pushback but NO Pushback Arrival activities For Others : </caption><tr>"
							+ "<th style=\"width:20%\"><b>Log ID</b></th> "
							+ "<th style=\"width:20%\"><b>Flight Number Departure</b></th> "
							+ " </tr>");
		
	 email_report_Pushback_And_PBT_For_CELEBI_Delhi22.append("<br><br>");
	 email_report_Pushback_And_PBT_For_CELEBI_Delhi22.append("<style>table#t01, th, td {border: 1px solid black;border-collapse: collapse;}table#t01 th{background-color:#80e5ff; } table#t01 tr:nth-child(even) {background-color: #f2f2f2;} table#t01 tr:nth-child(odd) { background-color: #DFEDEC;}table#t01 th, td {padding: 5px;}table#t01 th,td {text-align: center;} table#t01 caption {color: #008ae6;font-weight: bold;}</style>");
	 email_report_Pushback_And_PBT_For_CELEBI_Delhi22.append("<table style=\"width:100%\" id=\"t01\"><caption> Flights with Pushback activity with incorrect sequence For Others : </caption><tr>"
			 				+ "<th style=\"width:10%\"><b>Flight PK</b></th> "
			 				+ "<th style=\"width:10%\"><b>Flight Number</b></th> "
			 				+ "<th style=\"width:10%\"><b>DevID</b></th> "
			 				+ "<th style=\"width:15%\"><b>PBT (Start)</b></th> "
			 				+ "<th style=\"width:15%\"><b>Pushback (Start)</b></th> "
			 				+ "<th style=\"width:20%\"><b>PBT (End)</b></th> "
			 				+ "<th style=\"width:20%\"><b>Pushback (End)</b></th> "
			 				+ " </tr>");
		
	 ResultSet result = DBWrapper.Connect("SELECT distinct(devid) FROM `HealthMonitor` where date(logdate) = '"+SQL_Queries.yesterDate()+"' and devid like '%"+devId+"%'",environment);
		while (result.next())
		{
			 String str_devId = result.getString("devid");
			 list_totalActivePushbackDevices.add(str_devId);
			 list_PushbackDevicesNotUtilized.add(str_devId);
		}
		System.out.println("list_totalActivePushbackDevices : "+list_totalActivePushbackDevices.size());
		
		ResultSet result2 = DBWrapper.Connect("SELECT devid FROM `EquipActivityLogs` where date(flogdate) = '"+SQL_Queries.yesterDate()+"' and \r\n"
				+ "devid like '%"+devId+"%' and assigned=1 and operationname in ('pbt', 'pushback') group by devid",environment);
		while (result2.next())
		{				
			String str_devID = result2.getString("devid");
			List_totalPushbackDevicesUtilized.add(str_devID);
		}
		System.out.println(List_totalPushbackDevicesUtilized.size());
		list_PushbackDevicesNotUtilized.removeAll(List_totalPushbackDevicesUtilized);
		
		System.out.println(list_PushbackDevicesNotUtilized);
		
		if(list_PushbackDevicesNotUtilized.size()>0)
		{
			for(int i=0; i<list_PushbackDevicesNotUtilized.size(); i++)
			{
				email_report_Pushback_And_PBT_For_CELEBI_Delhi2.append(" <tr> <td><b style=\"color:red;\">"+list_PushbackDevicesNotUtilized.get(i)+"</b></td></tr>");
			}
		}
		
		ResultSet result3 = DBWrapper.Connect("SELECT * FROM `DailyFlightScheduleDeparture_GMR` where \r\n" +
		"date(coalesce(std, etd))= '"+SQL_Queries.yesterDate()+"' and operationunit= "+operationunit+" "
		+ "and flightNumber regexp ('TK|EY|CX|QR|ET|AK|KC|JL|KE|PS|AC|AF|AZ|BA|SU|IA|SV|UL|T5|FZ|KQ|I5')",environment);
				while (result3.next())
				{	
					String str_LogId = result3.getString("logid");				
					list_totalFlightsScheduledForDeparture.add(str_LogId);
				}
		System.out.println("list_totalFlightsScheduledForDeparture :"+list_totalFlightsScheduledForDeparture);
		System.out.println("list_totalFlightsScheduledForDeparture :"+list_totalFlightsScheduledForDeparture.size());
						
		ResultSet result4 = DBWrapper.Connect("SELECT * FROM `DailyFlightSchedule_Merged` where gmrpk_departure in (SELECT gmrpk FROM `DailyFlightScheduleDeparture_GMR` where\r\n" + 
		"date(coalesce(std, etd))= '"+SQL_Queries.yesterDate()+"' and operationunit= "+operationunit+") and flightnumber_departure regexp ('TK|EY|CX|QR|ET|AK|KC|JL|KE|PS|AC|AF|AZ|BA|SU|IA|SV|UL|T5|FZ|KQ|I5') "
		+ "and (atd is not null or sensor_atd is not null)",environment);
		while (result4.next())
		{	
			String str_logId = result4.getString("logid");			
			list_totalFlightsActuallyDeparted.add(str_logId);
			set_actuallyDepartedUnknown.add(str_logId);	
		}		
				
		ResultSet result5 = DBWrapper.Connect("SELECT DFM.Logid,DFM.flightNumber_departure, DFM.atd,DFM.sensor_ATD,FM.BodyType FROM `DailyFlightSchedule_Merged` as DFM \r\n"
		+ "inner join FlightMaster as FM on DFM.aircraft = FM.Hexcode and date(coalesce(DFM.std, DFM.etd)) = '"+SQL_Queries.yesterDate()+"' and \r\n"
		+ "DFM.operationunit= "+operationunit+" and DFM.flightNumber_departure regexp ('TK|EY|CX|QR|ET|AK|KC|JL|KE|PS|AC|AF|AZ|BA|SU|IA|SV|UL|T5|FZ|KQ|I5') "
		+ "and (atd is not null or sensor_atd is not null) ",environment);
				
				while (result5.next())
				{
					String str_logId = result5.getString("logid");
					String str_flightBodyType= result5.getString("BodyType");
					
					set_actuallyDepartureFoundInFlightMaster.add(str_logId);
					
					if(str_flightBodyType.equalsIgnoreCase("Narrow"))
					{
						list_actuallyDepartedNarrowbody.add(str_logId);
						set_BothPushbackAndPBTNotFormedForNarrowbody.add(str_logId);
					}
					if(str_flightBodyType.equalsIgnoreCase("Wide"))
					{
						list_actuallyDepartedWidebody.add(str_logId);
						set_BothPushbackAndPBTNotFormedForWidebody.add(str_logId);
					}
					if(str_flightBodyType.equalsIgnoreCase("Bombardier") || (str_flightBodyType.equalsIgnoreCase("Turboprop")))
					{
						list_actuallyDepartedBombardier.add(str_logId);
						set_BothPushbackAndPBTNotFormedForBombardier.add(str_logId);
					}
				}		
				
				set_actuallyDepartedUnknown.removeAll(set_actuallyDepartureFoundInFlightMaster);
				
				System.out.println("list_totalFlightsActuallyDeparted" +list_totalFlightsActuallyDeparted.size());
				System.out.println("list_totalFlightsActuallyDeparted" +list_totalFlightsActuallyDeparted);
				
				System.out.println("list_actuallyDepartedNarrowbody" +list_actuallyDepartedNarrowbody);
				System.out.println("list_actuallyDepartedNarrowbody" +list_actuallyDepartedNarrowbody.size());
				
				System.out.println("list_actuallyDepartedWidebody" +list_actuallyDepartedWidebody);
				System.out.println("list_actuallyDepartedWidebody" +list_actuallyDepartedWidebody.size());
				
				System.out.println("list_actuallyDepartedBombardier" +list_actuallyDepartedBombardier);
				System.out.println("list_actuallyDepartedBombardier" +list_actuallyDepartedBombardier.size());
				
				System.out.println("set_actuallyDepartedUnknown after" +set_actuallyDepartedUnknown);
				System.out.println("set_actuallyDepartedUnknown after" +set_actuallyDepartedUnknown.size());
				
				if(list_actuallyDepartedNarrowbody.size()>0)
				{
					for(int i=0; i<list_actuallyDepartedNarrowbody.size(); i++)
					{
					ResultSet result6 = DBWrapper.Connect("SELECT * FROM `EquipActivityLogs` where flight_pk= "+list_actuallyDepartedNarrowbody.get(i)+"", environment);
					while (result6.next())
					{
						String str_flight_pk = result6.getString("flight_pk");
						String str_operationName = result6.getString("operationName");	
						
						if(str_operationName.contains("PushBack"))
						{
							set_PushbackFormedForNarrowbody.add(str_flight_pk);						
							set_PushbackFormedButPBTNotFormedForNarrowbody.add(str_flight_pk);
							set_BothPushbackAndPBTFormedForNarrowbody.add(str_flight_pk);
						}
						if(str_operationName.contains("PBT"))
						{
							set_PBTFormedForNarrowbody.add(str_flight_pk);
							set_PBTFormedButPushbackNotFormedForNarrowbody.add(str_flight_pk);
						}
					}
					
						}
					}
				else
				{
					System.out.println("No data to display");
				}
				System.out.println("PushbackFormedForNarrowbody : " +set_PushbackFormedForNarrowbody);
				System.out.println("PBTFormedForNarrowbody : " +set_PBTFormedForNarrowbody);
				
				set_PushbackFormedButPBTNotFormedForNarrowbody.removeAll(set_PBTFormedForNarrowbody);
				set_PBTFormedButPushbackNotFormedForNarrowbody.removeAll(set_PushbackFormedForNarrowbody);
				set_BothPushbackAndPBTFormedForNarrowbody.retainAll(set_PBTFormedForNarrowbody);
				
				set_totalForNarrowBody.addAll(set_PushbackFormedForNarrowbody);
				set_totalForNarrowBody.addAll(set_PBTFormedForNarrowbody);
				set_totalForNarrowBody.addAll(set_BothPushbackAndPBTFormedForNarrowbody);
				set_BothPushbackAndPBTNotFormedForNarrowbody.removeAll(set_totalForNarrowBody);					
								
				System.out.println("PushbackFormedButPBTNotFormedForNarrowbody :" +set_PushbackFormedButPBTNotFormedForNarrowbody);
				System.out.println("PBTFormedButPushbackNotFormedForNarrowbody :" +set_PBTFormedButPushbackNotFormedForNarrowbody);
				System.out.println("pushback and PBT both formed : " +set_BothPushbackAndPBTFormedForNarrowbody);
				System.out.println("set_total : " +set_totalForNarrowBody);
				System.out.println("BothPushbackAndPBTNotFormedForNarrowbody :" +set_BothPushbackAndPBTNotFormedForNarrowbody);
				
				if(list_actuallyDepartedWidebody.size()>0)
				{
					for(int i=0; i<list_actuallyDepartedWidebody.size(); i++)
					{
					ResultSet result7 = DBWrapper.Connect("SELECT * FROM `EquipActivityLogs` where flight_pk= "+list_actuallyDepartedWidebody.get(i)+"", environment);
					while (result7.next())
					{
						String str_flight_pk = result7.getString("flight_pk");
						String str_operationName = result7.getString("operationName");	
						
						//set_BothPushbackAndPBTNotFormedForWidebody.add(str_flight_pk);
						
						if(str_operationName.contains("PushBack"))
						{
							set_PushbackFormedForWidebody.add(str_flight_pk);						
							set_PushbackFormedButPBTNotFormedForWidebody.add(str_flight_pk);
							set_BothPushbackAndPBTFormedForWidebody.add(str_flight_pk);
						}
						if(str_operationName.contains("PBT"))
						{
							set_PBTFormedForWidebody.add(str_flight_pk);
							set_PBTFormedButPushbackNotFormedForWidebody.add(str_flight_pk);
						}
					}
				   }
				}
				else
				{
					System.out.println("No data to display");
				}
				System.out.println("set_PushbackFormedForWidebody" +set_PushbackFormedForWidebody);
				System.out.println("set_PBTFormedForWidebody" +set_PBTFormedForWidebody);
				
				set_PushbackFormedButPBTNotFormedForWidebody.removeAll(set_PBTFormedForWidebody);
				set_PBTFormedButPushbackNotFormedForWidebody.removeAll(set_PushbackFormedForWidebody);
				set_BothPushbackAndPBTFormedForWidebody.retainAll(set_PBTFormedForWidebody);
				
				set_totalForWideBody.addAll(set_PushbackFormedForWidebody);
				set_totalForWideBody.addAll(set_PBTFormedForWidebody);
				set_totalForWideBody.addAll(set_BothPushbackAndPBTFormedForWidebody);
				
				set_BothPushbackAndPBTNotFormedForWidebody.removeAll(set_totalForWideBody);						
				
				System.out.println("set_PushbackFormedButPBTNotFormedForWidebody" +set_PushbackFormedButPBTNotFormedForWidebody);
				System.out.println("set_PBTFormedButPushbackNotFormedForWidebody" +set_PBTFormedButPushbackNotFormedForWidebody);
				System.out.println("pushback and PBT both formed for wide body: " +set_BothPushbackAndPBTFormedForWidebody);
				System.out.println("set_BothPushbackAndPBTNotFormedForWidebody" +set_BothPushbackAndPBTNotFormedForWidebody);
				
				if(list_actuallyDepartedBombardier.size()>0)
				{
					for(int i=0; i<list_actuallyDepartedBombardier.size(); i++)
					{
					ResultSet result8 = DBWrapper.Connect("SELECT * FROM `EquipActivityLogs` where flight_pk= "+list_actuallyDepartedBombardier.get(i)+"", environment);
					while (result8.next())
					{
						String str_flight_pk = result8.getString("flight_pk");
						String str_operationName = result8.getString("operationName");	
						//set_BothPushbackAndPBTNotFormedForBombardier.add(str_flight_pk);
						
						if(str_operationName.contains("PushBack"))
						{
							set_PushbackFormedForBombardier.add(str_flight_pk);						
							set_PushbackFormedButPBTNotFormedForBombardier.add(str_flight_pk);
							set_BothPushbackAndPBTFormedForBombardier.add(str_flight_pk);
						}
						if(str_operationName.contains("PBT"))
						{
							set_PBTFormedForBombardier.add(str_flight_pk);
							set_PBTFormedButPushbackNotFormedForBombardier.add(str_flight_pk);
						}
					}
				   }
				}
				else
				{
					System.out.println("No data to display");
				}
				System.out.println("set_PushbackFormedForBombardier" +set_PushbackFormedForBombardier);
				System.out.println("set_PBTFormedForBombardier" +set_PBTFormedForBombardier);
				
				set_PushbackFormedButPBTNotFormedForBombardier.removeAll(set_PBTFormedForBombardier);
				set_PBTFormedButPushbackNotFormedForBombardier.removeAll(set_PushbackFormedForBombardier);
				set_BothPushbackAndPBTFormedForBombardier.retainAll(set_PBTFormedForBombardier);
				
				set_totalForBombardier.addAll(set_PushbackFormedForBombardier);
				set_totalForBombardier.addAll(set_PBTFormedForBombardier);
				set_totalForBombardier.addAll(set_BothPushbackAndPBTFormedForWidebody);
				
				set_BothPushbackAndPBTNotFormedForBombardier.removeAll(set_totalForBombardier);					
				
				
				System.out.println("set_PushbackFormedButPBTNotFormedForBombardier " +set_PushbackFormedButPBTNotFormedForBombardier);
				System.out.println("set_PBTFormedButPushbackNotFormedForBombardier " +set_PBTFormedButPushbackNotFormedForBombardier);
				System.out.println("pushback and PBT both formed for Bombardier: " +set_BothPushbackAndPBTFormedForBombardier);
				System.out.println("set_BothPushbackAndPBTNotFormedForBombardier " +set_BothPushbackAndPBTNotFormedForBombardier);
				
				set_BothPushbackAndPBTNotFormedForUnknown.addAll(set_actuallyDepartedUnknown);
				
				if(set_actuallyDepartedUnknown.size()>0)
				{
					for(String actuallyDepartedUnknown: set_actuallyDepartedUnknown)
					{
					ResultSet result9 = DBWrapper.Connect("SELECT * FROM `EquipActivityLogs` where flight_pk= "+actuallyDepartedUnknown+"", environment);
					while (result9.next())
					{
						String str_flight_pk = result9.getString("flight_pk");
						String str_operationName = result9.getString("operationName");	
						
						//set_BothPushbackAndPBTNotFormedForUnknown.add(str_flight_pk);
						
						if(str_operationName.contains("PushBack"))
						{
							set_PushbackFormedForUnknown.add(str_flight_pk);						
							set_PushbackFormedButPBTNotFormedForUnknown.add(str_flight_pk);
							set_BothPushbackAndPBTFormedForUnknown.add(str_flight_pk);
						}
						if(str_operationName.contains("PBT"))
						{
							set_PBTFormedForUnknown.add(str_flight_pk);
							set_PBTFormedButPushbackNotFormedForUnknown.add(str_flight_pk);
						}
					}
				   }
				}
				else
				{
					System.out.println("No data to display");
				}
				System.out.println("set_PushbackFormedForUnknown" +set_PushbackFormedForUnknown);
				System.out.println("set_PBTFormedForUnknown" +set_PBTFormedForUnknown);
				
				set_PushbackFormedButPBTNotFormedForUnknown.removeAll(set_PBTFormedForUnknown);
				set_PBTFormedButPushbackNotFormedForUnknown.removeAll(set_PushbackFormedForUnknown);
				set_BothPushbackAndPBTFormedForUnknown.retainAll(set_PBTFormedForUnknown);
				
				set_totalForUnknown.addAll(set_PushbackFormedForUnknown);	
				set_totalForUnknown.addAll(set_PBTFormedForUnknown);
				set_totalForUnknown.addAll(set_BothPushbackAndPBTFormedForUnknown);
				
				set_BothPushbackAndPBTNotFormedForUnknown.removeAll(set_totalForUnknown);					
				
				
				System.out.println("set_PushbackFormedButPBTNotFormedForUnknown " +set_PushbackFormedButPBTNotFormedForUnknown);
				System.out.println("set_PBTFormedButPushbackNotFormedForUnknown " +set_PBTFormedButPushbackNotFormedForUnknown);
				System.out.println("pushback and PBT both formed for Unknown: " +set_BothPushbackAndPBTFormedForUnknown);
				System.out.println("set_BothPushbackAndPBTNotFormedForUnknown " +set_BothPushbackAndPBTNotFormedForUnknown);
				
				if(set_BothPushbackAndPBTFormedForNarrowbody.size()>0)
				{
					for(String BothPushbackAndPBTFormedForNarrowbody : set_BothPushbackAndPBTFormedForNarrowbody)
					{
					ResultSet result10 = DBWrapper.Connect("SELECT * FROM `DailyFlightSchedule_Merged` where logid = '"+BothPushbackAndPBTFormedForNarrowbody+"' ", environment);
					while(result10.next())
					{
						String str_logID = result10.getString("logid");
						String str_flightNumberDeparture = result10.getString("flightnumber_departure");
					
						email_report_Pushback_And_PBT_For_CELEBI_Delhi3.append(" <tr> <td><b style=\"color:green;\">"+str_logID+"</b></td> "
								+ "<td><b style=\"color:green;\">"+str_flightNumberDeparture+"</b></td></tr>");		
					}
					}
				}
				else
				{
					email_report_Pushback_And_PBT_For_CELEBI_Delhi3.append("<tr><td colspan=\"2\"><b style=\"color:red;\">No values found </b></td></tr>");	
				}
				
				if(set_BothPushbackAndPBTFormedForNarrowbody.size()>0)
				{
					for(String BothPushbackAndPBTFormedForNarrowbody : set_BothPushbackAndPBTFormedForNarrowbody)
					{
						ResultSet result11 = DBWrapper.Connect("SELECT * FROM `EquipActivityLogs` where flight_pk = '"+BothPushbackAndPBTFormedForNarrowbody+"' and operationname in ('pbt', 'pushback') order by devid", environment);
						while(result11.next())
						{
							String str_flightPK = result11.getString("flight_pk");
							String str_flightNumber = result11.getString("flightno");
							String str_deviceId = result11.getString("devid");
							String str_operationname= result11.getString("operationname");
							Timestamp str_flogDate= result11.getTimestamp("flogdate");
							Timestamp str_tlogDate= result11.getTimestamp("tlogdate");
							
							if(str_operationname.equalsIgnoreCase("PBT")) 
							{
								flogdateForPBT = str_flogDate;
								tlogdateForPBT = str_tlogDate;
							}
							if(str_operationname.equalsIgnoreCase("PushBack")) 
							{
								flogdateForPushback = str_flogDate;
								tlogdateForPushback = str_tlogDate;
							}
							if(flogdateForPBT != null && (flogdateForPushback != null) && (tlogdateForPushback != null) && (tlogdateForPBT != null)) 
							{
							if(flogdateForPBT.before(flogdateForPushback) && (flogdateForPushback.equals(tlogdateForPBT)) && (tlogdateForPBT.before(tlogdateForPushback)))
							{
								correctPushbackSequenceNB = correctPushbackSequenceNB + 1;
								
								/*email_report_Pushback_And_PBT_For_CELEBI_Delhi7.append(" <tr> "
										+ "<td><b style=\"color:green;\">"+str_flightPK+"</b></td> "
										+ "<td><b style=\"color:green;\">"+str_flightNumber+"</b></td>"
								 		+ " <td> <b style=\"color:green;\">"+str_deviceId+"</b></td>"
								 		+ " <td> <b style=\"color:green;\">"+flogdateForPBT+"</b></td>"
								 		+ " <td><b style=\"color:green;\">"+flogdateForPushback+"</b></td>"
								 		+ " <td><b style=\"color:green;\">"+tlogdateForPBT+"</b></td>"
								 		+ " <td><b style=\"color:green;\">"+tlogdateForPushback+"</b></td>"
								 		+ "</tr>");*/
							}
							else
							{
								incorrectPushbackSequenceNB = incorrectPushbackSequenceNB + 1;
								email_report_Pushback_And_PBT_For_CELEBI_Delhi7.append(" <tr> "
										+ "<td><b style=\"color:red;\">"+str_flightPK+"</b></td> "
										+ "<td><b style=\"color:red;\">"+str_flightNumber+"</b></td>"
								 		+ " <td> <b style=\"color:red;\">"+str_deviceId+"</b></td>"
								 		+ " <td> <b style=\"color:red;\">"+flogdateForPBT+"</b></td>"
								 		+ " <td><b style=\"color:red;\">"+flogdateForPushback+"</b></td>"
								 		+ " <td><b style=\"color:red;\">"+tlogdateForPBT+"</b></td>"
								 		+ " <td><b style=\"color:red;\">"+tlogdateForPushback+"</b></td>"
								 		+ "</tr>");	
							}
							}
						}
						flogdateForPBT = null;
						tlogdateForPBT = null;
						flogdateForPushback = null;
						tlogdateForPushback = null;
					}
				}
				else
				{
					email_report_Pushback_And_PBT_For_CELEBI_Delhi7.append("<tr><td colspan=\"7\"><b style=\"color:red;\">No values found </b></td></tr>");	
				}
				
				if(set_BothPushbackAndPBTNotFormedForNarrowbody.size()>0)
				{
					for(String BothPushbackAndPBTNotFormedForNarrowbody : set_BothPushbackAndPBTNotFormedForNarrowbody)
					{
					ResultSet result12 = DBWrapper.Connect("SELECT * FROM `DailyFlightSchedule_Merged` where logid = '"+BothPushbackAndPBTNotFormedForNarrowbody+"'", environment);
					while(result12.next())
					{
						String str_LogID = result12.getString("logid");
						String str_flightNumberDeparture = result12.getString("flightnumber_departure");
					
						email_report_Pushback_And_PBT_For_CELEBI_Delhi4.append(" <tr> <td><b style=\"color:red;\">"+str_LogID+"</b></td> "
								+ "<td><b style=\"color:red;\">"+str_flightNumberDeparture+"</b></td></tr>");		
					}
					}
				}
				else
				{
					email_report_Pushback_And_PBT_For_CELEBI_Delhi4.append("<tr><td colspan=\"2\"><b style=\"color:red;\">No values found </b></td></tr>");	
				}
				if(set_PBTFormedButPushbackNotFormedForNarrowbody.size()>0)
				{
					for(String PBTFormedButPushbackNotFormedForNarrowbody : set_PBTFormedButPushbackNotFormedForNarrowbody)
					{
					ResultSet result13 = DBWrapper.Connect("SELECT * FROM `DailyFlightSchedule_Merged` where logid = '"+PBTFormedButPushbackNotFormedForNarrowbody+"' ", environment);
					while(result13.next())
					{
						String str_LogId = result13.getString("logid");
						String str_flightNumberDeparture = result13.getString("flightnumber_departure");
					
						email_report_Pushback_And_PBT_For_CELEBI_Delhi5.append(" <tr> <td><b style=\"color:red;\">"+str_LogId+"</b></td> "
								+ "<td><b style=\"color:red;\">"+str_flightNumberDeparture+"</b></td></tr>");		
					}
					}
				}
				else
				{
					email_report_Pushback_And_PBT_For_CELEBI_Delhi5.append("<tr><td colspan=\"2\"><b style=\"color:red;\">No values found </b></td></tr>");	
				}
				
				if(set_PushbackFormedButPBTNotFormedForNarrowbody.size()>0)
				{
					for(String PushbackFormedButPBTNotFormedForNarrowbody : set_PushbackFormedButPBTNotFormedForNarrowbody)
					{
					ResultSet result14 = DBWrapper.Connect("SELECT * FROM `DailyFlightSchedule_Merged` where logid = '"+PushbackFormedButPBTNotFormedForNarrowbody+"'", environment);
					while(result14.next())
					{
						String str_logId = result14.getString("logid");
						String str_flightNumberDeparture = result14.getString("flightnumber_departure");
					
						email_report_Pushback_And_PBT_For_CELEBI_Delhi6.append(" <tr> <td><b style=\"color:red;\">"+str_logId+"</b></td> "
								+ "<td><b style=\"color:red;\">"+str_flightNumberDeparture+"</b></td></tr>");		
					}
					}
				}
				else
				{
					email_report_Pushback_And_PBT_For_CELEBI_Delhi6.append("<tr><td colspan=\"2\"><b style=\"color:red;\">No values found </b></td></tr>");	
				}
				
				
				email_report_Pushback_And_PBT_For_CELEBI_Delhi2.append("</table>");
				email_report_Pushback_And_PBT_For_CELEBI_Delhi3.append("</table>");
				email_report_Pushback_And_PBT_For_CELEBI_Delhi4.append("</table>");
				email_report_Pushback_And_PBT_For_CELEBI_Delhi5.append("</table>");
				email_report_Pushback_And_PBT_For_CELEBI_Delhi6.append("</table>");
				email_report_Pushback_And_PBT_For_CELEBI_Delhi7.append("</table>");
				
				if(set_BothPushbackAndPBTFormedForWidebody.size()>0)
				{
					for(String BothPushbackAndPBTFormedForWidebody : set_BothPushbackAndPBTFormedForWidebody)
					{
					ResultSet result15 = DBWrapper.Connect("SELECT * FROM `DailyFlightSchedule_Merged` where logid = '"+BothPushbackAndPBTFormedForWidebody+"' ", environment);
					while(result15.next())
					{
						String str_logID = result15.getString("logid");
						String str_flightNumberDeparture = result15.getString("flightnumber_departure");
					
						email_report_Pushback_And_PBT_For_CELEBI_Delhi8.append(" <tr> <td><b style=\"color:green;\">"+str_logID+"</b></td> "
								+ "<td><b style=\"color:green;\">"+str_flightNumberDeparture+"</b></td></tr>");		
					}
					}
				}
				else
				{
					email_report_Pushback_And_PBT_For_CELEBI_Delhi8.append("<tr><td colspan=\"2\"><b style=\"color:red;\">No values found </b></td></tr>");	
				}
				
				if(set_BothPushbackAndPBTFormedForWidebody.size()>0)
				{
					for(String BothPushbackAndPBTFormedForWidebody : set_BothPushbackAndPBTFormedForWidebody)
					{
						ResultSet result16 = DBWrapper.Connect("SELECT * FROM `EquipActivityLogs` where flight_pk = '"+BothPushbackAndPBTFormedForWidebody+"' and operationname in ('pbt', 'pushback') order by devid", environment);
						while(result16.next())
						{
							String str_flightPK = result16.getString("flight_pk");
							String str_flightNumber = result16.getString("flightno");
							String str_deviceId = result16.getString("devid");
							String str_operationname= result16.getString("operationname");
							Timestamp str_flogDate= result16.getTimestamp("flogdate");
							Timestamp str_tlogDate= result16.getTimestamp("tlogdate");
							
							if(str_operationname.equalsIgnoreCase("PBT")) 
							{
								flogdateForPBT = str_flogDate;
								tlogdateForPBT = str_tlogDate;
							}
							if(str_operationname.equalsIgnoreCase("PushBack")) 
							{
								flogdateForPushback = str_flogDate;
								tlogdateForPushback = str_tlogDate;
							}
							if(flogdateForPBT != null && (flogdateForPushback != null) && (tlogdateForPushback != null) && (tlogdateForPBT != null)) 
							{
							if(flogdateForPBT.before(flogdateForPushback) && (flogdateForPushback.equals(tlogdateForPBT)) && (tlogdateForPBT.before(tlogdateForPushback)))
							{
								correctPushbackSequenceWB = correctPushbackSequenceWB + 1;
								
								/*email_report_Pushback_And_PBT_For_CELEBI_Delhi12.append(" <tr> "
										+ "<td><b style=\"color:green;\">"+str_flightPK+"</b></td> "
										+ "<td><b style=\"color:green;\">"+str_flightNumber+"</b></td>"
								 		+ " <td> <b style=\"color:green;\">"+str_deviceId+"</b></td>"
								 		+ " <td> <b style=\"color:green;\">"+flogdateForPBT+"</b></td>"
								 		+ " <td><b style=\"color:green;\">"+flogdateForPushback+"</b></td>"
								 		+ " <td><b style=\"color:green;\">"+tlogdateForPBT+"</b></td>"
								 		+ " <td><b style=\"color:green;\">"+tlogdateForPushback+"</b></td>"
								 		+ "</tr>");*/
							}
							else
							{
								incorrectPushbackSequenceWB = incorrectPushbackSequenceWB + 1;
								email_report_Pushback_And_PBT_For_CELEBI_Delhi12.append(" <tr> "
										+ "<td><b style=\"color:red;\">"+str_flightPK+"</b></td> "
										+ "<td><b style=\"color:red;\">"+str_flightNumber+"</b></td>"
								 		+ " <td> <b style=\"color:red;\">"+str_deviceId+"</b></td>"
								 		+ " <td> <b style=\"color:red;\">"+flogdateForPBT+"</b></td>"
								 		+ " <td><b style=\"color:red;\">"+flogdateForPushback+"</b></td>"
								 		+ " <td><b style=\"color:red;\">"+tlogdateForPBT+"</b></td>"
								 		+ " <td><b style=\"color:red;\">"+tlogdateForPushback+"</b></td>"
								 		+ "</tr>");	
							}
							}
						}
						flogdateForPBT = null;
						tlogdateForPBT = null;
						flogdateForPushback = null;
						tlogdateForPushback = null;
					}
				}
				else
				{
					email_report_Pushback_And_PBT_For_CELEBI_Delhi12.append("<tr><td colspan=\"7\"><b style=\"color:red;\">No values found </b></td></tr>");	
				}
				
				if(set_BothPushbackAndPBTNotFormedForWidebody.size()>0)
				{
					for(String BothPushbackAndPBTNotFormedForWidebody : set_BothPushbackAndPBTNotFormedForWidebody)
					{
					ResultSet result17 = DBWrapper.Connect("SELECT * FROM `DailyFlightSchedule_Merged` where logid = '"+BothPushbackAndPBTNotFormedForWidebody+"'", environment);
					while(result17.next())
					{
						String str_LogID = result17.getString("logid");
						String str_flightNumberDeparture = result17.getString("flightnumber_departure");
					
						email_report_Pushback_And_PBT_For_CELEBI_Delhi9.append(" <tr> <td><b style=\"color:red;\">"+str_LogID+"</b></td> "
								+ "<td><b style=\"color:red;\">"+str_flightNumberDeparture+"</b></td></tr>");		
					}
					}
				}
				else
				{
					email_report_Pushback_And_PBT_For_CELEBI_Delhi9.append("<tr><td colspan=\"2\"><b style=\"color:red;\">No values found </b></td></tr>");	
				}
				
				if(set_PBTFormedButPushbackNotFormedForWidebody.size()>0)
				{
					for(String PBTFormedButPushbackNotFormedForWidebody : set_PBTFormedButPushbackNotFormedForWidebody)
					{
					ResultSet result18 = DBWrapper.Connect("SELECT * FROM `DailyFlightSchedule_Merged` where logid = '"+PBTFormedButPushbackNotFormedForWidebody+"' ", environment);
					while(result18.next())
					{
						String str_LogId = result18.getString("logid");
						String str_flightNumberDeparture = result18.getString("flightnumber_departure");
					
						email_report_Pushback_And_PBT_For_CELEBI_Delhi10.append(" <tr> <td><b style=\"color:red;\">"+str_LogId+"</b></td> "
								+ "<td><b style=\"color:red;\">"+str_flightNumberDeparture+"</b></td></tr>");		
					}
					}
				}
				else
				{
					email_report_Pushback_And_PBT_For_CELEBI_Delhi10.append("<tr><td colspan=\"2\"><b style=\"color:red;\">No values found </b></td></tr>");	
				}
				
				if(set_PushbackFormedButPBTNotFormedForWidebody.size()>0)
				{
					for(String PushbackFormedButPBTNotFormedForWidebody : set_PushbackFormedButPBTNotFormedForWidebody)
					{
					ResultSet result19 = DBWrapper.Connect("SELECT * FROM `DailyFlightSchedule_Merged` where logid = '"+PushbackFormedButPBTNotFormedForWidebody+"'", environment);
					while(result19.next())
					{
						String str_logId = result19.getString("logid");
						String str_flightNumberDeparture = result19.getString("flightnumber_departure");
					
						email_report_Pushback_And_PBT_For_CELEBI_Delhi11.append(" <tr> <td><b style=\"color:red;\">"+str_logId+"</b></td> "
								+ "<td><b style=\"color:red;\">"+str_flightNumberDeparture+"</b></td></tr>");		
					}
					}
				}
				else
				{
					email_report_Pushback_And_PBT_For_CELEBI_Delhi11.append("<tr><td colspan=\"2\"><b style=\"color:red;\">No values found </b></td></tr>");	
				}
				
				email_report_Pushback_And_PBT_For_CELEBI_Delhi8.append("</table>");
				email_report_Pushback_And_PBT_For_CELEBI_Delhi9.append("</table>");
				email_report_Pushback_And_PBT_For_CELEBI_Delhi10.append("</table>");
				email_report_Pushback_And_PBT_For_CELEBI_Delhi11.append("</table>");
				email_report_Pushback_And_PBT_For_CELEBI_Delhi12.append("</table>");
				
				if(set_BothPushbackAndPBTFormedForBombardier.size()>0)
				{
					for(String BothPushbackAndPBTFormedForBombardier : set_BothPushbackAndPBTFormedForBombardier)
					{
					ResultSet result20 = DBWrapper.Connect("SELECT * FROM `DailyFlightSchedule_Merged` where logid = '"+BothPushbackAndPBTFormedForBombardier+"' ", environment);
					while(result20.next())
					{
						String str_logID = result20.getString("logid");
						String str_flightNumberDeparture = result20.getString("flightnumber_departure");
					
						email_report_Pushback_And_PBT_For_CELEBI_Delhi13.append(" <tr> <td><b style=\"color:green;\">"+str_logID+"</b></td> "
								+ "<td><b style=\"color:green;\">"+str_flightNumberDeparture+"</b></td></tr>");		
					}
					}
				}
				else
				{
					email_report_Pushback_And_PBT_For_CELEBI_Delhi13.append("<tr><td colspan=\"2\"><b style=\"color:red;\">No values found </b></td></tr>");	
				}
				
				if(set_BothPushbackAndPBTFormedForBombardier.size()>0)
				{
					for(String BothPushbackAndPBTFormedForBombardier : set_BothPushbackAndPBTFormedForBombardier)
					{
						ResultSet result21 = DBWrapper.Connect("SELECT * FROM `EquipActivityLogs` where flight_pk = '"+BothPushbackAndPBTFormedForBombardier+"' and operationname in ('pbt', 'pushback') order by devid", environment);
						while(result21.next())
						{
							String str_flightPK = result21.getString("flight_pk");
							String str_flightNumber = result21.getString("flightno");
							String str_deviceId = result21.getString("devid");
							String str_operationname= result21.getString("operationname");
							Timestamp str_flogDate= result21.getTimestamp("flogdate");
							Timestamp str_tlogDate= result21.getTimestamp("tlogdate");
							
							if(str_operationname.equalsIgnoreCase("PBT")) 
							{
								flogdateForPBT = str_flogDate;
								tlogdateForPBT = str_tlogDate;
							}
							if(str_operationname.equalsIgnoreCase("PushBack")) 
							{
								flogdateForPushback = str_flogDate;
								tlogdateForPushback = str_tlogDate;
							}
							if(flogdateForPBT != null && (flogdateForPushback != null) && (tlogdateForPushback != null) && (tlogdateForPBT != null)) 
							{
							if(flogdateForPBT.before(flogdateForPushback) && (flogdateForPushback.equals(tlogdateForPBT)) && (tlogdateForPBT.before(tlogdateForPushback)))
							{
								correctPushbackSequenceBombardier = correctPushbackSequenceBombardier + 1;
								
								/*email_report_Pushback_And_PBT_For_CELEBI_Delhi17.append(" <tr> "
										+ "<td><b style=\"color:green;\">"+str_flightPK+"</b></td> "
										+ "<td><b style=\"color:green;\">"+str_flightNumber+"</b></td>"
								 		+ " <td> <b style=\"color:green;\">"+str_deviceId+"</b></td>"
								 		+ " <td> <b style=\"color:green;\">"+flogdateForPBT+"</b></td>"
								 		+ " <td><b style=\"color:green;\">"+flogdateForPushback+"</b></td>"
								 		+ " <td><b style=\"color:green;\">"+tlogdateForPBT+"</b></td>"
								 		+ " <td><b style=\"color:green;\">"+tlogdateForPushback+"</b></td>"
								 		+ "</tr>");*/
							}
							else
							{
								incorrectPushbackSequenceBombardier = incorrectPushbackSequenceBombardier + 1;
								email_report_Pushback_And_PBT_For_CELEBI_Delhi17.append(" <tr> "
										+ "<td><b style=\"color:red;\">"+str_flightPK+"</b></td> "
										+ "<td><b style=\"color:red;\">"+str_flightNumber+"</b></td>"
								 		+ " <td> <b style=\"color:red;\">"+str_deviceId+"</b></td>"
								 		+ " <td> <b style=\"color:red;\">"+flogdateForPBT+"</b></td>"
								 		+ " <td><b style=\"color:red;\">"+flogdateForPushback+"</b></td>"
								 		+ " <td><b style=\"color:red;\">"+tlogdateForPBT+"</b></td>"
								 		+ " <td><b style=\"color:red;\">"+tlogdateForPushback+"</b></td>"
								 		+ "</tr>");	
							}
							}
						}
						flogdateForPBT = null;
						tlogdateForPBT = null;
						flogdateForPushback = null;
						tlogdateForPushback = null;
					}
				}
				else
				{
					email_report_Pushback_And_PBT_For_CELEBI_Delhi17.append("<tr><td colspan=\"7\"><b style=\"color:red;\">No values found </b></td></tr>");	
				}
				
				if(set_BothPushbackAndPBTNotFormedForBombardier.size()>0)
				{
					for(String BothPushbackAndPBTNotFormedForBombardier : set_BothPushbackAndPBTNotFormedForBombardier)
					{
					ResultSet result22 = DBWrapper.Connect("SELECT * FROM `DailyFlightSchedule_Merged` where logid = '"+BothPushbackAndPBTNotFormedForBombardier+"'", environment);
					while(result22.next())
					{
						String str_LogID = result22.getString("logid");
						String str_flightNumberDeparture = result22.getString("flightnumber_departure");
					
						email_report_Pushback_And_PBT_For_CELEBI_Delhi14.append(" <tr> <td><b style=\"color:red;\">"+str_LogID+"</b></td> "
								+ "<td><b style=\"color:red;\">"+str_flightNumberDeparture+"</b></td></tr>");		
					}
					}
				}
				else
				{
					email_report_Pushback_And_PBT_For_CELEBI_Delhi14.append("<tr><td colspan=\"2\"><b style=\"color:red;\">No values found </b></td></tr>");	
				}
				
				if(set_PBTFormedButPushbackNotFormedForBombardier.size()>0)
				{
					for(String PBTFormedButPushbackNotFormedForBombardier : set_PBTFormedButPushbackNotFormedForBombardier)
					{
					ResultSet result23 = DBWrapper.Connect("SELECT * FROM `DailyFlightSchedule_Merged` where logid = '"+PBTFormedButPushbackNotFormedForBombardier+"' ", environment);
					while(result23.next())
					{
						String str_LogId = result23.getString("logid");
						String str_flightNumberDeparture = result23.getString("flightnumber_departure");
					
						email_report_Pushback_And_PBT_For_CELEBI_Delhi15.append(" <tr> <td><b style=\"color:red;\">"+str_LogId+"</b></td> "
								+ "<td><b style=\"color:red;\">"+str_flightNumberDeparture+"</b></td></tr>");		
					}
					}
				}
				else
				{
					email_report_Pushback_And_PBT_For_CELEBI_Delhi15.append("<tr><td colspan=\"2\"><b style=\"color:red;\">No values found </b></td></tr>");	
				}
				
				if(set_PushbackFormedButPBTNotFormedForBombardier.size()>0)
				{
					for(String PushbackFormedButPBTNotFormedForBombardier : set_PushbackFormedButPBTNotFormedForBombardier)
					{
					ResultSet result24 = DBWrapper.Connect("SELECT * FROM `DailyFlightSchedule_Merged` where logid = '"+PushbackFormedButPBTNotFormedForBombardier+"'", environment);
					while(result24.next())
					{
						String str_logId = result24.getString("logid");
						String str_flightNumberDeparture = result24.getString("flightnumber_departure");
					
						email_report_Pushback_And_PBT_For_CELEBI_Delhi16.append(" <tr> <td><b style=\"color:red;\">"+str_logId+"</b></td> "
								+ "<td><b style=\"color:red;\">"+str_flightNumberDeparture+"</b></td></tr>");		
					}
					}
				}
				else
				{
					email_report_Pushback_And_PBT_For_CELEBI_Delhi16.append("<tr><td colspan=\"2\"><b style=\"color:red;\">No values found </b></td></tr>");	
				}
				
				email_report_Pushback_And_PBT_For_CELEBI_Delhi13.append("</table>");
				email_report_Pushback_And_PBT_For_CELEBI_Delhi14.append("</table>");
				email_report_Pushback_And_PBT_For_CELEBI_Delhi15.append("</table>");
				email_report_Pushback_And_PBT_For_CELEBI_Delhi16.append("</table>");
				email_report_Pushback_And_PBT_For_CELEBI_Delhi17.append("</table>");
				
				if(set_BothPushbackAndPBTFormedForUnknown.size()>0)
				{
					for(String BothPushbackAndPBTFormedForUnknown : set_BothPushbackAndPBTFormedForUnknown)
					{
					ResultSet result25 = DBWrapper.Connect("SELECT * FROM `DailyFlightSchedule_Merged` where logid = '"+BothPushbackAndPBTFormedForUnknown+"' ", environment);
					while(result25.next())
					{
						String str_logID = result25.getString("logid");
						String str_flightNumberDeparture = result25.getString("flightnumber_departure");
					
						email_report_Pushback_And_PBT_For_CELEBI_Delhi18.append(" <tr> <td><b style=\"color:green;\">"+str_logID+"</b></td> "
								+ "<td><b style=\"color:green;\">"+str_flightNumberDeparture+"</b></td></tr>");		
					}
					}
				}
				else
				{
					email_report_Pushback_And_PBT_For_CELEBI_Delhi18.append("<tr><td colspan=\"2\"><b style=\"color:red;\">No values found </b></td></tr>");	
				}
				
				if(set_BothPushbackAndPBTFormedForUnknown.size()>0)
				{
					for(String BothPushbackAndPBTFormedForUnknown : set_BothPushbackAndPBTFormedForUnknown)
					{
						ResultSet result26 = DBWrapper.Connect("SELECT * FROM `EquipActivityLogs` where flight_pk = '"+BothPushbackAndPBTFormedForUnknown+"' and operationname in ('pbt', 'pushback') order by devid", environment);
						while(result26.next())
						{
							String str_flightPK = result26.getString("flight_pk");
							String str_flightNumber = result26.getString("flightno");
							String str_deviceId = result26.getString("devid");
							String str_operationname= result26.getString("operationname");
							Timestamp str_flogDate= result26.getTimestamp("flogdate");
							Timestamp str_tlogDate= result26.getTimestamp("tlogdate");
							
							if(str_operationname.equalsIgnoreCase("PBT")) 
							{
								flogdateForPBT = str_flogDate;
								tlogdateForPBT = str_tlogDate;
							}
							if(str_operationname.equalsIgnoreCase("PushBack")) 
							{
								flogdateForPushback = str_flogDate;
								tlogdateForPushback = str_tlogDate;
							}
							if(flogdateForPBT != null && (flogdateForPushback != null) && (tlogdateForPushback != null) && (tlogdateForPBT != null)) 
							{
							if(flogdateForPBT.before(flogdateForPushback) && (flogdateForPushback.equals(tlogdateForPBT)) && (tlogdateForPBT.before(tlogdateForPushback)))
							{
								correctPushbackSequenceOthers = correctPushbackSequenceOthers + 1;
								
								/*email_report_Pushback_And_PBT_For_CELEBI_Delhi22.append(" <tr> "
										+ "<td><b style=\"color:green;\">"+str_flightPK+"</b></td> "
										+ "<td><b style=\"color:green;\">"+str_flightNumber+"</b></td>"
								 		+ " <td> <b style=\"color:green;\">"+str_deviceId+"</b></td>"
								 		+ " <td> <b style=\"color:green;\">"+flogdateForPBT+"</b></td>"
								 		+ " <td><b style=\"color:green;\">"+flogdateForPushback+"</b></td>"
								 		+ " <td><b style=\"color:green;\">"+tlogdateForPBT+"</b></td>"
								 		+ " <td><b style=\"color:green;\">"+tlogdateForPushback+"</b></td>"
								 		+ "</tr>");*/
							}
							else
							{
								incorrectPushbackSequenceOthers = incorrectPushbackSequenceOthers + 1;
								email_report_Pushback_And_PBT_For_CELEBI_Delhi22.append(" <tr> "
										+ "<td><b style=\"color:red;\">"+str_flightPK+"</b></td> "
										+ "<td><b style=\"color:red;\">"+str_flightNumber+"</b></td>"
								 		+ " <td> <b style=\"color:red;\">"+str_deviceId+"</b></td>"
								 		+ " <td> <b style=\"color:red;\">"+flogdateForPBT+"</b></td>"
								 		+ " <td><b style=\"color:red;\">"+flogdateForPushback+"</b></td>"
								 		+ " <td><b style=\"color:red;\">"+tlogdateForPBT+"</b></td>"
								 		+ " <td><b style=\"color:red;\">"+tlogdateForPushback+"</b></td>"
								 		+ "</tr>");	
							}
							}
						}
						flogdateForPBT = null;
						tlogdateForPBT = null;
						flogdateForPushback = null;
						tlogdateForPushback = null;
					}
				}
				else
				{
					email_report_Pushback_And_PBT_For_CELEBI_Delhi22.append("<tr><td colspan=\"7\"><b style=\"color:red;\">No values found </b></td></tr>");	
				}
				
				if(set_BothPushbackAndPBTNotFormedForUnknown.size()>0)
				{
					for(String BothPushbackAndPBTNotFormedForUnknown : set_BothPushbackAndPBTNotFormedForUnknown)
					{
					ResultSet result27 = DBWrapper.Connect("SELECT * FROM `DailyFlightSchedule_Merged` where logid = '"+BothPushbackAndPBTNotFormedForUnknown+"'", environment);
					while(result27.next())
					{
						String str_LogID = result27.getString("logid");
						String str_flightNumberDeparture = result27.getString("flightnumber_departure");
					
						email_report_Pushback_And_PBT_For_CELEBI_Delhi19.append(" <tr> <td><b style=\"color:red;\">"+str_LogID+"</b></td> "
								+ "<td><b style=\"color:red;\">"+str_flightNumberDeparture+"</b></td></tr>");		
					}
					}
				}
				else
				{
					email_report_Pushback_And_PBT_For_CELEBI_Delhi19.append("<tr><td colspan=\"2\"><b style=\"color:red;\">No values found </b></td></tr>");	
				}
				
				if(set_PBTFormedButPushbackNotFormedForUnknown.size()>0)
				{
					for(String PBTFormedButPushbackNotFormedForUnknown : set_PBTFormedButPushbackNotFormedForUnknown)
					{
					ResultSet result28 = DBWrapper.Connect("SELECT * FROM `DailyFlightSchedule_Merged` where logid = '"+PBTFormedButPushbackNotFormedForUnknown+"' ", environment);
					while(result28.next())
					{
						String str_LogId = result28.getString("logid");
						String str_flightNumberDeparture = result28.getString("flightnumber_departure");
					
						email_report_Pushback_And_PBT_For_CELEBI_Delhi20.append(" <tr> <td><b style=\"color:red;\">"+str_LogId+"</b></td> "
								+ "<td><b style=\"color:red;\">"+str_flightNumberDeparture+"</b></td></tr>");		
					}
					}
				}
				else
				{
					email_report_Pushback_And_PBT_For_CELEBI_Delhi20.append("<tr><td colspan=\"2\"><b style=\"color:red;\">No values found </b></td></tr>");	
				}
				
				if(set_PushbackFormedButPBTNotFormedForUnknown.size()>0)
				{
					for(String PushbackFormedButPBTNotFormedForUnknown : set_PushbackFormedButPBTNotFormedForUnknown)
					{
					ResultSet result29 = DBWrapper.Connect("SELECT * FROM `DailyFlightSchedule_Merged` where logid = '"+PushbackFormedButPBTNotFormedForUnknown+"'", environment);
					while(result29.next())
					{
						String str_logId = result29.getString("logid");
						String str_flightNumberDeparture = result29.getString("flightnumber_departure");
					
						email_report_Pushback_And_PBT_For_CELEBI_Delhi21.append(" <tr> <td><b style=\"color:red;\">"+str_logId+"</b></td> "
								+ "<td><b style=\"color:red;\">"+str_flightNumberDeparture+"</b></td></tr>");		
					}
					}
				}
				else
				{
					email_report_Pushback_And_PBT_For_CELEBI_Delhi21.append("<tr><td colspan=\"2\"><b style=\"color:red;\">No values found </b></td></tr>");	
				}
				
				email_report_Pushback_And_PBT_For_CELEBI_Delhi18.append("</table>");
				email_report_Pushback_And_PBT_For_CELEBI_Delhi19.append("</table>");
				email_report_Pushback_And_PBT_For_CELEBI_Delhi20.append("</table>");
				email_report_Pushback_And_PBT_For_CELEBI_Delhi21.append("</table>");
				email_report_Pushback_And_PBT_For_CELEBI_Delhi22.append("</table>");
				
				email_report_Pushback_And_PBT_For_CELEBI_Delhi1.append("<style>table#t01, th, td {border: 1px solid black;border-collapse: collapse;}table#t01 th{background-color:#80e5ff; } table#t01 tr:nth-child(even) {background-color: #f2f2f2;} table#t01 tr:nth-child(odd) { background-color: #DFEDEC;}table#t01 th, td {padding: 5px;}table#t01 th,td {text-align: center;} table#t01 caption {color: #008ae6;font-weight: bold;}</style>");
				email_report_Pushback_And_PBT_For_CELEBI_Delhi1.append("<h4 align=\"center\" style=\"color:#008ae6;\">Airport Name : Delhi </h4>");
				email_report_Pushback_And_PBT_For_CELEBI_Delhi1.append("<h4 align=\"center\" style=\"color:#008ae6;\">Executed For : CELEBI </h4><h5 align=\"center\" style=\"color:#008ae6;\" >Execution Time: "+SQL_Queries.todayDayDateTime()+" </h5>");
				email_report_Pushback_And_PBT_For_CELEBI_Delhi1.append("<h5 align=\"center\" style=\"color:#008ae6;\" >Environment: "+environment+" </h5>");
				email_report_Pushback_And_PBT_For_CELEBI_Delhi1.append("<p align=\"left\"><b> Data belongs to - "+SQL_Queries.yesterDate()+" </b></p>");
				email_report_Pushback_And_PBT_For_CELEBI_Delhi1.append("<p align=\"left\"><b> Total Flights Scheduled for Departure : "+list_totalFlightsScheduledForDeparture.size()+"</b></p>");

				email_report_Pushback_And_PBT_For_CELEBI_Delhi1.append("<table style=\"width:100%\" id=\"t01\"><tr>"
						+ "<th style=\"width:35%\"><b>Scenario</b></th>"
						+ "<th style=\"width:15%\"><b>Narrow</b></th> "
						+ "<th style=\"width:15%\"><b>Wide</b></th>"
				 		+ "<th style=\"width:25%\"><b>Bombardier or TurboProp</b></th>"
				 		+ "<th style=\"width:10%\"><b>Others</b></th>"
				 		+ " </tr>");
				
				email_report_Pushback_And_PBT_For_CELEBI_Delhi1.append(" <tr> "
						+ "<td><b> Total flights departed (Actual) </b></td> "
						+ "<td><b style=\"color:green;\">"+list_actuallyDepartedNarrowbody.size()+"</b></td>"
				 		+ " <td><b style=\"color:green;\">"+list_actuallyDepartedWidebody.size()+"</b></td>"
				 		+ " <td><b style=\"color:green;\">"+list_actuallyDepartedBombardier.size()+"</b></td> "
				 		+ "<td><b style=\"color:green;\">"+set_actuallyDepartedUnknown.size()+"</b></td></tr>");	
				
				email_report_Pushback_And_PBT_For_CELEBI_Delhi1.append(" <tr> "
						+ "<td><b> Flights with all Pushback activities (Pushback Arrival & Pushback) </b></td> "
						+ "<td><b style=\"color:green;\">"+set_BothPushbackAndPBTFormedForNarrowbody.size()+"</b></td>"
				 		+ " <td><b style=\"color:green;\">"+set_BothPushbackAndPBTFormedForWidebody.size()+"</b></td>"
				 		+ " <td><b style=\"color:green;\">"+set_BothPushbackAndPBTFormedForBombardier.size()+"</b></td> "
				 		+ "<td><b style=\"color:green;\">"+set_BothPushbackAndPBTFormedForUnknown.size()+"</b></td></tr>");
				
				email_report_Pushback_And_PBT_For_CELEBI_Delhi1.append(" <tr> "
						+ "<td><b> Flights with NO Pushback Arrival & NO Pushback activities </b></td> "
						+ "<td><b style=\"color:green;\">"+set_BothPushbackAndPBTNotFormedForNarrowbody.size()+"</b></td>"
				 		+ " <td> <b style=\"color:green;\">"+set_BothPushbackAndPBTNotFormedForWidebody.size()+"</b></td>"
				 		+ " <td><b style=\"color:green;\">"+set_BothPushbackAndPBTNotFormedForBombardier.size()+"</b></td> "
				 		+ "<td><b style=\"color:green;\">"+set_BothPushbackAndPBTNotFormedForUnknown.size()+"</b></td></tr>");
				
				email_report_Pushback_And_PBT_For_CELEBI_Delhi1.append(" <tr> "
						+ "<td><b> Flights with Pushback Arrival but NO Pushback activities </b></td> "
						+ "<td><b style=\"color:green;\">"+set_PBTFormedButPushbackNotFormedForNarrowbody.size()+"</b></td>"
				 		+ " <td> <b style=\"color:green;\">"+set_PBTFormedButPushbackNotFormedForWidebody.size()+"</b></td>"
				 		+ " <td><b style=\"color:green;\">"+set_PBTFormedButPushbackNotFormedForBombardier.size()+"</b></td> "
				 		+ "<td><b style=\"color:green;\">"+set_PBTFormedButPushbackNotFormedForUnknown.size()+"</b></td></tr>");
				
				email_report_Pushback_And_PBT_For_CELEBI_Delhi1.append(" <tr> "
						+ "<td><b> Flights with Pushback but NO Pushback Arrival activities </b></td> "
						+ "<td><b style=\"color:green;\">"+set_PushbackFormedButPBTNotFormedForNarrowbody.size()+"</b></td>"
				 		+ " <td> <b style=\"color:green;\">"+set_PushbackFormedButPBTNotFormedForWidebody.size()+"</b></td>"
				 		+ " <td><b style=\"color:green;\">"+set_PushbackFormedButPBTNotFormedForBombardier.size()+"</b></td> "
				 		+ "<td><b style=\"color:green;\">"+set_PushbackFormedButPBTNotFormedForUnknown.size()+"</b></td></tr>");
				
				email_report_Pushback_And_PBT_For_CELEBI_Delhi1.append("</table>");
				
				HtmlReportUtil.test.log(LogStatus.INFO, email_report_Pushback_And_PBT_For_CELEBI_Delhi1.toString());
				HtmlReportUtil.testHist.log(LogStatus.INFO, email_report_Pushback_And_PBT_For_CELEBI_Delhi1.toString());
				
				ExtentTest child0 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:green;\" align=\"center\">Total Number of Pushback tractors (Active) : "+list_totalActivePushbackDevices.size()+"</b>");
				child0.log(LogStatus.INFO, "<b style=\"color:green;\" align=\"center\">Total Number of Pushback tractors (Active) : "+list_totalActivePushbackDevices.size()+"</b>");			
				ExtentTest child00 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:green;\" align=\"center\">Total Number of Pushback tractors (Active) : "+list_totalActivePushbackDevices.size()+"</b>");
				child00.log(LogStatus.INFO, "<b style=\"color:green;\" align=\"center\">Total Number of Pushback tractors (Active) : "+list_totalActivePushbackDevices.size()+"</b>");
				
				ExtentTest child1 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:green;\" align=\"center\">Total Number of Pushback tractors (Utilized) : "+List_totalPushbackDevicesUtilized.size()+"</b>");
				child1.log(LogStatus.INFO, "<b style=\"color:green;\" align=\"center\">Total Number of Pushback tractors (Utilized) : "+List_totalPushbackDevicesUtilized.size()+"</b>");			
				ExtentTest child11 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:green;\" align=\"center\">Total Number of Pushback tractors (Utilized) : "+List_totalPushbackDevicesUtilized.size()+"</b>");
				child11.log(LogStatus.INFO, "<b style=\"color:green;\" align=\"center\">Total Number of Pushback tractors (Utilized) : "+List_totalPushbackDevicesUtilized.size()+"</b>");
				
				ExtentTest child2 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:red;\" align=\"center\"> Total Number of Pushback tractors NOT Utilized) : "+list_PushbackDevicesNotUtilized.size()+"</b>");
				child2.log(LogStatus.INFO, email_report_Pushback_And_PBT_For_CELEBI_Delhi2.toString());
				ExtentTest child22 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:red;\" align=\"center\"> Total Number of Pushback tractors NOT Utilized : "+list_PushbackDevicesNotUtilized.size()+"</b>");
				child22.log(LogStatus.INFO, email_report_Pushback_And_PBT_For_CELEBI_Delhi2.toString());
				
				ExtentTest child3 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:green;\" align=\"center\"> Flights with all Pushback activities (Pushback Arrival & Pushback) for Narrow Body : "+set_BothPushbackAndPBTFormedForNarrowbody.size()+"</b>");
				child3.log(LogStatus.INFO, email_report_Pushback_And_PBT_For_CELEBI_Delhi3.toString());
				ExtentTest child33 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:green;\" align=\"center\"> Flights with all Pushback activities (Pushback Arrival & Pushback) for Narrow Body : "+set_BothPushbackAndPBTFormedForNarrowbody.size()+"</b>");
				child33.log(LogStatus.INFO, email_report_Pushback_And_PBT_For_CELEBI_Delhi3.toString());
				
				ExtentTest child4 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:red;\" align=\"center\"> Flights with NO Pushback Arrival & NO Pushback activities for Narrow Body : "+set_BothPushbackAndPBTNotFormedForNarrowbody.size()+"</b>");
				child4.log(LogStatus.INFO, email_report_Pushback_And_PBT_For_CELEBI_Delhi4.toString());
				ExtentTest child44 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:red;\" align=\"center\"> Flights with NO Pushback Arrival & NO Pushback activities for Narrow Body : "+set_BothPushbackAndPBTNotFormedForNarrowbody.size()+"</b>");
				child44.log(LogStatus.INFO, email_report_Pushback_And_PBT_For_CELEBI_Delhi4.toString());
				
				ExtentTest child5 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:red;\" align=\"center\"> Flights with Pushback Arrival but NO Pushback activities for Narrow Body : "+set_PBTFormedButPushbackNotFormedForNarrowbody.size()+"</b>");
				child5.log(LogStatus.INFO, email_report_Pushback_And_PBT_For_CELEBI_Delhi5.toString());
				ExtentTest child55 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:red;\" align=\"center\"> Flights with Pushback Arrival but NO Pushback activities for Narrow Body : "+set_PBTFormedButPushbackNotFormedForNarrowbody.size()+"</b>");
				child55.log(LogStatus.INFO, email_report_Pushback_And_PBT_For_CELEBI_Delhi5.toString());
				
				ExtentTest child6 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:red;\" align=\"center\"> Flights with Pushback but NO Pushback Arrival activities for Narrow Body : "+set_PushbackFormedButPBTNotFormedForNarrowbody.size()+"</b>");
				child6.log(LogStatus.INFO, email_report_Pushback_And_PBT_For_CELEBI_Delhi6.toString());
				ExtentTest child66 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:red;\" align=\"center\"> Flights with Pushback but NO Pushback Arrival activities for Narrow Body : "+set_PushbackFormedButPBTNotFormedForNarrowbody.size()+"</b>");
				child66.log(LogStatus.INFO, email_report_Pushback_And_PBT_For_CELEBI_Delhi6.toString());
				
				ExtentTest child7 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:red;\" align=\"center\"> Flights with InCorrect activitiy sequence for Narrow Body : "+incorrectPushbackSequenceNB+"</b>");
				child7.log(LogStatus.INFO, email_report_Pushback_And_PBT_For_CELEBI_Delhi7.toString());
				ExtentTest child77 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:red;\" align=\"center\"> Flights with InCorrect activitiy sequence for Narrow Body : "+incorrectPushbackSequenceNB+"</b>");
				child77.log(LogStatus.INFO, email_report_Pushback_And_PBT_For_CELEBI_Delhi7.toString());
				
				ExtentTest child8 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:green;\" align=\"center\"> Flights with all Pushback activities (Pushback Arrival & Pushback) for Wide Body : "+set_BothPushbackAndPBTFormedForWidebody.size()+"</b>");
				child8.log(LogStatus.INFO, email_report_Pushback_And_PBT_For_CELEBI_Delhi8.toString());
				ExtentTest child88 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:green;\" align=\"center\"> Flights with all Pushback activities (Pushback Arrival & Pushback) for Wide Body : "+set_BothPushbackAndPBTFormedForWidebody.size()+"</b>");
				child88.log(LogStatus.INFO, email_report_Pushback_And_PBT_For_CELEBI_Delhi8.toString());
				
				ExtentTest child9 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:red;\" align=\"center\"> Flights with NO Pushback Arrival & NO Pushback activities for Wide Body : "+set_BothPushbackAndPBTNotFormedForWidebody.size()+"</b>");
				child9.log(LogStatus.INFO, email_report_Pushback_And_PBT_For_CELEBI_Delhi9.toString());
				ExtentTest child99 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:red;\" align=\"center\"> Flights with NO Pushback Arrival & NO Pushback activities for Wide Body : "+set_BothPushbackAndPBTNotFormedForWidebody.size()+"</b>");
				child99.log(LogStatus.INFO, email_report_Pushback_And_PBT_For_CELEBI_Delhi9.toString());
				
				ExtentTest child10 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:red;\" align=\"center\"> Flights with Pushback Arrival but NO Pushback activities for Wide Body : "+set_PBTFormedButPushbackNotFormedForWidebody.size()+"</b>");
				child10.log(LogStatus.INFO, email_report_Pushback_And_PBT_For_CELEBI_Delhi10.toString());
				ExtentTest child101 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:red;\" align=\"center\"> Flights with Pushback Arrival but NO Pushback activities for Wide Body : "+set_PBTFormedButPushbackNotFormedForWidebody.size()+"</b>");
				child101.log(LogStatus.INFO, email_report_Pushback_And_PBT_For_CELEBI_Delhi10.toString());
				
				ExtentTest child111 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:red;\" align=\"center\"> Flights with Pushback but NO Pushback Arrival activities for Wide Body : "+set_PushbackFormedButPBTNotFormedForWidebody.size()+"</b>");
				child111.log(LogStatus.INFO, email_report_Pushback_And_PBT_For_CELEBI_Delhi11.toString());
				ExtentTest child1111 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:red;\" align=\"center\"> Flights with Pushback but NO Pushback Arrival activities for Wide Body : "+set_PushbackFormedButPBTNotFormedForWidebody.size()+"</b>");
				child1111.log(LogStatus.INFO, email_report_Pushback_And_PBT_For_CELEBI_Delhi11.toString());
				
				ExtentTest child12 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:red;\" align=\"center\"> Flights with InCorrect activitiy sequence for Wide Body : "+incorrectPushbackSequenceWB+"</b>");
				child12.log(LogStatus.INFO, email_report_Pushback_And_PBT_For_CELEBI_Delhi12.toString());
				ExtentTest child121 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:red;\" align=\"center\"> Flights with InCorrect activitiy sequence for Wide Body : "+incorrectPushbackSequenceWB+"</b>");
				child121.log(LogStatus.INFO, email_report_Pushback_And_PBT_For_CELEBI_Delhi12.toString());
				
				ExtentTest child13 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:green;\" align=\"center\"> Flights with all Pushback activities (Pushback Arrival & Pushback) for Bombardier : "+set_BothPushbackAndPBTFormedForBombardier.size()+"</b>");
				child13.log(LogStatus.INFO, email_report_Pushback_And_PBT_For_CELEBI_Delhi13.toString());
				ExtentTest child131 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:green;\" align=\"center\"> Flights with all Pushback activities (Pushback Arrival & Pushback) for Bombardier : "+set_BothPushbackAndPBTFormedForBombardier.size()+"</b>");
				child131.log(LogStatus.INFO, email_report_Pushback_And_PBT_For_CELEBI_Delhi13.toString());
				
				ExtentTest child14 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:red;\" align=\"center\"> Flights with NO Pushback Arrival & NO Pushback activities for Bombardier : "+set_BothPushbackAndPBTNotFormedForBombardier.size()+"</b>");
				child14.log(LogStatus.INFO, email_report_Pushback_And_PBT_For_CELEBI_Delhi14.toString());
				ExtentTest child141 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:red;\" align=\"center\"> Flights with NO Pushback Arrival & NO Pushback activities for Bombardier : "+set_BothPushbackAndPBTNotFormedForBombardier.size()+"</b>");
				child141.log(LogStatus.INFO, email_report_Pushback_And_PBT_For_CELEBI_Delhi14.toString());
				
				ExtentTest child15 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:red;\" align=\"center\"> Flights with Pushback Arrival but NO Pushback activities for Bombardier : "+set_PBTFormedButPushbackNotFormedForBombardier.size()+"</b>");
				child15.log(LogStatus.INFO, email_report_Pushback_And_PBT_For_CELEBI_Delhi15.toString());
				ExtentTest child151 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:red;\" align=\"center\"> Flights with Pushback Arrival but NO Pushback activities for Bombardier : "+set_PBTFormedButPushbackNotFormedForBombardier.size()+"</b>");
				child151.log(LogStatus.INFO, email_report_Pushback_And_PBT_For_CELEBI_Delhi15.toString());
				
				ExtentTest child16 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:red;\" align=\"center\"> Flights with Pushback but NO Pushback Arrival activities for Bombardier : "+set_PushbackFormedButPBTNotFormedForBombardier.size()+"</b>");
				child16.log(LogStatus.INFO, email_report_Pushback_And_PBT_For_CELEBI_Delhi16.toString());
				ExtentTest child161 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:red;\" align=\"center\"> Flights with Pushback but NO Pushback Arrival activities for Bombardier : "+set_PushbackFormedButPBTNotFormedForBombardier.size()+"</b>");
				child161.log(LogStatus.INFO, email_report_Pushback_And_PBT_For_CELEBI_Delhi16.toString());
				
				ExtentTest child17 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:red;\" align=\"center\"> Flights with InCorrect activitiy sequence for Bombardier : "+incorrectPushbackSequenceBombardier+"</b>");
				child17.log(LogStatus.INFO, email_report_Pushback_And_PBT_For_CELEBI_Delhi17.toString());
				ExtentTest child171 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:red;\" align=\"center\"> Flights with InCorrect activitiy sequence for Bombardier : "+incorrectPushbackSequenceBombardier+"</b>");
				child171.log(LogStatus.INFO, email_report_Pushback_And_PBT_For_CELEBI_Delhi17.toString());
				
				ExtentTest child18 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:green;\" align=\"center\"> Flights with all Pushback activities (Pushback Arrival & Pushback) for Others : "+set_BothPushbackAndPBTFormedForUnknown.size()+"</b>");
				child18.log(LogStatus.INFO, email_report_Pushback_And_PBT_For_CELEBI_Delhi18.toString());
				ExtentTest child181 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:green;\" align=\"center\"> Flights with all Pushback activities (Pushback Arrival & Pushback) for Others : "+set_BothPushbackAndPBTFormedForUnknown.size()+"</b>");
				child181.log(LogStatus.INFO, email_report_Pushback_And_PBT_For_CELEBI_Delhi18.toString());
				
				ExtentTest child19 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:red;\" align=\"center\"> Flights with NO Pushback Arrival & NO Pushback activities for Others : "+set_BothPushbackAndPBTNotFormedForUnknown.size()+"</b>");
				child19.log(LogStatus.INFO, email_report_Pushback_And_PBT_For_CELEBI_Delhi19.toString());
				ExtentTest child191 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:red;\" align=\"center\"> Flights with NO Pushback Arrival & NO Pushback activities for Others : "+set_BothPushbackAndPBTNotFormedForUnknown.size()+"</b>");
				child191.log(LogStatus.INFO, email_report_Pushback_And_PBT_For_CELEBI_Delhi19.toString());
				
				ExtentTest child20 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:red;\" align=\"center\"> Flights with Pushback Arrival but NO Pushback activities for Others : "+set_PBTFormedButPushbackNotFormedForUnknown.size()+"</b>");
				child20.log(LogStatus.INFO, email_report_Pushback_And_PBT_For_CELEBI_Delhi20.toString());
				ExtentTest child201 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:red;\" align=\"center\"> Flights with Pushback Arrival but NO Pushback activities for Others : "+set_PBTFormedButPushbackNotFormedForUnknown.size()+"</b>");
				child201.log(LogStatus.INFO, email_report_Pushback_And_PBT_For_CELEBI_Delhi20.toString());
				
				ExtentTest child21 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:red;\" align=\"center\"> Flights with Pushback but NO Pushback Arrival activities for Others : "+set_PushbackFormedButPBTNotFormedForUnknown.size()+"</b>");
				child21.log(LogStatus.INFO, email_report_Pushback_And_PBT_For_CELEBI_Delhi21.toString());
				ExtentTest child211 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:red;\" align=\"center\"> Flights with Pushback but NO Pushback Arrival activities for Others : "+set_PushbackFormedButPBTNotFormedForUnknown.size()+"</b>");
				child211.log(LogStatus.INFO, email_report_Pushback_And_PBT_For_CELEBI_Delhi21.toString());
				
				ExtentTest child222 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:red;\" align=\"center\"> Flights with InCorrect activitiy sequence for Others : "+incorrectPushbackSequenceOthers+"</b>");
				child222.log(LogStatus.INFO, email_report_Pushback_And_PBT_For_CELEBI_Delhi22.toString());
				ExtentTest child2221 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:red;\" align=\"center\"> Flights with InCorrect activitiy sequence for Others : "+incorrectPushbackSequenceOthers+"</b>");
				child2221.log(LogStatus.INFO, email_report_Pushback_And_PBT_For_CELEBI_Delhi22.toString());
				
				HtmlReportUtil.test.appendChild(child0).appendChild(child1).appendChild(child2).appendChild(child3).appendChild(child4).appendChild(child5).appendChild(child6).appendChild(child7).appendChild(child8).appendChild(child9).appendChild(child10).appendChild(child111).appendChild(child12).appendChild(child13).appendChild(child14).appendChild(child15).appendChild(child16).appendChild(child17).appendChild(child18).appendChild(child19).appendChild(child20).appendChild(child21).appendChild(child222);
				HtmlReportUtil.testHist.appendChild(child00).appendChild(child11).appendChild(child22).appendChild(child33).appendChild(child44).appendChild(child55).appendChild(child66).appendChild(child77).appendChild(child88).appendChild(child99).appendChild(child101).appendChild(child1111).appendChild(child121).appendChild(child131).appendChild(child141).appendChild(child151).appendChild(child161).appendChild(child171).appendChild(child181).appendChild(child191).appendChild(child201).appendChild(child211).appendChild(child2221);
		DBWrapper.dbConnectionClose();
 }
}
