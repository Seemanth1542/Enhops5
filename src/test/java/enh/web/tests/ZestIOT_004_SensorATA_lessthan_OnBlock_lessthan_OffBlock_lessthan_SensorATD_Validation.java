package enh.web.tests;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import enh.db.cases.DBTesting;
import listeners.CustomListeners;
import listeners.ExecutionStartEndListner;
import utilities.GlobalUtil;
import utilities.HtmlReportUtil;
import utilities.KeywordUtil;

@Listeners({ CustomListeners.class, ExecutionStartEndListner.class })
public class ZestIOT_004_SensorATA_lessthan_OnBlock_lessthan_OffBlock_lessthan_SensorATD_Validation extends KeywordUtil {
	String stepInfo = "";
	int retryCount = getIntValue("retryCount");
	static int retryingNumber = 1;

	

	@Test(testName = "ZstIOT_001_DepatedFlightValidationWithDB", groups = {
			"Regression" }, description = "To create 1 room & 1 night  reservation")
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
			DBTesting.validateSensorDataForHyderabad("prod");
			DBTesting.validateOnBlockTimeAndOffBlockTimeForHyderabad("prod");
			DBTesting.Sensor_ATA_LessThan_On_Block_LessThan_Off_Block_LessThan_Sensor_ATD("prod");

			
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
