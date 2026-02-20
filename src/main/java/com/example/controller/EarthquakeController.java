package com.example.controller;

import com.example.DTO.EarthquakeResponse;
import com.example.external.EarthquakeService;

import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/earthquake")
@CrossOrigin(origins = "*")
public class EarthquakeController {

    private final EarthquakeService earthquakeService;

    public EarthquakeController(EarthquakeService earthquakeService) {
        this.earthquakeService = earthquakeService;
    }

    @GetMapping
    public List<EarthquakeResponse> getEarthquakes(){
        return earthquakeService.getEarthquakes();

    }
}