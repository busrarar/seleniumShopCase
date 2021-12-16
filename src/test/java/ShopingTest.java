import org.testng.annotations.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class ShopingTest extends Driver{

    HomePage homePage = new HomePage();
    //String homePageTitle = homePage.getPageTtitle();
    ProductPage productPage = new ProductPage();
    NavigationBar navigationBar = new NavigationBar();
    BasketPage basketPage = new BasketPage();


    @Test
    public void testDeneme() {

        homePage.openHomePage();
        boolean isHomepageAvailable = homePage.checkIsHomePageAvailable();
        assertThat("when user tries to get home page", isHomepageAvailable, is(equalTo(true)));
        homePage.clickShopNow();

        String productPageTitle = productPage.getProductPageTitle();
        assertThat("when user tries to get product page", productPageTitle,is(equalTo("Products")));
        String productTitle = productPage.addProductToBasket(0);
        //sepetteki ürün sayısı doğru mu
        navigationBar.goBasket();
        String basketProductTitle = basketPage.getBasketProductTitle();
        assertThat("When user tries to add product to basket", productTitle, is(equalTo(basketProductTitle)));

        float beforeUpdatePrice = Float.parseFloat(basketPage.getTotalPrice().trim().replace("₺ ", "").replace(",","."));
        basketPage.changeQuantity(3);
        float afterUpdate = Float.parseFloat(basketPage.getTotalPrice().trim().replace("₺ ","").replace(".", "").replace(",", "."));
        boolean isPriceCorrect = beforeUpdatePrice*3 == afterUpdate;
        assertThat("When user tries to update product quantity price must will be change", isPriceCorrect, is(true));

        basketPage.removeProduct();
        String emptyBasketText = basketPage.getEmptyBasketText();
        assertThat("when user delete to product",emptyBasketText, is(equalTo("Your cart is currently empty.")));
    }
    @Test
    public void testSenaryo2(){
        homePage.openHomePage();
       homePage.scrollTillEnd();
       String contactInfoText = homePage.getContactInfoText();
       assertThat("when user tries to get contact ınfo",contactInfoText,is(equalTo("CONTACT INFO")));
       homePage.goToHomePageTop();
       String topTextx = homePage.getHomePageTopText(); //değişken tanımladım
       assertThat("when user tries go to homepage top",topTextx,is(equalTo("This site is for testing use only. ")));
    }
    @Test
    public void testSenaryo3(){
        homePage.openHomePage();
        homePage.clickMenuShop();
        String productPageTitle = productPage.getProductPageTitle();
        assertThat("when user tries to get product page", productPageTitle,is(equalTo("Products")));
        productPage.clickSortingBoxAndSelectExpectedItem();
        List<String> products = productPage.addMultipleProductToBasket(3);
        productPage.getViewCard();
        List<String> basketProducts = basketPage.getBasketProductTitles(3);
        assertThat("when user tries to add multiple product", products, is(equalTo(basketProducts)));

        basketPage.changeQuantityAtCheapProduct(4);
        basketPage.changeQuantityAtExpensiveProduct(2);
        basketPage.clickToProceedButton();
    }
}
