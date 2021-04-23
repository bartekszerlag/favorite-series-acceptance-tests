package frontend.tests;

import backend.pojo.SeriesRequest;
import frontend.pages.MainPage;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.SeverityLevel.BLOCKER;

public class SeriesUITests extends TestBase {

    @Severity(BLOCKER)
    @Description("User should add TV series and see it on the list")
    @Test
    void shouldAddNewSeries() {
        //given
        String title = "Ozark";

        //then
        new MainPage()
                .typeIntoTitleInput(title)
                .typeIntoPlatformInput("Netflix")
                .clickAddButton()
                .assertThatButtonAlertContainsMessage(
                        "TV series added",
                        "Reload page to see updated list"
                )
                .clickReloadPageButton()
                .assertThatAddedSeriesIsOnList(title)
                .assertThatSeriesListSizeIs(1);
    }

    @Severity(BLOCKER)
    @Description("User should remove series from the list, see success alert and empty list")
    @Test
    void shouldRemoveAddedSeries() {
        //given
        String seriesId = favoriteSeriesService
                .addSeries(new SeriesRequest("Friends", "Netflix"))
                .path("id")
                .toString();

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
}