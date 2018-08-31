package com.livehcm.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class TestBase {

	public static WebDriver driver;
	public static Properties prop;
	public static EventFiringWebDriver Event_driver;
	public static WebEventListner WebEvent_Listner;

	public TestBase() {
		try {

			prop = new Properties();
			FileInputStream fi = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\main\\java\\com\\livehcm\\testdata\\config.properties");
			prop.load(fi);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void browser_init() throws InterruptedException {
		String br = prop.getProperty("browser");

		if (br.equals("Chrome")) {
			System.setProperty("webdriver.chrome.driver",
					"D:\\Projects\\POM_Automation\\HCM_LIVE\\src\\main\\java\\com\\livehcm\\browsers\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (br.equals("Firefox")) {
			System.setProperty("webdriver.gecko.driver",
					"D:\\Projects\\POM_Automation\\HCM_LIVE\\src\\main\\java\\com\\livehcm\\browsers\\geckodriver.exe");
			driver = new FirefoxDriver();
		} else {
			System.out.println("No Browser Driver Available for Browser Instance");
		}

		Event_driver = new EventFiringWebDriver(driver);
		WebEvent_Listner = new WebEventListner();

		Event_driver.register(WebEvent_Listner);
		driver = Event_driver;

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);

		driver.get(prop.getProperty("url"));

		Thread.sleep(3000);

	}

}
