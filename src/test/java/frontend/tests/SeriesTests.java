package frontend.tests;

import frontend.pages.MainPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static backend.tests.data.Series.generateSeries;
import static backend.tests.utils.TestHelper.deleteAllSeries;
import static frontend.config.DriverManager.getDriver;

public class SeriesTests extends TestBase {

    @BeforeEach
    void classSetup() {
        deleteAllSeries();
        refreshPage();
    }

    @DisplayName("User should add series, see success alert and new series on the list")
    @Test
    void shouldAddSeries() {
        //when
        String title = "Ozark";
        String platform = "Netflix";

        //then
        new MainPage()
                .typeIntoTitleInput(title)
                .typeIntoPlatformInput(platform)
                .clickAddButton()
                .assertThatButtonAlertContainsMessage("TV series added")
                .clickReloadPageButton()
                .assertThatAddedSeriesIsOnList(title);

    }

    @DisplayName("User should remove series, see success alert and empty list")
    @Test
    void shouldRemoveSeries() {
        //when
        favoriteSeriesService.addSeries(generateSeries());
        String seriesId = String.valueOf(favoriteSeriesService.getAllSeries().get(0).getId());
        refreshPage();

        //then
        new MainPage()
                .typeIntoIdInput(seriesId)
                .clickRemoveButton()
                .assertThatButtonAlertContainsMessage("TV series removed")
                .clickReloadPageButton()
                .assertThatSeriesListIsEmpty();

    }

    private void refreshPage() {
        getDriver().navigate().refresh();
    }
}
