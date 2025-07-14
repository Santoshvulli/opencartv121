package pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	
	WebDriver driver; 
	WebDriverWait mywait;
	
	public BasePage(WebDriver driver)
	{
		 this.driver=driver;
		 PageFactory.initElements(driver, this);
		 mywait=new WebDriverWait(driver , Duration.ofSeconds(10));
	}

}
