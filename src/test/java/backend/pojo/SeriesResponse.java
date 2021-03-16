package backend.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SeriesResponse {

    private Integer id;
    private String title;
    private Double imdbRating;
    private Platform platform;
}