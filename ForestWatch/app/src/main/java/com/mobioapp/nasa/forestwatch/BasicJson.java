package com.mobioapp.nasa.forestwatch;

/**
 * Created by Rohan on 4/9/2015.
 */
public class BasicJson {


    private String title;
    private String description;
    private String latitude;
    private String longitude;

    private String img;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public BasicJson(){



    }

    public BasicJson(String title, String description, String latitude, String longitude, String img) {
        this.title = title;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.img = img;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "BasicJson{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}
