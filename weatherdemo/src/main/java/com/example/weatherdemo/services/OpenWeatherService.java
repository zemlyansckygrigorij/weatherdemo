package com.example.weatherdemo.services;

import com.example.weatherdemo.model.Weather;
import com.example.weatherdemo.services.dto.OpenWeatherDto;
import com.google.gson.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

//@RequiredArgsConstructor
@Service
public class OpenWeatherService implements WeatherService{
    private final RestTemplate restTemplate;
    private static Logger logger = LoggerFactory.getLogger(OpenWeatherService.class);
    @Value("${app.openweather-api-key}")
    private String apiKey;

    @Value("${app.city-name}")
    private String cityName;

    public OpenWeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    private final Gson gson = new GsonBuilder().registerTypeAdapter(Weather.class, new JsonDeserializer<Weather>(){
        @Override
        public Weather deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            JsonObject main = jsonElement.getAsJsonObject().getAsJsonObject("main");
            return new Weather("OpenWeatherMap", cityName, main.get("temp").getAsString());
        }
    }).create();

    public List<Weather> gWeather() {
        final String url = String.format("https://api.openweathermap.org/data/2.5/weather?" +
                "q=%s&units=metric&lang=ru&appid=%s",cityName,apiKey) ;
        final OpenWeatherDto dto  = restTemplate.getForObject(url, OpenWeatherDto.class);
         return Collections.singletonList(toModel(dto));

    }

    public List<Weather> getWeather() {
        logger.info("Open performing request...");
        String url = String.format("https://api.openweathermap.org/data/2.5/weather?q=%s&units=metric&lang=ru&appid=%s", cityName, apiKey);
        String weatherString = restTemplate.getForObject(url, String.class);
        logger.info("Open request done.");
        return Collections.singletonList(gson.fromJson(weatherString, Weather.class));
    }
    private Weather toModel(OpenWeatherDto dto){
        return new Weather("Openweather", cityName,dto.getMain().getTemperature());
    }
}





















