package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testcaseBase.BaseClass;
import utilities.DataProviders;


public class TC_003LoginDDT extends BaseClass 
{
	
	
	@Test(dataProvider="LoginDate" , dataProviderClass=DataProviders.class, groups= ("Datadriven"))
	public void verify_LoginDDT(String Username , String password , String res)
	{
		logger.info("Starting the Execution");
		
		//Home
		try
		{
		HomePage hp=new HomePage(driver);
		hp.ClickMyAccount();
		hp.ClickLoginbutton();
		
		
		//LoginPage
	    
		LoginPage lp=new LoginPage(driver);
		lp.Enteremail(Username);
		lp.Enterpassword(password);
		lp.ClickLoginbuttononloginpage();
		
		//Myaccount
		
		logger.info("Validating MyAccount page");
		MyAccountPage myaccount=new MyAccountPage(driver);
		boolean targetpage=myaccount.getMyAccountconfirmmessage();
		
		
		/*Data is valid  - login success - test pass  - logout
		                 - login failed - test fail

		Data is invalid - login success - test fail  - logout
		                - login failed - test pass
		*/
		
		logger.info("Validating from excel page");
		if(res.equalsIgnoreCase("valid"))
		{
			if(targetpage==true)
			{
				myaccount.ClickLogoutbutton();
				Assert.assertTrue(true);
			}
			else
			{
				Assert.assertTrue(false);
			}
		}
		
		if(res.equalsIgnoreCase("invalid"))
		{
			if(targetpage==true)
			{
				myaccount.ClickLogoutbutton();
				Assert.assertTrue(false);
			}
			else
			{
				Assert.assertTrue(true);
			}
		}
		
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		
		logger.info("Data drivan test is completed succefully");
		
	}

}
