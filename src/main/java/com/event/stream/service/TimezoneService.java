package com.event.stream.service;

import com.event.stream.model.TimeZoneResponse;
import com.event.stream.model.TimezoneInfo;
import com.event.stream.repository.TimezoneRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

@Service
public class TimezoneService {
    private static final String TIMEZONEDB_API_KEY = "6MLY29TZWH0R";
    private static final Logger logger = LoggerFactory.getLogger(TimezoneService.class);
    private final TimezoneRepository timezoneRepository;

    @PostConstruct
    public void init() throws IOException, URISyntaxException {
        // First we should retrieve and save timezones when the application starts
        retrieveAndSaveTimezones();
    }

    public TimezoneService(TimezoneRepository timezoneRepository) {
        this.timezoneRepository = timezoneRepository;
    }

    public List<TimezoneInfo> retrieveAndSaveTimezones() throws IOException, URISyntaxException {
        // https://timezonedb.com/
        String apiUrl = "http://api.timezonedb.com/v2.1/list-time-zone?key=" + TIMEZONEDB_API_KEY + "&format=json";

        // Make an HTTP (not HTTPS) GET request to the API
        URI uri = new URI(apiUrl);
        String jsonResponse = getString(uri.toURL());
        ObjectMapper objectMapper = new ObjectMapper();
        TimeZoneResponse timeZoneResponse = objectMapper.readValue(jsonResponse, TimeZoneResponse.class);
        logger.info("Timezone data retrieved: {} zones", timeZoneResponse.getZones().size());
        return timezoneRepository.saveAll(timeZoneResponse.getZones());
    }

    @NotNull
    private static String getString(URL url) throws IOException {
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
        return response.toString();
    }

    public List<TimezoneInfo> getAllTimeZones() {
        return timezoneRepository.findAll();
    }

    public List<TimezoneInfo> getTimezoneByCountryCode(String countryCode){
        return timezoneRepository.findTimezoneInfoByCountryCode(countryCode);
    }
}
