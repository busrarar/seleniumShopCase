import org.openqa.selenium.By;

public class NavigationBar extends BaseModel {

    public static By basketIcon = By.cssSelector("[aria-expanded] .tg-icon-shopping-cart");

    public void goBasket(){
        click(basketIcon);
    }
}
