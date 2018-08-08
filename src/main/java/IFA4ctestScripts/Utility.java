package IFA4ctestScripts;


import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Utility {
		
					
			public static void captureScreenShot(String screenshotname, WebDriver driver)  {
				
		        //Convert web driver object to TakeScreenshot

		        //Call getScreenshotAs method to create image file

		                try {
		                	TakesScreenshot ts=(TakesScreenshot)driver;
		                	
		                
							File source= ts.getScreenshotAs(OutputType.FILE);
         
 

							FileUtils.copyFile(source, new File("./Screenshots/"+screenshotname+".png"));
							System.out.println("Screenshotcaptured");
						} catch (Exception e) {
							
							System.out.println("Exception while taking screenshot"+e.getMessage());
						}
		

			
			}

	
			public static void scrollPageDown(WebDriver driver) {
				
				JavascriptExecutor js =((JavascriptExecutor)driver);
				
				js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
				


			} 
			
			}