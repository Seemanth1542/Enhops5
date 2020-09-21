package enh.db.pages;

import java.sql.ResultSet;
import java.util.ArrayList;

import utilities.HtmlReportUtil;

public class DBTesting {

	public static String hydReport;
	public static String delhiReport;
	public static String hyd_Sensor_ATA_LessThan_On_Block_LessThan_Off_Block_LessThan_Sensor_ATD_Report;
	public static String finalreport;
	public static ArrayList status1List = new ArrayList();
	public static ArrayList status0List = new ArrayList();
	public static String totalFlights= null;
	public static String countSensorATA;
	public static String countSensorATD;
	public static String countOnBlockTime;
	public static String countOffBlockTime;
	 
	
	public static void ATASensor_ATA_differences_1_minute(String environment) throws Exception{
		
		ResultSet result = DBWrapper.Connect(SQL_Queries.strQuery_1,environment);
		while (result.next()){
			
			String str_LogID = result.getString("logid");
			
			String	flight_NumberArrival = result.getString("flightnumber_arrival");
			
			String	flight_Sensor_ATA1 = result.getString("Sensor_ATA");
			
			String	flight_On_Block_Time = result.getString("ATA");
			
			String	flight_Off_Block_Time = result.getString("Diff");
			
			String status = result.getString("Status");
			boolean status1 = status.equals("1");
			if (status1) {
				HtmlReportUtil.stepInfo("<b style=\"color:green;\">=========On_Block time stamps is be between ATA and Sensor_ATA time stamps -- PASS=============</b>");
				System.out.println("On_Block time stamps is not be between ATA and Sensor_ATA time stamps -- PASS");
			}else{
				HtmlReportUtil.stepInfo("<b style=\"color:red;\">=========On_Block time stamps is not be between ATA and Sensor_ATA time stamps -- FAIL=============</b>");
				System.out.println("On_Block time stamps between ATA and Sensor_ATA time stamps -- FAIL");
			}
			HtmlReportUtil.stepInfo("| "+ str_LogID +" | "+ flight_NumberArrival +" | "+ flight_Sensor_ATA1 +" | "+ flight_On_Block_Time +" | "+ flight_Off_Block_Time +" | " + status + " |");
			System.out.println("| "+ str_LogID +" | "+ flight_NumberArrival +" | "+ flight_Sensor_ATA1 +" | "+ flight_On_Block_Time +" | "+ flight_Off_Block_Time +" | " + status + " |");
			System.out.println(" ");
		}
		DBWrapper.dbConnectionClose();
	}


