package fitPeoAssessment;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class fitPeoAssessment {

	public static void main(String[] args) {
		// 1.Navigate to FitPeo HomePage

		WebDriver driver = new ChromeDriver();
		driver.get("https://fitpeo.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		System.out.println("1.Navigated to the FitPeo HomePage");

		// 2.Navigate to the Revenue Calculator Page

		driver.findElement(By.xpath("//div[text()='Revenue Calculator']")).click();
		System.out.println("2.Navigated to the Revenue Calculator Page");

		// 3.ScrollDown to the Slider Section

		WebElement scrollElement = driver.findElement(By.xpath("//div//h4[text()='Medicare Eligible Patients']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", scrollElement);
		System.out.println("3.Scrolls down to the Slider Section");

		// 4.Adjust the Slider to 820

		WebElement sliderPoint = driver.findElement(By.xpath("//input[@type='range']"));
		int inputValue = 620;
		for (int i = 1; i <= inputValue; i++) {
			sliderPoint.sendKeys(Keys.ARROW_UP);
		}

		WebElement patientCount = driver
				.findElement(By.xpath("//p[text()='Total Individual Patient/Month']/following-sibling::p"));
		String patientsNumber = "820";
		String pateintCountText = patientCount.getText();

		if (patientsNumber.equals(pateintCountText)) {
			System.out.println("4.Silder Point position is at 820");
		} else {
			System.out.println("Slider Point position is not at 820");
		}

		// 5.Upadte the text field to 560

		WebElement inputField = driver.findElement(By.xpath("//input[@id=':r0:']"));
		inputField.click();
		Actions action = new Actions(driver);
		action.keyDown(Keys.CONTROL).sendKeys("A").keyUp(Keys.CONTROL).perform();
		action.keyDown(Keys.CONTROL).sendKeys("X").keyUp(Keys.CONTROL).perform();
		action.sendKeys("560").perform();
		System.out.println("5.Updated text field to 560");

		// 6. Validate the slider Value

		if (sliderPoint.getAttribute("value").equals("560")) {
			System.out.println("6.Now the Slider point position is at 560");
		}

		action.keyDown(Keys.CONTROL).sendKeys("A").keyUp(Keys.CONTROL).perform();
		action.keyDown(Keys.CONTROL).sendKeys("X").keyUp(Keys.CONTROL).perform();
		action.sendKeys("820").perform();
		System.out.println("Again slider point postion updated to 820");

		// 7. Select CPT codes

		driver.findElement(By.xpath("(//input[@type='checkbox'])[1]")).click();
		driver.findElement(By.xpath("(//input[@type='checkbox'])[2]")).click();
		driver.findElement(By.xpath("(//input[@type='checkbox'])[3]")).click();
		driver.findElement(By.xpath("(//input[@type='checkbox'])[8]")).click();
		System.out.println("7.Selected required check boxes");

		// 8 & 9. Validate Total Recurring Reimbursement & Verify the Header value shows
		// "$110700"

		WebElement recurringPatients = driver.findElement(By.xpath("//p[contains(text(),'Patients Per Month:')]//p"));
		String perMonth = recurringPatients.getText();
		System.out.println(perMonth);
		String amount = "$110700";
		if (perMonth.equals(amount)) {
			System.out.println("8/9.Selected ChechBoxes value & The Header value is EQUALS to $110700");
		} else {
			System.out.println("Selected ChechBoxes value & The Header value is NOT EQUALS to $110700");
		}

		
	}

}
