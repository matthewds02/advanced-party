package be.thomasmore.party.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Venue {
    @Id
    private Integer id;
    private String venueName;
    private String linkMoreInfo;
    private int capacity;
    private boolean foodProvided;
    private boolean indoor;
    private boolean outdoor;
    private boolean freeParkingAvailable;
    private String city;
    private double distanceFromPublicTransportInKm;

    public Venue(String venueName, String linkMoreInfo) {
        this.venueName = venueName;
        this.linkMoreInfo = "https://www.youtube.com/watch?v=cFD9KnwQgQY";
        this.capacity = 100;
        this.foodProvided = true;
        this.indoor = true;
        this.outdoor = false;
        this.freeParkingAvailable = false;
        this.distanceFromPublicTransportInKm = 1.0;
        this.city = "Antwerpen";
    }

    public Venue() {
    }

    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public String getLinkMoreInfo() {
        return linkMoreInfo;
    }

    public void setLinkMoreInfo(String linkMoreInfo) {
        this.linkMoreInfo = linkMoreInfo;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean getfoodProvided() {
        return foodProvided;
    }

    public boolean getindoor() {
        return indoor;
    }

    public boolean getoutdoor() {
        return outdoor;
    }

    public boolean getfreeParkingAvailable() {
        return freeParkingAvailable;
    }

    public String getCity() {
        return city;
    }

    public double getDistanceFromPublicTransportInKm() {
        return distanceFromPublicTransportInKm;
    }
}