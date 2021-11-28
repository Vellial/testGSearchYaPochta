package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchPage {

    private WebDriver driver;

    /**
     * определение локатора поля ввода поиска
     */
    @FindBy(css = "input[title='Поиск']")
    private WebElement searchField;

    /**
     * определение локатора кнопки поиска
     */
    @FindBy(css = "input[value='Поиск в Google']")
    private WebElement searchBtn;

    public SearchPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    /**
     * метод для ввода поискового запроса
     */
    public void inputSearchText(String text) {
        searchField.sendKeys(text);
    }

    /**
     * метод для осуществления нажатия кнопки поиска
     */
    public void clickSearchBtn() {
        searchField.sendKeys(Keys.ENTER);
//        searchBtn.click();
    }

    public List<WebElement> getListOfUrls() {
        return driver.findElements(By.xpath("//div[@id='search']//a"));
    }

    public void checkMvideo(List<WebElement> listUrls) {
        boolean mvideoExists = false;
        for (WebElement url : listUrls) {
            String urlText = url.getText();
            if (urlText.contains("mvideo.ru")){
                mvideoExists = true;
            }
        }

        assertTrue(mvideoExists);
    }
}
