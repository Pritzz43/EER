package pom.eer;

import java.time.Duration;
import java.util.Properties;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import baseUtils.BrowserManager;
import selva.util.LoggerUtil;
import selva.util.PropertiesFileManager;

public class Login_Testcases {

	static String locatorsPath = System.getProperty("user.dir") + "/src/test/resources/locators/";
	BrowserManager bm = new BrowserManager();

	public Login_Testcases(WebDriver driver) {
		bm.driver = driver;
	}
	// ..............load properties file

	private static final Logger LOGGER = LoggerUtil.getLogger();

	static {

		LOGGER.info("Setting path to access respective locators form properties file  --" + locatorsPath);
		PropertiesFileManager.getInstance().setPath(locatorsPath);
	}

	PropertiesFileManager loader = PropertiesFileManager.getInstance();
	Properties loginloctors = loader.getProperties("login.properties");

	// ............Pom elements fetched from properties file

	By issuerloginbutton = By.xpath(loginloctors.getProperty("IssuerLogin_button"));
	By username = By.id(loginloctors.getProperty("userName_field"));
	By password = By.id(loginloctors.getProperty("Pwd_field"));
	By signInbutton = By.id(loginloctors.getProperty("signIn_button"));

	// .........actions on webelements
	public void issuerButton() {
		bm.driver.findElement(issuerloginbutton).click();
		boolean onclick = bm.driver.findElement(issuerloginbutton).isEnabled();

		if (onclick) {
			System.out.println("Issuer Login button is enabled " + onclick);
		} else {
			System.out.println("Issuer Login button is disabled " + onclick);
		}
	}

	public void username(String userid) {
		bm.driver.findElement(username).sendKeys(userid);
		String Username = bm.driver.findElement(username).getText();
		
		System.out.println(Username);
	}

	public void password(String pass) {
		bm.driver.findElement(password).sendKeys(pass);
	}

	public void loginbuttonclick() {
		bm.driver.findElement(signInbutton).click();

	}

	/*
	 * 
	 * Test caseses on login page include button and login funtionality
	 * 
	 * 
	 */

	public void ex_apex_btn() {
		try {

			issuerButton();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

//.......login test cases
	public void testcases_login(String uid, String pass) {
		try {

			bm.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			issuerButton();
			username(uid);
			password(pass);
			loginbuttonclick();

			Thread.sleep(3000);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// .......Common login method for all usage
	public void commonlogin() {
		try {
			bm.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			issuerButton();
			username(loginloctors.getProperty("Issuerid"));
			password(loginloctors.getProperty("Issuerpass"));
			loginbuttonclick();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

//@FindBy(how=How.TAG_NAME, using="button")
//WebElement ex_apex_btn;
//@FindBy(how=How.ID,using="login")
//WebElement loginbtn;

//@FindBy(how=How.ID,using="password")
//WebElement password;

//@FindBy(how=How.ID,using="username")
//WebElement username;

//@FindBy(how=How.XPATH,using="//p[text()=\"Hi Content Creator Nulp Saas\"]")
//WebElement wlcmmsg;
//@FindBy(how=How.ID,using="error-summary")
//WebElement errorsummary;
