/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.ArrayList;
import model.Document;
import model.InvertedIndex;
import model.Posting;
import model.Term;

/**
 *
 * @author admin
 */
public class testDocument6 {

    public static void main(String[] args) {
        Document doc1 = new Document(1, "computer information retrieval");
        Document doc2 = new Document(2, "computer organization and architecture");
        Document doc3 = new Document(3, "machine learning architecture");

        InvertedIndex index = new InvertedIndex();
        index.addNewDocument(doc1);
        index.addNewDocument(doc2);
        index.addNewDocument(doc3);

        index.makeDictionary();

        for (int i = 0; i < index.getDictionary().size(); i++) {
            Term tempTerm = index.getDictionary().get(i);
            System.out.print(tempTerm.getTerm() + " , " + tempTerm.getNumberofDocument() + " => ");
            for (int j = 0; j < tempTerm.getNumberofDocument(); j++) {
                Posting tempPosting = tempTerm.getPostingList().get(j);
                Document tempDoc = tempPosting.getDocument();
                System.out.print("idDoc = " + tempDoc.getId() + " ");
            }
            System.out.println("");
        }

        //test git
    }
}
