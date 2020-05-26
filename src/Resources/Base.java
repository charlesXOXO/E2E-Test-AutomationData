package Resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;


public class Base {

	public static WebDriver driver;
	public static Properties prop;

	public static WebDriver intializeDriver() throws IOException 
	{
		prop = new Properties();
		FileInputStream fis = new FileInputStream(
				"C:\\Users\\charl\\E2EAWSArchitecture\\src\\Resources\\Data.properties");
		prop.load(fis);
		String browser_name = prop.getProperty("browser");
		if (browser_name.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
			driver = new ChromeDriver();
		}

		else if (browser_name.equalsIgnoreCase("firefox")) {

			System.setProperty("webdriver.gecko.driver", "C:\\geckodriver.exe");
			driver = new FirefoxDriver();

		}
		else if (browser_name.equalsIgnoreCase("I.E")){
			System.setProperty("webdriver.ie.driver", "C:\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		return driver;
	}
	
	

}
