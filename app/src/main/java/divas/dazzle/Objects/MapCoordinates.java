package divas.dazzle.Objects;

/**
 * Created by Wiseman on 2018-02-06.
 */

public class MapCoordinates {
    private String latitude;
    private String longitude;
    private String title;
    private String initial;
    private String surname;

    public MapCoordinates(String latitude, String longitude, String title, String initial, String surname) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.title = title;
        this.initial = initial;
        this.surname = surname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInitial() {
        return initial;
    }

    public void setInitial(String initial) {
        this.initial = initial;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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

}
