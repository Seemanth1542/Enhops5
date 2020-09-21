package enh.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import enh.db.pages.SQL_Queries;
import utilities.HtmlReportUtil;
import utilities.KeywordUtil;

public class AV_2304_Identify_the_coverage_of_Boarding_activities_and_validate_timestamps extends KeywordUtil {
	private static By menu = By.xpath("//mat-icon[contains(.,'menu')] ");
	private static By flightInfo = By.xpath("//a[contains(.,'Flight Info')]");
	private static By turnaroundList = By.xpath("//a[contains(.,'Turnaround List')]");
	private static By departed = By.xpath("//*[@class='mat-tab-label-content'][contains(.,'Departed')]");
	static JavascriptExecutor js = (JavascriptExecutor) getDriver();
	public static int DepatedFlightsCount=0;
	public static int NumberOfFlightsWithAllTheBoardingActivities=0;
	public static int NumberOfFlightsWithoutAllTheBoardingActivities=0;
	public static int NoPCBactivities = 0;
	public static int withPCBactivitiesButNoPCGactivities=0;
	public static int withPCBactivitiesButNoPCBAactivities =0;
	public static int missingFlight=0;
	public static int j=1;
	
	 public static StringBuilder email_BoardingActivities1= new StringBuilder();
	 public static StringBuilder email_BoardingActivities2= new StringBuilder();	 
	 public static StringBuilder email_BoardingActivities3= new StringBuilder();
	 public static StringBuilder email_BoardingActivities4= new StringBuilder();
	 public static StringBuilder email_BoardingActivities5= new StringBuilder();

