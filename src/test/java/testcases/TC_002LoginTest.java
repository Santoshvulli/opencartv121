package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testcaseBase.BaseClass;

public class TC_002LoginTest extends BaseClass {
	
	//testsetup
	
	@Test(groups= {"Regression" ,"Master"})
	public void verify_login()
	{
		logger.info(" starting login testcase");
		try
		{
	    //Home page
		HomePage hp=new HomePage(driver);
		hp.ClickMyAccount();
		hp.ClickLoginbutton();
		
		//Login page
		LoginPage lp=new LoginPage(driver);
		lp.Enteremail(p.getProperty("email"));
		lp.Enterpassword(p.getProperty("password"));
		lp.ClickLoginbuttononloginpage();
		
		//MyAccount Page
		MyAccountPage myaccount=new MyAccountPage(driver);
	    boolean targetpage=myaccount.getMyAccountconfirmmessage();
		
		Assert.assertTrue(targetpage);
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		
		logger.info("exicution is finished");
		
		
	}
	
	

}
