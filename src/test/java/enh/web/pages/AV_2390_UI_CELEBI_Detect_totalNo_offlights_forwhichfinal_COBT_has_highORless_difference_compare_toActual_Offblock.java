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

public class AV_2390_UI_CELEBI_Detect_totalNo_offlights_forwhichfinal_COBT_has_highORless_difference_compare_toActual_Offblock extends KeywordUtil{
	
	private static By menu = By.xpath("//mat-icon[contains(.,'menu')] ");
	private static By flightInfo = By.xpath("//a[contains(.,'Flight Info')]");
	private static By turnaroundList = By.xpath("//a[contains(.,'Turnaround List')]");
	private static By departed = By.xpath("//*[@class='mat-tab-label-content'][contains(.,'Departed')]");
	
	static JavascriptExecutor js = (JavascriptExecutor) getDriver();
	
	public static int DepatedFlightsCount=0;
	public static int missingFlight =0;
	public static int cobtTimeIsNull =0;
	public static int cobtTimeIsNotNull =0;
	public static int offBlockTimeIsNotNull =0;
	public static int offBlockTimeIsNull= 0;
	public static int differenceBetweenCobtAndOffblockIsMore = 0;
	
	
	public static long offblockMinusCobtInMinutes= 0;
	public static int differenceBetweenOffblockAndCobt =0;
	
