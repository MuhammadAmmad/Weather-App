package com.kerer.weatherapp.api;

import com.kerer.weatherapp.api.dto.WeatherResponseDTO;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by ivan on 04.02.17.
 */


public interface DarkSkyApi {
    @GET("6fe704e6b0c6a571be927f4b3b1adf1c/48.292079,25.935837?exclude=minutely,hourly,alerts,flags")
    Observable<WeatherResponseDTO> getCityInfo(/*@Path("coordinates") String coordinates*/);
}
