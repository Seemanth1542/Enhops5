package enh.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;

import enh.db.pages.SQL_Queries;
import utilities.HtmlReportUtil;
import utilities.KeywordUtil;

public class BflActivities extends KeywordUtil {
	private static By menu = By.xpath("//mat-icon[contains(.,'menu')] ");
	private static By flightInfo = By.xpath("//a[contains(.,'Flight Info')]");
	private static By turnaroundList = By.xpath("//a[contains(.,'Turnaround List')]");
	private static By departed = By.xpath("//*[@class='mat-tab-label-content'][contains(.,'Departed')]");

	public static String bflActivity_report;

	static JavascriptExecutor js = (JavascriptExecutor) getDriver();

	/**********************************************************************************************************
	 * Method Name: Verify BFL activities Description : To verify that BFL
	 * activities (BFA, BFP, Hold Open, BFU, BFL) are displayed on UI Change
	 * History: Date Created By Change Description Code Review and date ****
	 * ********** **************** ******************* 16/Nov/2019 Padmaja
	 * AD/-/Nov/2019
	 ***********************************************************************************************************/
	public static void verifyBFLActivityInTurnaroundList() throws Exception {
		int allActivitiesCount = 0;
		int BFLconnected_loading_unloading = 0;
		int BFLarrivalConnected = 0;
		int loading_unloading_withoutBFLarrival = 0;
		int loading_unloading_withoutBFLconnected = 0;
		int noallBFLActivitiesCount=0;
		int fail = 0;
		int bflNotFound = 0;
		int missingFlight = 0;
		int j = 1;
		delay(10000);
		String userName = getElementText(By.xpath("html/body/app-root/div/app-topnav/mat-toolbar/div[4]/div[2]"));
		System.out.println(userName);
		HtmlReportUtil.stepInfo("<b style=\"color:brown;\"> Reports for user: " + userName + " </b>");
		click(menu);
		delay(2000);
		click(flightInfo);
		delay(2000);
		click(turnaroundList);
		delay(2000);
		click(departed);
		delay(5000);
		// calenderInput("13/NOV/2019");
		getDriver().findElement(By.xpath("//input[@name='date']")).clear();
		delay(2000);
		// inputText(By.xpath("//input[@name='date']"), "20/11/2019");
		 inputText(By.xpath("//input[@name='date']"), SQL_Queries.yesterDate());
		delay(2000);
		System.out.println("Final");
		// departed =
		// By.xpath("//*[@class='mat-tab-label-content'][contains(.,'Departed')]");
		click(By.xpath("//mat-icon[contains(.,'search')]"));
		delay(10000);
		System.out.println("done");
		String str_DepatedFlights = getElementText(departed);
		String str_DepatedFlightsCount = str_DepatedFlights.replaceAll("[^0-9]", "");
		int DepatedFlightsCount = Integer.parseInt(str_DepatedFlightsCount);

		System.out.println(str_DepatedFlightsCount);
		WebDriverWait w = new WebDriverWait(getDriver(), 20);
		for (int i = 1; i <= DepatedFlightsCount; i++) {
			try {
				delay(3000);
				js.executeScript("arguments[0].scrollIntoView();",
						getWebElement(By.xpath("//div[@id='selectedCard'][" + i + "]")));
				delay(3000);
				click(By.xpath("//div[@id='selectedCard'][" + i + "]"));
				delay(3000);
				String activityList = getElementText(By.xpath("//div[@id='selectedCard'][" + i + "]"));
                System.out.println(activityList);
				if (activityList.contains("BFL Connected") && activityList.contains("BFL Arrival")
						&& activityList.contains("Baggage Unloading") && activityList.contains("Baggage Loading")) {
					allActivitiesCount = allActivitiesCount + 1;
					String flightNo = getElementText(By.xpath("//div[@id='selectedCard'][" + i + "]/descendant::p[1]"));
					HtmlReportUtil.stepInfo("<b style=\"color:blue;\">" + i + " Flight: " + flightNo
							+ " - with all BFL activities (BFL arrival, BFL connected, loading and unloading) are formed </b>");
					System.out.println(i + " Flight: " + flightNo
							+ " with all BFL activities (BFL arrival, BFL connected, loading and unloading) activities formed");

				}if (!activityList.contains("BFL Connected") && !activityList.contains("BFL Arrival")
						&& !activityList.contains("Baggage Unloading") && !activityList.contains("Baggage Loading")) {
					noallBFLActivitiesCount = noallBFLActivitiesCount + 1;
					String flightNo = getElementText(By.xpath("//div[@id='selectedCard'][" + i + "]/descendant::p[1]"));
					HtmlReportUtil.stepInfo("<b style=\"color:blue;\">" + i + " Flight: " + flightNo
							+ " - without all BFL activities (BFL arrival, BFL connected, loading and unloading) are formed </b>");
					System.out.println(i + " Flight: " + flightNo
							+ " without all BFL activities (BFL arrival, BFL connected, loading and unloading) activities formed");

				}

				if (activityList.contains("BFL Connected") && activityList.contains("Baggage Unloading")
						&& activityList.contains("Baggage Loading")) {
					BFLconnected_loading_unloading = BFLconnected_loading_unloading + 1;
					String flightNo = getElementText(By.xpath("//div[@id='selectedCard'][" + i + "]/descendant::p[1]"));
					HtmlReportUtil.stepInfo("<b style=\"color:blue;\">" + i + " Flight: " + flightNo
							+ " - with BFL connected, loading and unloading are formed </b>");
					System.out.println(i + " Flight: " + flightNo
							+ " with BFL connected, loading and unloading, activities formed");

				}
				if (activityList.contains("BFL Connected") && activityList.contains("BFL Arrival")) {
					BFLarrivalConnected = BFLarrivalConnected + 1;
					String flightNo = getElementText(By.xpath("//div[@id='selectedCard'][" + i + "]/descendant::p[1]"));
					HtmlReportUtil.stepInfo("<b style=\"color:blue;\">" + i + " Flight: " + flightNo
							+ " - with  BFL arrival and connected activities are formed </b>");
					System.out
							.println(i + " Flight: " + flightNo + " with  BFL arrival and connected activities formed");

				}
				if (!activityList.contains("BFL Arrival") && activityList.contains("Baggage Unloading")
						&& activityList.contains("Baggage Loading")) {
					loading_unloading_withoutBFLarrival = loading_unloading_withoutBFLarrival + 1;
					String flightNo = getElementText(By.xpath("//div[@id='selectedCard'][" + i + "]/descendant::p[1]"));
					HtmlReportUtil.stepInfo("<b style=\"color:red;\">" + i + " Flight: " + flightNo
							+ " - with loading and unloading without bfl arrival activities are formed </b>");
					System.out.println(i + " Flight: " + flightNo
							+ " with loading and unloading without bfl arrival activities formed");

				}

				if (!activityList.contains("BFL Connected") && activityList.contains("Baggage Unloading")
						&& activityList.contains("Baggage Loading")) {
					loading_unloading_withoutBFLconnected = loading_unloading_withoutBFLconnected + 1;
					String flightNo = getElementText(By.xpath("//div[@id='selectedCard'][" + i + "]/descendant::p[1]"));
					HtmlReportUtil.stepInfo("<b style=\"color:red;\">" + i + " Flight: " + flightNo
							+ " - with loading and unloading without bfl connected activities are formed </b>");
					System.out.println(i + " Flight: " + flightNo
							+ " with loading and unloading without bfl connected activities formed");

				}

				click(By.xpath("//div[@id='selectedCard'][" + i + "]"));
				delay(2000);
				try {
					j = j + 1;
					js.executeScript("arguments[0].scrollIntoView();",
							getWebElement(By.xpath("//div[@id='selectedCard'][" + (j) + "]")));
					delay(5000);
				} catch (Exception e) {
					// e.printStackTrace();
				}

			} catch (Exception e) {
				// e.printStackTrace();
				missingFlight = missingFlight + 1;
				HtmlReportUtil.stepInfo("<b style=\"color:brown;\"> " + i + " "
						+ getElementText(By.xpath("//div[@id='selectedCard'][" + i + "]")) + " </b>");
			}
		}

		HtmlReportUtil.stepInfo("<b style=\"color:green;\">Total number of Flights: " + DepatedFlightsCount + "</b>");
		HtmlReportUtil.stepInfo(
				"<b style=\"color:green;\">Total flights with all BFL activities (BFL arrival, BFL connected, loading and unloading) : "
						+ allActivitiesCount + "</b>");
						HtmlReportUtil.stepInfo(
								"<b style=\"color:green;\">Total flights without all BFL activities (BFL arrival, BFL connected, loading and unloading) : "
										+ noallBFLActivitiesCount + "</b>");
		HtmlReportUtil.stepInfo(
				"<b style=\"color:green;\">Total flights with BFL connected, loading and unloading : "
						+ BFLconnected_loading_unloading + "</b>");
		HtmlReportUtil
				.stepInfo("<b style=\"color:green;\">Total flights with BFL arrival and connected : "
						+ BFLarrivalConnected + "</b>");
		HtmlReportUtil.stepInfo(
				"<b style=\"color:red;\">Total flights with loading and unloading without bfl arrival : "
						+ loading_unloading_withoutBFLarrival + "</b>");
		HtmlReportUtil
				.stepInfo("<b style=\"color:red;\">Total flight with loading and unloading without bfl connected : "
						+ loading_unloading_withoutBFLconnected + "</b>");
		HtmlReportUtil.stepInfo(
				"<b style=\"color:brown;\">No. of missing flights in automation execution : " + missingFlight + "</b>");

	}

}
