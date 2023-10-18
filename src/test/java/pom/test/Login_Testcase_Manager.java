package pom.test;

import java.lang.reflect.Method;
import java.util.logging.Logger;

import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
//import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import baseUtils.BrowserManager;
import pom.eer.Login_Testcases;
import selva.util.LoggerUtil;
import selva.util.ReadExcelData;
import selva.util.Reporter;
import selva.util.TakeScreenshot;

public class Login_Testcase_Manager {

	private static final Logger logger = LoggerUtil.getLogger();
	BrowserManager bm = new BrowserManager();
	TakeScreenshot tss = new TakeScreenshot();

	static {
		String excelFilePath = System.getProperty("user.dir") + "/src/test/resources/Test_data/";
		logger.info("Setting path to access test data form xsls file  --" + excelFilePath);
		ReadExcelData.getInstance().setPath(excelFilePath);

	}

	@BeforeClass
	public void generatereport() {
		logger.info("Generating test report");
		Reporter.setupReport("loginPage");
	}

	@BeforeMethod
	public void setupopen() throws Exception {
		logger.info("Setting up the browser...");
		bm.browserrun();
	}

	@SuppressWarnings("static-access")
	@AfterMethod
	public void setupclose(ITestResult result) throws Exception {
		if (result.FAILURE == result.getStatus()) {
			logger.warning("Test case failed. Capturing screenshot...");
			tss.screenshot(bm.driver, "login test case");
		}

		Reporter.reportonTestResult(result, bm.driver);

		Reporter.flushReport();
		bm.driver.close();
	}

	@DataProvider(name = "creator")
	public Object[][] creator_tcdata() throws Exception {
		logger.info("Fetching data from the data provider 'creator'");
		Object[][] testData = ReadExcelData.getExcelDataIn2DArray("logintestdata.xlsx", "loginSheet");
		return testData;
	}
//		@DataProvider(name = "reviewer")
//		public Object[][] reviewer_tcdata() throws Exception {
//
//			String[][] testData = ReadExcelData.getExcelDataIn2DArray(System.getProperty("user.dir")+"//sunbirdsaas_resources//Test_data//createcoursetestdata.xlsx", "createcourseform");
//			return testData;
//		}

	@Test(priority = 0, enabled = true)
	public void btnTc(Method testmethodname) {
		LoggerUtil.startTimeMeasurement();
		logger.info("Executing btnTc method");
		Reporter.createTest(testmethodname.getName());
		Login_Testcases slt = PageFactory.initElements(bm.driver, Login_Testcases.class);
		Reporter.logStep(Status.INFO, "Steps performed according to test case");
		slt.ex_apex_btn();
		LoggerUtil.stopTimeMeasurement("Test case exicution completed");
	}
//		@BeforeClass
//		public void loadprop() throws Exception
//		{
//		
//		String proppaath=System.getProperty("user.dir")+"/resources/locators/login.properties";
//		loginproperties=new Propertiesreader(proppaath);
//		}

	@Test(dataProvider = "creator", priority = 1, enabled = true)
	public void username_tc_01(String userId, String pwd, Method testmethodname) {
		LoggerUtil.startTimeMeasurement();
		logger.info("Executing username_tc_01 method");
		Reporter.createTest(testmethodname.getName());
		Login_Testcases slt = PageFactory.initElements(bm.driver, Login_Testcases.class);
		Reporter.logStep(Status.INFO, "Steps performed according to test case");
		slt.testcases_login(userId, pwd);
		LoggerUtil.stopTimeMeasurement("Test case exicution completed");
	}

//		@Test(dataProvider = "reviewer",priority = 2)
//		public void username_tc_02(String userId, String pwd) {
//			sunbirdsaas_login_tc slt=PageFactory.initElements(bm.driver,sunbirdsaas_login_tc.class);
//			slt.username_tc_001(userId,pwd);
//
//		}
//	@AfterClass
//	public void deleteOldReportfile() {
//		Reporter.getInstance().deleteOldReportFiles();
//		LoggerUtil.getInstance().deleteOldLogFiles();
//	}
}
