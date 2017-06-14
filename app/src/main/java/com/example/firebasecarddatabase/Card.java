package com.example.firebasecarddatabase;

/**
 * Created by Eric on 2017-05-16.
 */

public class Card {
    String sName;
    //private String sID;
    String sSet;
    int nCMC;//Converted Mana Cost
    int nPower;
    int nToughness;//These should be private, but that was causing problems

    public Card(){}//Needed for datasnapshot.getValue()

    public Card(String sName, /*String sID,*/ String sSet, int nCMC, int nPower, int nToughness) {
        this.sName = sName;
        //this.sID = sID;
        this.nCMC = nCMC;
        this.nPower = nPower;
        this.nToughness = nToughness;
        this.sSet = sSet;
    }
//Methods below don't work for what they were supposed to do
/*    public String getName(){
        return sName;
    }

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
    */
}
