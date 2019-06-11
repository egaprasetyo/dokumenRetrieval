/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testCluster;

import java.io.File;
import java.util.ArrayList;
import model.Document;
import model.InvertedIndex;
import model.Posting;

/**
 *
 * @author egaprasetyo
 */
public class TestCluster3 {

    public static void main(String[] args) {
        // buat object invertedIndex
        InvertedIndex index = new InvertedIndex();

        File file = new File("50 Text Lagu");
        index.readDirectory(file);

        // bikin preclustering
        index.preClustering();

        index.clustering();

        for (int i = 0; i < index.getListOfCluster().size(); i++) {
            System.out.println("cluster = " + i + ", center = " + index.getListOfCluster().get(i).getCenter().getId());
            for (int j = 0; j < index.getListOfCluster().get(i).getMember().size(); j++) {
                System.out.println("id dok : " + index.getListOfCluster().get(i).getMember().get(j).getId());
            }
        }
    }

}

