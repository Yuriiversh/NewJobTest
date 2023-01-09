package tests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Result {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("image")
    private String image;
    @JsonProperty("imageType")
    private String imageType;

    public Result(Integer id, String title, String image, String imageType) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.imageType = imageType;
    }

    public Result() {
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public String getImageType() {
        return imageType;
    }
}