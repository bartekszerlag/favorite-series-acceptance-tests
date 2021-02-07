package frontend.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
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

    public MainPage typeIntoTitleInput(String title) {
        titleInput.sendKeys(title);
        logger.info(format("### User typed title: %s ###", title));
        return this;
    }

    public MainPage typeIntoPlatformInput(String platform) {
        platformInput.sendKeys(platform);
        logger.info(format("### User typed platform: %s ###", platform));
        return this;
    }

    public MainPage typeIntoIdInput(String id) {
        idInput.sendKeys(id);
        logger.info(format("### User typed id: %s ###", id));
        return this;
    }

    public MainPage clickAddButton() {
        addButton.click();
        logger.info("### User clicked Add button ###");
        return this;
    }

    public MainPage clickReloadPageButton() {
        reloadPageButton.click();
        logger.info("### User clicked Reload Page button ###");
        return this;
    }

    public MainPage clickRemoveButton() {
        removeButton.click();
        logger.info("### User clicked Remove button ###");
        return this;
    }

    public MainPage assertThatButtonAlertContainsMessage(String... messages) {
        WebElement buttonAlert = getDriver().findElement(By.className("series-alert"));
        Arrays.stream(messages).forEach(message -> {
            assertTrue(buttonAlert.getText().contains(message), format("%s is not expected message", message));
            logger.info(format("### SUCCESS: button alert contains proper text: %s ###", message));
        });
        return this;
    }

    public MainPage assertThatAddedSeriesIsOnList(String title) {
        WebElement firstItem = getDriver().findElements(By.className("list-group-item")).get(0);
        assertTrue(firstItem.getText().contains(title), format("%s is not expected title", title));
        logger.info(format("### SUCCESS: list contains TV series: %s ###", title));
        return this;
    }

    public MainPage assertThatSeriesListSizeIs(int size) {
        List<WebElement> list = getDriver().findElements(By.className("list-group-item"));
        assertEquals(size, list.size());
        logger.info(format("### SUCCESS: list size is %d ###", size));
        return this;
    }
}
