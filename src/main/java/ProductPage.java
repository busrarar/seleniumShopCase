import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ProductPage extends BaseModel {

    public static By productText = By.cssSelector(".tg-page-header__title");
    public static By addToBasket = By.linkText("Add to cart");
    public static By productTitle = By.cssSelector(".woocommerce-loop-product__title");
    public static By sortingSelectBox = By.cssSelector("[aria-label='Shop order']");
    public static By sortByLatestOption = By.cssSelector("[value='date']");
    public static By viewCart = By.linkText("View cart");
    public String getProductPageTitle(){
        return getElementText(productText);
    }

    public String addProductToBasket(int index){
        getElements(addToBasket).get(index).click();
        return getElements(productTitle).get(0).getText();
    }

    public void clickSortingBoxAndSelectExpectedItem(){
        click(sortingSelectBox);
        click(sortByLatestOption);
    }

    public List<String> addMultipleProductToBasket(int count) {
        List<WebElement> elements = getElements(addToBasket);
        List<String> productTitles = new ArrayList<String>();;
        for (int i = 0; i < count; i++) {
            productTitles.add(getElements(productTitle).get(i).getText());
            elements.get(i).click();
        }
        waitUntilIsElementSizeExpected(viewCart, count);
        return productTitles;
    }

    public void getViewCard(){
        click(viewCart);
    }
}