	public static StringBuilder email_report_COBT_For_Celebi_UI1 = new StringBuilder();
	public static StringBuilder email_report_COBT_For_Celebi_UI2 = new StringBuilder();
	public static StringBuilder email_report_COBT_For_Celebi_UI3 = new StringBuilder();
	public static StringBuilder email_report_COBT_For_Celebi_UI4 = new StringBuilder();
		
	
	/**********************************************************************************************************
	 * Method Name: Detect the flight for which COBT and Offblock difference is more or less than 5min
	 * Description : To get the details of flight for which COBT and Offblock difference is more or less than 5min on UI
	 * Change History:
	 *             Date                     Created By          Change Description        Code Review and date
	 *             ****                     **********            ****************          *******************
	 *         26/Dec/2019                   Padmaja	                                   AD/-/Dec/2019                                      
	 * @throws Exception 
	 ***********************************************************************************************************/
	public static void verifyCOBTAndOffBlockTimeDifferenceForFlights(String airportName, String userName) throws Exception
	{	
		email_report_COBT_For_Celebi_UI2.append("<br><br>");
		email_report_COBT_For_Celebi_UI2.append("<style>table#t01, th, td {border: 1px solid black;border-collapse: collapse;}table#t01 th{background-color:#80e5ff; } table#t01 tr:nth-child(even) {background-color: #f2f2f2;} table#t01 tr:nth-child(odd) { background-color: #DFEDEC;}table#t01 th, td {padding: 5px;}table#t01 th,td {text-align: center;} table#t01 caption {color: #008ae6;font-weight: bold;}</style>");
		email_report_COBT_For_Celebi_UI2.append("<table style=\"width:100%\" id=\"t01\"><caption> Total Flights without COBT time </caption><tr>"
													+ "<th style=\"width:15%\"><b>logID</b></th> "
													+ "<th style=\"width:15%\"><b>FlightNumber_Arrival</b></th>"
													+ "<th style=\"width:15%\"><b>FlightNumber_Departure</b></th>"
											 		+ "<th style=\"width:15%\"><b>On_BlocK_Time</b></th>"
											 		+ "<th style=\"width:20%\"><b>Off_BlocK_Time</b></th>"
											 		+ "<th style=\"width:20%\"><b>Airborne</b></th>"
											 		+ " </tr>");
		
		email_report_COBT_For_Celebi_UI3.append("<br><br>");
		email_report_COBT_For_Celebi_UI3.append("<style>table#t01, th, td {border: 1px solid black;border-collapse: collapse;}table#t01 th{background-color:#80e5ff; } table#t01 tr:nth-child(even) {background-color: #f2f2f2;} table#t01 tr:nth-child(odd) { background-color: #DFEDEC;}table#t01 th, td {padding: 5px;}table#t01 th,td {text-align: center;} table#t01 caption {color: #008ae6;font-weight: bold;}</style>");
		email_report_COBT_For_Celebi_UI3.append("<table style=\"width:100%\" id=\"t01\"><caption> Total Flights without Offblock time </caption><tr>"
													+ "<th style=\"width:15%\"><b>logID</b></th> "
													+ "<th style=\"width:15%\"><b>FlightNumber_Arrival</b></th>"
													+ "<th style=\"width:15%\"><b>FlightNumber_Departure</b></th>"
											 		+ "<th style=\"width:15%\"><b>COBT_Time</b></th>"
											 		+ "<th style=\"width:20%\"><b>On_BlocK_Time</b></th>"
											 		+ "<th style=\"width:20%\"><b>Airborne</b></th>"
											 		+ " </tr>");
		
		email_report_COBT_For_Celebi_UI4.append("<br><br>");
		email_report_COBT_For_Celebi_UI4.append("<style>table#t01, th, td {border: 1px solid black;border-collapse: collapse;}table#t01 th{background-color:#80e5ff; } table#t01 tr:nth-child(even) {background-color: #f2f2f2;} table#t01 tr:nth-child(odd) { background-color: #DFEDEC;}table#t01 th, td {padding: 5px;}table#t01 th,td {text-align: center;} table#t01 caption {color: #008ae6;font-weight: bold;}</style>");
		email_report_COBT_For_Celebi_UI4.append("<table style=\"width:100%\" id=\"t01\"><caption> Flights for which difference between Actual Offblock and COBT time > 5min </caption><tr>"
													+ "<th style=\"width:15%\"><b>logID</b></th> "
													+ "<th style=\"width:15%\"><b>FlightNumber_Arrival</b></th>"
													+ "<th style=\"width:15%\"><b>FlightNumber_Departure</b></th>"
											 		+ "<th style=\"width:15%\"><b>COBT_Time</b></th>"
											 		+ "<th style=\"width:20%\"><b>Off_BlocK_Time</b></th>"
											 		+ "<th style=\"width:20%\"><b>Difference (in mins)</b></th>"
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
				delay(2000);
				js.executeScript("arguments[0].scrollIntoView();",getWebElement(By.xpath("//div[@id='selectedCard']["+i+"]")));
				
				click(By.xpath("//div[@id='selectedCard']["+i+"]"));
				delay(2000);
												
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
				
				String cobtTimestamp = getElementText(By.xpath("//div[@id='selectedCard']["+i+"]/descendant::div[@class = 'cobt-status-col']/div/p"));
				String cobtTime= cobtTimestamp.replaceAll("info", "");
				
				System.out.println("COBT time:" +cobtTime);
				delay(2000);
				
				if(cobtTime.isEmpty() || (cobtTime.equals("-"))) 
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
					cobtTimeIsNull = cobtTimeIsNull +1;
					
					email_report_COBT_For_Celebi_UI2.append(" <tr> <td><b style=\"color:red;\">"+logId+"</b></td> "
							+ "<td><b style=\"color:red;\">"+flightNumArrival+"</b></td>"
					 		+ " <td> <b style=\"color:red;\">"+flightNumDeparture+"</b></td>"
					 		+ " <td><b style=\"color:red;\">"+onBlockDateAndTime+"</b></td>"
					 		+ " <td><b style=\"color:red;\">"+offBlockDateAndTime+"</b></td> "
					 		+ "<td><b style=\"color:red;\">"+airborneTime+"</b></td></tr>");
				}
				else {
					cobtTimeIsNotNull = cobtTimeIsNotNull + 1;
					}
				
				if(offBlockDateAndTime.isEmpty()) 
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
					offBlockTimeIsNull = offBlockTimeIsNull +1;
					
					email_report_COBT_For_Celebi_UI3.append(" <tr> <td><b style=\"color:red;\">"+logId+"</b></td> "
							+ "<td><b style=\"color:red;\">"+flightNumArrival+"</b></td>"
					 		+ " <td><b style=\"color:red;\">"+flightNumDeparture+"</b></td>"
					 		+ " <td><b style=\"color:red;\">"+cobtTime+"</b></td>"
					 		+ " <td><b style=\"color:red;\">"+onBlockDateAndTime+"</b></td>"
					 		+ "<td><b style=\"color:red;\">"+airborneTime+"</b></td></tr>");
				}
				else {
					offBlockTimeIsNotNull = offBlockTimeIsNotNull +1;
				}
				
