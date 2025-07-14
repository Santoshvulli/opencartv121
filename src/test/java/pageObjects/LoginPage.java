package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
	
	public LoginPage(WebDriver driver)
	{
		super(driver);
	}
	
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement email_txt;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement Password_txt;
	
	@FindBy(xpath="//input[@value='Login']")
	WebElement Login_btn;
	
	
	public void Enteremail(String email)
	{
		email_txt.sendKeys(email);
	}
	
	public void Enterpassword(String pass)
	{
		Password_txt.sendKeys(pass);
	}
	
	public void ClickLoginbuttononloginpage()
	{
		Login_btn.click();
	}

}
