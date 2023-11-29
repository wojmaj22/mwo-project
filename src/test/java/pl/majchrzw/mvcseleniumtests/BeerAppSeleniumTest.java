package pl.majchrzw.mvcseleniumtests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

public class BeerAppSeleniumTest {
	
	private static WebDriver driver;
	private static final String url = "http://localhost:8080";
	
	@Before
	public void setupAll() {
		WebDriverManager.chromedriver().setup();

		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
	}
	
	@Test
	public void getBeerListTest() {
		// get list
		driver.get(url + "/list");
		// check if table exists
		WebElement table = driver.findElement(By.tagName("table"));
		Assert.assertNotNull(table);
		// check if table has more than 1 row
		List<WebElement> rows = table.findElements(By.tagName("tr"));
		Assert.assertTrue( rows.size() > 1);
	}
	
	@Test
	public void addBeerTest(){
		// get current amount of beers in list
		driver.get(url + "/list");
		List<WebElement> oldBeerList = driver.findElements(By.tagName("tr"));
		int elements = oldBeerList.size();
		// add new element
		driver.get(url + "/add");
		WebElement nameInput = driver.findElement(By.id("name"));
		nameInput.sendKeys("Harnaś");
		WebElement priceInput = driver.findElement(By.id("price"));
		priceInput.sendKeys("3.65");
		WebElement submitButton = driver.findElement(By.id("submit-button"));
		submitButton.click();
		// check new amount
		driver.get(url + "/list");
		List<WebElement> beerList = driver.findElements(By.tagName("tr"));
		Assert.assertEquals(elements+1, beerList.size());
	}
	
	@Test
	public void deleteBeerTest(){
		// get current amount of beers in list
		driver.get(url + "/list");
		List<WebElement> oldBeerList = driver.findElements(By.tagName("tr"));
		int elements = oldBeerList.size();
		// delete element
		List<WebElement> deleteButtons = driver.findElements(By.id("delete-button"));
		deleteButtons.get(deleteButtons.size()-1).click();
		// check new amount
		driver.get(url + "/list");
		List<WebElement> beerList = driver.findElements(By.tagName("tr"));
		Assert.assertEquals(elements-1, beerList.size());
	}
	
	@Test
	public void updateBeerTest(){
		// get last beer in list
		driver.get(url + "/list");
		List<WebElement> beerList = driver.findElements(By.tagName("tr"));
		WebElement lastBeer = beerList.get(beerList.size()-1);
		String data = lastBeer.getText();
		// update last beer
		WebElement updateButton = lastBeer.findElement(By.id("update-button"));
		updateButton.click();
		WebElement nameInput = driver.findElement(By.id("name"));
		nameInput.clear();
		nameInput.sendKeys("Kuflowe mocne");
		WebElement submitButton = driver.findElement(By.tagName("button"));
		submitButton.click();
		// check if beer has been updated
		List<WebElement> newBeerList = driver.findElements(By.tagName("tr"));
		WebElement newLastBeer = newBeerList.get(newBeerList.size()-1);
		String newData = newLastBeer.getText();
		Assert.assertNotEquals(data, newData);
	}
	
	@After
	public void teardown() {
		driver.quit();
	}
}
