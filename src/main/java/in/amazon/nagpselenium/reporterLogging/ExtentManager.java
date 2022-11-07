/**
 * 
 */
package in.amazon.nagpselenium.reporterLogging;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import in.amazon.nagpselenium.common.util.Base;

/**
 * @author sarojchandrabanshi
 * This class to intialize extent manager and creating extent report.
 */
public class ExtentManager {
	public static final ExtentReports extentReports=new ExtentReports();
	/**
	 * Creating instance of extent report.
	 * @return extentReports onject.
	 */
	public synchronized static ExtentReports createExtentReports() {
		ExtentSparkReporter reporter=new ExtentSparkReporter(System.getProperty("user.dir")+"/current_test_results/"+Base.runDateTime+"/ExecutionReport.html");
		reporter.config().setReportName("Amazon.in Execution Report");
		extentReports.attachReporter(reporter);
		extentReports.setSystemInfo("Task", "NAGP Selenium Assignment");
		extentReports.setSystemInfo("Author", "Saroj Chandrabanshi");
		return extentReports;
	}
}
