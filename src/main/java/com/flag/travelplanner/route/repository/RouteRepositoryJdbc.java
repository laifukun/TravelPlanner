package com.flag.travelplanner.route.repository;

import com.flag.travelplanner.poi.entity.POI;
import com.flag.travelplanner.route.entity.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static java.sql.Types.BIGINT;
import static java.sql.Types.VARCHAR;

@Repository
public class RouteRepositoryJdbc implements RouteRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public long count() {
        long count = 0;
        try {
            count = jdbcTemplate.queryForObject("select count(*) from routes", Long.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public Route save(Route route) {
        //String startPoint = "POINT("+route.getStartLat()+" "+route.getStartLng()+")";
       // String endPoint = "POINT("+route.getEndLat()+" "+route.getEndLng()+")";
        String sqlRoute = "insert ignore into routes " +
                "(name, startAddress, endAddress, createdBy) " +
                "values ( ?, ?, ?, ?)";
        String getLastId = "select last_insert_id()";
        String sqlPOIRoute = "insert into poi_route (poiId, routeId) values(?, ?)";

        List<Object[]> poiRouteBatchArgs = new ArrayList<>();

       // String sqlUserRoute = "insert into route_user (routeId, userId) values(?,?)";

        try {
            jdbcTemplate.update(sqlRoute,
                    route.getName(),
                    route.getStartAddress(),
                    route.getEndAddress(),
                    route.getUser().getUsername());
            long routeId = jdbcTemplate.queryForObject(getLastId, Long.class);
            route.setRouteId(routeId);
            for (POI poi : route.getPoiList()) {
                Object [] obj = new Object[]{poi.getPoiId(), routeId};
                poiRouteBatchArgs.add(obj);
            }
            jdbcTemplate.batchUpdate(sqlPOIRoute, poiRouteBatchArgs);

        } catch(Exception e) {
            e.printStackTrace();
        }
        return route;
    }

    @Override
    public Route findById(long id) {

        Route route = null;
        String sqlQuery = "select * from routes where routeId = ?";
        String poiQuery = "select pois.poiId, name, ST_X(geoLocation) as lat, ST_Y(geoLocation) as lng, " +
                " imageUrl, description, popularity  from pois " +
                "inner join poi_route on poi_route.poiId=pois.poiId where poi_route.routeId = ?";

        try {
            route = jdbcTemplate.queryForObject(sqlQuery, new Object[]{id}, new int[]{BIGINT},
                    (rs, rowNum)-> new Route(rs.getInt("routeId"),
                            rs.getString("name"),
                            rs.getDate("createTime"),
                            rs.getString("startAddress"),
                            rs.getString("endAddress")));
            List<POI> POIs = jdbcTemplate.query(poiQuery, new Object[]{id}, new int[]{BIGINT},
                    (rs, rowNum)-> new POI(rs.getInt("poiId"),
                            rs.getString("name"),
                            rs.getDouble("lat"),
                            rs.getDouble("lng"),
                            rs.getString("imageUrl"),
                            rs.getString("description"),
                            rs.getDouble("popularity")));


            route.setPoiList(POIs);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return route;
    }

    @Override
    public int update(Route route) {
        String sqlQuery ="update routes set name = ?, startAddress = ?, endAddress = ?, createdBy = ? where routeId = ?";
        String deletePOIRoute = "delete from poi_route where routeId = ?";
        String sqlPOIRoute = "insert into poi_route (poiId, routeId) values(?, ?)";

        List<Object[]> poiRouteBatchArgs = new ArrayList<>();
        for (POI poi : route.getPoiList()) {
            Object [] obj = new Object[]{poi.getPoiId(), route.getRouteId()};
            poiRouteBatchArgs.add(obj);
        }
        int row = 0;
        try {
            jdbcTemplate.update(sqlQuery, route.getName(),
                    route.getStartAddress(),
                    route.getEndAddress(),
                    route.getUser().getUsername(),
                    route.getRouteId());
            jdbcTemplate.update(deletePOIRoute, route.getRouteId());
            row = jdbcTemplate.batchUpdate(sqlPOIRoute, poiRouteBatchArgs).length;

        } catch(Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public int deleteById(long id) {
        String routeQuery = "delete from routes where routeId = ? ";
        int row = 0;
        try {
            row = jdbcTemplate.update(routeQuery, id);
        }catch(Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public List<Route> findRoutesByUser(String username) {
        String sqlQuery = "select * from routes where createdBy = ?";

        List<Route> routes = null;
        try {
            routes = jdbcTemplate.query(sqlQuery, new Object[]{username}, new int[]{VARCHAR},
                    (rs, rowNum)-> new Route(rs.getInt("routeId"),
                            rs.getString("name"),
                            rs.getDate("createTime"),
                            rs.getString("startAddress"),
                            rs.getString("endAddress")));
        } catch(Exception e) {
            e.printStackTrace();
        }
        return routes;
    }
}
