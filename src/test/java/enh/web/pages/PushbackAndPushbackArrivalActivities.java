package enh.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;

import enh.db.pages.SQL_Queries;
import utilities.HtmlReportUtil;
import utilities.KeywordUtil;

public class PushbackAndPushbackArrivalActivities extends KeywordUtil {
	private static By menu = By.xpath("//mat-icon[contains(.,'menu')] ");
	private static By flightInfo = By.xpath("//a[contains(.,'Flight Info')]");
	private static By turnaroundList = By.xpath("//a[contains(.,'Turnaround List')]");
	private static By departed = By.xpath("//*[@class='mat-tab-label-content'][contains(.,'Departed')]");
	
	public static String pushbackArrivalAndPushbackActivity_report;
	
	
	static JavascriptExecutor js = (JavascriptExecutor) getDriver();
	public static int DepatedFlightsCount=0;
	public static int pushbackAndArrival=0;
	public static int pushback=0;
	public static int pushback_NoPBT = 0;
	public static int PBT_NoPushback=0;
	public static int pbArrival =0;
	public static int noPBTnoPushback =0;
	public static int missingFlight=0;
	public static int j=1;
		
	/**********************************************************************************************************
	 * Method Name: Verify Pushback arrival and Pushback activities
	 * Description : To verify that Pushback arrival and Pushback activities are displayed on UI
	 * Change History:
	 *             Date                     Created By          Change Description        Code Review and date
	 *             ****                     **********            ****************          *******************
	 *         14/Nov/2019                   Padmaja	                                   AD/-/Nov/2019                                      
	 ***********************************************************************************************************/
	public static void verifyPushbackActivityInTurnaroundList() throws Exception
	{
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
			try
			{
				delay(3000);
				js.executeScript("arguments[0].scrollIntoView();",getWebElement(By.xpath("//div[@id='selectedCard']["+i+"]")));
				
				//WebElement departedFlightDetails=getWebElement(By.xpath("//div[@id='selectedCard']["+i+"]"));
				
				delay(3000);
				//js.executeScript("arguments[0].scrollIntoView();",departedFlightDetails);
				//WebElement ele= w.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='selectedCard']["+i+"]")));
				click(By.xpath("//div[@id='selectedCard']["+i+"]"));
				delay(3000);
				//String x=departedFlightDetails.getText();
				String flightInfoText=getElementText(By.xpath("//div[@id='selectedCard']["+i+"]"));
				//System.out.println("text"+ xhockText);
				//getWebElement(By.xpath("//div[@id='selectedCard']["+i+"]")).click();
				
						if(flightInfoText.contains("Pushback") && (flightInfoText.contains("Pushback Arrival")))
						{
							pushbackAndArrival= pushbackAndArrival+1;
							String x1=getElementText(By.xpath("//div[@id='selectedCard']["+i+"]/descendant::p[1]"));
							HtmlReportUtil.stepInfo("<b style=\"color:green;\">"+i+" Flight: "+x1+" both Pushback arrival and Pushback are formed </b>");
						}
						if(flightInfoText.contains("Pushback") && (!flightInfoText.contains("Pushback Arrival")))
						{
							pushback_NoPBT = pushback_NoPBT+1;
							String x1=getElementText(By.xpath("//div[@id='selectedCard']["+i+"]/descendant::p[1]"));
							HtmlReportUtil.stepInfo("<b style=\"color:red;\">"+i+" Flight: "+x1+" Pushback is formed but Pushback Arrival is not formed </b>");
						}
						if(!flightInfoText.contains("Pushback") && (flightInfoText.contains("Pushback Arrival")))
						{
							PBT_NoPushback = PBT_NoPushback+1;
							String x1=getElementText(By.xpath("//div[@id='selectedCard']["+i+"]/descendant::p[1]"));
							HtmlReportUtil.stepInfo("<b style=\"color:red;\">"+i+" Flight: "+x1+" Pushback arrival is formed but Pushback is not formed </b>");
						}
						if(flightInfoText.contains("Pushback"))
						{
								pushback= pushback+1;
								String x1=getElementText(By.xpath("//div[@id='selectedCard']["+i+"]/descendant::p[1]"));
								HtmlReportUtil.stepInfo("<b style=\"color:black;\">"+i+" Flight: "+x1+" only Pushback is formed </b>");
						}
						if(flightInfoText.contains("Pushback Arrival"))
							{
								pbArrival= pbArrival+1;
								String x1=getElementText(By.xpath("//div[@id='selectedCard']["+i+"]/descendant::p[1]"));
								HtmlReportUtil.stepInfo("<b style=\"color:black;\">"+i+" Flight: "+x1+" only Pushback Arrival is formed </b>");	
							}
						if(!flightInfoText.contains("Pushback") && (!flightInfoText.contains("Pushback Arrival")))
						{
							noPBTnoPushback = noPBTnoPushback+1;
							String x1=getElementText(By.xpath("//div[@id='selectedCard']["+i+"]/descendant::p[1]"));
							HtmlReportUtil.stepInfo("<b style=\"color:red;\">"+i+" Flight: "+x1+" neither pushback nor pushback arrival is formed </b>");
							
						}
						click(By.xpath("//div[@id='selectedCard']["+i+"]"));
						delay(2000);
						try{
							j=j+1;
							js.executeScript("arguments[0].scrollIntoView();",getWebElement(By.xpath("//div[@id='selectedCard']["+j+"]")));	
							delay(3000);
						}catch(Exception e)
						{
							//e.printStackTrace();
						} 
						
						
			}
			catch(Exception e)
			{
				missingFlight=missingFlight+1;
				//e.printStackTrace();
				HtmlReportUtil.stepInfo("<b style=\"color:red;\"> "+i+" - "+getElementText(By.xpath("//div[@id='selectedCard']["+i+"]"))+"</b>");
			} 

		}
		HtmlReportUtil.stepInfo("<b style=\"color:blue;\">Total number of Flights for Celebi user : "+DepatedFlightsCount+"</b>");
		HtmlReportUtil.stepInfo("<b style=\"color:green;\">No. of flights for which both Pushback arrival and Pushback are formed : "+pushbackAndArrival+"</b>");
		HtmlReportUtil.stepInfo("<b style=\"color:red;\">No. of flights for which pushback is formed but pushback arrival is not formed : "+pushback_NoPBT+"</b>");
		HtmlReportUtil.stepInfo("<b style=\"color:red;\">No. of flights for which pushback arrival is formed but pushback is not formed : "+PBT_NoPushback+"</b>");
		HtmlReportUtil.stepInfo("<b style=\"color:black;\">No. of flights with only Pushback formed : "+pushback+"</b>");
		HtmlReportUtil.stepInfo("<b style=\"color:black;\">No. of flights with only Pushback Arrival formed : "+pbArrival+"</b>");
		HtmlReportUtil.stepInfo("<b style=\"color:red;\">No. of flights for which both Pushback and Pushback Arrival are not formed : "+noPBTnoPushback+"</b>");
		HtmlReportUtil.stepInfo("<b style=\"color:brown;\">No. of missing flights in automation execution : "+missingFlight+"</b>");
		
	
	
	
	}	
		}
	/*	String pushbackArrivalAndPushbackActivity=
		         
     		 "<html> <p>Hi Team, <br> Please find below automation report <br> <b> Delhi:</b><br></p>"
       		 + "<table width='100%' border='1' align='center'>"
               + "<tr  bgColor=#00b3b3 align='center'>"
               + "<td><b>Date<b></td>"
               + "<td><b>Description<b></td>"
               + "<td><b>Total No. of Flights <b></td>"
               + "</tr>";
		String pushbackArrivalAndPushbackActivity2=pushbackArrivalAndPushbackActivity+"<tr align='center'>"
		 +"<td>" + SQL_Queries.yesterDate() + "</td>"
           +"<td>" + "Total number of flights "+ "</td>"
           + "<td>" + DepatedFlightsCount + "</td>"
               +"</tr></html>";
		String pushbackArrivalAndPushbackActivity3=pushbackArrivalAndPushbackActivity2+"<tr align='center'>"
				 +"<td>" + SQL_Queries.yesterDate() + "</td>"
		           +"<td>" + "No. of flights for which Pushback arrival and Pushback both the activities are formed.  "+ "</td>"
		           + "<td>" + pass + "</td>"
		               +"</tr></html>";
		pushbackArrivalAndPushbackActivity_report=pushbackArrivalAndPushbackActivity3+"<tr align='center'>"
				 +"<td>" + SQL_Queries.yesterDate() + "</td>"
		           +"<td>" + "No. of flights for which either Pushback arrival or Pushback activity is formed  "+ "</td>"
		           + "<td>" + fail + "</td>"
		           +"</tr></table>"
	                + " <br><br> Thank You, <br> Automation Team </html>"; */
			