	public static void On_Block_time_stamps_should_be_between_ATA_and_Sensor_ATA_time_stamps(String environment) throws Exception{
		
		ResultSet result = DBWrapper.Connect(SQL_Queries.strQuery_2,environment);
		while (result.next()){							        
            
			String str_LogID = result.getString("logid");
			
			String	flight_NumberArrival = result.getString("flightnumber_arrival");
			
			String	ATA = result.getString("ATA");
			
			String	flight_On_Block_Time = result.getString("On_Block_Time");
		
			String	flight_Sensor_ATA = result.getString("Sensor_ATA");
			
			String	status = result.getString("Status");
			
			boolean status1 = status.equals("1");
			if (status1) {
				HtmlReportUtil.stepInfo("<b style=\"color:green;\">=========On_Block time stamps is be between ATA and Sensor_ATA time stamps -- PASS=============</b>");
				System.out.println("On_Block time stamps is not be between ATA and Sensor_ATA time stamps -- PASS");
			}else{
				HtmlReportUtil.stepInfo("<b style=\"color:red;\">=========On_Block time stamps is not be between ATA and Sensor_ATA time stamps -- FAIL=============</b>");
				System.out.println("On_Block time stamps between ATA and Sensor_ATA time stamps -- FAIL");
			}
			HtmlReportUtil.stepInfo("| "+ str_LogID +" | "+ flight_NumberArrival +" | "+ ATA +" | "+ flight_On_Block_Time +" | "+ flight_Sensor_ATA +" | " + status + " |");
			System.out.println("| "+ str_LogID +" | "+ flight_NumberArrival +" | "+ ATA +" | "+ flight_On_Block_Time +" | "+ flight_Sensor_ATA +" | " + status + " |");
			System.out.println(" ");
		}
		DBWrapper.dbConnectionClose();
	}
	
	
	public static void Sensor_ATA_LessThan_On_Block_LessThan_Off_Block_LessThan_Sensor_ATD(String environment) throws Exception{
		
		ResultSet result = DBWrapper.Connect(SQL_Queries.strQuery_3,environment);
		while (result.next()){	
			            
			String str_LogID = result.getString("logid");
			
			String	flight_NumberArrival = result.getString("flightnumber_arrival");
		
			String	flight_Sensor_ATA = result.getString("Sensor_ATA");
			
			String	flight_On_Block_Time = result.getString("On_Block_Time");
			
			String	flight_Off_Block_Time = result.getString("Off_Block_Time");
			
			String	flight_Sensor_ATD = result.getString("Sensor_ATD");
			
			String	status = result.getString("Status");
		
			boolean status1 = status.equals("1");
			if (status1) {
				//HtmlReportUtil.stepInfo("<b style=\"color:green;\">=========On_Block and Off_Block timestamps are in between Sensor_ATA and Sensor_ATD timestamps =============</b>");
				System.out.println("On_Block time stamps is between ATA and Sensor_ATA time stamps -- PASS");
			status1List.add(str_LogID);
			}else{
				//HtmlReportUtil.stepInfo("<b style=\"color:red;\">=========On_Block time stamps is not between ATA and Sensor_ATA time stamps -- FAIL=============</b>");
				System.out.println("On_Block and Off_Block timestamps are not in between Sensor_ATA and Sensor_ATD timestamps ");
				status0List.add(str_LogID);
			}
			//HtmlReportUtil.stepInfo("| "+ str_LogID +" | "+ flight_NumberArrival +" | "+ flight_Sensor_ATA +" | "+ flight_On_Block_Time +" | "+ flight_Off_Block_Time +" | " + flight_Sensor_ATD +" | " + status + " |");
			System.out.println("| "+ str_LogID +" | "+ flight_NumberArrival +" | "+ flight_Sensor_ATA +" | "+ flight_On_Block_Time +" | "+ flight_Off_Block_Time +" | " + flight_Sensor_ATD +" | " + status + " |");
			System.out.println(" ");
		}
		HtmlReportUtil.stepInfo("<b style=\"color:blue;\">Total number of flights in Hyderabad: "+(status1List.size()+status0List.size())+" </b>");
		HtmlReportUtil.stepInfo("<b style=\"color:green;\">No. of flights for which sensor_ATA captured :  "+countSensorATA+" </b>");
		HtmlReportUtil.stepInfo("<b style=\"color:green;\">No. of flights for which sensor_ATD captured. : "+countSensorATD+"</b>");
		
		HtmlReportUtil.stepInfo("<b style=\"color:green;\">No. of flights for which On_block captured :  "+countOnBlockTime+" </b>");
		HtmlReportUtil.stepInfo("<b style=\"color:green;\">No. of flights for which off_block captured : "+countOffBlockTime+"</b>");
		
		HtmlReportUtil.stepInfo("<b style=\"color:green;\">No. of flights for which on_block and off_block are between sensor_ATA and sensor_ATD :  "+status1List.size()+" </b>");
		HtmlReportUtil.stepInfo("<b style=\"color:red;\">No. of flights for which on_block and off_block are not in between sensor_ATA and sensor_ATD : "+status0List.size()+"</b>");
		
		DBWrapper.dbConnectionClose();
		
		String sensor_ata_table_header=
		         
       		 "<html><p>Hi Team, <br> Please find automation report for Sensor_ATA and Sensor_ATD and report for the below scenario: <br>"
       		 + " 'Sensor-ATA < ONBL< OFBL < Sensor-ATD'  <br><b> Hyderabad user: </b><br><br>"
       		 + "<table width='100%' border='1' align='center'>"
               + "<tr  bgColor=#00b3b3 align='center'>"
               + "<td><b>Date<b></td>"
               + "<td><b>Description<b></td>"
               + "<td><b>Total No. of Flights <b></td>"
               + "</tr>";
		String sensor_ata_table11=sensor_ata_table_header+"<tr align='center'>"
				 +"<td>" + SQL_Queries.yesterDate() + "</td>"
		           +"<td>" + "Total number of flights "+ "</td>"
		           + "<td>" + (status1List.size()+status0List.size()) + "</td>"
		               +"</tr>";
		String sensor_ata_table12=sensor_ata_table11+"<tr align='center'>"
				 +"<td>" + SQL_Queries.yesterDate() + "</td>"
		           +"<td>" + "No.of Flights with Sensor ATA "+ "</td>"
		           + "<td>" + countSensorATA + "</td>"
		               +"</tr>";
		String sensor_ata_table1=sensor_ata_table12+"<tr align='center'>"
				 +"<td>" + SQL_Queries.yesterDate() + "</td>"
		           +"<td>" + "No.of Flights with Sensor ATD "+ "</td>"
		           + "<td>" + countSensorATD+ "</td>"
		               +"</tr>";
		String sensor_ata_table2=sensor_ata_table1+"<tr align='center'>"
				 +"<td>" + SQL_Queries.yesterDate() + "</td>"
		           +"<td>" + "Total number of flights for which On_Block and Off_Block timestamps are in between Sensor_ATA and Sensor_ATD timestamps "+ "</td>"
		           + "<td>" + status1List.size() + "</td>"
		               +"</tr>";
		
		hyd_Sensor_ATA_LessThan_On_Block_LessThan_Off_Block_LessThan_Sensor_ATD_Report=sensor_ata_table2 +"<tr align='center'>"
				+"<td>" + SQL_Queries.yesterDate() + "</td>"
				+"<td>" + "Total number of flights for which On_Block and Off_Block timestamps are not in between Sensor_ATA and Sensor_ATD timestamps " + "</td>"
				+"<td>" + status0List.size() + "</td>"
				+"</tr></table>"
                + " <br><br> Thank You, <br><br> Automation Team </html>";
	


	}
public static void validateSensorDataForHyderabad(String environment) throws Exception{
	String totalFlights= null;
//	String countSensorATA= null;
//	String countSensorATD= null;
	 
	
		
		//ResultSet result = DBWrapper.Connect(SQL_Queries.strQuery_4);
		ResultSet result = DBWrapper.Connect(SQL_Queries.strQuery_1,environment);
		while (result.next())
		{	
			 totalFlights = result.getString("count(*)");
			
			System.out.println(totalFlights);
			HtmlReportUtil.stepInfo("<b style=\"color:green;\">=========Total Flights in DB or UI for GMR-Hyderabad = "+ totalFlights +" =============</b>");
		}
		ResultSet result2 = DBWrapper.Connect(SQL_Queries.strQuery_5,environment);
		while (result2.next())
		{	
			String hasSensorATA = result2.getString("has_data");
			 countSensorATA = result2.getString("count(*)");
			if(hasSensorATA.contains("yes"))
			{
				System.out.println(countSensorATA);
				HtmlReportUtil.stepInfo("<b style=\"color:blue;\">=========Total flights having Sensor_ATA for GMR-Hyderabad = "+ countSensorATA +" =============</b>");
			}
		}	
		ResultSet result3 = DBWrapper.Connect(SQL_Queries.strQuery_6,environment);
		while (result3.next())
		{	
			String hasSensorATD = result3.getString("has_data");
			 countSensorATD = result3.getString("count(*)");
			if(hasSensorATD.contains("yes"))
			{
				System.out.println(countSensorATD);
				HtmlReportUtil.stepInfo("<b style=\"color:blue;\">=========Total flights having Sensor_ATD for GMR-Hyderabad = "+ countSensorATD +" =============</b>");
			}
		}	
		System.out.println("|Date | Location  |	Total No. of Flights |	No.of Flights with Sensor ATA |	No.of Flights with Sensor ATD|");
		System.out.println("|Date | Location  |	  "+totalFlights+"   |	    "+countSensorATA+"        |        "+countSensorATD+"    | ");
		
		DBWrapper.dbConnectionClose();
		String text=
		         
		        		 "<html> <p>Hi Team, <br> Please find automation report for Sensor_ATA and Sensor_ATD and report for the below scenario: <br>"
       		 + " 'Sensor-ATA < ONBL< OFBL < Sensor-ATD'  <br><b> Hyderabad user: </b> </p>"
		        		 + "<table width='100%' border='1' align='center'>"
		                + "<tr  bgColor=#00b3b3 align='center'>"
		                + "<td><b>Date<b></td>"
		                + "<td><b>Location<b></td>"
		                + "<td><b>Total No. of Flights <b></td>"
		                + "<td><b>No.of Flights with Sensor ATA<b></td>"
		                + "<td><b> No.of Flights with Sensor ATD<b></td>"
		                + "</tr>";
		hydReport=text+"<tr align='center'>"
				 +"<td>" + SQL_Queries.yesterDate() + "</td>"
				  +"<td>" + "Hyderabad" + "</td>"
		            +"<td>" + totalFlights + "</td>"
                    + "<td>" + countSensorATA + "</td>"
                        +"<td>" + countSensorATD + "</td>"
		                +"</tr> <br><br> </html>";
	}
//////////////////////////////////////////////////////////////////////////////////////////////
		public static void validateSensorDataForDelhi(String environment) throws Exception{
			String totalFlights=null;
			String countSensorATA=null;
			String countSensorATD =null;
			
			ResultSet result = DBWrapper.Connect(SQL_Queries.strQuery_7,environment);
			while (result.next())
			{	
					 totalFlights = result.getString("count(*)");
					System.out.println(totalFlights);
					HtmlReportUtil.stepInfo("<b style=\"color:green;\">=========Total Flights in DB or UI for GMR-Delhi = "+ totalFlights +" =============</b>");
			}
			ResultSet result2 = DBWrapper.Connect(SQL_Queries.strQuery_8,environment);
			while (result2.next())
			{	
				String hasSensorATA = result2.getString("has_data");
				 countSensorATA = result2.getString("count(*)");
				if(hasSensorATA.contains("yes"))
				{
					System.out.println(countSensorATA);
					HtmlReportUtil.stepInfo("<b style=\"color:blue;\">=========Total flights having Sensor_ATA for GMR-Delhi = "+ countSensorATA +" =============</b>");
				}
			}	
			ResultSet result3 = DBWrapper.Connect(SQL_Queries.strQuery_9,environment);
			while (result3.next())
				{	
				String hasSensorATD = result3.getString("has_data");
				 countSensorATD = result3.getString("count(*)");
				if(hasSensorATD.contains("yes"))
				{
					System.out.println(countSensorATD);
					HtmlReportUtil.stepInfo("<b style=\"color:blue;\">=========Total flights having Sensor_ATD for GMR-Delhi = "+ countSensorATD +" =============</b>");
				}
				}	
			
			DBWrapper.dbConnectionClose();
			System.out.println("|Date | Location  |	Total No. of Flights |	No.of Flights with Sensor ATA |	No.of Flights with Sensor ATD|");
			System.out.println("|Date | Location  |	  "+totalFlights+"   |	    "+countSensorATA+"        |        "+countSensorATD+"    | ");
			
			delhiReport=hydReport+"<tr align='center'>"
					 +"<td>" + SQL_Queries.yesterDate() + "</td>"
					  +"<td>" + "Delhi" + "</td>"
			            +"<td>" + totalFlights + "</td>"
	                     + "<td>" + countSensorATA + "</td>"
	                         +"<td>" + countSensorATD + "</td>"
			                +"</tr></table>"
			                + " <br><br> Thnak You, <br> Automation Team </html>";
			}

