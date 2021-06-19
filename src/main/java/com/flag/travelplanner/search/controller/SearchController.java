package com.flag.travelplanner.search.controller;

import com.flag.travelplanner.poi.entity.POI;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/search")
public class SearchController {

    @GetMapping("{keyword}")
    @ResponseBody
    public List<POI> searchPOIByKeyword(@PathVariable("keyword") String keyword) {
        return new ArrayList<>();
    }

    @GetMapping(value = "/{lat}/{lng}/{bound}")
    public List<POI> searchNearbyPOIs(@PathVariable("lat") double lat, @PathVariable("lng") double lng,
                                @PathVariable("bound") double bound) {
        return new ArrayList<>();
    }
}
