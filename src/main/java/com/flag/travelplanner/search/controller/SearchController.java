package com.flag.travelplanner.search.controller;

import com.flag.travelplanner.poi.entity.POI;
import com.flag.travelplanner.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/search")
public class SearchController {

    @Autowired
    private SearchService searchService;

    /* Search POIs by keyword
    Keywords could be in name or description
     */
    @GetMapping("{keyword}")
    @ResponseBody
    public List<POI> searchPOIByKeyword(@PathVariable("keyword") String keyword) {

        return searchService.searchPOIByKeyword(keyword);
    }

    /*
    Search nearby POIs by three inputs:
    @lat: latitude of origin position
    @lng: longitude of origin position
    @range: search radius in kilometer
     */
    @GetMapping(value = "/{lat}/{lng}/{range}")
    public List<POI> searchNearbyPOIs(@PathVariable("lat") double lat, @PathVariable("lng") double lng,
                                @PathVariable("range") double range) {
        return searchService.searchNearbyPOIs(lat, lng, range*1000);
    }
}
