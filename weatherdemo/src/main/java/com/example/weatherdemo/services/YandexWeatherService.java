package com.example.weatherdemo.services;

import com.example.weatherdemo.model.Weather;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class YandexWeatherService implements WeatherService {
private static Logger logger = LoggerFactory.getLogger(YandexWeatherService.class);
    @Value("${app.city-name}")
    private String cityName;

    private static final List<Weather> EMPTY_LIST = new ArrayList<>();

    @SneakyThrows
    @Override
    public List<Weather> getWeather() {
        try {
            logger.info("Yandex performing request... ");
            Thread.sleep(1000);
            Document doc = Jsoup.connect(String.format("https://yandex.ru/pogoda/%s",cityName)).get();
            Element tempValue = doc.selectFirst(".temp__value");
            logger.info("Yandex request done... ");
            List<Weather> list = new ArrayList<>();
            list.add(new Weather("YandexWeather", cityName, tempValue.text()));
            return list;
            // return List.of(new Weather("YandexWeather", cityName, tempValue.text()));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
            return EMPTY_LIST;
        }
    }
}







































