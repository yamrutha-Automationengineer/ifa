package IFA4ctestScripts;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
public class ifaquickquote  {
  static WebDriver driver;
	public static void main(String[] args) throws IOException, InterruptedException {
		GenerateData genData;
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\amruta_yembuluru\\driver\\chromedriver_win32\\chromedriver.exe");
		//to run on Mozillafirefox browser
		//System.setProperty("webdriver.chrome.driver", "C:\\Users\\amruta_yembuluru\\driver\\geckodriver-v0.20.1-win64\\geckodriver.exe");
		  driver = new ChromeDriver();
			// driver.manage().window().maximize();
		 driver.manage().deleteAllCookies(); driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		 driver.get("https://ifa1.4cplatform-staging.com");
		 genData=new GenerateData();
		
        
			
		 //gettitle
		 String i = driver.getTitle();
		  System.out.println("Your page title Is : "+i);

			 Assert.assertEquals("4C Platform",i);
			 Assert.assertEquals("4C Platform",driver.getTitle());

			
	
		  driver.findElement(By.linkText("LOGIN")).click();
		  
		  WebElement email = driver.findElement(By.xpath( "//input[@id='email']"));	
		  email.sendKeys("ifa1_user1@usaycompare.com");
		  WebElement pwd = driver.findElement(By.xpath( "//input[@id='password']"));	
		  pwd.sendKeys("secret");
		  driver.findElement(By.xpath("//button[contains(.,'Sign in  ')]")).click(); 
		  String j = driver.getTitle();
		  System.out.println("Your page title Is : "+j);	
			 Assert.assertEquals("4C Platform",j);
			 Assert.assertEquals("4C Platform",driver.getTitle()); 
			String  Url = driver.getCurrentUrl();
			 System.out.println("Your page Url Is : "+Url);
		
			 driver.findElement(By.xpath("//button[contains(@value,'IFA')]")).click(); 
			 String k = driver.getTitle();
			  System.out.println("Your page title Is : "+k);	
			Assert.assertEquals("4C Platform",driver.getTitle());
			 System.out.println("Valid page Title");
			// WebElement bodyText = driver.findElement(By.tagName("body"));

			  //String AllTextOnPage = bodyText.getText();

			 // System.out.println(AllTextOnPage);
			 driver.findElement(By.xpath("//small[contains(.,'Create a quick quote based on the date of birth and postcode')]")).click();  
			 String  Urlqq = driver.getCurrentUrl();
			 System.out.println("Your page Urlqq Is : "+Urlqq);	
			
			 
			Select p1dob = new Select(driver.findElement(By.xpath("//select[@id='date_of_birth_dd']")));	
			p1dob.selectByValue("15");
			
			Select p1mm = new Select(driver.findElement(By.xpath("//select[@name='date_of_birth_mm']")));	
			p1mm.selectByVisibleText("Aug");
			Select p1yy = new Select(driver.findElement(By.xpath("//select[@name='date_of_birth_yyyy']")));	
			p1yy.selectByValue("1975");
			WebElement pcode = driver.findElement(By.xpath( "//input[@placeholder='Enter a postcode']"));	
			  pcode.sendKeys("bs1 6dp");
			  driver.findElement(By.xpath("//button[@data-target='#formQuestionDisclosure']")).submit();  
			
			
			driver.findElement(By.xpath("//button[@class=' btn btn-primary btn-block']")).click(); 
			//blocked by the issue raised Fcpi-552//
			
			Select p2dob = new Select(driver.findElement(By.xpath("//select[@id='date_of_birth_dd']")));	
			p2dob.selectByValue("17");
			Thread.sleep(2000);
			Select p2mm = new Select(driver.findElement(By.xpath("//select[@name='date_of_birth_mm']")));	
			p2mm.selectByVisibleText("Sep");
			Select p2yy = new Select(driver.findElement(By.xpath("//select[@name='date_of_birth_yyyy']")));	
			p2yy.selectByValue("1985");
			 Thread.sleep(3000);
			 /*** List<WebElement> autoPopulatedList=driver.findElements(By.cssSelector("tr>td>span"));  
			  for(WebElement ele:autoPopulatedList)  
			  {  
			     System.out.println(ele.getText());  
			  } ***/ 
			 
			 Utility.captureScreenShot("Quote", driver);
		//pending due to screenshot	
			  //to check essential
			 
			  
		//this is for base table
			//  WebElement baseTable = driver.findElement(By.id("quickquote-table"));
			 
		         driver.findElement(By.xpath("//button[@data-target='#quoteModal']")).click(); 
		         Thread.sleep(3000);
		         //to check the email quotation
		         WebElement emailquot = driver.findElement(By.xpath("//h4[contains(@id,'quoteModalLabel')]"));
		         String emailquotd = emailquot.getText();
				 boolean emailquotv = emailquot.isDisplayed();
				 System.out.println(emailquotd);
		         System.out.println(emailquotv);
		         //to check the email quotation below text
		         WebElement emailqotext = driver.findElement(By.xpath("//*[@id=\"quoteModal\"]/div/div/div[2]/p/strong"));
		         String emailqotextd = emailqotext.getText();
				 boolean emailqotextv = emailqotext.isDisplayed();
				 System.out.println(emailqotextd);
		         System.out.println(emailqotextv);
		         //to enter the Name-Firstname & lastname.
		        WebElement ftname = driver.findElement(By.xpath("//input[@placeholder='First name']"));
		        ftname.sendKeys("louis");
		        
		         WebElement ltname = driver.findElement(By.xpath("//input[@placeholder='Last name']"));
		         ltname.sendKeys("martin");
			      
			      Thread.sleep(3000);
		         // to enter Email
		         driver.findElement(By.xpath("//input[@placeholder='Email']")).sendKeys(genData.generateEmail(20));
		         // to enter Phone
		         driver.findElement(By.xpath("//input[@placeholder='Phone number']")).sendKeys(genData.generateRandomNumber(10));
                 //to print the message
		         
		        WebElement message = driver.findElement(By.xpath("//textarea[@placeholder='Enter message']"));
		        message.clear();
		        message.sendKeys(genData.generateRandomString(150));
		        
		        //to check the Close button 
		        
		        
		        WebElement Close = driver.findElement(By.xpath("//button[@class='btn btn-secondary']"));
		       Boolean closev =  Close.isDisplayed(); 
		        System.out.println("The Close button on Email Quotation is displayed:"+closev);
		        
		        // to Send the emailquotation
		        
		       driver.findElement(By.xpath("//button[@id='sendquotation']")).click();
		       Thread.sleep(3000);
		       // to get leads
		       driver.findElement(By.xpath("//a[contains(.,'Leads')]")).click();
				 // to go to liveleads 
		       
				  driver.findElement(By.xpath("//a[@href='https://ifa1.4cplatform-staging.com/leads']")).click();
				  Utility.captureScreenShot("LeadsDatabase", driver); 
				  driver.findElement(By.xpath("//input[@class='form-control input-sm']")).sendKeys("louis martin");
				  
				  List<WebElement> allHeadersOfTable1= driver.findElements(By.id("index"));
				  System.out.println("Headers in table are below:");
				  System.out.println("Total headers found: "+allHeadersOfTable1.size());
				  for(WebElement header:allHeadersOfTable1)
				  {
				  	System.out.println(header.getText());
				  }
		   Thread.sleep(2000);
		   List<WebElement> allColumnsInRow = driver.findElements(By.xpath("//*[@Id=\"Index\"]/tbody/tr/td[text()='louis']/../td[1]"));
	        for(WebElement e: allColumnsInRow)
	        {
	        	System.out.println(e.getText());
	        }
	    
	       driver.findElement(By.cssSelector("#index > tbody > tr > td.column-actions > a")).click();
	       Thread.sleep(2000); 
	       driver.findElement(By.xpath("//label[contains(.,'Valid lead - convert to contact')]")).click();
	       Thread.sleep(2000); 
	       driver.findElement(By.xpath("//button[@name='submitName']")).click();
	       Utility.captureScreenShot("Databaseinformation", driver);  
	}
	
	}

	
	