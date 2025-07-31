package utilities;


import java.awt.Desktop;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports; // <-- now no conflict
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testcaseBase.BaseClass;



public class ExtentReportsMang implements ITestListener{
	
	public ExtentSparkReporter sparkreporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	String repName;
	
	public void onStart(ITestContext context) {
		
		SimpleDateFormat df=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		Date dt=new Date();
		String Timestamp=df.format(dt);
		
		repName="Automation-Report"+Timestamp+".html";
		sparkreporter=new ExtentSparkReporter(".\\Reports\\"+repName);
		
		sparkreporter.config().setDocumentTitle("Automation");
		sparkreporter.config().setReportName("Testing Test cases");
		sparkreporter.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();  
        extent.attachReporter(sparkreporter);
        extent.setSystemInfo("Project", "Opencart");
        extent.setSystemInfo("Module", "Admin");
        extent.setSystemInfo("UserName", System.getProperty("user.name"));
        extent.setSystemInfo("Env", "QA");
        
        String os=context.getCurrentXmlTest().getParameter("os");
        extent.setSystemInfo("Operating System", os);
        
        String browser=context.getCurrentXmlTest().getParameter("browser");
        extent.setSystemInfo("Browser", browser);
        
        List<String> Includegroup=context.getCurrentXmlTest().getIncludedGroups();
        if(!Includegroup.isEmpty())
        {
        	extent.setSystemInfo("Groups", Includegroup.toString());
        }
        
        
	  }
	
	 public void onTestSuccess(ITestResult result) {
		 test=extent.createTest(result.getTestClass().getName());
		 test.assignCategory(result.getMethod().getGroups());  //to display groups in report
		 test.log(Status.PASS, "Test is passed: "+result.getTestClass().getName());
		  }
	 
	 public void onTestFailure(ITestResult result) {
		 
		 test=extent.createTest(result.getTestClass().getName());
		 test.assignCategory(result.getMethod().getGroups());
		 
		 test.log(Status.FAIL, "Test Failed "+result.getTestClass().getName());
		 test.log(Status.INFO, result.getThrowable().getMessage());
		 
		 try {
            String imgpath=new BaseClass().captureScreen(result.getName());
            test.addScreenCaptureFromPath(imgpath);
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		 
		 
		  }
	 
	 public void onTestSkipped(ITestResult result) {
		 
		 test=extent.createTest(result.getTestClass().getName());
		 test.assignCategory(result.getMethod().getGroups());
		 test.log(Status.SKIP, "Test Skipped"+result.getTestClass().getName());
		 test.log(Status.INFO, result.getThrowable().getMessage());
		  }
	 
	 
	 public void onFinish(ITestContext context) {
		 extent.flush();
		 String filepath=System.getProperty("user.dir")+".\\Reports\\"+repName;
		 File fi=new File(filepath);
		 
		 try {
			 Desktop.getDesktop().browse(fi.toURI());
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		 
		  }

}
