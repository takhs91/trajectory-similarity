/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import org.jdesktop.swingx.mapviewer.Waypoint;

/**
 *
 * @author takis
 */
public class MyWaypoint extends Waypoint{
    private final Character id;

    public MyWaypoint(Character id, double latitude, double longitude) {
        super(latitude, longitude);
        this.id = id;
    }   

    /**
     * @return the id
     */
    public Character getId() {
        return id;
    }
}