	public static void boardingActivities() throws Exception
	{
		email_BoardingActivities1.append("<style>table#t01, th, td {border: 1px solid black;border-collapse: collapse;}table#t01 th{background-color:#80e5ff; } table#t01 tr:nth-child(even) {background-color: #f2f2f2;} table#t01 tr:nth-child(odd) { background-color: #DFEDEC;}table#t01 th, td {padding: 5px;}table#t01 th,td {text-align: center;} table#t01 caption {color: #008ae6;font-weight: bold;}</style>");
		email_BoardingActivities1.append("<table style=\"width:100%\" id=\"t01\"><h4><caption> Total flights without the Boarding activities (PCG, PCB, and PCBA): </caption></h4>"
		 		+ "<tr><th style=\"width:25%\">Flight Unique ID</th> <th style=\"width:25%\">Flight Number - Departure Number</th>"
			 		+ "<th style=\"width:25%\">Departure Date </th></tr>");
		
		email_BoardingActivities2.append("<style>table#t01, th, td {border: 1px solid black;border-collapse: collapse;}table#t01 th{background-color:#80e5ff; } table#t01 tr:nth-child(even) {background-color: #f2f2f2;} table#t01 tr:nth-child(odd) { background-color: #DFEDEC;}table#t01 th, td {padding: 5px;}table#t01 th,td {text-align: center;} table#t01 caption {color: #008ae6;font-weight: bold;}</style>");
		email_BoardingActivities2.append("<table style=\"width:100%\" id=\"t01\"><h4><caption> Total flights with No PCB activities:  </caption></h4>"
		 		+ "<tr><th style=\"width:25%\">Flight Unique ID</th> <th style=\"width:25%\">Flight Number - Departure Number</th>"
			 		+ "<th style=\"width:25%\">Departure Date </th></tr>");
	
		email_BoardingActivities3.append("<style>table#t01, th, td {border: 1px solid black;border-collapse: collapse;}table#t01 th{background-color:#80e5ff; } table#t01 tr:nth-child(even) {background-color: #f2f2f2;} table#t01 tr:nth-child(odd) { background-color: #DFEDEC;}table#t01 th, td {padding: 5px;}table#t01 th,td {text-align: center;} table#t01 caption {color: #008ae6;font-weight: bold;}</style>");
		email_BoardingActivities3.append("<table style=\"width:100%\" id=\"t01\"><h4><caption>	Total flights with PCB activities but no PCG activities  </caption></h4>"
		 		+ "<tr><th style=\"width:25%\">Flight Unique ID</th> <th style=\"width:25%\">Flight Number - Departure Number</th>"
			 		+ "<th style=\"width:25%\">Departure Date </th><th style=\"width:25%\">Operation PCB timestamp: Start & End </th></tr>");
	
		email_BoardingActivities4.append("<style>table#t01, th, td {border: 1px solid black;border-collapse: collapse;}table#t01 th{background-color:#80e5ff;} table#t01 tr:nth-child(even) {background-color: #f2f2f2;} table#t01 tr:nth-child(odd) { background-color: #DFEDEC;}table#t01 th, td {padding: 5px;}table#t01 th,td {text-align: center;} table#t01 caption {color: #008ae6;font-weight: bold;}</style>");
		email_BoardingActivities4.append("<table style=\"width:100%\" id=\"t01\"><h4><caption>	Total Number of flights with PCB activities but no PCBA activities:   </caption></h4>"
		 		+ "<tr><th style=\"width:25%\">Flight Unique ID</th> <th style=\"width:25%\">Flight Number - Departure Number</th>"
			 		+ "<th style=\"width:25%\">Departure Date </th><th style=\"width:25%\">Operation PCB timestamp: Start & End </th></tr>");
	
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
		//calenderInput("13/NOV/2019");
		clearInput(By.xpath("//input[@name='date']"));
		delay(2000);
		inputText(By.xpath("//input[@name='date']"), SQL_Queries.yesterDate());
		delay(2000);
		System.out.println("Final");
		//departed = By.xpath("//*[@class='mat-tab-label-content'][contains(.,'Departed')]");
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
			try {
			
				delay(3000);
				js.executeScript("arguments[0].scrollIntoView();",getWebElement(By.xpath("//div[@id='selectedCard']["+i+"]")));				
				delay(3000);
				click(By.xpath("//div[@id='selectedCard']["+i+"]"));
				delay(3000);
				String flightInfoText=getElementText(By.xpath("//div[@id='selectedCard']["+i+"]"));
				System.out.println(flightInfoText);
				
						if(flightInfoText.contains("PC Boarding") && (flightInfoText.contains("PC Gate Arrival"))&& (flightInfoText.contains("PCB Arrival ")))
						{
							NumberOfFlightsWithAllTheBoardingActivities= NumberOfFlightsWithAllTheBoardingActivities+1;	
							System.out.println("NumberOfFlightsWithAllTheBoardingActivities");
							System.out.println(i);
							String flightNum=getElementText(By.xpath("//div[@id='selectedCard']["+i+"]/descendant::p[1]"));
							System.out.println(flightNum);
							
						}
						if(!flightInfoText.contains("PC Boarding") && (!flightInfoText.contains("PC Gate Arrival"))&& (!flightInfoText.contains("PCB Arrival ")))
						{
							NumberOfFlightsWithoutAllTheBoardingActivities = NumberOfFlightsWithoutAllTheBoardingActivities+1;
							String flightNum=getElementText(By.xpath("//div[@id='selectedCard']["+i+"]/descendant::p[1]"));
							String flightNumm[] =flightNum.split("-");
							String logID = getWebElement(By.xpath("//div[@id='selectedCard']["+i+"]/descendant::div[3]/div")).getAttribute("title");
							delay(2000);
							hoverElement(By.xpath("(//div[@id='selectedCard']["+i+"]//div[@class= 'departure-time-col']/descendant::div/div)[1]"));
							String deparDate=getElementText(By.xpath("//div[@id='selectedCard']["+i+"]//div[@class= 'departure-time-col']/descendant::div/div[2]/p[4]"));
							email_BoardingActivities1.append("<tr><td><b style=\"color:red;\">"+flightNumm[1]+"</b></td><td><b style=\"color:red;\">"+logID+"</b></td>"
									+ "<td><b style=\"color:red;\">"+deparDate+"</b></td></tr>");
							System.out.println("NumberOfFlightsWithoutAllTheBoardingActivities");
							System.out.println(i+"-"+flightNum+"-"+flightNumm[1]+"-"+logID+"-"+deparDate);
						}
						if((!flightInfoText.contains("PC Boarding")))
						{
							NoPCBactivities = NoPCBactivities+1;
							String flightNum=getElementText(By.xpath("//div[@id='selectedCard']["+i+"]/descendant::p[1]"));
							String flightNumm[] =flightNum.split("-");
							String logID = getWebElement(By.xpath("//div[@id='selectedCard']["+i+"]/descendant::div[3]/div")).getAttribute("title");							
							delay(2000);
							hoverElement(By.xpath("(//div[@id='selectedCard']["+i+"]//div[@class= 'departure-time-col']/descendant::div/div)[1]"));
							String deparDate=getElementText(By.xpath("//div[@id='selectedCard']["+i+"]//div[@class= 'departure-time-col']/descendant::div/div[2]/p[4]"));
							
							email_BoardingActivities2.append("<tr><td><b style=\"color:red;\">"+flightNumm[1]+"</b></td><td><b style=\"color:red;\">"+logID+"</b></td>"
									+ "<td><b style=\"color:red;\">"+deparDate+"</b></td></tr>");
							System.out.println("NoPCBactivities");
							System.out.println(i+"-"+flightNum+"-"+flightNumm[1]+"-"+logID+"-"+deparDate);
						}
						if(flightInfoText.contains("PC Boarding") && (!flightInfoText.contains("PC Gate Arrival")))
						{
							withPCBactivitiesButNoPCGactivities= withPCBactivitiesButNoPCGactivities+1;
							String flightNum=getElementText(By.xpath("//div[@id='selectedCard']["+i+"]/descendant::p[1]"));
							String flightNumm[] =flightNum.split("-");
							String logID = getWebElement(By.xpath("//div[@id='selectedCard']["+i+"]/descendant::div[3]/div")).getAttribute("title");							
							delay(2000);
							hoverElement(By.xpath("(//div[@id='selectedCard']["+i+"]//div[@class= 'departure-time-col']/descendant::div/div)[1]"));
							String deparDate=getElementText(By.xpath("//div[@id='selectedCard']["+i+"]//div[@class= 'departure-time-col']/descendant::div/div[2]/p[4]"));
							
							email_BoardingActivities3.append("<tr><td><b style=\"color:red;\">"+flightNumm[1]+"</b></td><td><b style=\"color:red;\">"+logID+"</b></td>"
									+ "<td><b style=\"color:red;\">"+deparDate+"</b></td><td><b style=\"color:red;\">-</b></td></tr>");
							System.out.println("withPCBactivitiesButNoPCG_activities");
							System.out.println(i+"-"+flightNum+"-"+flightNumm[1]+"-"+logID+"-"+deparDate);
							
						}
						if(flightInfoText.contains("PC Boarding")&& (!flightInfoText.contains("PCB Arrival ")))
							{
							withPCBactivitiesButNoPCBAactivities= withPCBactivitiesButNoPCBAactivities+1;
							String flightNum=getElementText(By.xpath("//div[@id='selectedCard']["+i+"]/descendant::p[1]"));
							String flightNumm[] =flightNum.split("-");
							String logID = getWebElement(By.xpath("//div[@id='selectedCard']["+i+"]/descendant::div[3]/div")).getAttribute("title");							
							delay(2000);
							hoverElement(By.xpath("(//div[@id='selectedCard']["+i+"]//div[@class= 'departure-time-col']/descendant::div/div)[1]"));
							String deparDate=getElementText(By.xpath("//div[@id='selectedCard']["+i+"]//div[@class= 'departure-time-col']/descendant::div/div[2]/p[4]"));
							email_BoardingActivities4.append("<tr><td><b style=\"color:red;\">"+flightNumm[1]+"</b></td><td><b style=\"color:red;\">"+logID+"</b></td>"
									+ "<td><b style=\"color:red;\">"+deparDate+"</b></td><td><b style=\"color:red;\">-</b></td></tr>");
							
							System.out.println("withPCBactivitiesButNoPCB_Aactivities");
							System.out.println(i+"-"+flightNum+"-"+flightNumm[1]+"-"+logID+"-"+deparDate);
							}						
				
			}catch(Exception e) {
			
				missingFlight=missingFlight+1;
				String flightNum=getElementText(By.xpath("//div[@id='selectedCard']["+i+"]/descendant::p[1]"));
				System.out.println("missingFlight");
				System.out.println(flightNum);
				//e.printStackTrace();
				//HtmlReportUtil.stepInfo("<b style=\"color:red;\"> "+i+" - "+getElementText(By.xpath("//div[@id='selectedCard']["+i+"]"))+"</b>");
			}

		}
		email_BoardingActivities1.append("</table>");
		email_BoardingActivities2.append("</table>");
		email_BoardingActivities3.append("</table>");
		email_BoardingActivities4.append("</table>");
		email_BoardingActivities5.append("<style>table#t01, th, td {border: 1px solid black;border-collapse: collapse;}table#t01 th{background-color:#80e5ff; } table#t01 tr:nth-child(even) {background-color: #f2f2f2;} table#t01 tr:nth-child(odd) { background-color: #DFEDEC;}table#t01 th, td {padding: 5px;}table#t01 th,td {text-align: center;} table#t01 caption {color: #008ae6;font-weight: bold;}</style>");
		email_BoardingActivities5.append("<h4 align=\"center\" style=\"color:#008ae6;\">Airport Name: DIAL CELEBI </h4><h4 align=\"center\" style=\"color:#008ae6;\">Executed For :Boarding activities and validate timestamps</h4><h5 align=\"center\" style=\"color:#008ae6;\" >Execution Time: "+SQL_Queries.todayDayDateTime()+" </h5>");
		email_BoardingActivities5.append("<table style=\"width:100%\" id=\"t01\"><tr><th style=\"width:15%\"><b>Date</b></th>"
				+ "<th style=\"width:15%\"><b>Total Flights Departed ("+SQL_Queries.yesterDate()+") </b></th> "
				+ "<th style=\"width:15%\"><b>Total Number of flights with all the Boarding activities (PCG, PCB, and PCBA) </b></th>"
			 	+ "<th style=\"width:15%\"><b>Total Number of flights without the Boarding activities (PCG, PCB, and PCBA) </b></th>"
			 	+ "<th style=\"width:15%\"><b>Total Number of flights with No PCB activities</b></th>"
			 	+ "<th style=\"width:15%\"><b>Total Number of flights with PCB activities but no PCG activities</b></th>"
			 	+ "<th style=\"width:15  %\"><b>Total Number of flights with PCB activities but no PCBA activities </b></th>"
			 	+ " </tr>");
		email_BoardingActivities5.append(" <tr> <td><b>"+SQL_Queries.yesterDate()+"</b></td>"
				+ " <td><b>"+DepatedFlightsCount+"</b></td>"
			 	+ " <td> <b style=\"color:red;\">"+NumberOfFlightsWithAllTheBoardingActivities+"</b></td>"
			 	+ " <td><b style=\"color:red;\">"+NumberOfFlightsWithoutAllTheBoardingActivities+"</b></td>"
			 	+ " <td><b style=\"color:red;\">"+NoPCBactivities+"</b></td> "
			 	+ " <td><b style=\"color:red;\">"+withPCBactivitiesButNoPCGactivities+"</b></td> "
			 	+ " <td><b style=\"color:red;\">"+withPCBactivitiesButNoPCBAactivities+"</b></td> "
			 	+ " </tr></table>");			 

		
		 HtmlReportUtil.test.log(LogStatus.INFO, email_BoardingActivities5.toString());
		 HtmlReportUtil.testHist.log(LogStatus.INFO, email_BoardingActivities5.toString());
		 ExtentTest child0 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:green;\" align=\"center\">Total Flights Departed ("+SQL_Queries.yesterDate()+"):  "+DepatedFlightsCount+"</b>");
		 child0.log(LogStatus.INFO, "<b style=\"color:green;\" >Total Flights Departed ("+SQL_Queries.yesterDate()+"):  "+DepatedFlightsCount+"</b>");
		 ExtentTest child00 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:green;\" align=\"center\">Total Flights Departed ("+SQL_Queries.yesterDate()+"): "+DepatedFlightsCount+"</b>");
		 child00.log(LogStatus.INFO,"<b style=\"color:green;\" >Total Flights Departed ("+SQL_Queries.yesterDate()+"):  "+DepatedFlightsCount+"</b>");

		 ExtentTest child1 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:green;\" align=\"center\">	Total Number of flights with all the Boarding activities (PCG, PCB, and PCBA): "+NumberOfFlightsWithAllTheBoardingActivities+"</b>");
		 child1.log(LogStatus.INFO, "<b style=\"color:green;\" >Total Number of flights with all the Boarding activities (PCG, PCB, and PCBA): "+NumberOfFlightsWithAllTheBoardingActivities+"</b>");
		 ExtentTest child11 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:green;\" align=\"center\">Total Number of flights with all the Boarding activities (PCG, PCB, and PCBA): "+NumberOfFlightsWithAllTheBoardingActivities+"</b>");
		 child11.log(LogStatus.INFO,"<b style=\"color:green;\" >Total Number of flights with all the Boarding activities (PCG, PCB, and PCBA): "+NumberOfFlightsWithAllTheBoardingActivities+"</b>");

		 ExtentTest child2 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:green;\" align=\"center\">	Total Number of flights without the Boarding activities (PCG, PCB, and PCBA): "+NumberOfFlightsWithoutAllTheBoardingActivities+"</b>");
		 child2.log(LogStatus.INFO, email_BoardingActivities1.toString());
		 ExtentTest child22 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:green;\" align=\"center\">Total Number of flights without the Boarding activities (PCG, PCB, and PCBA): "+NumberOfFlightsWithoutAllTheBoardingActivities+"</b>");
		 child22.log(LogStatus.INFO,email_BoardingActivities1.toString());
		 
		 ExtentTest child3 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:green;\" align=\"center\">	Total Number of flights with No PCB activities:  "+NoPCBactivities+"</b>");
		 child3.log(LogStatus.INFO,email_BoardingActivities2.toString() );
		 ExtentTest child33 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:green;\" align=\"center\">Total Number of flights with No PCB activities: "+NoPCBactivities+"</b>");
		 child33.log(LogStatus.INFO,email_BoardingActivities2.toString());
		 
		 ExtentTest child4 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:green;\" align=\"center\">	Total Number of flights with PCB activities but no PCG activities:   "+withPCBactivitiesButNoPCGactivities+"</b>");
		 child4.log(LogStatus.INFO, email_BoardingActivities3.toString());
		 ExtentTest child44 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:green;\" align=\"center\">	Total Number of flights with PCB activities but no PCG activities:  "+withPCBactivitiesButNoPCGactivities+"</b>");
		 child44.log(LogStatus.INFO,email_BoardingActivities3.toString());
 
		 ExtentTest child5 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:green;\" align=\"center\">Total Number of flights with PCB activities but no PCBA activities:    "+withPCBactivitiesButNoPCBAactivities+"</b>");
		 child5.log(LogStatus.INFO,email_BoardingActivities4.toString() );
		 ExtentTest child55 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:green;\" align=\"center\">	Total Number of flights with PCB activities but no PCBA activities:  "+withPCBactivitiesButNoPCBAactivities+"</b>");
		 child55.log(LogStatus.INFO,email_BoardingActivities4.toString());
		
		 ExtentTest child6 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:green;\" align=\"center\">missing Flights   "+missingFlight+"</b>");
		 child6.log(LogStatus.INFO,""+missingFlight );
		 ExtentTest child66 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:green;\" align=\"center\"> missing flights "+missingFlight+"</b>");
		 child66.log(LogStatus.INFO,""+missingFlight);
		 HtmlReportUtil.test.appendChild(child0).appendChild(child1).appendChild(child2).appendChild(child3).appendChild(child4).appendChild(child5).appendChild(child6);
		 HtmlReportUtil.testHist.appendChild(child0).appendChild(child11).appendChild(child22).appendChild(child33).appendChild(child44).appendChild(child55).appendChild(child66);

		}	
		}
