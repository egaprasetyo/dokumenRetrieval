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
 * @author puspaingtyas
 */
public class Document implements Comparable<Document>{

    private int id;
    private String content;

    public Document() {
    }

    public Document(String content) {
        this.content = content;
    }

    public Document(int id, String content) {
        this.id = id;
        this.content = content;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    public String[] getListofTerm() {
        String[] term = content.split("\\s+");
        for (int i = 0; i < term.length; i++) {
            term[i] = term[i].replaceAll("[^\\w]", "");
        }
        return term;
    }

    public ArrayList<Posting> getListofPosting() {
        String tempString[] = getListofTerm();
        ArrayList<Posting> result = new ArrayList<Posting>();

        for (int i = 0; i < tempString.length; i++) {
            if (i == 0) {
                Posting temPosting = new Posting(this, tempString[0]);
                result.add(temPosting);
            } else {
                Collections.sort(result);
                Posting temPosting = new Posting(this, tempString[i]);
                int indexCari = Collections.binarySearch(result, temPosting);
                if (indexCari < 0) {
                    result.add(temPosting);
                } else {
                    int tempNumber = result.get(indexCari).getNumberOfTerm()+1;
                    result.get(indexCari).setNumberOfTerm(tempNumber);
                }
            }
        }
        return result;
    }

    @Override
    public int compareTo(Document doc) {
         return id-doc.getId();
    }
}
