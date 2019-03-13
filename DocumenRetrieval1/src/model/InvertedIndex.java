/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author admin
 */
public class InvertedIndex {

    private ArrayList<Document> listofDocument = new ArrayList<Document>();
    private ArrayList<Term> dictionary = new ArrayList<Term>();

    public InvertedIndex() {
    }

    public void addNewDocument(Document document) {
        getListofDocument().add(document);
    }

    public ArrayList<Posting> getUnsortedPostingList() {
        ArrayList<Posting> list = new ArrayList<Posting>();

        for (int i = 0; i < getListofDocument().size(); i++) {
            String[] termResult = getListofDocument().get(i).getListofTerm();
            for (int j = 0; j < termResult.length; j++) {
                Posting tempPosting = new Posting(getListofDocument().get(i), termResult[j]);
                list.add(tempPosting);
            }
        }
        return list;
    }

    public ArrayList<Posting> getUnsortedPostingListWithTermNumber() {
        ArrayList<Posting> list = new ArrayList<Posting>();

        for (int i = 0; i < getListofDocument().size(); i++) {
//            String[] termResult = getListofDocument().get(i).getListofTerm();
            ArrayList<Posting> postingDocument = getListofDocument().get(i).getListofPosting();
            Collections.sort(postingDocument);
            for (int j = 0; j < postingDocument.size(); j++) {
                Posting tempPosting = postingDocument.get(j);
                list.add(tempPosting);
            }
        }
        return list;
    }

    public ArrayList<Posting> getSortedPostingList() {
        ArrayList<Posting> list = new ArrayList<Posting>();
        list = this.getUnsortedPostingList();
        Collections.sort(list);
        return list;
    }

    public ArrayList<Posting> getSortedPostingListWithNumberTerm() {
        ArrayList<Posting> list = new ArrayList<Posting>();
        list = this.getUnsortedPostingListWithTermNumber();
        Collections.sort(list);
        return list;
    }

    public void makeDictionary() {
        ArrayList<Posting> list = getSortedPostingList();
        for (int i = 0; i < list.size(); i++) {
            if (getDictionary().isEmpty()) {
                Term term = new Term(list.get(i).getTerm());
                term.getPostingList().add(list.get(i));
                getDictionary().add(term);
            } else {
                Term tempTerm = new Term(list.get(i).getTerm());
                int position = Collections.binarySearch(getDictionary(), tempTerm); //keluar berupa index posisinya
                //kalo hasil position -1 maka tidak ada
                if (position < 0) {
                    //term baru
                    tempTerm.getPostingList().add(list.get(i));
                    getDictionary().add(tempTerm);
                } else {
                    getDictionary().get(position).getPostingList().add(list.get(i));
                    Collections.sort(getDictionary().get(position).getPostingList());
                }
                Collections.sort(getDictionary());
            }
        }
    }

    public void makeDictionaryWithTermNumber() {
        ArrayList<Posting> list = getSortedPostingListWithNumberTerm();
        for (int i = 0; i < list.size(); i++) {
            if (getDictionary().isEmpty()) {
                Term term = new Term(list.get(i).getTerm());
                term.getPostingList().add(list.get(i));
                getDictionary().add(term);
            } else {
                Term tempTerm = new Term(list.get(i).getTerm());
                int position = Collections.binarySearch(getDictionary(), tempTerm); //keluar berupa index posisinya
                //kalo hasil position -1 maka tidak ada
                if (position < 0) {
                    //term baru
                    tempTerm.getPostingList().add(list.get(i));
                    getDictionary().add(tempTerm);
                } else {
                    getDictionary().get(position).getPostingList().add(list.get(i));
                    Collections.sort(getDictionary().get(position).getPostingList());
                }
                Collections.sort(getDictionary());
            }
        }
    }

    public ArrayList<Posting> search(String query) {
        // buat index/dictionary
//        makeDictionary();
        String tempQuery[] = query.split(" ");
        ArrayList<Posting> result = new ArrayList<Posting>();
        for (int i = 0; i < tempQuery.length; i++) {
            String string = tempQuery[i];
            if (i == 0) {
                result = searchOneWord(string);
            } else {
                ArrayList<Posting> result1 = searchOneWord(string);
                result = intersection(result, result1);
            }
        }
        return result;
    }

    public ArrayList<Posting> intersection(ArrayList<Posting> p1,
            ArrayList<Posting> p2) {
        if (p1 == null || p2 == null) {
            return new ArrayList<>();
        }

        ArrayList<Posting> postings = new ArrayList<>();
        int p1Index = 0;
        int p2Index = 0;

        Posting post1 = p1.get(p1Index);
        Posting post2 = p2.get(p2Index);

        while (true) {
            if (post1.getDocument().getId() == post2.getDocument().getId()) {
                try {
                    postings.add(post1);
                    p1Index++;
                    p2Index++;
                    post1 = p1.get(p1Index);
                    post2 = p2.get(p2Index);
                } catch (Exception e) {
                    break;
                }

            } else if (post1.getDocument().getId() < post2.getDocument().getId()) {
                try {
                    p1Index++;
                    post1 = p1.get(p1Index);
                } catch (Exception e) {
                    break;
                }

            } else {
                try {
                    p2Index++;
                    post2 = p2.get(p2Index);
                } catch (Exception e) {
                    break;
                }
            }
        }
        return postings;
    }

