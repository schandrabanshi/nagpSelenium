/**
 * 
 */
package in.amazon.nagpselenium.common.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import in.amazon.nagpselenium.reporterLogging.Logging;
import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * @author sarojchandrabanshi
 * Base class for all driver related generic functions/methods.
 */
public class Base {
	public static WebDriver Driver=null;
	public static final String runDateTime=getDateTime("dd_M_yyyy_hh_mm_ss");
	public static final String screenShotPath=System.getProperty("user.dir")+"/current_test_results/"+runDateTime;
	
	/**
	 * Itializing broser based on passed parameter. Default browser is chrome.
	 * @param browserType : Based on input (Chrome, Firefox or Edge)
	 */	
	public synchronized static void Init(String browserType) 
	{
		
		if (Driver==null) {
			Logging.log.info("Opening "+browserType+" browser.");
			switch(browserType) {
			case "Firefox":
				WebDriverManager.firefoxdriver().driverVersion("105.0").arch64().setup();
				FirefoxOptions option=new FirefoxOptions();
				option.setAcceptInsecureCerts(true);
				option.setCapability("headless", true);
				option.setCapability("marionette",true);
				DesiredCapabilities cap=new DesiredCapabilities();
				cap.setJavascriptEnabled(true);
				option.merge(cap);
				Driver= new FirefoxDriver(option);				
				break;
			case "Edge":
				WebDriverManager.edgedriver().arch64().driverVersion("105.0.1343.42").setup();
				Driver= new EdgeDriver();			
				
				break;
			case "Safari":
				WebDriverManager.safaridriver().setup();
				Driver= new SafariDriver();
				break;
			case "Chrome":
			default:
				WebDriverManager.chromedriver().setup();
				//.driverVersion("102.0.5005.63").arch64()
				//System.getProperty("webdriver.chrome.driver", "C:\\Users\\sarojchandrabanshi\\eclipse-workspace\\TestNGSelenium\\chromedriver.exe");
				Driver= new ChromeDriver();
				break;
			
			}
			
		}
	}
	/**
	 * Closing all opened windows or tabs.
	 */
	public static void Quit() 
	{
		Driver.quit();
	}
	/**
	 * Closing current or focused window or tab.
	 */
	public static void Close() 
	{
		Driver.close();
	}
	/**
	 * implicit wait based on passed time interver in seconds.
	 * @param timeSecond
	 */
	public final static void Wait(int timeSecond) 
	{
		Driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeSecond));
	}
	/**
	 * Explicit wait based on passed element visible and clickable. Maxwait time is 60 seconds i.e. passed from config file.
	 * @param ele WebElement looking for.
	 */
	public final static void Wait(WebElement ele) 
	{
		WebDriverWait wait;
		try {
			wait = new WebDriverWait(Driver, Duration.ofSeconds(Integer.parseInt(FileUtility.getConfigProperties("MaxwaitTime"))));
			wait.until(ExpectedConditions.visibilityOf(ele));
			wait.until(ExpectedConditions.elementToBeClickable(ele));
		} catch (NumberFormatException | IOException e) {
			Logging.log.error("Unable to conver config file MaxWatTime to integer or config.properties file not found.");
			e.printStackTrace();
		}
				
	}
	/**
	 * Scrolling to view webelement.
	 * @param ele WebElement looking for.
	 */
	public final static void ScrollToView(WebElement ele) {
		((JavascriptExecutor) Driver).executeScript("arguments[0].scrollIntoView(true);", ele);
		Wait(30);
		
	}
	/**
	 * Selecting Text value from dropdown options.
	 * @param ele :Dropdown option webelement.
	 * @param option :Text to select from dropdown.
	 */
	public static void SelectFromDropdown(WebElement ele,String option) {
		Select options=new Select(ele);
		options.selectByVisibleText(option);		
	}
	
	
	/**
	 * Caturing page screenshot.
	 * @param name Passing the require name for captured image.
	 * @return Returning path for stored image.
	 * @throws WebDriverException Throwing exception in case failure.
	 * @throws IOException Throwing file exception.
	 */
	public final static String Capture(String name) throws WebDriverException, IOException {
		
		String  dest=screenShotPath+"/"+name+".png";
		FileUtils.copyFile(((TakesScreenshot)Driver).getScreenshotAs(OutputType.FILE), new File(dest));	
		return dest;
	}
	/**
	 * Switch to child window or new window.
	 */
	public synchronized static void SwitchToNewWindow() {
		String homeWindow = Driver.getWindowHandle();
	    Set<String> allWindows = Driver.getWindowHandles();

	    
	    Iterator<String> windowIterator =  allWindows.iterator();

	    //Verify next window is available
	    while(windowIterator.hasNext()){

	    	String childWindow = windowIterator.next();	            
	         
	        if (!homeWindow.equals(childWindow)){	        	
	        	Driver.switchTo().window(childWindow);	            
	        }
	     }	
	}
	/**
	 * Verify title of page.
	 * @param title Expected page title.
	 */
	public synchronized static void VerifyPageTitle(String title) {
		Assert.assertEquals(Driver.getTitle(), title,"Page title is wrong");
	}
	/**
	 * Refresh window or tab.
	 */
	public static void Refresh() {
		Driver.navigate().refresh();
	}
	/**
	 * Move back on window or tab.
	 */
	public static void MoveBack() {
		Driver.navigate().back();
	}
	/**
	 * Move to forword.
	 */
	public static void MoveForward() {
		Driver.navigate().forward();
	}
	/**
	 * Get current datetime based on format.
	 * @param format Required format.
	 * @return Returning datetime in required format.
	 */
	public static String getDateTime(String format) {
		Date date= new Date();
		SimpleDateFormat  formatter = new SimpleDateFormat(format);  
		return formatter.format(date);
	}
	/**
	 * Switch to frame by id or name.
	 * @param frame Frame id or name.
	 */
	public synchronized static void SwitchToFrame(String frame) {
		Driver.switchTo().frame(frame);
	}
	
	
}
