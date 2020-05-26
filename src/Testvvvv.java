import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Testvvvv {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/AutomationPractice");
		File inFile = new File("C:\\Users\\charl\\Desktop\\Script.txt");
		StringBuilder targetString = new StringBuilder("");
		try {
			FileReader fr = new FileReader(inFile);
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(fr);

			String s = null;
			while ((s = br.readLine()) != null) {
				targetString.append(s);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		Thread.sleep(2000L);
		WebElement Bucket_Policy_Text = driver.findElement(By.xpath("//input[@id='name']"));
		Bucket_Policy_Text.click();
		Thread.sleep(2000L);
		Bucket_Policy_Text.sendKeys(targetString);

	}

}
