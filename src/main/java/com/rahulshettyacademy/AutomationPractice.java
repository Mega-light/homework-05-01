package com.rahulshettyacademy;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.*;

public class AutomationPractice {

    public static void main(String[] args) {
        AutomationPractice automationPractice = new AutomationPractice();
        automationPractice.run(new ChromeDriver());
        automationPractice.run(new FirefoxDriver());
        automationPractice.run(new EdgeDriver());
    }

    public AutomationPractice() {
        loadWebDriverProperties();
    }

    /**
     * Load the vendors' driver into System's properties.
     */
    private void loadWebDriverProperties() {
        Properties configProp = System.getProperties();
        try (InputStream in = AutomationPractice.class.getClassLoader().getResourceAsStream("webdriver.properties")) {
            configProp.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run(@NotNull WebDriver driver) {
        driver.manage().window().maximize();
        String baseUrl = "https://rahulshettyacademy.com/AutomationPractice/";
        driver.get(baseUrl);
        runRadioButtons(driver);
        System.out.println();
        runInput(driver);
        System.out.println();
        runSelect(driver);
        System.out.println();
        runButtons(driver);
        System.out.println();
        runTabs(driver);
        System.out.println();
        driver.quit();
    }

    private void runTabs(@NotNull WebDriver driver) {
//        a) Select "Open Tab" button
//        b) Switch back to parent tab - Switch must be done using “SwitchTo()”
//        c) Print the current number of tabs
        WebElement opentab = driver.findElement(By.id("opentab"));
        opentab.click();
        Set<String> windowHandles = driver.getWindowHandles();
        Iterator<String> iterator = windowHandles.iterator();
        driver.switchTo().window(iterator.next());
        System.out.println("Number of tabs: " + windowHandles.size());
    }

    private void runButtons(@NotNull WebDriver driver) {
//        Buttons: [Home | Practice | Login | Signup]
//        k. Click on each header button (One at the time, you can click it and then go back)
//        l. Print in the console the "text" of each header button and concat "Different URL"
//        if the button redirects to a different URL or add "Same URL" if the button doesn't redirect to a different URL.
//        Example:
//        "Different URL - Btn: Home"
//        "Same URL - Btn: Practice"
//        "Same URL - Btn: Login"
//        "Same URL - Btn: Signup"
        String cssSelector = "header > div > *";
        By by = By.cssSelector(cssSelector);
        int buttonsSize = driver.findElements(by).size();
        String automationPracticeURL = driver.getCurrentUrl();

        for (int i = 1; i <= buttonsSize; i++) {
            WebElement button = driver.findElement(By.cssSelector(cssSelector + ":nth-child(" + i + ")"));
            String buttonText = button.getText();
            button.click();
            String msg;
            if (driver.getCurrentUrl().equals(automationPracticeURL)) {
                msg = "Same URL - Btn: " + buttonText;
            } else {
                driver.navigate().back();
                msg = "Different URL - Btn: " + buttonText;
            }
            System.out.println(msg);
        }
    }

    private void runSelect(@NotNull WebDriver driver) {
//        Select: "Dropdown Example"
//        g. Use "Select" Java Object and Select option 2
//        h. Print in the console the text value (Expected result:"Option2")
//        i. Use "WebElement" Java Object and simulate the Select behavior and select option 3
//        j. Print in the console the text value (Expected result:"Option3")
        String id = "dropdown-class-example";
        WebElement dropdown = driver.findElement(By.id(id));
        Select select = new Select(dropdown);
        select.selectByIndex(2);
        System.out.println(select.getFirstSelectedOption().getText());

        By by = By.cssSelector("option[value='option3']");
        WebElement optionSelected = driver.findElement(by);
        optionSelected.click();
        System.out.println(optionSelected.getText());

    }

    private void runInput(@NotNull WebDriver driver) {
//        d. Print in the console the place holder of "Suggestion Class Example"
//        input (Expected result: "Type to Select Countries")
//        e. Add the text "El Sal" and select the first option
//        f. Print in the console the text value (Expected result: "El Salvador")
        WebElement autocomplete = driver.findElement(By.id("autocomplete"));
        System.out.println(autocomplete.getAttribute("placeholder"));
        autocomplete.sendKeys("El Sal");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1L));
        By by = By.cssSelector("div[id^='ui-id-']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        WebElement option = driver.findElement(by);
        option.click();
        String text = autocomplete.getAttribute("value");
        System.out.println(text);
    }

    private void runRadioButtons(@NotNull WebDriver driver) {
//        Generate a random between 1-3 (Take note of each random value is equal to
//        each radioButton on the web site) then the result should be used on the
//        following exercises.
//
//        a. Print in the console the random result
//        b. Click over radio button (take note of random result)
//        c. Print in the console the SELECTED and NOT SELECTED radioButton
//        (the text should be taken of Web Element)
//
//        Example:
//        "NOT Selected RadioButton: Radio1"
//        "Selected RadioButton: Radio2"
//        "NOT Selected RadioButton: Radio3"
        List<WebElement> radioButtons = driver.findElements(By.name("radioButton"));
        Random random = new Random();
        int randomIndex = random.nextInt(radioButtons.size());
        System.out.println("Random number: " + (randomIndex + 1));
        WebElement radioButton = radioButtons.get(randomIndex);
        radioButton.click();
        for (WebElement rB : radioButtons) {
            WebElement label = driver.findElement(By.cssSelector("label[for='" + rB.getAttribute("value") + "']"));
            String selected = (rB.isSelected() ? "" : "NOT ");
            System.out.println(selected + "Selected RadioButton: " + label.getText());
        }
    }

}
