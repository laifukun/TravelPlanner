package com.flag.travelplanner.route.service;

import com.flag.travelplanner.route.entity.Route;
import com.flag.travelplanner.route.repository.RouteRepository;
import com.flag.travelplanner.user.entity.User;
import com.flag.travelplanner.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RouteServiceImpl implements RouteService{

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private UserService userService;

    @Override
    public Route retrieveRouteDetails(long id) {
        return routeRepository.findById(id);
    }

    @Override
    @Transactional
    public List<Route> retrieveAllRoutesOfUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        //User user = userService.getUserByUserName(username);

        return routeRepository.findRoutesByUser(username);
    }

    @Override
    public void updateRoute(Route route) {
        routeRepository.update(route);
    }

    @Override
    public void deleteRoute(long id) {
        routeRepository.deleteById(id);
    }

    @Override
    public Route saveRoute(Route route) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.getUserByUserName(username);
        route.setUser(user);
        return routeRepository.save(route);
    }
}
