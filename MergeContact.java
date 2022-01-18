package week4.day1;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
public class MergeContact {

	public static void main(String[] args) throws InterruptedException {
		
		// Setup
		WebDriverManager.chromedriver() .setup();
		
		//Launch URL
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://leaftaps.com/opentaps/control/login");
		
		// Maximize
		driver.manage().window().maximize();
		
		// Enter UserName and Password Using Id Locator
		WebElement userName = driver.findElement(By.id("username"));

		userName.sendKeys("DemoCSR");
		driver.findElement(By.id("password")).sendKeys("crmsfa");
		
		// Click on Login Button using Class Locator
		driver.findElement(By.className("decorativeSubmit")).click();
		
		// Click on CRM/SFA Link
		driver.findElement(By.linkText("CRM/SFA")).click();
		
		// Click on contacts Button	
		driver.findElement(By.linkText("Contacts")).click();
		
		// Click on Merge Contacts using Xpath Locator
		driver.findElement(By.xpath("//a[text()='Merge Contacts']")).click();
		
		// Click on Widget of From Contact
		driver.findElement(By.xpath("(//span[text()='From Contact']/following::a)[1]")).click();
		
		Thread.sleep(3000);
		
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> windows = new ArrayList<String>(windowHandles);
		
		driver.switchTo().window(windows.get(1));
		System.out.println(driver.getTitle());
		
		// Click on First Resulting Contact
		driver.findElement(By.xpath("(//div[@class='x-grid3-cell-inner x-grid3-col-firstName']/a)[1]")).click();

		driver.switchTo().window(windows.get(0));
		
		// Click on Widget of To Contact
		driver.findElement(By.xpath("(//span[text()='To Contact']/following::a)[1]")).click();
		
		Thread.sleep(3000);
		
		windowHandles = driver.getWindowHandles();
		windows = new ArrayList<String>(windowHandles);
		
		driver.switchTo().window(windows.get(1));
		
		// Click on Second Resulting Contact
		driver.findElement(By.xpath("(//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a)[2]")).click();

		driver.switchTo().window(windows.get(0));
		
		// Click on Merge button using Xpath Locator
		driver.findElement(By.xpath("//a[text()='Merge']")).click();
		
		// Accept the Alert
		driver.switchTo().alert().accept();
		
		// Verify the title of the page
		System.out.println(driver.getTitle());	
	}
}
