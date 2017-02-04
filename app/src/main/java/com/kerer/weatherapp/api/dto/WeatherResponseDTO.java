
package com.kerer.weatherapp.api.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WeatherResponseDTO {

    @SerializedName("latitude")
    @Expose
    private Double latitude;
    @SerializedName("longitude")
    @Expose
    private Double longitude;
    @SerializedName("timezone")
    @Expose
    private String timezone;
    @SerializedName("offset")
    @Expose
    private Integer offset;
    @SerializedName("currently")
    @Expose
    private CurrentlyDTO currentlyDTO;
    @SerializedName("daily")
    @Expose
    private DailyDTO dailyDTO;

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public CurrentlyDTO getCurrentlyDTO() {
        return currentlyDTO;
    }

    public void setCurrentlyDTO(CurrentlyDTO currentlyDTO) {
        this.currentlyDTO = currentlyDTO;
    }

    public DailyDTO getDailyDTO() {
        return dailyDTO;
    }

    public void setDailyDTO(DailyDTO dailyDTO) {
        this.dailyDTO = dailyDTO;
    }

}
