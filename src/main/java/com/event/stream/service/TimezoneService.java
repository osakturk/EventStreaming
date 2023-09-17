package com.event.stream.service;

import com.event.stream.model.TimeZoneResponse;
import com.event.stream.model.TimezoneInfo;
import com.event.stream.repository.TimezoneRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@Service
public class TimezoneService {
    private static final String TIMEZONEDB_API_KEY = "6MLY29TZWH0R";

    private final TimezoneRepository timezoneRepository;

    public TimezoneService(TimezoneRepository timezoneRepository) {
        this.timezoneRepository = timezoneRepository;
    }

    public List<TimezoneInfo> retrieveAndSaveTimezones() throws IOException {
        String apiUrl = "http://api.timezonedb.com/v2.1/list-time-zone?key=" + TIMEZONEDB_API_KEY + "&format=json";

        // Make an HTTP GET request to the API
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        // Read the response from the API
        Scanner scanner = new Scanner(connection.getInputStream());
        StringBuilder response = new StringBuilder();
        while (scanner.hasNextLine()) {
            response.append(scanner.nextLine());
        }
        scanner.close();

        // Parse the JSON response to get the time zone ID
        String jsonResponse = response.toString();
        ObjectMapper objectMapper = new ObjectMapper();
        TimeZoneResponse timeZoneResponse = objectMapper.readValue(jsonResponse, TimeZoneResponse.class);


        return timezoneRepository.saveAll(timeZoneResponse.getZones());
    }

    public List<TimezoneInfo> getTimezoneByCountryCode(String countryCode){
        return timezoneRepository.findTimezoneInfoByCountryCode(countryCode);
    }
}
