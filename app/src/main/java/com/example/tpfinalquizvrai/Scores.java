package com.example.tpfinalquizvrai;

import java.io.Serializable;
import java.util.ArrayList;

public class Scores implements Serializable {

//    Classe qui sert a etre une liste de score en memoire pour la serialiser

    private ArrayList<Score> arrayScores;

    public Scores(){
        this.arrayScores = new ArrayList<>();
    }

    public ArrayList<Score> getArrayScores() {
        return arrayScores;
    }

    public void updateArrayScore(Score score) {
        this.arrayScores.add(score);
    }
}
