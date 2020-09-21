package enh.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import enh.db.pages.SQL_Queries;
import utilities.HtmlReportUtil;
import utilities.KeywordUtil;

public class AV_2298_Identify_thecoverage_ofDeBoarding_activities_andvalidate_timestamps extends KeywordUtil {
	private static By menu = By.xpath("//mat-icon[contains(.,'menu')] ");
	private static By flightInfo = By.xpath("//a[contains(.,'Flight Info')]");
	private static By turnaroundList = By.xpath("//a[contains(.,'Turnaround List')]");
	private static By departed = By.xpath("//*[@class='mat-tab-label-content'][contains(.,'Departed')]");
	private static By PCDeplane = By.xpath("//div[@title = 'Bay Departure - Drop arrival']/following-sibling::div[1]/div[2]/descendant::p");
	
		
	static JavascriptExecutor js = (JavascriptExecutor) getDriver();
	public static int DepatedFlightsCount=0;
	public static int PCDAndPCDABothFormed =0;
	public static int PCDAndPCDABothNotFormed =0;
	public static int PCDeplaneNotFormed=0;
	public static int PCDFormedPCDA_NotFormed=0;
	
	public static int missingFlight=0;
	public static int j=1;
	
	 public static StringBuilder email_DeboardingActivities1= new StringBuilder();
	 public static StringBuilder email_DeboardingActivities2= new StringBuilder();	 
	 public static StringBuilder email_DeboardingActivities3= new StringBuilder();
	 public static StringBuilder email_DeboardingActivities4= new StringBuilder();
	 
