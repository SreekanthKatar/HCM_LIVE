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

public class HomePageTest extends TestBase {

	LoginPage loginpage;
	HomePage homepage;
	TestUtil util;

	public HomePageTest() {
		super();
	}

	@BeforeMethod
	public void SetUp() throws InterruptedException, IOException {
		browser_init();
		loginpage = new LoginPage();
		homepage = loginpage.PerformLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@AfterMethod
	public void tearDown() {

		driver.quit();

	}

	@Test(priority = 0)
	public void ValidateHomePageTitle() {
		String pt = homepage.ValidateHomePageTitle();
		Reporter.log("Home Page Title is: " + pt, true);
		assertEquals(pt, "HCM");

	}

	@Test(priority = 1)
	public void ValidateHomePageURL() {
		String hp_url = homepage.ValidateHomePageURL();
		Reporter.log("HomePageURL is " + hp_url, true);
		assertEquals(hp_url, "http://192.168.2.140:8080/hcm/employeeSelfService");

	}

	@Test(priority = 2)
	public void ValidateHomePageHCMLogo() {
		boolean logo = homepage.VerifyHCMLogo();
		if (logo) {
			Reporter.log("HCM Logo is Displayed in Home Page", true);
		} else {
			Reporter.log("HCM Logo is Not Displayed in Home Page", true);
		}
		assertTrue(logo);

	}

}
