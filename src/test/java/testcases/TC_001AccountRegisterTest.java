package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegisterPage;
import pageObjects.HomePage;
import testcaseBase.BaseClass;

public class TC_001AccountRegisterTest extends BaseClass
{
	
	
	@Test(groups={"sanity" , "Master"})
	public void verify_regestationpage()
	{
	 try
	 { 
		logger.info(".....Starting Execution...");
		HomePage hp=new HomePage(driver);
		hp.ClickMyAccount();
		logger.info("clicked on MyAccount button");
		hp.ClickRegister();
		
		AccountRegisterPage regpage=new AccountRegisterPage(driver);
		regpage.EnterFirstName(randomString().toUpperCase());
		regpage.EnterLastName(randomString().toUpperCase());
		regpage.EnterEmail(randomString()+"@gmail.com");
		
		String password=randomAlphaNumeric();
		regpage.EnterPhoneNumber(randomNumber());
		regpage.EnterPassword(password);
		regpage.EnterConfirmPassword(password);
		regpage.Clickpolicy();
		regpage.Clickconfirmbutton();
		
		logger.info("Validating the regestration conform page");
		String confmsg=regpage.getconformationmessage();
		if(confmsg.equals("Your Account Has Been Created!"))
		{
			Assert.assertTrue(true);
		}
		else
		{
			logger.debug("Test Failed");
			logger.debug("Debug logs");
			Assert.assertTrue(false);
		}
		
	 }
	 catch(Exception e)
	 {
		Assert.fail();
		
	 }
   }

}
