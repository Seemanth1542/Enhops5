package enh.web.tests;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import enh.db.pages.Bsspl;
import listeners.CustomListeners;
import listeners.ExecutionStartEndListner;
import utilities.ConfigReader;
import utilities.GlobalUtil;
import utilities.HtmlReportUtil;
import utilities.KeywordUtil;

@Listeners({ CustomListeners.class, ExecutionStartEndListner.class })
public class ZestIOT_000_bsspl extends KeywordUtil {
	String stepInfo = "";
	int retryCount = getIntValue("retryCount");
	static int retryingNumber = 1;


	/*@DataProvider
	public Object[][] testData() throws Exception {
		Object data[][] = TestDataProvider.excelReader(ConfigReader.getValue("ExcelPath"), "9Dec-bayNo");
		return data;
	}*/

	@Test(testName = "ZestIOT_000_bsspl", groups = {
			"Regression" }, description = "Verify Bay number obtained from BSSPL with Avileap DB")
	public void test()
			throws Throwable {
		try {
			setTestCaseID(getClass().getSimpleName());
			// ======================BASIC SETTING FOR
			// TEST=====================================================
			if (retryingNumber == 1)
				initTest();
			// ================== END BASIC SETTING
			// ============================================================
			// .........Script Start...........................
			

			Bsspl.bsspl(ConfigReader.getValue("ExcelPath"),"Wed_BSSPL","prod");

			
			/*if(SQL_Queries.todayDayDateTime().contains("Tue")) {
				Bsspl.bsspl(Mon_Flight_Num);
			}else if(SQL_Queries.todayDayDateTime().contains("Wed")) {
				Bsspl.bsspl(Tue_Flight_Num);
			}else if(SQL_Queries.todayDayDateTime().contains("Thu")) {
				Bsspl.bsspl(Wed_Flight_Num);
			}else if(SQL_Queries.todayDayDateTime().contains("Fri")) {
				Bsspl.bsspl(Thu_Flight_Num);
			}else if(SQL_Queries.todayDayDateTime().contains("Sat")) {
				Bsspl.bsspl(Fri_Flight_Num);
			}else if(SQL_Queries.todayDayDateTime().contains("Sun")) {
				Bsspl.bsspl(Sat_Flight_Num);
			}else if(SQL_Queries.todayDayDateTime().contains("Mon")) {
				Bsspl.bsspl(Sun_Flight_Num);
			}*/

			
			
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
				

				test( );
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
