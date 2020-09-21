package enh.web.pages;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import enh.db.pages.SQL_Queries;
import utilities.HtmlReportUtil;
import utilities.KeywordUtil;

public class AV_2307_UI_CELEBI_SensorATA_OnBlock_OffBlock_SensorATD_Validation extends KeywordUtil{
	
	private static By menu = By.xpath("//mat-icon[contains(.,'menu')] ");
	private static By flightInfo = By.xpath("//a[contains(.,'Flight Info')]");
	private static By turnaroundList = By.xpath("//a[contains(.,'Turnaround List')]");
	private static By departed = By.xpath("//*[@class='mat-tab-label-content'][contains(.,'Departed')]");
	
	static JavascriptExecutor js = (JavascriptExecutor) getDriver();
	
	public static int DepatedFlightsCount=0;
	public static int missingFlight =0;
	public static int landingTimeIsNull=0;
	public static int landingTimeIsNotNull =0;
	
	public static int onBlockTimeIsNull =0;
	public static int onBlockTimeIsNotNull =0;
	
	public static int offBlockTimeIsNull =0;
	public static int offBlockTimeIsNotNull =0;
	
	public static int airborneTimeIsNull =0;
	public static int airborneTimeIsNotNull =0;
	public static int flightsWithCorrectsequence=0;
	public static int flightsNotInCorrectSequence=0;
	
	
	
	public static StringBuilder email_report_ATA_OnBlock_OffBlock_ATD_For_Celebi_UI1 = new StringBuilder();
	public static StringBuilder email_report_ATA_OnBlock_OffBlock_ATD_For_Celebi_UI2 = new StringBuilder();
	public static StringBuilder email_report_ATA_OnBlock_OffBlock_ATD_For_Celebi_UI3 = new StringBuilder();
	public static StringBuilder email_report_ATA_OnBlock_OffBlock_ATD_For_Celebi_UI4 = new StringBuilder();
	public static StringBuilder email_report_ATA_OnBlock_OffBlock_ATD_For_Celebi_UI5 = new StringBuilder();
	public static StringBuilder email_report_ATA_OnBlock_OffBlock_ATD_For_Celebi_UI6 = new StringBuilder();
	
