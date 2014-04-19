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

    public String getHtmlDiff() {
        DiffType type = null;
        List<DiffEntry<Character>> diffs = diff();
        StringBuffer buf = new StringBuffer();

        for (DiffEntry<Character> entry : diffs) {
            if (type != entry.getType()) {
                if (type != null) {
                    buf.append("</span>");
                }
                buf.append("<span class=\"" + entry.getType().getName() + "\">");
                type = entry.getType();
            }
            buf.append(escapeHtml(entry.getValue()));
        }
        buf.append("</span>");
        return buf.toString();
    }

    private String escapeHtml(Character ch) {
        switch (ch) {
            case '<':
                return "&lt;";
            case '>':
                return "&gt;";
            case '"':
                return "\\&quot;";
            default:
                return ch.toString();
        }
    }

}
