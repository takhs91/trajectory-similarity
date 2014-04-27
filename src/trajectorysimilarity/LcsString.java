/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trajectorysimilarity;

import java.util.List;

public class LcsString extends LongestCommonSubsequence<Character> {

    private String x;
    private String y;

    public LcsString(String from, String to) {
        this.x = from;
        this.y = to;
    }

    protected int lengthOfY() {
        return y.length();
    }

    protected int lengthOfX() {
        return x.length();
    }

    protected Character valueOfX(int index) {
        return x.charAt(index);
    }

    protected Character valueOfY(int index) {
        return y.charAt(index);
    }

}
