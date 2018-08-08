package IFA4ctestScripts;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
//Test to makesure theclient journey is aborted on not accepting the Consent to use personal information.
public class IFAJourney1 {
	 WebDriver driver;
	 Logger log = Logger.getLogger(Loginifa1_4cplatform.class);
	 GenerateData genData;
	String name;
	 @BeforeSuite
		public void setup() throws InterruptedException{
		log.info("****************************** Starting test cases execution  *****************************************");
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Amruta.Yembuluru\\driver\\chromedriver_win32\\chromedriver.exe");
	    driver = new ChromeDriver();
		genData=new GenerateData();
	    // driver.manage().window().maximize();
	    driver.manage().deleteAllCookies();
	    driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    //Launch the 4CPlatform
	    Thread.sleep(2000);
		 log.info("entering application URL");
		 driver.get("https://ifa1.4cplatform-staging.com");}
		 @BeforeTest
			public void LoginTest() throws InterruptedException{
		  driver.findElement(By.linkText("LOGIN")).click();
		  Thread.sleep(2000);
		  WebElement email = driver.findElement(By.xpath( "//input[@id='email']"));	
		  email.sendKeys("ifa1_user1@usaycompare.com");
		  WebElement pwd = driver.findElement(By.xpath( "//input[@id='password']"));	
		  pwd.sendKeys("secret");
		  driver.findElement(By.xpath("//button[contains(.,'Sign in  ')]")).click(); 
		  driver.findElement(By.xpath("//button[@name='submit'][contains(.,'IFA administrator')]")).click();
		 		 Thread.sleep(2000); 
		 //clients-clients in this account
		 	driver.findElement(By.xpath("//span[@class='fourc-tile3-header prompt'][contains(.,'Clients')]")).click();
	
		 }
		 @Test(priority=1)
		 public void AddNewClient() throws Exception {
			 //add New client
	
			driver.findElement(By.xpath("//a[contains(.,'New client')]")).click(); 
			//Email address
			
			 Thread.sleep(5000); 
			driver.findElement(By.id("user[email]")).sendKeys(genData.generateEmail(20));	
					
			//Firstname
			driver.findElement(By.xpath("//input[@id='user[firstname]']")).sendKeys(genData.generateRandomString(5));
			//Lastname
			driver.findElement(By.xpath("//input[@id='user[lastname]']")).sendKeys(genData.generateRandomString(9));
			//Date of birth
			new Select(driver.findElement(By.xpath("//select[@name='user[date_of_birth_dd]']"))).selectByValue("21");
			//month
			new Select(driver.findElement(By.xpath("//select[@name='user[date_of_birth_mm]']"))).selectByVisibleText("Jan");
			 Thread.sleep(1000); 
			//year
			new Select(driver.findElement(By.xpath("//select[@name='user[date_of_birth_yyyy]']"))).selectByValue("1975");
			//Gender at birth
			driver.findElement(By.xpath("//span[@href='#'][contains(.,'Male')]")).click();
			//Title
			new Select(driver.findElement(By.xpath("//select[@id='user_title']"))).selectByVisibleText("Prof");
			//Home phone
			driver.findElement(By.xpath("//input[@id='user[phone1]']")).sendKeys("078"+genData.generateRandomNumber(7));
			//Address
			new Select(driver.findElement(By.xpath("//select[@id='address[country]']"))).selectByVisibleText("United Kingdom");
			driver.findElement(By.xpath("//input[@id='address[postcode]']")).sendKeys("bs1 6dp");
			driver.findElement(By.xpath("//button[@id='find-address']")).click();
			WebDriverWait wait = new WebDriverWait (driver, 30);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(.,'Choose address from list:')]")));
			Actions action = new Actions(driver);
			WebElement addr = driver.findElement(By.xpath("(//td[@class='fourc-hover-pointer clk'])"));
			
