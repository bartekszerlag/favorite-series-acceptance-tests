package backend.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SeriesRequest {

    private String title;
    private String platform;
}