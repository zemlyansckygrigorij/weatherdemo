package com.example.weatherdemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

//@Data
//@AllArgsConstructor
public class Weather {
    private String source;
    private String city;
    private String temperature;

    public Weather(String source, String city, String temperature) {
        this.source = source;
        this.city = city;
        this.temperature = temperature;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }
}
