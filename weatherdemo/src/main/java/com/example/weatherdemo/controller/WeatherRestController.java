package com.example.weatherdemo.controller;

import com.example.weatherdemo.model.Weather;
import com.example.weatherdemo.services.WeatherService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WeatherRestController {
    private final WeatherService weatherService ;

    public WeatherRestController(@Qualifier("weatherAggregationService") WeatherService weatherService){
        this.weatherService = weatherService;
    }

    @GetMapping("api/weather")
    public List<Weather> getWeather(){
        return weatherService.gWeather();
    }

}
