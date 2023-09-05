package com.example.tpfinalquizvrai;

import java.util.ArrayList;
import java.util.Collections;

public class Artistes {

    //Classe servant a stocker plusieurs artistes a la fois

    ArrayList<Artiste> artists;

    //Pour recevoir un arrayList d un nombre n d artistes piges au hasard
    public ArrayList<Artiste> getNArtists(int nb) {
        Collections.shuffle(this.artists);
        ArrayList<Artiste> arrArt = new ArrayList<>();
        if (this.artists.size() < nb){
            nb = this.artists.size();
        }
        for (int i = 0; i < nb; i++){
            arrArt.add(this.artists.get(i));
        }
        return arrArt;
    }

//  Pour aller chercher un artiste en particulier avec son nom
    public Artiste getArtiste(String nomArtiste){
        Artiste art = new Artiste();

        for (Artiste artiste : this.artists){
            if (artiste.name.equals(nomArtiste)){
                art = artiste;
            }
        }
        return art;
    }


}
