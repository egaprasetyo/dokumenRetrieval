/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.File;
import java.util.ArrayList;
import model.Document;
import model.InvertedIndex;

/**
 *
 * @author admin
 */
public class testFileDokumen2 {
    public static void main(String[] args) {
        File dir = new File("folderDoc");
        InvertedIndex index = new InvertedIndex();
        index.readDirectory(dir);
        ArrayList<Document> listDoc = index.getListOfDocument();
        for (int i = 0; i < 10; i++) {
            Document doc = listDoc.get(i);
            System.out.println("Content : " + doc.getId());
            System.out.println(doc.getContent());
        }
    }
}
