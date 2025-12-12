package com.example.weatherapp.service;

import com.example.weatherapp.exception.ApiException;
import com.example.weatherapp.model.WeatherData;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class WeatherService {
	  @Value("${openweather.api.key}")
	private String API_KEY;
    private Map<String, WeatherData> cache = new HashMap<>();
    RestTemplate restTemplate = new RestTemplate();

   
    public WeatherData fetchAndStore(String city) {

        try {

            String url = "https://api.openweathermap.org/data/2.5/weather?q="+ city + "&appid=" + API_KEY + "&units=metric";
            Map response = restTemplate.getForObject(url, Map.class);

            if (response == null || !response.containsKey("main")) {
                throw new ApiException("Weather data not available.");
            }

            Map main = (Map) response.get("main");

            List weatherList = (List) response.get("weather");
            Map weather = (Map) weatherList.get(0);

            WeatherData weatherData = new WeatherData(
                    city,
                    Double.valueOf(main.get("temp").toString()),
                    Integer.valueOf(main.get("humidity").toString()),
                    weather.get("description").toString()
            );
            cache.put(city.toLowerCase(), weatherData);
            return weatherData;
        } catch (Exception ex) {
            throw new ApiException("Could not fetch weather. Reason: " + ex.getMessage());
        }
    }

    public List<WeatherData> getAll(Double minTemp) {
        List<WeatherData> list = new ArrayList<>(cache.values());
        if (minTemp != null) {
            list.removeIf(data -> data.getTemperature() < minTemp);
        }
        return list;
    }

    public WeatherData getByCity(String city) {
        WeatherData data = cache.get(city.toLowerCase());
        if (data == null) {
            throw new ApiException("City not found. Please fetch it first.");
        }
        return data;
    }
}