				if(!cobtTime.equals("-") && (!offBlockDateAndTime.isEmpty()))
				{
					String offBlockTimestamp[]= offBlockDateAndTime.split(" ");
					String offBlockTime= offBlockTimestamp[1];
										
					SimpleDateFormat format = new SimpleDateFormat("HH:mm");
					Date cobt = format.parse(cobtTime);
					Date offBlock = format.parse(offBlockTime);
					
					long offblockMinusCobt = offBlock.getTime() - cobt.getTime();
					long offblockMinusCobtInMinutes = Math.abs(offblockMinusCobt / (60 * 1000) % 60);
					System.out.println("Difference between COBT and OFB: "+offblockMinusCobtInMinutes);
					
					if(offblockMinusCobtInMinutes > 5) {
						
						System.out.println("In If condition to substract");
						String flightNumber =getElementText(By.xpath("//div[@id='selectedCard']["+i+"]/descendant::p[1]"));
						String flightNum[] = flightNumber.split("-");
						String flightNumArrival = flightNum[0];
						String flightNumDeparture = flightNum[1];
						hoverElement(By.xpath("//div[@id='selectedCard'][1]/descendant::div[4]"));
						delay(2000);
						String logId= getWebElement(By.xpath("//div[@id='selectedCard']["+i+"]/descendant::div[4]")).getAttribute("title");
						
						differenceBetweenOffblockAndCobt= differenceBetweenOffblockAndCobt +1;
						
						email_report_COBT_For_Celebi_UI4.append(" <tr> <td><b style=\"color:red;\">"+logId+"</b></td> "
								+ "<td><b style=\"color:red;\">"+flightNumArrival+"</b></td>"
						 		+ " <td> <b style=\"color:red;\">"+flightNumDeparture+"</b></td>"
						 		+ " <td> <b style=\"color:red;\">"+cobtTime+"</b></td>"
						 		+ " <td><b style=\"color:red;\">"+offBlockDateAndTime+"</b></td>"
						 		+ "<td><b style=\"color:red;\">"+offblockMinusCobtInMinutes+"</b></td></tr>");
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
		
		email_report_COBT_For_Celebi_UI2.append("</table>");
		email_report_COBT_For_Celebi_UI3.append("</table>");
		email_report_COBT_For_Celebi_UI4.append("</table>");	
		
		email_report_COBT_For_Celebi_UI1.append("<style>table#t01, th, td {border: 1px solid black;border-collapse: collapse;}table#t01 th{background-color:#80e5ff; } table#t01 tr:nth-child(even) {background-color: #f2f2f2;} table#t01 tr:nth-child(odd) { background-color: #DFEDEC;}table#t01 th, td {padding: 5px;}table#t01 th,td {text-align: center;} table#t01 caption {color: #008ae6;font-weight: bold;}</style>");
		email_report_COBT_For_Celebi_UI1.append("<h4 align=\"center\" style=\"color:#008ae6;\"> Airport Name : "+airportName+"</h4>");
		email_report_COBT_For_Celebi_UI1.append("<h4 align=\"center\" style=\"color:#008ae6;\">Executed For : "+userName+"  </h4><h5 align=\"center\" style=\"color:#008ae6;\" >Execution Time: "+SQL_Queries.todayDayDateTime()+" </h5>");
		email_report_COBT_For_Celebi_UI1.append("<table style=\"width:100%\" id=\"t01\"><tr>"
						+ "<th style=\"width:10%\"><b> Date </b></th>"
						+ "<th style=\"width:10%\"><b>Total Flights Departed </b></th>"
						+ "<th style=\"width:15%\"><b>Total Flights without COBT </b></th> "
						+ "<th style=\"width:15%\"><b>Total Flights without Off-Block time</b></th>"
						+ " </tr>");
		email_report_COBT_For_Celebi_UI1.append(" <tr> "
						+ "<td><b>"+SQL_Queries.yesterDate()+"</b></td> "
						+ "<td> <b style=\"color:green;\">"+DepatedFlightsCount+"</b></td>"
						+ " <td> <b style=\"color:red;\">"+cobtTimeIsNull+"</b></td> "
						+ "<td><b style=\"color:red;\">"+offBlockTimeIsNull+"</b></td> "
						+ " </tr>");
		email_report_COBT_For_Celebi_UI1.append("</table>");
		
		HtmlReportUtil.test.log(LogStatus.INFO, email_report_COBT_For_Celebi_UI1.toString());
		HtmlReportUtil.testHist.log(LogStatus.INFO, email_report_COBT_For_Celebi_UI1.toString());
		
		ExtentTest child0 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:green;\" align=\"center\">Total Flights Departed ("+SQL_Queries.yesterDate()+"): "+DepatedFlightsCount+"</b>");
		child0.log(LogStatus.INFO, "<b style=\"color:green;\" align=\"center\">Total Flights Departed ("+SQL_Queries.yesterDate()+"): "+DepatedFlightsCount+"</b>");			
		ExtentTest child00 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:green;\" align=\"center\">Total Flights Departed ("+SQL_Queries.yesterDate()+"): "+DepatedFlightsCount+"</b>");
		child00.log(LogStatus.INFO, "<b style=\"color:green;\" align=\"center\">Total Flights Departed ("+SQL_Queries.yesterDate()+"): "+DepatedFlightsCount+"</b>");
	
