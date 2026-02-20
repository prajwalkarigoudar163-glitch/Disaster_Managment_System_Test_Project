package com.example.external;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import com.example.DTO.EarthquakeResponse;

@Service
public class EarthquakeService {

    private final String USGS_URL =
            "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_day.geojson";

    public List<EarthquakeResponse> getEarthquakes() {

        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(USGS_URL, String.class);

        List<EarthquakeResponse> earthquakes = new ArrayList<>();

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response);

            JsonNode features = root.path("features");

            for (JsonNode feature : features) {

                double mag = feature.path("properties").path("mag").asDouble();

                // Only significant earthquakes
                if (mag >= 4.0) {

                    String place = feature.path("properties").path("place").asText();
                    long timestamp = feature.path("properties").path("time").asLong();

                    String formattedTime = java.time.Instant
                        .ofEpochMilli(timestamp)
                        .atZone(java.time.ZoneId.systemDefault())
                        .toLocalDateTime()
                        .toString();
                    int tsunami = feature.path("properties").path("tsunami").asInt();

                    double longitude = feature.path("geometry")
                            .path("coordinates").get(0).asDouble();

                    double latitude = feature.path("geometry")
                            .path("coordinates").get(1).asDouble();

                    earthquakes.add(
                            new EarthquakeResponse(place, mag, formattedTime,
                                    tsunami, latitude, longitude)
                    );
                }
            }

        } catch (Exception e) {
            throw new RuntimeException("Error parsing earthquake data");
        }

        return earthquakes;
    }
}