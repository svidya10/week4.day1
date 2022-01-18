package week4.day1;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ServiceNow {

	public static void main(String[] args) throws InterruptedException, IOException {
		// Setup
		WebDriverManager.chromedriver().setup();

		// Step1: Load ServiceNow application URL
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://dev66648.service-now.com/login.do?");

		// Maximize
		driver.manage().window().maximize();

		// Step2: Enter username (Check for frame before entering the username)
		driver.findElement(By.xpath("//input[@id='user_name']")).sendKeys("admin");

		// Step3: Enter password

		driver.findElement(By.xpath("//input[@id='user_password']")).sendKeys("Janu1012$");
		// Step4: Click Login

		driver.findElement(By.xpath("//button[@id='sysverb_login']")).click();

		// Step5: Search “incident “ Filter Navigator
		driver.findElement(By.xpath("//input[@id='filter']")).sendKeys("incidents");

		Thread.sleep(5000);

		driver.findElement(By.xpath("//input[@id='filter']")).sendKeys(Keys.ENTER);

		Thread.sleep(3000);

		// Step6: Click “All”

		driver.switchTo().frame("gsft_main");
		driver.findElement(By.xpath("//span[@id='incident_breadcrumb']/a[1]")).click();

		// Step7: Click New button

		driver.findElement(By.xpath("//button[@id='sysverb_new']")).click();

		// Step8: Select a value for Caller and Enter value for short_description

		driver.findElement(By.xpath("//button[@id='lookup.incident.caller_id']")).click();

		Thread.sleep(3000);

		//// Switch to child frame
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> windows = new ArrayList<String>(windowHandles);

		driver.switchTo().window(windows.get(1));
		System.out.println(driver.getTitle());

		driver.findElement(By.xpath("//table[@id='sys_user_table']//tr[1]/td[3]/a")).click();

		//// Switch to parent frame
		windowHandles = driver.getWindowHandles();
		windows = new ArrayList<String>(windowHandles);

		driver.switchTo().window(windows.get(0));

		Thread.sleep(3000);
		System.out.println(driver.getTitle());

		driver.switchTo().frame("gsft_main");
		driver.findElement(By.xpath("//input[@id='incident.short_description']")).sendKeys("Short Description");

		// Step9: Read the incident number and save it a variable
		String incidentNo = driver.findElement(By.xpath("//input[@id='incident.number']")).getAttribute("value");
		System.out.println(incidentNo);

		// Step10: Click on Submit button
		driver.findElement(By.xpath("//button[text()='Submit']")).click();

		// Step 11: Search the same incident number in the next search screen as below
		driver.findElement(By.xpath(
				"//span[text()='Press Enter from within the input to submit the search.']/following-sibling::input"))
				.sendKeys(incidentNo);
		driver.findElement(By.xpath(
				"//span[text()='Press Enter from within the input to submit the search.']/following-sibling::input"))
				.sendKeys(Keys.ENTER);

		// Step12: Verify the incident is created successful and take snapshot of the
		// created incident.
		String searchIncidentNo = driver.findElement(By.xpath("//table[@id='incident_table']//tr[1]/td[3]")).getText();
		System.out.println(searchIncidentNo);

		if (incidentNo.equals(searchIncidentNo) == true) {
			System.out.println("incident number present");
		} else {
			System.out.println("incident number not present");
		}

		// Take Snapshot

		File source = driver.getScreenshotAs(OutputType.FILE);
		File destination = new File("./images/ServiceNow.png");
		FileUtils.copyFile(source, destination);

	}

}
