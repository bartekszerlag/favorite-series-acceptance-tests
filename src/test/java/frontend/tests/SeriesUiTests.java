package frontend.tests;

import backend.pojo.SeriesRequest;
import frontend.pages.MainPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static backend.tests.utils.TestHelper.deleteAllSeries;
import static backend.tests.utils.TestHelper.fillSeriesList;
import static frontend.config.DriverManager.getDriver;

public class SeriesUiTests extends TestBase {

    @BeforeEach
    void classSetup() {
        deleteAllSeries();
        getDriver().navigate().refresh();
    }

    @DisplayName("User should add series to empty list, see success alert and new series on the list")
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
                .typeIntoTitleInput(title)
                .typeIntoPlatformInput(platform)
                .clickAddButton()
                .assertThatButtonAlertContainsMessage(
                        "TV series not added",
                        "TV series already exist"
                )
                .assertThatSeriesListSizeIs(1);
    }

    @DisplayName("User should add series to full list and see failed alert")
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

    @DisplayName("User should remove series from list with one element, see success alert and empty list")
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

    @DisplayName("User should not remove series and see failed alert and empty list")
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