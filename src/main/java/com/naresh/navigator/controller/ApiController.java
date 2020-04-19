package com.naresh.navigator.controller;

import com.naresh.navigator.model.AutocompleteResponse;
import com.naresh.navigator.model.Location;
import com.naresh.navigator.model.PlaceDetailsResponse;
import com.naresh.navigator.model.Prediction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ApiController {

    private static final String GMap_Url = "https://maps.googleapis.com/maps/api/";
    private static final String Api_Key = "";

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/hello-world")
    public String getAddress() {
        return "hello-world";
    }

    @GetMapping("/place/autocomplete")
    public Prediction[] getPlaceSuggestions(@RequestParam String placeName, @RequestParam String latitude,
                                            @RequestParam String longitude) {
        String url = GMap_Url + "place/autocomplete/json?input=" + placeName + "&location=" +
                latitude + "," + longitude + "&radius=500&key=" + Api_Key;
        AutocompleteResponse predictions = restTemplate.getForObject(url, AutocompleteResponse.class);
        return predictions.getPredictions();
    }

    @GetMapping("/place/details")
    public Location getPlaceDetails(@RequestParam String placeId) {
        String url = GMap_Url + "place/details/json?place_id=" + placeId + "&key=" + Api_Key;
        PlaceDetailsResponse placeDetails = restTemplate.getForObject(url, PlaceDetailsResponse.class);
        return placeDetails.getResult().getGeometry().getLocation();
    }
}
