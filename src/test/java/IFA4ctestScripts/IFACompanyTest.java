package IFA4ctestScripts;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class IFACompanyTest {
	static WebDriver driver;
	 static Logger log = Logger.getLogger(IFACompanyTest.class);
	 GenerateData genData;
	
	 @BeforeSuite
		public static void setup() throws InterruptedException{
		log.info("****************************** Starting test cases execution  *****************************************");
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Amruta.Yembuluru\\driver\\chromedriver_win32\\chromedriver.exe");
	    driver = new ChromeDriver();
	    // driver.manage().window().maximize();
	    driver.manage().deleteAllCookies();
	    driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    //Launch the 4CPlatform
	    driver.get("https://admin.4cplatform-staging.com/");
	 }
	    @BeforeTest
	    public static void ADlogintest() throws InterruptedException {
	    
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
	       //click on Super Administrator
	      driver.findElement(By.xpath("//button[@value='Super-administrator']")).click();
		  Thread.sleep(2000); 
		  //clicking on List of IFAs
		  driver.findElement(By.xpath("//small[contains(.,'View list of IFAs')]")).click();
		  //handling alert
		// Alert alert = driver.switchTo().alert();
       // alert.accept();
        //Clicking on Ifa
		  new Select(driver.findElement(By.name("index_length"))).selectByValue("100");
		  driver.findElement(By.xpath("//a[contains(.,'IFA 2 Ltd.')]")).click();
		 
         }
      
      @Test(priority=1)
      public static void IFADatabaseInfoTest() throws InterruptedException {
   	   //checking name is displayed.
   	   List<WebElement> DatabaseInformation = driver.findElements(By.id("collapseDatabaseInfo"));
   	   for(WebElement f: DatabaseInformation)
   	   {
   	   	System.out.println(f.getText());
   	   }
        WebElement	 name = driver.findElement(By.cssSelector("#collapseDatabaseInfo > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(2)"));
   	  if( name.isDisplayed()) {
 			System.out.println("IFA name is displayed");
 	  	    } else {
 	  	    System.out.println("IFA name is not displayed");
 	  	    }
   	   //checking Contact is displayed
   	  
   	  WebElement Contact = driver.findElement(By.cssSelector("#collapseDatabaseInfo > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(2)"));
   	 
   	  if( Contact.isDisplayed()) {
 			System.out.println("IFA contact is displayed");
 	  	    } else {
 	  	    System.out.println("IFA contact is not displayed");
 	  	    }
		  Thread.sleep(2000); 
		  //checking Email is displayed
		  
		 WebElement Email = driver.findElement(By.cssSelector("#collapseDatabaseInfo > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(3) > th:nth-child(1)"));
   	 String Emailtext = Email.getText();
   	 System.out.println(Emailtext);
  	  if( Email.isDisplayed()) {
			System.out.println("IFA Email is displayed");
	  	    } else {
	  	    System.out.println("IFA Email is not displayed");
	  	    }
		  Thread.sleep(2000); 
   	   
      }
      

      @Test(priority=2)
      public static void IFAActivitysstest() {
      driver.findElement(By.xpath("//a[contains(.,'Activity')]")).click();
      List<WebElement> Activitystream = driver.findElements(By.id("index"));
	   for(WebElement f: Activitystream)
	   {
	   	System.out.println(f.getText());
	   }
		  Utility.captureScreenShot("ActivityStream", driver);  
		  driver.navigate().back();
      }
      
      @Test(priority=3)
      public static void IFAEdittest() throws InterruptedException {
      driver.findElement(By.cssSelector("a.btn:nth-child(2)")).click();	   
      String title = driver.findElement(By.cssSelector(".panel-heading > h2:nth-child(1)")).getText();
      System.out.println(title);
      
    WebElement firmemail =  driver.findElement(By.xpath("//input[@id='firm[email]']"));
   		 firmemail.clear();
   		 firmemail.sendKeys("queries@IFA1.com");
      
      WebElement phone= driver.findElement(By.xpath("//input[@id='firm[phone]']"));
            phone.clear();
            phone.sendKeys("01285646464");
            
            //Starting NCD
            WebElement NCD = driver.findElement(By.id("firm[starting_ncd]"));
            NCD.clear();
            NCD.sendKeys("1");
            
            //save
            
            driver.findElement(By.xpath("//input[@value='Save']")).submit();
        WebElement successmsg =    driver.findElement(By.xpath("//div[@class='alert alert-success'][contains(.,'IFA updated successfully.')]"));
       if( successmsg.isDisplayed()) {
       	System.out.println("IFA updated successfully. is displayed");
	  	    } else {
	  	    System.out.println("IFA updated successfully.  is not displayed");
	  	    }
	      Thread.sleep(2000); 
       
        //Cancel
      driver.findElement(By.xpath("//a[contains(.,'Cancel')]")).click();
      
      } 
      @Test(priority=4)
      public void ResendActEmailtest() throws InterruptedException {
   	   driver.findElement(By.xpath("//button[@value='Resend Activation Email'][contains(.,'Resend Activation Email')]")).click();
   	   WebElement Actresent =    driver.findElement(By.xpath("//div[@role='alert'][contains(.,'Activation email resent')]"));
          if( Actresent.isDisplayed()) {
          	System.out.println("Activation email resent is displayed");
  	  	    } else {
  	  	    System.out.println("Activation email resent  is not displayed");
  	  	    }
  	      Thread.sleep(2000);  
  	      //waiting for the bug to be fixed
      }
      @Test(priority=5)
      public void IFAAddresstest() {
   	   driver.findElement(By.xpath("//a[contains(.,'Address')]")).click();
   	  WebElement adddis= driver.findElement(By.cssSelector("#collapseAddress > div"));
   	  if( adddis.isDisplayed()) {
            	System.out.println("Address is displayed");
    	  	    } else {
    	  	    System.out.println("Address  is not displayed");
    	  	    }
      }
   /***   @Test(priority=6)
      public void Agencycodestest() throws InterruptedException {
   	   driver.findElement(By.xpath("//span[@class='flex'][contains(.,'Agency Codes')]")).click();
   		  Utility.captureScreenShot("Agencycode", driver);   
   		  //Add Agency code 
   		 driver.findElement(By.xpath("//a[contains(.,'Add Agency Code')]")).click();
   		 //create Agency Code
   		  //provider
   		  Select provider = new Select(driver.findElement(By.xpath("//select[contains(@name,'product_id')]")));
   		  provider.selectByVisibleText("VitalityHealth");
   		  new Select(driver.findElement(By.id("style"))).selectByVisibleText("Switch Policies");
   		driver.findElement(By.id("code")).sendKeys("1-52626846");
   		  driver.findElement(By.id("note")).sendKeys("Creating a new agency code");
   		  //save
   		  driver.findElement(By.xpath("//button[@name='submitName'][contains(.,'Save')]")).click();
   		  driver.findElement(By.xpath("//span[@class='flex'][contains(.,'Agency Codes')]")).click();
   		  Thread.sleep(2000); 
   		  driver.findElement(By.cssSelector("#collapseCodes > table > tbody > tr.code-45 > td:nth-child(6) > a")).click();
   		  Thread.sleep(2000); 	
   		  driver.findElement(By.cssSelector("#app-layout > div.sweet-alert.showSweetAlert.visible > div.sa-button-container > div > button")).click();
   		  Thread.sleep(3000); 
   		  driver.findElement(By.cssSelector("#app-layout > div.sweet-alert.showSweetAlert.visible > div.sa-button-container > div > button")).click();
   		
      }***/


 @AfterSuite
public void tearDown(){
	driver.quit();
	log.info("****************************** Browser is closed *****************************************"); 
}

}