	/**********************************************************************************************************
	 * Method Name: Verify PCD and PCDA Deboarding activities
	 * Description : To verify that PCD and PCDA activities are displayed on UI
	 * Change History:
	 *             Date                     Created By          Change Description        Code Review and date
	 *             ****                     **********            ****************          *******************
	 *         12/Dec/2019                   Padmaja	                                   AD/-/Dec/2019                                      
	 ***********************************************************************************************************/
	public static void verifyDeboardingActivities() throws Exception
	{
		email_DeboardingActivities1.append("<style>table#t01, th, td {border: 1px solid black;border-collapse: collapse;}table#t01 th{background-color:#80e5ff;} table#t01 tr:nth-child(even) {background-color: #f2f2f2;} table#t01 tr:nth-child(odd) { background-color: #DFEDEC;}table#t01 th, td {padding: 5px;}table#t01 th,td {text-align: center;} table#t01 caption {color: #008ae6;font-weight: bold;}</style>");
		email_DeboardingActivities1.append("<table style=\"width:100%\" id=\"t01\"><h4><caption> Total flights without the De-boarding activities (PCD and PCDA): </caption></h4>"
		 		+ "<tr><th style=\"width:25%\">Flight Unique ID</th> <th style=\"width:25%\">Flight Number - Arrival</th>"
			 		+ "<th style=\"width:25%\">Arrival Date </th></tr>");
		
		email_DeboardingActivities2.append("<style>table#t01, th, td {border: 1px solid black;border-collapse: collapse;}table#t01 th{background-color:#80e5ff;} table#t01 tr:nth-child(even) {background-color: #f2f2f2;} table#t01 tr:nth-child(odd) { background-color: #DFEDEC;}table#t01 th, td {padding: 5px;}table#t01 th,td {text-align: center;} table#t01 caption {color: #008ae6;font-weight: bold;}</style>");
		email_DeboardingActivities2.append("<table style=\"width:100%\" id=\"t01\"><h4><caption> Total flights with No PCD activities:  </caption></h4>"
		 		+ "<tr><th style=\"width:25%\">Flight Unique ID</th> <th style=\"width:25%\">Flight Number - Arrival</th>"
			 		+ "<th style=\"width:25%\">Arrival Date </th></tr>");
	
		email_DeboardingActivities3.append("<style>table#t01, th, td {border: 1px solid black;border-collapse: collapse;}table#t01 th{background-color:#80e5ff;} table#t01 tr:nth-child(even) {background-color: #f2f2f2;} table#t01 tr:nth-child(odd) { background-color: #DFEDEC;}table#t01 th, td {padding: 5px;}table#t01 th,td {text-align: center;} table#t01 caption {color: #008ae6;font-weight: bold;}</style>");
		email_DeboardingActivities3.append("<table style=\"width:100%\" id=\"t01\"><h4><caption>	Total flights with PCD activities but no PCDA activities  </caption></h4>"
		 		+ "<tr><th style=\"width:25%\">Flight Unique ID</th> <th style=\"width:25%\">Flight Number - Arrival</th>"
			 		+ "<th style=\"width:25%\">Arrival Date </th><th style=\"width:25%\">Operation PCD timestamp: Start & End </th></tr>");
	
		String userName=getElementText(By.xpath("html/body/app-root/div/app-topnav/mat-toolbar/div[4]/div[2]"));
		System.out.println(userName);
		HtmlReportUtil.stepInfo("<b style=\"color:brown;\"> Reports for "+userName+" </b>");
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
		System.out.println("Final");
		
		click(By.xpath("//mat-icon[contains(.,'search')]"));
		delay(10000);
		System.out.println("done");
		String str_DepatedFlights = getElementText(departed);
		String str_DepatedFlightsCount = str_DepatedFlights.replaceAll("[^0-9]", "");
		DepatedFlightsCount=Integer.parseInt(str_DepatedFlightsCount);
		System.out.println(str_DepatedFlightsCount);
		WebDriverWait w = new WebDriverWait(getDriver(), 20);
		for(int i=1;i<=DepatedFlightsCount;i++)
		{
			try
			{					
				delay(3000);
				js.executeScript("arguments[0].scrollIntoView();",getWebElement(By.xpath("//div[@id='selectedCard']["+i+"]")));
				delay(3000);
				click(By.xpath("//div[@id='selectedCard']["+i+"]"));
				delay(3000);
				
				String flightActivityList=getElementText(By.xpath("//div[@id='selectedCard']["+i+"]"));
				
						if(flightActivityList.contains("PC Deplane") && (flightActivityList.contains("PCD Arrival")))
						{
							PCDAndPCDABothFormed= PCDAndPCDABothFormed+1;
						}
						if(!flightActivityList.contains("PC Deplane") && (!flightActivityList.contains("PCD Arrival")))
						{
							PCDAndPCDABothNotFormed = PCDAndPCDABothNotFormed+1;
							String flightNumber=getElementText(By.xpath("//div[@id='selectedCard']["+i+"]/descendant::p[1]"));
						    String flightNum[] =flightNumber.split("-");
							String logID = getWebElement(By.xpath("//div[@id='selectedCard']["+i+"]/descendant::div[3]/div")).getAttribute("title");
							hoverElement(By.xpath("//div[@id='selectedCard']["+i+"]//div[@class= 'arrival-time-col']/descendant::div[2]"));
							String arrivalDateAndTime = getElementText(By.xpath("//div[@id='selectedCard']["+i+"]//div[@class= 'arrival-time-col']/descendant::div[3]/p[5]"));
							
							email_DeboardingActivities1.append("<tr><td><b style=\"color:red;\">"+flightNum[0]+"</b></td><td><b style=\"color:red;\">"+logID+"</b></td>"
									+ "<td><b style=\"color:red;\">"+arrivalDateAndTime+"</b></td></tr>");
						}
						
						
						if(!flightActivityList.contains("PC Deplane"))
						{
							PCDeplaneNotFormed = PCDeplaneNotFormed+1;
							String flightNumber=getElementText(By.xpath("//div[@id='selectedCard']["+i+"]/descendant::p[1]"));
							String flightNum[] =flightNumber.split("-");
							String logID = getWebElement(By.xpath("//div[@id='selectedCard']["+i+"]/descendant::div[3]/div")).getAttribute("title");
							
							hoverElement(By.xpath("//div[@id='selectedCard']["+i+"]//div[@class= 'arrival-time-col']/descendant::div[2]"));
							String arrivalDateAndTime = getElementText(By.xpath("//div[@id='selectedCard']["+i+"]//div[@class= 'arrival-time-col']/descendant::div[3]/p[5]"));
												
							email_DeboardingActivities2.append("<tr><td><b style=\"color:red;\">"+flightNum[0]+"</b></td><td><b style=\"color:red;\">"+logID+"</b></td>"
									+ "<td><b style=\"color:red;\">"+arrivalDateAndTime+"</b></td></tr>");
						}
						
						if(flightActivityList.contains("PC Deplane") && (!flightActivityList.contains("PCD Arrival")))
						{
							PCDFormedPCDA_NotFormed= PCDFormedPCDA_NotFormed+1;
							String flightNumber=getElementText(By.xpath("//div[@id='selectedCard']["+i+"]/descendant::p[1]"));
							String flightNum[] =flightNumber.split("-");
							String logID = getWebElement(By.xpath("//div[@id='selectedCard']["+i+"]/descendant::div[3]/div")).getAttribute("title");
							hoverElement(By.xpath("//div[@id='selectedCard']["+i+"]//div[@class= 'arrival-time-col']/descendant::div[2]"));
							String arrivalDateAndTime = getElementText(By.xpath("//div[@id='selectedCard']["+i+"]//div[@class= 'arrival-time-col']/descendant::div[3]/p[5]"));
													
							email_DeboardingActivities3.append("<tr><td><b style=\"color:red;\">"+flightNum[0]+"</b></td><td><b style=\"color:red;\">"+logID+"</b></td>"
									+ "<td><b style=\"color:red;\">"+arrivalDateAndTime+"</b></td></tr>");;
						}
						
										
			}
			catch(Exception e)
			{
				missingFlight=missingFlight+1;
				//e.printStackTrace();
				//HtmlReportUtil.stepInfo("<b style=\"color:red;\"> "+i+" - "+getElementText(By.xpath("//div[@id='selectedCard']["+i+"]"))+"</b>");
			} 

		}
		/*HtmlReportUtil.stepInfo("<b style=\"color:blue;\">Total number of Flights for Celebi user : "+DepatedFlightsCount+"</b>");
		HtmlReportUtil.stepInfo("<b style=\"color:green;\">No. of flights for which both PC Deplane and PCD Arrival both are formed : "+PCDAndPCDABothFormed+"</b>");
		HtmlReportUtil.stepInfo("<b style=\"color:red;\">No. of flights for which both PC Deplane and PCD Arrival both are NOT formed : "+PCDAndPCDABothNotFormed+"</b>");
		HtmlReportUtil.stepInfo("<b style=\"color:red;\">No. of flights for which PC Deplane is NOT formed : "+PCDeplaneNotFormed+"</b>");
		HtmlReportUtil.stepInfo("<b style=\"color:black;\">No. of flights which PC Deplane is formed but PCD Arrival is NOT formed : "+PCDFormedPCDA_NotFormed+"</b>");
		HtmlReportUtil.stepInfo("<b style=\"color:brown;\">No. of missing flights in automation execution : "+missingFlight+"</b>");*/
		
		email_DeboardingActivities1.append("</table>");
		email_DeboardingActivities2.append("</table>");
		email_DeboardingActivities3.append("</table>");
		
		email_DeboardingActivities4.append("<style>table#t01, th, td {border: 1px solid black;border-collapse: collapse;}table#t01 th{background-color:#80e5ff;} table#t01 tr:nth-child(even) {background-color: #f2f2f2;} table#t01 tr:nth-child(odd) { background-color: #DFEDEC;}table#t01 th, td {padding: 5px;}table#t01 th,td {text-align: center;} table#t01 caption {color: #008ae6;font-weight: bold;}</style>");
		email_DeboardingActivities4.append("<h4 align=\"center\" style=\"color:#008ae6;\"> Airport Name : DIAL Celebi</h4><h4 align=\"center\" style=\"color:#008ae6;\"> Executed For : De-boarding activities and validate timestamps </h4><h5 align=\"center\" style=\"color:#008ae6;\" >Execution Time: "+SQL_Queries.todayDayDateTime()+" </h5>");
		email_DeboardingActivities4.append("<table style=\"width:100%\" id=\"t01\"><tr><th style=\"width:10%\"><b>Date</b></th><th style=\"width:15%\"><b>Total No. of Departed Flights</b></th> <th style=\"width:15%\"><b> Total Number of flights with all the Deboarding activities </b></th>"
			 		+ "<th style=\"width:20%\"><b>Total Number of flights without Deboarding activities </b></th>"
			 		+ "<th style=\"width:20%\"><b> Total Number of flights with No PCD activities</b></th>"
			 		+ "<th style=\"width:20%\"><b> Total Number of flights with PCD activities but no PCDA activities</b></th>"
			 		+ " </tr>");
		email_DeboardingActivities4.append(" <tr> <td><b>"+SQL_Queries.yesterDate()+"</b></td>"
				+ "<td><b>"+DepatedFlightsCount+"</b></td>"
		 		+ " <td> <b style=\"color:green;\">"+PCDAndPCDABothFormed+"</b></td> "
		 		+ "<td><b style=\"color:red;\">"+PCDAndPCDABothNotFormed+"</b></td> "
		 		+ "<td><b style=\"color:red;\">"+PCDeplaneNotFormed+"</b></td> "
		 		+ "<td><b style=\"color:red;\">"+PCDFormedPCDA_NotFormed+"</b></td>");			 	
		email_DeboardingActivities4.append("</table>");
		
		 HtmlReportUtil.test.log(LogStatus.INFO, email_DeboardingActivities4.toString());
		 HtmlReportUtil.testHist.log(LogStatus.INFO, email_DeboardingActivities4.toString());
		 
		 ExtentTest child0 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:green;\" align=\"center\">Total Flights for Celebi user: "+DepatedFlightsCount+"</b>");
		 child0.log(LogStatus.INFO, "<b style=\"color:green;\" >Total Flights for Celebi user: "+DepatedFlightsCount+"</b>");
		 ExtentTest child00 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:green;\" align=\"center\">Total Flights for Celebi user: "+DepatedFlightsCount+"</b>");
		 child00.log(LogStatus.INFO,"<b style=\"color:green;\" >Total Flights for Celebi user: "+DepatedFlightsCount+"</b>");
		 

		 ExtentTest child1 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:green;\" align=\"center\">Total Number of flights with all the Deboarding activities (PCD, PCDA): "+PCDAndPCDABothFormed+"</b>");
		 child1.log(LogStatus.INFO, "<b style=\"color:green;\" >Total Flights for Celebi user: "+PCDAndPCDABothFormed+"</b>");
		 ExtentTest child11 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:green;\" align=\"center\">Total Number of flights with all the Deboarding activities (PCD, PCDA): "+PCDAndPCDABothFormed+"</b>");
		 child11.log(LogStatus.INFO,"<b style=\"color:green;\" >Total Flights for Celebi user: "+PCDAndPCDABothFormed+"</b>");
		 
		 ExtentTest child2 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:red;\" align=\"center\">Total Number of flights without Deboarding activities: "+PCDAndPCDABothNotFormed+"</b>");
		 child2.log(LogStatus.INFO, email_DeboardingActivities1.toString());
		 ExtentTest child22 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:red;\" align=\"center\">Total Number of flights without Deboarding activities: "+PCDAndPCDABothNotFormed+"</b>");
		 child22.log(LogStatus.INFO, email_DeboardingActivities1.toString());
		 
		 ExtentTest child3 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:red;\" align=\"center\">Total Number of flights with No PCD activities: "+PCDeplaneNotFormed+"</b>");
		 child3.log(LogStatus.INFO, email_DeboardingActivities2.toString());
		 ExtentTest child33 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:red;\" align=\"center\">Total Number of flights with No PCD activities: "+PCDeplaneNotFormed+"</b>");
		 child33.log(LogStatus.INFO, email_DeboardingActivities2.toString());
		 
		 ExtentTest child4 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:red;\" align=\"center\">Total Number of flights with PCD activities but no PCDA activities: "+PCDFormedPCDA_NotFormed+"</b>");
		 child4.log(LogStatus.INFO, email_DeboardingActivities3.toString());
		 ExtentTest child44 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:red;\" align=\"center\">Total Number of flights with PCD activities but no PCDA activities: "+PCDFormedPCDA_NotFormed+"</b>");
		 child44.log(LogStatus.INFO, email_DeboardingActivities3.toString());
		 
		 HtmlReportUtil.test.appendChild(child0).appendChild(child1).appendChild(child2).appendChild(child3).appendChild(child4);
		 HtmlReportUtil.testHist.appendChild(child00).appendChild(child11).appendChild(child22).appendChild(child33).appendChild(child44);
		}	
		}
	


