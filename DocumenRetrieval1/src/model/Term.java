/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class Term {

    private String term;
    private ArrayList<Posting> postingList = new ArrayList<Posting>();

    public Term() {
    }

    public int getNumberofTerm() {
        return postingList.size();
    }

    /**
     * @return the term
     */
    public String getTerm() {
        return term;
    }

    /**
     * @param term the term to set
     */
    public void setTerm(String term) {
        this.term = term;
    }

    /**
     * @return the postingList
     */
    public ArrayList<Posting> getPostingList() {
        return postingList;
    }

    /**
     * @param postingList the postingList to set
     */
    public void setPostingList(ArrayList<Posting> postingList) {
        this.postingList = postingList;
    }
}