package week4.day1;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FramesAssignment3 {

	public static void main(String[] args) throws IOException {
		// Setup
		WebDriverManager.chromedriver().setup();

		// Step1: Load ServiceNow application URL
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://chercher.tech/practice/frames-example-selenium-webdriver");

		// Maximize
		driver.manage().window().maximize();

		// Switch to Frame at top
		driver.switchTo().frame("frame1");

		// Enter Text in Topic Text box
		driver.findElement(By.xpath("//b[@id='topic']//following-sibling::input")).sendKeys(Keys.ENTER);
		driver.findElement(By.xpath("//b[@id='topic']//following-sibling::input")).sendKeys("Testleaf");

		// Switch to Inner Frame that has the Check box
		driver.switchTo().frame("frame3");

		// Check mark the Inner Frame Check box
		driver.findElement(By.xpath("//input[@id='a']")).click();

		// Switch to default frame

		driver.switchTo().defaultContent();

		// Swtich to Animals frame and select Avatar

		driver.switchTo().frame("frame2");
		WebElement dd = driver.findElement(By.xpath("//select[@id='animals']"));
		Select dropdown = new Select(dd);
		dropdown.selectByVisibleText("Avatar");

		// Take Snapshot

		File source = driver.getScreenshotAs(OutputType.FILE);
		File destination = new File("./images/Avatar.png");
		FileUtils.copyFile(source, destination);

	}

}
