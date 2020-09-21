package enh.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import enh.db.pages.SQL_Queries;
import utilities.HtmlReportUtil;
import utilities.KeywordUtil;

public class FuelingAndCateringForCelebi extends KeywordUtil{
	
	private static By menu = By.xpath("//mat-icon[contains(.,'menu')] ");
	private static By flightInfo = By.xpath("//a[contains(.,'Flight Info')]");
	private static By turnaroundList = By.xpath("//a[contains(.,'Turnaround List')]");
	private static By departed = By.xpath("//*[@class='mat-tab-label-content'][contains(.,'Departed')]");
	public static String fuelingAndCateringForCelebi_report;
	
	static JavascriptExecutor js = (JavascriptExecutor) getDriver();
	
	/**********************************************************************************************************
	 * Method Name: Verify Fueling and Catering activities
	 * Description : To verify that Fueling and Catering activities are not displayed on UI for Celebi User
	 * Change History:
	 *             Date                     Created By          Change Description        Code Review and date
	 *             ****                     **********            ****************          *******************
	 *         14/Nov/2019                   Padmaja	                                   AD/-/Nov/2019                                      
	 ***********************************************************************************************************/
	public static void verifyFuelingAndCateringActivitiesInTurnaroundList() throws Exception
	{
		int pass=0;
		int fail=0;
		int j=1;
		delay(10000);
		String userName=getElementText(By.xpath("html/body/app-root/div/app-topnav/mat-toolbar/div[4]/div[2]"));
		System.out.println(userName);
		HtmlReportUtil.stepInfo("<b style=\"color:brown;\"> Reports for "+userName+" </b>");
		hoverElement(menu);
		delay(2000);
		hoverElement(flightInfo);
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
		int DepatedFlightsCount=Integer.parseInt(str_DepatedFlightsCount);
		System.out.println(str_DepatedFlightsCount);
		//WebDriverWait w = new WebDriverWait(getDriver(), 20);
		for(int i=1;i<=DepatedFlightsCount;i++)
		{
			try
			{
				delay(5000);
				js.executeScript("arguments[0].scrollIntoView();",getWebElement(By.xpath("//div[@id='selectedCard']["+i+"]")));
				
				//WebElement departedFlightDetails=getWebElement(By.xpath("//div[@id='selectedCard']["+i+"]"));			
				
				//js.executeScript("arguments[0].scrollIntoView();",departedFlightDetails);
				// WebElement ele= w.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='selectedCard']["+i+"]")));
				delay(5000);
				click(By.xpath("//div[@id='selectedCard']["+i+"]"));
				delay(5000);
				String x=getElementText(By.xpath("//div[@id='selectedCard']["+i+"]"));
				//System.out.println(x);
				//getWebElement(By.xpath("//div[@id='selectedCard']["+i+"]")).click();
				delay(2000);
						if(!x.contains("Fuel Arrival") || !x.contains("Catering Arrival") || !x.contains("Catering Connected"))
						{
							pass=pass+1;
							String x1=getElementText(By.xpath("//div[@id='selectedCard']["+i+"]/descendant::p[1]"));
							HtmlReportUtil.stepInfo("<b style=\"color:blue;\">"+i+"  "+x1+"  doesn't contain Fueling or Catering activities </b>");
							System.out.println(i+" Flight: "+x1+" doesn't contain Fueling or Catering activities");
						}
						else
						{
							fail=fail+1;
							//String flightNo = getDriver().findElement(By.xpath("//div[@id='selectedCard']["+i+"]/descendant::p[1]")).getText();
							//w.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='selectedCard']")));
							//System.out.println("Flight Number : "+getDriver().findElement(By.xpath("//div[@id='selectedCard']["+i+"]/descendant::p[1]")).getText()+" doesn't contain ===");
							
							String x1=getElementText(By.xpath("//div[@id='selectedCard']["+i+"]/descendant::p[1]"));
							HtmlReportUtil.stepInfo("<b style=\"color:red;\">"+i+"  "+x1+"  contains the following :</b>");
							System.out.println(i+" Flight: "+x1+" contain Fueling or Catering activities");
							if(x.contains("Fuel Arrival"))
							{
								//HtmlReportUtil.stepInfo("<b style=\"color:red;\">       Fuel Arrival</b>");
								System.out.println("Fuel Arrival");
							}
							if(x.contains("Catering Arrival"))
							{
								//HtmlReportUtil.stepInfo("<b style=\"color:red;\">       Catering Arrival</b>");
								System.out.println("Catering Arrival");
							}
							if(x.contains("Catering Connected"))
							{
								//HtmlReportUtil.stepInfo("<b style=\"color:red;\">       Catering Connected</b>");
								System.out.println("Catering Connected");
							}
						}
						click(By.xpath("//div[@id='selectedCard']["+i+"]"));
						delay(2000);
						try{
							j=j+2;
						js.executeScript("arguments[0].scrollIntoView();",getWebElement(By.xpath("//div[@id='selectedCard']["+j+"]")));
						delay(5000);
						}catch(Exception e)
						{
							//e.printStackTrace();
						} 
						delay(5000);
			}
			catch(Exception e)
			{
				HtmlReportUtil.stepInfo("<b style=\"color:red;\"> "+i+"</b>");
			} 
	}
		HtmlReportUtil.stepInfo("<b style=\"color:green;\">Total number of Flights for Celebi user : "+DepatedFlightsCount+"</b>");
		HtmlReportUtil.stepInfo("<b style=\"color:blue;\">No. of flights for which Fueling and Catering activities are formed : "+pass+"</b>");	
		HtmlReportUtil.stepInfo("<b style=\"color:red;\">No of flights for which Fueling and Catering activities are not formed : "+fail+"</b>");
		String fuelingAndCateringForCelebi1=
		         
       		 "<html> <p>Hi Team, <br> Please find below automation report <br> <b> Celebi user:</b><br></p>"
       		 + "<table width='100%' border='1' align='center'>"
               + "<tr  bgColor=#00b3b3 align='center'>"
               + "<td><b>Date<b></td>"
               + "<td><b>Description<b></td>"
               + "<td><b>Total No. of Flights <b></td>"
               + "</tr>";
		String fuelingAndCateringForCelebi2=fuelingAndCateringForCelebi1+"<tr align='center'>"
		 +"<td>" + SQL_Queries.yesterDate() + "</td>"
           +"<td>" + "Total number of flights "+ "</td>"
           + "<td>" + DepatedFlightsCount + "</td>"
               +"</tr></html>";
		String fuelingAndCateringForCelebi3=fuelingAndCateringForCelebi2+"<tr align='center'>"
				 +"<td>" + SQL_Queries.yesterDate() + "</td>"
		           +"<td>" + "No of flights for which Fueling and Catering activities are formed.  "+ "</td>"
		           + "<td>" + fail + "</td>"
		               +"</tr></html>";
		fuelingAndCateringForCelebi_report=fuelingAndCateringForCelebi3+"<tr align='center'>"
				 +"<td>" + SQL_Queries.yesterDate() + "</td>"
		           +"<td>" + "No of flights for which Fueling and Catering activities are not formed  "+ "</td>"
		           + "<td>" + pass + "</td>"
		           +"</tr></table>"
	                + " <br><br> Thank You, <br> Automation Team </html>";
	}	


}
