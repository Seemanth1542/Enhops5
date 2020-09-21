package enh.web.pages;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import enh.db.pages.SQL_Queries;
import utilities.HtmlReportUtil;
import utilities.KeywordUtil;

public class DepartedFlights extends KeywordUtil{
	
	static JavascriptExecutor js = (JavascriptExecutor) getDriver();
	static String lessThan = "<";
	static String equals = "=";
	static String str_ChocksOff;
	static String str_ChocksOn;
	static LocalTime int_ATATim;
	static LocalTime int_ChocksOn;
	static LocalTime int_Landed;
	static String str_AirBorneTime;
	static LocalTime int_ChocksOff;
	static String str_ATDTime;
	static LocalTime time_ATDTime;
	static java.util.Date date_Landed_Date;
	static java.util.Date date_AirBorneTime_Date;
	static int b;
	static int bay;
	static int belt;
	static int gate;
	static SoftAssert softAssertion= new SoftAssert();

	private static By menu = By.xpath("//mat-icon[contains(.,'menu')] ");
	private static By flightInfo = By.xpath("//a[contains(.,'Flight Info')]");
	private static By turnaroundList = By.xpath("//a[contains(.,'Turnaround List')]");
	private static By departed = By.xpath("//*[@class='mat-tab-label-content'][contains(.,'Departed')]");
	private static By matIcon = By.xpath(
			"/html/body/app-root/div/mat-sidenav-container/mat-sidenav-content/app-airport-view-v1/div/div[1]/button[2]/mat-icon");
	private static By chocksOnTime1 = By.xpath("//div[@title='Bay Chocks-On']/following-sibling::div/div[2]/div/p/span[1]");
	private static By chocksOnTime2 = By.xpath("(//div[@title='Bay Chocks-On']/following-sibling::div/div[2]/div[1]/p/span[1])[2]");
	private static By chocksOffTime1 = By.xpath("//div[@title=\"Bay Chocks-Off\"]/following-sibling::div/div[2]/div/p/span[2]");
	private static By chocksOffTime2 = By.xpath("(//div[@title=\"Bay Chocks-Off\"]/following-sibling::div)[1]/div[1]/div[1]/p/span[2]");
	//---------------------------VerifyAlert------------------------------
	
	
	/**********************************************************************************************************
	 * Method Name: VerifyDepatedFlightDetailsWithDB
	 * Description : 
	 * Change History:
	 *             Date                     Created By          Change Description        Code Review and date
	 *             ****                     **********            ****************          *******************
	 *         21/Sept/2019                   Rajesh			                                        AD/-/Sept/2019                                     
	 ***********************************************************************************************************/
	public static void VerifyDepatedFlightDetailsWithDB() throws InterruptedException, SQLException {

		HtmlReportUtil.stepInfo(
				"<b style=\"color:green;\">################# ZestIOT DB Access Function Started #################</b>");
		 List<String> flightsDB = DB.getDBConnectionAndExecuteQuery_FlightNumberArrival();
		DB.dbConnectionClose();
		HtmlReportUtil.stepInfo(
				"<b style=\"color:green;\">############## ZestIOT DB Access Function Ended #############</b>");

		// #########################DB Ending###############################
		
		
		HtmlReportUtil.stepInfo(
				"<b style=\"color:green;\">############## ZestIOT Departed Flights Validation with DB Function Started #############</b>");
		click(departed);
		delay(2000);
		String str_DepatedFlights = getElementText(departed);
		String str_DepatedFlightsCount = str_DepatedFlights.replaceAll("[^0-9]", "");

		System.out.println(str_DepatedFlightsCount);
		click(matIcon);
		delay(1000);
		List<WebElement> elm_flight = getListElements(By.cssSelector(".flightno-col p"));
		for (WebElement webElement1 : elm_flight) {
			js.executeScript("arguments[0].scrollIntoView();", webElement1);
			delay(2000);
		}

		java.util.List<String> flightsUi = new ArrayList<>();
		List<WebElement> elm_flights = getListElements(By.cssSelector(".flightno-col p"));
		for (WebElement webElement : elm_flights) {
			delay(250);
			String str_DepartedFlights = webElement.getText().split("-")[0].trim();
			flightsUi.add(str_DepartedFlights);
		}

		HtmlReportUtil.stepInfo("<b style=\"color:green;\">############## Total flights counts in UI: "
				+ flightsUi.size() + " #############</b>");
		HtmlReportUtil.stepInfo("<b style=\"color:green;\">############## Total flights counts in DB: "
				+ flightsDB.size() + " #############</b>");
		System.out.println("Total flights counts in UI: " + flightsUi.size());
		System.out.println("Total flights counts in DB: " + flightsDB.size());

		Collections.sort(flightsUi);
		System.out.println("UI: " + flightsUi.toString());

		for (String fl : flightsUi) {
			if (flightsDB.contains(fl)) {
				HtmlReportUtil.stepInfo("<b style=\"color:green;\">############## Data is available in db and ui: " + fl
						+ " #############</b>");
				System.out.println("Data is available in db and ui: " + fl);
				
			} else {
				HtmlReportUtil.stepInfo("<b style=\"color:red;\">############## Data not is available in db and ui: "
						+ fl + " #############</b>");
				System.out.println("Data not available in db and ui: " + fl);
				softAssertion.assertTrue(false);
			}

		}
		softAssertion.assertAll();
		HtmlReportUtil.stepInfo(
				"<b style=\"color:green;\">############## ZestIOT Departed Flights Validation with DB Function Ended #############</b>");
	}

	
	/**********************************************************************************************************
	 * Method Name: VerifyingTime
	 * Description : 
	 * Change History:
	 *             Date                     Created By          Change Description        Code Review and date
	 *             ****                     **********            ****************          *******************
	 *         21/Sept/2019                   Rajesh			                                        AD/-/Sept/2019                                      
	 ***********************************************************************************************************/
	public static void VerifyingATAandATDTime() throws InterruptedException, SQLException, IOException {
		
		hoverElement(menu);
		hoverElement(flightInfo);
		click(turnaroundList);
		HtmlReportUtil.stepInfo(
				"<b style=\"color:green;\">################# ZestIOT DB Access Function Started #################</b>");
		HtmlReportUtil.stepInfo(
				"<b style=\"color:green;\">############## ZestIOT DB Access Function Ended #############</b>");

		// #########################DB Ending###############################

		HtmlReportUtil.stepInfo(
				"<b style=\"color:green;\">############## ZestIOT Departed Flights Validation with DB Function Started #############</b>");
		click(departed);
		delay(2000);
		String str_DepatedFlights = getElementText(departed);
		String str_DepatedFlightsCount = str_DepatedFlights.replaceAll("[^0-9]", "");
		System.out.println(str_DepatedFlightsCount);
		delay(1000);
		List<WebElement> elm_flight = getListElements(By.cssSelector(".flightno-col p"));
		for (WebElement webElement1 : elm_flight) {
			js.executeScript("arguments[0].scrollIntoView();", webElement1);
			delay(2000);
		}
	
		List<String> flightsUi = new ArrayList<>();
		List<WebElement> elm_flights = getListElements(By.cssSelector(".flightno-col p"));
		System.out.println(elm_flights.size());
		for (int i = 0; i <= elm_flights.size()-3; i++) {
			delay(500);
			try {
			b = i+1;
			String str_DepartedFlights = elm_flights.get(i).getText().split("-")[0].trim();
			flightsUi.add(str_DepartedFlights);
			elm_flights.get(i).click();
			hoverElement(By.xpath("(//*[@id=\"selectedCard\"]/div/div[1]/div[7]/div/div[1])["+b+"]"));
			HtmlReportUtil.stepInfo("<b style=\"color:green;\">================Departed Flight Number: "+str_DepartedFlights+"===============</b>");
			
			//######################## Capture Landed Info ##############################
			String strLanded = getElementText(By.xpath("(//*[@id='selectedCard']/div/div[1]/div[7]/div/div[2]/p[4])["+b+"]"));
			System.out.println(strLanded);
			String[] strLanded1  = strLanded.split(" ");
			String str_Landed = strLanded1[2];
			String str_Landed_Date = strLanded1[1];
			int_Landed = LocalTime.parse(str_Landed);
			date_Landed_Date = new SimpleDateFormat("dd/MM/yyyy").parse(str_Landed_Date); 
			HtmlReportUtil.stepInfo("<b style=\"color:green;\">Landed Time: "+str_Landed+"</b>");
			
			//######################## ATA Info ##############################
			String strATATime = getElementText(By.xpath("(//*[@id=\"selectedCard\"]/div/div[1]/div[7]/div/div[2]/p[3]/span)["+b+"]"));
			delay(500);
			System.out.println(strATATime);
			String[] strATATime1  = strATATime.split(" ");
			String str_ATATime = strATATime1[1];
			System.out.println(str_ATATime);
			int_ATATim = LocalTime.parse(str_ATATime);
			click(By.xpath("//*[@id=\"staticheader\"]/div[2]"));
			HtmlReportUtil.stepInfo("<b style=\"color:green;\">ATA Time: "+strATATime+"</b>");
			
			//######################## Chocks On Info ##############################
			boolean ChocksOnTime = getDriver().findElements(chocksOnTime1).size()>0;
			if(ChocksOnTime) {
				str_ChocksOn = getElementText(chocksOnTime1);
			}else {
				str_ChocksOn = getElementText(chocksOnTime2);		
			}
			HtmlReportUtil.stepInfo("<b style=\"color:green;\">Chocks On Time: "+str_ChocksOn+"</b>");
			int_ChocksOn = LocalTime.parse(str_ChocksOn);
			
			//######################## Chocks Off Info ##############################
			boolean ChocksOff = getDriver().findElements(chocksOffTime1).size()>0;
				//getDriver().findElements(By.xpath("((//div[@title=\"Bay Chocks-Off\"]/following-sibling::div)[3]/div/div/p/span[2])[2]")).size()>0;
			if(ChocksOff) {
				str_ChocksOff = getElementText(chocksOffTime1);
			}else {
				str_ChocksOff = getElementText(chocksOffTime2);	
				softAssertion.assertTrue(ChocksOff);
				
			}
			int_ChocksOff = LocalTime.parse(str_ChocksOff);
			//######################## AirBorne Off Info ##############################
			hoverElement(By.xpath("(//*[@id=\"selectedCard\"]/div/div[1]/div[8]/div/div[1])["+b+"]"));
			String strAirBorneTime = getElementText(By.xpath("(//p[contains(.,' AIRBORNE')])["+b+"]"));	
			String[] strAirBorneTime1  = strAirBorneTime.split(" ");
			str_AirBorneTime = strAirBorneTime1[2];
			String str_AirBorneTime_Date = strAirBorneTime1[1];
			date_AirBorneTime_Date = new SimpleDateFormat("dd/MM/yyyy").parse(str_AirBorneTime_Date);
			
			/*//######################## ATD Info ##############################
			String strATDTime = getElementText(By.xpath("(//*[@id='selectedCard']/div/div[1]/div[8]/div/div[2]/p[4])["+b+"]"));
			delay(1000);	
			String[] strATDTime1  = strATDTime.split(" ");
			str_ATDTime = strATDTime1[2];
			time_ATDTime = LocalTime.parse(str_ATDTime);
			click(By.xpath("//*[@id=\"staticheader\"]/div[2]"));*/

			//######################## Landed time less than ATA on time ##############################
			boolean Landed_Less_Than_ATA = int_ATATim.isAfter(int_Landed);
			if(Landed_Less_Than_ATA) {
				HtmlReportUtil.stepInfo("<b style=\"color:green;\">Landed Time Less then ATA Time: "+int_Landed+""+lessThan+""+int_ATATim+"</b>");
			}else {
				softAssertion.assertTrue(Landed_Less_Than_ATA);
				HtmlReportUtil.stepInfo("<b style=\"color:red;\">Landed Time Less then ATA Time: "+int_Landed+""+lessThan+""+int_ATATim+"</b>");
			}
			
			//######################## ATA Time Equals to Chocks on Time ##############################
			boolean ATATim_Less_Than_ChocksOn = int_ChocksOn.equals(int_ATATim);
			if(ATATim_Less_Than_ChocksOn) {
				HtmlReportUtil.stepInfo("<b style=\"color:green;\">ATA Time Equals to Chocks on Time: "+int_ATATim+""+equals+""+int_ChocksOn+"</b>");
			}else {
				softAssertion.assertTrue(ATATim_Less_Than_ChocksOn);
				HtmlReportUtil.stepInfo("<b style=\"color:red;\">ATA Time is not Equals to Chocks on Time: "+int_ATATim+""+equals+""+int_ChocksOn+"</b>");
			}
			
			//######################## Chocks on time less than Chocks off time ###############################
			boolean ChocksOn_Less_Than_ChocksOff = int_ChocksOff.isAfter(int_ChocksOn);
			if(ChocksOn_Less_Than_ChocksOff) {
				HtmlReportUtil.stepInfo("<b style=\"color:green;\">Chocks on Time Less then Chocks Off Time: "+int_ChocksOn+""+lessThan+""+int_ChocksOff+"</b>");
			}else {
				softAssertion.assertTrue(ChocksOn_Less_Than_ChocksOff);
				HtmlReportUtil.stepInfo("<b style=\"color:red;\">Chocks on Time is not Less then Chocks Off Time: "+int_ChocksOn+""+lessThan+""+int_ChocksOff+"</b>");
			}
			
			/*//######################## Chocks off Time  Equals to ATD time ###############################
			boolean ChocksOff_Equals_To_ATD = int_ChocksOff.equals(time_ATDTime);
			if(ChocksOff_Equals_To_ATD) {
				HtmlReportUtil.stepInfo("<b style=\"color:green;\">Chocks off Time Equals to ATD time: "+int_ChocksOff+"="+int_ATATim+"</b>");
			}else {
				HtmlReportUtil.stepInfo("<b style=\"color:red;\">Chocks off Time not Equals to ATD time: "+int_ChocksOff+"="+int_ATATim+"</b>");
			}
			*/
			
			//######################## Chocks off time less than AirBorne time ###############################
			
			LocalTime time_AirBorneTime = LocalTime.parse(str_AirBorneTime);	
			boolean ChocksOff_Less_Than_AirBorne = time_AirBorneTime.isAfter(int_ChocksOff);
			boolean Landed_Date_Equal_to_AirBorne = date_Landed_Date.equals(date_AirBorneTime_Date);
			
			if(Landed_Date_Equal_to_AirBorne) {
			if(ChocksOff_Less_Than_AirBorne) {
				HtmlReportUtil.stepInfo("<b style=\"color:green;\">Chocks Off Time Less then AirBorne Time: "+int_ChocksOff+""+lessThan+""+time_AirBorneTime+"</b>");
			}else {
				softAssertion.assertTrue(ChocksOff_Less_Than_AirBorne);
				HtmlReportUtil.stepInfo("<b style=\"color:red;\">Chocks Off Time is not Less then AirBorne Time: "+int_ChocksOff+""+lessThan+""+time_AirBorneTime+"</b>");
			}
			}else {
				softAssertion.assertTrue(ChocksOff_Less_Than_AirBorne);
				HtmlReportUtil.stepInfo("<b style=\"color:blue;\">Landed and airborne time is different: "+date_Landed_Date+"Not equal"+date_AirBorneTime_Date+"</b>");
			}
			clickJSElm(elm_flights.get(i));
			js.executeScript("arguments[0].scrollIntoView();", elm_flights.get(b));
			
			}catch (Exception e) {
				
				HtmlReportUtil.stepInfo("<b style=\"color:red;\">Exception: "+e+"</b>");
				
				clickJSElm(elm_flights.get(i));
				js.executeScript("arguments[0].scrollIntoView();", elm_flights.get(b));
			}
		}
		softAssertion.assertAll();
	}
	

