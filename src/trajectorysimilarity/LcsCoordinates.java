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
public class LcsCoordinates extends LongestCommonSubsequence<Coords> {

    private Coords[] x;
    private Coords[] y;
    private double epsilon;

    public LcsCoordinates(Coords[] from, Coords[] to, double epsilon) {
        this.x = from;
        this.y = to;
        this.epsilon = epsilon;
    }

    @Override
    protected int lengthOfY() {
        return y.length;
    }

    @Override
    protected int lengthOfX() {
        return x.length;
    }

    @Override
    protected Coords valueOfX(int index) {
        return x[index];
    }

    @Override
    protected Coords valueOfY(int index) {
        return y[index];
    }

    @Override
    protected boolean equals(Coords x1, Coords y1) {
        return (null == x1 && null == y1) || x1.equals(y1, epsilon);
    }

}
