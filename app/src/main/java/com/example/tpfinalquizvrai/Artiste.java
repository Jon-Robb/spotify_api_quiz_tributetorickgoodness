package com.example.tpfinalquizvrai;

import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

public class Artiste {

//    Classe renfermant les informations pertinantes a garder lors d une requete JSON transformée en GSON

    String name;
    ArrayList<String> genres;
    Followers followers;
    int popularity;
    Vector<Image> images;

    // Pour avoir un int de la grosseur du vec d'images
    public int randomFromVecImg(){
        return new Random().nextInt(images.size());
    }
}
