package frontend.tests;

import backend.pojo.SeriesRequest;
import frontend.pages.MainPage;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static backend.tests.utils.TestHelper.deleteAllSeries;
import static backend.tests.utils.TestHelper.fillSeriesList;
import static frontend.config.DriverManager.getDriver;
import static io.qameta.allure.SeverityLevel.BLOCKER;

public class SeriesUITests extends TestBase {

    @BeforeEach
    void classSetup() {
        deleteAllSeries();
        getDriver().navigate().refresh();
    }

    @Severity(BLOCKER)
    @Description("User should try add two series with te same title and see only one series on the list")
    @Test
    void shouldAddOnlyOneSeriesWithTheSameTitleToEmptyList() {
        //when
        String title = "Ozark";
        String platform = "Netflix";

        //then
        new MainPage()
                .typeIntoTitleInput(title)
                .typeIntoPlatformInput(platform)
                .clickAddButton()
                .assertThatButtonAlertContainsMessage(
                        "TV series added",
                        "Reload page to see updated list"
                )
                .clickReloadPageButton()
                .assertThatAddedSeriesIsOnList(title)
                .assertThatSeriesListSizeIs(1)
                .typeIntoTitleInput(title)
                .typeIntoPlatformInput(platform)
                .clickAddButton()
                .assertThatButtonAlertContainsMessage(
                        "TV series not added",
                        "TV series already exist"
                )
                .clickReloadPageButton()
                .assertThatSeriesListSizeIs(1);
    }

    @Severity(BLOCKER)
    @Description("User should not add series to full list and see failed alert")
    @Test
    void shouldNotAddSeriesToFullList() {
        //when
        fillSeriesList();

        String title = "Ozark";
        String platform = "Netflix";

        //then
        new MainPage()
                .typeIntoTitleInput(title)
                .typeIntoPlatformInput(platform)
                .clickAddButton()
                .assertThatButtonAlertContainsMessage(
                        "TV series not added",
                        "Maximum number of TV series is reached"
                );
    }

    @Severity(BLOCKER)
    @Description("User should remove series from list with one element, see success alert and empty list")
    @Test
    void shouldRemoveSeriesFromListWithOneElement() {
        //when
        favoriteSeriesService.addSeries(new SeriesRequest("Friends", "Netflix"));
        String seriesId = String.valueOf(favoriteSeriesService.getAllSeries().get(0).getId());

        //then
        new MainPage()
                .typeIntoIdInput(seriesId)
                .clickRemoveButton()
                .assertThatButtonAlertContainsMessage(
                        "TV series removed",
                        "Reload page to see updated list"
                )
                .clickReloadPageButton()
                .assertThatSeriesListSizeIs(0);

    }

    @Severity(BLOCKER)
    @Description("User should not remove series and see failed alert and empty list")
    @Test
    void shouldNotRemoveSeriesFromEmptyList() {
        //given
        String seriesId = "1";

        //then
        new MainPage()
                .typeIntoIdInput(seriesId)
                .clickRemoveButton()
                .assertThatButtonAlertContainsMessage(
                        "TV series not removed",
                        "Not found TV series with this ID"
                );
    }
}
