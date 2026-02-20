package com.example.controller;

import com.example.external.NasaFloodService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/nasa-flood")
@CrossOrigin(origins = "*")
public class NasaFloodController {

    private final NasaFloodService nasaFloodService;

    public NasaFloodController(NasaFloodService nasaFloodService) {
        this.nasaFloodService = nasaFloodService;
    }

    @GetMapping
       public List<String> getFloods(){
        return nasaFloodService.getFloodEvents();
       }
    }
