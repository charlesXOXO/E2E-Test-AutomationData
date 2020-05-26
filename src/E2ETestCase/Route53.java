package E2ETestCase;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import PageObject.AWS_Dashboard;
import PageObject.Landing_Page;
import PageObject.Login_Page;
import Resources.Base;

public class Route53 extends Base {

	
	@BeforeTest
	public void Route53_merge() throws InterruptedException, IOException {

		driver = intializeDriver();

		Landing_Page Landing = new Landing_Page(driver);
		Login_Page Login = new Login_Page(driver);
		AWS_Dashboard Dropdown = new AWS_Dashboard(driver);
		String Username = prop.getProperty("Username");
		String Password = prop.getProperty("Password");
		String Account_ID = prop.getProperty("Account_ID");
		String URL = prop.getProperty("URL");

		driver.get(URL);
		Landing.get_login().click();

		Login.ExistingUser().click();
		Login.IAM_User().click();
		Login.AccountID().sendKeys(Account_ID);
		Login.Next_button().click();

		// Enter the credentials to your IAM ROLE assinged by AWS Administrator

		Login.Username().sendKeys(Username);
		Login.Password().sendKeys(Password);
		Login.SignIn().click();
		Dropdown.Service_Dropdown().click();
	}

	@Test
	public void route53_create() throws InterruptedException {
		
		String Domain_Name = prop.getProperty("DOMAINNAME");
		String CNAME = prop.getProperty("CNAME");
		String Fetched_Domain = "";
		AWS_Dashboard Dropdown = new AWS_Dashboard(driver);
		
		WebElement Cloudfront = driver.findElement(By.xpath("//*[@id='awsc-services-container']/ul[1]/li[36]/a/span"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement scroll = Cloudfront;
		js.executeScript("arguments[0].scrollIntoView(true)", scroll);

		Cloudfront.click();
		
		
		
		/* Finding Unique Domain_Name of the newly created cloud front distribution for Route53 Creation*/
		WebElement Table = driver.findElement(By.xpath("//table[@class='GHRMFH5CMAE']"));
		int count_row = Table.findElements(By.xpath("//table[@class='GHRMFH5CMAE']/tbody/tr")).size();
		System.out.println(count_row);
		int count_coloumns = Table.findElements(By.xpath("//table[@class='GHRMFH5CMAE']/tbody/tr/td[7]")).size();
		for (int i = 0; i < count_coloumns; i++) 
		{
			String domainName = Table.findElements(By.xpath("//table[@class='GHRMFH5CMAE']/tbody/tr/td[7]")).get(i)
					.getText();

			if (domainName.equalsIgnoreCase(CNAME)) {
				Fetched_Domain = Table.findElements(By.xpath("//table[@class='GHRMFH5CMAE']/tbody/tr/td[4]")).get(i)
						.getText();
			}
			System.out.println(Fetched_Domain);
		}
		
		
		Dropdown.Service_Dropdown().click();
		WebElement Route53_Select = driver
				.findElement(By.xpath("//*[@id='awsc-services-container']/ul[1]/li[37]/a/span"));
		Route53_Select.click();

		WebElement Hosted_Zones = driver.findElement(By.xpath("//*[contains(text(),'Hosted zones')]"));
		Hosted_Zones.click();

		WebElement domain_Name_Click = driver.findElement(By.linkText(Domain_Name));
		domain_Name_Click.click();
		WebElement Create_RecordSet = driver.findElement(By.xpath("//div[@class='GLATOE0HNE']/button[2]"));
		Create_RecordSet.click();

		WebElement recordSetName = driver.findElement(By.xpath("//input[@class='GLATOE0P1D']"));
		recordSetName.click();
		String parts[] = CNAME.split("\\.");
		String recordName = parts[0];
		recordSetName.sendKeys(recordName);
		WebElement Alias_Yes = driver.findElement(By.xpath("//input[@name='aliased']"));
		Alias_Yes.click();
		
		WebElement AliasName = driver
				.findElement(By.xpath("//input[@class='gwt-SuggestBox GLATOE0EFE GLATOE0BHE GLATOE0PGE GLATOE0IFE']"));
		AliasName.click();
		AliasName.sendKeys(Fetched_Domain);
		WebElement FinalCreate = driver
				.findElement(By.xpath("//button[@class='GLATOE0FE GLATOE0D GLATOE0B GLATOE0L GLATOE0OGE']"));
		FinalCreate.click();
		
		
	}
	@AfterTest
	public void teardown() {
		driver.close();
	}

}
