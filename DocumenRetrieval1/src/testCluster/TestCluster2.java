/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testCluster;

import java.util.ArrayList;
import model.Document;
import model.InvertedIndex;
import model.Posting;

/**
 *
 * @author egaprasetyo
 */
public class TestCluster2 {
    public static void main(String[] args) {
        // seting dokumen
        Document doc1 = new Document(1, "Fahri Hamzah Usul Ibu Kota Pindah ke Kepulauan Seribu");
        Document doc2 = new Document(2, "Gaya Nyentrik Menteri Susi Saat Pimpin Penenggelaman 13 Kapal Vietnam di Kalbar");
        Document doc3 = new Document(3, "TNI AL: KRI Tjiptadi-381 Diprovokasi Kapal Pengawas Ikan Vietnam ");

        // buat object invertedIndex
        InvertedIndex index = new InvertedIndex();
        // lakukan stemming untuk semua dokumen
        doc1.IndonesiaStemming();
        doc2.IndonesiaStemming();
        doc3.IndonesiaStemming();
        // tmbahkan document ke index
        index.addNewDocument(doc1);
        index.addNewDocument(doc2);
        index.addNewDocument(doc3);
        // bikin dictionary
        index.makeDictionaryWithTermNumber();
        // bikin preclustering
        index.clustering();

        for (int i = 0; i < index.getListOfCluster().size(); i++) {
            System.out.println("cluster = " + i + ", center = " + index.getListOfCluster().get(i).getCenter().getId());
            for (int j = 0; j < index.getListOfCluster().get(i).getMember().size(); j++) {
                System.out.println("id dok : " + index.getListOfCluster().get(i).getMember().get(j).getId());
            }
        }
    }
   
}









