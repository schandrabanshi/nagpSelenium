/**
 * 
 */
package in.amazon.nagpselenium.reporterLogging;

import java.util.*;

import com.aventstack.extentreports.*;

/**
 * @author sarojchandrabanshi
 *This class for extent test manager to getTest and startTest.
 */
public class ExtentTestManager{
	static Map<Integer,ExtentTest> extentTestMap= new HashMap<>();
	
	static ExtentReports extent= ExtentManager.createExtentReports();
	/**
	 * Get Test for extent report with current thread.
	 * @return returning extent test object.
	 */
	public synchronized static  ExtentTest getTest() {
		return extentTestMap.get((int) Thread.currentThread().getId());		
	}
	/**
	 * Stating extent test with current thread.
	 * @param testName Passing test name.
	 * @param desc test case description to log into extent report.
	 * @return returning extent test object.
	 */
	public synchronized static ExtentTest startTest(String testName, String desc) {
		ExtentTest test=extent.createTest(testName,desc);
		extentTestMap.put((int)Thread.currentThread().getId(), test);
		return test;
	}
	
}
