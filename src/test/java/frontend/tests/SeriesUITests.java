package frontend.tests;

import frontend.pages.MainPage;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static backend.tests.data.Series.generateSeries;
import static backend.tests.utils.TestHelper.deleteAllSeries;
import static frontend.config.DriverManager.getDriver;
import static io.qameta.allure.SeverityLevel.BLOCKER;

public class SeriesUITests extends TestBase {

    @BeforeEach
    void classSetup() {
        deleteAllSeries();
        refreshPage();
    }

    @Severity(BLOCKER)
    @Description("User should add series, see success alert and new series on the list")
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
                .assertThatAddedSeriesIsOnList(title)
                .assertThatSeriesListSizeIsEqualTo(1);
    }

    @Severity(BLOCKER)
    @Description("User should remove series, see success alert and empty list")
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
                .assertThatSeriesListSizeIsEqualTo(0);
    }

    private void refreshPage() {
        getDriver().navigate().refresh();
    }
}
