
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author The Babies
 */
public class Automation {

    WebDriver driver;
    //String driverPath = "C:/Users/The Babies/Desktop/automation test/chromedriver.exe";
    String driverPath = "chromedriver.exe";
    WebPageMapping wp = new WebPageMapping();

    public void startSelenium() {
        driver = new ChromeDriver();
        System.setProperty("webdriver.chrome.driver", driverPath);
        driver.navigate().to(wp.deafultURL);

    }

    public String CelsiusToFahrenheit(double num) {
        driver.navigate().to(wp.cToF_URL);
        driver.findElement(By.xpath(wp.cToF_TextFiled)).sendKeys(Double.toString(num));
        String ans = driver.findElement(By.xpath(wp.CtoF_result_path)).getText();
        ans = fixResult(ans);
        System.out.println(ans);
        driver.close();
        return ans;

    }

    private static String fixResult(String toFix) {
        String ans = "";
        int index = toFix.lastIndexOf("=") + 1;
        for (int i = index; i < toFix.length(); i++) {
            ans += toFix.charAt(i);
        }
        return ans;
    }

    public String MeterstoFeet(double num) {
        driver.navigate().to(wp.mToF_URL);
        driver.findElement(By.xpath(wp.mToF_textFiled_path)).sendKeys(Double.toString(num));
        String ans = driver.findElement(By.xpath(wp.mToF_result_path)).getText();
        ans = fixResult(ans);
        System.out.println(ans);
        driver.close();
        return ans;
    }

    public String OnucesToGrames(double num) {
        driver.navigate().to(wp.oTg_URL);
        driver.findElement(By.xpath(wp.oTg_TextFiled_path)).sendKeys(Double.toString(num));
        String ans = driver.findElement(By.xpath(wp.oTg_resultPath)).getText();
        ans = fixResult(ans);
        System.out.println(ans);
        driver.close();
        return ans;
    }

    public String GetWetherForZipWithCheats() throws InterruptedException {

        driver = new ChromeDriver();
        System.setProperty("webdriver.chrome.driver", driverPath);
        // driver.manage().window().setPosition(new Point(-1000, -2000));
        driver.navigate().to("https://developer.here.com/api-explorer/rest/auto_weather/weather-observation-zipcode");
        Thread.sleep(3000);
        driver.switchTo().frame("/inline-template/documentation/iframe");
        //driver.findElement(By.id("params-zipcode")).clear();
        driver.findElement(By.xpath("//*[@id=\"params-zipcode\"]")).sendKeys("20852");
        driver.findElement(By.xpath("//*[@id=\"go-btn\"]")).click();
        String Wether
                = driver.findElement(By.xpath("//*[@id=\"json-response\"]/pre")).getText();

        System.out.println(Wether);
        return Wether;
    }
}
