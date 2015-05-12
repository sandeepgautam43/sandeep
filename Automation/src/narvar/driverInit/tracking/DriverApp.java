package narvar.driverInit.tracking;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import jxl.Sheet;
import jxl.read.biff.BiffException;
import narvar.readExcelFiles.tracking.ReadExl;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class DriverApp {

public static WebDriver driver;
	

/*---------------Invoking the Web Browser--------------------------------*/
	
    @BeforeSuite
    public void BrowserType() throws BiffException, IOException {
    	Sheet testSuiteSheet = new ReadExl().getSheet("MasterConfiguration");
		String BrowserType =testSuiteSheet.getCell(1, 1).getContents();
		try {
			if (BrowserType.equalsIgnoreCase("FF")) {
				driver = new FirefoxDriver();
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				driver.manage().window().maximize();
				driver.manage().deleteAllCookies();
			} else if (BrowserType.equalsIgnoreCase("CHROME")) {
				System.setProperty("webdriver.chrome.driver",
						"/drivers/chromedriver");
				driver = new ChromeDriver();
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				driver.manage().window().maximize();
			} else if (BrowserType.equalsIgnoreCase("IE")) {
				System.setProperty("webdriver.ie.driver",
						"/drivers/IEDriverServer.exe");
				driver = new InternetExplorerDriver();
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				driver.manage().window().maximize();
			}
		      else if (BrowserType.equalsIgnoreCase("SELENDROID")) {
				System.setProperty("webdriver.ie.driver",
						"/drivers/IEDriverServer.exe");
				driver = new InternetExplorerDriver();
			}
			
			
		} catch (WebDriverException e) {
			System.out.println("Please type the correct browser Name:"+e.getMessage());
		}
	}
		
		@AfterSuite
		public void tearDown(){
			driver.close();
			driver.quit();
		}

}
