package com.example.weatherdemo.services;

import com.example.weatherdemo.model.Weather;

import java.util.List;

public interface WeatherService {
    List<Weather> getWeather();
}
