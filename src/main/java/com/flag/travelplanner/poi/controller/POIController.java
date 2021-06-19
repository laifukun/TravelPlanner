package com.flag.travelplanner.poi.controller;

import com.flag.travelplanner.poi.entity.POI;
import com.flag.travelplanner.poi.service.POIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/pois")
public class POIController {

  //  @Autowired
   // private POIService poiService;

    @GetMapping(value = "/{poi_id}")
    public POI getPOIDetails(@PathVariable("poi_id") long id) {
        return null;
    }

    @GetMapping(value = "/{city}")
    public POI getAllPOIsByCity(@PathVariable("city") String city) {
        return null;
    }


}
