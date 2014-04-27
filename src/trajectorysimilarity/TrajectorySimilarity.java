/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trajectorysimilarity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author takis
 */
public class TrajectorySimilarity {

    private static Coords[] first;
    private static Coords[] second;
    private static Coords[] subset;
    private static Double epsilon;

//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String[] args) {
////        LcsString seq = new LcsString("<p>the quick brown fox</p>", "<p>the <b>Fast</b> brown dog</p>");
////        System.out.println("LCS: " + seq.getLcsLength());
////        System.out.println("Edit Dist: " + seq.getMinEditDistance());
////        System.out.println("Backtrack: " + seq.backtrack());
//
//        //     Coords[] first;
//        //    Coords[] second;
//        first = readDatafromFile("/1000Points/5099.txt");
//        second = readDatafromFile("/10000Points/5075.txt");
//        LcsCoordinates test = new LcsCoordinates(first, second, 0.01);
//        System.out.println("LCS: " + test.calculateLcsGetFirstMatch());
//        System.out.println("LCS: " + test.getLcsLength());
//        //   System.out.println("Edit Dist: " + test.getMinEditDistance());
//        //  System.out.println("Backtrack: " + test.backtrack().toString());
//        System.out.println("Similarity: " + test.getSimilarity());
//        epsilon = 0.01;
//        System.out.println("most similar: " + getMostSimilarSubset(999));
//
//    }

    public static Double computeSimilarity() {
        if (first != null && second != null && getEpsilon() != null) {
            LcsCoordinates test = new LcsCoordinates(first, second, getEpsilon());
            return test.getSimilarity();
        }
        return null;
    }

    /*O((n * n+ delta)*number of elements that two trajectories have in common) computationaly
     expensive but makes sure to find a subset with the highest similarity*/
    public static Double getMostSimilarSubset(int delta) {
        if (first != null && second != null && getEpsilon() != null) {
            if (delta <= 0 || delta >= first.length) {
                System.err.println("Delta has to be between 0 and Lq");
                return null;
            }
            int size = first.length + delta;
            if (second.length < size) {
                System.err.println("Ls must be greater than Lq");
                return null;
            }
            int i = 0;
            Double max = 0.0;
            int maxi = 0;
            while (i < second.length - size) {
                Coords[] temp = new Coords[size];
                System.arraycopy(second, i, temp, 0, size);
                LcsCoordinates test = new LcsCoordinates(first, temp, getEpsilon());
                Integer lcs = test.calculateLcsGetFirstMatch();
                Double sim = test.getSimilarity();
                if (sim > max) {
                    max = sim;
                    maxi = i;
                }
                if (lcs != null && lcs != 0) {
                    i = i + lcs;
                    //in a region containing matches the next region that possibly holds best similarity
                    //should be starting from the first match after the current position
                } else {
                    i = i + size + 1;
                    //nomatch at this space, jump over to the next region
                }
                temp = null;
            }
            //get the actual substring
            Coords[] temp = new Coords[size];
            System.arraycopy(second, maxi, temp, 0, size);
            //LcsCoordinates test = new LcsCoordinates(first, temp, getEpsilon());
            //System.out.println(test.backtrack().toString());
            //System.out.println(Arrays.toString(temp));
            setSubset(temp);
           // writeCoordsToFile(temp);
            return max;
        }
        return null;

    }

    public static Coords[] readDatafromFile(String file) {
        try (BufferedReader br = new BufferedReader(new FileReader("/home/takis/NetBeansProjects/TrajectorySimilarity/datasets" + file))) {
            String sCurrentLine;
            ArrayList<Coords> temp = new ArrayList<>();
            while ((sCurrentLine = br.readLine()) != null) {
                String[] split = sCurrentLine.split(",");
                Coords coord = new Coords(Double.parseDouble(split[2]), Double.parseDouble(split[3]));
                temp.add(coord);
            }
            Coords[] array = new Coords[temp.size()];
            return temp.toArray(array);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void writeCoordsToFile(Coords[] coords) {
        try {

            String content = "This is the content to write into file";
            File file = new File("/home/takis/NetBeansProjects/TrajectorySimilarity/subsets/subset.txt");

            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            for (Coords coord : coords) {
                bw.write(coord.getLatitude() + "," + coord.getLongtitude() + "\n");
            }

            bw.close();

            System.out.println("Done");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return the first
     */
    public static Coords[] getFirst() {
        return first;
    }

    /**
     * @param aFirst the first to set
     */
    public static void setFirst(Coords[] aFirst) {
        first = aFirst;
    }

    /**
     * @return the second
     */
    public static Coords[] getSecond() {
        return second;
    }

    /**
     * @param aSecond the second to set
     */
    public static void setSecond(Coords[] aSecond) {
        second = aSecond;
    }

    /**
     * @return the epsilon
     */
    public static Double getEpsilon() {
        return epsilon;
    }

    /**
     * @param aEpsilon the epsilon to set
     */
    public static void setEpsilon(Double aEpsilon) {
        epsilon = aEpsilon;
    }

    /**
     * @return the subset
     */
    public static Coords[] getSubset() {
        return subset;
    }

    /**
     * @param aSubset the subset to set
     */
    public static void setSubset(Coords[] aSubset) {
        subset = aSubset;
    }

    public void listf(String directoryName, ArrayList<File> files) {
        File directory = new File(directoryName);

        // get all the files from a directory
        File[] fList = directory.listFiles();
        for (File file : fList) {
            if (file.isFile()) {
                files.add(file);
            } else if (file.isDirectory()) {
                listf(file.getAbsolutePath(), files);
            }
        }
    }

}
