package com.example.external;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;

@Service
public class NasaFloodService {

    private final String NASA_URL =
            "https://eonet.gsfc.nasa.gov/api/v3/events";

    public List<String> getFloodEvents() {

        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(NASA_URL, String.class);

        List<String> floods = new ArrayList<>();

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response);

            JsonNode events = root.path("events");

            for (JsonNode event : events) {

                JsonNode categories = event.path("categories");

                for (JsonNode category : categories) {

                    if (category.path("title").asText().equalsIgnoreCase("Floods")) {
                        floods.add(event.path("title").asText());
                    }
                }
            }

        } catch (Exception e) {
            throw new RuntimeException("Error parsing NASA flood data");
        }

        return floods;
    }
}