/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trajectorysimilarity;

import static java.lang.Math.max;
import java.util.ArrayList;
import java.util.List;

public abstract class LongestCommonSubsequence<VALUE> {

    private int[][] c;
    private ArrayList<VALUE> backtrack;

    /**
     * A constructor for classes inheriting this one, allowing them to do some
     * initialization before setting the values of X and Y. Once the
     * initialization is complete, the inheriting class must call
     * initValues(VALUE[] x, VALUE[] y)
     */
    protected LongestCommonSubsequence() {

    }

    protected abstract int lengthOfY();

    protected abstract int lengthOfX();

    protected abstract VALUE valueOfX(int index);

    protected abstract VALUE valueOfY(int index);

    protected boolean equals(VALUE x1, VALUE y1) {
        return (null == x1 && null == y1) || x1.equals(y1);
    }

    private boolean isXYEqual(int i, int j) {
        return equals(valueOfXInternal(i), valueOfYInternal(j));
    }

    private VALUE valueOfXInternal(int i) {
        return valueOfX(i - 1);
    }

    private VALUE valueOfYInternal(int j) {
        return valueOfY(j - 1);
    }

    public void calculateLcs() {
        if (c != null) {
            return;
        }
        c = new int[lengthOfX() + 1][];
        for (int i = 0; i < c.length; i++) {
            c[i] = new int[lengthOfY() + 1];
        }

        for (int i = 1; i < c.length; i++) {
            for (int j = 1; j < c[i].length; j++) {
                if (isXYEqual(i, j)) {
                    c[i][j] = c[i - 1][j - 1] + 1;
                } else {
                    c[i][j] = max(c[i][j - 1], c[i - 1][j]);
                }
            }
        }
    }

//Method needed for the most similar subtrajectory in order to know
    //where to search after
    public Integer calculateLcsGetFirstMatch() {
//        if (c != null) {
//            return;
//        }
        Integer match = null;
        boolean flag = true;
        c = new int[lengthOfX() + 1][];
        for (int i = 0; i < c.length; i++) {
            c[i] = new int[lengthOfY() + 1];
        }

        for (int i = 1; i < c.length; i++) {
            for (int j = 1; j < c[i].length; j++) {
                if (isXYEqual(i, j)) {
                    c[i][j] = c[i - 1][j - 1] + 1;
                    if (flag && j!=1) {
                        match = j-1;
                        //match = j;
                        flag = false;
                    }
                } else {
                    c[i][j] = max(c[i][j - 1], c[i - 1][j]);
                }
            }
        }
        return match;
    }

    public int getLcsLength() {
        calculateLcs();

        return c[lengthOfX()][lengthOfY()];
    }

    public double getSimilarity() {
        calculateLcs();

        return ((double) c[lengthOfX()][lengthOfY()] / Math.min(lengthOfX(), lengthOfY()));
    }



    public List<VALUE> backtrack() {
        calculateLcs();
        if (this.backtrack == null) {
            this.backtrack = new ArrayList<VALUE>();
            backtrack(lengthOfX(), lengthOfY());
        }
        return this.backtrack;
    }

    public void backtrack(int i, int j) {
        calculateLcs();

        if (i == 0 || j == 0) {
            return;
        } else if (isXYEqual(i, j)) {
            backtrack(i - 1, j - 1);
            backtrack.add(valueOfXInternal(i));
        } else {
            if (c[i][j - 1] > c[i - 1][j]) {
                backtrack(i, j - 1);
            } else {
                backtrack(i - 1, j);
            }
        }
    }





    @Override
    public String toString() {
        calculateLcs();

        StringBuffer buf = new StringBuffer();
        buf.append("  ");
        for (int j = 1; j <= lengthOfY(); j++) {
            buf.append(valueOfYInternal(j));
        }
        buf.append("\n");
        buf.append(" ");
        for (int j = 0; j < c[0].length; j++) {
            buf.append(Integer.toString(c[0][j]));
        }
        buf.append("\n");
        for (int i = 1; i < c.length; i++) {
            buf.append(valueOfXInternal(i));
            for (int j = 0; j < c[i].length; j++) {
                buf.append(Integer.toString(c[i][j]));
            }
            buf.append("\n");
        }
        return buf.toString();
    }

    public void print() {
        calculateLcs();

        System.out.print("  ");
        for (int j = 1; j <= lengthOfY(); j++) {
            System.out.print(valueOfYInternal(j));
        }
        System.out.print("\n");
        System.out.print(" ");

        for (int j = 0; j < c[0].length; j++) {
            System.out.print(Integer.toString(c[0][j]));
        }
        System.out.print("\n");
        for (int i = 1; i < c.length; i++) {
            System.out.print(valueOfXInternal(i));
            for (int j = 0; j < c[i].length; j++) {
                System.out.print(Integer.toString(c[i][j]));
            }
            System.out.print("\n");
        }

    }





}