	/**********************************************************************************************************
	 * Method Name: Verify_BFL_PushBack_Activity
	 * Description : 
	 * Change History:
	 *             Date                     Created By          Change Description        Code Review and date
	 *             ****                     **********            ****************          *******************
	 *         31/Oct/2019                   Rajesh			                                   AD/-/OCT/2019                                      
	 ***********************************************************************************************************/
	public static void Verify_BFL_PushBack_Activity() throws InterruptedException, SQLException, IOException {
		
		click(menu);
		click(flightInfo);
		click(turnaroundList);
		click(departed);
		delay(2000);
		
		clearInput(By.xpath("//input[@name='date']"));
		inputText(By.xpath("//input[@name='date']"), SQL_Queries.yesterDate());
		delay(2000);
		click(By.xpath("//mat-icon[contains(.,'search')]"));
		delay(3000);
		//mat-card[@class="main-card mat-card"]
		String str_DepatedFlights = getElementText(departed);
		String str_DepatedFlightsCount = str_DepatedFlights.replaceAll("[^0-9]", "");
		System.out.println(str_DepatedFlightsCount);
		int int_DepatedFlightsCount = Integer.parseInt(str_DepatedFlightsCount);
		
		for (int i = 0; i < int_DepatedFlightsCount-5; i++) {
			int scrollCount = i+1;
			WebElement flightinfo = getDriver().findElement(By.xpath("(//div[@class='flightno-col'])["+scrollCount+"]"));
			js.executeScript("arguments[0].scrollIntoView();", flightinfo);
			System.out.println("scroll: " + scrollCount);
			delay(2000);
		}
		
		List<WebElement> elm_flights = getListElements(By.cssSelector(".flightno-col p"));
		System.out.println(elm_flights.size());
		for (int i = 0; i <= elm_flights.size()-3; i++) {
			delay(500);
			b = i+1;
			String str_DepartedFlights = elm_flights.get(i).getText().split("-")[0].trim();
			js.executeScript("arguments[0].scrollIntoView();", elm_flights.get(i));
			elm_flights.get(i).click();
			delay(500);
			boolean bfl_Arrival = getDriver().findElements(By.xpath("//span[contains(.,'BFL Arrival')]")).size()>0;
			boolean bfl_Connected = getDriver().findElements(By.xpath("//span[contains(.,'BFL Connected')]")).size()>0;
			boolean hold_Open = getDriver().findElements(By.xpath("//span[contains(.,'Hold Open')]")).size()>0;
			boolean baggage_Unloading = getDriver().findElements(By.xpath("//span[contains(.,'Baggage Unloading')]")).size()>0;
			boolean baggage_Loading = getDriver().findElements(By.xpath("//span[contains(.,'Baggage Loading')]")).size()>0;
			if (bfl_Arrival) {
				
				HtmlReportUtil.stepInfo("<b style=\"color:blue;\">======================================= Flight No: "+str_DepartedFlights+" ======================================</b>");
				HtmlReportUtil.stepInfo("<b style=\"color:green;\">--> BFL arrival activity found</b>");
				System.out.println("BFL activity found");
				if (bfl_Connected) {
					HtmlReportUtil.stepInfo("<b style=\"color:green;\">--> BFL connected activity found</b>");
					System.out.println("BFL activity found");
					elm_flights.get(i).click();
				}else {
					HtmlReportUtil.stepInfo("<b style=\"color:red;\">--> BFL connected activity not found</b>");
					System.out.println("BFL activity not found");
					softAssertion.assertTrue(bfl_Connected);
					elm_flights.get(i).click();
				}
				
				if (hold_Open) {
					HtmlReportUtil.stepInfo("<b style=\"color:green;\">--> Hold open activity found</b>");
					System.out.println("BFL activity found");
					elm_flights.get(i).click();
				}else {
					HtmlReportUtil.stepInfo("<b style=\"color:red;\">--> Hold open not found</b>");
					System.out.println("BFL activity not found");
					softAssertion.assertTrue(hold_Open);
					elm_flights.get(i).click();
				}
				
				if (baggage_Unloading) {
					HtmlReportUtil.stepInfo("<b style=\"color:green;\">--> Baggage unloading activity found</b>");
					System.out.println("BFL activity found");
					elm_flights.get(i).click();
				}else {
					HtmlReportUtil.stepInfo("<b style=\"color:red;\">--> Baggage unloading activity not found</b>");
					System.out.println("BFL activity not found");
					softAssertion.assertTrue(baggage_Unloading);
					elm_flights.get(i).click();
				}
				
				if (baggage_Loading) {
					HtmlReportUtil.stepInfo("<b style=\"color:green;\">--> Baggage Loading activity found</b>");
					System.out.println("BFL activity found");
					elm_flights.get(i).click();
				}else {
					HtmlReportUtil.stepInfo("<b style=\"color:red;\">--> Baggage Loading activity not found</b>");
					System.out.println("BFL activity not found");
					softAssertion.assertTrue(baggage_Loading);
					elm_flights.get(i).click();
				}
			
				elm_flights.get(i).click();
			}else {
				
				elm_flights.get(i).click();
			}
			
			
	
	
		}	
		//softAssertion.assertAll();
	
}
	
