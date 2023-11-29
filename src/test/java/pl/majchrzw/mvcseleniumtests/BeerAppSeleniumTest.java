package pl.majchrzw.mvcseleniumtests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.BeforeClass;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.util.List;

@DisplayName("Application")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class BeerAppSeleniumTest{
	
	protected static WebDriver driver;
	protected final String url = "http://localhost:8080";
	
	@BeforeClass
	public static void init(){
		WebDriverManager.chromedriver().setup();
	}
	
	@BeforeEach
	public void setupAll() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
	}
	
	@Test
	@Order(2)
	public void getBeerListTest() {
		// get list
		driver.get(url + "/list");
		// check if table exists
		WebElement table = driver.findElement(By.tagName("table"));
		Assertions.assertNotNull(table);
		// check if table has more than 1 row
		List<WebElement> rows = table.findElements(By.tagName("tr"));
		Assertions.assertTrue( rows.size() > 1);
	}
	
	@Test
	@Order(1)
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
		Assertions.assertEquals(elements+1, beerList.size());
	}
	
	@Test
	@Order(4)
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
		Assertions.assertEquals(elements-1, beerList.size());
	}
	
	@Test
	@Order(3)
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
		Assertions.assertNotEquals(data, newData);
	}
	
	@Test
	public void shouldFail(){
		Assertions.assertEquals(0,1);
	}
	
	@AfterEach
	public void teardown() {
		driver.quit();
	}
}
