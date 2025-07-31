package testcaseBase;



import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;//log4j
import org.apache.logging.log4j.Logger;   //log4j
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	
	public static WebDriver driver;
	public Logger logger;
	public Properties p;
	
	@BeforeClass(groups= {"Sanity" ,"Regression","Master"})
	@Parameters({"os" , "browser"})
	public void setup(String os , String br) throws InterruptedException, IOException
	{
		//Reading config.pro[erties file
		
		FileReader file=new FileReader("./src//test//resources//config.properties");
		p=new Properties();
		p.load(file);
		
		logger=LogManager.getLogger(this.getClass());
		
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities cap= new DesiredCapabilities();
			
			//os
			if(os.equalsIgnoreCase("Windows"))
			{
				cap.setPlatform(Platform.WINDOWS);
			}
			else if(os.equalsIgnoreCase("linux"))
			{
				cap.setPlatform(Platform.LINUX);
			}
			
			else if (os.equalsIgnoreCase("Mac"))
			{
				cap.setPlatform(Platform.MAC);
			}
			else
			{
				System.out.println("Invalid browser");
				return;
			}
			
			//browser
			switch(br.toLowerCase())
			{
			case "chrome" : cap.setBrowserName("chrome"); break;
			case "edge" : cap.setBrowserName("MicrosoftEdge");break;
			case "firefox" : cap.setBrowserName("Firefox"); break;
			default: System.out.println("invalid browser"); return;
			
			}
			
			driver=new RemoteWebDriver(new URL("http://localhost:4444"),cap);
			
		}
		
		
		if(p.getProperty("execution_env").equalsIgnoreCase("local"))
		{
			switch(br.toLowerCase())
			{
			case "chrome"   : driver=new ChromeDriver(); break;
			case "firefox" : driver=new FirefoxDriver(); break;
			case "edge"    : driver=new EdgeDriver(); break;
			default : System.out.println("invalid browser"); return;

			}
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(p.getProperty("appURL"));	
		driver.manage().window().maximize();
		Thread.sleep(5000);
	}
	
	@AfterClass(groups= {"Sanity" ,"Regression","Master"})
	public void teardown()
	{
		if(driver !=null)
		{
			driver.quit();
		}
		
	}
	
	
	public String randomString()
	{
		String generatedrandomstring=RandomStringUtils.randomAlphabetic(7);
		return generatedrandomstring;
	}
	
	public String randomNumber()
	{
		String generatedrandomnumber=RandomStringUtils.randomNumeric(10);
		return generatedrandomnumber;
	}
	
	public String randomAlphaNumeric()
	{
		String generatedrandomstring=RandomStringUtils.randomAlphabetic(3);
		String generatedrandomnumber=RandomStringUtils.randomNumeric(4);
		return (generatedrandomstring+"@"+generatedrandomnumber);
	}
	
	
	
	public String captureScreen(String tname) throws IOException {
		
		SimpleDateFormat df=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		Date dt=new Date();
		String timeStamp=df.format(dt);
				
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\Screenshorts\\" + tname + "_" + timeStamp + ".png";
		File targetFile=new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
			
		return targetFilePath;

	}
	

		

}
