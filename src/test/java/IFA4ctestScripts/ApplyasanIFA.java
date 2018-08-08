package IFA4ctestScripts;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
public class ApplyasanIFA {

	
		String name;
		 GenerateData genData;
		
		
		 WebDriver driver;
		 Logger log = Logger.getLogger(ApplyasanIFA.class);
	
		 @BeforeMethod
		 
			public void setup() throws InterruptedException{
		
			log.info("****************************** Starting test cases execution  *****************************************");
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\Amruta.Yembuluru\\driver\\chromedriver_win32\\chromedriver.exe");
		    driver = new ChromeDriver();
		    // driver.manage().window().maximize();
		    driver.manage().deleteAllCookies();
		    driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		 //Logging into the 4CPlatform
		 driver.get("https://www.4cplatform-staging.com");
		 }
		 
		 @Test
			public void OnboardingTest() throws InterruptedException{
			 genData=new GenerateData();
	      //to apply as an IFA      
		  driver.findElement(By.linkText("APPLY")).click();
		  
		  org.openqa.selenium.WebElement btn1 = driver.findElement(By.xpath( "//a[contains(@class,'btn btn-success pull-right')]"));	
		  btn1.click();
		   					
		// entering the details
			
		WebElement crn = driver.findElement(By.id("firm[company_registration_number]"));
		crn.sendKeys(genData.generateRandomNumber(2)+genData.generateRandomNumber(6));
		
		 driver.findElement(By.id("firm[name]")).sendKeys("IFAtest"+genData.generateRandomNumber(3)+"ltd");
		 driver.findElement(By.id("firm[fca_ref]")).sendKeys("4786"+genData.generateRandomNumber(3)); 
		 driver.findElement(By.id("firm[agent_intermediary_id]")).sendKeys("1-52626"+genData.generateRandomNumber(3));
		 driver.findElement(By.id("firm[website]")).sendKeys("https://"+genData.generateUrl(10)+".com"); 
		 org.openqa.selenium.WebElement text = driver.findElement(By.xpath("//legend[contains(.,'Primary contact')]"));	
		  text.getText();
		  System.out.println("text : "+text);
		 WebElement fnname = driver.findElement(By.id("user[firstname]"));
		 fnname.sendKeys(genData.generateRandomString(5));
		
		  name = fnname.getText();
		  System.out.println(name);
		  driver.findElement(By.id("user[lastname]")).sendKeys("testln");
		  driver.findElement(By.id("user[phone1]")).sendKeys(genData.generateRandomNumber(10));
		  driver.findElement(By.id("user[email]")).sendKeys(""+genData.generateRandomString(7)+"@test.org");
		  Thread.sleep(3000);
		 
		  Select country = new Select(driver.findElement(By.id("country")));
		   country.selectByVisibleText("United Kingdom");
		    Thread.sleep(2000);
		    driver.findElement(By.id("postcode")).sendKeys("Bs1 6dp");
		 
			  
			/*** Select chaddressl = new Select(driver.findElement(By.xpath( "//td[contains(.,'Choose address from list:')]")));
			 Thread.sleep(2000);
			 System.out.println("address : "+chaddressl); 
			   chaddressl.getAllSelectedOptions();***/
		    
		    driver.findElement(By.id("address[line1]")).sendKeys("1 Apollo Apartments");
		    
		    driver.findElement(By.id("address[line2]")).sendKeys("30-38 Baldwin Street"); 
		    driver.findElement(By.id("address[city]")).sendKeys("Bristol");
		    driver.findElement(By.id("address[county]")).sendKeys("Somerset");
		    org.openqa.selenium.WebElement ctc = driver.findElement(By.xpath("//legend[contains(.,'Consent to communications')]"));	
			  ctc.getText();
			  System.out.println("ctc : "+ctc);
			  org.openqa.selenium.WebElement ctcbtn = driver.findElement(By.xpath("//span[@class='btn btn-default btn-db-clk']"));	
			  ctcbtn.click();
			 org.openqa.selenium.WebElement next = driver.findElement(By.xpath("//button[contains(@class,'btn btn-success pull-right')]"));	
			  next.click();
			  Utility.captureScreenShot("Applicationdetails", driver);  			
					Thread.sleep(2000);
			  String j = driver.getTitle();
			  System.out.println("Your page title Is : "+j);
			  
			 Assert.assertEquals("4C Platform",j);
			 Assert.assertEquals("4C Platform",driver.getTitle());
			 //to get the page text
			  WebElement bodyText = driver.findElement(By.tagName("body"));

			  String AllTextOnPage = bodyText.getText();

			  System.out.println(AllTextOnPage);
			  //to display successful msg text
			  if(driver.getPageSource().contains("Thank you for your application"))
			        System.out.println("Text is present in the webpage");
			    else
			        System.err.println("Text is not present in the webpage");

		
			  String cmname = driver.findElement(By.cssSelector(".table > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(2)")).getText();
	
		  System.out.println("company:"+cmname);
		  
		  String Email = driver.findElement(By.cssSelector(".table > tbody:nth-child(1) > tr:nth-child(4) > td:nth-child(2)")).getText();
		  System.out.println("IFA emailid:"+Email);
		  
		  driver.get("https://admin.4cplatform-staging.com");  
		  driver.findElement(By.xpath("//a[@href='/login']")).click();
			 //"Sadmin@usaycompare.com"
			 driver.findElement(By.xpath("//input[@name='email']")).sendKeys("Sadmin@usaycompare.com");
			 driver.findElement(By.xpath("//input[@name='password']")).sendKeys("CXoF2sL5w2");
	driver.findElement(By.xpath("//button[@form='loginForm']")).click();
	driver.findElement(By.xpath("//button[@value='Super-administrator']")).click();
	driver.findElement(By.xpath("//span[contains(.,'IFA list')]")).click();
	new Select(driver.findElement(By.name("index_length"))).selectByValue("100");
	Thread.sleep(2000);
	//to click on checklist
	String before_xpath="//*[@id=\"index\"]/tbody/tr[";
	String after_xpath="]/td[1]/a";
	int n=99;
	for(int i=1;1<=n;i++) {
		String companyname=driver.findElement(By.xpath(before_xpath +i+after_xpath )).getText();
		if(companyname.contains(cmname)) {
			//*[@id="index"]/tbody/tr[36]/td[6]/span[2]/a
			driver.findElement(By.xpath("//*[@id=\"index\"]/tbody/tr["+i+"]/td[6]/span[2]/a")).click();
			break;
		}
	} 
		  
	Select onapp =new Select(driver.findElement(By.cssSelector("div.checklist-item:nth-child(2) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(2) > select:nth-child(1)")));
	onapp.selectByVisibleText("Complete");
	Thread.sleep(2000);
	//2a. Companies House check
	Select Housecheck =new Select(driver.findElement(By.cssSelector("div.checklist-item:nth-child(3) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(2) > select:nth-child(1)")));
	Housecheck.selectByVisibleText("Complete");
	Thread.sleep(2000);
	//2b. FCA registration number check

	Select FCAregno =new Select(driver.findElement(By.cssSelector("div.checklist-item:nth-child(4) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(2) > select:nth-child(1)")));
	FCAregno.selectByVisibleText("Complete");
	Thread.sleep(2000);
	//3a. Review check

	Select Reviewcheck =new Select(driver.findElement(By.cssSelector("div.checklist-item:nth-child(5) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(2) > select:nth-child(1)")));
	Reviewcheck.selectByVisibleText("Complete");
	Thread.sleep(2000);
	//3b. General internet checks
	Select Internetcheck =new Select(driver.findElement(By.cssSelector("div.checklist-item:nth-child(6) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(2) > select:nth-child(1)")));
	Internetcheck.selectByVisibleText("Complete");
	Thread.sleep(2000);
	//3c. Credit checks
	Select Creditcheck =new Select(driver.findElement(By.cssSelector("div.checklist-item:nth-child(7) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(2) > select:nth-child(1)")));
	Creditcheck.selectByVisibleText("Complete");
	Thread.sleep(2000);
	//3d. Final checks
	Select Finalcheck =new Select(driver.findElement(By.cssSelector("div.checklist-item:nth-child(8) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(2) > select:nth-child(1)")));
	Finalcheck.selectByVisibleText("Complete");
	Thread.sleep(2000);

	// Confirm no further action required
	Select Nofurtheraction =new Select(driver.findElement(By.cssSelector("div.checklist-item:nth-child(9) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(2) > select:nth-child(1)")));
	Nofurtheraction.selectByVisibleText("Complete");
	Thread.sleep(2000);

	//Return to IFA List
	driver.findElement(By.xpath("//a[contains(.,'Return to IFA list')]")).click();
	Thread.sleep(2000);
	new Select(driver.findElement(By.name("index_length"))).selectByValue("100");
	Thread.sleep(2000);
	Utility.scrollPageDown(driver);
   Thread.sleep(2000);
	//to activate
	int n1=99;
	for(int i1=1;1<=n1;i1++) {
	String	companyname1=driver.findElement(By.xpath(before_xpath +i1+after_xpath )).getText();

		if(companyname1.contains(cmname)) {
			//*[@id="index"]/tbody/tr[36]/td[7]/a
			//*[@id="index"]/tbody/tr[44]/td[7]/a
		 driver.findElement(By.xpath("//*[@id=\"index\"]/tbody/tr["+i1+"]/td[7]/a")).click();
			
			break;
		}
	}


		//to Enable
	new Select(driver.findElement(By.name("index_length"))).selectByValue("100");
	Thread.sleep(2000);

	Utility.scrollPageDown(driver);
   Thread.sleep(2000);
	int n11=99;
	for(int i11=1;1<=n11;i11++) {
	String	companyname2=driver.findElement(By.xpath(before_xpath +i11+after_xpath )).getText();
	
	
		if(companyname2.contains(cmname)) {
			//*[@id="index"]/tbody/tr[46]/td[8]/a
			//*[@id="index"]/tbody/tr[38]/td[8]/a
			//*[@id="index"]/tbody/tr[81]/td[8]/a
			Thread.sleep(500);
			
		   
		 driver.findElement(By.xpath("//*[@id=\"index\"]/tbody/tr["+i11+"]/td[8]/a")).click();
			
			break;
		}
	}
	Thread.sleep(2000);
	//mailtrap 
	
	 driver.get("https://mailtrap.io/");
	 //to log in
	 driver.findElement(By.linkText("Log in")).click();
	 //email id
	 driver.findElement(By.id("user_email")).sendKeys("support@4cplatform.com");
	 //password
	 driver.findElement(By.id("user_password")).sendKeys("#xk\\/dqu3zzn5Gn+");
	 //login
	 
	 driver.findElement(By.cssSelector("#new_user > div.submit.separated-block.from-bottom > input")).click();
		Thread.sleep(2000);
	 driver.findElement(By.cssSelector("#main > div > div.companies_list > div > div > div.table > table > tbody > tr > td:nth-child(1) > div.initial > strong > a > span")).click();
	 
	 driver.findElement(By.cssSelector("#main > div > div.master_region.sidebar > div > ul > li.email.new-email")).click();
	 int size = driver.findElements(By.tagName("iframe")).size();
	 System.out.println(size);
	 driver.switchTo().frame(0);
	 WebElement click = driver.findElement(By.linkText("Activate account"));
		click.click();
		//click.sendKeys(Keys.CONTROL , Keys.ARROW_DOWN);
		 driver.getWindowHandle();
		//to click on checklist
		  Thread.sleep(2000);
		String url = driver.getCurrentUrl();
		System.out.println(url);
		String handlewindow = (String) driver.getWindowHandles().toArray()[1];
		driver.switchTo().window(handlewindow);  
		String ispresent = driver.findElement(By.xpath("//h2[contains(.,'Activate your account')]")).getText();
		System.out.println(ispresent);
		Assert.assertEquals("Activate your account",ispresent);
		

	 //Thankyou msg
	String Thankyoutext =  driver.findElement(By.xpath("//strong[contains(.,'Thank you for your interest in the 4CPlatform.')]")).getText();
	System.out.println(Thankyoutext);
	Assert.assertEquals("Thank you for your interest in the 4CPlatform.",Thankyoutext);
	

	
		 driver.findElement(By.id("email")).sendKeys(Email);
	
		 

//to click on Next
driver.findElement(By.xpath("//button[@form='registerForm'][contains(.,'Next')]")).click();

//Your details 
 Utility.captureScreenShot("Your Details", driver);  
//Your company details edit button
 driver.findElement(By.xpath("//button[@data-target='#modalYourCompany']")).click();
 Thread.sleep(2000);
 //checking the form is visible
 Boolean yourcompanyform =driver.findElement(By.id("formYourCompany")).isDisplayed();
 System.out.println(yourcompanyform);

//close button
 driver.findElement(By.cssSelector("#modalYourCompany > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > button:nth-child(1)")).click();
 Thread.sleep(2000);
 //Next button
 driver.findElement(By.xpath("//span[@class='prompt'][contains(.,'Next')]")).click();
 
 //Choose a password
 
 WebElement choosepassword = driver.findElement(By.xpath("//input[@name='password']"));
 choosepassword.sendKeys("S£cret1F@");

 //confirm password
  driver.findElement(By.xpath("//input[contains(@name,'password_confirmation')]")).sendKeys("S£cret1F@");
 //next button
  
  driver.findElement(By.cssSelector(".btn")).click();
  
  //Terms & conditions
  //alert info is displayed
Boolean alert = driver.findElement(By.cssSelector(".alert")).isDisplayed();
Assert.assertTrue(alert);



//clicking the Terms of Service

driver.findElement(By.xpath("//a[@data-target='#modalTermsAndConditions']")).click();
 Thread.sleep(2000);
 driver.findElement(By.xpath("//button[@id='button-agree-terms-and-conditions']")).click(); 
 Thread.sleep(2000);
//clicking on the Privacy Policy link
 driver.findElement(By.xpath("//a[@data-target='#modalPrivacyPolicy']")).click();
 Thread.sleep(2000);
 driver.findElement(By.xpath("//button[@id='button-agree-privacy-policy']")).click();
 Thread.sleep(2000);
 //next button
 driver.findElement(By.xpath("//button[@form='registerForm']")).click();
 //Lead purchase preferences
 //Do you currently buy sales leads?
 driver.findElement(By.cssSelector(".col-md-6 > span:nth-child(1)")).click();
 //Products sold
//ritical illness cover 
 driver.findElement(By.cssSelector(".table > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(1) > div:nth-child(1) > label:nth-child(1)")).click();
 Thread.sleep(2000);
 driver.findElement(By.cssSelector(".table > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(3) > div:nth-child(1) > div:nth-child(1) > span:nth-child(1)")).click();
 //Medical Insurance
 driver.findElement(By.cssSelector(".table > tbody:nth-child(1) > tr:nth-child(8) > td:nth-child(1) > div:nth-child(1) > label:nth-child(1)")).click();
 Thread.sleep(2000);
 driver.findElement(By.cssSelector(".table > tbody:nth-child(1) > tr:nth-child(8) > td:nth-child(3) > div:nth-child(1) > div:nth-child(1) > span:nth-child(1)")).click();
//next button
 driver.findElement(By.xpath("//button[@form='registerForm']")).click();
	 //Set up branding

 driver.findElement(By.xpath("//button[@form='registerForm']")).click();

 Utility.captureScreenShot("Manage team", driver); 
 //Manage Team
 String Name = driver.findElement(By.cssSelector(".table > tbody:nth-child(2) > tr:nth-child(1) > td:nth-child(1)")).getText();

 System.out.println("Name:"+Name);

 String Email1 = driver.findElement(By.cssSelector(".table > tbody:nth-child(2) > tr:nth-child(1) > td:nth-child(2) > a:nth-child(1)")).getText();
 System.out.println("Email:"+Email1);

 driver.findElement(By.xpath("//span[@class='prompt'][contains(.,'Next')]")).click();

 //Policy onboarding


 Boolean alert1 =driver.findElement(By.xpath("//div[contains(@class,'alert alert-info')]")).isDisplayed();
 System.out.println(alert1);

 //checking the Use simulated onboard button is selected.

 WebElement simonboardbtn =driver.findElement(By.cssSelector("#registerForm > div > div.panel-body > div.form-group > div > label"));
 if (simonboardbtn.isSelected()) {
     System.out.println("Use simulated onboarding check box is already checked");
 } else {
     System.out.println("Use simulated onboarding check box is not checked");
 }

 //next button
 driver.findElement(By.xpath("//button[@form='registerForm'][contains(.,'Next')]")).click();
 //Summary
   WebElement summarytext = driver.findElement(By.xpath("//p[contains(.,'Thank you for completing the 4CPlatform account activation process.')]"));
   if (summarytext.isDisplayed()) {
 	    System.out.println("Thank you for completing the 4CPlatform account activation process. is displayed");
 	} else {
 	    System.out.println("Thank you for completing the 4CPlatform account activation process. is not displayed");
 	}
 //Continue to dashboard
   driver.findElement(By.xpath("//span[@class='prompt'][contains(.,'Continue to dashboard')]")).click();
   
 //choose role to begin
     driver.findElement(By.xpath("//button[@name='submit'][contains(.,'IFA administrator')]")).click();
 	 
 	 
 	//checking the company is displayed.
    System.out.println(driver.findElement(By.xpath("//span[contains(@class,'label label-white')]")).getText());
   
     
 
		 }
		 @AfterMethod
			public void tearDown(){
				driver.quit();
				log.info("****************************** Browser is closed *****************************************"); 
		    }
}

