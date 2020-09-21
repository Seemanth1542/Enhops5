package enh.db.pages;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

	public class SQL_Queries {
		public static Calendar cal;
		public static DateFormat dateFormat;
		
	public static String yesterDate(){
			 cal = Calendar.getInstance();
			   dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			   //System.out.println("Today's date is "+dateFormat.format(cal.getTime()));

			   cal.add(Calendar.DATE, -1);
			  // System.out.println("Yesterday's date was "+dateFormat.format(cal.getTime())); 
			   return dateFormat.format(cal.getTime());
		}	
	public static String todayDate(){
		 cal = Calendar.getInstance();
		   dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		  // System.out.println("Today's date is "+dateFormat.format(cal.getTime()));
           return dateFormat.format(cal.getTime());
		  
	}	
	public static String todayDayDateTime(){
		Date date = new Date();
		 SimpleDateFormat formatter = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss z");  
		 String strDate = formatter.format(date);
		 System.out.println("Date Format with E, dd MMM yyyy HH:mm:ss z : "+strDate);  
		return strDate;  
		  
	}	
	public static String todayDateTime(){
		Date date = new Date();
		SimpleDateFormat	formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");  
		String  strDate = formatter.format(date);  
	    System.out.println("Date Format with dd-M-yyyy hh:mm:ss : "+strDate); 
		return strDate;  
		  
	}	
	
	//	 ATA & Sensor_ATA - differences +/- 1 minute
	public static String strQuery_1 = "select logid, flightnumber_arrival, ATA, Sensor_ATA, Diff, (case when (Diff < 60 OR Diff < -60) then '1' else '0' end) as Status from "
			+ "(SELECT logid, flightnumber_arrival, ATA, Sensor_ATA, TIMESTAMPDIFF(second, ATA, Sensor_ATA) as Diff  FROM `DailyFlightSchedule_Merged`\r\n" + 
			"where (date(atd)='2019-09-30' or date(sensor_atd)='2019-09-30') and operationunit = 4 and flightnumber_arrival not like '%leader%' and\r\n" + 
			"(sta is not null and eta is not null) and flightnumber_arrival like '%sg%' group by logid)x group by logid";
	
	// On_Block time stamps should not be between ATA and Sensor_ATA time stamps
	public static String strQuery_2 = "SELECT logid, flightnumber_arrival, ATA, On_Block_Time, Sensor_ATA, (case when (On_Block_Time > ATA AND On_Block_Time > Sensor_ATA) then 1 else 0 end) as Status \r\n" + 
			"FROM `DailyFlightSchedule_Merged` where (date(atd)='2019-09-27' or date(sensor_atd)='2019-09-27') and\r\n" + 
			"operationunit = 4 and flightnumber_arrival not like '%leader%' and (sta is not null and eta is not null) and flightnumber_arrival like '%sg%'";
	
	// Sensor_ATA < On_Block < Off_Block < Sensor_ATD
	public static String strQuery_3 = "SELECT logid, flightnumber_arrival, Sensor_ATA, On_Block_Time, Off_Block_Time, Sensor_ATD, (case when ((Sensor_ATA < On_Block_Time AND Sensor_ATA < Off_Block_Time) \r\n" + 
			"AND (Sensor_ATD > On_Block_Time AND Sensor_ATD > Off_Block_Time)) then 1 else 0 end) as Status FROM `DailyFlightSchedule_Merged`\r\n" + 
			"where (date(atd)='"+SQL_Queries.yesterDate()+"' or date(sensor_atd)='"+SQL_Queries.yesterDate()+"') and operationunit = 4 and gmrpk_arrival is not null order by Logid desc";

	// DB On_Block_Time = UI ATA , DB Off_Block_Time = UI ATD
		// Total Count of Sensor_ATA and Sensor_ATD captured by ZestIOT Sensors for Hyderabad
	//public static String strQuery_4="SELECT  count(*) FROM `DailyFlightSchedule_Merged` where (date(sensor_atd) ='2019-11-12' or date(atd)= '2019-11-12') and operationunit=4 and gmrpk_arrival is not null";
	/*public static String strQuery_4="SELECT  count(*) FROM `DailyFlightSchedule_Merged` where (date(sensor_atd) ='"+SQL_Queries.yesterDate()+"' or date(atd)= '"+SQL_Queries.yesterDate()+"') and operationunit=4 and gmrpk_arrival is not null";
	public static String strQuery_5="SELECT  count(*), if(sensor_ata, 'yes', 'no') as has_data FROM `DailyFlightSchedule_Merged` where (date(sensor_atd) ='"+SQL_Queries.yesterDate()+"' or date(atd)= '"+SQL_Queries.yesterDate()+"') and operationunit=4 and gmrpk_arrival is not null  group by has_data";
	public static String strQuery_6="SELECT  count(*), if(sensor_atd, 'yes', 'no') as has_data FROM `DailyFlightSchedule_Merged` where (date(sensor_atd) ='"+SQL_Queries.yesterDate()+"' or date(atd)= '"+SQL_Queries.yesterDate()+"') and operationunit=4 and gmrpk_arrival is not null  group by has_data";

	// Total Count of Sensor_ATA and Sensor_ATD captured by ZestIOT Sensors for Delhi
	public static String strQuery_7="SELECT  count(*) FROM `DailyFlightSchedule_Merged` where (date(sensor_atd) ='"+SQL_Queries.yesterDate()+"' or date(atd)= '"+SQL_Queries.yesterDate()+"') and operationunit=22 and gmrpk_arrival is not null";
	public static String strQuery_8="SELECT  count(*), if(sensor_ata, 'yes', 'no') as has_data FROM `DailyFlightSchedule_Merged` where (date(sensor_atd) ='"+SQL_Queries.yesterDate()+"' or date(atd)= '"+SQL_Queries.yesterDate()+"') and operationunit=22 and gmrpk_arrival is not null  group by has_data";
	public static String strQuery_9="SELECT  count(*), if(sensor_atd, 'yes', 'no') as has_data FROM `DailyFlightSchedule_Merged` where (date(sensor_atd) ='"+SQL_Queries.yesterDate()+"' or date(atd)= '"+SQL_Queries.yesterDate()+"') and operationunit=22 and gmrpk_arrival is not null  group by has_data";
	
	// Total Count of On_block_time and Off_block_time for hyderabad
	public static String strQuery_10="SELECT  count(*) FROM `DailyFlightSchedule_Merged` where (date(sensor_atd) ='"+SQL_Queries.yesterDate()+"' or date(atd)= '"+SQL_Queries.yesterDate()+"') and operationunit=4 and gmrpk_arrival is not null";
	public static String strQuery_11="SELECT  count(*), if(on_block_time, 'yes', 'no') as has_data FROM `DailyFlightSchedule_Merged` where (date(sensor_atd) ='"+SQL_Queries.yesterDate()+"' or date(atd)= '"+SQL_Queries.yesterDate()+"') and operationunit=4 and gmrpk_arrival is not null  group by has_data";
	public static String strQuery_12="SELECT  count(*), if(off_block_time, 'yes', 'no') as has_data FROM `DailyFlightSchedule_Merged` where (date(sensor_atd) ='"+SQL_Queries.yesterDate()+"' or date(atd)= '"+SQL_Queries.yesterDate()+"') and operationunit=4 and gmrpk_arrival is not null  group by has_data";*/
    
	public static String strQuery_5 ="SELECT  count(Logid)\r\n" + 
			" FROM `DailyFlightSchedule_Merged` where (date(atd)='"+SQL_Queries.yesterDate()+"' or date(sensor_atd)='"+SQL_Queries.yesterDate()+"')\r\n" + 
			" and operationunit = 22 and FlightNumber_Arrival regexp\r\n" + 
			" 'TK|EY|CX|QR|ET|AK|KC|JL|KE|PS|AC|AF|AZ|BA|SU|IA|SV|UL|T5|FZ|KQ|I5' ";
	
	public static String strQuery_6 ="SELECT Logid, FlightNumber_Arrival, cobt, Off_Block_Time FROM `DailyFlightSchedule_Merged` \r\n" + 
			"where (date(atd)='"+SQL_Queries.yesterDate()+"' or date(sensor_atd)='"+SQL_Queries.yesterDate()+"') and operationunit = 22 and \r\n" + 
			"FlightNumber_Arrival regexp 'TK|EY|CX|QR|ET|AK|KC|JL|KE|PS|AC|AF|AZ|BA|SU|IA|SV|UL|T5|FZ|KQ|I5'\r\n" + 
			"and cobt is  null";
			public static String strQuery_7 ="SELECT Logid, FlightNumber_Arrival, cobt, Off_Block_Time FROM `DailyFlightSchedule_Merged` \r\n" + 
					"where (date(atd)='"+SQL_Queries.yesterDate()+"' or date(sensor_atd)='"+SQL_Queries.yesterDate()+"') and operationunit = 22 and \r\n" + 
					"FlightNumber_Arrival regexp 'TK|EY|CX|QR|ET|AK|KC|JL|KE|PS|AC|AF|AZ|BA|SU|IA|SV|UL|T5|FZ|KQ|I5'\r\n" + 
					"and Off_Block_Time is  null";
			public static String strQuery_8 ="SELECT Logid, FlightNumber_Arrival, cobt, Off_Block_Time, CONCAT('',TIMEDIFF(Off_Block_Time, cobt)), " + 
					" TIME_TO_SEC(TIMEDIFF(Off_Block_Time, cobt)),TIME_TO_SEC(TIMEDIFF(cobt, Off_Block_Time)) FROM `DailyFlightSchedule_Merged` where (date(atd)='"+SQL_Queries.yesterDate()+"' or date(sensor_atd)='"+SQL_Queries.yesterDate()+"')\r\n" + 
					" and operationunit = 22 and FlightNumber_Arrival regexp\r\n" + 
					" 'TK|EY|CX|QR|ET|AK|KC|JL|KE|PS|AC|AF|AZ|BA|SU|IA|SV|UL|T5|FZ|KQ|I5'";
			
			public static String strQuery_9 ="SELECT  count(CONCAT('',TIMEDIFF(Off_Block_Time, cobt)))\r\n" + 
					" FROM `DailyFlightSchedule_Merged` where (date(atd)='"+SQL_Queries.yesterDate()+"' or date(sensor_atd)='"+SQL_Queries.yesterDate()+"')\r\n" + 
					" and operationunit = 22 and FlightNumber_Arrival regexp\r\n" + 
					" 'TK|EY|CX|QR|ET|AK|KC|JL|KE|PS|AC|AF|AZ|BA|SU|IA|SV|UL|T5|FZ|KQ|I5' and (TIME_TO_SEC(TIMEDIFF(Off_Block_Time, cobt))>300 or TIME_TO_SEC(TIMEDIFF(cobt, Off_Block_Time))>300)";



			public static String strQuery_10 ="SELECT Logid, FlightNumber_Arrival, cobt, Off_Block_Time FROM `DailyFlightSchedule_Merged` \r\n" + 
					"where (date(atd)='"+SQL_Queries.yesterDate()+"' or date(sensor_atd)='"+SQL_Queries.yesterDate()+"') and operationunit = 4 and \r\n" + 
					"FlightNumber_Arrival regexp 'G9|AI|9I|CX|EY|FZ|G8|J9|9W|S2|SV|MI|UL|TG|UK|WY'\r\n" + 
					"and cobt is  null";
					public static String strQuery_11 ="SELECT Logid, FlightNumber_Arrival, cobt, Off_Block_Time FROM `DailyFlightSchedule_Merged` \r\n" + 
							"where (date(atd)='"+SQL_Queries.yesterDate()+"' or date(sensor_atd)='"+SQL_Queries.yesterDate()+"') and operationunit = 4 and \r\n" + 
							"FlightNumber_Arrival regexp 'G9|AI|9I|CX|EY|FZ|G8|J9|9W|S2|SV|MI|UL|TG|UK|WY'\r\n" + 
							"and Off_Block_Time is  null";
					public static String strQuery_12 ="SELECT Logid, FlightNumber_Arrival, cobt, Off_Block_Time, CONCAT('',TIMEDIFF(Off_Block_Time, cobt)), " + 
							" TIME_TO_SEC(TIMEDIFF(Off_Block_Time, cobt)),TIME_TO_SEC(TIMEDIFF(cobt, Off_Block_Time)) FROM `DailyFlightSchedule_Merged` where (date(atd)='"+SQL_Queries.yesterDate()+"' or date(sensor_atd)='"+SQL_Queries.yesterDate()+"')\r\n" + 
							" and operationunit = 4 and FlightNumber_Arrival regexp\r\n" + 
							" 'G9|AI|9I|CX|EY|FZ|G8|J9|9W|S2|SV|MI|UL|TG|UK|WY'";
					
					public static String strQuery_13 ="SELECT  count(CONCAT('',TIMEDIFF(Off_Block_Time, cobt)))\r\n" + 
							" FROM `DailyFlightSchedule_Merged` where (date(atd)='"+SQL_Queries.yesterDate()+"' or date(sensor_atd)='"+SQL_Queries.yesterDate()+"')\r\n" + 
							" and operationunit = 4 and FlightNumber_Arrival regexp\r\n" + 
							" 'G9|AI|9I|CX|EY|FZ|G8|J9|9W|S2|SV|MI|UL|TG|UK|WY' and (TIME_TO_SEC(TIMEDIFF(Off_Block_Time, cobt))>300 or TIME_TO_SEC(TIMEDIFF(cobt, Off_Block_Time))>300)";

					
					public static String strQuery_14 ="SELECT Logid, FlightNumber_Arrival, cobt, Off_Block_Time FROM `DailyFlightSchedule_Merged` \r\n" + 
							"where (date(atd)='"+SQL_Queries.yesterDate()+"' or date(sensor_atd)='"+SQL_Queries.yesterDate()+"') and operationunit = 4 and \r\n" + 
							"flightnumber_arrival like 'SG%'" + 
							"and cobt is  null";
							public static String strQuery_15 ="SELECT Logid, FlightNumber_Arrival, cobt, Off_Block_Time FROM `DailyFlightSchedule_Merged` \r\n" + 
									"where (date(atd)='"+SQL_Queries.yesterDate()+"' or date(sensor_atd)='"+SQL_Queries.yesterDate()+"') and operationunit = 4 and \r\n" + 
									"flightnumber_arrival like 'SG%'\r\n" + 
									"and Off_Block_Time is  null";
							public static String strQuery_16 ="SELECT Logid, FlightNumber_Arrival, cobt, Off_Block_Time, CONCAT('',TIMEDIFF(Off_Block_Time, cobt)), " + 
									" TIME_TO_SEC(TIMEDIFF(Off_Block_Time, cobt)),TIME_TO_SEC(TIMEDIFF(cobt, Off_Block_Time)) FROM `DailyFlightSchedule_Merged` where (date(atd)='"+SQL_Queries.yesterDate()+"' or date(sensor_atd)='"+SQL_Queries.yesterDate()+"')\r\n" + 
									" and operationunit = 4 and flightnumber_arrival like 'SG%'";
							
							public static String strQuery_17 ="SELECT  count(CONCAT('',TIMEDIFF(Off_Block_Time, cobt)))\r\n" + 
									" FROM `DailyFlightSchedule_Merged` where (date(atd)='"+SQL_Queries.yesterDate()+"' or date(sensor_atd)='"+SQL_Queries.yesterDate()+"')\r\n" + 
									" and operationunit = 4 and flightnumber_arrival like 'SG%' and (TIME_TO_SEC(TIMEDIFF(Off_Block_Time, cobt))>300 or TIME_TO_SEC(TIMEDIFF(cobt, Off_Block_Time))>300)";


	
							//Total flights scheduled departure for GMR-Hyd
						    public static String strQuery_021="SELECT count(*) FROM `DailyFlightScheduleDeparture_GMR` where \r\n" +
						    "(date(std)= '"+SQL_Queries.yesterDate()+"' or date(mediator_std)='"+SQL_Queries.yesterDate()+"') and operationunit= 4";
						                           
						    //Total flights count for GMR-Hyd for which sensor_atd has been captured       
						    public static String strQuery_022="SELECT count(*) FROM `DailyFlightSchedule_Merged` where \r\n " +
						    "flightdepartureId in (SELECT logid FROM `DailyFlightScheduleDeparture_GMR` where (date(std)= '"+SQL_Queries.yesterDate()+"' or date(mediator_std)='"+SQL_Queries.yesterDate()+"') and operationunit= 4) \r\n" +
						    "and sensor_atd is not null";
						                                           
						    //Total flights list for GMR-Hyd for which sensor_atd has not been captured       
						    public static String strQuery_023="SELECT logid, flightnumber_departure, std, etd, atd FROM `DailyFlightSchedule_Merged` where (date(std)= '"+SQL_Queries.yesterDate()+"' or date(mediator_std)='"+SQL_Queries.yesterDate()+"') and operationunit= 4 and sensor_atd is null";	//Total flights for GMR-Hyd for which sensor_atd has not been captured		
	public static String strQuery_024="SELECT logid, flightnumber_arrival, flightnumber_departure, sensor_atd FROM `DailyFlightSchedule_Merged` where flightdepartureId in (SELECT logid FROM `DailyFlightScheduleDeparture_GMR` where (date(std)= '"+SQL_Queries.yesterDate()+"' or date(mediator_std)='"+SQL_Queries.yesterDate()+"') and operationunit= 4) and sensor_atd is null";
	
	
		//Total flights scheduled arrival for Delhi
		public static String strQuery_025="SELECT count(*) FROM `DailyFlightScheduleArrival_GMR`where (date(sta)= '"+SQL_Queries.yesterDate()+"' or date(mediator_sta)='"+SQL_Queries.yesterDate()+"') and operationunit= 22";
		
		//Total flights count for Delhi for which sensor_ata has been captured
		public static String strQuery_026="SELECT count(*) FROM `DailyFlightSchedule_Merged` where flightArrivalId in (SELECT logid FROM `DailyFlightScheduleArrival_GMR`where (date(sta)= '"+SQL_Queries.yesterDate()+"' or date(mediator_sta)='"+SQL_Queries.yesterDate()+"') and operationunit=22) and sensor_ata is not null";
		
		//Total flights count for Delhi for which sensor_ata has not been captured
		public static String strQuery_027="SELECT count(*) FROM `DailyFlightSchedule_Merged` where flightArrivalId in (SELECT logid FROM `DailyFlightScheduleArrival_GMR`where (date(sta)= '"+SQL_Queries.yesterDate()+"' or date(mediator_sta)='"+SQL_Queries.yesterDate()+"') and operationunit=22) and sensor_ata is null";
		
		//Total flights for Delhi for which sensor_ata has not been captured
		public static String strQuery_028="SELECT logid, flightnumber_arrival, flightnumber_departure, sensor_ata FROM `DailyFlightSchedule_Merged` where flightArrivalId in (SELECT logid FROM `DailyFlightScheduleArrival_GMR`where (date(sta)= '"+SQL_Queries.yesterDate()+"' or date(mediator_sta)='"+SQL_Queries.yesterDate()+"') and operationunit=22) and sensor_ata is null";
		
		//Total flights scheduled departure for Delhi
		public static String strQuery_029="SELECT count(*) FROM `DailyFlightScheduleDeparture_GMR` where (date(std)= '"+SQL_Queries.yesterDate()+"' or date(mediator_std)='"+SQL_Queries.yesterDate()+"') and operationunit= 22";
		
		//Total flights count for Delhi for which sensor_atd has been captured		
		public static String strQuery_030="SELECT count(*) FROM `DailyFlightSchedule_Merged` where flightdepartureId in (SELECT logid FROM `DailyFlightScheduleDeparture_GMR` where (date(std)= '"+SQL_Queries.yesterDate()+"' or date(mediator_std)='"+SQL_Queries.yesterDate()+"') and operationunit= 22) and sensor_atd is not null";
		
		//Total flights count for Delhi for which sensor_atd has not been captured		
		public static String strQuery_031="SELECT count(*) FROM `DailyFlightSchedule_Merged` where flightdepartureId in (SELECT logid FROM `DailyFlightScheduleDeparture_GMR` where (date(std)= '"+SQL_Queries.yesterDate()+"' or date(mediator_std)='"+SQL_Queries.yesterDate()+"') and operationunit= 22) and sensor_atd is null";
		
		//Total flights for Delhi for which sensor_atd has not been captured		
		public static String strQuery_032="SELECT logid, flightnumber_arrival, flightnumber_departure, sensor_atd FROM `DailyFlightSchedule_Merged` where flightdepartureId in (SELECT logid FROM `DailyFlightScheduleDeparture_GMR` where (date(std)= '"+SQL_Queries.yesterDate()+"' or date(mediator_std)='"+SQL_Queries.yesterDate()+"') and operationunit= 22) and sensor_atd is null";
	
	
	    
	   	    //Total flights list for GMR-Hyd for which sensor_ata has not been captured
	    public static String strQuery_020= "SELECT logid, flightnumber, sta, eta, ata FROM `DailyFlightScheduleArrival_GMR`where \r\n" +
	    "(date(sta)= '"+SQL_Queries.yesterDate()+"' or date(mediator_sta)='"+SQL_Queries.yesterDate()+"') and operationunit=4 and sensor_ata is null";

	  //Total flights for HYD for which Off-block captured from Sensor (type= aircraft)   
	    public static String strQuery_036= "SELECT count(*) FROM `EquipActivityLogs` where flight_pk in (SELECT logid FROM `DailyFlightSchedule_Merged`\r\n "+
	     "where (date(std)= '"+SQL_Queries.yesterDate()+"' or date(mediator_std)= '"+SQL_Queries.yesterDate()+"') and operationunit = 4 and off_block_time is not null ) and operationname = 'ofb' and type = 'aircraft'order by flightno";
	       
	    //Total flights for HYD for which Off-block captured from Computer Vision (type= cv)   
	    public static String strQuery_037= "SELECT flight_pk, flightno FROM `EquipActivityLogs` where flight_pk in (SELECT logid FROM `DailyFlightSchedule_Merged`\r\n" +
	            "where (date(std)= '"+SQL_Queries.yesterDate()+"' or date(mediator_std)= '"+SQL_Queries.yesterDate()+"') and operationunit = 4 and off_block_time is not null ) and operationname = 'ofb' and type = 'cv'order by flightno";
	       
	    //Total flights for HYD for which Off-block > Sensor_ATD (negative scenario)   
	    public static String strQuery_038= "SELECT logid, flightnumber_departure, sensor_ATD, Off_block_time, (case when (Off_Block_Time < Sensor_ATD) then 1 else 0 end) as Status, \r\n"
	    + "CONCAT('',TIMEDIFF(Off_Block_Time, Sensor_ATD)) as difference FROM `DailyFlightSchedule_Merged` where (date(std)= '"+SQL_Queries.yesterDate()+"' or date(mediator_std)= '"+SQL_Queries.yesterDate()+"') and operationunit = 4 and (sensor_atd is not null and Off_block_time is not null) order by flightnumber_arrival";   
	
	  //Total flights scheduled arrival for GMR-Hyd
		public static String strQuery_017="SELECT count(*) FROM `DailyFlightScheduleArrival_GMR`where \r\n" 
		+ "(date(sta)= '"+SQL_Queries.yesterDate()+"' or date(mediator_sta)='"+SQL_Queries.yesterDate()+"') and operationunit= 4";
								
		//Total flights count for GMR-Hyd for which sensor_ata has been captured
		public static String strQuery_018="SELECT count(*) FROM `DailyFlightSchedule_Merged` where \r\n" +
		"flightArrivalId in (SELECT logid FROM `DailyFlightScheduleArrival_GMR`where (date(sta)= '"+SQL_Queries.yesterDate()+"' or date(mediator_sta)='"+SQL_Queries.yesterDate()+"') and operationunit=4) \r\n" + 
		"and sensor_ata is not null";
														
		//Total flights list for GMR-Hyd for which sensor_ata has not been captured
		public static String strQuery_019= "SELECT logid, flightnumber, sta, eta, ata FROM `DailyFlightScheduleArrival_GMR`where \r\n" +
		"(date(sta)= '"+SQL_Queries.yesterDate()+"' or date(mediator_sta)='"+SQL_Queries.yesterDate()+"') and operationunit=4 and sensor_ata is null";

	//Total flights for HYD for which On-block captured from Sensor (type= aircraft)	
		public static String strQuery_033= "SELECT count(*) FROM `EquipActivityLogs` where flight_pk in (SELECT logid FROM `DailyFlightSchedule_Merged` \r\n"
		+ "where (date(sta)= '"+SQL_Queries.yesterDate()+"' or date(mediator_sta)= '"+SQL_Queries.yesterDate()+"') and operationunit = 4 and on_block_time is not null ) \r\n "
		+ "and operationname = 'onb' and type = 'aircraft' order by flightno";
		
		//Total flights for HYD for which On-block captured from Computer Vision (type= cv)	
		public static String strQuery_034= "SELECT flight_pk, flightno FROM `EquipActivityLogs` where flight_pk in (SELECT logid FROM `DailyFlightSchedule_Merged` \r\n"
		+ "where (date(sta)= '"+SQL_Queries.yesterDate()+"' or date(mediator_sta)= '"+SQL_Queries.yesterDate()+"') and operationunit = 4 and on_block_time is not null ) \r\n "
		+ "and operationname = 'onb' and type = 'cv' order by flightno";
		
		//Total flights list for HYD for which Sensor_ATA > On-block (negative scenario)
		public static String strQuery_035= "SELECT logid, flightnumber_arrival, sensor_ATA, On_block_time, (case when (sensor_ATA < On_Block_Time) then 1 else 0 end) as Status, \r\n"
		+ "CONCAT('',TIMEDIFF(sensor_ata, on_block_time)) as difference FROM `DailyFlightSchedule_Merged` where (date(sta)= '"+SQL_Queries.yesterDate()+"' or date(mediator_sta)= '"+SQL_Queries.yesterDate()+"') and operationunit = 4 and (sensor_ata is not null and On_block_time is not null) order by flightnumber_arrival";	
	
	
	
	
	
	
	
	}