		public static void validateOnBlockTimeAndOffBlockTimeForHyderabad(String environment) throws Exception{
			String totalFlights= null;
//			String countSensorATA= null;
//			String countSensorATD= null;
			 
			
				
				ResultSet result = DBWrapper.Connect(SQL_Queries.strQuery_10,environment);
				while (result.next())
				{	
					 totalFlights = result.getString("count(*)");
					
					System.out.println(totalFlights);
					//HtmlReportUtil.stepInfo("<b style=\"color:green;\">=========Total Flights in DB or UI for GMR-Hyderabad = "+ totalFlights +" =============</b>");
				}
				ResultSet result2 = DBWrapper.Connect(SQL_Queries.strQuery_11,environment);
				while (result2.next())
				{	
					String hasSensorATA = result2.getString("has_data");
					 countOnBlockTime = result2.getString("count(*)");
					if(hasSensorATA.contains("yes"))
					{
						System.out.println(countOnBlockTime);
						//HtmlReportUtil.stepInfo("<b style=\"color:blue;\">=========Total flights having On_block_time for GMR-Hyderabad = "+ countOnBlockTime +" =============</b>");
					}
				}	
				ResultSet result3 = DBWrapper.Connect(SQL_Queries.strQuery_12,environment);
				while (result3.next())
				{	
					String hasSensorATD = result3.getString("has_data");
					 countOffBlockTime = result3.getString("count(*)");
					if(hasSensorATD.contains("yes"))
					{
						System.out.println(countOffBlockTime);
						//HtmlReportUtil.stepInfo("<b style=\"color:blue;\">=========Total flights having Off_block_time for GMR-Hyderabad = "+ countOffBlockTime +" =============</b>");
					}
				}	
				System.out.println("|Date | Location  |	Total No. of Flights |	No.of Flights with Sensor ATA |	No.of Flights with Sensor ATD|");
				System.out.println("|Date | Location  |	  "+totalFlights+"   |	    "+countOnBlockTime+"        |        "+countOffBlockTime+"    | ");
				
				DBWrapper.dbConnectionClose();
}
//////////////////////////////////////////////////////////////////////////////////////////////
		
