package narvar.performActions.tracking;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import jxl.Sheet;
import jxl.read.biff.BiffException;
import narvar.driverInit.tracking.DriverApp;
import narvar.readExcelFiles.tracking.ReadDatabase;
import narvar.readExcelFiles.tracking.ReadExl;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.google.common.base.Function;

public class PerformActions {
	GetObjectDetails ob = new GetObjectDetails();

	public void verifyTextPresent(String objectName, String assertionValue)
			throws BiffException, IOException {
		String pageObject[] = ob.getObjectDetails(objectName);

		try {

			if (pageObject[0].equals("xpath")) {

				String textDisplayed = DriverApp.driver.findElement(
						By.xpath(pageObject[1])).getText();
				Assert.assertEquals(textDisplayed, assertionValue);
				System.out.println(textDisplayed);

			} else if (pageObject[0].isEmpty()) {

				System.out.println("Object Not Found:- XPATH or ID");

			}
		} catch (ElementNotFoundException e) {
			System.out.println("No such element found");

		}

	}

	public void verifyElementDisplay(String objectName) throws BiffException,
			IOException {
		String pageObject[] = ob.getObjectDetails(objectName);

		try {

			if (pageObject[0].equals("xpath")) {
				Assert.assertEquals(true,
						DriverApp.driver.findElement(By.xpath(pageObject[1]))
								.isDisplayed());
			}
		} catch (ElementNotFoundException e) {
			System.out.println("No such element found");

		}

	}

	public void click(String testCaseName, String testStepId, String objectName)
			throws BiffException, IOException {

		String pageObject[] = ob.getObjectDetails(objectName);
		try {

			if (pageObject[0].equals("xpath")) {
				DriverApp.driver.findElement(By.xpath(pageObject[1])).click();
			}
		} catch (ElementNotFoundException e) {
			System.out.println("No such element found");

		}

	}

	public void type(String testCaseName, String testStepId, String objectName,
			String value) throws BiffException, IOException {

		String pageObject[] = ob.getObjectDetails(objectName);

		try {

			if (pageObject[0].equals("xpath")) {
				DriverApp.driver.findElement(By.xpath(pageObject[1])).clear();
				DriverApp.driver.findElement(By.xpath(pageObject[1])).sendKeys(
						value);
			}
		} catch (ElementNotFoundException e) {
			System.out.println("No such element found");

		}
	}

	public void enterURL(String objectName) throws BiffException, IOException {
		Sheet testSuiteSheet = new ReadExl().getSheet("MasterConfiguration");
		String baseURL = testSuiteSheet.getCell(1, 2).getContents();
		DriverApp.driver.get(baseURL + objectName);
	}

	public void closeBrowser() throws BiffException, IOException {
		DriverApp.driver.close();
		// DriverApp.driver.quit();

	}

