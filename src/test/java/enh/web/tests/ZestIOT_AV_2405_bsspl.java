package enh.web.tests;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import enh.db.pages.AV_2405_Read_scheduledFlights_andcheck_thecoverage_ScheduledFlights_Vs_FlightsDetected_ScheduledFlights_Vs_FuelActivityFormed_BSSPL;
import enh.db.pages.SQL_Queries;
import listeners.CustomListeners;
import listeners.ExecutionStartEndListner;
import utilities.ConfigReader;
import utilities.GlobalUtil;
import utilities.HtmlReportUtil;
import utilities.KeywordUtil;

@Listeners({ CustomListeners.class, ExecutionStartEndListner.class })
public class ZestIOT_AV_2405_bsspl extends KeywordUtil {
	String stepInfo = "";
	int retryCount = getIntValue("retryCount");
	static int retryingNumber = 1;

	/*@DataProvider
	public Object[][] testData() throws Exception {
		Object data[][] = TestDataProvider.excelReader(ConfigReader.getValue("ExcelPath"), "9Dec-bayNo");
		return data;
	}*/

	@Test(testName = "ZestIOT_000_bsspl", groups = {
			"Regression" }, description = "ZestIOT_000_bsspl")
	public void test() throws Throwable {
		try {
			setTestCaseID(getClass().getSimpleName());
			// ======================BASIC SETTING FOR
			// TEST=====================================================
			if (retryingNumber == 1)
				initTest();
			// ================== END BASIC SETTING
			// ============================================================
			// .........Script Start...........................
				
			if(SQL_Queries.todayDayDateTime().contains("Mon")) {
				AV_2405_Read_scheduledFlights_andcheck_thecoverage_ScheduledFlights_Vs_FlightsDetected_ScheduledFlights_Vs_FuelActivityFormed_BSSPL.ScheduledVsFlightDetectedForBSSPL_Report(ConfigReader.getValue("ExcelPath"),"Sun_BSSPL","prod");	
			}
			else if(SQL_Queries.todayDayDateTime().contains("Tue")) {
				AV_2405_Read_scheduledFlights_andcheck_thecoverage_ScheduledFlights_Vs_FlightsDetected_ScheduledFlights_Vs_FuelActivityFormed_BSSPL.ScheduledVsFlightDetectedForBSSPL_Report(ConfigReader.getValue("ExcelPath"),"Mon_BSSPL","prod");
			}
			else if(SQL_Queries.todayDayDateTime().contains("Wed")) {
				AV_2405_Read_scheduledFlights_andcheck_thecoverage_ScheduledFlights_Vs_FlightsDetected_ScheduledFlights_Vs_FuelActivityFormed_BSSPL.ScheduledVsFlightDetectedForBSSPL_Report(ConfigReader.getValue("ExcelPath"),"Tue_BSSPL","prod");
			}
			else if(SQL_Queries.todayDayDateTime().contains("Thu")) {
				AV_2405_Read_scheduledFlights_andcheck_thecoverage_ScheduledFlights_Vs_FlightsDetected_ScheduledFlights_Vs_FuelActivityFormed_BSSPL.ScheduledVsFlightDetectedForBSSPL_Report(ConfigReader.getValue("ExcelPath"),"Wed_BSSPL","prod");
			}
			else if(SQL_Queries.todayDayDateTime().contains("Fri")) {
				AV_2405_Read_scheduledFlights_andcheck_thecoverage_ScheduledFlights_Vs_FlightsDetected_ScheduledFlights_Vs_FuelActivityFormed_BSSPL.ScheduledVsFlightDetectedForBSSPL_Report(ConfigReader.getValue("ExcelPath"),"Thurs_BSSPL","prod");
			}
			else if(SQL_Queries.todayDayDateTime().contains("Sat")) {
				AV_2405_Read_scheduledFlights_andcheck_thecoverage_ScheduledFlights_Vs_FlightsDetected_ScheduledFlights_Vs_FuelActivityFormed_BSSPL.ScheduledVsFlightDetectedForBSSPL_Report(ConfigReader.getValue("ExcelPath"),"Fri_BSSPL","prod");
			}
			else if(SQL_Queries.todayDayDateTime().contains("Sun")) {
				AV_2405_Read_scheduledFlights_andcheck_thecoverage_ScheduledFlights_Vs_FlightsDetected_ScheduledFlights_Vs_FuelActivityFormed_BSSPL.ScheduledVsFlightDetectedForBSSPL_Report(ConfigReader.getValue("ExcelPath"),"Sat_BSSPL","prod");
			}
			
			
			// .........Script Start...........................
		} catch (Exception e) {
			if (retryCount > 0) {
				String imagePath = takeScreenshot(getDriver(), getTestCaseID() + "_" + retryingNumber);

				logStepFail(stepInfo + " - " + KeywordUtil.lastAction);
				logStepError(e.getMessage());
				HtmlReportUtil.attachScreenshot(imagePath, false);

				GlobalUtil.getTestResult().setScreenshotref(imagePath);

				HtmlReportUtil.stepInfo("Trying to Rerun" + " " + getTestCaseID() + " for " + retryingNumber + " time");
				retryCount--;
				retryingNumber++;
				utilities.LogUtil.infoLog(getClass(), "****************Waiting for " + getIntValue("retryDelayTime")
						+ " Secs before retrying.***********");
				delay(getIntValue("retryDelayTime"));
				// Rerun same test
				test();
			} else {
				String imagePath = takeScreenshot(getDriver(), getTestCaseID());
				logStepFail(stepInfo + " - " + KeywordUtil.lastAction);
				logStepError(e.getMessage());
				HtmlReportUtil.attachScreenshot(imagePath, false);
				GlobalUtil.getTestResult().setScreenshotref(imagePath);
				GlobalUtil.setTestException(e);
				throw e;
			}
		}
	}// End Test

}
