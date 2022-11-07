package in.amazon.nagpselenium.TestCases;

import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.annotations.Test;

import in.amazon.nagpselenium.base.BaseTest;
import in.amazon.nagpselenium.common.util.Base;

public class TC3_AddedToCart_Message extends BaseTest{

	@Test(priority=1,groups = {"Chrome"},description="Verifying TV store page displayed.")
	public void TC_AddedToCart(Method method) throws NumberFormatException, IOException {
		ele.addToCart();
		 Base.Wait(2);
		ele.validateAddedToCardMessage();
	}
}
