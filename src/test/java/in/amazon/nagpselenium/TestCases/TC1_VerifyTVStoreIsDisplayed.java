package in.amazon.nagpselenium.TestCases;

import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.annotations.Test;

import in.amazon.nagpselenium.base.BaseTest;

public class TC1_VerifyTVStoreIsDisplayed extends BaseTest {

	@Test(priority=1,groups = {"Chrome"},description="Verifying TV store page displayed.")
	public void TC_verifyTvStore(Method method) throws NumberFormatException, IOException {
		ele.navigateToTVStore();
	}
}
