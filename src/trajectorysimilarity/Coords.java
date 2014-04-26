/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trajectorysimilarity;

import static java.lang.Math.abs;

/**
 *
 * @author takis
 */
public class Coords {

    private double latitude;
    private double longtitude;

    @Override
    public boolean equals(Object o) {
        Coords c = (Coords) o;
        return c.getLatitude() == getLatitude() && c.getLongtitude() == getLongtitude();
    }

    public boolean equals(Object o, double epsilon) {
        Coords c = (Coords) o;
        return (abs(c.getLatitude() - getLatitude()) < epsilon) && (abs(c.getLongtitude() - getLongtitude()) < epsilon);
    }

    public Coords(double x, double y) {
        super();
        this.latitude = x;
        this.longtitude = y;
    }
    
    
    @Override
    public String toString(){
        return "("+this.getLatitude()+","+this.getLongtitude()+")";
        
    }

    /**
     * @return the latitude
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * @param latitude the latitude to set
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * @return the longtitude
     */
    public double getLongtitude() {
        return longtitude;
    }

    /**
     * @param longtitude the longtitude to set
     */
    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
    }
}