    public ArrayList<Posting> searchOneWord(String word) {
        Term tempTerm = new Term(word);
        if (getDictionary().isEmpty()) {
            // dictionary kosong
            return null;
        } else {
            int positionTerm = Collections.binarySearch(dictionary, tempTerm);
            if (positionTerm < 0) {
                // tidak ditemukan
                return null;
            } else {
                return dictionary.get(positionTerm).getPostingList();
            }
        }
    }

    /**
     * @return the listofDocument
     */
    public ArrayList<Document> getListofDocument() {
        return listofDocument;
    }

    /**
     * @param listofDocument the listofDocument to set
     */
    public void setListofDocument(ArrayList<Document> listofDocument) {
        this.listofDocument = listofDocument;
    }

    /**
     * @return the dictionary
     */
    public ArrayList<Term> getDictionary() {
        return dictionary;
    }

    /**
     * @param dictionary the dictionary to set
     */
    public void setDictionary(ArrayList<Term> dictionary) {
        this.dictionary = dictionary;
    }

    /**
     * Fungsi mencari frequensi sebuah term dalam sebuah index
     *
     * @param term
     * @return
     */
    public int getDocumentFrequency(String term) {
        Term tempTerm = new Term(term);
        int index = Collections.binarySearch(dictionary, tempTerm);
        if (index > 0) {
            ArrayList<Posting> tempPosting = dictionary.get(index).getPostingList();
            return tempPosting.size();
        } else {
            return -1;
        }
    }

    /**
     * Fungsi untuk mencari inverse term dari sebuah index
     *
     * @param term
     * @return
     */
    public double getInverseDocumentFrequency(String term) {
        Term tempTerm = new Term(term);
        int index = Collections.binarySearch(dictionary, tempTerm);
        if (index > 0) {
            int N = listofDocument.size();
            int ni = getDocumentFrequency(term);
            return Math.log10(N / ni);
        } else {
            return 0.0;
        }
    }

    /**
     * Fungsi untuk mencari term frequency
     *
     * @param term
     * @param idDocument
     * @return
     */
    public int getTermFrequency(String term, int idDocument) {
        Document document = new Document();
        document.setId(idDocument);
        int pos = Collections.binarySearch(listofDocument, document);
        if (pos >= 0) {
            ArrayList<Posting> tempPosting = listofDocument.get(pos).getListofPosting();
            Posting posting = new Posting();
            posting.setTerm(term);
            int postingIndex = Collections.binarySearch(tempPosting, posting);
            if (postingIndex >= 0) {
                return tempPosting.get(postingIndex).getNumberOfTerm();
            }
            return 0;
        }

        return 0;
    }

    /**
     * Fungsi untuk menghitung TF-IDF dari sebuah dokumen
     *
     * @param idDocument
     */
    public ArrayList<Posting> makeTFIDF(int idDocument) {
        Document doc = new Document();
        doc.setId(idDocument);
        int index = Collections.binarySearch(getListofDocument(), doc);
        if (index < 0) {
            return null;
        }
        doc = getListofDocument().get(index);
        ArrayList<Posting> result = doc.getListofPosting();
        for (int i = 0; i < result.size(); i++) {
            // weight = tf * idf
            double weight = result.get(i).getNumberOfTerm() * getInverseDocumentFrequency(result.get(i).getTerm());
            result.get(i).setWeight(weight);
        }

        return result;
    }

    /**
     * Fungsi perkalian inner product dari PostingList Atribut yang dikalikan
     * adalah atribut weight TFIDF dari posting
     *
     * @param p1
     * @param p2
     * @return
     */
    public double getInnerProduct(ArrayList<Posting> p1, ArrayList<Posting> p2) {
        double result = 0;
        for (int i = 0; i < p1.size(); i++) {
            int index = Collections.binarySearch(p2, p1.get(i));
            if (index >= 0) {
                result = result + (p1.get(i).getWeight() * p2.get(index).getWeight());
            }
        }

        return result;
    }

    /**
     * Fungsi untuk membentuk posting list dari sebuah query
     *
     * @param query
     * @return
     */
    public ArrayList<Posting> getQueryPosting(String query) {
        Document doc = new Document();
        doc.setContent(query);

        ArrayList<Posting> result = doc.getListofPosting();
        for (int i = 0; i < result.size(); i++) {
            // weight = tf * idf
            double weight = result.get(i).getNumberOfTerm() * getInverseDocumentFrequency(result.get(i).getTerm());
            result.get(i).setWeight(weight);
        }

        return result;
    }
    
    public double getConsineSimilarity(ArrayList <Posting> posting, ArrayList<Posting> posting1){
        return 0;
    }
    
    public ArrayList<Document> searchTFIDF (String query){
        return null;
    }
    
    public ArrayList<Document> searchCosineSimilarity(String query){
        return null;
    }
}
