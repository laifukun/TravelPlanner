package com.flag.travelplanner.route.controller;

import com.flag.travelplanner.route.entity.Route;
import com.flag.travelplanner.route.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/routes")
public class RouteController {

    @Autowired
    private RouteService routeService;

    @GetMapping(value="/{route_id}")
    @ResponseBody
    public Route retrieveRoute(@PathVariable("route_id") long id) {
        return routeService.retrieveRoute(id);
    }

    @PostMapping()
    @ResponseStatus(value= HttpStatus.CREATED)
    @ResponseBody
    public Route saveRoute(@RequestBody Route route) {
        return routeService.saveRoute(route);
    }

    @DeleteMapping()
    @ResponseStatus(value= HttpStatus.OK)
    public void deleteRoute(@PathVariable("route_id") long id) {
        routeService.deleteRoute(id);
    }

    @PutMapping("/{route_id}")
    @ResponseStatus(value= HttpStatus.OK)
    public void updateRoute(@PathVariable("route_id") long id, @RequestBody Route route) {
         routeService.updateRoute(route);
    }

}