	/**********************************************************************************************************
	 * Method Name: Verify_BeltNumber_BayArea_GateNumber
	 * Description : 
	 * Change History:
	 *             Date                     Created By          Change Description        Code Review and date
	 *             ****                     **********            ****************          *******************
	 *         31/Oct/2019                   Rajesh			                                   AD/-/OCT/2019                                      
	 ***********************************************************************************************************/
	public static void Verify_BeltNumber_BayArea_GateNumber() throws InterruptedException, SQLException, IOException {
		
		hoverElement(menu);
		delay(2000);
		hoverElement(flightInfo);
		click(turnaroundList);
		click(departed);
		delay(2000);
		clearInput(By.xpath("//input[@name='date']"));
		inputText(By.xpath("//input[@name='date']"), "05/11/2019");
		click(By.xpath("//mat-icon[contains(.,'search')]"));
		delay(5000);
		String str_DepatedFlights = getElementText(departed);
		String str_DepatedFlightsCount = str_DepatedFlights.replaceAll("[^0-9]", "");
		System.out.println(str_DepatedFlightsCount);
		int int_DepatedFlightsCount = Integer.parseInt(str_DepatedFlightsCount);
		
		
		for (int i = 0; i < int_DepatedFlightsCount-5; i++) {
			int scrollCount = i+1;
			WebElement flightinfo = getDriver().findElement(By.xpath("(//div[@class='flightno-col'])["+scrollCount+"]"));
			js.executeScript("arguments[0].scrollIntoView();", flightinfo);
			System.out.println("scroll: " + scrollCount);
			delay(2000);
		}
		
		List<WebElement> elm_flights = getListElements(By.cssSelector(".flightno-col p"));
		System.out.println(elm_flights.size());
		for (int i = 0; i <= elm_flights.size()-5; i++) {
			delay(500);
			bay = i+1;
			belt = i+2;
			gate = i+2;
			
			String str_DepartedFlights = elm_flights.get(i).getText().split("-")[0].trim();
			js.executeScript("arguments[0].scrollIntoView();", elm_flights.get(i));
			
			String str_Bay = getElementText(By.xpath("(//div[starts-with(@title,'bay')])["+bay+"]"));
			System.out.println(str_Bay);
			
			String str_Belt = getElementText(By.xpath("(//div[@class='belt-col'])["+belt+"]"));
			System.out.println(str_Belt);
			
			String str_Gate = getElementText(By.xpath("(//div[@class='gate-col'])["+gate+"]"));
			System.out.println(str_Gate);
			
			//HtmlReportUtil.stepInfo("<b style=\"color:green;\">-->>Bay: "+str_Bay+"  -->>Belt: "+str_Belt+"  -->>Gate: "+str_Gate+"</b>");
			
			
			if(str_Bay.equals("")) {
				HtmlReportUtil.stepInfo("<b style=\"color:blue;\">======================================= Flight No: "+str_DepartedFlights+" ======================================</b>");
				softAssertion.assertTrue(false);
				HtmlReportUtil.stepInfo("<b style=\"color:red;\">-->>Bay: "+str_Bay+"</b>");
			}else {
				
			}
			
			if(str_Belt.equals("")) {
				HtmlReportUtil.stepInfo("<b style=\"color:blue;\">======================================= Flight No: "+str_DepartedFlights+" ======================================</b>");
				softAssertion.assertTrue(false);
				HtmlReportUtil.stepInfo("<b style=\"color:red;\">-->>Belt: "+str_Belt+"</b>");
				
			}else {
				
			}

			if(str_Gate.equals("")) {
				HtmlReportUtil.stepInfo("<b style=\"color:blue;\">======================================= Flight No: "+str_DepartedFlights+" ======================================</b>");
				softAssertion.assertTrue(false);
				HtmlReportUtil.stepInfo("<b style=\"color:red;\">-->>Gate: "+str_Gate+"</b>");
				
			}else {
	
			}
			
	
		}	
		softAssertion.assertAll();
	
}
	
