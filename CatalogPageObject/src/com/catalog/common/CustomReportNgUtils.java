import java.util.List;

import org.testng.ITestResult;
import org.uncommons.reportng.ReportNGUtils;

class CustomReportNgUtils extends ReportNGUtils
{
    public List<String> getTestOutput(ITestResult testResult)
    {
        List<String> output = super.getTestOutput(testResult);

        if ( testResult.getAttribute(ScreenCapture.KEY) != null )
        {
            ScreenCapture screenshot = (ScreenCapture) testResult.getAttribute(ScreenCapture.KEY);
            String screenshotFileName = screenshot.getFileName();

            if (screenshot != null)
            {
                String url = (String) testResult.getAttribute("screenshotUrl");
                output.add(String.format("screenshot for %s  %s <br/><img src='../screenshots/%s'>",
                                         testResult.getName(), url, screenshotFileName)
                );
            }
        }

        return output;
    }
}