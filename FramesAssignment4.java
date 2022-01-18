package week4.day1;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FramesAssignment4 {

	public static void main(String[] args) throws IOException {
		// Setup
		WebDriverManager.chromedriver().setup();

		// Step1: Load ServiceNow application URL
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://leafground.com/pages/frame.html");

		// Maximize
		driver.manage().window().maximize();

		// 1.Take the the screenshot of the click me button of first frame

		driver.switchTo().frame(driver.findElement(By.xpath("(//div[@id='wrapframe']/iframe)[1]")));

		WebElement clickButton = driver.findElement(By.xpath("//button[text()='Click Me']"));
		// driver.findElement(By.xpath("//button[text()='Click Me']")).click();
		File source = clickButton.getScreenshotAs(OutputType.FILE);
		File destination = new File("./images/Clickbutton.png");
		FileUtils.copyFile(source, destination);

		driver.switchTo().defaultContent();
		// 2.Find the number of frames
		// - find the Elements by tagname - iframe and store in list
		List<WebElement> findElements = driver.findElements(By.xpath("//iframe"));

		int frameCount = findElements.size();

		System.out.println(frameCount);

	}

}
