package com.Aimers.journalApp.service;

import com.Aimers.journalApp.api.response.WeatherResponse;
import com.Aimers.journalApp.cache.AppCache;
import com.Aimers.journalApp.constants.Placeholders;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class WeatherService {

    @Value("${weather.api.key}")
    private String apikey;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AppCache appCache;

    @Autowired
    private RedisService redisService;


    public WeatherResponse getWeather(String city) {
        WeatherResponse weatherResponse = redisService.get("Weather_of_" + city, WeatherResponse.class);
        if (weatherResponse != null) {
            log.info("📦 Fetched weather for '{}' from Redis", city);
            return weatherResponse;
        } else {
            String finalAPI = appCache.appCache.get(Placeholders.WEATHER_API.value()).replace(Placeholders.CITY.value(), city).replace(Placeholders.API_KEY.value(), apikey);
            ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);
            WeatherResponse body = response.getBody();
            if (body != null){
                redisService.set("Weather_of_" + city, body, 900L);
            }
            log.info("Fetching weather for '{}' from API", city);
            return body;
        }
    }
}
