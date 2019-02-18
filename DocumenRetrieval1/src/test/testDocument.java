/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import model.Document;

/**
 *
 * @author admin
 */
public class testDocument {
    public static void main(String[] args) {
        Document doc1 = new Document(1 , "computer information retrieval");
        Document doc2 = new Document(2 , "computer retrieval");
        Document doc3 = new Document(3 , "information");
        Document doc4 = new Document(4 , "computer information");
        
        String result[] = doc1.getListofTerm();
        for (int i = 0; i < result.length; i++) {
            System.out.println("term " + i + " = " + result[i]);
        }
        
        //test git
    }
}
