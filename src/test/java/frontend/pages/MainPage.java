package frontend.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static frontend.config.DriverManager.getDriver;
import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.openqa.selenium.support.PageFactory.initElements;

public class MainPage {

    private final Logger logger = LoggerFactory.getLogger(MainPage.class);

    public MainPage() {
        initElements(getDriver(), this);
    }

    @FindBy(id = "title")
    private WebElement titleInput;

    @FindBy(id = "platform")
    private WebElement platformInput;

    @FindBy(id = "add")
    private WebElement addButton;

    @FindBy(id = "reload")
    private WebElement reloadPageButton;

    @FindBy(id = "id")
    private WebElement idInput;

    @FindBy(id = "remove")
    private WebElement removeButton;

    @Step("User types title")
    public MainPage typeIntoTitleInput(String title) {
        titleInput.sendKeys(title);
        logger.info(format("### User typed title: %s ###", title));
        return this;
    }

    @Step("User types platform")
    public MainPage typeIntoPlatformInput(String platform) {
        platformInput.sendKeys(platform);
        logger.info(format("### User typed platform: %s ###", platform));
        return this;
    }

    @Step("User types id")
    public MainPage typeIntoIdInput(String id){
        idInput.sendKeys(id);
        logger.info(format("### User typed id: %s ###", id));
        return this;
    }

    @Step("User clicks add button")
    public MainPage clickAddButton() {
        addButton.click();
        logger.info("### User clicked Add button ###");
        return this;
    }

    @Step("User clicks reload page button")
    public MainPage clickReloadPageButton() {
        reloadPageButton.click();
        logger.info("### User clicked Reload Page button ###");
        return this;
    }

    @Step("User clicks remove button")
    public MainPage clickRemoveButton() {
        removeButton.click();
        logger.info("### User clicked Remove button ###");
        return this;
    }

    @Step("Button alert assert")
    public MainPage assertThatButtonAlertContainsMessage(String message) {
        WebElement buttonAlert = getDriver().findElement(By.className("series-alert"));
        assertTrue(buttonAlert.getText().contains(message), format("%s is not expected message", message));
        logger.info(format("### SUCCESS: button alert contains proper text: %s ###", message));
        return this;
    }

    @Step("Added series assert")
    public MainPage assertThatAddedSeriesIsOnList(String title) {
        WebElement firstItem = getDriver().findElements(By.className("list-group-item")).get(0);
        assertTrue(firstItem.getText().contains(title), format("%s is not expected title", title));
        logger.info(format("### SUCCESS: series list contains TV series: %s ###", title));
        return this;
    }

    @Step("Series list size assert")
    public MainPage assertThatSeriesListSizeIsEqualTo(int number) {
        List<WebElement> seriesList = getDriver().findElements(By.className("list-group-item"));
        assertEquals(number, seriesList.size(), String.format("%d is not expected list size", number));
        logger.info(format("### SUCCESS: series list size is: %d ###", number));
        return this;
    }
}