	public static WebElement waitForPageToLoad(By by) {

		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(DriverApp.driver)
				.withTimeout(20, TimeUnit.SECONDS)
				.pollingEvery(2, TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class);

		WebElement element = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.id("foo"));
			}
		});
		return element;
	}

	public void testLogoLink(String objectName, String assertionValue)
			throws BiffException, IOException, InterruptedException {
		String pageObject[] = ob.getObjectDetails(objectName);

		if (pageObject[0].equals("xpath")) {
			String parentHandle = DriverApp.driver.getWindowHandle();
			DriverApp.driver.findElement(By.xpath(pageObject[1])).click();
			Set<String> handles = DriverApp.driver.getWindowHandles();
			String currURL = null;
			for (String handle : handles) {
				DriverApp.driver.switchTo().window(handle);
				currURL = DriverApp.driver.getCurrentUrl();
			}
			System.out.println("Current Title is:" + currURL);
			System.out.println("Expected Title is:" + assertionValue);
			Assert.assertEquals(currURL, assertionValue);
			DriverApp.driver.close();
			DriverApp.driver.switchTo().window(parentHandle);
		}

	}

	public void customerCareLink(String objectName, String assertionValue)
			throws BiffException, IOException {
		String pageObject[] = ob.getObjectDetails(objectName);

		if (pageObject[0].equals("xpath")) {
			String parentHandle = DriverApp.driver.getWindowHandle();
			DriverApp.driver.findElement(By.xpath(pageObject[1])).click();
			Set<String> handles = DriverApp.driver.getWindowHandles();
			String currURL = null;
			for (String handle : handles) {
				DriverApp.driver.switchTo().window(handle);
				currURL = DriverApp.driver.getCurrentUrl();
			}
			System.out.println("Current Title is:" + currURL);
			System.out.println("Expected Title is:" + assertionValue);
			Assert.assertEquals(currURL, assertionValue);
			DriverApp.driver.switchTo().window(parentHandle);
		}

	}

	public void dbDecryption(String assertionValue, String inputdata)
			throws BiffException, IOException, ClassNotFoundException,
			SQLException {

		ReadDatabase database = new ReadDatabase();
		Connection con = database.getconnection();
		Statement stmt = null;
		String Result = null;

		String ObjectValCh = inputdata;
		stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(ObjectValCh);

		while (rs.next()) {
			Result = rs.getString(1);
			System.out.println("Mobile Number = " + Result);

			try {
				StandardPBEStringEncryptor textEncryptor = new StandardPBEStringEncryptor();
				textEncryptor.setPassword("narvar");
				String plainText = textEncryptor.decrypt(Result);
				System.out.println("Mobile Number Digit =" + plainText);
				Assert.assertEquals(plainText, assertionValue);
				System.out.println("Mobile Number Assertion: Passed");

			}

			catch (Exception ex) {
				ex.printStackTrace();

			}
		}

		con.close();
	}

	public void overallScore(String assertionValue, String inputdata)
			throws BiffException, IOException, ClassNotFoundException,
			SQLException {

		ReadDatabase database = new ReadDatabase();
		Connection con = database.getconnection();
		Statement stmt = null;
		String Result = null;

		String ObjectValCh = inputdata;
		stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(ObjectValCh);

		while (rs.next()) {
			Result = rs.getString(1);
			System.out.println("Overall Score = " + Result);

			Assert.assertEquals(Result, assertionValue);
			System.out.println("Overall Score Assertion: Passed");
		}

		con.close();
	}

	public void itemcondScore(String assertionValue, String inputdata)
			throws BiffException, IOException, ClassNotFoundException,
			SQLException {

		ReadDatabase database = new ReadDatabase();
		Connection con = database.getconnection();
		Statement stmt = null;
		String Result = null;

		String ObjectValCh = inputdata;
		stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(ObjectValCh);

		while (rs.next()) {
			Result = rs.getString(1);
			System.out.println("Item Code = " + Result);

			Assert.assertEquals(Result, assertionValue);
			System.out.println("Item Code Assertion: Passed");
		}

		con.close();
	}

	public void packagingScore(String assertionValue, String inputdata)
			throws BiffException, IOException, ClassNotFoundException,
			SQLException {

		ReadDatabase database = new ReadDatabase();
		Connection con = database.getconnection();
		Statement stmt = null;
		String Result = null;

		String ObjectValCh = inputdata;
		stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(ObjectValCh);

		while (rs.next()) {
			Result = rs.getString(1);
			System.out.println("Packaging Score = " + Result);

			Assert.assertEquals(Result, assertionValue);
			System.out.println("Packaging Score Assertion: Passed");

		}

		con.close();
	}

	public void uidesignScore(String assertionValue, String inputdata)
			throws BiffException, IOException, ClassNotFoundException,
			SQLException {

		ReadDatabase database = new ReadDatabase();
		Connection con = database.getconnection();
		Statement stmt = null;
		String Result = null;

		String ObjectValCh = inputdata;
		stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(ObjectValCh);

		while (rs.next()) {
			Result = rs.getString(1);
			System.out.println("UI Design Score = " + Result);

			Assert.assertEquals(Result, assertionValue);
			System.out.println("UI Designed Assertion: Passed");

		}

		con.close();
	}

	public void shortComment(String assertionValue, String inputdata)
			throws BiffException, IOException, ClassNotFoundException,
			SQLException {

		ReadDatabase database = new ReadDatabase();
		Connection con = database.getconnection();
		Statement stmt = null;
		String Result = null;

		String ObjectValCh = inputdata;
		stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(ObjectValCh);

		while (rs.next()) {
			Result = rs.getString(1);
			System.out.println("Short Comment = " + Result);

			Assert.assertEquals(Result, assertionValue);
			System.out.println("Short Comment Assertion: Passed");

		}

		con.close();
	}

	public void longComment(String assertionValue, String inputdata)
			throws BiffException, IOException, ClassNotFoundException,
			SQLException {

		ReadDatabase database = new ReadDatabase();
		Connection con = database.getconnection();
		Statement stmt = null;
		String Result = null;

		String ObjectValCh = inputdata;
		stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(ObjectValCh);

		while (rs.next()) {
			Result = rs.getString(1);
			System.out.println("Long Comment = " + Result);

			Assert.assertEquals(Result, assertionValue);
			System.out.println("Long Comment Assertion: Passed");

		}

		con.close();
	}

	public void clickCounts(String assertionValue, String inputdata)
			throws BiffException, IOException, ClassNotFoundException,
			SQLException {

		ReadDatabase database = new ReadDatabase();
		Connection con = database.getconnection();
		Statement stmt = null;
		String Result = null;

		String ObjectValCh = inputdata;
		stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(ObjectValCh);

		while (rs.next()) {
			Result = rs.getString(1);
			System.out.println("Number of Clicks = " + Result);

			Assert.assertEquals(Result, assertionValue);
			System.out.println("Number of Clicks Assertion: Passed");
		}

		con.close();
	}

	public void verifyImageDisplay(String objectName) throws BiffException,
			IOException {
		String pageObject[] = ob.getObjectDetails(objectName);

		try {

			if (pageObject[0].equals("xpath")) {
				Assert.assertEquals(true,
						DriverApp.driver.findElement(By.xpath(pageObject[1]))
								.isDisplayed());
			}
		} catch (ElementNotFoundException e) {
			System.out.println("No such element found");

		}
	}

	public void trackingNumberFlip(String objectName, String assertionValue)
			throws BiffException, IOException {

		String pageObject[] = ob.getObjectDetails(objectName);
		try {

			if (pageObject[0].equals("xpath")) {
				DriverApp.driver.findElement(By.xpath(pageObject[1])).click();
				String titleSecondPanel = DriverApp.driver
						.findElement(
								By.xpath("//span[contains(.//text(), 'Recent Shipment History')]"))
						.getText();
				System.out.println(titleSecondPanel);
				Assert.assertEquals(titleSecondPanel, assertionValue);
			}
		} catch (ElementNotFoundException e) {
			System.out.println("No such element found");

		}

	}

	public void recentHistoryFlip(String objectName, String assertionValue)
			throws BiffException, IOException {

		String pageObject[] = ob.getObjectDetails(objectName);
		try {

			if (pageObject[0].equals("xpath")) {
				DriverApp.driver.findElement(By.xpath(pageObject[1])).click();
				String titleSecondPanel = DriverApp.driver
						.findElement(
								By.xpath("//span[contains(.//text(), 'Recent Shipment History')]"))
						.getText();
				System.out.println(titleSecondPanel);
				Assert.assertEquals(titleSecondPanel, assertionValue);
			}
		} catch (ElementNotFoundException e) {
			System.out.println("No such element found");

		}

	}

	public void calenderDatePicker(String objectName, String assertionValue)
			throws BiffException, IOException {

		String pageObject[] = ob.getObjectDetails(objectName);
		try {

			if (pageObject[0].equals("xpath")) {
				DriverApp.driver.findElement(By.xpath(pageObject[1])).clear();
				DriverApp.driver.findElement(By.xpath(pageObject[1])).click();

			}
		} catch (ElementNotFoundException e) {
			System.out.println("No such element found");

		}

	}

}
