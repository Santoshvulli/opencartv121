package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage { 
		
		public MyAccountPage(WebDriver driver)
		{
			super(driver);
		}
		
		
		@FindBy(xpath="//h2[normalize-space()='My Account']")
		WebElement MyAccount_txt;
		
		@FindBy(xpath="//a[@class='list-group-item'][normalize-space()='Logout']")
		WebElement Logout_btn;
		
		
		public boolean getMyAccountconfirmmessage()
		{
			try
			{
			return(MyAccount_txt.isDisplayed());
			}
			catch(Exception e)
			{
				return false;
			}
		}
		
		
		public void ClickLogoutbutton()
		{
			Logout_btn.click();
		}

}
