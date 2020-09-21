package enh.web.tests;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import enh.db.pages.AV_2307_SensorATA_OnBlock_OffBlock_SensorATD_DIAL_Delhi_Validation;
import listeners.CustomListeners;
import listeners.ExecutionStartEndListner;
import utilities.GlobalUtil;
import utilities.HtmlReportUtil;
import utilities.KeywordUtil;

@Listeners({ CustomListeners.class, ExecutionStartEndListner.class })
public class ZestIOT_AV_2307_DIAL_Delhi_Validate_LANDING_ONBLOCK_OFFBLOCK_AIRBORNE_timestamps_of_Arrival_and_Departure_aircrafts_Any_Data_source extends KeywordUtil {
	String stepInfo = "";
	int retryCount = getIntValue("retryCount");
	static int retryingNumber = 1;

	@Test(testName = "AV_2307_Validate_LANDING_ONBLOCK_OFFBLOCK_AIRBORNE_timestamps_of_Arrival_and_Departure_aircrafts_Any_Data_source", groups = {
			"Regression" }, description = "Validate LANDING, ONBLOCK, OFFBLOCK and AIRBORNE timestamps of Arrival and Departure aircrafts from Any Data source for DIAL-Delhi")
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

			AV_2307_SensorATA_OnBlock_OffBlock_SensorATD_DIAL_Delhi_Validation.SensorATA_LessThanOnBlock_LessThanOffBlock_LessThanSensorATD_Delhi_Report(22,"prod");
			

			
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
