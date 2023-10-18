package pom.test;

import java.util.logging.Logger;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import baseUtils.BrowserManager;
import pom.eer.AddSingleRecord;
import pom.eer.Login_Testcases;
import selva.util.LoggerUtil;
import selva.util.ReadExcelData;
import selva.util.TakeScreenshot;

public class AddSingleRecordManager {

	private static final Logger logger = LoggerUtil.getLogger();
	BrowserManager bm = new BrowserManager();
	TakeScreenshot tss = new TakeScreenshot();

	static {
		String excelFilePath = System.getProperty("user.dir") + "/src/test/resources/Test_data/";
		logger.info("Setting path to access test data form xsls file  --" + excelFilePath);
		ReadExcelData.getInstance().setPath(excelFilePath);

	}

	@BeforeMethod
	public void setupopen() throws Exception {
		try {
			logger.info("Initializing browser...");
			bm = new BrowserManager();
			bm.browserrun();
			logger.info("Browser initialized successfully.");
		} catch (Exception e) {
			LoggerUtil.logException(e, "Exception occurred in setupopen method");
		}
	}

	@DataProvider(name = "AddRecord")
	public Object[][] creator_tcdata() throws Exception {
		try {
			logger.info("Fetching test data...");
			Object[][] testData = ReadExcelData.getExcelDataIn2DArray("data.xlsx", "data");
			logger.info("Test data fetched successfully.");
			return testData;
		} catch (Exception e) {
			LoggerUtil.logException(e, "Exception occurred in creator_tcdata method");
			return new Object[0][0];
		}
	}

	@Test(dataProvider = "AddRecord")
	public void AddRecord(String name, String contact, String email, String Validupto) {
		try {
			logger.info("Starting test execution for  method...");
			Login_Testcases lt = PageFactory.initElements(bm.driver, Login_Testcases.class);

			AddSingleRecord wcc = PageFactory.initElements(bm.driver, AddSingleRecord.class);

			lt.commonlogin();
			logger.info("Logged in successfully.");

			wcc.viewAdd();
			logger.info("Clicked on viewAdd button.");

			wcc.AddButton();
			logger.info("Clicked on Add button");

			wcc.AddSingleRecord();
			logger.info("Clicked on Add Single Record button");

			wcc.AddDetails(name, contact, email, Validupto);
			logger.info("Filled the record details.");

			wcc.save();
			logger.info("Record Saved Successfully");

			/*
			 * wcc.Logout(); logger.info("Logout Successfully");
			 */

			logger.info("Test execution completed for addingSingleRecord method.");

		} catch (Exception e) {
			LoggerUtil.logException(e, "Exception occurred in AddSingleRecord method");
		}
	}
}
