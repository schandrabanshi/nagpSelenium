/**
 * 
 */
package in.amazon.nagpselenium.listeners;
import java.io.IOException;

import org.openqa.selenium.WebDriverException;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;

import in.amazon.nagpselenium.common.util.Base;
import in.amazon.nagpselenium.common.util.FileUtility;
import in.amazon.nagpselenium.reporterLogging.ExtentManager;
import in.amazon.nagpselenium.reporterLogging.ExtentTestManager;
import in.amazon.nagpselenium.reporterLogging.Logging;
/**
 * @author sarojchandrabanshi
 *
 */
public class Listener extends Logging implements ITestListener{

	
	private static String getTestMethodName(ITestResult iTestResult) {
		return iTestResult.getMethod().getConstructorOrMethod().getName();
	}
	
	/**
	   * Invoked each time before a test will be invoked. The <code>ITestResult</code> is only partially
	   * filled with the references to class, method, start millis and status.
	   *
	   * @param result the partially filled <code>ITestResult</code>
	   * @see ITestResult#STARTED
	   */
	  public void onTestStart(ITestResult result) {
	    log.info("Execution started for test case: "+getTestMethodName(result));
	    ExtentTestManager.startTest(result.getName(),result.getMethod().getDescription());
	  }

	  /**
	   * Invoked each time a test succeeds.
	   *
	   * @param result <code>ITestResult</code> containing information about the run test
	   * @see ITestResult#SUCCESS
	   */
	  public void onTestSuccess(ITestResult result) {
		  log.info("Test case: "+getTestMethodName(result)+" execution is succeed.");
		  ExtentTestManager.getTest().log(Status.PASS,result.getName()+" execution is passed.");
	  }

	  /**
	   * Invoked each time a test fails.
	   *
	   * @param result <code>ITestResult</code> containing information about the run test
	   * @see ITestResult#FAILURE
	   */
	  public void onTestFailure(ITestResult result) {
		  log.info(getTestMethodName(result)+" is failed because of "+result.getThrowable().getMessage());
		  String screenshotpath=null;		  
		  try {			  
			  screenshotpath=Base.Capture(result.getName()+"_"+result.getThrowable().getMessage().substring(0, 15));
			   
		} catch (WebDriverException e) {
			ExtentTestManager.getTest().log(Status.FAIL, result.getThrowable().getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			ExtentTestManager.getTest().log(Status.FAIL, result.getThrowable().getMessage());
			e.printStackTrace();
		}
		  ExtentTestManager.getTest().log(Status.FAIL, result.getThrowable().getMessage(),
				  ExtentTestManager.getTest().addScreenCaptureFromPath(screenshotpath).getModel().getMedia().get(0));
		  
	  }

	  /**
	   * Invoked each time a test is skipped.
	   *
	   * @param result <code>ITestResult</code> containing information about the run test
	   * @see ITestResult#SKIP
	   */
	  public void onTestSkipped(ITestResult result) {
	    log.info(getTestMethodName(result)+" execution is skipped.");
	    ExtentTestManager.getTest().log(Status.SKIP, "Test execution is skipped");
	    
	  }

	  /**
	   * Invoked each time a method fails but has been annotated with successPercentage and this failure
	   * still keeps it within the success percentage requested.
	   *
	   * @param result <code>ITestResult</code> containing information about the run test
	   * @see ITestResult#SUCCESS_PERCENTAGE_FAILURE
	   */
	  public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	    log.info("Test failed but it is in defined success ratio "+getTestMethodName(result));
	  }

	  /**
	   * Invoked each time a test fails due to a timeout.
	   *
	   * @param result <code>ITestResult</code> containing information about the run test
	   */
	  public void onTestFailedWithTimeout(ITestResult result) {
	    onTestFailure(result);
	  }

	  /**
	   * Invoked before running all the test methods belonging to the classes inside the &lt;test&gt;
	   * tag and calling all their Configuration methods.
	   *
	   * @param context The test context
	   */
	  public void onStart(ITestContext context) {	
		  log.info("Starting execution on "+context.getName());
		  context.setAttribute("WebDriver", Base.Driver);
		  FileUtility.archiveReport();
	  }

	  /**
	   * Invoked after all the test methods belonging to the classes inside the &lt;test&gt; tag have
	   * run and all their Configuration methods have been called.
	   *
	   * @param context The test context
	   */
	  public void onFinish(ITestContext context) {
	    log.info("Execution ends on "+context.getName());
	    ExtentManager.extentReports.flush();
	  }
}
