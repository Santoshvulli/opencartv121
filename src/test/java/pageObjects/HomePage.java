package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {

	
	public HomePage(WebDriver driver)
	{
		super(driver);
	}
	
	
	@FindBy(xpath = "//span[text()='My Account']")
	WebElement myaccount_btn;
	
	@FindBy(xpath="//a[normalize-space()='Register']")
	WebElement register_btn;
	
	@FindBy(xpath="//a[normalize-space()='Login']")
	WebElement Login_btn;
	
	
	public void ClickMyAccount()
	{
		mywait.until(ExpectedConditions.elementToBeClickable(myaccount_btn)).click();
	}
	
	public void ClickRegister()
	{
		mywait.until(ExpectedConditions.elementToBeClickable(register_btn)).click();
	}

	
	public void ClickLoginbutton() 
	{
		Login_btn.click();
	}
	
	
}
