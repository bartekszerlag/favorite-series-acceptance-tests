package frontend.pages;

import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Arrays;
import java.util.List;

import static frontend.config.DriverManager.getDriver;
import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.openqa.selenium.support.PageFactory.initElements;

@Slf4j
public class MainPage {

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
        log.info(format("### User typed title: %s ###", title));
        return this;
    }

    @Step("User types platform")
    public MainPage typeIntoPlatformInput(String platform) {
        platformInput.sendKeys(platform);
        log.info(format("### User typed platform: %s ###", platform));
        return this;
    }

    @Step("User types id")
    public MainPage typeIntoIdInput(String id) {
        idInput.sendKeys(id);
        log.info(format("### User typed id: %s ###", id));
        return this;
    }

    @Step("User clicks add button")
    public MainPage clickAddButton() {
        addButton.click();
        log.info("### User clicked Add button ###");
        return this;
    }

    @Step("User clicks reload page button")
    public MainPage clickReloadPageButton() {
        reloadPageButton.click();
        log.info("### User clicked Reload Page button ###");
        return this;
    }

    @Step("User clicks remove button")
    public MainPage clickRemoveButton() {
        removeButton.click();
        log.info("### User clicked Remove button ###");
        return this;
    }

    @Step("Button alert assert")
    public MainPage assertThatButtonAlertContainsMessage(String... messages) {
        WebElement buttonAlert = getDriver().findElement(By.className("series-alert"));
        Arrays.stream(messages).forEach(message -> {
            assertTrue(buttonAlert.getText().contains(message), format("%s is not expected message", message));
            log.info(format("### SUCCESS: button alert contains proper text: %s ###", message));
        });
        return this;
    }

    @Step("Added series assert")
    public MainPage assertThatAddedSeriesIsOnList(String title) {
        WebElement firstItem = getDriver().findElements(By.className("list-group-item")).get(0);
        assertTrue(firstItem.getText().contains(title), format("%s is not expected title", title));
        log.info(format("### SUCCESS: series list contains TV series: %s ###", title));
        return this;
    }

    @Step("Series list size assert")
    public MainPage assertThatSeriesListSizeIs(int size) {
        List<WebElement> list = getDriver().findElements(By.className("list-group-item"));
        assertEquals(size, list.size());
        log.info(format("### SUCCESS: list size is %d ###", size));
        return this;
    }
}