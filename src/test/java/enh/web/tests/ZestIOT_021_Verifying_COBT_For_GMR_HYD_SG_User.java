package enh.web.tests;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import enh.db.cases.COBT_For_DIALCelebi_User;
import enh.db.cases.COBT_For_GMR_HYD_AISATS_User;
import enh.db.cases.COBT_For_GMR_HYD_SG_User;
import enh.web.pages.BflActivities;
import enh.web.pages.DepartedFlights;
import enh.web.pages.HomePage;
import listeners.CustomListeners;
import listeners.ExecutionStartEndListner;
import utilities.ConfigReader;
import utilities.GlobalUtil;
import utilities.HtmlReportUtil;
import utilities.KeywordUtil;

@Listeners({ CustomListeners.class, ExecutionStartEndListner.class })
public class ZestIOT_021_Verifying_COBT_For_GMR_HYD_SG_User extends KeywordUtil {
	String stepInfo = "";
	int retryCount = getIntValue("retryCount");
	static int retryingNumber = 1;


	@Test(testName = "ZestIOT_021_Verifying_COBT_For_GMR_HYD_SG_User", groups = {
			"Regression" }, description = "Verifying cobt  Activity")
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
			COBT_For_GMR_HYD_SG_User.cOBT_For_GMR_HYD_SG_User();
			/*COBT_For_GMR_HYD_SG_User.totalFlights();
			COBT_For_GMR_HYD_SG_User.cobtIsNotNull();
			COBT_For_GMR_HYD_SG_User.ofBlockTimeIsNotNull();
			COBT_For_GMR_HYD_SG_User.cobtGreaterThan5Min();
*/			// .........Script Start...........................
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
	

	@AfterTest
	public void afterTest() {
		
	}
	
}
