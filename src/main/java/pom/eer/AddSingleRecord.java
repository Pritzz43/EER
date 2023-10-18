package pom.eer;

import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import baseUtils.BrowserManager;
import selva.util.PropertiesFileManager;

public class AddSingleRecord {

	private static final String WebElement = null;

	static String locatorsPath = System.getProperty("user.dir") + "/src/test/resources/locators/";

	BrowserManager bm = new BrowserManager();

	public AddSingleRecord(WebDriver driver) {

		bm.driver = driver;

	}

	// ..............load properties file

	PropertiesFileManager loader = PropertiesFileManager.getInstance();
	Properties viewAdd = loader.getProperties("viewAdd.properties");

	// ............Pom elements fetched from properties file

	By dashboard = By.linkText(viewAdd.getProperty("viewAdd_button"));
	By Add = By.id(viewAdd.getProperty("AddButton"));
	By AddSingleRecord = By.id(viewAdd.getProperty("AddSingleRecord"));

	By name = By.id(viewAdd.getProperty("name"));
	By contact = By.id(viewAdd.getProperty("contact"));
	By email = By.id(viewAdd.getProperty("email"));
	By validupto = By.id(viewAdd.getProperty("validUpto"));
	By save = By.cssSelector(viewAdd.getProperty("saveButton"));
	By Logout = By.xpath(viewAdd.getProperty("Logout"));

	public void viewAdd() {
		bm.driver.findElement(dashboard).click();
	}

	public void AddButton() throws InterruptedException {

		boolean onclick = bm.driver.findElement(Add).isEnabled();

		if (onclick) {
			System.out.println("Add button is enabled " + onclick);
		} else {
			System.out.println("Add button is disabled " + onclick);
		}

		WebDriverWait wait = new WebDriverWait(bm.driver, Duration.ofSeconds(10));
		WebElement add = wait.until(ExpectedConditions.elementToBeClickable(Add));

		Actions acti = new Actions(bm.driver);
		acti.moveToElement(add).perform();
		acti.click(add).perform();

		Thread.sleep(5000);
		acti.doubleClick(add);
		// acti.contextClick(add);

		// add.click();

		// bm.driver.findElement(Add).click();
	}

	public void AddSingleRecord() {

		WebDriverWait wait = new WebDriverWait(bm.driver, Duration.ofSeconds(10));
		WebElement addSingleRecord = wait.until(ExpectedConditions.elementToBeClickable(AddSingleRecord));
		addSingleRecord.click();
	}
	// bm.driver.findElement(AddSingleRecord).click();

	// create form content

	public void name(String Name) {
		bm.driver.findElement(name).clear();

		bm.driver.findElement(name).sendKeys(Name);
	}

	public void contact(String Contact) {
		bm.driver.findElement(contact).sendKeys(Contact);
	}

	public void email(String EmailID) {

		bm.driver.findElement(email).sendKeys(EmailID);
	}

	public void validupto(String Validupto) {

		bm.driver.findElement(validupto).sendKeys(Validupto);
	}

	public void save() {

		bm.driver.findElement(save).click();
	}

	/*
	 * public void Logout() { bm.driver.findElement(Logout).click(); }
	 */
	// tests

	public void view_Add() {
		try {
			bm.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			viewAdd();

			Thread.sleep(4000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void AddDetails(String name, String contact, String email, String Validupto) {

		try {
			bm.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			AddButton();
			AddSingleRecord();
			name(name);
			contact(contact);
			email(email);
			validupto(Validupto);
			save();

		} catch (Exception e) {
			e.printStackTrace();

		}

	}
}
