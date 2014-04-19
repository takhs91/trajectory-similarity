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

    double latitude;
    double longtitude;

    @Override
    public boolean equals(Object o) {
        Coords c = (Coords) o;
        return c.latitude == latitude && c.longtitude == longtitude;
    }

    public boolean equals(Object o, double epsilon) {
        Coords c = (Coords) o;
        return (abs(c.latitude - latitude) < epsilon) && (abs(c.longtitude - longtitude) < epsilon);
    }

    public Coords(double x, double y) {
        super();
        this.latitude = x;
        this.longtitude = y;
    }
    
    
    @Override
    public String toString(){
        return "("+this.latitude+","+this.longtitude+")";
        
    }
}
