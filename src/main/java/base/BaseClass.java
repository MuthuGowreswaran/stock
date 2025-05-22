package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;

import utils.Reporter;

public class BaseClass extends Reporter {

	public static Properties prop;

	@BeforeMethod()
	public void preCondition() throws IOException {
		FileInputStream config = new FileInputStream("./src/main/resources/config.properties");
		prop = new Properties();
		prop.load(config);
		setDriver(prop.getProperty("browser"), prop.getProperty("nse.url"));
		setWait();
	}

	@AfterClass(alwaysRun = true)
	public void postCondition() {

		getDriver().quit();

	}

	public String snapshot(String filename) {

		File screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String targetfilepath = System.getProperty("user.dir") + "./Screenshot/" + filename + ".png";

		File targetfile = new File(targetfilepath);
		try {
			FileUtils.copyFile(screenshot, targetfile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return targetfilepath;
	}

}
