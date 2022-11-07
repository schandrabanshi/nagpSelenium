/**
 * 
 */
package in.amazon.nagpselenium.base;
import in.amazon.nagpselenium.common.util.*;
import in.amazon.nagpselenium.pages.electronics;

import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.annotations.*;

/**
 * @author sarojchandrabanshi
 *This is base test class for intializing browser and test url. Intializing all pages instances.
 */
public class BaseTest {
	/**
	 * Intializing browser and opening test url based on passed browser type.
	 * @param browser
	 */
	@Parameters("browser")
	@BeforeClass(alwaysRun = true)
	public void Initialize(String browser) {
		try {
			Base.Init(browser);
			Base.Driver.get(FileUtility.getConfigProperties("TestURL"));
			Base.Driver.manage().window().maximize();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Closing browser/windows.
	 */
	@AfterTest(alwaysRun = true)
	public void Finish() {
		Base.Close();
		Base.Quit();
	}
	

	public electronics ele;
	/**
	 * Creating page objects.
	 * @param method
	 */
	@BeforeMethod(alwaysRun = true)
	public void CreateInstance(Method method) {
		ele=new electronics(Base.Driver);
	}
	/**
	 * Closing all page instances.
	 */
	@AfterSuite(alwaysRun = true)
	public void CloseInstance() {
		ele=null;
	}
	
	
}
