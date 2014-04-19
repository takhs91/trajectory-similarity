/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trajectorysimilarity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author takis
 */
public class TrajectorySimilarity {
    
    private static Coords[] first;
    private static Coords[] second;
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
//        
//        Coords[] first;
//        Coords[] second;
//        first = readDatafromFile("/10000Points/4798.txt");
//        second = readDatafromFile("/10000Points/5075.txt");
//        LcsCoordinates test = new LcsCoordinates(first, second, 0.01);
//        System.out.println("LCS: " + test.getLcsLength());
//        //   System.out.println("Edit Dist: " + test.getMinEditDistance());
//        //  System.out.println("Backtrack: " + test.backtrack().toString());
//        System.out.println("Similarity: " + test.getSimilarity());
//
//    }
    
    public static Double computeSimilarity(){
        if (first!=null && second!=null && getEpsilon()!=null){
            LcsCoordinates test = new LcsCoordinates(first, second, getEpsilon());
            return test.getSimilarity();
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