		public static void scheduledAndSensorATAForHyderabad(String environment) throws Exception{
		
			int totalScheduledArrival = 0;
			int nullSensorATA = 0;
			int notNullSensorATA =0;
			int totalSensorATA= 0;
			int totalArrivedWithSensorATA = 0;
			String str_LogID = null;
			String str_flight_NumberArrival= null;
			String str_flight_NumberDeparture= null;
			String str_flight_Sensor_ATA = null;
					
			ArrayList<String> sensorATA_NullList = new ArrayList<String>();
			
			
			ResultSet result = DBWrapper.Connect(SQL_Queries.strQuery_017,environment);
			while (result.next())
			{				
				totalScheduledArrival = result.getInt("count(*)");
				System.out.println(totalScheduledArrival);
				HtmlReportUtil.stepInfo("<b style=\"color:purple;\"> Airport : GMR_HYD :</b> <b style=\"color:green;\">Total No. of flights Scheduled Arrival (based on STA or Mediator-STA) = "+ totalScheduledArrival +"</b>");
				//HtmlReportUtil.stepInfo("<b style=\"color:green;\">Total Flights Scheduled (Arrival) = "+ totalScheduledArrival +"</b>");
			}
			ResultSet result2 = DBWrapper.Connect(SQL_Queries.strQuery_018,environment);
			while (result2.next())
			{				
				notNullSensorATA = result2.getInt("count(*)");
				System.out.println(notNullSensorATA);
				HtmlReportUtil.stepInfo("<b style=\"color:green;\">No. of flights detected by Sensor = "+ notNullSensorATA +"</b>");
			}
			ResultSet result3 = DBWrapper.Connect(SQL_Queries.strQuery_019,environment);
			while (result3.next())
			{				
				nullSensorATA = result3.getInt("count(*)");
				System.out.println(nullSensorATA);
				HtmlReportUtil.stepInfo("<b style=\"color:red;\">No. of flights NOT detected by Sensor = "+ nullSensorATA +"</b>");
			}
					
			ResultSet result4 = DBWrapper.Connect(SQL_Queries.strQuery_020,environment);
			while (result3.next())
			{	
				str_LogID = result4.getString("logid");
				
				str_flight_NumberArrival = result4.getString("flightnumber_arrival");
				
				str_flight_NumberDeparture = result4.getString("flightnumber_departure");
				
				str_flight_Sensor_ATA = result4.getString("sensor_ata");
				
				sensorATA_NullList.add(str_LogID);
							
				if(sensorATA_NullList.size()>0){
					System.out.println("In for loop");
					HtmlReportUtil.stepInfo("LogId:  "+ str_LogID +"||  FlightnoArrival:  "+ str_flight_NumberArrival +"|| FlightnoDeparture:  "+ str_flight_NumberDeparture +"|| Sensor-ATA:  "+ str_flight_Sensor_ATA +" |");	
				}
					else{
						//HtmlReportUtil.stepInfo("<b style=\"color:blue;\">=========Total Flights for which Sensor_ATA is null = "+totalArrivedWithSensorATA +" =============</b>");	
					}
			}
			
				DBWrapper.dbConnectionClose();	
				
		}
				
//////////////////////////////////////////////////////////////////////////////////////////////
				
