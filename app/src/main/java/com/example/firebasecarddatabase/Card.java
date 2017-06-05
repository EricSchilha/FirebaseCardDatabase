package com.example.firebasecarddatabase;

/**
 * Created by Eric on 2017-05-16.
 */

public class Card {
    private String sName;
    //private String sID;
    private String sSet;
    private int nCMC;//Converted Mana Cost
    private int nPower;
    private int nToughness;

    public Card(){}//Needed for datasnapshot.getValue()

    public Card(String sName, /*String sID,*/ String sSet, int nCMC, int nPower, int nToughness) {
        this.sName = sName;
        //this.sID = sID;
        this.nCMC = nCMC;
        this.nPower = nPower;
        this.nToughness = nToughness;
        this.sSet = sSet;
    }

    public String getName(){
        return sName;
    }
/*
    public String getID() {
        return sID;
    }
*/
    public String getSet() {
        return sSet;
    }

    public int getCMC() {
        return nCMC;
    }

    public int getPower() {
        return nPower;
    }

    public int getToughness() {
        return nToughness;
    }
}
