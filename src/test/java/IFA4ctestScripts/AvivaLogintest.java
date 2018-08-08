package IFA4ctestScripts;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AvivaLogintest {

	WebDriver driver;
	 Logger log = Logger.getLogger(Loginifa1_4cplatform.class);

	@SuppressWarnings("deprecation")
	@BeforeMethod
		public void setup() throws InterruptedException{
			log.info("****************************** Starting test cases execution  *****************************************");

		System.setProperty("webdriver.ie.driver", "C:\\Users\\Amruta.Yembuluru\\driver\\IEDriverServer_Win32_3.13.0\\IEDriverServer.exe");
		//to run on Mozillafirefox browser
		//System.setProperty("webdriver.chrome.driver", "C:\\Users\\amruta_yembuluru\\driver\\geckodriver-v0.20.1-win64\\geckodriver.exe");
		DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
		caps.setCapability("ignoreZoomSetting", true);
		caps.setCapability("ignoreProtectedModeSettings" , true);
		//Delete Browser Cache since IE does not open a clean profile unlike Chrome & FireFox
		try {
			Runtime.getRuntime().exec("RunDll32.exe InetCpl.cpl,ClearMyTracksByProcess 255");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver = new InternetExplorerDriver(caps);
		driver.manage().deleteAllCookies();
	
			// driver.manage().window().maximize();
		
		driver.manage().deleteAllCookies();
		 Thread.sleep(5000);
		 driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		 Thread.sleep(2000);
		 driver.get("https://aviva.4cplatform-staging.com");  
				 log.info("entering application URL");
		
			 driver.findElement(By.xpath("//a[@class='nav-link page-scroll'][contains(.,'LOGIN')]")).click();
			
			  WebElement email = driver.findElement(By.xpath( "//input[@id='email']"));	
			  email.sendKeys("provider_user1@usaycompare.com");
			  WebElement pwd = driver.findElement(By.xpath( "//input[@id='password']"));	
			  pwd.sendKeys("K*fZGF0X$YjL");
			  driver.findElement(By.xpath("//button[@form='loginForm'][contains(.,'Sign in')]")).click(); 
			 		 Thread.sleep(2000); 
			 	//Account manager
			 driver.findElement(By.xpath("//button[@name='submit'][contains(.,'Account manager')]")).click();
			 	//My account
			 Thread.sleep(2000); 
			 driver.findElement(By.xpath("//span[contains(.,'My account')]")).click();
				 }
		   @Test(priority=1)
		   public void UserprofileTest() {
		//Boolean title=  driver.findElement(By.xpath("//div[@class='panel-heading'][contains(.,'Account\r\n" + "Manage user profile')]")).isDisplayed();
			//  System.out.println(title);
			   
			 WebElement ltname=  driver.findElement(By.xpath("//input[@name='user[lastname]']"));
			 ltname.clear(); ltname.sendKeys("Aviva admin1");
			 WebElement ftname= driver.findElement(By.xpath("//input[@name='user[firstname]']"));
			 ftname.clear();ftname.sendKeys("AA");
			WebElement phone= driver.findElement(By.xpath("//input[@name='user[phone1]']"));
			phone.clear();phone.sendKeys("0789654321");
			 //save
			 driver.findElement(By.xpath("//button[contains(.,'Save')]")).click();
			 String alertmsg=driver.findElement(By.xpath("//p[@class='alert alert-success']")).getText();
			 System.out.println(alertmsg);
		   }
	
           @Test(priority=2)
           public void yourcompanyTest() throws InterruptedException {
        	   driver.findElement(By.xpath("//a[@href='https://aviva.4cplatform-staging.com/account/edit-company'][contains(.,'Your company')]")).click();
               WebElement cmyregno=driver.findElement(By.xpath("//input[@name='firm[company_registration_number]']"));
               cmyregno.clear();cmyregno.sendKeys("280274");
              WebElement Regcomname=driver.findElement(By.xpath("//input[@name='firm[name]']"));
              Regcomname.clear();Regcomname.sendKeys("Aviva");
              WebElement Fcaref=driver.findElement(By.xpath("//input[contains(@name,'firm[fca_ref]')]"));
              Fcaref.clear();Fcaref.sendKeys("565441");
              WebElement Avivaagycode = driver.findElement(By.xpath("//input[@name='firm[agent_intermediary_id]']"));
              Avivaagycode.clear();Avivaagycode.sendKeys("1-7657688");
              WebElement comyweb=driver.findElement(By.xpath("//input[contains(@id,'firm[website]')]"));
              comyweb.clear();comyweb.sendKeys("http://www.aviva.com");
              //registered company address
              new Select(driver.findElement(By.xpath("//select[@id='country']"))).selectByVisibleText("United Kingdom");
              //postcode
             WebElement postcode= driver.findElement(By.xpath("//input[@id='postcode']"));
             postcode.clear();postcode.sendKeys("EC3P 3DQ");
             driver.findElement(By.xpath("//button[@type='button'][contains(.,'Find address')]")).click();
             WebDriverWait wait = new WebDriverWait (driver, 30);
 			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(.,'Choose address from list:')]")));
 			Actions action = new Actions(driver);
 			WebElement addr = driver.findElement(By.xpath("//td[@class='fourc-hover-pointer clk']"));
 			action.moveToElement(addr).click().perform();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//button[@form='page-form'][contains(.,'Save')]")).click();
            String alertmsg=driver.findElement(By.xpath("//p[@class='alert alert-success']")).getText();
			 System.out.println(alertmsg);
           }
           @Test(priority=3)
           public void PolicyonboardingTest() {
        	   driver.findElement(By.xpath("//a[@href='https://aviva.4cplatform-staging.com/account/edit-onboarding'][contains(.,'Policy onboarding')]")).click();
        	   //use simulated onboarding
        	   WebElement simulateboarding =driver.findElement(By.xpath("//input[@id='simulate_onboarding']"));
        	   simulateboarding.click();
        	   simulateboarding.click();
        	   //save
        	   driver.findElement(By.xpath("//button[@form='page-form'][contains(.,'Save')]")).click();
           }
           @Test(priority=4)
           public void chgpwdTest() {
        	   driver.findElement(By.xpath("//a[@href='https://aviva.4cplatform-staging.com/account/change-password'][contains(.,'Change password')]")).click();
        	   driver.findElement(By.xpath("//input[@id='old-password']")).sendKeys("K*fZGF0X$YjL");
        	  WebElement newpwd=driver.findElement(By.xpath("//input[@id='password']"));
        	  newpwd.sendKeys("K*fZGF0X$YjL");
        	  driver.findElement(By.xpath("//input[@name='password_confirmation']")).sendKeys("K*fZGF0X$YjL");
        	  driver.findElement(By.xpath("//button[contains(.,'Change password')]")).click();
        	   
           }
           @AfterSuite
           public void tearDown(){
           	driver.quit();
           	log.info("****************************** Browser is closed *****************************************");  
	
	}
}
