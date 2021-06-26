package com.flag.travelplanner.poi.repository;

import com.flag.travelplanner.poi.entity.POI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import static java.sql.Types.BIGINT;

@Repository
public class POIRepositoryJdbc implements POIRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public long count() {
        long count = 0;
        try {
            count = jdbcTemplate.queryForObject("select count(*) from pois", Long.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public int create(POI poi) {
        String point = "POINT("+poi.getLat()+" "+poi.getLng()+")";
        String sqlQuery = "insert ignore into pois (poiId, name, geoLocation, imageUrl, description, popularity) " +
                "values (?, ?, ?, ?, ?, ?)";

        return jdbcTemplate.update(sqlQuery, poi.getPoiId(),
                poi.getName(),
                point,
                poi.getImageUrl(),
                poi.getDescription(),
                poi.getPopularity());
    }

    @Override
    public POI findById(long id) {
        String sqlQuery = "select poiId, name, ST_X(geoLocation) as lat, ST_Y(geoLocation) as lng," +
                " imageUrl, description, popularity from pois " +
                "where poiId = ?";

        POI poi = null;
        try {
            poi = jdbcTemplate.queryForObject(sqlQuery, new Object[]{id}, new int[]{BIGINT},
                    (rs, rowNum)-> new POI(rs.getInt("poiId"),
                            rs.getString("name"),
                            rs.getDouble("lat"),
                            rs.getDouble("lng"),
                            rs.getString("imageUrl"),
                            rs.getString("description"),
                            rs.getDouble("popularity")));
        } catch(Exception e) {
            e.printStackTrace();
        }
        return poi;
    }

    @Override
    public int update(POI poi) {
        String point = "POINT("+poi.getLat()+" "+poi.getLng()+")";
        String sqlQuery ="update pois set name = ?, geoLocation = ?, imageUrl = ?, description = ?, popularity = ? where poiId = ?";

        return jdbcTemplate.update(sqlQuery, poi.getPoiId(),
                poi.getName(),
                point,
                poi.getImageUrl(),
                poi.getDescription(),
                poi.getPopularity());
    }

    @Override
    public int deleteById(long id) {
        return jdbcTemplate.update("delete from pois where poiId = ?", id);
    }
}
