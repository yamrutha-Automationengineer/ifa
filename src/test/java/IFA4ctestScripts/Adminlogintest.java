package IFA4ctestScripts;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class Adminlogintest {
	WebDriver driver;
	 Logger log = Logger.getLogger(Adminlogintest.class);
	 
	 @BeforeMethod
		public void setup() throws InterruptedException{
			log.info("****************************** Starting test cases execution  *****************************************");

			System.setProperty("webdriver.chrome.driver", "C:\\Users\\Amruta.Yembuluru\\driver\\chromedriver_win32\\chromedriver.exe");
		//to run on Mozillafirefox browser
		//System.setProperty("webdriver.chrome.driver", "C:\\Users\\amruta_yembuluru\\driver\\geckodriver-v0.20.1-win64\\geckodriver.exe");
		 driver = new ChromeDriver();
			// driver.manage().window().maximize();
		
		 driver.manage().deleteAllCookies();
		 driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		 Thread.sleep(2000);
		 driver.get("https://admin.4cplatform-staging.com");  
				 log.info("entering application URL");
		
	 }
			@Test(priority=1)
			public void AdminTitleTest() throws InterruptedException{

		 //gettitle
		 String i = driver.getTitle();
		  System.out.println("Your page title Is : "+i);
	
		
			 Assert.assertEquals("4C Platform",i);
			 Assert.assertEquals("4C Platform",driver.getTitle());
			}
						
			@Test(priority=2)
			public void AdLoginTest() throws InterruptedException{
				//to click on login 
				  driver.findElement(By.xpath("//a[@href='/login']")).click();
		  Thread.sleep(2000);
		  //entering email address
		  driver.findElement(By.xpath("//input[@name='email']")).sendKeys("Sadmin@usaycompare.com");
		  //entering password
			 driver.findElement(By.xpath("//input[@name='password']")).sendKeys("CXoF2sL5w2");
			 //Remember me checkbox
			 driver.findElement(By.name("remember")).click();
			 //signin button
	driver.findElement(By.xpath("//button[@form='loginForm']")).click();
	//checking the useremail dispaly at the top left corner. 
	driver.findElement(By.xpath("//button[@value='Super-administrator']")).click();
		 		 Thread.sleep(2000); 
			}
			@AfterMethod
			public void tearDown(){
				driver.quit();
				log.info("****************************** Browser is closed *****************************************"); 

	}

}