            action.moveToElement(addr).click().perform();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//input[contains(@name,'address[line1]')]")).click();
            
            
            driver.findElement(By.xpath("//button[@name='submitName'][contains(.,'Save')]")).click();
           
		 }
		 @Test(priority=2)
		  public void NOtoConsentTest() throws InterruptedException {
			 	//Selecting the Start Journey from Clients Database.
			 Thread.sleep(2000);
			 WebElement sort = driver.findElement(By.xpath("//th[@class='sorting_asc'][contains(.,'Name')]"));
				sort.click();
				
				 Thread.sleep(3000);
				 driver.findElement(By.xpath("(//a[contains(.,'Start journey')])")).click();
				  driver.findElement(By.cssSelector(".journey-header > h3:nth-child(2)")).getText();
				 System.out.println(name);
			
			 System.out.println( driver.findElement(By.xpath("//h4[contains(.,'Consent to use personal information?')]")).getText());
			 Thread.sleep(2000);
			 //checking client consent received is enabled as ''No'
			if( driver.findElement(By.xpath("//span[@href='#'][contains(.,'No')]")).isEnabled()) {
				System.out.println("Client consent not accepted button is enabled");}
				
				driver.findElement(By.xpath("//button[@name='submitName'][contains(.,'Save and continue')]")).click();
				//Abort
				System.out.println(driver.findElement(By.xpath("//h4[contains(.,'Abort')]")).getText());
				
				System.out.println(driver.findElement(By.xpath("//h4[contains(.,'Abort')]")).getText());
				
				driver.findElement(By.xpath("//button[@name='submitName'][contains(.,'Abort')]")).click();
		 }
		      @Test(priority=3) 
		      public void NonresidentAbortTest() throws InterruptedException {
		    	  Thread.sleep(2000);
					WebElement sort = driver.findElement(By.xpath("//th[@class='sorting_asc'][contains(.,'Name')]"));
					sort.click();
						
				    Thread.sleep(3000);
					driver.findElement(By.xpath("(//a[contains(.,'Start journey')])")).click();
					driver.findElement(By.cssSelector(".journey-header > h3:nth-child(2)")).getText();
					 System.out.println(name);
				
				 System.out.println( driver.findElement(By.xpath("//h4[contains(.,'Consent to use personal information?')]")).getText());
				 Thread.sleep(2000);
				 //Client consent received-YES
				driver.findElement(By.xpath("//span[@href='#'][contains(.,'Yes')]")).click();
				//Save and Continue
				driver.findElement(By.xpath("//button[@name='submitName'][contains(.,'Save and continue')]")).click();
			 Thread.sleep(3000);
				Utility.scrollPageDown(driver);
			 //Permanent UK resident?--No
			    driver.findElement(By.xpath("//span[contains(@data-fieldname,'user[uk_resident]')][2]")).click();
			    //PMI required for visa?--No
			    driver.findElement(By.xpath("//span[contains(@data-fieldname,'user[require_for_visa]')][2]")).click();
			    //Used tobacco products within past 12 months?---No
			    driver.findElement(By.xpath("//span[contains(@data-fieldname,'user[smokes]')][2]")).click();
			    //Save and Continue
			  driver.findElement(By.xpath("//button[@name='submitName'][contains(.,'Save and continue')]")).click();
			
			  System.out.println(driver.findElement(By.xpath("//h4[contains(.,'Cannot proceed for non-UK resident')]")).getText());
			  driver.findElement(By.xpath("//button[@class=' btn btn-success btn-lg pull-right btn-block'][contains(.,'Cancel')]")).click();
			}
		   
		      @Test(priority=4)
		      public void CurrentPolicyYesTest() throws InterruptedException {
		    	  Thread.sleep(2000);
					WebElement sort = driver.findElement(By.xpath("//th[@class='sorting_asc'][contains(.,'Name')]"));
					sort.click();
						
				    Thread.sleep(3000);
					driver.findElement(By.xpath("(//a[contains(.,'Start journey')])")).click();
					driver.findElement(By.cssSelector(".journey-header > h3:nth-child(2)")).getText();
					 System.out.println(name);
				
				 System.out.println( driver.findElement(By.xpath("//h4[contains(.,'Consent to use personal information?')]")).getText());
				 Thread.sleep(2000);
				 //Client consent received-YES
				driver.findElement(By.xpath("//span[@href='#'][contains(.,'Yes')]")).click();
				//Save and Continue
				driver.findElement(By.xpath("//button[@name='submitName'][contains(.,'Save and continue')]")).click();
			 Thread.sleep(3000);
				Utility.scrollPageDown(driver);
			 //Permanent UK resident?--No
			    driver.findElement(By.xpath("//span[contains(@data-fieldname,'user[uk_resident]')][1]")).click();
			    //PMI required for visa?--No
			    driver.findElement(By.xpath("//span[contains(@data-fieldname,'user[require_for_visa]')][2]")).click();
			    //Used tobacco products within past 12 months?---No
			    driver.findElement(By.xpath("//span[contains(@data-fieldname,'user[smokes]')][2]")).click();
			    //Save and Continue
			  driver.findElement(By.xpath("//button[@name='submitName'][contains(.,'Save and continue')]")).click();
			//h4[contains(.,'Additional members to be covered')]
		      }
	 }
	
