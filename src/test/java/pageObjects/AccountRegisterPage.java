package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegisterPage extends BasePage {
	
	
	//Constructor
	public AccountRegisterPage(WebDriver driver)
	{
		super(driver);
	}
	
	//Locators
	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement FirstName;
	
	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement LastName;
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement Email;
	
	@FindBy(xpath="//input[@id='input-telephone']")
	WebElement PhoneNumber;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement Password;
	
	@FindBy(xpath="//input[@id='input-confirm']")
	WebElement ConformPassword;
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement policy;
	
	@FindBy(xpath="//input[@value='Continue']")
	WebElement conformbutton;
	
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement conformmessage;
	
	
	//Action methods
	public void EnterFirstName(String fname)
	{
		FirstName.sendKeys(fname);
	}
	
	public void EnterLastName(String lname)
	{
		LastName.sendKeys(lname);
	}
	
	public void EnterEmail(String email)
	{
		Email.sendKeys(email);
	}
	
	public void EnterPhoneNumber(String number)
	{
		PhoneNumber.sendKeys(number);
	}
	
	
	public void EnterPassword(String password)
	{
		Password.sendKeys(password);
	}
	
	
	public void EnterConfirmPassword(String con_password)
	{
		ConformPassword.sendKeys(con_password);
	}
	
	public void Clickpolicy()
	{
		policy.click();
	}
	
	public void Clickconfirmbutton()
	{
		conformbutton.click();
	}
	
	
	
	public String getconformationmessage()
	{
		try 
		{
			return(conformmessage.getText());
		}
		catch(Exception e)
		{
			return(e.getMessage());
		}
	}
	

}
