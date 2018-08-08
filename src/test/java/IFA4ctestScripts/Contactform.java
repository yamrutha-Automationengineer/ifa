package IFA4ctestScripts;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class Contactform {

	 WebDriver driver;
	 Logger log = Logger.getLogger(Contactform.class);
	 GenerateData genData;
	 String title;
	 @BeforeMethod
		public void setup() throws InterruptedException{
		log.info("****************************** Starting test cases execution  *****************************************");
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Amruta.Yembuluru\\driver\\chromedriver_win32\\chromedriver.exe");
	    driver = new ChromeDriver();
	    // driver.manage().window().maximize();
	    driver.manage().deleteAllCookies();
	    driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    //Launch the 4CPlatform
	    driver.get("https://ifa1.4cplatform-staging.com/");
	     }
	 //Test 
	 @Test(priority=2)
		public void ContactScrollTest() throws InterruptedException{
	    // scroll to Contacts page
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("window.scrollBy(0,9000)");
	    Thread.sleep(2000);
	    String title = driver.findElement(By.xpath("//h1[contains(.,'Contact the team today.')]")).getText();
		 System.out.println(title);
		 Assert.assertEquals(title, "Contact the team today." , "text is not matched");	
		 
	    }
	 @Test(priority=3)
		public void ContactTest() throws InterruptedException{
	    //to open contact page--click on contact
	    Thread.sleep(3000);
	    driver.findElement(By.xpath("//a[contains(.,'CONTACT')]")).click();
	 
	    //contact the team today text is displayed
	    Thread.sleep(3000);
	    String title = driver.findElement(By.xpath("//h1[contains(.,'Contact the team today.')]")).getText();
	    System.out.println(title);
	    Assert.assertEquals(title, "Contact the team today." , "text is not matched");	
	 
	    }
	 @Test(priority=4)
	    public void Screenshot() throws InterruptedException {
		   driver.findElement(By.xpath("//a[contains(.,'CONTACT')]")).click();
		   Thread.sleep(3000);
		  Utility.captureScreenShot("ContactPage", driver);  
	    }

	    
	 @Test(priority=5)
	    public void GetintouchTest() throws InterruptedException {
		   driver.findElement(By.xpath("//a[contains(.,'FAQ')]")).click();
		   Thread.sleep(3000);
		driver.findElement(By.xpath("//a[contains(.,'Get in touch today')]")).click();
		 Thread.sleep(5000);
		 WebElement Gitt = driver.findElement(By.xpath("//h1[contains(.,'Contact the team today.')]"));
		     if( Gitt.isDisplayed()) {
			System.out.println("Contact the team today.is displayed");
	  	    } else {
	  	    System.out.println("Contact the team today.is not displayed");
	  	    }
		 }
			 
	   
	 
	 @Test(priority=1)
	    public void SubmitcontactformTest() throws InterruptedException {
		 genData=new GenerateData();
		   driver.findElement(By.xpath("//a[contains(.,'CONTACT')]")).click();
		    Thread.sleep(3000);
		    //name
		    driver.findElement(By.xpath("//input[@id='name']")).sendKeys(genData.generateRandomString(8));
		    Thread.sleep(3000);
		    //Email
		    driver.findElement(By.xpath("//input[@id='email']")).sendKeys(genData.generateEmail(13));
		    Thread.sleep(3000);
		    //Phone
		    driver.findElement(By.xpath("//input[@id='phone']")).sendKeys("078"+genData.generateRandomNumber(7));
		    Thread.sleep(3000);
		    //Comments:
		    driver.findElement(By.xpath("//textarea[@id='message']")).sendKeys(genData.generateRandomString(200));
		    Thread.sleep(3000);
		    //Submit Enquiry
		    driver.findElement(By.xpath("//button[@id='sendemailbtn']")).click();
		    Thread.sleep(3000);
		    //Thank you msg
		  WebElement Thankyoutext =  driver.findElement(By.xpath("//h2[contains(.,'Thank you.')]"));
		    if (Thankyoutext.isDisplayed()) {
		  	    System.out.println("Thank you  is displayed");
		  	} else {
		  	    System.out.println("Thank you is not displayed");
		  	}
	       }
	 @Test(priority=6)
	 public void BacktotopbtnTest() throws InterruptedException {
		 driver.findElement(By.xpath("//a[contains(.,'CONTACT')]")).click();
		    Thread.sleep(3000);
         driver.findElement(By.xpath("//a[@href='#top'][contains(.,'Back to top')]"));
         Thread.sleep(5000);
	 WebElement top = driver.findElement(By.xpath("//a[contains(.,'Discover the 4CPlatform')]"));
	     if( top.isDisplayed()) {
		System.out.println("Simple PMI for brokers & IFAs.is displayed");
 	    } else {
 	    System.out.println("Simple PMI for brokers & IFAs.is not displayed");
 	    }
	 }
		 
	 
	 @AfterMethod
		public void tearDown(){
			driver.quit();
			log.info("****************************** Browser is closed *****************************************"); 
	    }
}
