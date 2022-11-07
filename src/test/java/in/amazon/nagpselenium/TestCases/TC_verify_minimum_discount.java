package in.amazon.nagpselenium.TestCases;

import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.annotations.Test;

import in.amazon.nagpselenium.base.BaseTest;

public class TC_verify_minimum_discount extends BaseTest {
	
	@Test(priority=1,groups = {"Chrome"},description="minimum discount")
	public void TC_verifyMinimuDiscount(Method method) throws NumberFormatException, IOException {
		ele.clickOnDiscount();
		String dis=ele.validateDiscountApplied();
	}

}
