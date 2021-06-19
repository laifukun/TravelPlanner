package com.flag.travelplanner.route.controller;

import com.flag.travelplanner.poi.entity.POI;
import com.flag.travelplanner.user.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/routes")
public class RouteController {

    @GetMapping(value="/{route_id}")
    @ResponseBody
    public List<POI> retrieveRoute(@PathVariable("route_id") long id) {
        return new ArrayList<>();
    }

    @PostMapping()
    @ResponseStatus(value= HttpStatus.CREATED)
    public void saveRoute(@RequestBody List<POI> poiList) {

    }

    @DeleteMapping()
    @ResponseStatus(value= HttpStatus.OK)
    public void deleteRoute(@PathVariable("route_id") long id) {

    }

    @PutMapping("/{route_id}")
    @ResponseStatus(value= HttpStatus.OK)
    public void updateRoute(@PathVariable("route_id") long id, @RequestBody List<POI> poiList) {

    }

}