	/**********************************************************************************************************
	 * Method Name: Verify_BeltNumber_BayArea_GateNumber
	 * Description : 
	 * Change History:
	 *             Date                     Created By          Change Description        Code Review and date
	 *             ****                     **********            ****************          *******************
	 *         31/Oct/2019                   Rajesh			                                   AD/-/OCT/2019                                      
	 ***********************************************************************************************************/
	public static void verify_AllActivity() throws InterruptedException, SQLException, IOException {
		List<WebElement> elm_flights;
		String str_DepartedFlights;
		hoverElement(menu);
		hoverElement(flightInfo);
		click(turnaroundList);
		click(departed);
		delay(2000);
		
		clearInput(By.xpath("//input[@name='date']"));
		inputText(By.xpath("//input[@name='date']"), "04/11/2019");
		delay(2000);
		click(By.xpath("//mat-icon[contains(.,'search')]"));
		delay(4000);
		String str_DepatedFlights = getElementText(departed);
		String str_DepatedFlightsCount = str_DepatedFlights.replaceAll("[^0-9]", "");
		System.out.println(str_DepatedFlightsCount);
		int int_DepatedFlightsCount = Integer.parseInt(str_DepatedFlightsCount);
		
		for (int i = 0; i < int_DepatedFlightsCount-5; i++) {
			int scrollCount = i+1;
			WebElement flightinfo = getDriver().findElement(By.xpath("(//div[@class='flightno-col'])["+scrollCount+"]"));
			js.executeScript("arguments[0].scrollIntoView();", flightinfo);
			System.out.println("scroll: " + scrollCount);
			delay(2000);
		}
		
			 
		 elm_flights = getListElements(By.cssSelector(".flightno-col p"));
		System.out.println(elm_flights.size());
		for (int i = 0; i <= elm_flights.size(); i++) {
			delay(500);
		
			
			str_DepartedFlights = elm_flights.get(i).getText().split("-")[0].trim();
			js.executeScript("arguments[0].scrollIntoView();", elm_flights.get(i));
			
			delay(500);
			elm_flights.get(i).click();
			
			boolean chocks_On = getDriver().findElements(By.xpath("//span[contains(.,'Chocks On')]")).size()>0;
			if (chocks_On) {
				HtmlReportUtil.stepInfo("<b style=\"color:blue;\">======================================= Flight No: "+str_DepartedFlights+" ======================================</b>");
				
				HtmlReportUtil.stepInfo("<b style=\"color:green;\">--> Chocks on activity found</b>");
				System.out.println("chocks_On activity found");
				break;
			}else {
				//HtmlReportUtil.stepInfo("<b style=\"color:red;\">--> Chocks on activity not found</b>");
				System.out.println("Chocks on activity not found");
				
				
			}
			
		}
			 elm_flights = getListElements(By.cssSelector(".flightno-col p"));
			System.out.println(elm_flights.size());
			for (int i = 0; i <= elm_flights.size(); i++) {
				delay(500);
			
				
				str_DepartedFlights = elm_flights.get(i).getText().split("-")[0].trim();
				js.executeScript("arguments[0].scrollIntoView();", elm_flights.get(i));
				
				delay(500);
				elm_flights.get(i).click();
				
				boolean ladder_Connected = getDriver().findElements(By.xpath("//span[contains(.,'Ladder Connected')]")).size()>0;
				if (ladder_Connected) {
					HtmlReportUtil.stepInfo("<b style=\"color:blue;\">======================================= Flight No: "+str_DepartedFlights+" ======================================</b>");
					HtmlReportUtil.stepInfo("<b style=\"color:green;\">--> ladder_Connected activity found</b>");
					System.out.println("ladder_Connected activity found");
					break;
				}else {
					//HtmlReportUtil.stepInfo("<b style=\"color:red;\">--> ladder_Connected activity not found</b>");
					System.out.println("ladder_Connected activity not found");
					
					
				}
				
			}
			
				
			elm_flights = getListElements(By.cssSelector(".flightno-col p"));
			System.out.println(elm_flights.size());
			for (int i = 0; i <= elm_flights.size(); i++) {
				delay(500);
			
				
				str_DepartedFlights = elm_flights.get(i).getText().split("-")[0].trim();
				js.executeScript("arguments[0].scrollIntoView();", elm_flights.get(i));
				
				delay(500);
				elm_flights.get(i).click();
				
				boolean ladder_Arrival = getDriver().findElements(By.xpath("//span[contains(.,'Ladder Arrival')]")).size()>0;
				if (ladder_Arrival) {
					HtmlReportUtil.stepInfo("<b style=\"color:blue;\">======================================= Flight No: "+str_DepartedFlights+" ======================================</b>");
					
					HtmlReportUtil.stepInfo("<b style=\"color:green;\">--> ladder_Arrival activity found</b>");
					System.out.println("ladder_Arrival activity found");
					break;
				}else {
					//HtmlReportUtil.stepInfo("<b style=\"color:red;\">--> ladder_Arrival activity not found</b>");
					System.out.println("ladder_Arrival activity not found");
					softAssertion.assertTrue(ladder_Arrival);
				
				}
				
			}
			
			elm_flights = getListElements(By.cssSelector(".flightno-col p"));
			System.out.println(elm_flights.size());
			for (int i = 0; i <= elm_flights.size(); i++) {
				delay(500);
			
				
				str_DepartedFlights = elm_flights.get(i).getText().split("-")[0].trim();
				js.executeScript("arguments[0].scrollIntoView();", elm_flights.get(i));
				
				delay(500);
				elm_flights.get(i).click();
				
				boolean BFL_Arrival = getDriver().findElements(By.xpath("//span[contains(.,'BFL Arrival')]")).size()>0;
				if (BFL_Arrival) {
					HtmlReportUtil.stepInfo("<b style=\"color:blue;\">======================================= Flight No: "+str_DepartedFlights+" ======================================</b>");
					
					HtmlReportUtil.stepInfo("<b style=\"color:green;\">--> BFL_Arrival activity found</b>");
					System.out.println("BFL_Arrival activity found");
					break;
				}else {
					//HtmlReportUtil.stepInfo("<b style=\"color:red;\">--> ladder_Arrival activity not found</b>");
					System.out.println("BFL_Arrival activity not found");
					softAssertion.assertTrue(BFL_Arrival);
					
				}
				
			}
			
			elm_flights = getListElements(By.cssSelector(".flightno-col p"));
			System.out.println(elm_flights.size());
			for (int i = 0; i <= elm_flights.size(); i++) {
				delay(500);
			
				
				str_DepartedFlights = elm_flights.get(i).getText().split("-")[0].trim();
				js.executeScript("arguments[0].scrollIntoView();", elm_flights.get(i));
				
				delay(500);
				elm_flights.get(i).click();
				
				boolean BFL_Connected = getDriver().findElements(By.xpath("//span[contains(.,'BFL Connected')]")).size()>0;
				if (BFL_Connected) {
					HtmlReportUtil.stepInfo("<b style=\"color:blue;\">======================================= Flight No: "+str_DepartedFlights+" ======================================</b>");
					
					HtmlReportUtil.stepInfo("<b style=\"color:green;\">--> BFL_Connected activity found</b>");
					System.out.println("BFL_Connected activity found");
					break;
				}else {
					//HtmlReportUtil.stepInfo("<b style=\"color:red;\">--> ladder_Arrival activity not found</b>");
					System.out.println("BFL_Connected activity not found");
					softAssertion.assertTrue(BFL_Connected);
					
				}
				
				
			}
	
			elm_flights = getListElements(By.cssSelector(".flightno-col p"));
			System.out.println(elm_flights.size());
			for (int i = 0; i <= elm_flights.size(); i++) {
				delay(500);
			
				
				str_DepartedFlights = elm_flights.get(i).getText().split("-")[0].trim();
				js.executeScript("arguments[0].scrollIntoView();", elm_flights.get(i));
				
				delay(500);
				elm_flights.get(i).click();
				
				boolean Hold_Open = getDriver().findElements(By.xpath("//span[contains(.,'Hold Open')]")).size()>0;
				if (Hold_Open) {
					HtmlReportUtil.stepInfo("<b style=\"color:blue;\">======================================= Flight No: "+str_DepartedFlights+" ======================================</b>");
					
					HtmlReportUtil.stepInfo("<b style=\"color:green;\">--> Hold_Open activity found</b>");
					System.out.println("BFL activity found");
					break;
				}else {
					//HtmlReportUtil.stepInfo("<b style=\"color:red;\">--> ladder_Arrival activity not found</b>");
					System.out.println("Hold_Open activity not found");
					softAssertion.assertTrue(Hold_Open);
					
				}
				
			}
	
	
			elm_flights = getListElements(By.cssSelector(".flightno-col p"));
			System.out.println(elm_flights.size());
			for (int i = 0; i <= elm_flights.size(); i++) {
				delay(500);
			
				
				str_DepartedFlights = elm_flights.get(i).getText().split("-")[0].trim();
				js.executeScript("arguments[0].scrollIntoView();", elm_flights.get(i));
				
				delay(500);
				elm_flights.get(i).click();
				
				boolean Fuel_Arrival = getDriver().findElements(By.xpath("//span[contains(.,'Fuel Arrival')]")).size()>0;
				if (Fuel_Arrival) {
					HtmlReportUtil.stepInfo("<b style=\"color:blue;\">======================================= Flight No: "+str_DepartedFlights+" ======================================</b>");
					
					HtmlReportUtil.stepInfo("<b style=\"color:green;\">--> Fuel_Arrival activity found</b>");
					System.out.println("Fuel_Arrival activity found");
					break;
				}else {
					//HtmlReportUtil.stepInfo("<b style=\"color:red;\">--> ladder_Arrival activity not found</b>");
					System.out.println("Hold_Open activity not found");
					softAssertion.assertTrue(Fuel_Arrival);
					
				}
				
			}
			
			elm_flights = getListElements(By.cssSelector(".flightno-col p"));
			System.out.println(elm_flights.size());
			for (int i = 0; i <= elm_flights.size(); i++) {
				delay(500);
			
				
				str_DepartedFlights = elm_flights.get(i).getText().split("-")[0].trim();
				js.executeScript("arguments[0].scrollIntoView();", elm_flights.get(i));
				
				delay(500);
				elm_flights.get(i).click();
				
				boolean Toilet_Cart_Arrival = getDriver().findElements(By.xpath("//span[contains(.,'Toilet Cart Arrival')]")).size()>0;
				if (Toilet_Cart_Arrival) {
					HtmlReportUtil.stepInfo("<b style=\"color:blue;\">======================================= Flight No: "+str_DepartedFlights+" ======================================</b>");
					
					HtmlReportUtil.stepInfo("<b style=\"color:green;\">--> Toilet_Cart_Arrival activity found</b>");
					System.out.println("Toilet_Cart_Arrival activity found");
					break;
				}else {
					//HtmlReportUtil.stepInfo("<b style=\"color:red;\">--> ladder_Arrival activity not found</b>");
					System.out.println("Toilet_Cart_Arrival activity not found");
					softAssertion.assertTrue(Toilet_Cart_Arrival);
					
				}
				
			}
	
			elm_flights = getListElements(By.cssSelector(".flightno-col p"));
			System.out.println(elm_flights.size());
			for (int i = 0; i <= elm_flights.size(); i++) {
				delay(500);
			
				
				str_DepartedFlights = elm_flights.get(i).getText().split("-")[0].trim();
				js.executeScript("arguments[0].scrollIntoView();", elm_flights.get(i));
				
				delay(500);
				elm_flights.get(i).click();
				
				boolean Chocks_Off = getDriver().findElements(By.xpath("//span[contains(.,'Chocks Off')]")).size()>0;
				if (Chocks_Off) {
					HtmlReportUtil.stepInfo("<b style=\"color:blue;\">======================================= Flight No: "+str_DepartedFlights+" ======================================</b>");
					
					HtmlReportUtil.stepInfo("<b style=\"color:green;\">--> Chocks_Off activity found</b>");
					System.out.println("Chocks_Off activity found");
					break;
				}else {
					//HtmlReportUtil.stepInfo("<b style=\"color:red;\">--> ladder_Arrival activity not found</b>");
					System.out.println("Chocks_Off activity not found");
					softAssertion.assertTrue(Chocks_Off);
					
				}
				
			}
	
			elm_flights = getListElements(By.cssSelector(".flightno-col p"));
			System.out.println(elm_flights.size());
			for (int i = 0; i <= elm_flights.size(); i++) {
				delay(500);
			
				
				str_DepartedFlights = elm_flights.get(i).getText().split("-")[0].trim();
				js.executeScript("arguments[0].scrollIntoView();", elm_flights.get(i));
				
				delay(500);
				elm_flights.get(i).click();
				
				boolean Pushback_Arrival = getDriver().findElements(By.xpath("//span[contains(.,'Pushback Arrival')]")).size()>0;
				if (Pushback_Arrival) {
					HtmlReportUtil.stepInfo("<b style=\"color:blue;\">======================================= Flight No: "+str_DepartedFlights+" ======================================</b>");
					
					HtmlReportUtil.stepInfo("<b style=\"color:green;\">--> Pushback_Arrival activity found</b>");
					System.out.println("Pushback_Arrival activity found");
					break;
				}else {
					//HtmlReportUtil.stepInfo("<b style=\"color:red;\">--> ladder_Arrival activity not found</b>");
					System.out.println("Pushback_Arrival activity not found");
					softAssertion.assertTrue(Pushback_Arrival);
					
				}
				
			}
	
			elm_flights = getListElements(By.cssSelector(".flightno-col p"));
			System.out.println(elm_flights.size());
			for (int i = 0; i <= elm_flights.size(); i++) {
				delay(500);
			
				
				str_DepartedFlights = elm_flights.get(i).getText().split("-")[0].trim();
				js.executeScript("arguments[0].scrollIntoView();", elm_flights.get(i));
				
				delay(500);
				elm_flights.get(i).click();
				
				boolean Baggage_Unloading = getDriver().findElements(By.xpath("//span[contains(.,'Baggage Unloading')]")).size()>0;
				if (Baggage_Unloading) {
					HtmlReportUtil.stepInfo("<b style=\"color:blue;\">======================================= Flight No: "+str_DepartedFlights+" ======================================</b>");
					
					HtmlReportUtil.stepInfo("<b style=\"color:green;\">--> Baggage_Unloading activity found</b>");
					System.out.println("Baggage_Unloading activity found");
					break;
				}else {
					//HtmlReportUtil.stepInfo("<b style=\"color:red;\">--> ladder_Arrival activity not found</b>");
					System.out.println("Baggage_Unloading activity not found");
					softAssertion.assertTrue(Baggage_Unloading);
					
				}
				
			}
	
			elm_flights = getListElements(By.cssSelector(".flightno-col p"));
			System.out.println(elm_flights.size());
			for (int i = 0; i <= elm_flights.size(); i++) {
				delay(500);
			
				
				str_DepartedFlights = elm_flights.get(i).getText().split("-")[0].trim();
				js.executeScript("arguments[0].scrollIntoView();", elm_flights.get(i));
				
				delay(500);
				elm_flights.get(i).click();
				
				boolean First_Trolley_BAY_BBA = getDriver().findElements(By.xpath("//span[contains(.,'First Trolley (BAY-BBA)')]")).size()>0;
				if (First_Trolley_BAY_BBA) {
					HtmlReportUtil.stepInfo("<b style=\"color:blue;\">======================================= Flight No: "+str_DepartedFlights+" ======================================</b>");
					
					HtmlReportUtil.stepInfo("<b style=\"color:green;\">--> First_Trolley_BAY_BBA activity found</b>");
					System.out.println("First_Trolley_BAY_BBA activity found");
					break;
				}else {
					//HtmlReportUtil.stepInfo("<b style=\"color:red;\">--> ladder_Arrival activity not found</b>");
					System.out.println("First_Trolley_BAY_BBA activity not found");
					softAssertion.assertTrue(First_Trolley_BAY_BBA);
					
				}
				
			}
			
			elm_flights = getListElements(By.cssSelector(".flightno-col p"));
			System.out.println(elm_flights.size());
			for (int i = 0; i <= elm_flights.size(); i++) {
				delay(500);
			
				
				str_DepartedFlights = elm_flights.get(i).getText().split("-")[0].trim();
				js.executeScript("arguments[0].scrollIntoView();", elm_flights.get(i));
				
				delay(500);
				elm_flights.get(i).click();
				
				boolean Last_Trolley_BAY_BBA = getDriver().findElements(By.xpath("//span[contains(.,'Last Trolley (BAY-BBA)')]")).size()>0;
				if (Last_Trolley_BAY_BBA) {
					HtmlReportUtil.stepInfo("<b style=\"color:blue;\">======================================= Flight No: "+str_DepartedFlights+" ======================================</b>");
					
					HtmlReportUtil.stepInfo("<b style=\"color:green;\">--> Last_Trolley_BAY_BBA activity found</b>");
					System.out.println("Last_Trolley_BAY_BBA activity found");
					break;
				}else {
					//HtmlReportUtil.stepInfo("<b style=\"color:red;\">--> ladder_Arrival activity not found</b>");
					System.out.println("Last_Trolley_BAY_BBA activity not found");
					softAssertion.assertTrue(Last_Trolley_BAY_BBA);
					
				}
				
			}
			
			elm_flights = getListElements(By.cssSelector(".flightno-col p"));
			System.out.println(elm_flights.size());
			for (int i = 0; i <= elm_flights.size(); i++) {
				delay(500);
			
				
				str_DepartedFlights = elm_flights.get(i).getText().split("-")[0].trim();
				js.executeScript("arguments[0].scrollIntoView();", elm_flights.get(i));
				
				delay(500);
				elm_flights.get(i).click();
				
				boolean Baggage_Loading = getDriver().findElements(By.xpath("//span[contains(.,'Baggage Loading')]")).size()>0;
				if (Baggage_Loading) {
					HtmlReportUtil.stepInfo("<b style=\"color:blue;\">======================================= Flight No: "+str_DepartedFlights+" ======================================</b>");
					
					HtmlReportUtil.stepInfo("<b style=\"color:green;\">--> Baggage_Loading activity found</b>");
					System.out.println("Baggage_Loading activity found");
					break;
				}else {
					//HtmlReportUtil.stepInfo("<b style=\"color:red;\">--> ladder_Arrival activity not found</b>");
					System.out.println("Baggage_Loading activity not found");
					softAssertion.assertTrue(Baggage_Loading);
					
				}
				
			}
	
			elm_flights = getListElements(By.cssSelector(".flightno-col p"));
			System.out.println(elm_flights.size());
			for (int i = 0; i <= elm_flights.size(); i++) {
				delay(500);
			
				
				str_DepartedFlights = elm_flights.get(i).getText().split("-")[0].trim();
				js.executeScript("arguments[0].scrollIntoView();", elm_flights.get(i));
				
				delay(500);
				elm_flights.get(i).click();
				
				boolean PCD_Arrival = getDriver().findElements(By.xpath("//span[contains(.,'PCD Arrival')]")).size()>0;
				if (PCD_Arrival) {
					HtmlReportUtil.stepInfo("<b style=\"color:blue;\">======================================= Flight No: "+str_DepartedFlights+" ======================================</b>");
					
					HtmlReportUtil.stepInfo("<b style=\"color:green;\">--> PCD_Arrival activity found</b>");
					System.out.println("PCD_Arrival activity found");
					break;
				}else {
					//HtmlReportUtil.stepInfo("<b style=\"color:red;\">--> ladder_Arrival activity not found</b>");
					System.out.println("PCD_Arrival activity not found");
					softAssertion.assertTrue(PCD_Arrival);
					
				}
				
			}
	
			elm_flights = getListElements(By.cssSelector(".flightno-col p"));
			System.out.println(elm_flights.size());
			for (int i = 0; i <= elm_flights.size(); i++) {
				delay(500);
			
				
				str_DepartedFlights = elm_flights.get(i).getText().split("-")[0].trim();
				js.executeScript("arguments[0].scrollIntoView();", elm_flights.get(i));
				
				delay(500);
				elm_flights.get(i).click();
				
				boolean PC_Deplane = getDriver().findElements(By.xpath("//span[contains(.,'PC Deplane')]")).size()>0;
				if (PC_Deplane) {
					HtmlReportUtil.stepInfo("<b style=\"color:blue;\">======================================= Flight No: "+str_DepartedFlights+" ======================================</b>");
					
					HtmlReportUtil.stepInfo("<b style=\"color:green;\">--> PC_Deplane activity found</b>");
					System.out.println("PC_Deplane activity found");
					break;
				}else {
					//HtmlReportUtil.stepInfo("<b style=\"color:red;\">--> ladder_Arrival activity not found</b>");
					System.out.println("PC_Deplane activity not found");
					softAssertion.assertTrue(PC_Deplane);
					
				}
				
			}
			
			
		
		
		
		
		 
		 
		 
		 
		 
		 
		 
	//===Chocks on
		//span[contains(.,'Chocks On')]
	//===Ladder Connected
		//span[contains(.,'Ladder Connected')]
	//===Ladder Arrival	
		//span[contains(.,'Ladder Arrival')]
	//===BFL Arrival	
		//span[contains(.,'BFL Arrival')]
	//===BFL Connected	
		//span[contains(.,'BFL Connected')]
	//===Hold Open	
		//span[contains(.,'Hold Open')]
	//===Fuel Arrival	
		//span[contains(.,'Fuel Arrival')]
	//===Toilet Cart Arrival
		//span[contains(.,'Toilet Cart Arrival')]
	//===Chocks Off	
		//span[contains(.,'Chocks Off')]
	//===Pushback Arrival	
		//span[contains(.,'Pushback Arrival')]
	//===Baggage Unloading		
		//span[contains(.,'Baggage Unloading')]
	//===First Trolley (BAY-BBA)
		//span[contains(.,'First Trolley (BAY-BBA)')]
	//===Last Trolley (BAY-BBA)
		//span[contains(.,'Last Trolley (BAY-BBA)')]
	//===Baggage Loading
		//span[contains(.,'Baggage Loading')]
	//===PCD Arrival	
		//span[contains(.,'PCD Arrival')]
	//===PC Deplane	
		//span[contains(.,'PC Deplane')]
	
		
		
		
	
}
	