		ExtentTest child1 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:green;\" align=\"center\">Total Flights with COBT : "+cobtTimeIsNotNull+"</b>");
		child1.log(LogStatus.INFO, "<b style=\"color:green;\" align=\"center\">Total Flights with COBT: "+cobtTimeIsNotNull+"</b>");			
		ExtentTest child11 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:green;\" align=\"center\">Total Flights with COBT : "+cobtTimeIsNotNull+"</b>");
		child11.log(LogStatus.INFO, "<b style=\"color:green;\" align=\"center\">Total Flights with COBT: "+cobtTimeIsNotNull+"</b>");
	
		ExtentTest child2 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:red;\" align=\"center\"> Total Flights without COBT : "+cobtTimeIsNull+"</b>");
		child2.log(LogStatus.INFO, email_report_COBT_For_Celebi_UI2.toString());
		ExtentTest child22 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:red;\" align=\"center\"> Total Flights without COBT : "+cobtTimeIsNull+"</b>");
		child22.log(LogStatus.INFO, email_report_COBT_For_Celebi_UI2.toString());
				
		ExtentTest child3 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:green;\" align=\"center\">Total Flights with Offblock Time: "+offBlockTimeIsNotNull+"</b>");
		child3.log(LogStatus.INFO, "<b style=\"color:green;\" align=\"center\">Total Flights with Offblock Time: "+offBlockTimeIsNotNull+"</b>");			
		ExtentTest child33 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:green;\" align=\"center\">Total Flights with Offblock Time: "+offBlockTimeIsNotNull+"</b>");
		child33.log(LogStatus.INFO, "<b style=\"color:green;\" align=\"center\">Total Flights with Offblock Time: "+offBlockTimeIsNotNull+"</b>");
	
		ExtentTest child4 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:red;\" align=\"center\"> Total Flights without Offblock time : "+offBlockTimeIsNull+"</b>");
		child4.log(LogStatus.INFO, email_report_COBT_For_Celebi_UI3.toString());
		ExtentTest child44 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:red;\" align=\"center\"> Total Flights without Offblock time : "+offBlockTimeIsNull+"</b>");
		child44.log(LogStatus.INFO, email_report_COBT_For_Celebi_UI3.toString());
	
		ExtentTest child5 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:red;\" align=\"center\"> Total Flights difference between actual Offblock and COBT > 5 mins : "+differenceBetweenOffblockAndCobt+"</b>");
		child5.log(LogStatus.INFO, email_report_COBT_For_Celebi_UI4.toString());
		ExtentTest child55 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:red;\" align=\"center\"> Total Flights difference between actual Offblock and COBT > 5 mins : "+differenceBetweenOffblockAndCobt+"</b>");
		child55.log(LogStatus.INFO, email_report_COBT_For_Celebi_UI4.toString());
		
		ExtentTest child6 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:brown;\" align=\"center\">Total Flights missed in automation execution: "+missingFlight+"</b>");
		child6.log(LogStatus.INFO, "<b style=\"color:brown;\" align=\"center\">Total Flights missed in automation execution: "+missingFlight+"</b>");			
		ExtentTest child66 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:brown;\" align=\"center\">Total Flights missed in automation execution: "+missingFlight+"</b>");
		child66.log(LogStatus.INFO, "<b style=\"color:brown;\" align=\"center\">Total Flights missed in automation execution: "+missingFlight+"</b>");
		
		HtmlReportUtil.test.appendChild(child0).appendChild(child1).appendChild(child2).appendChild(child3).appendChild(child4).appendChild(child5).appendChild(child6);
		HtmlReportUtil.testHist.appendChild(child00).appendChild(child11).appendChild(child22).appendChild(child33).appendChild(child44).appendChild(child55).appendChild(child66);
	
	}	
}	

