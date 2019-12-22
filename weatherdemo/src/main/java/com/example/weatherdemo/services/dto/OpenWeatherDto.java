package com.example.weatherdemo.services.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


/*
*
* {
* }*/
@Data
public class OpenWeatherDto {
    @JsonProperty("main")
    private MainDto main;

    public MainDto getMain() {
        return main;
    }

    public void setMain(MainDto main) {
        this.main = main;
    }

    @Data
    public static class MainDto{
        @JsonProperty("temp")
        private String temperature;

        public String getTemperature() {
            return temperature;
        }

        public void setTemperature(String temperature) {
            this.temperature = temperature;
        }
    }
}