	/**********************************************************************************************************
	 * Method Name: Verify_BeltNumber_BayArea_GateNumber
	 * Description : 
	 * Change History:
	 *             Date                     Created By          Change Description        Code Review and date
	 *             ****                     **********            ****************          *******************
	 *         31/Oct/2019                   Rajesh			                                   AD/-/OCT/2019                                      
	 ***********************************************************************************************************/
	public static void verify_AllActivity1() throws InterruptedException, SQLException, IOException {
		
		List<WebElement> elm_flights;
		String str_DepartedFlights;
		
		
		hoverElement(menu);
		hoverElement(flightInfo);
		click(turnaroundList);
		click(departed);
		delay(2000);
		String str_DepatedFlights = getElementText(departed);
		clearInput(By.xpath("//input[@name='date']"));
		inputText(By.xpath("//input[@name='date']"), "30/10/2019");
		delay(2000);
		click(By.xpath("//mat-icon[contains(.,'search')]"));
		delay(4000);
		String str_DepatedFlightsCount = str_DepatedFlights.replaceAll("[^0-9]", "");
		System.out.println(str_DepatedFlightsCount);
		int int_DepatedFlightsCount = Integer.parseInt(str_DepatedFlightsCount);
		
		for (int i = 0; i < 50; i++) {
			int scrollCount = i+1;
			WebElement flightinfo = getDriver().findElement(By.xpath("(//div[@class='flightno-col'])["+scrollCount+"]"));
			js.executeScript("arguments[0].scrollIntoView();", flightinfo);
			System.out.println("scroll: " + scrollCount);
			delay(500);
		}
		
			 
		 elm_flights = getListElements(By.cssSelector(".flightno-col p"));
		System.out.println(elm_flights.size());
		for (int i = 0; i <= elm_flights.size(); i++) {
			delay(500);
		
			
			str_DepartedFlights = elm_flights.get(i).getText().split("-")[0].trim();
			js.executeScript("arguments[0].scrollIntoView();", elm_flights.get(i));
			
			delay(500);
			elm_flights.get(i).click();
			
			boolean chocks_On = getDriver().findElements(By.xpath("//span[contains(.,'Chocks On')]")).size()>0;
			if (chocks_On) {
				HtmlReportUtil.stepInfo("<b style=\"color:blue;\">======================================= Flight No: "+str_DepartedFlights+" ======================================</b>");
				
				HtmlReportUtil.stepInfo("<b style=\"color:green;\">--> Chocks on activity found</b>");
				System.out.println("chocks_On activity found");
				break;
			}else {
				//HtmlReportUtil.stepInfo("<b style=\"color:red;\">--> Chocks on activity not found</b>");
				System.out.println("Chocks on activity not found");
				
				
			}
			
		}
			 elm_flights = getListElements(By.cssSelector(".flightno-col p"));
			System.out.println(elm_flights.size());
			for (int i = 0; i <= elm_flights.size(); i++) {
				delay(500);
			
				
				str_DepartedFlights = elm_flights.get(i).getText().split("-")[0].trim();
				js.executeScript("arguments[0].scrollIntoView();", elm_flights.get(i));
				
				delay(500);
				elm_flights.get(i).click();
				
				boolean ladder_Connected = getDriver().findElements(By.xpath("//span[contains(.,'Ladder Connected')]")).size()>0;
				if (ladder_Connected) {
					HtmlReportUtil.stepInfo("<b style=\"color:blue;\">======================================= Flight No: "+str_DepartedFlights+" ======================================</b>");
					HtmlReportUtil.stepInfo("<b style=\"color:green;\">--> ladder_Connected activity found</b>");
					System.out.println("ladder_Connected activity found");
					break;
				}else {
					//HtmlReportUtil.stepInfo("<b style=\"color:red;\">--> ladder_Connected activity not found</b>");
					System.out.println("ladder_Connected activity not found");
					
					
				}
				
			}
			
				
	}
	

	
	
	
}