			public static void scheduledAndSensorATDForHyderabad(String environment) throws Exception{
				
				int totalScheduledDeparture = 0;
				int nullSensorATD =0;
				int notNullSensorATD =0;
				int totalDepartedWithSensorATD = 0;
				String str_LogID = null;
				String str_flight_NumberArrival= null;
				String str_flight_NumberDeparture= null;
				String str_flight_Sensor_ATD = null;
				
				ArrayList<String> sensorATD_NullList = new ArrayList<String>();
			
				ResultSet result = DBWrapper.Connect(SQL_Queries.strQuery_021,environment);
				while (result.next())
				{				
					totalScheduledDeparture = result.getInt("count(*)");
					System.out.println(totalScheduledDeparture);
					HtmlReportUtil.stepInfo("<b style=\"color:purple;\"> Airport : GMR_HYD :</b> <b style=\"color:green;\">Total No. of flights Scheduled Departure(based on STD or Mediator-STD) = "+ totalScheduledDeparture +"</b>");
				}
				
				ResultSet result2 = DBWrapper.Connect(SQL_Queries.strQuery_022,environment);
				while (result2.next())
				{				
					notNullSensorATD = result2.getInt("count(*)");
					System.out.println(notNullSensorATD);
					HtmlReportUtil.stepInfo("<b style=\"color:green;\">No. of flights detected by Sensor = "+ notNullSensorATD +" </b>");
				}
				
				ResultSet result3 = DBWrapper.Connect(SQL_Queries.strQuery_023,environment);
				while (result3.next())
				{				
					nullSensorATD = result3.getInt("count(*)");
					System.out.println(nullSensorATD);
					HtmlReportUtil.stepInfo("<b style=\"color:red;\">No. of flights NOT detected by Sensor = "+ nullSensorATD +" </b>");
				}
				
				ResultSet result4 = DBWrapper.Connect(SQL_Queries.strQuery_024,environment);
				while (result4.next())
				{	
					str_LogID = result4.getString("logid");
					
					str_flight_NumberArrival = result4.getString("flightnumber_arrival");
					
					str_flight_NumberDeparture = result4.getString("flightnumber_departure");
					
					str_flight_Sensor_ATD = result4.getString("sensor_atd");
					
					sensorATD_NullList.add(str_LogID);
					totalDepartedWithSensorATD=  sensorATD_NullList.size(); 
					System.out.println(totalDepartedWithSensorATD);
					
					if(sensorATD_NullList.size()>0){
						System.out.println("In If condition");
						//HtmlReportUtil.stepInfo("| LogId | flight_NumberArrival | flight_NumberDeparture | Sensor_ATD|");
						System.out.println("In for condition");
						HtmlReportUtil.stepInfo("LogId:  "+ str_LogID +"||  FlightnoArrival:  "+ str_flight_NumberArrival +"|| FlightnoDeparture:  "+ str_flight_NumberDeparture +"|| Sensor-ATD:  "+ str_flight_Sensor_ATD +" |");	
					}
						else{
							System.out.println("In else condition");
							//HtmlReportUtil.stepInfo("<b style=\"color:blue;\">=========Total Flights for which Sensor_ATD is null = "+totalDepartedWithSensorATD +" =============</b>");	
						}
				}
				
			DBWrapper.dbConnectionClose();	
			
		}
			
//////////////////////////////////////////////////////////////////////////////////////////////
			
