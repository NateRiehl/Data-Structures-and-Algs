/*
 * Sample Visualizer Test for B-Tree 
 * CS216 Data Structures
 * -- Sunny Kim (skim@gettysburg.edu)
 * -- modifed by Ivaylo Ilinkin (to extend to B-Tree)
 * -- takes in a BTree as an array, and the degree (order) of the tree
 *    and visualizes the tree from root to leaf
 * -- takes in a tree as a treenode (to root) and visualize the tree
 * 
 * 
 */

import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.tree.*;

// to capture screen shots
import javax.imageio.*;
import java.awt.image.*;
import java.io.*;

public class BTreeVisTest {
    
    // takes in a BTree and array of data
    public static <E> void addNdraw(BTree<E> bt, E[] data) {
        
        String entries = "";                                       // for debugging
        E entry;
        BTreeVis<E> btreeVis;
        for (int i = 0; i < data.length; ++i) {
            entry = data[i];
            entries += entry + " ";                                // for debugging
            bt.add(entry);
            
            btreeVis = new BTreeVis<E>(bt.getRoot(), 
                                       "_after_" + entry + "_added" + 
                                       ((i < data.length-1) ? "_NEXT_ADD_" + data[i+1] : "_FINISHED"));  
            btreeVis.dispose();
        }
        System.err.println("BTree add order: " + entries);         // for debugging
    }

    private static class IntComparator implements Comparator<Integer> {
        public int compare(Integer i1, Integer i2) {
            return i1.compareTo(i2);
        }
    }
    
    private static class CharComparator implements Comparator<Character> {
        public int compare(Character i1, Character i2) {
            return i1.compareTo(i2);
        }
    }
    
    
    // an example usage
    public static void main(String[] args) {
        
        Integer[]   data1 = {40, 2, 17, 9, 38, 20, 12, 93, 8, 99, 95, 50, 39, 60, 25};
        Character[] data2 = {'Z', 'Y', 'X', 'W', 'V', 'U', 'T', 'S', 'R', 'Q', 'P', 'O', 'N', 
                             'M', 'L', 'K', 'J', 'I', 'H', 'G', 'F', 'E', 'D', 'C', 'B', 'A'};
        
        // create a 2-3 tree of Integer (B-tree of degree 3)
//        addNdraw(new BTree<Integer>(new IntComparator(), 3), data1);
        
        // create a 2-3-4 tree of Integer (B-tree of degree 4)
        addNdraw(new BTree<Integer>(new IntComparator(), 4), data1);
        
        // create a B-tree of degree 5
        //addNdraw(new BTree<Character>(new CharComparator(), 5), data2);
    }
}
