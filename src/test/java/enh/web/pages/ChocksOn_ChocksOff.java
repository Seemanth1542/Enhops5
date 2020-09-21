package enh.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import enh.db.pages.SQL_Queries;
import utilities.HtmlReportUtil;
import utilities.KeywordUtil;

public class ChocksOn_ChocksOff extends KeywordUtil {
	private static By menu = By.xpath("//mat-icon[contains(.,'menu')] ");
	private static By flightInfo = By.xpath("//a[contains(.,'Flight Info')]");
	private static By turnaroundList = By.xpath("//a[contains(.,'Turnaround List')]");
	private static By departed = By.xpath("//*[@class='mat-tab-label-content'][contains(.,'Departed')]");
	private static By searchButton = By.xpath("//mat-icon[contains(.,'search')]");
	
	
	static JavascriptExecutor js = (JavascriptExecutor) getDriver();
	
	/**********************************************************************************************************
	 * Method Name: Verify Chocks On, On-Block, Off-Block, Chocks off 
	 * Description : To verify that Chocks On, On-Block, Off-Block, Chocks off timestamps are displayed on UI
	 * Change History:
	 *             Date                     Created By          Change Description        Code Review and date
	 *             ****                     **********            ****************          *******************
	 *         19/Nov/2019                   Padmaja	                                   AD/-/Nov/2019                                      
	 ***********************************************************************************************************/
	public static void verifyChoOnblOfblChfActivityInTurnaroundList() throws Exception
	{
		int activityFormed=0;
		int noActivity=0;
		int missingflight=0;
		int j=1;
		delay(5000);
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
		//inputText(By.xpath("//input[@name='date']"), "19/11/2019");
		delay(2000);
		System.out.println("upto delay");
		click(searchButton);
		delay(10000);
		System.out.println("done");
		String str_DepatedFlights = getElementText(departed);
		String str_DepatedFlightsCount = str_DepatedFlights.replaceAll("[^0-9]", "");
		int DepatedFlightsCount=Integer.parseInt(str_DepatedFlightsCount);
		System.out.println(str_DepatedFlightsCount);
		//WebDriverWait w = new WebDriverWait(getDriver(), 20);
		for(int i=1;i<=DepatedFlightsCount;i++)
		{	
			try {
				delay(3000);
				//WebElement departedFlightDetails=getWebElement(By.xpath("//div[@id='selectedCard']["+i+"]"));
				js.executeScript("arguments[0].scrollIntoView();",getWebElement(By.xpath("//div[@id='selectedCard']["+i+"]")));
				delay(3000);
				//w.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='selectedCard']["+i+"]")));
				click(By.xpath("//div[@id='selectedCard']["+i+"]"));
				delay(2000);
				String activityList=getElementText(By.xpath("//div[@id='selectedCard']["+i+"]"));
				delay(2000);
				//String elm =getElementText(By.xpath("//div[@id='selectedCard']["+i+"]/descendant::p[1]"));
				//System.out.println(elmList);
				//System.out.println(elm);
				if( activityList.contains("On Block") && activityList.contains("Off Block") )
						{
							activityFormed= activityFormed+1;
							String flightNo =getElementText(By.xpath("//div[@id='selectedCard']["+i+"]/descendant::p[1]"));
							//delay(1000);
							HtmlReportUtil.stepInfo("<b style=\"color:blue;\">"+i+" Flight: "+flightNo+" contains On-block, Off-block activities </b>");
							System.out.println(i+" Flight: "+flightNo+" contain Chocks-on, On-block, Off-block, Chocks-off all activities");
						}
						else
						{
							noActivity= noActivity+1;
							String flightNo=getElementText(By.xpath("//div[@id='selectedCard']["+i+"]/descendant::p[1]"));
							//delay(1000);
							HtmlReportUtil.stepInfo("<b style=\"color:red;\">"+i+" Flight: "+flightNo+"  doesn't contain following activities :</b>");
							System.out.println(i+" Flight: "+flightNo+" doesn't contain Chocks-on, On-block, Off-block, Chocks-off  activities");
							
							if(!activityList.contains("On Block"))
							{
								HtmlReportUtil.stepInfo("<b style=\"color:;\">       On Block</b>");
								System.out.println("On Block");
							}
							if(!activityList.contains("Off Block"))
							{
								HtmlReportUtil.stepInfo("<b style=\"color:;\">       Off Block</b>");
								System.out.println("Off Block");
							}
							
							
						}
				click(By.xpath("//div[@id='selectedCard']["+i+"]"));
				 delay(2000);
					try {
						j=j+1;
		 	        js.executeScript("arguments[0].scrollIntoView();",getWebElement(By.xpath("//div[@id='selectedCard']["+j+"]")));	
		 	       delay(3000);
					}catch(Exception e) {
						
		}	
						
							
			}
			catch(Exception e) {
				missingflight=missingflight+1;
				HtmlReportUtil.stepInfo("<b style=\"color:brown;\">"+i+" "+getElementText(By.xpath("//div[@id='selectedCard']["+i+"]")) +"</b>");	
				
			}	
			
		}
		
		HtmlReportUtil.stepInfo("<b style=\"color:blue;\">Total number of Flights in Hyderabad : "+DepatedFlightsCount+"</b>");
		HtmlReportUtil.stepInfo("<b style=\"color:green;\">No. of flights for which On-block, Off-block are formed : "+activityFormed+"</b>");	
		HtmlReportUtil.stepInfo("<b style=\"color:red;\">No. of flights for which  On-block, Off-block are not formed : "+noActivity+"</b>");
		HtmlReportUtil.stepInfo("<b style=\"color:brown;\">No. of missing flights in automation  : "+missingflight+"</b>");	
	}
	}


	
	




