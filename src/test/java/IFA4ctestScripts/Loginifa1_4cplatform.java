package IFA4ctestScripts;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Loginifa1_4cplatform {

	WebDriver driver;
	 Logger log = Logger.getLogger(Loginifa1_4cplatform.class);
	 
	 @BeforeMethod
		public void setup() throws InterruptedException{
			
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\Amruta.Yembuluru\\driver\\chromedriver_win32\\chromedriver.exe");
	
		 driver = new ChromeDriver();
			// driver.manage().window().maximize();
		
		 driver.manage().deleteAllCookies();
		 driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		 Thread.sleep(2000);
		 log.info("entering application URL");
		 driver.get("https://ifa1.4cplatform-staging.com");
		
		 log.info("****************************** Starting test cases execution  *****************************************");
	 }
			@Test(priority=1)
			public void IFATitleTest() throws InterruptedException{

		 //gettitle
		 String i = driver.getTitle();
		  System.out.println("Your page title Is : "+i);
	
		
			 Assert.assertEquals("4C Platform",i);
			 Assert.assertEquals("4C Platform",driver.getTitle());
			}
			@Test(priority=2)
			public void SimpletextTest() throws InterruptedException{
       WebElement Simpletextdv = driver.findElement(By.xpath("//h1[contains(.,'Simple PMI for brokers & IFAs.')]"));
		String Simpletextd = Simpletextdv.getText();
		 System.out.println(Simpletextd);
		 Boolean Simpletextv = Simpletextdv.isDisplayed();
		 System.out.println(Simpletextv);
			 Thread.sleep(2000);
			}
			@Test(priority=3)
			public void LoginTest() throws InterruptedException{
		  driver.findElement(By.linkText("LOGIN")).click();
		  Thread.sleep(2000);
		  WebElement email = driver.findElement(By.xpath( "//input[@id='email']"));	
		  email.sendKeys("ifa1_user1@usaycompare.com");
		  WebElement pwd = driver.findElement(By.xpath( "//input[@id='password']"));	
		  pwd.sendKeys("secret");
		  driver.findElement(By.xpath("//button[contains(.,'Sign in  ')]")).click(); 
		 		
		 		 Thread.sleep(2000); 
			}
			@AfterMethod
			public void tearDown(){
				driver.quit();
				log.info("****************************** Browser is closed *****************************************");

	}

}