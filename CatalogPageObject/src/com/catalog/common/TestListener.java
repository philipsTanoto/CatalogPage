import org.apache.commons.io.FileUtils;
import org.apache.velocity.VelocityContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.IConfigurationListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.uncommons.reportng.HTMLReporter;

import java.io.File;
import java.net.URL;

public class TestListener extends HTMLReporter
       implements ITestListener, IConfigurationListener
{
    protected static final CustomReportNgUtils REPORT_NG_UTILS = new CustomReportNgUtils();

    @Override 
    protected VelocityContext createContext()
    {
        VelocityContext context = super.createContext();

        // VelocityContext has three properties: meta, utils, messages 
        // - see AbstractReporter.createContext()
        context.put("utils", REPORT_NG_UTILS);

        return context;
    }

    /** Invoked when test method (method with annotation @Test) fails. */
    @Override
    public void onTestFailure(ITestResult testResult)
    {
        if (getWebDriver(testResult) != null)
        {
            File scrFile = ((TakesScreenshot) getWebDriver(testResult))
                                             .getScreenshotAs(OutputType.FILE);
            String screenshotName = createScreenshotName(testResult);

            File targetFile = new File(screenshotName);
            FileUtils.copyFile(scrFile, targetFile); 
 
            URL scrUrl = new URL(getDriver(testResult).getCurrentUrl()); 
            Screenshot screenshot = new Screenshot(targetFile, srcUrl );
            testResult.setAttribute(Screenshot.KEY, screenshot);
        }

    }

	@Override
	public void onConfigurationFailure(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onConfigurationSkip(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onConfigurationSuccess(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestStart(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestSuccess(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

    // ...
}