import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

import static org.openqa.selenium.Keys.ARROW_DOWN;


public class BaseModel extends Driver {

    public boolean checkUrl(String url) {
        return Objects.equals(webDriver.getCurrentUrl(), url);
    }

    public void click(By element) {
        waitUntilIsElementVisible(element);
        webDriver.findElement(element).click();
    }

    public String getElementText(By element) {
        return webDriver.findElement(element).getText();
    }

    public boolean isElementSelected(By element) {
        return webDriver.findElement(element).isSelected();
    }

    public boolean checkColour(By element, String colorCode) {
        String color = webDriver.findElement(element).getCssValue("color");
        return color.equals(colorCode);
    }

    public void getUrl(String url) {
        webDriver.get(url);
    }

    public List<WebElement> getElements(By element) {
        return webDriver.findElements(element);
    }

    public void sendKey(By element, String key) {
        webDriver.findElement(element).clear();
        webDriver.findElement(element).sendKeys(key);
    }

    public void waitUntilIsElementNotEqualValue(By element, String value) {
        WebDriverWait wait = new WebDriverWait(webDriver, 20);
        wait.until((ExpectedCondition<Boolean>) driver -> !Objects.equals(driver.findElement(element).getText(), value));
    }

    public void waitUntilIsElementVisible(By element) {
        WebDriverWait wait = new WebDriverWait(webDriver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public void waitUntilIsElementClickable(By element) {
        WebDriverWait wait = new WebDriverWait(webDriver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public String getPageTtitle() {
        return webDriver.getTitle();

    }
    public void scrollToBottomOfPage(){
        JavascriptExecutor js= (JavascriptExecutor) webDriver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }
    public void setCookie (Cookie cookie){
        webDriver.manage().addCookie(cookie);

    }

    public void sendKeysToActiveElement(Keys key) {
        webDriver.switchTo().activeElement().sendKeys(key);
    }

    public void selectByArrowDown(By selectBy, int downTimes) {
        click(selectBy);
        IntStream.range(0, downTimes).mapToObj(i -> ARROW_DOWN).forEach(this::sendKeysToActiveElement);
        sendKeysToActiveElement(Keys.ENTER);
    }

    public void waitUntilIsElementSizeExpected(By element, int value) {
        WebDriverWait wait = new WebDriverWait(webDriver, 20);
        wait.until((ExpectedCondition<Boolean>) driver -> Objects.equals(driver.findElements(element).size(), value));
    }

    public float calculateToPrice(String price){
        return Float.parseFloat(price.trim().replace("₺ ","").replace(".", "").replace(",", "."));
    }
}
