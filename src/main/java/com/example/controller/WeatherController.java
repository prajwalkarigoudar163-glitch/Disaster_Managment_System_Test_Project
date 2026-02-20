package com.example.controller;

import com.example.external.weatherservice;
import org.springframework.web.bind.annotation.*;
import com.example.DTO.*;

@RestController
@RequestMapping("/api/weather")
@CrossOrigin(origins = "*")
public class WeatherController {

    private final weatherservice weatherservice;

    public WeatherController(weatherservice weatherservice) {
        this.weatherservice = weatherservice;
    }

    @GetMapping("/{city}")
    public WeatherResponse getWeather(@PathVariable String city){
        return weatherservice.getWeather(city);
    }

}