	/**********************************************************************************************************
	 * Method Name: Verify SensorATA < OnBlock < OffBlock < SensorATD timestamps
	 * Description : To verify SensorATA, OnBlock, OffBlock and SensorATD timestamps displayed on UI
	 * Change History:
	 *             Date                     Created By          Change Description        Code Review and date
	 *             ****                     **********            ****************          *******************
	 *         20/Dec/2019                   Padmaja	                                   AD/-/Dec/2019                                      
	 * @throws Exception 
	 ***********************************************************************************************************/
	public static void verifySensorATA_OnBlock_OffBlock_SensorATD_timestamps(String airportName, String userName) throws Exception
	{
		 email_report_ATA_OnBlock_OffBlock_ATD_For_Celebi_UI2.append("<br><br>");
		 email_report_ATA_OnBlock_OffBlock_ATD_For_Celebi_UI2.append("<style>table#t01, th, td {border: 1px solid black;border-collapse: collapse;}table#t01 th{background-color:#80e5ff; } table#t01 tr:nth-child(even) {background-color: #f2f2f2;} table#t01 tr:nth-child(odd) { background-color: #DFEDEC;}table#t01 th, td {padding: 5px;}table#t01 th,td {text-align: center;} table#t01 caption {color: #008ae6;font-weight: bold;}</style>");
		 email_report_ATA_OnBlock_OffBlock_ATD_For_Celebi_UI2.append("<table style=\"width:100%\" id=\"t01\"><caption> Total Flights without Landing time </caption><tr>"
													+ "<th style=\"width:15%\"><b>logID</b></th> "
													+ "<th style=\"width:15%\"><b>FlightNumber_Arrival</b></th>"
													+ "<th style=\"width:15%\"><b>FlightNumber_Departure</b></th>"
											 		+ "<th style=\"width:15%\"><b>On_BlocK_Time</b></th>"
											 		+ "<th style=\"width:20%\"><b>Off_BlocK_Time</b></th>"
											 		+ "<th style=\"width:20%\"><b>Airborne</b></th>"
											 		+ " </tr>");
		 
		 email_report_ATA_OnBlock_OffBlock_ATD_For_Celebi_UI3.append("<br><br>");
		 email_report_ATA_OnBlock_OffBlock_ATD_For_Celebi_UI3.append("<style>table#t01, th, td {border: 1px solid black;border-collapse: collapse;}table#t01 th{background-color:#80e5ff; } table#t01 tr:nth-child(even) {background-color: #f2f2f2;} table#t01 tr:nth-child(odd) { background-color: #DFEDEC;}table#t01 th, td {padding: 5px;}table#t01 th,td {text-align: center;} table#t01 caption {color: #008ae6;font-weight: bold;}</style>");
		 email_report_ATA_OnBlock_OffBlock_ATD_For_Celebi_UI3.append("<table style=\"width:100%\" id=\"t01\"><caption> Total Flights without OnBlock time </caption><tr>"
													+ "<th style=\"width:15%\"><b>logID</b></th> "
													+ "<th style=\"width:15%\"><b>FlightNumber_Arrival</b></th>"
													+ "<th style=\"width:15%\"><b>FlightNumber_Departure</b></th>"
											 		+ "<th style=\"width:15%\"><b>Landing</b></th>"
											 		+ "<th style=\"width:20%\"><b>Off_BlocK_Time</b></th>"
											 		+ "<th style=\"width:20%\"><b>Airborne</b></th>"
											 		+ " </tr>");
		
		 email_report_ATA_OnBlock_OffBlock_ATD_For_Celebi_UI4.append("<br><br>");
		 email_report_ATA_OnBlock_OffBlock_ATD_For_Celebi_UI4.append("<style>table#t01, th, td {border: 1px solid black;border-collapse: collapse;}table#t01 th{background-color:#80e5ff; } table#t01 tr:nth-child(even) {background-color: #f2f2f2;} table#t01 tr:nth-child(odd) { background-color: #DFEDEC;}table#t01 th, td {padding: 5px;}table#t01 th,td {text-align: center;} table#t01 caption {color: #008ae6;font-weight: bold;}</style>");
		 email_report_ATA_OnBlock_OffBlock_ATD_For_Celebi_UI4.append("<table style=\"width:100%\" id=\"t01\"><caption> Total Flights without OffBlock time </caption><tr>"
													+ "<th style=\"width:15%\"><b>logID</b></th> "
													+ "<th style=\"width:15%\"><b>FlightNumber_Arrival</b></th>"
													+ "<th style=\"width:15%\"><b>FlightNumber_Departure</b></th>"
											 		+ "<th style=\"width:15%\"><b>Landing</b></th>"
											 		+ "<th style=\"width:20%\"><b>On_BlocK_Time</b></th>"
											 		+ "<th style=\"width:20%\"><b>Airborne</b></th>"
											 		+ " </tr>");
		 
		 email_report_ATA_OnBlock_OffBlock_ATD_For_Celebi_UI5.append("<br><br>");
		 email_report_ATA_OnBlock_OffBlock_ATD_For_Celebi_UI5.append("<style>table#t01, th, td {border: 1px solid black;border-collapse: collapse;}table#t01 th{background-color:#80e5ff; } table#t01 tr:nth-child(even) {background-color: #f2f2f2;} table#t01 tr:nth-child(odd) { background-color: #DFEDEC;}table#t01 th, td {padding: 5px;}table#t01 th,td {text-align: center;} table#t01 caption {color: #008ae6;font-weight: bold;}</style>");
		 email_report_ATA_OnBlock_OffBlock_ATD_For_Celebi_UI5.append("<table style=\"width:100%\" id=\"t01\"><caption> Total Flights without Airborne time </caption><tr>"
													+ "<th style=\"width:15%\"><b>logID</b></th> "
													+ "<th style=\"width:15%\"><b>FlightNumber_Arrival</b></th>"
													+ "<th style=\"width:15%\"><b>FlightNumber_Departure</b></th>"
											 		+ "<th style=\"width:15%\"><b>Landing</b></th>"
											 		+ "<th style=\"width:20%\"><b>On_BlocK_Time</b></th>"
											 		+ "<th style=\"width:20%\"><b>Off_BlocK_Time</b></th>"
											 		+ " </tr>");
		 
		 email_report_ATA_OnBlock_OffBlock_ATD_For_Celebi_UI6.append("<br><br>");
		 email_report_ATA_OnBlock_OffBlock_ATD_For_Celebi_UI6.append("<style>table#t01, th, td {border: 1px solid black;border-collapse: collapse;}table#t01 th{background-color:#80e5ff; } table#t01 tr:nth-child(even) {background-color: #f2f2f2;} table#t01 tr:nth-child(odd) { background-color: #DFEDEC;}table#t01 th, td {padding: 5px;}table#t01 th,td {text-align: center;} table#t01 caption {color: #008ae6;font-weight: bold;}</style>");
		 email_report_ATA_OnBlock_OffBlock_ATD_For_Celebi_UI6.append("<table style=\"width:100%\" id=\"t01\"><caption> Total Flights NOT in sequence like Landing < On-Block < Off-Block < Airborne </caption><tr>"
													+ "<th style=\"width:10%\"><b>logID</b></th> "
													+ "<th style=\"width:15%\"><b>FlightNumber_Arrival</b></th>"
													+ "<th style=\"width:15%\"><b>FlightNumber_Departure</b></th>"
											 		+ "<th style=\"width:15%\"><b>Landing</b></th>"
											 		+ "<th style=\"width:15%\"><b>On_BlocK_Time</b></th>"
											 		+ "<th style=\"width:15%\"><b>Off_BlocK_Time</b></th>"
											 		+ "<th style=\"width:15%\"><b>Airborne</b></th>"
											 		+ " </tr>");
		 
		String userNamee=getElementText(By.xpath("html/body/app-root/div/app-topnav/mat-toolbar/div[4]/div[2]"));
		System.out.println(userNamee);
		HtmlReportUtil.stepInfo("<b style=\"color:brown;\"> Reports for "+userNamee+" </b>");
		delay(10000);
		click(menu);
		delay(2000);
		click(flightInfo);
		delay(2000);
		click(turnaroundList);
		delay(2000);
		click(departed);
		delay(2000);
		clearInput(By.xpath("//input[@name='date']"));
		delay(2000);
		inputText(By.xpath("//input[@name='date']"), SQL_Queries.yesterDate());
		delay(2000);
		click(By.xpath("//mat-icon[contains(.,'search')]"));
		delay(10000);
		String str_DepatedFlights = getElementText(departed);
		String str_DepatedFlightsCount = str_DepatedFlights.replaceAll("[^0-9]", "");
		DepatedFlightsCount=Integer.parseInt(str_DepatedFlightsCount);
		System.out.println(str_DepatedFlightsCount);
		for(int i=1;i<=DepatedFlightsCount;i++)
		{
			try {

				js.executeScript("arguments[0].scrollIntoView();",getWebElement(By.xpath("//div[@id='selectedCard']["+i+"]")));
				
				delay(2000);

				click(By.xpath("//div[@id='selectedCard']["+i+"]"));
				delay(2000);
				String flightInfoText=getElementText(By.xpath("//div[@id='selectedCard']["+i+"]"));
								
				hoverElement(By.xpath("//div[@id='selectedCard']["+i+"]//div[@class= 'arrival-time-col']/descendant::div[2]"));
				delay(2000);
				String landingDateAndTime = getElementText(By.xpath("//div[@id='selectedCard']["+i+"]//div[@class= 'arrival-time-col']/descendant::div[3]/p[5]"));
				System.out.println(landingDateAndTime);
				String landingTime= landingDateAndTime.replace("Landed: ", "");
				System.out.println("landing time:" +landingTime);
				
				String onBlockDateAndTime = getElementText(By.xpath("//div[@id='selectedCard']["+i+"]//div[@class= 'arrival-time-col']/descendant::div[3]/p[6]/span"));
				System.out.println(onBlockDateAndTime); 
				
				hoverElement(By.xpath("//div[@id='selectedCard']["+i+"]//div[@class= 'departure-time-col']"));
				delay(2000);
				String offBlockDateAndTime = getElementText(By.xpath("//div[@id='selectedCard']["+i+"]//div[@class= 'departure-time-col']/descendant::div[3]/p[3]/span"));
				System.out.println("OFB:" +offBlockDateAndTime); 
				
				String airborneDateAndTime = getElementText(By.xpath("//div[@id='selectedCard']["+i+"]//div[@class= 'departure-time-col']/descendant::div[3]/p[4]"));
				String airborneTime= airborneDateAndTime.replace("Airborne: ", "");
				System.out.println("Airborne time:" +airborneTime);
				
				if(landingTime.isEmpty()) 
				{
					String flightNumber =getElementText(By.xpath("//div[@id='selectedCard']["+i+"]/descendant::p[1]"));
					String flightNum[] = flightNumber.split("-");
					String flightNumArrival = flightNum[0];
					String flightNumDeparture = flightNum[1];
					hoverElement(By.xpath("//div[@id='selectedCard'][1]/descendant::div[4]"));
					delay(2000);
					String logId= getWebElement(By.xpath("//div[@id='selectedCard']["+i+"]/descendant::div[4]")).getAttribute("title");
					//System.out.println(logId);
					//System.out.println("Landing not available Flight Info:" +flightNumber+ " "+landingTime+ " " +onBlockDateAndTime+ " " +offBlockDateAndTime+ " " +airborneTime);
					landingTimeIsNull = landingTimeIsNull +1;
					
					email_report_ATA_OnBlock_OffBlock_ATD_For_Celebi_UI2.append(" <tr> <td><b style=\"color:red;\">"+logId+"</b></td> "
							+ "<td><b style=\"color:red;\">"+flightNumArrival+"</b></td>"
					 		+ " <td> <b style=\"color:red;\">"+flightNumDeparture+"</b></td>"
					 		+ " <td><b style=\"color:red;\">"+onBlockDateAndTime+"</b></td>"
					 		+ " <td><b style=\"color:red;\">"+offBlockDateAndTime+"</b></td> "
					 		+ "<td><b style=\"color:red;\">"+airborneTime+"</b></td></tr>");
				}
				else {
					landingTimeIsNotNull = landingTimeIsNotNull + 1;
					}
				
				if(onBlockDateAndTime.isEmpty()) {
					String flightNumber =getElementText(By.xpath("//div[@id='selectedCard']["+i+"]/descendant::p[1]"));
					String flightNum[] = flightNumber.split("-");
					String flightNumArrival = flightNum[0];
					String flightNumDeparture = flightNum[1];
					hoverElement(By.xpath("//div[@id='selectedCard'][1]/descendant::div[4]"));
					delay(2000);
					String logId= getWebElement(By.xpath("//div[@id='selectedCard']["+i+"]/descendant::div[4]")).getAttribute("title");
					//System.out.println("onblock not available Flight Info:" +flightNumber+ " "+landingTime+ " " +onBlockDateAndTime+ " " +offBlockDateAndTime+ " " +airborneTime);	
					
					onBlockTimeIsNull= onBlockTimeIsNull +1;
					
					email_report_ATA_OnBlock_OffBlock_ATD_For_Celebi_UI3.append(" <tr> <td><b style=\"color:red;\">"+logId+"</b></td> "
							+ "<td><b style=\"color:red;\">"+flightNumArrival+"</b></td>"
					 		+ " <td> <b style=\"color:red;\">"+flightNumDeparture+"</b></td>"
					 		+ " <td><b style=\"color:red;\">"+landingTime+"</b></td>"
					 		+ " <td><b style=\"color:red;\">"+offBlockDateAndTime+"</b></td> "
					 		+ "<td><b style=\"color:red;\">"+airborneTime+"</b></td></tr>");
				}
				else {
					onBlockTimeIsNotNull = onBlockTimeIsNotNull + 1;
				}				
				
				if(offBlockDateAndTime.isEmpty()) {
					String flightNumber =getElementText(By.xpath("//div[@id='selectedCard']["+i+"]/descendant::p[1]"));
					String flightNum[] = flightNumber.split("-");
					String flightNumArrival = flightNum[0];
					String flightNumDeparture = flightNum[1];
					System.out.println("offblock not available Flight Info:" +flightNumber+ " "+landingTime+ " " +onBlockDateAndTime+ " " +offBlockDateAndTime+ " " +airborneTime);
					hoverElement(By.xpath("//div[@id='selectedCard'][1]/descendant::div[4]"));
					delay(2000);
					String logId= getWebElement(By.xpath("//div[@id='selectedCard']["+i+"]/descendant::div[4]")).getAttribute("title");
					
					offBlockTimeIsNull = offBlockTimeIsNull+1;
					
					email_report_ATA_OnBlock_OffBlock_ATD_For_Celebi_UI4.append(" <tr> <td><b style=\"color:red;\">"+logId+"</b></td> "
							+ "<td><b style=\"color:red;\">"+flightNumArrival+"</b></td>"
					 		+ " <td> <b style=\"color:red;\">"+flightNumDeparture+"</b></td>"
					 		+ " <td><b style=\"color:red;\">"+landingTime+"</b></td>"
					 		+ " <td><b style=\"color:red;\">"+onBlockDateAndTime+"</b></td> "
					 		+ "<td><b style=\"color:red;\">"+airborneTime+"</b></td></tr>");
				}
				else {
					offBlockTimeIsNotNull = offBlockTimeIsNotNull + 1;					
				}				
				
				if(airborneTime.isEmpty()) {
					String flightNumber =getElementText(By.xpath("//div[@id='selectedCard']["+i+"]/descendant::p[1]"));
					String flightNum[] = flightNumber.split("-");
					String flightNumArrival = flightNum[0];
					String flightNumDeparture = flightNum[1];
					System.out.println("airborne not available Flight Info:" +flightNumber+ " "+landingTime+ " " +onBlockDateAndTime+ " " +offBlockDateAndTime+ " " +airborneTime);
					hoverElement(By.xpath("//div[@id='selectedCard'][1]/descendant::div[4]"));
					delay(2000);
					String logId= getWebElement(By.xpath("//div[@id='selectedCard']["+i+"]/descendant::div[4]")).getAttribute("title");
					
					airborneTimeIsNull = airborneTimeIsNull+1;
					email_report_ATA_OnBlock_OffBlock_ATD_For_Celebi_UI5.append(" <tr> <td><b style=\"color:red;\">"+logId+"</b></td> "
							+ "<td><b style=\"color:red;\">"+flightNumArrival+"</b></td>"
					 		+ " <td> <b style=\"color:red;\">"+flightNumDeparture+"</b></td>"
					 		+ " <td><b style=\"color:red;\">"+landingTime+"</b></td>"
					 		+ " <td><b style=\"color:red;\">"+onBlockDateAndTime+"</b></td> "
					 		+ "<td><b style=\"color:red;\">"+offBlockDateAndTime+"</b></td></tr>");
				}				
				else {
					airborneTimeIsNotNull = airborneTimeIsNotNull + 1;
				}
				
				if(!landingTime.isEmpty() && (!onBlockDateAndTime.isEmpty()) && (!offBlockDateAndTime.isEmpty()) && (!airborneTime.isEmpty()))
				{
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
					Date landingCaptured = sdf.parse(landingTime);
					Date onBlockCaptured = sdf.parse(onBlockDateAndTime);
					Date offBlockCaptured = sdf.parse(offBlockDateAndTime);
					Date airborneCaptured = sdf.parse(airborneTime);
				if(landingCaptured.before(onBlockCaptured) && onBlockCaptured.before(offBlockCaptured) && offBlockCaptured.before(airborneCaptured)) 
				{
					System.out.println("correct sequence");
					flightsWithCorrectsequence = flightsWithCorrectsequence +1;
				}
				else {
					System.out.println("not in sequence");
					flightsNotInCorrectSequence = flightsNotInCorrectSequence + 1;
					String flightNumber =getElementText(By.xpath("//div[@id='selectedCard']["+i+"]/descendant::p[1]"));
					String flightNum[] = flightNumber.split("-");
					String flightNumArrival = flightNum[0];
					String flightNumDeparture = flightNum[1];
					System.out.println("airborne not available Flight Info:" +flightNumber+ " "+landingTime+ " " +onBlockDateAndTime+ " " +offBlockDateAndTime+ " " +airborneTime);
					hoverElement(By.xpath("//div[@id='selectedCard'][1]/descendant::div[4]"));
					delay(2000);
					String logId= getWebElement(By.xpath("//div[@id='selectedCard']["+i+"]/descendant::div[4]")).getAttribute("title");
					email_report_ATA_OnBlock_OffBlock_ATD_For_Celebi_UI6.append(" <tr> <td><b style=\"color:red;\">"+logId+"</b></td> "
							+ "<td><b style=\"color:red;\">"+flightNumArrival+"</b></td>"
					 		+ " <td> <b style=\"color:red;\">"+flightNumDeparture+"</b></td>"
					 		+ " <td><b style=\"color:red;\">"+landingTime+"</b></td>"
					 		+ " <td><b style=\"color:red;\">"+onBlockDateAndTime+"</b></td> "
					 		+ "<td><b style=\"color:red;\">"+offBlockDateAndTime+"</b></td>"
					 		+ "<td><b style=\"color:red;\">"+airborneTime+"</b></td>"
					 		+ "</tr>");
				}
				
				}
				
				click(By.xpath("//div[@id='selectedCard']["+i+"]"));
			}
			
			catch(Exception e)
			{
				missingFlight=missingFlight+1;
				//e.printStackTrace();
				//HtmlReportUtil.stepInfo("<b style=\"color:brown;\"> "+i+" - "+getElementText(By.xpath("//div[@id='selectedCard']["+i+"]"))+"</b>");
			} 
		}
		
		email_report_ATA_OnBlock_OffBlock_ATD_For_Celebi_UI2.append("</table>");
		email_report_ATA_OnBlock_OffBlock_ATD_For_Celebi_UI3.append("</table>");
		email_report_ATA_OnBlock_OffBlock_ATD_For_Celebi_UI4.append("</table>");
		email_report_ATA_OnBlock_OffBlock_ATD_For_Celebi_UI5.append("</table>");
		email_report_ATA_OnBlock_OffBlock_ATD_For_Celebi_UI6.append("</table>");
		
	email_report_ATA_OnBlock_OffBlock_ATD_For_Celebi_UI1.append("<style>table#t01, th, td {border: 1px solid black;border-collapse: collapse;}table#t01 th{background-color:#80e5ff; } table#t01 tr:nth-child(even) {background-color: #f2f2f2;} table#t01 tr:nth-child(odd) { background-color: #DFEDEC;}table#t01 th, td {padding: 5px;}table#t01 th,td {text-align: center;} table#t01 caption {color: #008ae6;font-weight: bold;}</style>");
	email_report_ATA_OnBlock_OffBlock_ATD_For_Celebi_UI1.append("<h4 align=\"center\" style=\"color:#008ae6;\"> Airport Name : "+airportName+"</h4>");
	email_report_ATA_OnBlock_OffBlock_ATD_For_Celebi_UI1.append("<h4 align=\"center\" style=\"color:#008ae6;\">Executed For :"+userName+"  </h4><h5 align=\"center\" style=\"color:#008ae6;\" >Execution Time: "+SQL_Queries.todayDayDateTime()+" </h5>");
	email_report_ATA_OnBlock_OffBlock_ATD_For_Celebi_UI1.append("<table style=\"width:100%\" id=\"t01\"><tr>"
						+ "<th style=\"width:10%\"><b>Total Flights Departed ("+SQL_Queries.yesterDate()+") </b></th>"
						+ "<th style=\"width:15%\"><b>Total Flights with Landing time</b></th> "
						+ "<th style=\"width:15%\"><b>Total Flights with ON-Block time</b></th>"
						+ "<th style=\"width:15%\"><b>Total Flights with Off-Block time</b></th>"
						+ "<th style=\"width:20%\"><b>Total Flights with Airborne time</b></th>"
						+ " </tr>");
	email_report_ATA_OnBlock_OffBlock_ATD_For_Celebi_UI1.append(" <tr> "
						+ "<td><b>"+DepatedFlightsCount+"</b></td> "
						+ "<td> <b style=\"color:green;\">"+landingTimeIsNotNull+"</b></td>"
						+ " <td> <b style=\"color:green;\">"+onBlockTimeIsNotNull+"</b></td> "
						+ "<td><b style=\"color:green;\">"+offBlockTimeIsNotNull+"</b></td> "
						+ "<td><b style=\"color:green;\">"+airborneTimeIsNotNull+"</b></td>"	
	 					+ " </tr>");
	email_report_ATA_OnBlock_OffBlock_ATD_For_Celebi_UI1.append("</table>");
	
	HtmlReportUtil.test.log(LogStatus.INFO, email_report_ATA_OnBlock_OffBlock_ATD_For_Celebi_UI1.toString());
	HtmlReportUtil.testHist.log(LogStatus.INFO, email_report_ATA_OnBlock_OffBlock_ATD_For_Celebi_UI1.toString());
	
	ExtentTest child0 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:green;\" align=\"center\">Total Flights Departed ("+SQL_Queries.yesterDate()+"): "+DepatedFlightsCount+"</b>");
	child0.log(LogStatus.INFO, "<b style=\"color:green;\" align=\"center\">Total Flights Departed ("+SQL_Queries.yesterDate()+"): "+DepatedFlightsCount+"</b>");			
	ExtentTest child00 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:green;\" align=\"center\">Total Flights Departed ("+SQL_Queries.yesterDate()+"): "+DepatedFlightsCount+"</b>");
	child00.log(LogStatus.INFO, "<b style=\"color:green;\" align=\"center\">Total Flights Departed ("+SQL_Queries.yesterDate()+"): "+DepatedFlightsCount+"</b>");
	
	ExtentTest child1 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:green;\" align=\"center\">Total Flights with Landing time: "+landingTimeIsNotNull+"</b>");
	child1.log(LogStatus.INFO, "<b style=\"color:green;\" align=\"center\">Total Flights with Landing time: "+landingTimeIsNotNull+"</b>");			
	ExtentTest child11 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:green;\" align=\"center\">Total Flights with Landing time: "+landingTimeIsNotNull+"</b>");
	child11.log(LogStatus.INFO, "<b style=\"color:green;\" align=\"center\">Total Flights with Landing time: "+landingTimeIsNotNull+"</b>");
	
	ExtentTest child2 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:red;\" align=\"center\"> Total Flights without Landing time : "+landingTimeIsNull+"</b>");
	child2.log(LogStatus.INFO, email_report_ATA_OnBlock_OffBlock_ATD_For_Celebi_UI2.toString());
	ExtentTest child22 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:red;\" align=\"center\"> Total Flights without Landing time : "+landingTimeIsNull+"</b>");
	child22.log(LogStatus.INFO, email_report_ATA_OnBlock_OffBlock_ATD_For_Celebi_UI2.toString());
	
	ExtentTest child3 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:green;\" align=\"center\">Total Flights with On-Block time: "+onBlockTimeIsNotNull+"</b>");
	child3.log(LogStatus.INFO, "<b style=\"color:green;\" align=\"center\">Total Flights with On-Block time: "+onBlockTimeIsNotNull+"</b>");			
	ExtentTest child33 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:green;\" align=\"center\">Total Flights with On-Block time: "+onBlockTimeIsNotNull+"</b>");
	child33.log(LogStatus.INFO, "<b style=\"color:green;\" align=\"center\">Total Flights with On-Block time: "+onBlockTimeIsNotNull+"</b>");
	
	ExtentTest child4 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:red;\" align=\"center\"> Total Flights without On-Block time : "+onBlockTimeIsNull+"</b>");
	child4.log(LogStatus.INFO, email_report_ATA_OnBlock_OffBlock_ATD_For_Celebi_UI3.toString());
	ExtentTest child44 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:red;\" align=\"center\"> Total Flights without On-Block time : "+onBlockTimeIsNull+"</b>");
	child44.log(LogStatus.INFO, email_report_ATA_OnBlock_OffBlock_ATD_For_Celebi_UI3.toString());
	
	ExtentTest child5 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:green;\" align=\"center\">Total Flights with Off-Block time: "+offBlockTimeIsNotNull+"</b>");
	child5.log(LogStatus.INFO, "<b style=\"color:green;\" align=\"center\">Total Flights with Off-Block time: "+offBlockTimeIsNotNull+"</b>");			
	ExtentTest child55 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:green;\" align=\"center\">Total Flights with Off-Block time: "+offBlockTimeIsNotNull+"</b>");
	child55.log(LogStatus.INFO, "<b style=\"color:green;\" align=\"center\">Total Flights with Off-Block time: "+offBlockTimeIsNotNull+"</b>");
	
	ExtentTest child6 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:red;\" align=\"center\"> Total Flights without Off-Block time : "+offBlockTimeIsNull+"</b>");
	child6.log(LogStatus.INFO, email_report_ATA_OnBlock_OffBlock_ATD_For_Celebi_UI4.toString());
	ExtentTest child66 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:red;\" align=\"center\"> Total Flights without Off-Block time : "+offBlockTimeIsNull+"</b>");
	child66.log(LogStatus.INFO, email_report_ATA_OnBlock_OffBlock_ATD_For_Celebi_UI4.toString());
	
	ExtentTest child7 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:green;\" align=\"center\">Total Flights with Airborne time: "+airborneTimeIsNotNull+"</b>");
	child7.log(LogStatus.INFO, "<b style=\"color:green;\" align=\"center\">Total Flights with Airborne time: "+airborneTimeIsNotNull+"</b>");			
	ExtentTest child77 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:green;\" align=\"center\">Total Flights with Airborne time: "+airborneTimeIsNotNull+"</b>");
	child77.log(LogStatus.INFO, "<b style=\"color:green;\" align=\"center\">Total Flights with Airborne time: "+airborneTimeIsNotNull+"</b>");
	
	ExtentTest child8 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:red;\" align=\"center\"> Total Flights without Airborne time : "+airborneTimeIsNull+"</b>");
	child8.log(LogStatus.INFO, email_report_ATA_OnBlock_OffBlock_ATD_For_Celebi_UI5.toString());
	ExtentTest child88 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:red;\" align=\"center\"> Total Flights without Airborne time : "+airborneTimeIsNull+"</b>");
	child88.log(LogStatus.INFO, email_report_ATA_OnBlock_OffBlock_ATD_For_Celebi_UI5.toString());
	
	ExtentTest child9 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:green;\" align=\"center\">Total Flights with Landing < On-Block < Off-Block < Airborne time: "+flightsWithCorrectsequence+"</b>");
	child9.log(LogStatus.INFO, "<b style=\"color:green;\" align=\"center\">Total Flights with Landing < On-Block < Off-Block < Airborne time: "+flightsWithCorrectsequence+"</b>");			
	ExtentTest child99 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:green;\" align=\"center\">Total Flights with Landing < On-Block < Off-Block < Airborne time: "+flightsWithCorrectsequence+"</b>");
	child99.log(LogStatus.INFO, "<b style=\"color:green;\" align=\"center\">Total Flights with Landing < On-Block < Off-Block < Airborne time: "+flightsWithCorrectsequence+"</b>");
	
	ExtentTest child10 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:red;\" align=\"center\"> Total Flights which are NOT in sequence as Landing < On-Block < Off-Block < Airborne time : "+flightsNotInCorrectSequence+"</b>");
	child10.log(LogStatus.INFO, email_report_ATA_OnBlock_OffBlock_ATD_For_Celebi_UI6.toString());
	ExtentTest child101 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:red;\" align=\"center\"> Total Flights which are NOT in sequence as Landing < On-Block < Off-Block < Airborne time : "+flightsNotInCorrectSequence+"</b>");
	child101.log(LogStatus.INFO, email_report_ATA_OnBlock_OffBlock_ATD_For_Celebi_UI6.toString());
	
	ExtentTest child111 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:brown;\" align=\"center\"> Missing flights in automation execution: "+missingFlight+"</b>");
	child111.log(LogStatus.INFO, "<b style=\"color:brown;\" align=\"center\">Missing flights in automation execution: "+missingFlight+"</b>");			
	ExtentTest child1111 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:brown;\" align=\"center\">Missing flights in automation execution: "+missingFlight+"</b>");
	child1111.log(LogStatus.INFO, "<b style=\"color:brown;\" align=\"center\">Missing flights in automation execution: "+missingFlight+"</b>");
	
	HtmlReportUtil.test.appendChild(child0).appendChild(child1).appendChild(child2).appendChild(child3).appendChild(child4).appendChild(child5).appendChild(child6).appendChild(child7).appendChild(child8).appendChild(child9).appendChild(child10).appendChild(child111);
	HtmlReportUtil.testHist.appendChild(child00).appendChild(child11).appendChild(child22).appendChild(child33).appendChild(child44).appendChild(child55).appendChild(child66).appendChild(child77).appendChild(child88).appendChild(child99).appendChild(child101).appendChild(child1111);
	}	
}	

