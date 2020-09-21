package enh.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;

import enh.db.pages.SQL_Queries;
import utilities.HtmlReportUtil;
import utilities.KeywordUtil;

public class NavigateToFlightInfo extends KeywordUtil{
	private static By menu = By.xpath("//mat-icon[contains(.,'menu')] ");
	private static By flightInfo = By.xpath("//a[contains(.,'Flight Info')]");
	private static By turnaroundList = By.xpath("//a[contains(.,'Turnaround List')]");
	
	private static By ongoingTab = By.xpath("//*[@class='mat-tab-label-content'][contains(.,' Ongoing ')]");
	private static By incomingTab = By.xpath("//*[@class='mat-tab-label-content'][contains(.,' Incoming ')]");
	private static By departedTab = By.xpath("//*[@class='mat-tab-label-content'][contains(.,'Departed')]");
	static JavascriptExecutor js = (JavascriptExecutor) getDriver();


	/**********************************************************************************************************
	 * Method Name: Airport view page verification
	 * Description : To scroll down the page till end in all the Ongoing, Incoming and Departed tabs in Airport view page
	 * Change History:
	 *             Date                     Created By          Change Description        Code Review and date
	 *             ****                     **********            ****************          *******************
	 *         14/Nov/2019                   Padmaja			                             AD/-/Nov/2019                                     
	 ***********************************************************************************************************/
	

	public static void verifyFlightInfo() throws Exception{
		HtmlReportUtil.stepInfo("<b style=\"color:green;\">=========ZestIOT Airportview page verification started=============</b>");
		delay(2000);
		hoverElement(menu);
		hoverElement(flightInfo);
		click(turnaroundList);		
		click(ongoingTab);
		delay(2000);
		clearInput(By.xpath("//input[@name='date']"));
		inputText(By.xpath("//input[@name='date']"), SQL_Queries.yesterDate());
		click(By.xpath("//mat-icon[contains(.,'search')]"));
		delay(5000);
		String str_OngoingFlights = getElementText(ongoingTab);
		String str_OngoingFlightsCount = str_OngoingFlights.replaceAll("[^0-9]", "");
		int OngoingFlightsCount=Integer.parseInt(str_OngoingFlightsCount);
		System.out.println(OngoingFlightsCount);
		WebDriverWait w = new WebDriverWait(getDriver(), 20);
		for(int i=1;i<=OngoingFlightsCount;i++){
			js.executeScript("arguments[0].scrollIntoView();",getWebElement(By.xpath("//div[@id='selectedCard']["+i+"]")));
		}

}
}
