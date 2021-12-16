import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class BasketPage extends BaseModel{
    public static By basketProductTitle = By.xpath("//*[@data-title='Product']");
    public static By quantityInput = By.xpath("//*[@title='Qty']");
    public static By totalPrice = By.xpath("//*[@data-title='Subtotal']");
    public static By updateCard = By.xpath("//*[@name='update_cart']");
    public static By removeProduct = By.xpath("//*[@class='remove']");
    public static By cartEmptyInfo = By.xpath("//*[@class='cart-empty woocommerce-info']");
    public static By productData = By.cssSelector(".woocommerce-cart-form__cart-item.cart_item");
    public static By price = By.xpath("//*[@data-title='Price']");
    public static By proceedByCheckOutButton = By.cssSelector(".checkout-button");

    public String getBasketProductTitle(){
        webDriver.navigate().refresh();
        return webDriver.findElements(basketProductTitle).get(0).getText();
    }

    public void changeQuantity(int quantity){
        String totalPriceBeforeUpdate = getTotalPrice();
        sendKey(quantityInput, String.valueOf(quantity));
        click(updateCard);
        waitUntilIsElementNotEqualValue(totalPrice, totalPriceBeforeUpdate);
    }

    public String getTotalPrice(){
        return getElementText(totalPrice);
    }

    public void removeProduct(){
        waitUntilIsElementClickable(removeProduct);
        click(removeProduct);
        waitUntilIsElementVisible(cartEmptyInfo);
    }

    public String getEmptyBasketText(){
        return getElementText(cartEmptyInfo);
    }

    public List<String> getBasketProductTitles(int count){
        List<String> productTitles = new ArrayList<String>();
        for (int i = 0; i < count; i++) {
            productTitles.add(getElements(basketProductTitle).get(i).getText());
        }
        return productTitles;
    }

    public WebElement getCheapCart() {
        float lowest = Float.MAX_VALUE;
        List<WebElement> fullCarts = getElements(productData);
        WebElement cheapElement = null;
        for (int i = 0; i < fullCarts.size(); i++) {
            var lowestPrice = calculateToPrice(fullCarts.get(i).findElement(price).getText());
            if (lowestPrice < lowest) {
                lowest = lowestPrice;
                cheapElement = getElements(productData).get(i);
            }
        }
        return cheapElement;
    }

    public WebElement getExpensiveCart() {
        float highest = Float.MIN_VALUE;
        List<WebElement> fullCarts = getElements(productData);
        WebElement expensiveElement = null;
        for (int i = 0; i < fullCarts.size(); i++) {
            var lowestPrice = calculateToPrice(fullCarts.get(i).findElement(price).getText());
            if (lowestPrice > highest) {
                highest = lowestPrice;
                expensiveElement = getElements(productData).get(i);
            }
        }
        return expensiveElement;
    }


    public void changeQuantityAtCheapProduct(int quantity){
        String totalPriceBeforeUpdate = getTotalPrice();
        getCheapCart().findElement(quantityInput).sendKeys(String.valueOf(quantity));
        click(updateCard);
        waitUntilIsElementNotEqualValue(totalPrice, totalPriceBeforeUpdate);
    }

    public void changeQuantityAtExpensiveProduct(int quantity){
        String totalPriceBeforeUpdate = getTotalPrice();
        getExpensiveCart().findElement(quantityInput).sendKeys(String.valueOf(quantity));
        click(updateCard);
        waitUntilIsElementNotEqualValue(totalPrice, totalPriceBeforeUpdate);
    }

    public void clickToProceedButton(){
        click(proceedByCheckOutButton);
    }
}
