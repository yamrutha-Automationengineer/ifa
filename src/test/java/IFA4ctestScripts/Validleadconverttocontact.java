package IFA4ctestScripts;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class Validleadconverttocontact {

	public static void main(String[] args) throws InterruptedException, Exception {

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Amruta.Yembuluru\\driver\\chromedriver_win32\\chromedriver.exe");
		//to run on Mozillafirefox browser
		//System.setProperty("webdriver.chrome.driver", "C:\\Users\\amruta_yembuluru\\driver\\geckodriver-v0.20.1-win64\\geckodriver.exe");
		 WebDriver driver = new ChromeDriver();
			// driver.manage().window().maximize();
		 driver.manage().deleteAllCookies(); driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		 Thread.sleep(2000);
		 driver.get("https://ifa1.4cplatform-staging.com");
	
		 //gettitle
		 String i = driver.getTitle();
		  System.out.println("Your page title Is : "+i);


			 Assert.assertEquals("4C Platform",i);
			 Assert.assertEquals("4C Platform",driver.getTitle());
		WebElement Simpletextdv = driver.findElement(By.xpath("//h1[contains(.,'Simple PMI for brokers & IFAs.')]"));
		String Simpletextd = Simpletextdv.getText();
		 System.out.println(Simpletextd);
		 Boolean Simpletextv = Simpletextdv.isDisplayed();
		 System.out.println(Simpletextv);
			 Thread.sleep(2000);

		  driver.findElement(By.linkText("LOGIN")).click();
		  
		  WebElement email = driver.findElement(By.xpath( "//input[@id='email']"));	
		  email.sendKeys("ifa1_user1@usaycompare.com");
		  WebElement pwd = driver.findElement(By.xpath( "//input[@id='password']"));	
		  pwd.sendKeys("secret");
		  driver.findElement(By.xpath("//button[contains(.,'Sign in  ')]")).click(); 
		  driver.findElement(By.xpath("//button[@value='IFA']")).click(); 

		  driver.findElement(By.xpath("//span[contains(.,' Live leads ')]")).click(); 
		  //sortng by name
		  Thread.sleep(3000);
		  driver.findElement(By.xpath("//th[@aria-label='Name: activate to sort column ascending']"));
		  //update cmd on liveleads
		  Thread.sleep(3000);
		  driver.findElement(By.xpath("//a[contains(@class,'btn btn-success btn-xs')]")).click(); 
		  List<WebElement> allColumnsInTable = driver.findElements(By.cssSelector("div.row:nth-child(3) > div:nth-child(1)"));
		  for(WebElement e: allColumnsInTable)
		  {
		  	System.out.println(e.getText());
		  }


		  //lead status-Valid lead-convert to contact
		  driver.findElement(By.xpath("//label[contains(.,'Valid lead - convert to contact')]")).click();
		//to click on update button
		  driver.findElement(By.xpath("//button[@value='save']")).click();
List<WebElement> DatabaseInformation = driver.findElements(By.id("collapseDefault"));
for(WebElement f: DatabaseInformation)
{
	System.out.println(f.getText());
}
WebElement name = driver.findElement(By.xpath("//span[contains(@class,'label label-white pull-right')]"));
String text = name.getText();
System.out.println(text);
driver.findElement(By.xpath("//a[@class='btn btn-primary btn-xs pull-left']")).click();

WebElement onlineactivationstatus = driver.findElement(By.xpath("//td[contains(@class,'bg-danger')]"));
String Activestatus = onlineactivationstatus.getText();
System.out.println(Activestatus);
driver.findElement(By.cssSelector("#headingTwo > h4:nth-child(1)")).click();

List<WebElement> PersonalInformation = driver.findElements(By.id("collapseTwo"));
Thread.sleep(3000);
for(WebElement g: PersonalInformation)
{
	System.out.println(g.getText());
}


	}

}



