package com.example.weatherapp.controller;

import com.example.weatherapp.model.WeatherData;
import com.example.weatherapp.service.WeatherService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    private final WeatherService service;

    public WeatherController(WeatherService service) {
        this.service = service;
    }

    @PostMapping("/fetch/{city}")
    public WeatherData fetchWeather(@PathVariable String city) {
        return service.fetchAndStore(city);
    }

    @GetMapping("/all")
    public List<WeatherData> getAll(
            @RequestParam(required = false) Double minTemp
    ) {
        return service.getAll(minTemp);
    }

    @GetMapping("/{city}")
    public WeatherData getCity(@PathVariable String city) {
        return service.getByCity(city);
    }
}