			public static void scheduledAndSensorATAForDelhi(String environment) throws Exception{
				int totalScheduledArrival = 0;
				int nullSensorATA = 0;
				int notNullSensorATA =0;
				int totalSensorATA= 0;
				int totalArrivedWithSensorATA = 0;
				String str_LogID = null;
				String str_flight_NumberArrival= null;
				String str_flight_NumberDeparture= null;
				String str_flight_Sensor_ATA = null;
						
				ArrayList<String> sensorATA_NullList = new ArrayList<String>();
				
				
				ResultSet result = DBWrapper.Connect(SQL_Queries.strQuery_025,environment);
				while (result.next())
				{				
					totalScheduledArrival = result.getInt("count(*)");
					System.out.println(totalScheduledArrival);
					HtmlReportUtil.stepInfo("<b style=\"color:purple;\"> Airport : Delhi :</b> <b style=\"color:green;\">Total No. of flights Scheduled Arrival (based on STA or Mediator-STA) = "+ totalScheduledArrival +"</b>");
					//HtmlReportUtil.stepInfo("<b style=\"color:green;\">Total Flights Scheduled (Arrival) = "+ totalScheduledArrival +"</b>");
				}
				ResultSet result2 = DBWrapper.Connect(SQL_Queries.strQuery_026,environment);
				while (result2.next())
				{				
					notNullSensorATA = result2.getInt("count(*)");
					System.out.println(notNullSensorATA);
					HtmlReportUtil.stepInfo("<b style=\"color:green;\">No. of flights detected by Sensor = "+ notNullSensorATA +"</b>");
				}
				ResultSet result3 = DBWrapper.Connect(SQL_Queries.strQuery_027,environment);
				while (result3.next())
				{				
					nullSensorATA = result3.getInt("count(*)");
					System.out.println(nullSensorATA);
					HtmlReportUtil.stepInfo("<b style=\"color:red;\">No. of flights NOT detected by Sensor = "+ nullSensorATA +"</b>");
				}
						
				ResultSet result4 = DBWrapper.Connect(SQL_Queries.strQuery_028,environment);
				while (result3.next())
				{	
					str_LogID = result4.getString("logid");
					
					str_flight_NumberArrival = result4.getString("flightnumber_arrival");
					
					str_flight_NumberDeparture = result4.getString("flightnumber_departure");
					
					str_flight_Sensor_ATA = result4.getString("sensor_ata");
					
					sensorATA_NullList.add(str_LogID);
								
					if(sensorATA_NullList.size()>0){
						System.out.println("In for loop");
						HtmlReportUtil.stepInfo("LogId:  "+ str_LogID +"||  FlightnoArrival:  "+ str_flight_NumberArrival +"|| FlightnoDeparture:  "+ str_flight_NumberDeparture +"|| Sensor-ATA:  "+ str_flight_Sensor_ATA +" |");	
					}
						else{
							//HtmlReportUtil.stepInfo("<b style=\"color:blue;\">=========Total Flights for which Sensor_ATA is null = "+totalArrivedWithSensorATA +" =============</b>");	
						}
				}

			DBWrapper.dbConnectionClose();			
}

//////////////////////////////////////////////////////////////////////////////////////////////
			
public static void scheduledAndSensorATDForDelhi(String environment) throws Exception{

	int totalScheduledDeparture = 0;
	int nullSensorATD =0;
	int notNullSensorATD =0;
	int totalDepartedWithSensorATD = 0;
	String str_LogID = null;
	String str_flight_NumberArrival= null;
	String str_flight_NumberDeparture= null;
	String str_flight_Sensor_ATD = null;
	
	ArrayList<String> sensorATD_NullList = new ArrayList<String>();

	ResultSet result = DBWrapper.Connect(SQL_Queries.strQuery_029,environment);
	while (result.next())
	{				
		totalScheduledDeparture = result.getInt("count(*)");
		System.out.println(totalScheduledDeparture);
		HtmlReportUtil.stepInfo("<b style=\"color:purple;\"> Airport : GMR_HYD :</b> <b style=\"color:green;\">Total No. of flights Scheduled Departure(based on STD or Mediator-STD) = "+ totalScheduledDeparture +"</b>");
	}
	
	ResultSet result2 = DBWrapper.Connect(SQL_Queries.strQuery_030,environment);
	while (result2.next())
	{				
		notNullSensorATD = result2.getInt("count(*)");
		System.out.println(notNullSensorATD);
		HtmlReportUtil.stepInfo("<b style=\"color:green;\">No. of flights detected by Sensor = "+ notNullSensorATD +" </b>");
	}
	
	ResultSet result3 = DBWrapper.Connect(SQL_Queries.strQuery_031,environment);
	while (result3.next())
	{				
		nullSensorATD = result3.getInt("count(*)");
		System.out.println(nullSensorATD);
		HtmlReportUtil.stepInfo("<b style=\"color:red;\">No. of flights NOT detected by Sensor = "+ nullSensorATD +" </b>");
	}
	
	ResultSet result4 = DBWrapper.Connect(SQL_Queries.strQuery_032,environment);
	while (result4.next())
	{	
		str_LogID = result4.getString("logid");
		
		str_flight_NumberArrival = result4.getString("flightnumber_arrival");
		
		str_flight_NumberDeparture = result4.getString("flightnumber_departure");
		
		str_flight_Sensor_ATD = result4.getString("sensor_atd");
		
		sensorATD_NullList.add(str_LogID);
		totalDepartedWithSensorATD=  sensorATD_NullList.size(); 
		System.out.println(totalDepartedWithSensorATD);
		
		if(sensorATD_NullList.size()>0){
			System.out.println("In If condition");
			//HtmlReportUtil.stepInfo("| LogId | flight_NumberArrival | flight_NumberDeparture | Sensor_ATD|");
			System.out.println("In for condition");
			HtmlReportUtil.stepInfo("LogId:  "+ str_LogID +"||  FlightnoArrival:  "+ str_flight_NumberArrival +"|| FlightnoDeparture:  "+ str_flight_NumberDeparture +"|| Sensor-ATD:  "+ str_flight_Sensor_ATD +" |");	
		}
			else{
				System.out.println("In else condition");
				//HtmlReportUtil.stepInfo("<b style=\"color:blue;\">=========Total Flights for which Sensor_ATD is null = "+totalDepartedWithSensorATD +" =============</b>");	
			}
	}
DBWrapper.dbConnectionClose();			
}
}
