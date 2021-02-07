package backend.pojo;

public class SeriesRequest {

    private String title;
    private String platform;

    public SeriesRequest(String title, String platform) {
        this.title = title;
        this.platform = platform;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }
}
