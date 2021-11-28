package org.example;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class LoginTest {
    public static LoginPage loginPage;
    public static WebDriver driver;

    /**
     * осуществление первоначальной настройки
     */
    @BeforeAll
    public static void setup() {
        //определение пути до драйвера и его настройка
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
        //создание экземпляра драйвера
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        //окно разворачивается на полный экран
        driver.manage().window().maximize();
        //задержка на выполнение теста = 10 сек.
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //получение ссылки на страницу входа из файла настроек
        driver.get(ConfProperties.getProperty("loginpage"));
    }

    /**
     * тестовый метод для осуществления аутентификации
     */
    @Test
    public void loginTest() throws InterruptedException {
        //получение доступа к методам класса LoginPage для взаимодействия с элементами страницы
        //значение login/password берутся из файла настроек по аналогии с chromedriver
        //и loginpage

        String login = ConfProperties.getProperty("login");

        loginPage.clickLoginBtn();
        //вводим логин
        loginPage.inputLogin(login);
        //нажимаем кнопку входа
        loginPage.clickLoginBtn2();
        //вводим пароль
        loginPage.inputPasswd(ConfProperties.getProperty("password"));
        //нажимаем кнопку входа
        loginPage.clickLoginBtn2();

        try {
            WebElement checkLogin = driver.findElement(By.xpath("//*[contains(text(),'" + login + "')]"));
        } catch (NoSuchElementException e) {
            System.out.println("Element not found");
            fail();
        }

    }

    /*
     * осуществление выхода из аккаунта с последующим закрытием окна браузера
     */
    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}