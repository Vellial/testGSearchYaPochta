package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchTest {

    public static WebDriver driver;
    public static SearchPage searchPage;

    /**
     * осуществление первоначальной настройки
     */
    @BeforeAll
    public static void setup() throws InterruptedException {
        //определение пути до драйвера и его настройка
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
        //создание экземпляра драйвера
        driver = new ChromeDriver();
        searchPage = new SearchPage(driver);
        //окно разворачивается на полный экран
        driver.manage().window().maximize();
        //ожидание = 10 сек.
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //переходим на страницу поиска Google из файла настроек
        driver.get(ConfProperties.getProperty("searchpage"));
    }

    @Test
    public void searchText() throws InterruptedException {
        searchPage.inputSearchText("купить кофемашину bork c804");
        Thread.sleep(5000);
        searchPage.clickSearchBtn();
        Thread.sleep(5000);
        //добавить проверки, что результатов больше 10
        //и что среди них есть mvideo.ru

        List<WebElement> listUrls = searchPage.getListOfUrls();
        assertTrue(listUrls.size() > 10);

        searchPage.checkMvideo(listUrls);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

}
