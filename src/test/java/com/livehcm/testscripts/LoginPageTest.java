package com.livehcm.testscripts;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.livehcm.pages.HomePage;
import com.livehcm.pages.LoginPage;
import com.livehcm.utils.TestBase;
import com.livehcm.utils.TestUtil;

public class LoginPageTest extends TestBase {

	LoginPage loginpage;
	HomePage homepage;
	TestUtil util;

	public LoginPageTest() {
		super();
	}

	@BeforeMethod
	public void SetUp() throws InterruptedException, IOException {
		browser_init();
		loginpage = new LoginPage();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 0)
	public void ValidateLoginPageTitle() {
		String pt = loginpage.ValidateLoginPageTitle();
		Reporter.log("Login Page Title is: " + pt, true);
		assertEquals(pt, "Login");

	}

	@Test(priority = 1)
	public void ValidateLoginPageURL() {
		String lp_url = loginpage.ValidateLoginPageURL();
		Reporter.log("LoginPageURL is " + lp_url, true);
		assertEquals(lp_url, "http://192.168.2.140:8080/hcm/");

	}

	@Test(priority = 2)
	public void ValidateLoginPagePhoneLogo() {
		boolean phone_logo = loginpage.VerifyPhoneLogo();
		if (phone_logo) {
			Reporter.log("Phone Logo is Displayed in Login Page", true);
		} else {
			Reporter.log("Phone Logo is Not Displayed in Login Page", true);
		}
		assertTrue(phone_logo);

	}

	@Test(priority = 3)
	public void Login() throws InterruptedException, IOException {
		homepage = loginpage.PerformLogin(prop.getProperty("username"), prop.getProperty("password"));
		TestUtil.takeScreenshotAtEndOfTest();
	}

}
