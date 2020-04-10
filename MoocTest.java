import static org.junit.Assert.assertTrue;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class MoocTest {

	private WebDriver driver;
	private String url;
	private WebElement elementById;//搜索框的id选择器
	private WebElement elementByCssSelctor;//同一个搜索框的css选择器
	
	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "E:/chromedriver_win32/chromedriver.exe");//chromedriver的版本要与浏览器版本相契合
		driver = new ChromeDriver();
		url = "https://www.bilibili.com/";
		driver.get(url);
		elementById = driver.findElement(By.className("nav-search-keyword"));
		elementByCssSelctor = driver.findElement(By.cssSelector("#nav_searchform > input"));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);//隐式等待
	}
	
	//用例一：正常输入
	@Test
	public void testMooc1() {
		elementById.clear();
		elementById.sendKeys("软件测试");
		elementById.submit();
		
		elementByCssSelctor.clear();
		elementByCssSelctor.sendKeys("软件测试");
		elementByCssSelctor.submit();
		
		assertTrue(isElementPresent(By.linkText("首页")));
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	//用例一：输入为空
	@Test
	public void testMooc2() {
		elementById.clear();
		elementById.sendKeys("");
		elementById.submit();
		
		assertTrue(isElementPresent(By.linkText("首页")));
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	//用例三：输入特殊字符
	@Test
	public void testMooc3() {
		elementById.clear();
		elementById.sendKeys("+-*/");
		elementById.submit();
		
		assertTrue(isElementPresent(By.linkText("首页")));
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@After
	public void exitBrowser() {
		driver.quit();
	}
	
	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		}catch (NoSuchElementException e){
			return false;
		}
		
	}
	
}
