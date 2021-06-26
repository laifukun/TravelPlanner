package com.flag.travelplanner.route.entity;

import com.flag.travelplanner.poi.entity.POI;
import com.flag.travelplanner.user.entity.User;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "routes")
public class Route implements Serializable {

    private static final long serialVersionUID = 670983485351766613L;
    @Id
    private long routeId;
    private String name;
    private Date createTime;
    private String startAddress;
    private String endAddress;

    @OneToMany
    private List<POI> poiList;

    @ManyToOne
    private User user;

    public Route(long routeId, String name, Date createTime, String startAddress, String endAddress) {
        this.routeId = routeId;
        this.name = name;
        this.createTime = createTime;
        this.startAddress = startAddress;
        this.endAddress = endAddress;
    }

    public long getRouteId() {
        return routeId;
    }

    public void setRouteId(long routeId) {
        this.routeId = routeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getStartAddress() {
        return startAddress;
    }

    public void setStartAddress(String startAddress) {
        this.startAddress = startAddress;
    }

    public String getEndAddress() {
        return endAddress;
    }

    public void setEndAddress(String endAddress) {
        this.endAddress = endAddress;
    }

    public List<POI> getPoiList() {
        return poiList;
    }

    public void setPoiList(List<POI> poiList) {
        this.poiList = poiList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
