import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class HomePage extends BaseModel {
    public static By menuShopButton = By.linkText("Shop");
    public static By textFromTop = By.cssSelector("strong");
    public static  By iconArrowUp =By.cssSelector(".tg-icon-arrow-up");
    public static By contactInfo = By.xpath("//*[@class='widget-title' and text()='CONTACT INFO']");

    public static By shopNowButton = By.xpath("//*[@class='elementor-button-text' and text()='SHOP NOW']");
    public static By homePageButton = By.id("menu-item-833");
    public void clickShopNow(){ click(shopNowButton);
    }



    public boolean checkIsHomePageAvailable(){
        boolean isUrlOk = checkUrl("https://training.qastorming.com/");
        return isUrlOk&&checkColour(homePageButton,"rgba(0, 0, 0, 1)");
    }

    public void openHomePage(){
        Cookie ck = new Cookie("store_noticefe68900dee1653cf2d0662bb68d31594","hidden");
        webDriver.get("https://training.qastorming.com/");
        setCookie(ck);
        webDriver.navigate().refresh();

        String homePageTitle = getPageTtitle();
        assertThat("when user tries to get homepage title",homePageTitle,is(equalTo("Home - Sample Shop")));

    }
    public void scrollTillEnd(){
        scrollToBottomOfPage();

    }
    public String getContactInfoText (){
        return getElementText(contactInfo);
    }
    public void goToHomePageTop (){
        click(iconArrowUp);
    }
    public String getHomePageTopText (){
        return getElementText(textFromTop);
    }
    public void clickMenuShop(){
        click(menuShopButton);
    }
}
