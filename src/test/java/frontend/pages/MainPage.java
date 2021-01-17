package frontend.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static frontend.config.DriverManager.getDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.openqa.selenium.support.PageFactory.initElements;

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

    public MainPage typeIntoTitleInput(String title) {
        titleInput.sendKeys(title);
        return this;
    }

    public MainPage typeIntoPlatformInput(String platform) {
        platformInput.sendKeys(platform);
        return this;
    }

    public MainPage typeIntoIdInput(String id){
        idInput.sendKeys(id);
        return this;
    }

    public MainPage clickAddButton() {
        addButton.click();
        return this;
    }

    public MainPage clickReloadPageButton() {
        reloadPageButton.click();
        return this;
    }

    public MainPage clickRemoveButton() {
        removeButton.click();
        return this;
    }

    public MainPage assertThatButtonAlertContainsMessage(String message) {
        WebElement buttonAlert = getDriver().findElement(By.className("series-alert"));
        assertTrue(buttonAlert.getText().contains(message), message + " is not expected message");
        return this;
    }

    public MainPage assertThatAddedSeriesIsOnList(String title) {
        WebElement firstItem = getDriver().findElements(By.className("list-group-item")).get(0);
        assertTrue(firstItem.getText().contains(title), title + " is not expected title");
        return this;
    }

    public MainPage assertThatSeriesListIsEmpty() {
        List<WebElement> list = getDriver().findElements(By.className("list-group-item"));
        assertEquals(0, list.size(), "List is not empty");
        return this;
    }
}
