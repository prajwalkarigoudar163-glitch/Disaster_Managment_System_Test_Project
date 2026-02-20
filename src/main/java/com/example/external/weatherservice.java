package com.example.external;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.DTO.WeatherResponse;

@Service
public class weatherservice {

    @Value("${openweather.api.key}")
    private String apiKey;

    private final String BASE_URL =
            "https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=metric";

    public WeatherResponse getWeather(String city) {

        RestTemplate restTemplate = new RestTemplate();
        String url = String.format(BASE_URL, city, apiKey);

        String response = restTemplate.getForObject(url, String.class);

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response);

            String name = root.path("name").asText();
            double temp = root.path("main").path("temp").asDouble();
            double humidity = root.path("main").path("humidity").asDouble();
            String description = root.path("weather").get(0).path("description").asText();
            double windSpeed = root.path("wind").path("speed").asDouble();

            return new WeatherResponse(name, temp, humidity, description, windSpeed);

        } catch (Exception e) {
            throw new RuntimeException("Error parsing weather data");
        }
    }
}