package com.kerer.weatherapp.util;

/**
 * Date: 09.02.17
 * Time: 2:58
 *
 * @author Ivan Kerer
 */

public class IconsUtil {

    public String getFromText(String text) {
        switch (text){
            case "clear-day":
                return "A";
            case "clear-night":
                return "B";
            case "rain":
                return "R";
            case "snow":
                return "W";
            case "sleet":
                return "X";
            case "wind":
                return "a";
            case "fog":
                return "N";
            case "cloudy":
                return "P";
            case "partly-cloudy-day":
                return "O";
            case "partly-cloudy-night":
                return "D";
            case "thunderstorm":
                return "U";
            case "tornado":
                return "e";
            case "hail":
                return "s";
        }
        return "p";
    }
}
